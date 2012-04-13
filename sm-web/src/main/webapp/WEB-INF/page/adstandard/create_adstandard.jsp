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
		window.location = "${path}/adstandard/findAll.do";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/>
	<form action="${path}/adstandard/create.do" enctype="multipart/form-data" method="post">
		<fieldset title="标准收费" lang="标准收费">
		<legend>标准收费</legend>
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>标准:</label></td>
					<td>
						<select name="standardMoney.standard">
							<option value="">请选择</option>
							<c:forEach items="${standardList}" var="standard">
								<option value="${standard}">${standard.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>定向条件数:</label></td>
					<td>
						<input name="standardMoney.conditionCount" type="text"/>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>用户单价:</label></td>
					<td>
						<input name="standardMoney.userMoney" type="text"/>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>广告主单价:</label></td>
					<td>
						<input name="standardMoney.clientMoney" type="text"/>
					</td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>