<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="error" value="${requestScope.get('error')}"/>
<c:set var="error_un" value="${requestScope.get('error_un')}"/>
<c:set var="username" value="${requestScope.get('username') == null ? '' : requestScope.get('username')}"/>
<c:set var="email" value="${requestScope.get('email') == null ? '' : requestScope.get('email')}"/>
<c:set var="telephone" value="${requestScope.get('telephone') == null ? '' : requestScope.get('telephone')}"/>
<c:set var="address" value="${requestScope.get('address') == null ? '' : requestScope.get('address')}"/>

<!DOCTYPE HTML>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
	<title>注册</title>

	<link href="${ctx}/foreground/css/bootstrap.css" rel='stylesheet' type='text/css' />
	<!-- Custom Theme files -->
	<link href="${ctx}/foreground/css/style.css" rel='stylesheet' type='text/css' />

	<script src="${ctx}/foreground/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/foreground/js/move-top.js"></script>
	<script type="text/javascript" src="${ctx}/foreground/js/easing.js"></script>

	<script>
		$(document).ready(function () {
			setTimeout(function () {
				window.location.href = "${ctx}/index.jsp";
            }, 3000);
        });
	</script>
</head>
<body>
<!----container---->
<div class="container">
	<!----top-header---->
	<div class="top-header">
		<div class="logo">
			<a href="${ctx}/index.jsp"><img src="${ctx}/foreground/images/logo.png" title="网上商城" /></a>
		</div>
		<div class="clearfix"> </div>
	</div>
	<!----//top-header---->
</div>
<!--//top-header-nav---->
<!----content---->
<div class="content">
	<!---top-row--->
	<div class="container">
		<!----speical-products---->
		<div class="special-products">
			<!----special-products-grids---->
			<div class="special-products-grids">
				<span>注册成功, 即将跳转到首页...</span>
				<div class="clearfix"> </div>
			</div>
			<!---//special-products-grids---->
		</div>
		<!---//speical-products---->
	</div>
</div>
<!----container---->

</body>
</html>
