<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Advertisement List</title>
<script type="text/javascript">
	function backto() {
		window.location = "${path}/admanage/listAll.do";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/>
	<table>
		<thead style="background-color: gray;">
			<tr>
				<td>单选</td>
				<td>广告名称</td>
				<td>广告主</td>
				<td>广告类型</td>
				<td>状态<td>
				<td>创建时间</td>
				<td>图标类型</td>
				<td>广告文件类型</td>
				<td>URL</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${adList}" var="ad">
				<tr>
					<td><input name="adId" type="radio"/></td>
					<td>${ad.name}</td>
					<td>${ad.advertiser.name}</td>
					<td>${ad.adType.chineseName}</td>
					<td>${ad.standard.name}</td>
					<td>${ad.createdDate}</td>
					<td>${ad.iconExtendedName}</td>
					<td>${ad.contentExtendedName}</td>
					<td>${ad.url}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>