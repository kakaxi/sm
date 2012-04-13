<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>List All Applied Exchange</title>
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script type="text/javascript" src="${path}/js/json.js"></script>
<script type="text/javascript">
	var selectedRecordId = null;
	
	function change_id(recordId) {
		selectedRecordId = recordId;
	}
	
	function accept_process() {
		if(selectedRecordId == null) {
			alert("请选择接受申请的记录");
			return;
		}
		$.post("${path}/moneyexchange/process.do", {recordId : selectedRecordId}, function(data) {
			if(eval(data)) {
				alert("接受申请记录成功");
				window.location = "${path}/moneyexchange/findAppliedRecords.do";
			} else {
				alert("接受申请记录失败,请重试");
			}
		});
	}
</script>
</head>
<body>
	<input type="button" value="接受申请" onclick="accept_process()"/>
	<table>
		<thead style="background-color: gray;">
			<tr>
				<td>单选</td>
				<td>手机号</td>
				<td>项目金额</td>
				<td>状态</td>
				<td>申请时间</td>
				<td>接受处理时间</td>
				<td>充值时间</td>
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${recordList}" var="record">
				<tr>
					<td><input type="radio" onclick="change_id('${record.id}')"/></td>
					<td>${record.user.mobileNo}</td>
					<td>${record.money}</td>
					<td>${record.status.name}</td>
					<td>${record.appliedDate}</td>
					<td>${record.processedDate}</td>
					<td>${record.completedDate}</td>
				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>