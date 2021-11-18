<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<!-- 此标签的作用是使jstl解析，如果不加的话会出现jstl不解析的情况 -->
<%@page isELIgnored="false" %>
<!DOCTYPE html>
<html>
<head>
    <title>Title</title>
    <!-- 为避免每次都要在前面加一长串jstl获取项目名，在前面加个base标签以便后续使用 -->
    <!-- http://localhost:8080/atcrowdfunding02-admin-webui/test/ssm.html -->
    <!--
        pageContext.request.contextPath前面的“/”要删掉，因为表达式自带“/”，
        后面的“/要留着，因为在下面调用base的时候如果在路径前面加了“/”就不参考base标签了，base就白写了
        页面上所有参照base标签的标签要在base标签后面
         -->
    <base href="http://${pageContext.request.serverName}:${pageContext.request.serverPort}${pageContext.request.contextPath}/">
    <script type="text/javascript" src="jquery/jquery-2.1.1.min.js"></script>
    <script type="text/javascript" src="layer/layer.js"></script>
    <script type="text/javascript">
        $(function(){
            $("#btn1").click(function(){
                $.ajax({
                    "url":"send/array.html",        //  请求目标资源的地址
                    "type":"post",                  //  请求方式
                    "data":{"array":[5,8,12]},      //  要发送的请求参数
                    "dataType":"text",              //  如何对待服务器端返回的数据
                    "success":function(response){   //  服务器端成功处理请求时调用的回调函数，response是响应体数据
                        alert(response);
                    },
                    "error":function(response){     //  服务器端处理请求失败后调用的回调函数
                        alert(response);
                    }
                })
            });
            $("#btn2").click(function(){
                $.ajax({
                    "url":"send/array/two.html",        //  请求目标资源的地址
                    "type":"post",                  //  请求方式
                    "data":{"array[0]":5,"array[1]":8,"array[2]":12},      //  要发送的请求参数
                    "dataType":"text",              //  如何对待服务器端返回的数据
                    "success":function(response){   //  服务器端成功处理请求时调用的回调函数，response是响应体数据
                        alert(response);
                    },
                    "error":function(response){     //  服务器端处理请求失败后调用的回调函数
                        alert(response);
                    }
                })
            });
            var array = [5,8,12];
            var stringify = JSON.stringify(array);
            $("#btn3").click(function(){
                $.ajax({
                    "url":"send/array/three.html",        //  请求目标资源的地址
                    "type":"post",                  //  请求方式
                    "data":stringify,      //  要发送的请求参数
                    "contentType":"application/json;charset=UTF-8", //设置请求体的内容类型，告诉服务器端本次请求的请求体是json类型(在请求体是requrest payload并且是json数据的时候需要加这个)
                    "dataType":"text",              //  如何对待服务器端返回的数据
                    "success":function(response){   //  服务器端成功处理请求时调用的回调函数，response是响应体数据
                        console.log(response)
                    },
                    "error":function(response){     //  服务器端处理请求失败后调用的回调函数
                        console.log(response)
                    }
                })
            });
            $("#btn4").click(function(){
               var student = {
                   "stuId":5,
                   "stuName":"tom",
                   "address":{
                       "province":"山东",
                       "city":"青岛",
                       "stress":"珠海街道"
                   },
                   "subjectList":[
                       {
                           "subjectName":"语文",
                           "subjectScore":100
                       },
                       {
                           "subjectName":"数学",
                           "subjectScore":99
                       }
                   ],
                   "map":{
                       "k1":"v1",
                       "k2":"v2"
                   }
               };
               student = JSON.stringify(student);
               $.ajax({
                   "url":"send/array/compose.html",        //  请求目标资源的地址
                   "type":"post",                  //  请求方式
                   "data":student,      //  要发送的请求参数
                   "contentType":"application/json;charset=UTF-8", //设置请求体的内容类型，告诉服务器端本次请求的请求体是json类型(在请求体是requrest payload并且是json数据的时候需要加这个)
                   "dataType":"text",              //  如何对待服务器端返回的数据
                   "success":function(response){   //  服务器端成功处理请求时调用的回调函数，response是响应体数据
                       console.log(response)
                   },
                   "error":function(response){     //  服务器端处理请求失败后调用的回调函数
                       console.log(response)
                   }
               })
            });
            $("#btn5").click(function(){
               var student = {
                   "stuId":5,
                   "stuName":"tom",
                   "address":{
                       "province":"山东",
                       "city":"青岛",
                       "stress":"珠海街道"
                   },
                   "subjectList":[
                       {
                           "subjectName":"语文",
                           "subjectScore":100
                       },
                       {
                           "subjectName":"数学",
                           "subjectScore":99
                       }
                   ],
                   "map":{
                       "k1":"v1",
                       "k2":"v2"
                   }
               };
               student = JSON.stringify(student);
               $.ajax({
                   "url":"send/array/compose.json",        //  请求目标资源的地址
                   "type":"post",                  //  请求方式
                   "data":student,      //  要发送的请求参数
                   "contentType":"application/json;charset=UTF-8", //设置请求体的内容类型，告诉服务器端本次请求的请求体是json类型(在请求体是requrest payload并且是json数据的时候需要加这个)
                   "dataType":"json",              //  如何对待服务器端返回的数据
                   "success":function(response){   //  服务器端成功处理请求时调用的回调函数，response是响应体数据
                       console.log(response)
                   },
                   "error":function(response){     //  服务器端处理请求失败后调用的回调函数
                       console.log(response)
                   }
               })
            });

            $("#btn6").click(function () {
               layer.msg("这是一个弹框");
            });
        })
    </script>
</head>
<body>
    <a href="test/ssm.html">测试整合ssm环境</a>

    <button id="btn1">send [5,8,12] one</button>
    <button id="btn2">send [5,8,12] two</button>
    <button id="btn3">send [5,8,12] three</button>
    <button id="btn4">send compose</button>
    <button id="btn5">return json</button>
    <button id="btn6">layer弹框</button>
</body>
</html>
