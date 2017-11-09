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
		<link  href="manager/css/service.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/annu.js" type="text/javascript"></script>
		<!-- 引入公用控件 -->
        <script src="manager/js/jquery-1.7.2.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
		<script src="manager/common/equip/js/base.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerComboBox.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerResizable.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/ligerTree.js" type="text/javascript"></script>
		<script src="manager/common/equip/js/createTree.js" type="text/javascript"></script>
		<script type="text/javascript" src="manager/js/jquery.validate.js"></script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
		<script src="manager/javascript/servicerBusiness/sermainDetail.js" type="text/javascript"></script>
		<style type="text/css">
		/*new 2015-07-01 左右选择框的样式*/
		/*stats*/
		.button_css{width:49px; height:auto;font-size: 13pt;margin: 7px;}
		.tetle_font{height: 28px;font-size: 15px;border-bottom: 1px solid #cccccc;font-family: 微软雅黑;line-height: 3px;}
		.font_size_color{font-size: 14px;font-weight: bold;color: #0066FF;height: 26px;}
		.select_css{width:387px;height:270px;float:left; border:0px #A0A0A4 outset; padding:4px;font-size: 14px;font-weight: bold;color: #0066FF;overflow: auto;}
		/*去掉下拉框滚动条*/
		/*需求：内容超出时自动加滚动条*/
		/*1.增加body-->style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;"*/
		/*2.给slect对应外面的td上面的class=中添加lm_dlg*/
		/*3.给slect的style=中添加overflow: auto;*/
		.lm_dlg {
			overflow: hidden;
		}
		/*end*/
		</style>
<script type="text/javascript">
	$(document).ready(function(){
		alreayDivText('${alreayMains}');
	});
		     //移到右边
    function userRole_select_add(){
        $('#userId1 option:selected').appendTo('#roleId1');
    }
    //移到左边
    function userRole_select_remove(){
        $('#roleId1 option:selected').appendTo('#userId1');
    }
    //全部移到右边
    function userRole_select_add_all(){
        //获取全部的选项,删除并追加给对方
        $('#userId1 option').appendTo('#roleId1');
    }
        //全部移到左边
     function userRole_select_remove_all(){
        $('#roleId1 option').appendTo('#userId1');
    }
     //双击选项right
     function userRole_select_dblclick_right(){
       $("option:selected",$('#userId1')).appendTo('#roleId1'); //追加给对方
     }
     //双击选项left
     function userRole_select_dblclick_left(){
       $("option:selected",$('#roleId1')).appendTo('#userId1');
     }
		</script>
	</head>

	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
			<table width="780" border="0">
					<tr style="height:30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							维修人员管理——维修人员${editFlag=='update'?'编辑--修改':'添加--新增' }
						</td>
					</tr>
				</table>
			</div>
			<form action="" method="post" id="subForm" name="subForm">
				<input type="hidden" name="editFlag" value="${editFlag }"
					id="editFlag" />
				<input type="hidden" name="dbSerDetail.seq" value="${dbSerDetail.seq }"
					id="seq" />
				<input type="hidden" name="classId" id="classId" />
				<table width="1000" border="0" cellspacing="1" class="gai_left_duiqi">
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f"" >
					    <td class="admin_bgclor_e3f" style="width: 15%;padding: 20px 0 0 21px;">
							维修人员名称<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f"  style="width: 20%;padding: 20px 0 0 21px;">
							<input type="text" maxlength="10" id="memName" name="dbSerDetail.memName" value="${dbSerDetail.memName}" />
						</td>
						<td   class="admin_bgclor_f1f" ></td>
						<td class="admin_bgclor_e3f" style="width: 15%;">
							所属服务商<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f lm_dlg"  style="width: 20%;">	
							<select name="dbSerDetail.dbSermain.dbService.seq" id="memSer" style="width:155px;">
								<option value=""> --请选择服务商--</option>
								<c:forEach items="${serviceList}" var="sl">
									<option value="${sl.seq }" ${dbSerDetail.dbSermain.dbService.seq == sl.seq?'selected':''}>${sl.servname }</option>
								</c:forEach>
							</select>											
						</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2 admin_bgclor_f1f" >
						<td class="admin_bgclor_e3f" style="padding: 20px 0 0 21px;">
							服务固话(包含区号)
						</td>
						<td class="admin_bgclor_f1f" style="padding: 20px 0 0 21px;">
							<input type="text" id="tele" name="dbSerDetail.memTele" value="${dbSerDetail.memTele}"/>
						</td>
						<td ></td>
						<td class="admin_bgclor_e3f" >
							服务手机<span class="admin_clor_f00">*</span>
						</td>
						<td class="admin_bgclor_f1f" >
							<input type="text" maxlength="11" id="mobitele" name="dbSerDetail.memMobtele" value="${dbSerDetail.memMobtele}" onkeyup="if(isNaN(value)){execCommand('undo');}" />
						</td>
					</tr>
					<tr >
					<td colspan="5" style="height: 5px;padding: 20px 0 0 21px;" ></td>
					</tr>
					<tr>
					  <td align="left" class="font_size_color" style="padding: 20px 0 0 21px;">全部班组</td>
					  <td></td>
					  <td></td>
					  <td align="left" class="font_size_color" style="padding: 20px 0 0 0px;">所属班组</td>
					  <td></td>
					</tr>
					<tr>
					    <td colspan="2"  style="height: 270px; width: 387px;padding:0px 0 0 21px;" class="lm_dlg" align="right">
							<select multiple="multiple" id="userId1"  ondblclick="userRole_select_dblclick_right()"  class="select_css" style="width: 366px;">
								          <c:if test="${!empty allMainList}">
												<c:forEach items="${allMainList}" var="ml">
													<option value="${ml.seq}">${ml.content1 }</option>
												</c:forEach>
										</c:if>
							</select>
						</td>
                        <td align="center" style="width: 20%;">
								          <div style="float:left;margin: 0 auto;width: 100%;"> 
								             <input type="button" class="button_css" onclick="userRole_select_add()" value=">"/>
								            <br/>
								             <input type="button" class="button_css" onclick="userRole_select_add_all()" value=">>"/>
								            <br/>
								              <input type="button" class="button_css" onclick="userRole_select_remove()"  value="< " />
								            <br/>
								              <input type="button" class="button_css" onclick="userRole_select_remove_all()" value="<< " />
								            <br/>
						                   </div>
						</td>
						<td colspan="2" style="height: 270px; width: 387px;" align="left" class="lm_dlg">
								<select multiple="multiple" id="roleId1" ondblclick="userRole_select_dblclick_left()" class="select_css" style="width: 366px;">
							           <c:if test="${!empty alreayMains}">
												<c:forEach items="${alreayMains}" var="al">
												  <option value="${al.seq}">${al.content1 }</option>
												</c:forEach>
							           </c:if>
								</select>
						</td>
					</tr>
				</table>
				<table width="84%" border="0" class="gai_left_duiqi" style="margin: 20px auto;">
					<tr>
						<td></td>
						<td  width="65">
						    <a href="javascript:void(0);" onclick="editMen();" class="btn blue">保 存</a>										
						</td>
						<td  width="65"><a href="javascript:void(0);" onclick="javascript:history.go(-1)" class="btn blue">取 消</a>																
						</td>
					</tr>
				</table>
			</form>
		</div>
	</body>
</html>