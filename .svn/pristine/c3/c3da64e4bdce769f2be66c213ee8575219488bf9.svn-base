<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<script src="<%=path%>/manager/js/My97DatePicker/WdatePicker.js" type="text/javascript"></script>
		<base href="<%=basePath%>"/>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>医院后勤智能化管理平台——后勤管理</title>
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet"
			type="text/css" />
		<link  href="manager/common/equip/css/ligerui-all.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<!-- 引入公用控件 -->
        <script type="text/javascript" src="manager/common/equip/js/jquery-1.3.2.min.js"></script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script type="text/javascript"
			src="manager/javascript/servicerBusiness/servicerBusiness.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
	</head>

	<body>
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							服务商管理——服务商${editFlag=='update'?'编辑':'添加' }
						</td>
					</tr>
				</table>
			</div>
			
			<form action="" method="post" id="subForm" name="subForm">
				<input type="hidden" name="editFlag" value="${editFlag }"
					id="editFlag" />
				<input type="hidden" name="dbSer.seq" value="${dbSer.seq }"
					id="seq" />
				<table width="1000" border="0" cellspacing="1" class="gai_left_duiqi">
					
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
					    <td class="admin_bgclor_e3f">
							服务商名称<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" maxlength="10" id="serName" name="dbSer.servname" value="${dbSer.servname}" onblur="validateIsUnique()"/>
						</td>
						<td class="admin_bgclor_e3f">
							服务商类型<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f">	
							<select name="dbSer.dbServiceType.seq" id="serType" style="width:155px">
								<option value=""> --请选择服务商类型${dbSer.dbServiceType.seq}--</option>
								<c:forEach items="${serviceTypeList}" var="stype">
									<option value="${stype.seq }" ${dbSer.dbServiceType.seq == stype.seq?'selected':''}>${stype.content1 }</option>
								</c:forEach>
							</select>											
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f">
						<td class="admin_bgclor_e3f">
							服务固话(包含区号)
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" id="tele" name="dbSer.tele" value="${dbSer.tele}"/>
						</td>
						<td class="admin_bgclor_e3f">
							服务手机
						</td>
						<td class="admin_bgclor_f1f">
							<input type="text" maxlength="11" id="mobitele" name="dbSer.mobitele" value="${dbSer.mobitele}" onkeyup="if(isNaN(value)){execCommand('undo');}" />
						</td>
					</tr>
	
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
					    <td class="admin_bgclor_e3f">
							主要负责人
						</td>
						<td class="admin_bgclor_f1f" colspan="3">
							<input type="text" id="princ" maxlength="10" name="dbSer.princ" value="${dbSer.princ}"/>
						</td>
					</tr>
					
				</table>
				<table width="770" border="0" class="gai_left_duiqi">
					<tr>
						<td></td>
						<td  width="65">
						    <a href="javascript:void(0);" onclick="editService();" class="btn blue">保存</a>										
						</td>
						<td  width="65"><a href="javascript:void(0);" onclick="javascript:history.go(-1)" class="btn blue">取 消</a>																
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>