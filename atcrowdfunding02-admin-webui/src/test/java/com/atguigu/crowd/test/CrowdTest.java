package com.atguigu.crowd.test;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.mapper.AdminMapper;
import com.atguigu.crowd.service.api.AdminService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

// 在类上标记必要的注解，Spring整合Junit
@RunWith(SpringJUnit4ClassRunner.class)
//  加载xml文件
@ContextConfiguration(locations = {"classpath:spring-persist-mybatis.xml", "classpath*:spring-persist-tx.xml"})
public class CrowdTest {

    @Autowired
    private DataSource dataSource;

    @Autowired
    private AdminMapper adminMapper;

    @Autowired
    private AdminService adminService;

    @Test
    public void testPage(){
        //PageHelper.startPage(1,5);
        List list1 = adminMapper.selectAdminByKeyWord("tom");
        PageInfo pageInfo = new PageInfo(list1);
        //List list = pageInfo.getList();
        //Object o = list.get(0);
        System.out.println("-------------------------"+pageInfo);
       // System.out.println(o);
    }

    @Test
    public void test(){
        for (int i = 0; i < 238; i++) {
           adminMapper.insertSelective(new Admin("jerry"+i,"jerry"+i,"1","jerry"+i+"@qq.com",new Date().toString()));
        }
    }

    @Test
    public void testTx(){
        Admin admin = new Admin("jerry", "123123", "jerry", "jerry@qq.com", new Date().toString());
        adminService.saveAdmin(admin);
    }

    @Test
    public void testLog(){

        //获取logger对象，这里传入的class类就是打印日志的类
        Logger logger = LoggerFactory.getLogger(CrowdTest.class);

        // 根据不同的级别打印日志
        logger.debug("this is a debug level flag");
        logger.debug("this is a debug level flag");
        logger.debug("this is a debug level flag");

        logger.warn("this is a warn level flag");
        logger.warn("this is a warn level flag");
        logger.warn("this is a warn level flag");

        logger.info("this is a info level flag");
        logger.info("this is a info level flag");
        logger.info("this is a info level flag");

        logger.error("this is a error level flag");
        logger.error("this is a error level flag");
        logger.error("this is a error level flag");
    }
    @Test
    public void testAdmin(){
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String s = sdf.format(d);
        int i = adminMapper.insertSelective(new Admin(null, "tom2", "123123", "汤姆", "tom@qq.com",s));
        System.out.println(i);
    }
    /**
     * 测试数据库连接
     * @throws SQLException
     */
    @Test
    public void testConnection() throws SQLException {
        Connection connection = dataSource.getConnection();
        System.out.println(connection);

    }

}
