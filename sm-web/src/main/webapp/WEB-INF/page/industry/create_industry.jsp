<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Advertiser</title>
<script type="text/javascript">
	function backto() {
		window.location = "${path}/industry/findAll.do";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/>
	<form action="${path}/industry/create.do" enctype="multipart/form-data" method="post">
		<fieldset title="行业" lang="行业">
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>行业名称:</label></td>
					<td><input name="name" type="text"/></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>