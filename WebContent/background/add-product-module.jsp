<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<c:set var="result" value="${requestScope.get('result')}"/>
<c:set var="book_types" value="${requestScope.get('book_types')}"/>
<c:set var="book_name" value="${requestScope.get('book_name')}"/>
<c:set var="book_author" value="${requestScope.get('book_author')}"/>
<c:set var="book_publisher" value="${requestScope.get('book_publisher')}"/>
<c:set var="book_publish_data" value="${requestScope.get('v')}"/>
<c:set var="book_price" value="${requestScope.get('book_price')}"/>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>添加商品 - 网上商城管理系统</title>

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

	<style type="text/css">
		.imgtable {
			width: auto;
			margin: auto;
		}

		.imgtable tr {
		}

		.imgtable tr input {
			width: 100%;
			height: 30px !important;
		}
		.imgtable td {
			padding: 5px 10px;
			text-indent: 0;
		}
		.imgtable tr td:nth-child(2) {
			width: 300px;
		 }
		.imgtable tr select {
			width: 100%;
			height: 30px;
		}
		.imgtable td textarea {
			width: 100%;
			height: 60px;
		}

	</style>
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
			<li><cite></cite><a href="${ctx}/admin/book/add"><b>添加</b></a><i></i></li>
			<li><cite></cite><a href="${ctx}/admin/book/search" target="rightFrame"><label>查询</label></a><i></i>
			</li>
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
	<h1 align="center">
		${result}
	</h1>
	<br>
	<br>
	<form action="${ctx}/admin/book/add.do" method="post" enctype="multipart/form-data">
		<table align="center" class="imgtable">
			<tr>
				<td width="100">商品类型：</td>
				<td>
					<select name="type">
						<option value="book" selected>图书</option>
					</select>
				</td>
				<td>*</td>
			</tr>
			<tr>
				<td>图书名称：</td>
				<td><input type="text" name="book_name" value="${book_name}"/></td>
				<td>*</td>
			</tr>
			<tr>
				<td>图书作者：</td>
				<td><input type="text" name="book_author" value="${book_author}"/></td>
				<td>*</td>
			</tr>
			<tr>
				<td>图书出版社：</td>
				<td><input type="text" name="book_publisher" value="${book_publisher}"/></td>
				<td>*</td>
			</tr>
			<tr>
				<td>图书出版信息：</td>
				<td>
					<textarea name="book_publish_data">${book_publish_data}</textarea>
				<td>*</td>
			</tr>
			<tr>
				<td>图书价格：</td>
				<td><input type="text" name="book_price" value="${book_price}"/></td>
				<td>*</td>
			</tr>
			<tr>
				<td>图书类型：</td>
				<td>
					<select name="book_type">
						<c:forEach var="type" items="${book_types}">
							<option value="${type.id}">${type.name}</option>
						</c:forEach>
					</select>
				</td>
				<td>*</td>
			</tr>
			<tr>
				<td>图书图片：</td>
				<td><input type="file" name="book_image" id="book-image"/></td>
				<td>*</td>
			</tr>
			<tr height="60" style="border-top: 1px solid #cbcbcb;">
				<td align="center" colspan="3"><input type="submit" value="添加"></td>
			</tr>
		</table>
	</form>
</div>
</body>
</html>
