package com.atguigu.crowd.service.impl;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.AdminExample;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminMapper adminMapper;

    @Override
    public void saveAdmin(Admin admin) {
        adminMapper.insertSelective(admin);
    }

    @Override
    public List<Admin> getAll() {
        return adminMapper.selectByExample(null);
    }

    @Override
    public Admin getAdminByLoginAcct(String loginAcct, String userPswd) {

        // 1.根据登录账号查询admin对象
        // ①.创建adminExample对象
        AdminExample example = new AdminExample();

        // ②.创建criteria对象
        AdminExample.Criteria criteria = example.createCriteria();

        // ③.在criteria对象中封装查询条件
        criteria.andLoginAcctEqualTo(loginAcct);

        // ④.调用adminMapper对象进行查询
        List<Admin> list = adminMapper.selectByExample(example);

        // 判断admin对象是否为null
        if(list == null || list.size() == 0){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        if(list.size() > 1){
            throw new LoginFailedException(CrowdConstant.MESSAGE_SYSTEM_ERROR_LOGIN_NOT_UNIQUE);
        }

        Admin admin = list.get(0);

        // 如果admin对象为null则抛出异常
        if(admin == null){
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 如果admin对象不为null则把密码从admin对象中取出
        String userPswdDB = admin.getUserPswd();

        // 将表单提交的明文密码进行加密
        userPswd = CrowdUtil.md5(userPswd);

        // object的工具方法，用来比较两个变量是否相等，避免抛出空指针的异常
        if(!Objects.equals(userPswd,userPswdDB)){

            //如果比较结果不一致则抛出异常
            throw new LoginFailedException(CrowdConstant.MESSAGE_LOGIN_FAILED);
        }

        // 吐过比较结果一致则返回admin对象
        return admin;
    }

    @Override
    public PageInfo<Admin> getPageInfo(String keyword, Integer pageNum, Integer pageSize) {

        // 1.调用pageHelper的静态方法开启分页功能，这里充分体现了pageHelper的非侵入式设计，原本的查询不必做任何修改
        // 开启分页功能以后，返回的list接口实际上是返回了Page类型（多态）,可以把他理解成ASCII码，英文字母也可以转换成数字，能互相转换
        PageHelper.startPage(pageNum,pageSize);

        // 2.执行查询
        List<Admin> admins = adminMapper.selectAdminByKeyWord(keyword);

        //封装到pageInfo对象中
        return new PageInfo(admins);
    }
}
