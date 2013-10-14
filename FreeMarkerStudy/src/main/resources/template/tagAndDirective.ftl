<html>
<head>
    <meta charset="UTF-8">
    <title>标签和指令</title>
</head>
<body>
判断标签if、else if 、else
<hr>
<p>请选择性别：</p>

<form action="fmstudy?methodName=tagAndDirective" method="post">
    <input type="radio" name="sex" value="female" checked="checked"/>女
    <input type="radio" name="sex" value="male"/>男
    <input type="submit" value="提交"/>
</form>
<#if sex?exists>    <#-- 判断sex是否为空  -->
    <p>你的性别：</p>
    <#if sex == "female"> <#-- 如果sex的值等于female -->
    <p>女</p>
    <#else>
    <p>男</p>
    </#if>
<#else>
<p>尚未选择性别！！！</p>
</#if>
<hr>
list使用
    <ul>
<#list persons as p>
        <li>${p.name}</li>
</#list>
    </ul>
</body>
</html>