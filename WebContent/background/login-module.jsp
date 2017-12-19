<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="error" value="${requestScope.get('error')}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>登录 - 网上商城管理系统</title>

	<link href="${ctx}/background/css/style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}/background/js/jquery.js"></script>
	<script type="text/javascript" src="${ctx}/background/js/cloud.js"></script>

	<script language="javascript">
        $(function () {
            $('.loginbox').css({
                'position': 'absolute',
                'left': ($(window).width() - 692) / 2
            });
            $(window).resize(function () {
                $('.loginbox').css({
                    'position': 'absolute',
                    'left': ($(window).width() - 692) / 2
                });
            })
        });
	</script>

</head>

<body style="background-color: #1c77ac; background-image: url(images/light.png); background-repeat: no-repeat; background-position: center top; overflow: hidden;">


<div id="mainBody">
	<div id="cloud1" class="cloud"></div>
	<div id="cloud2" class="cloud"></div>
</div>


<div class="logintop">
	<span>欢迎登录网上商城管理平台</span>
</div>
<form action="${ctx}/admin/login.do" method="post">
	<div class="loginbody">

		<div class="loginbox">
			<ul>
				<li><input name="username" type="text" class="loginuser" placeholder="用户名..."/></li>
				<li><input name="password" type="password" class="loginpwd" placeholder="密码..."/></li>
				<li><input type="submit" class="loginbtn" value="登录"/><span style="color: red;">${error}</span></li>
			</ul>
		</div>
	</div>
</form>
<div class="loginbm">
	网上商城 - 课程设计
</div>


</body>
</html>
