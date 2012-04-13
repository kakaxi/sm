<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Advertiser</title>
<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/validate/screen.css" />
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/json.js"></script>
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
	function province_select() {
		var proId = document.getElementById("province").value;
		var cityEle = $(document.getElementById("city"));
		if(proId == "") {
			cityEle.empty();
			cityEle.append("<option value=''>请选择</option>");
		} else {
			$.post("${path}/area/findCities.do", {"proId": proId}, function(cities) {
				cityEle.empty();
				cityEle.append("<option value=''>请选择</option>");
				for(var index = 0; index < cities.length; index++) {
					cityEle.append("<option value='" + cities[index].id + "'>" + cities[index].name + "</option>");
				}
			});
		}
	}
	
	function city_select() {
		var cityId = document.getElementById("city").value;
		var areaEle = $(document.getElementById("area"));
		if(cityId == "") {
			areaEle.empty();
			areaEle.append("<option value=''>请选择</option>");
		} else {
			$.post("${path}/area/findAreas.do", {"cityId": cityId}, function(areas) {
				areaEle.empty();
				areaEle.append("<option value=''>请选择</option>");
				for(var index = 0; index < areas.length; index++) {
					areaEle.append("<option value='" + areas[index].id + "'>" + areas[index].name + "</option>");
				}
			});
		}
	}
	
	function setupValidate() {
		$("#create_form").validate({
			rules:{
				'advertiser.name':{
					required: true
				},
				'advertiser.area.id':{
					required: true
				},
				'advertiser.industry.id':{
					required: true
				}
			},
			messages:{
				'advertiser.name':{
					required: "请输入名称"
				},
				'advertiser.area.id':{
					required: "请选择你所在的区域"
				},
				'advertiser.industry.id':{
					required: "请选择你所属的行业"
				}
			}
		});
	}
	
	$(document).ready(function(){
		setupValidate();
	});
	
</script>
</head>
<body>
	<form id="create_form" action="${path}/advertiser/create.do" enctype="multipart/form-data" method="post">
		<fieldset title="广告主" lang="广告主">
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>广告主名称:</label></td>
					<td><input name="advertiser.name" type="text"/></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>所在区域:</label></td>
					<td>
						<select id="province" onchange="province_select()">
							<option value="">请选择</option>
							<c:forEach items="${provinceList}" var="province">
								<option value="${province.id}">${province.name}</option>
							</c:forEach>
						</select>
						<select id="city" onchange="city_select()">
							<option value="">请选择</option>
							<c:forEach items="${cityList}" var="city">
								<option value="${city.id}">${city.name}</option>
							</c:forEach>
						</select>
						<select id="area" name="advertiser.area.id">
							<option value="">请选择</option>
							<c:forEach items="${areaList}" var="area">
								<option value="${area.id}">${area.name}</option>
							</c:forEach>
						</select>
						<input name="advertiser.address" type="text"/>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>所属行业:</label></td>
					<td>
						<select name="advertiser.industry.id">
							<option value="">请选择</option>
							<c:forEach items="${industryList}" var="industry">
								<option value="${industry.id}">${industry.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>