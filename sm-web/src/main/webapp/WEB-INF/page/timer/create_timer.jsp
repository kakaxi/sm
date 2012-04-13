<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Timer</title>
<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/validate/screen.css" />
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
	function setupValidate() {
		$("#create_form").validate({
			rules:{
				'start':{
					required: true,
					number: true
				},
				'end':{
					required: true,
					number: true
				},
				'interval':{
					required: true,
					number: true
				}
			},
			messages:{
				'start':{
					required: "请输入开始时间",
					number: "开始时间须为数字类型"
				},
				'end':{
					required: "请输入结束时间",
					number: "结束时间须为数字类型"
				},
				'interval':{
					required: "请输入时间间隔",
					number: "时间间隔须为数字类型"
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
	<form id="create_form" action="${path}/timer/create.do" enctype="multipart/form-data" method="post">
		<fieldset title="时间表" lang="时间表">
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>开始时间(分):</label></td>
					<td><input name="start" value="${timer.start}" type="text"/></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>结束时间(分):</label></td>
					<td><input name="end" value="${timer.end}" type="text"/></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>时间间隔(分):</label></td>
					<td><input name="interval" value="${timer.interval}" type="text"/></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>