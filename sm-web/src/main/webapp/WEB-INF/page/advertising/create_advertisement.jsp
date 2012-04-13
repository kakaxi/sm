<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Create Advertisement</title>
<link rel="stylesheet" type="text/css" media="screen" href="${path}/css/validate/screen.css" />
<script type="text/javascript" src="${path}/js/jquery.js"></script>
<script src="${path}/js/jquery.validate.js" type="text/javascript"></script>
<script type="text/javascript">
	function ad_type_change() {
		var adTypeValue = document.getElementById("adType").value;
		if(adTypeValue == "") {
			hideAll();
			return;
		} else {
			var adTypeId = parseInt(adTypeValue);
			switch (adTypeId) {
			case 1:
			case 2:
			case 3:
				showContent(true);
				break;
			case 4:
			case 5:
				showContent(false);
				break;
			default:
				break;
			}
		}
	}
	
	function showContent(flag) {
		if(flag) {
			document.getElementById("ad_content").style.display = '';
			document.getElementById("ad_url").style.display = 'none';
		} else {
			document.getElementById("ad_content").style.display = 'none';
			document.getElementById("ad_url").style.display = '';
		}
	}
	
	function hideAll() {
		document.getElementById("ad_content").style.display = 'none';
		document.getElementById("ad_url").style.display = 'none';
	}
	
	function backto() {
		window.location = "${path}/admanage/findNew.do";
	}
	
	function setupValidate() {
		$("#create_form").validate({
			rules:{
				'ad.advertiser.id':{
					required: true
				},
				'ad.name':{
					required: true
				},
				'ad.adType':{
					required: true
				},
				'icon':{
					required: true,
					accept: "png|jpg"
				},
				'content':{
					required: function() {
						var adTypeValue = document.getElementById("adType").value;
						if(adTypeValue == "") {
							return false;
						} else {
							var adTypeId = parseInt(adTypeValue);
							switch (adTypeId) {
							case 1:
							case 2:
							case 3:
								return true;
							case 4:
							case 5:
								return false;
							default:
								return false;
							}
						}
					},
					accept: function() {
						var adTypeValue = document.getElementById("adType").value;
						if(adTypeValue == "") {
							return "";
						} else {
							var adTypeId = parseInt(adTypeValue);
							switch (adTypeId) {
							case 1:
								return "txt|doc|docx|html|htm";
							case 2: 
								return "png|jpg|gif";
							case 3:
								return "mp4|3gp";
							case 4:
							case 5:
								return "";
							default:
								return "";
							}
						}
					}
				},
				'ad.url':{
					required: function() {
						var adTypeValue = document.getElementById("adType").value;
						if(adTypeValue == "") {
							return false;
						} else {
							var adTypeId = parseInt(adTypeValue);
							switch (adTypeId) {
							case 1:
							case 2:
							case 3:
								return false;
							case 4:
							case 5:
								return true;
							default:
								return false;
							}
						}
					},
					url: function() {
						var adTypeValue = document.getElementById("adType").value;
						if(adTypeValue == "") {
							return false;
						} else {
							var adTypeId = parseInt(adTypeValue);
							switch (adTypeId) {
							case 1:
							case 2:
							case 3:
								return false;
							case 4:
							case 5:
								return true;
							default:
								return false;
							}
						}
					}
				}
			},
			messages:{
				'ad.advertiser.id':{
					required: "请选择广告主"
				},
				'ad.name':{
					required: "请输入广告名称或描述"
				},
				'ad.adType':{
					required: "请选择广告类型"
				},
				'icon':{
					required: "请选择广告图标",
					accept: "广告图标只限于png,jpg格式"
				},
				'content':{
					required: "请选择广告文件",
					accept: function() {
						var adTypeValue = document.getElementById("adType").value;
						if(adTypeValue == "") {
							return "请先选择广告类型";
						} else {
							var adTypeId = parseInt(adTypeValue);
							switch (adTypeId) {
							case 1:
								return "文字广告仅限于txt|doc|docx|html|htm文件格式";
							case 2: 
								return "图片广告仅限于png|jpg|gif文件格式";
							case 3:
								return "Video视频广告仅限于mp4|3gp文件格式";
							case 4:
							case 5:
								return "非文件广告类型";
							default:
								return "非文件广告类型";
							}
						}
					}
				},
				'ad.url':{
					required: "请输入网址",
					url: "请输入正确的网址"
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
	<form id="create_form" action="${path}/admanage/create.do" enctype="multipart/form-data" method="post">
		<fieldset title="广告">
		<table>
			<tbody>
				<tr>
					<td style="text-align: right"><label>广告主:</label></td>
					<td>
						<select name="ad.advertiser.id">
							<option value="">请选择</option>
							<c:forEach items="${advertiserList}" var="advertiser">
								<option value="${advertiser.id}">${advertiser.name}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right"><label>广告名称:</label></td>
					<td><input name="ad.name" type="text"/></td>
				</tr>
				<tr>
					<td style="text-align: right"><label>广告类型:</label></td>
					<td>
						<select id="adType" onchange="ad_type_change()" name="ad.adType">
							<option value="">请选择</option>
							<c:forEach items="${adTypes}" var="adType">
								<option value="${adType.id}">${adType.chineseName}</option>
							</c:forEach>
						</select>
					</td>
				</tr>
				<tr>
					<td style="text-align: right"><label>上传图标:</label></td>
					<td><input name="icon" type="file"/></td>
				</tr>
				<tr id="ad_content" style="display: none;">
					<td style="text-align: right"><label>上传广告:</label></td>
					<td><input name="content" type="file"/></td>
				</tr>
				<tr id="ad_url" style="display: none;">
					<td style="text-align: right"><label>URL:</label></td>
					<td><input name="ad.url" type="text"/></td>
				</tr>
			</tbody>
		</table>
		</fieldset>
		<input type="submit" value="创建"/>
	</form>
</body>
</html>