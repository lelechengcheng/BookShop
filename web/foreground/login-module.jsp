<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE HTML>
<html>
<head>
	<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
	<title>登录</title>

	<link href="${ctx}/foreground/css/bootstrap.css" rel='stylesheet' type='text/css' />
	<!-- Custom Theme files -->
	<link href="${ctx}/foreground/css/style.css" rel='stylesheet' type='text/css' />

	<script src="${ctx}/foreground/js/jquery.min.js"></script>
	<script type="text/javascript" src="${ctx}/foreground/js/move-top.js"></script>
	<script type="text/javascript" src="${ctx}/foreground/js/easing.js"></script>
	<script type="text/javascript">
        $(document).ready(function() {
            $().UItoTop({ easingType: 'easeOutQuart' });

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
			<div class="s-products-head">
				<div class="s-products-head-left">
					<h3>登录 | <a href="${ctx}/register.jsp" style="font-size: 60%;">没有账号? 点击注册</a></h3>
				</div>
				<div class="clearfix"> </div>
			</div>
			<!----special-products-grids---->
			<div class="special-products-grids">
				<form method="post" action="${ctx}/login.do">
					<div class="contact-form">
						<div class="contact-to">
							<input type="text" class="text" name="username" placeholder="用户名...">
							<input type="password" class="text" name="password" placeholder="密码...">
							<c:if test="${requestScope.get('error') != null}">
								<span style="color:red; margin: 30px; line-height: 66px">${requestScope.get("error")}</span>
							</c:if>
						</div>
						<div class="clearfix"></div>
						<input type="submit" value="登录">
					</div>
				</form>
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
