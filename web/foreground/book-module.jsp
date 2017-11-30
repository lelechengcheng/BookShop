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
	<title>${book.name} -- ${book.publisher}</title>
	<meta name="viewport" content="width=device-width, initial-scale=1" charset="utf-8">

	<link href="${ctx}/foreground/css/bootstrap.css" rel='stylesheet' type='text/css'/>
	<!-- Custom Theme files -->
	<link href="${ctx}/foreground/css/style.css" rel='stylesheet' type='text/css'/>

	<style>
		.product-price-left {
			width: 100%;
		}
		.product-price-details {
			padding-top: 30px;
			padding-left:20px;
		}
	</style>

	<!-- jQuery (necessary for Bootstrap's JavaScript plugins) -->
	<script src="${ctx}/foreground/js/jquery.min.js"></script>

	<script type="text/javascript" src="${ctx}/foreground/js/move-top.js"></script>
	<script type="text/javascript" src="${ctx}/foreground/js/easing.js"></script>
	<script type="text/javascript">

		// start-smoth-scrolling
        jQuery(document).ready(function ($) {
            $(".scroll").click(function (event) {
                event.preventDefault();
                $('html,body').animate({scrollTop: $(this.hash).offset().top}, 1000);
            });
        });
        // //start-smoth-scrolling

        addEventListener("load", function () {
            setTimeout(hideURLbar, 0);
        }, false);

        function hideURLbar() {
            window.scrollTo(0, 1);
        }

	</script>
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
	<!----product-details--->
	<div class="product-details">
		<div class="container">
			<div class="product-details-row1">
				<div class="product-details-row1-head">
					<h2 style="display: inline;">${book.name}</h2>
					<p style="display: inline;">&nbsp;&nbsp;--&nbsp;&nbsp;${book.author}</p>
				</div>
				<div class="col-md-4 product-details-row1-col1">
					<!----details-product-slider--->
					<!-- Include the Etalage files -->
					<link rel="stylesheet" href="${ctx}/foreground/css/etalage.css">
					<script src="${ctx}/foreground/js/jquery.etalage.min.js"></script>
					<!-- Include the Etalage files -->
					<script>
                        jQuery(document).ready(function ($) {

                            $('#etalage').etalage({
                                thumb_image_width: 300,
                                thumb_image_height: 400,
                                source_image_width: 900,
                                source_image_height: 1000,
                                show_hint: true,
                                click_callback: function (image_anchor, instance_id) {
                                    alert('Callback example:\nYou clicked on an image with the anchor: "' + image_anchor + '"\n(in Etalage instance: "' + instance_id + '")');
                                }
                            });
                            // This is for the dropdown list example:
                            $('.dropdownlist').change(function () {
                                etalage_show($(this).find('option:selected').attr('class'));
                            });

                        });
					</script>
					<!----//details-product-slider--->
					<div class="details-left">
						<div class="details-left-slider">
							<ul id="etalage">
								<li>
									<a>
										<img class="etalage_thumb_image" src="${book.imageUrl}"/>
										<img class="etalage_source_image" src="${book.imageUrl}"/>
									</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
				<div class="col-md-8 product-details-row1-col2">
					<div class="product-price">
						<div class="product-price-left text-right" style="float: right;">
							<h5>${book.price} 元</h5>
						</div>
						<div class="clearfix"></div>
					</div>
					<div class="product-price-details">
						<p class="text-left">书名: ${book.name}</p>
						<p class="text-left">作者: ${book.author}</p>
						<p class="text-left">类型: ${book.type.name}</p>
						<p class="text-left">价格: ${book.price} 元</p>
						<p class="text-left">出版社: ${book.publisher}</p>
						<p class="text-left">出版信息: ${book.publishData}</p>

						<a class="shipping"><span> </span>免邮</a>
						<div class="clearfix"></div>
						<hr>
						<div class="clearfix"></div>
						<div class="product-cart-share">
							<div class="add-cart-btn">
								<input type="button" value="添加到购物车" onmouseup="window.location.href='${ctx}/add_to_cart.do?book_id=${book.id}'"/>
							</div>
							<ul class="product-share text-right">
								<h3>分享至: </h3>
								<ul>
									<li><a class="share-face" href="javascript:void(0);"><span> </span> </a></li>
									<li><a class="share-twitter" href="javascript:void(0);"><span> </span> </a></li>
									<li><a class="share-google" href="javascript:void(0);"><span> </span> </a></li>
									<li><a class="share-rss" href="javascript:void(0);"><span> </span> </a></li>
									<div class="clear"></div>
								</ul>
							</ul>
						</div>
					</div>
				</div>
				<div class="clearfix"></div>
				<!--//product-details--->
			</div>
		</div>
	</div>
	<!----content---->
	<div class="clearfix"></div>

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

