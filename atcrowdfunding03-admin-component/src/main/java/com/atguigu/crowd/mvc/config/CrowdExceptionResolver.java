package com.atguigu.crowd.mvc.config;

import com.atguigu.crowd.constant.CrowdConstant;
import com.atguigu.crowd.exception.LoginFailedException;
import com.atguigu.crowd.util.CrowdUtil;
import com.atguigu.crowd.util.ResultEntity;
import com.google.gson.Gson;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 异常处理类
 * @ControllerAdvice表示当前类是基于注解的异常处理器类
 * 基于xml配置异常映射的方式是用作view-controller作为跳转页面控制器的时候使用
 * 此类是基于注解的异常映射方式，用于非普通请求时使用（项目都是ajax请求）
 */

@ControllerAdvice
public class CrowdExceptionResolver {

    /**
     * 登录失败异常处理方法
     * @param exception
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @ExceptionHandler(value = LoginFailedException.class)
    public ModelAndView loginFailedException(LoginFailedException exception,HttpServletRequest request,HttpServletResponse response) throws Exception {

        String viewName = "admin-login";

        return commonResolve(viewName,exception,request,response);
    }


    /**
     * 算数异常处理方法（测试用）
     * @param exception 算数异常
     * @param request 当前请求对象
     * @param response 当前响应对象
     * @return 返回视图或响应体
     */
    @ExceptionHandler(value = ArithmeticException.class)
    public ModelAndView resolveArithmeticException(ArithmeticException exception,HttpServletRequest request,HttpServletResponse response) throws Exception {

        String viewName = "system-error";

        return commonResolve(viewName, exception, request, response);
    }

    /**
     * 空指针异常处理方法（测试用）
     * @param exception 空指针异常
     * @param request 当前请求对象
     * @param response 当前相应对象
     * @return 返回错误页面
     * @throws Exception 抛出异常
     */
    //@ExceptionHandler将一个具体的异常类型和一个方法关联起来
    @ExceptionHandler(value = NullPointerException.class)
    public ModelAndView resolveNullPointerException(NullPointerException exception, HttpServletRequest request, HttpServletResponse response
            ) throws Exception {

        //指定要去的页面
        String viewName = "system-error";

        return commonResolve(viewName,exception,request,response);

    }

    private ModelAndView commonResolve(
            // 异常处理完成后要去的地方
            String viewName,
            //实际捕获到的异常类型
            Exception exception,
            //当前请求对象
            HttpServletRequest request,
            //当前响应对象
            HttpServletResponse response) throws Exception{

        // 1.判断请求类型
        boolean requestType = CrowdUtil.judgeRequestType(request);

        // 2.如果是一个ajax请求，
        if(requestType){

            // 3.创建resultEntity对象
            ResultEntity<Object> resultEntity = ResultEntity.failed(exception.getMessage());

            // 4.创建Gson对象
            Gson gson = new Gson();

            // 5.将resultEntity对象转换为json字符串
            String json = gson.toJson(resultEntity);

            // 6.将json字符串作为响应体返回给浏览器
            // 与@responseBody效果一样，可以在success中获取json
            response.getWriter().write(json);

            // 7.由于上面已经通过原生的response对象返回了响应，所以不提供ModelAndView对象
            return null;
        }

        // 8.如果不是ajax请求，则将创建ModelAndView对象
        ModelAndView modelAndView = new ModelAndView();

        // 9.将exception对象存入modelAndView
        // 异常处理默认是往请求域中放exception对象，这里的objectName必须也为“exception”，否则请求域中拿不到异常   message
        modelAndView.addObject(CrowdConstant.ATTR_NAME_EXCEPTION,exception);

        // 10.设置对应的视图名称
        modelAndView.setViewName(viewName);

        // 11.返回modelAndView对象
        return modelAndView;
    }
}
