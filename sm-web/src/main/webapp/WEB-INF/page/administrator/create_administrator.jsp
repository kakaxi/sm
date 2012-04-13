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
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
	function setupValidate() {
		$("#create_form").validate({
			rules:{
				'admin.mobile':{
					required: true,
					number: true,
					rangelength: [11, 11]
				},
				'admin.password':{
					required: true,
					minlength: 6,
					maxlength: 12
				},
				'confirm_password':{
					required: true,
					equalTo: "#password"
				}
			},
			messages:{
				'admin.mobile':{
					required: "请输入手机号",
					number: "请输入正确的手机号",
					rangelength: "请输入11位的手机号"
				},
				'admin.password':{
					required: "请输入密码",
					minlength: "密码长度须大于或等于6位数",
					maxlength: "密码长度须小于或等于12位数"
				},
				'confirm_password':{
					required: "请确认密码",
					equalTo: "确认密码与密码不一致"
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
	<form id="create_form" action="${path}/administrator/create.do" enctype="multipart/form-data" method="post">
		<fieldset title="管理员" lang="管理员">
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>账户:</label></td>
					<td><input name="admin.mobile" type="text"/></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>密码:</label></td>
					<td><input id="password" name="admin.password" type="password"/></td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>确认密码:</label></td>
					<td><input name="confirm_password" type="password"/></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>