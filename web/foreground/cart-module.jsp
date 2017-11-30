<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="user" value="${sessionScope.get('user')}"/>
<c:set var="cart" value="${sessionScope.get('cart')}"/>
<c:set var="book" value="${requestScope.get('book')}"/>

<c:set var="cart_book_count" value="0"/>
<c:forEach var="item" items="${cart == null ? null : cart.orderDetails}">
	<c:set var="cart_book_count" value="${cart_book_count + item.count}"/>
</c:forEach>
<c:remove var="item"/>

<!DOCTYPE HTML>
<html>
<head>
	<title>购物车</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">
	<link href="${ctx}/foreground/css/bootstrap.css" rel='stylesheet' type='text/css'/>
	<!-- Custom Theme files -->
	<link href="${ctx}/foreground/css/style.css" rel='stylesheet' type='text/css'/>

	<style>
		div.cart-item {
			width: 100%;
			padding-bottom: 8px;
		}
		div.cart-item div {
			height: 100px;
		}
		div.cart-item div:nth-child(1) {
			text-align: center;
		}
		div.cart-item div:nth-child(2) {
			line-height: 100px;
		}
		div.cart-item div:nth-child(3) {
			text-align: center;
		}
		div.cart-item div:nth-child(3) span {
			line-height: 100px;
		}
		div.cart-item img {
			height: 100%;
			width: auto;
			border-bottom: 0;
			margin-bottom: 0;
		}
	</style>
</head>
<body>
<!----container---->
<div class="container">
	<!----top-header---->
	<div class="top-header">
		<div class="logo">
			<a href="${ctx}/index.jsp"><img src="${ctx}/foreground/images/logo.png" title="网上商城"/></a>
		</div>
		<div class="top-header-info">
			<div class="top-contact-info">
				<ul class="unstyled-list list-inline">
					<li><span class="phone"> </span>0314 - 8888888</li>
					<li><span class="mail"> </span><a href="javascript:void(0);">help@lele.com</a></li>
					<div class="clearfix"></div>
				</ul>
			</div>
			<div class="cart-details">
				<a href="${ctx}/cart.jsp">
					<span class="cart">${cart_book_count} 件商品</span>
				</a>
				<c:choose>
					<c:when test="${user == null}">
						<div class="login-rigister">
							<ul class="unstyled-list list-inline">
								<li><a class="login" href="${ctx}/login.jsp">登录</a></li>
								<li><a class="rigister" href="${ctx}/register.jsp">注册 <span> </span></a></li>
								<div class="clearfix"></div>
							</ul>
						</div>
					</c:when>
					<c:otherwise>
						<div class="login-rigister">
							<ul class="unstyled-list list-inline">
								<li><a class="login" style="margin: 0 0.6em 0 1em">${user.username}, 您好!</a></li>
								<li><a class="rigister" href="${ctx}/logout.do" style="padding: 0.5em;">退出登陆</a></li>
								<div class="clearfix"></div>
							</ul>
						</div>
					</c:otherwise>
				</c:choose>
				<div class="clearfix"></div>
			</div>
		</div>
		<div class="clearfix"></div>
	</div>
	<!----//top-header---->

	<!---top-header-nav---->
	<div class="top-header-nav">
		<!----start-top-nav---->
		<nav class="top-nav main-menu">
			<ul class="top-nav">
				<li><a href="${ctx}/index.jsp">图书</a><span> </span></li>
				<li><a href="javascript:alert('功能暂未开放!');">衣服</a><span> </span></li>
				<li><a href="javascript:alert('功能暂未开放!');">鞋子</a><span> </span></li>
				<li><a href="javascript:alert('功能暂未开放!');">零食</a><span> </span></li>
				<div class="clearfix"></div>
			</ul>
		</nav>
		<!----End-top-nav---->
		<div class="clearfix"></div>
	</div>
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
					<h3>我的购物车</h3>
				</div>
				<div class="clearfix"></div>
			</div>
			<!----special-products-grids---->
			<hr>
			<div class="special-products-grids">
				<c:forEach var="item" items="${cart == null ? null : cart.orderDetails}">
					<div class="col-md-12 cart-item special-products-grid">
						<div class="col-md-2">
							<img src="${ctx}${item.book.imageUrl}" title="${item.book.name}"/>
						</div>
						<div class="col-md-6" style="overflow: hidden;">
							<span><a href="${ctx}/book.jsp?book_id=${item.book.id}">${item.book.name}</a></span>
						</div>
						<div class="col-md-4">
							<span>数量: ${item.count}&nbsp;&nbsp;&nbsp;&nbsp;</span>
							<a class="product-btn" href="${ctx}/remove_from_cart.do?book_id=${item.book.id}">减少1件</a>
							<a class="product-btn" href="${ctx}/add_to_cart.do?book_id=${item.book.id}&cart_page=true">增加1件</a>
						</div>
					</div>
				</c:forEach>
				<div class="clearfix"></div>
				<div class="add-cart-btn" style="height: 30px;">
					<input type="button" style="float: right;" value="下订单" onmouseup="window.location.href='${ctx}/order.do'"/>
				</div>
			</div>
			<!---//special-products-grids---->
		</div>
		<!---//speical-products---->
	</div>
	<!----content---->

	<!----footer--->
	<div class="footer">
		<div class="container">
			<div class="col-md-3 footer-logo">
				<a href="${ctx}/index.jsp"><img src="${ctx}/foreground/images/flogo.png" title="brand-logo"/></a>
			</div>
			<div class="col-md-7 footer-links">
				<ul class="unstyled-list list-inline">
					<li><a href="javascript:void(0);"> 隐私政策</a> <span> </span></li>
					<li><a href="javascript:void(0);"> 使用条款</a> <span> </span></li>
					<li><a href="javascript:void(0);"> 销售政策</a> <span> </span></li>
					<li><a href="javascript:void(0);"> 法律信息</a> <span> </span></li>
					<li><a href="javascript:void(0);"> 网站地图</a></li>
					<div class="clearfix"></div>
				</ul>
			</div>
			<div class="col-md-2 footer-social">
				<ul class="unstyled-list list-inline">
					<li><a class="pin" href="javascript:void(0);"><span> </span></a></li>
					<li><a class="twitter" href="javascript:void(0);"><span> </span></a></li>
					<li><a class="facebook" href="javascript:void(0);"><span> </span></a></li>
					<div class="clearfix"></div>
				</ul>
			</div>
			<div class="clearfix"></div>
		</div>
	</div>
	<div class="clearfix"></div>
	<!---//footer--->
	<!---copy-right--->
	<div class="copy-right">
		<div class="container">
			<p>网上商城 - 课程设计</p>
		</div>
	</div>
	<!--//copy-right--->
</div>
<!----container---->

</body>
</html>
