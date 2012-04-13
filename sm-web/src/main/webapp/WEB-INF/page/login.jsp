<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Simple Mobile Advertisement</title>
</head>
<body style="width:100%; height:100%;">
		<div style="margin: auto; width: 300px; height: 300px;">
			<h1>登录</h1>
			<form action="${path}/administrator/login.do">
				<div><label>用户名:</label><input name="admin.mobile" type="text"/></div>
				<div><label>密  码:</label><input name="admin.password" type="password"/></div>
				<div><input type="submit" value="登  录"/></div>
			</form>
		</div>
	
</body>
</html>
