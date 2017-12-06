<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="order" value="${requestScope.get('order')}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>订单详情 - 网上商城管理系统</title>

	<link href="${ctx}/background/css/style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}/background/js/jquery.js"></script>
	<script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson li").click(function () {
                $(".menuson li.active").removeClass("active")
                $(this).addClass("active");
            });

            $('.title').click(function () {
                var $ul = $(this).next('ul');
                $('dd').find('ul').slideUp();
                if ($ul.is(':visible')) {
                    $(this).next('ul').slideUp();
                } else {
                    $(this).next('ul').slideDown();
                }
            });
        })
	</script>


</head>

<body style="background: #f0f9fd;">
<div class="lefttop">
	<span></span>后台管理
</div>
<dl class="leftmenu">
	<dd>
		<div class="title">
			<span><img src="${ctx}/background/images/leftico01.png"/></span>商品管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="${ctx}/admin/list-product.jsp" target="rightFrame">查询所有</a><i></i></li>
			<li><cite></cite><a href="${ctx}/admin/add-product.jsp" target="rightFrame">添加</a><i></i></li>
			<li><cite></cite><a href="${ctx}/admin/search-product.jsp" target="rightFrame"><label>查询</label></a><i></i>
			</li>
		</ul>
	</dd>

	<dd>
		<div class="title">
			<span><img src="${ctx}/background/images/leftico01.png"/></span>
			<a href="${ctx}/background/product/list">订单管理</a>
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="${ctx}/admin/list-order.jsp" target="rightFrame">查询所有</a><i></i></li>
		</ul>
	</dd>
</dl>
<div class="center">
	<h1 align="left">
		订单号: ${order == null ? "" : order.id}
	</h1>
	<h1 align="left">
		订单用户ID: ${order == null ? "" : order.user.id}
	</h1>
	<h1 align="left">
		订单用户名: ${order == null ? "" : order.user.username}
	</h1>
	<h1 align="left">
		订单用户联系方式: ${order == null ? "" : order.user.telephone}
	</h1>
	<h1 align="left">
		订单用户邮箱: ${order == null ? "" : order.user.email}
	</h1>
	<h1 align="left">
		订单用户地址: ${order == null ? "" : order.user.address}
	</h1>
	<br>
	<table class="imgtable">
		<thead>
		<tr>
			<th width="100px;">商品ID</th>
			<th width="100px;">商品名称</th>
			<th width="100px;">单价</th>
			<th width="100px;">商品重量/个数</th>
		</tr>
		</thead>

		<tbody>
		<c:forEach items="${order == null ? null : order.orderDetails}" var="detail">
			<tr>
				<td>${detail.book.id}</td>
				<td>${detail.book.name}</td>
				<td>${detail.book.price}</td>
				<td>${detail.count}</td>
			</tr>
		</c:forEach>

		</tbody>
	</table>
</div>
</body>
</html>
