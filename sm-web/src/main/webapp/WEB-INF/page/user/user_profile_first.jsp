<%@page import="com.simplemad.base.domain.enums.Job"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UserProfile Modify Step 1</title>
<link href="${path}/css/jquery-ui.custom.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/validate/screen.css" />

<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/jquery-ui.custom.js" type="text/javascript"></script>
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript" src="${path}/js/json.js"></script>
<script type="text/javascript">
	$(function() {
		//$('#birthday').datepicker({ defaultDate: '${birthday}',yearRange: '1910:2012',altFormat: 'yy-mm-dd', changeYear: true, changeMonth: true, dateFormat: 'yy-mm-dd'});
		setupValidate();
		changeBodyHeight();
	});
	
	function changeBodyHeight() {
		var offsetHeight = document.body.offsetHeight;
		var clientHeight = document.documentElement.clientHeight;
		if(clientHeight > offsetHeight) {
			document.body.style.height = clientHeight + "px";
		}
	}
	
	function setupValidate() {
		$("#modify_form").validate({
			errorLabelContainer: '#error',
			rules:{
				'profile.gender':{
					required: true
				},
				'profile.birthday':{
					required: true
				},
				'profile.area.id':{
					required: true
				},
				'profile.salary':{
					required: true
				},
				'profile.job':{
					required: true
				}
			},
			messages:{
				'profile.gender':{
					required: "请选择性别;"
				},
				'profile.birthday':{
					required: "请选择出生日期;"
				},
				'profile.area.id':{
					required: "请选择常驻地区;"
				},
				'profile.salary':{
					required: "请选择月薪;"
				},
				'profile.job':{
					required: "请选择职业;"
				}
			}
		});
	}
	
	function province_select() {
		var proId = document.getElementById("province").value;
		var cityEle = $(document.getElementById("city"));
		if(proId == "") {
			cityEle.empty();
			cityEle.append("<option value=''>请选择</option>");
			city_select();
		} else {
			$.post("${path}/area/findCities.do", {"proId": proId}, function(cities) {
				cityEle.empty();
				cityEle.append("<option value=''>请选择</option>");
				for(var index = 0; index < cities.length; index++) {
					cityEle.append("<option value='" + cities[index].id + "'>" + cities[index].name + "</option>");
				}
				city_select();
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
</script>
<style type="text/css">
*{margin:0; padding:0; list-style:none; font-size:18px; font-family:"宋体";}
body{width:100%;height:100%;}
#layout {
 border: 0px solid #A9C9E2;
 background-color: #E8F5FE;
 width: 100%;
 height:100%;
}
.msg_box{margin:0px;}
.msg_box .title{
background:#E8E8E8;
border-bottom:1px solid #CCCCCC;
border-top:1px solid #4F80A0;
color:#1E537F;
margin:0 0 7px;
padding:6px 10px 6px}
.msg_box .title img{vertical-align:middle; margin:0 0 0 0;}
.msg_box .note{line-height:18px;}
.msg_box .note em{color:#999; font-style:normal;}
.list_topic{margin:0 0 10px;}
.list_topic li{line-height:35px; border-bottom:solid 1px #d9d9d9; overflow:hidden; zoom:1; padding:0 0px 0 5px;}
.list_topic li span{display:block; float:left;}
.list_topic li .s1{width:65px; text-align: right;}
.list_topic li .s2{}
.list_topic li .s3{width:300px;}
.list_topic li a{color:#1f5896;}
.list_topic .topic_title{font-weight:bold;}
.inputform{height:30px; margin-top:4px;margin-bottom:4px;float:left}
.area_select{font-size:12px; width: 75px;}
.required_label{color: red;fond-size:12px;};
</style>
</head>
<body>
	<div id="layout">
	<div id="error"></div>
	<form id="modify_form" action="${path}/user/modify_user_first.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="profile.user.mobileNo" value="${profile.user.mobileNo}"/>
		<div class="msg_box">
		<ul class="list_topic">
        <li class="topic_title"><span class="s3">请按提示完善个人资料</span></li>
        <li class="topic_title"><span class="s1">分类</span><span class="s2">内容</span></li>
        <li><span class="s1">昵称：</span><span class="s2"><input type="text" value="${profile.userName}" name="profile.userName" class="inputform" ></span></li>
        <li><span class="s1">性别<label class="required_label">*</label>：</span><span class="s2">
        <c:forEach items="${genderList}" var="gender" varStatus="status">
							<c:choose>
								<c:when test="${(profile.gender == null && status.count == 1) || (profile.gender != null && profile.gender.name == gender.name)}">
									<input type="radio" name="profile.gender" checked="checked" value="${gender}">${gender.name}
								</c:when>
								<c:otherwise>
									<input type="radio" name="profile.gender" value="${gender}">${gender.name}
								</c:otherwise>
							</c:choose>		
						</c:forEach>
        </span></li>
         <li><span class="s1">生日<label class="required_label">*</label>：</span><span class="s2">
         	<%-- <input type="text" value="${birthday}" name="profile.birthday" readonly="readonly" id="birthday" class="inputform"/> --%>
         	<select class="inputform" id="birthday" name="profile.birthday">
							<option value="">请选择</option>
							<c:forEach items="${yearList}" var="year">
								<c:choose>
									<c:when test='${birthday == null && year == "1985"}'>
										<option selected="selected" value="${year}">${year}</option>
									</c:when>
									<c:when test="${birthday != null && birthday == year}">
										<option selected="selected" value="${year}">${year}</option>
									</c:when>
									<c:otherwise>
										<option value="${year}">${year}</option>
									</c:otherwise>
								</c:choose>　
							</c:forEach>
						</select>
         </span></li>
         <li><span class="s1">地区<label class="required_label">*</label>：</span><span class="s2">
         <select class="area_select inputform" id="province" onchange="province_select()">
							<option value="">请选择</option>
							<c:forEach items="${provinceList}" var="province">
								<c:choose>
									<c:when test="${profile.area != null && profile.area.city.province.id == province.id}">
										<option selected="selected" value="${province.id}">${province.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${province.id}">${province.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
		<select class="area_select inputform" id="city" onchange="city_select()">
							<option value="">请选择</option>
							<c:forEach items="${cityList}" var="city">
								<c:choose>
									<c:when test="${profile.area != null && profile.area.city.id == city.id}">
										<option selected="selected" value="${city.id}">${city.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${city.id}">${city.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
		<select class="area_select inputform" id="area" name="profile.area.id">
							<option value="">请选择</option>
							<c:forEach items="${areaList}" var="area">
								<c:choose>
									<c:when test="${profile.area != null && profile.area.id == area.id}">
										<option selected="selected" value="${area.id}">${area.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${area.id}">${area.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
		</span></li>
		<li><span class="s1">地址：</span><span class="s2">
            <input name="profile.address" value="${profile.address}" type="text" class="inputform"/></span></li>
        <li><span class="s1">月薪<label class="required_label">*</label>：</span><span class="s2">
        	<select class="inputform" id="salary" name="profile.salary">
							<option value="">请选择</option>
							<c:forEach items="${salaryList}" var="salary">
								<c:choose>
									<c:when test="${profile.salary != null && profile.salary.name == salary.name}">
										<option selected="selected" value="${salary}">${salary.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${salary}">${salary.name}</option>
									</c:otherwise>
								</c:choose>　
							</c:forEach>
						</select>
        </span></li>
        <li><span class="s1">职业<label class="required_label">*</label>：</span><span class="s2">	
        	<select class="inputform" id="job" name="profile.job">
							<option value="">请选择</option>
							<c:forEach items="${jobList}" var="job">
								<c:choose>
									<c:when test="${profile.job != null && profile.job.name == job.name}">
										<option selected="selected" value="${job}">${job.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${job}">${job.name}</option>
									</c:otherwise>
								</c:choose>　
							</c:forEach>
						</select>
		</span></li>
		<li class="topic_title"><span class="s3"><input type="submit" value="下一步" class="inputform" style="width:100px;"></span></li>
        </ul>
        </div>
	</form>
	</div>
</body>
</html>