<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Advertisement List</title>
<script type="text/javascript">
	function backto() {
		window.location = "${path}/admanage/viewPublished.do";
	}
</script>
</head>
<body>
	<input type="button" value="返回" onclick="backto()"/><br/>
	<fieldset>
		<legend>广告信息</legend>
		<table>
			<tbody>
				<tr>
					<td>广告名称</td>
					<td>:</td>
					<td>${ad.name}</td>
				</tr>
				<tr>
					<td>广告主</td>
					<td>:</td>
					<td>${ad.advertiser.name}</td>
				</tr>
				<tr>
					<td>广告类型</td>
					<td>:</td>
					<td>${ad.adType.chineseName}</td>
				</tr>
				<tr>
					<td>广告标准</td>
					<td>:</td>
					<td>${ad.standard.name}</td>
				</tr>
				<tr>
					<td>创建时间</td>
					<td>:</td>
					<td>${ad.createdDate}</td>
				</tr>
				<tr>
					<td>当前状态</td>
					<td>:</td>
					<td>${ad.status.name}</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
	<fieldset>
		<legend>广告效益</legend>
		<table>
			<tbody>
				<tr>
					<td>总发布量</td>
					<td>:</td>
					<td>${ad.publishQuantity}</td>
				</tr>
				<tr>
					<td>接收数量</td>
					<td>:</td>
					<td>${ad.receivedQuantity}</td>
				</tr>
				<tr>
					<td>正在下载数量</td>
					<td>:</td>
					<td>${ad.downloadingQuantity}</td>
				</tr>
				<tr>
					<td>下载完毕数量</td>
					<td>:</td>
					<td>${ad.downloadedQuantity}</td>
				</tr>
				<tr>
					<td>打开数量</td>
					<td>:</td>
					<td>${ad.openedQuantity}</td>
				</tr>
				<tr>
					<td>提交数量</td>
					<td>:</td>
					<td>${ad.completedQuantity}</td>
				</tr>
				<tr>
					<td>分享数量</td>
					<td>:</td>
					<td>${ad.sharedQuantity}</td>
				</tr>
				<tr>
					<td>计费分享数量</td>
					<td>:</td>
					<td>${ad.sharedQuantityFee}</td>
				</tr>
				<tr>
					<td>总观看数量</td>
					<td>:</td>
					<td>${ad.times}</td>
				</tr>
				<tr>
					<td>分享费用</td>
					<td>:</td>
					<td>${ad.sharedTotalMoney}</td>
				</tr>
				<tr>
					<td>广告费用</td>
					<td>:</td>
					<td>${ad.adTotalMoney}</td>
				</tr>
			</tbody>
		</table>
	</fieldset>
	<input type="button" value="返回" onclick="backto()"/>
</body>
</html>