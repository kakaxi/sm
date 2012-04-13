<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>UserProfile Modify Step 3</title>
<script type="text/javascript">
	function changeBodyHeight() {
		var offsetHeight = document.body.offsetHeight;
		var clientHeight = document.documentElement.clientHeight;
		if(clientHeight > offsetHeight) {
			document.body.style.height = clientHeight + "px";
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
.list_topic li .s1{width:130px; text-align: left;}
.list_topic li .s2{}
.list_topic li .s3{width:300px;}
.list_topic li a{color:#1f5896;}
.list_topic .topic_title{font-weight:bold;}
.inputform{height:30px; margin-top:4px;margin-bottom:4px;float:left}
.area_select{font-size:12px; width: 75px;}
.required_label{color: red;fond-size:12px;};
</style>
</head>
<body onload="changeBodyHeight()">
<div id="layout">
	<form action="${path}/user/modify_user_third.do" enctype="multipart/form-data" method="post">
		<input type="hidden" name="profile.user.mobileNo" value="${profile.user.mobileNo}"/>
		<div class="msg_box">
		<ul class="list_topic">
	        <li class="topic_title"><span class="s3">请按提示完善个人资料</span></li>
	        <li><span class="s1">投资意向：</span><span class="s2">
	        	<c:forEach items="${investmentList}" var="investment">
							<c:set var="investmentsLength" value="0"/>
							<c:forEach items="${profile.investments}" var="investmentVal" varStatus="investmentStatus" end="${investmentEnd}">
								<c:set var="investmentsLength" value="${investmentStatus.count}"/>
								<c:choose>
									<c:when test="${investment == investmentVal}">
										<input type="checkbox" checked="checked" name="profile.investments" value="${investment}">${investment.name}
										<c:set var="investmentEnd" value="0"/>
									</c:when>
									<c:otherwise>
										<c:if test="${investmentStatus.last}">
											<input type="checkbox" name="profile.investments" value="${investment}">${investment.name}
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:set var="investmentEnd" value="${investmentsLength}"/>
							<c:if test="${investmentsLength == 0}">
								<input type="checkbox" name="profile.investments" value="${investment}">${investment.name}
							</c:if>
						</c:forEach>
	        </span></li>
	        <li><span class="s1">业余爱好：</span><span class="s2">
	        	<c:forEach items="${spareHobbyList}" var="spareHobby">
							<c:set var="spareHobbiesLength" value="0"/>
							<c:forEach items="${profile.spareHobbies}" var="spareHobbyVal" varStatus="spareHobbyStatus" end="${spareHobbyEnd}">
								<c:set var="spareHobbiesLength" value="${spareHobbyStatus.count}"/>
								<c:choose>
									<c:when test="${spareHobby == spareHobbyVal}">
										<input type="checkbox" checked="checked" name="profile.spareHobbies" value="${spareHobby}">${spareHobby.name}
										<c:set var="spareHobbyEnd" value="0"/>
									</c:when>
									<c:otherwise>
										<c:if test="${spareHobbyStatus.last}">
											<input type="checkbox" name="profile.spareHobbies" value="${spareHobby}">${spareHobby.name}
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:set var="spareHobbyEnd" value="${spareHobbiesLength}"/>
							<c:if test="${spareHobbiesLength == 0}">
								<input type="checkbox" name="profile.spareHobbies" value="${spareHobby}">${spareHobby.name}
							</c:if>
						</c:forEach>
	        </span></li>
	        <li><span class="s1">手机应用喜好：</span><span class="s2">
	        	<c:forEach items="${phoneAppHobbyList}" var="phoneAppHobby">
							<c:set var="phoneAppHobbiesLength" value="0"/>
							<c:forEach items="${profile.phoneAppHobbies}" var="phoneAppHobbyVal" varStatus="phoneAppHobbyStatus" end="${phoneAppHobbyEnd}">
								<c:set var="phoneAppHobbiesLength" value="${phoneAppHobbyStatus.count}"/>
								<c:choose>
									<c:when test="${phoneAppHobby == phoneAppHobbyVal}">
										<input type="checkbox" checked="checked" name="profile.phoneAppHobbies" value="${phoneAppHobby}">${phoneAppHobby.name}
										<c:set var="phoneAppHobbyEnd" value="0"/>
									</c:when>
									<c:otherwise>
										<c:if test="${phoneAppHobbyStatus.last}">
											<input type="checkbox" name="profile.phoneAppHobbies" value="${phoneAppHobby}">${phoneAppHobby.name}
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:set var="phoneAppHobbyEnd" value="${phoneAppHobbiesLength}"/>
							<c:if test="${phoneAppHobbiesLength == 0}">
								<input type="checkbox" name="profile.phoneAppHobbies" value="${phoneAppHobby}">${phoneAppHobby.name}
							</c:if>
						</c:forEach>
	        </span></li>
	        <li><span class="s1">饮食口味：</span><span class="s2">
	        	<c:forEach items="${tasteList}" var="taste">
							<c:set var="tastesLength" value="0"/>
							<c:forEach items="${profile.tastes}" var="tasteVal" varStatus="tasteStatus" end="${tasteEnd}">
								<c:set var="tastesLength" value="${tasteStatus.count}"/>
								<c:choose>
									<c:when test="${taste == tasteVal}">
										<input type="checkbox" checked="checked" name="profile.tastes" value="${taste}">${taste.name}
										<c:set var="tasteEnd" value="0"/>
									</c:when>
									<c:otherwise>
										<c:if test="${tasteStatus.last}">
											<input type="checkbox" name="profile.tastes" value="${taste}">${taste.name}
										</c:if>
									</c:otherwise>
								</c:choose>
							</c:forEach>
							<c:set var="tasteEnd" value="${tastesLength}"/>
							<c:if test="${tastesLength == 0}">
								<input type="checkbox" name="profile.tastes" value="${taste}">${taste.name}
							</c:if>
						</c:forEach>
	        </span></li>
	        <li class="topic_title"><span class="s3"><input type="submit" value="下一步" class="inputform" style="width:100px;"></span></li>
        </ul></div>
	</form>
</div>
</body>
</html>