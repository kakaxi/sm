<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Province</title>
<script type="text/javascript">

	var proId = null;
	
	function create_province() {
		window.location = "${path}/area/create_start_province.do";
	}
	
	function create_city() {
		if(proId == null) {
			alert("请选择省份");
		} else {
			window.location = "${path}/area/create_start_city.do?proId=" + proId;
		}
	}
	
	function view_city() {
		if(proId == null) {
			alert("请选择省份");
		} else {
			window.location = "${path}/area/findAllCities.do?proId=" + proId;
		}
	}
	
	function select_pro(id) {
		proId = id;
	}
	
	function backto() {
		window.location = "${path}";
	}
</script>
</head>
<body>
	<input type="button" value="创建省份" onclick="create_province()"/>
	<input type="button" value="创建城市" onclick="create_city()"/>
	<input type="button" value="查看城市" onclick="view_city()"/>
	<input type="button" value="返回" onclick="backto()"/>
	<table border="2">
		<thead style="background-color: gray;">
			<tr>
				<td><label>单选</label></td>
				<td><label>名称</label></td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${provinceList}" var="province">
				<tr>
					<td><input name="proId" onclick="select_pro('${province.id}')" type="radio"/></td>
					<td><label>${province.name}</label></td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>