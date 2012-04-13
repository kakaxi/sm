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
		<input type="hidden" name="standardMoney.id" value="${standardMoney.id}"/>
		<legend>标准收费</legend>
		<table>
			<tbody>
				<tr>
					<td  style="text-align: right"><label>标准:</label></td>
					<td>
						<select name="standardMoney.standard">
							<option value="">请选择</option>
							<c:forEach items="${standardList}" var="standard">
								<c:choose>
									<c:when test="${standardMoney.standard == standard}">
										<option selected="selected" value="${standard}">${standard.name}</option>
									</c:when>
									<c:otherwise>
										<option value="${standard}">${standard.name}</option>
									</c:otherwise>
								</c:choose>
								
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>定向条件数:</label></td>
					<td>
						<input name="standardMoney.conditionCount" value="${standardMoney.conditionCount}" type="text"/>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>用户单价:</label></td>
					<td>
						<input name="standardMoney.userMoney" value="${standardMoney.userMoney}" type="text"/>
					</td>
				</tr>
				<tr>
					<td  style="text-align: right"><label>广告主单价:</label></td>
					<td>
						<input name="standardMoney.clientMoney" value="${standardMoney.clientMoney}" type="text"/>
					</td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>