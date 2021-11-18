package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class AdminHandler {

    @Autowired
    private AdminService adminService;

    @RequestMapping("/admin/do/login.html")
    public String doLogin(@RequestParam("loginAcct") String loginAcct, @RequestParam("userPswd") String userPswd, HttpSession session){

        // 调用service方法进行登陆检查
        // 这个方法如果能够返回admin对象表示成功，如果账号密码不正确则会抛出异常
        Admin admin = adminService.getAdminByLoginAcct(loginAcct,userPswd);

        session.setAttribute(CrowdConstant.ATTR_NAME_LOGIN_ADMIN,admin);

        //不带参数过去的话一般用重定向，带参数尽量少用转发
        return "redirect:/admin/to/main/page.html";
    }

    @RequestMapping("/admin/do/logOut.html")
    public String doLogOut(HttpSession session){

        //强制session失效
        session.invalidate();

        return "redirect:/admin/to/login/page.html";
    }

    @RequestMapping("/admin/get/page.html")
    public String getPageInfo(

            // 使用@RequestParam注解的defaultValue属性，指定默认值，在请求中没有携带参数时使用默认值
            // keyword默认使用空字符串，和sql语句配合实现两种情况适配
            @RequestParam(value = "keyword",defaultValue = "") String keyWord,

            // pageNum默认使用1
            @RequestParam(value = "pageNum",defaultValue = "1") Integer pageNum,

            // pageSize默认使用5
            @RequestParam(value = "pageSize", defaultValue = "5") Integer pageSize,

            ModelMap modelMap){

        PageInfo<Admin> pageInfo = adminService.getPageInfo(keyWord, pageNum, pageSize);

        modelMap.addAttribute(CrowdConstant.ATTR_NAME_PAGE_INFO,pageInfo);

        List<Admin> list = pageInfo.getList();

        //请求的转发方式，默认数据存放在requestScope里
        return "admin-page";
    }
}
