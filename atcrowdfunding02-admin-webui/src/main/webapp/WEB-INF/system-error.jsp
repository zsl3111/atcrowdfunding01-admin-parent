<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/10/29
  Time: 14:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="keys" content="">
    <meta name="author" content="">
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <link rel="stylesheet" href="bootstrap/css/bootstrap.min.css">
    <link rel="stylesheet" href="css/font-awesome.min.css">
    <link rel="stylesheet" href="css/login.css">
    <script src="jquery/jquery-2.1.1.min.js"></script>
    <script src="bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" >
        $(function(){
            $("#rollBack").click(function () {
                //相当于浏览器的后退按钮
                window.history.back();
            })
        })
    </script>
    <style>

    </style>
    <title>登录</title>
</head>
<body>
<nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
    <div class="container">
        <div class="navbar-header">
            <div><a class="navbar-brand" href="index.html" style="font-size:32px;">尚筹网-创意产品众筹平台</a></div>
        </div>
    </div>
</nav>

<div class="container">
    <h2 class="form-signin-heading" style="text-align: center;"><i class="glyphicon glyphicon-log-in"></i> 尚筹网系统消息</h2>
    <!--
     requestScope对应的是存放request域数据的map
     requestScope.exception相当于request.getAttribute("exception")
     requestScope.exception.message相当于exception.getMessage()
     -->
    <!-- 固定写法，requestScope.exception.message就可以获取到异常信息 -->
    <h3 style="text-align: center;">${requestScope.exception.message}</h3>
    <button id="rollBack" class="btn btn-lg btn-success btn-block" style="width: 15%;margin: 50px auto 0px auto">点我返回上一步</button>
</div>
</body>
</html>