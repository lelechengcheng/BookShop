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
	<script type="text/javascript">
        $(document).ready(function() {
            $().UItoTop({ easingType: 'easeOutQuart' });

            var username = $("#username");
            username.blur(function () {
				if (username.val() === "") {
				    $("#null-un").get(0).style.display = "inline";
				} else {
                    $("#null-un").get(0).style.display = "none";
                }
            });

            var password = $("#password");
            var rePassword = $("#re-password");
            var fun_error_pwd = function () {
				if (password.val() !== rePassword.val()) {
					$("#not-same-pwd").get(0).style.display = "inline";
				} else {
                    $("#not-same-pwd").get(0).style.display = "none";
                    if (password.val().length < 6) {
                        $("#less-six-letter-pwd").get(0).style.display = "inline";
					} else {
                        $("#less-six-letter-pwd").get(0).style.display = "none";
                    }
				}
            };
            password.blur(fun_error_pwd);
            rePassword.blur(fun_error_pwd);
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
					<h3>注册 | <a href="${ctx}/login.jsp" style="font-size: 60%;">已有账号? 点击登录</a></h3>
				</div>
				<div class="clearfix"> </div>
			</div>
			<!----special-products-grids---->
			<div class="special-products-grids">
				<form method="post" action="${ctx}/register.do">
					<div class="contact-form">
						<div class="contact-to">
							<input type="text" class="text" value="${username}" placeholder="用户名..." name="username" id="username">
							<span style="color:red; margin: 30px; line-height: 66px; display: none;" id="null-un">用户名不能为空!</span>
							<c:if test="${error_un != null}">
								<span style="color:red; margin: 30px; line-height: 66px;" id="error-un">${error_un}</span>
							</c:if>
						</div>
						<div class="clearfix"></div>
						<div class="contact-to">
							<input type="password" class="text" placeholder="密码..." name="password" id="password">
							<input type="password" class="text" placeholder="再次输入密码..." id="re-password">
							<span style="color:red; margin: 30px; line-height: 66px; display: none;" id="not-same-pwd">密码不一致!</span>
							<span style="color:red; margin: 30px; line-height: 66px; display: none;" id="less-six-letter-pwd">密码小于6位!</span>
						</div>
						<div class="clearfix"></div>
						<div class="contact-to">
							<input type="text" class="text" value="${email}" placeholder="邮箱..." name="email">
							<input type="text" class="text" value="${telephone}" placeholder="联系电话..." name="telephone">
						</div>
						<div class="text2">
							<textarea placeholder="地址..." style="height: 100px;" name="address">${address}</textarea>
						</div>
						<div class="clearfix"></div>
						<input type="submit" value="注册">
						<c:if test="${error != null}">
							<span style="color:red; margin: 30px; line-height: 66px;">${error}</span>
						</c:if>
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
