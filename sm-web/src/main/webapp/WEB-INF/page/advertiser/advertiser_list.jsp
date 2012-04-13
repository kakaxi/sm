<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Advertiser</title>
<script type="text/javascript">
	function advertiser_register() {
		window.location = "${path}/advertiser/create_start.do";
	}
	
	function backto() {
		window.location = "${path}";
	}
</script>
</head>
<body>
	<input type="button" value="注册广告主" onclick="advertiser_register()"/>
	<input type="button" value="返回" onclick="backto()"/>
	<table>
		<thead style="background-color: gray;">
			<tr>
				<td>广告主</td>
				<td>行业</td>
				<td>区域</td>
				<td>创建时间</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${advertiserList}" var="advertiser">
				<tr>
					<td>${advertiser.name}</td>
					<td>${advertiser.industry.name}</td>
					<td>${advertiser.area.city.province.name}${advertiser.area.city.name}${advertiser.area.name}${advertiser.address}</td>
					<td>${advertiser.registerDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>