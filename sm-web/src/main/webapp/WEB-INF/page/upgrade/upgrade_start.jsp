<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Upgrade</title>
<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/validate/screen.css" />
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
	function backto() {
		window.location = "${path}/upgrade/upgrade_list.do";
	}
	
	function setupValidate() {
		$("#upload_form").validate({
			rules:{
				'file': {
					required: true,
					accept: 'apk|ipa'
				}
			},
			messages:{
				'file': {
					required: "请选择应用程序文件",
					accept: "仅支持上传apk|ipa文件"
				}
			}
		});
	}
	
	$(document).ready(function() {
		setupValidate();
	});
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/>
	<form id="upload_form" action="${path}/upgrade/upgrade.do" enctype="multipart/form-data" method="post">
		<fieldset title="版本更新" lang="版本更新">
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>客户端程序:</label></td>
					<td><input name="file" type="file"/></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="更新"/>
	</form>
</body>
</html>