<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>欢迎登陆网上商城管理系统</title>
	
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
			<li><cite></cite><a href="${ctx}/admin/book/list" target="rightFrame">查询所有</a><i></i></li>
			<li><cite></cite><a href="${ctx}/admin/book/add">添加</a><i></i></li>
			<li><cite></cite><a href="${ctx}/admin/book/search" target="rightFrame"><label>查询</label></a><i></i></li>
		</ul>
	</dd>

	<dd>
		<div class="title">
			<span><img src="${ctx}/background/images/leftico01.png"/></span>订单管理
		</div>
		<ul class="menuson">
			<li><cite></cite><a href="${ctx}/admin/order/list" target="rightFrame">查询所有</a><i></i></li>
		</ul>
	</dd>
</dl>
<div class="center">
	<h1 align="center">欢迎登陆网上商城管理系统!</h1>
</div>
</body>
</html>
