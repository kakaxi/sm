<%@page import="java.util.ArrayList"%>
<%@page import="com.simplemad.base.domain.AreaCondition"%>
<%@page import="java.util.List"%>
<%@page import="com.simplemad.ad.domain.AdPublishCondition"%>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	AdPublishCondition condition = (AdPublishCondition)request.getAttribute("condition");
	List<AreaCondition> areaList = null;
	if(condition == null || condition.getCondition() == null || condition.getCondition().getAreas() == null) {
		areaList = new ArrayList<AreaCondition>();
	} else {
		areaList = condition.getCondition().getAreas();
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Advertisement</title>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/json.js"></script>
<script type="text/javascript">
	var selectedProvince = null;
	var selectedCity = null;
	var selectedAreaList = <%=areaList%>;
	
	function openMask() {
		document.getElementById("mask").style.display = "";
		loadProvinces();
	}
	
	function loadProvinces() {
		$.post("${path}/area/findProvinces.do", {}, function(provinces) {
			var provinceDiv = $("#provinceDiv");
			provinceDiv.empty();
			for(var index = 0; index < provinces.length; index++) {
				var p = provinces[index];
				provinceDiv.append('<a href="javascript:loadCities(\'' + p.id + '\', \'' + p.name +'\')">' + p.name + '</a><br/>');
			}
		});
	}
	
	function loadCities(proId, proName) {
		selectedProvince = {};
		selectedProvince.id = proId;
		selectedProvince.name = proName;
		$.post("${path}/area/findCities.do", {proId: proId}, function(cities) {
			var cityDiv = $("#cityDiv");
			cityDiv.empty();
			for(var index = 0; index < cities.length; index++) {
				var c = cities[index];
				cityDiv.append('<a href="javascript:loadAreas(\'' + c.id + '\', \'' + c.name + '\')">' + c.name + '</a><br/>');
			}
		});
	}
	
	function loadAreas(cityId, cityName) {
		selectedCity = {};
		selectedCity.id = cityId;
		selectedCity.name = cityName;
		$.post("${path}/area/findAreas.do", {cityId: cityId}, function(areas) {
			var areaDiv = $("#areaDiv");
			areaDiv.empty();
			for(var index = 0; index < areas.length; index++) {
				var a = areas[index];
				areaDiv.append('<a href="javascript:addArea(\'' + a.id + '\', \'' + a.name + '\')">' + a.name + '</a><br/>');
			}
		});
	}
	
	function addArea(areaId, areaName) {
		var selectedArea = {};
		selectedArea.id = areaId;
		selectedArea.name = selectedProvince.name + selectedCity.name + areaName; 
		if(isExist(selectedArea)) {
			return;
		}
		selectedAreaList.push(selectedArea);
		
		var areaResultDiv = $("#areaResultDiv");
		areaResultDiv.append("<label>" + selectedArea.name +"</label>");
		areaResultDiv.append('<a href="javascript:deleteArea(\'' + selectedArea.id + '\')">删除</a><br/>');
		
		var areaSelected = $("#areaSelected");
		areaSelected.append("<input type='hidden' name='condition.condition.areas[" + (selectedAreaList.length - 1) + "].id' value='" + selectedArea.id + "'/>"); 
		areaSelected.append("<input readonly='readonly' type='text' name='condition.condition.areas[" + (selectedAreaList.length - 1) + "].name' value='" + selectedArea.name + "'/>");
	}
	
	function isExist(selectedArea) {
		for(var index = 0; index < selectedAreaList.length; index++) {
			if(selectedAreaList[index].id == selectedArea.id) {
				return true;
			}
		}
		return false;
	}
	
	function deleteArea(areaId) {
		var newSelectedAreaList = [];
		for(var index = 0; index < selectedAreaList.length; index++) {
			var selectedArea = selectedAreaList[index];
			if(selectedArea.id != areaId) {
				newSelectedAreaList.push(selectedArea);
			}
		}
		selectedAreaList = newSelectedAreaList;
		var areaResultDiv = $("#areaResultDiv");
		areaResultDiv.empty();
		var areaSelected = $("#areaSelected");
		areaSelected.empty();
		for(var index = 0; index < selectedAreaList.length; index++) {
			var selectedArea = selectedAreaList[index];
			
			areaResultDiv.append("<label>" + selectedArea.name +"</label>");
			areaResultDiv.append('<a href="javascript:deleteArea(\'' + selectedArea.id + '\')">删除</a><br/>');
			
			areaSelected.append("<input type='hidden' name='condition.condition.areas[" + index + "].id' value='" + selectedArea.id + "'/>"); 
			areaSelected.append("<input readonly='readonly' type='text' name='condition.condition.areas[" + index + "].name' value='" + selectedArea.name + "'/>");
		}
	}
	
	function closeMask() {
		document.getElementById("mask").style.display = "none";
	}
	
	function backto() {
		window.location = "${path}/admanage/findAuditPassed.do";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/><br/>
	<div id="mask" style="display: none; position:absolute; left:50px; top:50px; height: 600px; width: 800px; filter:alpha(Opacity=80);-moz-opacity:0.5;opacity: 0.5;z-index:1000; background-color:#ffffff;">
		<input type="button" value="关闭" onclick="closeMask();">
		<div id="areaDialog">
			<div style="height: 550px; width: 200px; position: relative;float: left;">
				选择省份:
				<div id="provinceDiv">
					
				</div>
			</div>
			<div style="height: 550px; width: 200px; position: relative;float: left;">
				选择城市:
				<div id="cityDiv">
				
				</div>
			</div>
			<div style="height: 550px; width: 200px; position: relative;float: left;">
				选择区域:
				<div id="areaDiv">
				
				</div>
			</div>
			<div style="height: 550px; width: 200px; position: relative;float: left;">
				结果:
				<div id="areaResultDiv">
				
				</div>
			</div>
		</div>
	</div>
	<form action="${path}/advertising/createOrUpdateCondition.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="condition.advertisement.id" value="${ad.id}"/>
		<input type="hidden" name="condition.id" value="${condition.id}"/>
		<fieldset title="选择地区">
			<legend>选择地区</legend>
			<input type="button" value="选择地区" onclick="openMask();"><br/>
			<div id="areaSelected">
				<c:forEach items="${condition.condition.areas}" var="areaCondition" varStatus="status">
					<input type='hidden' name='condition.condition.areas[${status.count - 1}].id' value='${areaCondition.id}'/> 
					<input readonly='readonly' type='text' name='condition.condition.areas[${status.count - 1}].name' value='${areaCondition.name}'/>
				</c:forEach>
			</div>
		</fieldset>
		<fieldset title="性别" lang="性别">
			<legend>性别</legend>
			<c:forEach items="${genders}" var="gender">
				<c:set var="gendersLength" value="0"/>
				<c:forEach items="${condition.condition.genders}" varStatus="genderStatus" var="genderCondition" end="${genderEnd}">
					<c:set var="gendersLength" value="${genderStatus.count}"/>
					<c:choose>
						<c:when test="${genderCondition == gender}">
							<input type="checkbox" name="condition.condition.genders" checked="checked" value="${gender}">${gender.name}</input>
							<c:set var="genderEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${genderStatus.last}">
								<input type="checkbox" name="condition.condition.genders" value="${gender}">${gender.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="genderEnd" value="${gendersLength}"/>
				<c:if test="${gendersLength == 0}">
					<input type="checkbox" name="condition.condition.genders" value="${gender}">${gender.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="年龄段">
			<legend>年龄段</legend>
			从<input type="text" name="condition.condition.ages[0].min" value="${condition.condition.ages[0].min}">至<input type="text" name="condition.condition.ages[0].max" value="${condition.condition.ages[0].max}"><br/>
		</fieldset>
		<fieldset title="月薪">
			<legend>月薪</legend>
			<c:forEach items="${salaries}" var="salary">
				<c:set var="salariesLength" value="0"/>
				<c:forEach items="${condition.condition.salaries}" var="salaryCondition" varStatus="salaryStatus" end="${salaryEnd}">
					<c:set var="salariesLength" value="${salaryStatus.count}"/>
					<c:choose>
						<c:when test="${salary == salaryCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.salaries" value="${salary}">${salary.name}</input>
							<c:set var="salaryEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${salaryStatus.last}">
								<input type="checkbox" name="condition.condition.salaries" value="${salary}">${salary.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="salaryEnd" value="${salariesLength}"/>
				<c:if test="${salariesLength == 0}">
					<input type="checkbox" name="condition.condition.salaries" value="${salary}">${salary.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="职业">
			<legend>职业</legend>
			<c:forEach items="${jobs}" var="job">
				<c:set var="jobsLength" value="0"/>
				<c:forEach items="${condition.condition.jobs}" var="jobCondition" varStatus="jobStatus" end="${jobEnd}">
					<c:set var="jobsLength" value="${jobStatus.count}"/>
					<c:choose>
						<c:when test="${job == jobCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.jobs" value="${job}">${job.name}</input>
							<c:set var="jobEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${jobStatus.last}">
								<input type="checkbox" name="condition.condition.jobs" value="${job}">${job.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="jobEnd" value="${jobsLength}"/>
				<c:if test="${jobsLength == 0}">
					<input type="checkbox" name="condition.condition.jobs" value="${job}">${job.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="婚烟">
			<legend>婚烟</legend>
			<c:forEach items="${marriages}" var="marriage">
				<c:set var="jobsLength" value="0"/>
				<c:forEach items="${condition.condition.marriages}" var="marriageCondition" varStatus="marriageStatus" end="${marriageEnd}">
					<c:set var="marriagesLength" value="${marriageStatus.count}"/>
					<c:choose>
						<c:when test="${marriage == marriageCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.marriages" value="${marriage}">${marriage.name}</input>
							<c:set var="marriageEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${marriageStatus.last}">
								<input type="checkbox" name="condition.condition.marriages" value="${marriage}">${marriage.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="marriageEnd" value="${marriagesLength}"/>
				<c:if test="${marriagesLength == 0}">
					<input type="checkbox" name="condition.condition.marriages" value="${marriage}">${marriage.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="体型">
			<legend>体型</legend>
			<c:forEach items="${bodies}" var="body">
				<c:set var="bodiesLength" value="0"/>
				<c:forEach items="${condition.condition.bodies}" var="bodyCondition" varStatus="bodyStatus" end="${bodyEnd}">
					<c:set var="bodiesLength" value="${bodyStatus.count}"/>
					<c:choose>
						<c:when test="${body == bodyCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.bodies" value="${body}">${body.name}</input>
							<c:set var="bodyEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${bodyStatus.last}">
								<input type="checkbox" name="condition.condition.bodies" value="${body}">${body.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="bodyEnd" value="${bodiesLength}"/>
				<c:if test="${bodiesLength}">
					<input type="checkbox" name="condition.condition.bodies" value="${body}">${body.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="学历">
			<legend>学历</legend>
			<c:forEach items="${degrees}" var="degree">
				<c:set var="degreesLength" value="0"/>
				<c:forEach items="${condition.condition.degrees}" var="degreeCondition" varStatus="degreeStatus" end="${degreeEnd}">
					<c:set var="degreesLength" value="${degreeStatus.count}"/>
					<c:choose>
						<c:when test="${degree == degreeCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.degrees" value="${degree}">${degree.name}</input>
							<c:set var="marriageEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${degreeStatus.last}">
								<input type="checkbox" name="condition.condition.degrees" value="${degree}">${degree.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="degreeEnd" value="${degreesLength}"/>
				<c:if test="${degreesLength == 0}">
					<input type="checkbox" name="condition.condition.degrees" value="${degree}">${degree.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="家庭月收入">
			<legend>家庭月收入</legend>
			<c:forEach items="${familySalaries}" var="familySalary">
				<c:set var="familySalariesLength" value="0"/>
				<c:forEach items="${condition.condition.familySalaries}" var="familySalaryCondition" varStatus="familySalaryStatus" end="${familySalaryEnd}">
					<c:set var="familySalariesLength" value="${familySalaryStatus.count}"/>
					<c:choose>
						<c:when test="${familySalary == familySalaryCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.familySalaries" value="${familySalary}">${familySalary.name}</input>
							<c:set var="familySalaryEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${familySalaryStatus.last}">
								<input type="checkbox" name="condition.condition.familySalaries" value="${familySalary}">${familySalary.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="familySalaryEnd" value="${familySalariesLength}"/>
				<c:if test="${familySalariesLength == 0}">
					<input type="checkbox" name="condition.condition.familySalaries" value="${familySalary}">${familySalary.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="车状况">
			<legend>车状况</legend>
			<c:forEach items="${haves}" var="have" varStatus="status">
				<input type="checkbox" name="condition.condition.cars[${status.count - 1}].have" value="${have}">${have.name}</input>
				<c:if test="${status.count == 1}">
					<fieldset title="车型" lang="车型">
					<legend>车型</legend>
					<c:forEach items="${carTypes}" var="carType" varStatus="typStatus">
						<input type="checkbox" name="condition.condition.cars[${status.count -  1}].carTypes[${typStatus.count - 1}]" value="${carType}">${carType.name}</input>
					</c:forEach>
					</fieldset>
					<fieldset title="购买年段" lang="购买年段">
						<legend>购买年段</legend>
						从<input type="text" name="condition.condition.cars[${status.count - 1}].buyDates[0].start"></input>
						到<input type="text" name="condition.condition.cars[${status.count - 1}].buyDates[0].end"></input>
					</fieldset>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="小孩状况">
			<legend>小孩状况</legend>
			<c:forEach items="${haves}" var="have" varStatus="status">
				<input type="checkbox" name="condition.condition.childs[${status.count - 1}].have" value="${have}">${have.name}</input>
				<c:if test="${status.count == 1}">
				<fieldset title="性别" lang="性别">
					<legend>性别</legend>
					<c:forEach items="${genders}" var="gender">
						<input type="checkbox" name="condition.condition.childs[${status.count - 1}].genders" value="${gender}">${gender.name}</input>
					</c:forEach>
				</fieldset>
				<fieldset title="年龄段">
					从<input type="text" name="condition.condition.childs[${status.count - 1}].ages[0].min">至<input type="text" name="condition.condition.childs[${status.count - 1}].ages[0].max"><br/>
					<legend>年龄段</legend>
				</fieldset>
				<fieldset title="学历">
					<legend>学历</legend>
					<c:forEach items="${degrees}" var="degree">
						<input type="checkbox" name="condition.condition.childs[${status.count - 1}].degrees" value="${degree}">${degree.name}</input>
					</c:forEach>
				</fieldset>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="投资意向">
			<legend>投资意向</legend>
			<c:forEach items="${investments}" var="investment">
				<c:set var="investmentsLength" value="0"/>
				<c:forEach items="${condition.condition.investments}" var="investmentCondition" varStatus="investmentStatus" end="${investmentEnd}">
					<c:set var="investmentsLength" value="${investmentStatus.count}"/>
					<c:choose>
						<c:when test="${investment == investmentCondition}">
							<input type="checkbox" name="condition.condition.investments" value="${investment}">${investment.name}</input>
							<c:set var="marriageEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${investmentStatus.last}">
								<input type="checkbox" name="condition.condition.investments" value="${investment}">${investment.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="investmentEnd" value="${investmentsLength}"/>
				<c:if test="${investmentsLength == 0}">
					<input type="checkbox" name="condition.condition.investments" value="${investment}">${investment.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="业余爱好">
			<legend>业余爱好</legend>
			<c:forEach items="${spareHobbies}" var="spareHobby">
				<c:set var="spareHobbiesLength" value="0"/>
				<c:forEach items="${condition.condition.spareHobbies}" var="spareHobbyCondition" varStatus="spareHobbyStatus" end="${spareHobbyEnd}">
					<c:set var="spareHobbiesLength" value="${spareHobbyStatus.count}"/>
					<c:choose>
						<c:when test="${spareHobby == spareHobbyCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.spareHobbies" value="${spareHobby}">${spareHobby.name}</input>
							<c:set var="marriageEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${spareHobbyStatus.last}">
								<input type="checkbox" name="condition.condition.spareHobbies" value="${spareHobby}">${spareHobby.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="spareHobbyEnd" value="${spareHobbiesLength}"/>
				<c:if test="${spareHobbiesLength == 0}">
					<input type="checkbox" name="condition.condition.spareHobbies" value="${spareHobby}">${spareHobby.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="手机应用喜好">
			<legend>手机应用喜好</legend>
			<c:forEach items="${appHobbies}" var="appHobby">
				<c:set var="appHobbiesLength" value="0"/>
				<c:forEach items="${condition.condition.appHobbies}" var="appHobbyCondition" varStatus="appHobbyStatus" end="${appHobbyEnd}">
					<c:set var="appHobbiesLength" value="${appHobbyStatus.count}"/>
					<c:choose>
						<c:when test="${appHobby == appHobbyCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.appHobbies" value="${appHobby}">${appHobby.name}</input>
							<c:set var="marriageEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${appHobbyStatus.last}">
								<input type="checkbox" name="condition.condition.appHobbies" value="${appHobby}">${appHobby.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="appHobbyEnd" value="${appHobbiesLength}"/>
				<c:if test="${appHobbiesLength == 0}">
					<input type="checkbox" name="condition.condition.appHobbies" value="${appHobby}">${appHobby.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="饮食口味">
			<legend>饮食口味</legend>
			<c:forEach items="${tastes}" var="taste">
				<c:set var="tastesLength" value="0"/>
				<c:forEach items="${condition.condition.tastes}" var="tasteCondition" varStatus="tasteStatus" end="${tasteEnd}">
					<c:set var="tastesLength" value="${tasteStatus.count}"/>
					<c:choose>
						<c:when test="${taste == tasteCondition}">
							<input type="checkbox" checked="checked" name="condition.condition.tastes" value="${taste}">${taste.name}</input>
							<c:set var="marriageEnd" value="0"/>
						</c:when>
						<c:otherwise>
							<c:if test="${tasteStatus.last}">
								<input type="checkbox" name="condition.condition.tastes" value="${taste}">${taste.name}</input>
							</c:if>
						</c:otherwise>
					</c:choose>
				</c:forEach>
				<c:set var="tasteEnd" value="${tastesLength}"/>
				<c:if test="${tastesLength == 0}">
					<input type="checkbox" name="condition.condition.tastes" value="${taste}">${taste.name}</input>
				</c:if>
			</c:forEach>
		</fieldset>
		<fieldset title="分享微博">
			<legend>分享微博</legend>
			<label>是否分享微博:</label>
			<c:choose>
				<c:when test="${condition.sharable}">
					<input type="checkbox" name="condition.sharable" checked="checked"/>
				</c:when>
				<c:otherwise>
					<input type="checkbox" name="condition.sharable"/>
				</c:otherwise>
			</c:choose>
		</fieldset>
		<input type="submit" value="下一步"/>
	</form>
</body>
</html>