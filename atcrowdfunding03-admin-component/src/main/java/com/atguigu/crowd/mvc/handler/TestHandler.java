package com.atguigu.crowd.mvc.handler;

import com.atguigu.crowd.entity.Admin;
import com.atguigu.crowd.entity.ParamData;
import com.atguigu.crowd.entity.Student;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.service.api.AdminService;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class TestHandler {
    @Autowired
    private AdminService adminService;

    private Logger logger = LoggerFactory.getLogger(TestHandler.class);

    @RequestMapping("test/ssm.html")
    public String testSSM(ModelMap modelMap,HttpServletRequest request){

        System.out.println("-----------------------------------");

        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);

        logger.info("judgeRequestType="+judgeRequestType);

        List<Admin> adminList = adminService.getAll();

        System.out.println(adminList+"---------------------------------------------");

        modelMap.addAttribute("adminList",adminList);

        System.out.println(10 / 0);
       /* String a = null;
        System.out.println(a.length());*/

        return "target";
    }

    @RequestMapping("send/array.html")
    //参数带“[]”的原因是json从前端发送参数的时候自动把参数名后面加了“[]”，如果不加的话接收不到参数
    public String testReceiveArray(@RequestParam("array[]") List<Integer> array){
        for(Integer i : array){
            System.out.println(i);
        }
        System.out.println(10 / 0);
        return "target";
    }

    @RequestMapping("send/array/two.html")
    @ResponseBody
    //发送数组的方式2，通过建立一个实体类接收
    public String testReceiveArrayTwo(ParamData paramData){
        List<Integer> array = paramData.getArray();
        for(Integer i : array){
            System.out.println(i);
        }
        return "success";
    }

    @RequestMapping("send/array/three.html")
    @ResponseBody
    public String testReceiveArrayThree(@RequestBody List<Integer> array){

        for(Integer i : array){
            logger.info("number = "+i);
        }
        return "success";
    }

    @RequestMapping("send/array/compose.html")
    @ResponseBody
    //接收复杂对象，利用requestbody可以直接接收
    public String testReceiveArrayCompose(@RequestBody Student student){
        logger.info(student.toString());

        return "success";
    }

    @RequestMapping("send/array/compose.json")
    @ResponseBody
    public ResultEntity<Student> testResultEntity(@RequestBody Student student,HttpServletRequest request){

        boolean judgeRequestType = CrowdUtil.judgeRequestType(request);

        logger.info("judgeRequestType="+judgeRequestType);

        String a = null;
        System.out.println(a.length());

        return ResultEntity.successWithData(student);
    }
}
