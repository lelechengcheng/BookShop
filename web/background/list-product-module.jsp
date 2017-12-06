<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="products" value="${requestScope.get('products')}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>商品列表 - 网上商城管理系统</title>
	<link href="${ctx}/background/css/style.css" rel="stylesheet" type="text/css"/>
	<script type="text/javascript" src="${ctx}/background/js/jquery.js"></script>

	<script type="text/javascript">
        $(function () {
            //导航切换
            $(".menuson li").click(function () {
                $(".menuson li.active").removeClass("active");
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
			<li><cite></cite><a href="${ctx}/admin/list-product.jsp" target="rightFrame"><b>查询所有</b></a><i></i></li>
			<li><cite></cite><a href="${ctx}/admin/add-product.jsp">添加</a><i></i></li>
			<li><cite></cite><a href="${ctx}/admin/search-product.jsp" target="rightFrame"><label>查询</label></a><i></i>
			</li>
		</ul>
	</dd>

	<dd>
		<div class="title">
			<span><img src="${ctx}/background/images/leftico01.png"/></span>订单管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="${ctx}/admin/list-order.jsp" target="rightFrame">查询所有</a><i></i></li>
		</ul>
	</dd>
</dl>
<div class="center">
	<table class="imgtable">
		<thead>
		<tr>
			<th width="10%" style="text-align: center;">商品ID</th>
			<th width="40%" style="text-align: center;">商品名称</th>
			<th width="20%" style="text-align: center;">单价</th>
			<th width="30%" style="text-align: center;">操作</th>
		</tr>
		</thead>

		<tbody>
		<c:forEach var="product" items="${products}">
			<tr>
				<td>${product.id}</td>
				<td>${product.name}</td>
				<td>${product.price}</td>
				<td>
					<a href="${ctx}/admin/edit-product.jsp?id=${product.id}">编辑</a>
					<a href="${ctx}/admin/delete-product.do?id=${product.id}">删除</a>
				</td>
			</tr>
		</c:forEach>
		</tbody>
	</table>
</div>
</body>
</html>
