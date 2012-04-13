<%@page import="com.simplemad.base.domain.enums.Have"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UserProfile Modify Step 2</title>
<link href="${path}/css/jquery-ui.custom.css" type="text/css" rel="stylesheet" />
<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/validate/screen.css" />
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/jquery-ui.custom.js" type="text/javascript"></script>
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">

	$(function() {
		//$('#buyDate').datepicker({ defaultDate: '${buyDate}',yearRange: '1910:2012',altFormat: 'yy', changeYear: true, changeMonth: false, dateFormat: 'yy'});
		child_select();
		car_select();
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
				'profile.marriage':{
					required: true
				},
				'profile.body':{
					required: true
				},
				'profile.degree':{
					required: true
				},
				'profile.familySalary':{
					required: true
				},
				'profile.child.have':{
					required: true
				},
				'profile.child.gender':{
					required: function() {
						if(document.getElementById("child").value == "HAVE") {
							return true;
						} else {
							return false;
						}
					}
				},
				'profile.child.degree':{
					required: function() {
						if(document.getElementById("child").value == "HAVE") {
							return true;
						} else {
							return false;
						}
					}
				},
				'profile.child.age':{
					required: function() {
						if(document.getElementById("child").value == "HAVE") {
							return true;
						} else {
							return false;
						}
					},
					number: true,
					min: 0,
					max: 30
				},
				'profile.car.have':{
					required: true
				},
				'profile.car.car':{
					required: function() {
						if(document.getElementById("car").value == "HAVE") {
							return true;
						} else {
							return false;
						}
					}
				},
				'profile.car.buyDate':{
					required: function() {
						if(document.getElementById("car").value == "HAVE") {
							return true;
						} else {
							return false;
						}
					}
				}
			},
			messages:{
				'profile.marriage':{
					required: "请选择婚烟;"
				},
				'profile.body':{
					required: "请选择体型;"
				},
				'profile.degree':{
					required: "请选择学历;"
				},
				'profile.familySalary':{
					required: "请选择家庭月收入;"
				},
				'profile.child.have':{
					required: "请选择小孩状况;"
				},
				'profile.child.gender':{
					required: "请选择小孩性别;"
				},
				'profile.child.degree':{
					required: "请选择小孩学历;"
				},
				'profile.child.age':{
					required: "请填写小孩年龄;",
					number: "年龄只能为数字;",
					min: "年龄不能为负数;",
					max: "小孩年龄不应超过30;"
				},
				'profile.car.have':{
					required: "请选择私家车;"
				},
				'profile.car.car':{
					required: "请选择车型;"
				},
				'profile.car.buyDate':{
					required: "请选择购买年份;"
				}
			}
		});
	}
	
	function child_select() {
		var have = document.getElementById("child").value;
		if(have == "<%=Have.HAVE%>") {
			child_visibility(true);
		} else {
			child_visibility(false);
		}
	}
	
	function child_visibility(isHave) {
		if(isHave) {
			document.getElementById("childgender").style.display = "";
			document.getElementById("childage").style.display = "";
			document.getElementById("childdegree").style.display = "";
		} else {
			document.getElementById("childgender").style.display = "none";
			document.getElementById("childage").style.display = "none";
			document.getElementById("childdegree").style.display = "none";
		}
	}
	
	function car_select() {
		var have = document.getElementById("car").value;
		if(have == "<%=Have.HAVE%>") {
			car_visibility(true);
		} else {
			car_visibility(false);
		}
	}
	
	function car_visibility(isHave) {
		if(isHave) {
			document.getElementById("cartype").style.display = "";
			document.getElementById("carbuydate").style.display = "";
		} else {
			document.getElementById("cartype").style.display = "none";
			document.getElementById("carbuydate").style.display = "none";
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
.list_topic li .s1{width:120px; text-align: right;}
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
	<form id="modify_form" action="${path}/user/modify_user_second.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="profile.user.mobileNo" value="${profile.user.mobileNo}"/>
		<div class="msg_box">
		<ul class="list_topic">
        <li class="topic_title"><span class="s3">请按提示完善个人资料</span></li>
        	<li><span class="s1">婚姻<label class="required_label">*</label>：</span><span class="s2">
        		<select class="inputform" name="profile.marriage">
							<option value="">请选择</option>
							<c:forEach items="${marriageList}" var="marriage">
								<c:choose>
									<c:when test="${profile.marriage != null && profile.marriage.name == marriage.name}">
										<option selected="selected" value="${marriage}">${marriage.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${marriage}">${marriage.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
        	</span></li>
        	<li><span class="s1">体型<label class="required_label">*</label>：</span><span class="s2">
        		<select class="inputform" name="profile.body">
							<option value="">请选择</option>
							<c:forEach items="${bodyList}" var="body">
								<c:choose>
									<c:when test="${profile.body != null && profile.body.name == body.name}">
										<option selected="selected" value="${body}">${body.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${body}">${body.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
        	</span></li>
        	<li><span class="s1">学历<label class="required_label">*</label>：</span><span class="s2">
        		<select class="inputform" name="profile.degree">
							<option value="">请选择</option>
							<c:forEach items="${degreeList}" var="degree">
								<c:choose>
									<c:when test="${profile.degree != null && profile.degree.name == degree.name}">
										<option selected="selected" value="${degree}">${degree.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${degree}">${degree.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
        	</span></li>
        	<li><span class="s1">家庭月收入<label class="required_label">*</label>：</span><span class="s2">
        		<select class="inputform" name="profile.familySalary">
							<option value="">请选择</option>
							<c:forEach items="${familySalaryList}" var="familySalary">
								<c:choose>
									<c:when test="${profile.familySalary != null && profile.familySalary.name == familySalary.name}">
										<option selected="selected" value="${familySalary}">${familySalary.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${familySalary}">${familySalary.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
        	</span></li>
        	<!-- start 小孩资料 -->
        	<li><span class="s1">小孩<label class="required_label">*</label>：</span><span class="s2">
        		<select class="inputform" id="child" onchange="child_select()" name="profile.child.have">
							<option value="">请选择</option>
							<c:forEach items="${haveList}" var="have">
								<c:choose>
									<c:when test="${profile.child != null && profile.child.have != null && profile.child.have.name == have.name}">
										<option selected="selected" value="${have}">${have.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${have}">${have.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
        	</span></li>
        	<li id="childgender"><span class="s1">性别：</span><span class="s2">
		        <c:forEach items="${genderList}" var="gender" varStatus="status">
							<c:choose>
								<c:when test="${(profile.child.gender == null && status.count == 1) || (profile.child.gender != null && profile.child.gender.name == gender.name)}">
									<input type="radio" name="profile.child.gender" checked="checked" value="${gender}">${gender.name}
								</c:when>
								<c:otherwise>
									<input type="radio" name="profile.child.gender" value="${gender}">${gender.name}
								</c:otherwise>
							</c:choose>		
						</c:forEach>
		        </span></li>
		    <li id="childage"><span class="s1">年龄：</span><span class="s2">
		    	<input class="inputform" type="text" name="profile.child.age" value="${profile.child.age}"/>
		    </span></li>
		    <li id="childdegree"><span class="s1">学历：</span><span class="s2">
		    	<select class="inputform" name="profile.child.degree">
							<option value="">请选择</option>
							<c:forEach items="${degreeList}" var="degree">
								<c:choose>
									<c:when test="${profile.child != null && profile.child.degree != null && profile.child.degree.name == degree.name}">
										<option selected="selected" value="${degree}">${degree.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${degree}">${degree.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
		    </span></li>
		    <!-- end 小孩资料 -->
		    <!-- start 私家车 -->
        	<li><span class="s1">私家车<label class="required_label">*</label>：</span><span class="s2">
        		<select class="inputform" id="car" onchange="car_select()" name="profile.car.have">
							<option value="">请选择</option>
							<c:forEach items="${haveList}" var="have">
								<c:choose>
									<c:when test="${profile.car != null && profile.car.have != null && profile.car.have.name == have.name}">
										<option selected="selected" value="${have}">${have.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${have}">${have.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
        	</span></li>
        	<li id="cartype"><span class="s1">车型：</span><span class="s2">
        		<select class="inputform" name="profile.car.car">
							<option value="">请选择</option>
							<c:forEach items="${carList}" var="car">
								<c:choose>
									<c:when test="${profile.car != null && profile.car.car != null && profile.car.car.name == car.name}">
										<option selected="selected" value="${car}">${car.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${car}">${car.name}</option>
									</c:otherwise>
								</c:choose>
							</c:forEach>
						</select>
        	</span></li>
        	<li id="carbuydate"><span class="s1">购买年份：</span><span class="s2">
        		<select class="inputform" id="buyDate" name="profile.car.buyDate">
							<option value="">请选择</option>
							<c:forEach items="${yearList}" var="year">
								<c:choose>
									<c:when test="${buyDate != null && buyDate == year}">
										<option selected="selected" value="${year}">${year}</option>
									</c:when>
									<c:otherwise>
										<option value="${year}">${year}</option>
									</c:otherwise>
								</c:choose>　
							</c:forEach>
						</select>
        	</span></li>
        	<!-- end 私家车 -->
        	
        	<li class="topic_title"><span class="s3"><input type="submit" value="下一步" class="inputform" style="width:100px;"></span></li>
        </ul>
        </div>
	</form>
	</div>
</body>
</html>