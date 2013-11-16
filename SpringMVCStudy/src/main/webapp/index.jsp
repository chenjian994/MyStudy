<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>Spring MVC</title>
</head>
<body>
<h3>Hello world piece</h3>
<ul>
    <li><a href="/SpringMVCStudy/test?username=test&password=test">test</a></li>
    <li><a href="/SpringMVCStudy/redirect">redirect</a></li>
    <li><a href="/SpringMVCStudy/forward">forward</a></li>
</ul>
<hr/>
 <h3>Annotation piece</h3>
<ul>
    <li>@pathVariable:<a href="/SpringMVCStudy/annotation/pathVariable/Bob">name_Bob</a>
        | <a href="/SpringMVCStudy/annotation/pathVariable/Tom">name_Tom</a>
        | <a href="/SpringMVCStudy/annotation/pathVariable/Jack">name_Jack</a></li>
    <li>@CookieValue:<a href="/SpringMVCStudy/annotation/cookieValue">cookieValue</a></li>
    <li>formObject:<a href="/SpringMVCStudy/annotation/formObject?username=zhengsan&password=123456">formObject</a></li>
    <li>model、map、modelMap:<a href="/SpringMVCStudy/annotation/model">model_map_modelMap</a></li>
    <li>regexURL:<a href="/SpringMVCStudy/annotation/regexURL?requestCode=555">regexURL</a></li>
    <li>@RequestParam: <a href="/SpringMVCStudy/annotation/requestParam?value=pppppppp">value</a></li>
    <li>@RequestHeader: <a href="/SpringMVCStudy/annotation/requestHeader">requestHeader</a></li>
    <li>@ResponseBody: <a href='/SpringMVCStudy/annotation/responseBody1'>responseBody1</a></li>
    <li>@ResponseBody: <a href='/SpringMVCStudy/annotation/responseBody2'>responseBody2</a></li>
    <li>@ModelAttribute1 : <a href="/SpringMVCStudy/annotation/modelAttribute1?username=zhangsan&password=123456">modelAttribute1</a></li>
    <li>@ModelAttribute2 : <a href="/SpringMVCStudy/annotation/modelAttribute2/zhangsan?password=123456">modelAttribute2</a></li>
    <li>@ModelAttribute3 : <a href="/SpringMVCStudy/annotation/modelAttribute3?username=lisi">modelAttribute3</a></li>
    <li>@ModelAttribute4 : <a href="/SpringMVCStudy/annotation/modelAttribute4">modelAttribute4</a></li>
    <li>@SessionAttributes : <a href="/SpringMVCStudy/annotation/sessionUser">sessionAttributes</a></li>
    <li>@SessionAttributes : <a href="/SpringMVCStudy/annotation/clearSession">clearSession</a></li>
    <li>@Value : <a href="/SpringMVCStudy/annotation/value">value</a></li>
</ul>
<hr/>
<h3>Spring 练习</h3>
<ul>
    <li>资源上传下载：<a href="/SpringMVCStudy/resource/download">上传下载</a></li>
</ul>
</body>
</html>
