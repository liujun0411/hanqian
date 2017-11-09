<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
	<head>
		<base href="<%=basePath%>" />

		<title>角色管理-用户角色配置</title>

		<meta http-equiv="pragma" content="no-cache" />
		<meta http-equiv="cache-control" content="no-cache" />
		<meta http-equiv="expires" content="0" />
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3" />
		<meta http-equiv="description" content="This is my page" />
		<meta http-equiv="Content-Type" content="text/html; charset=gb2312">
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet"type="text/css" />
		<link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/message.css" rel="stylesheet" type="text/css" />
		<script src="manager/js/jquery-1.7.2.js" type="text/javascript"></script>


<style type="text/css">
/*查询表单样式*/
.q_table {
	width: 100%;
	border-collapse: collapse;
	border-top: solid 1px #666;
	border-bottom: solid 1px #666;
}

.q_table1 {
	border-collapse: collapse;
	border-top: solid 1px #666;
	border-bottom: solid 1px #666;
}

.q_table1 th {
	font-size: 12px;
	font-weight: normal;
	border-bottom: solid 1px #8c8c8c;
	background-color: #6EC2FD;
	font-weight: 400;
	font-size: 13px;
	color: #000;
	height: 30px;
}

.q_table1 td {
	background-color: #eeeeff;
	border-bottom: solid 1px #8c8c8c;
	border-left: solid 1px #fff;
}

.q_table th {
	font-size: 12px;
	font-weight: normal;
	border-bottom: solid 1px #8c8c8c;
	background-color: #6EC2FD;
	font-weight: 400;
	font-size: 13px;
	color: #000;
	height: 30px;
	width: 15%;
}

.q_table td {
	background-color: #eeeeff;
	border-bottom: solid 1px #8c8c8c;
	border-left: solid 1px #fff;
	width: 18%;
}

.table_btn_left {
	border:0px;
 height: 25px;
 width: 85px;
	background-image: url('manager/images/imgs/system_left.png');
}
.table_btn_right {
	border:0px;
 height: 25px;
 width: 100px;
	background-image: url('manager/images/imgs/system_right.png');
}

/*按钮样式修改保持统一性*/
input.btn.blue {
    margin: 17px 0 0 14px;
    padding-left: 9px;
    text-align: center;
    padding-right: 8px;
}
/*new 2015-07-01 左右选择框的样式*/
/*stats*/
.button_css{width:49px; height:auto;font-size: 13pt;margin: 7px;}
.tetle_font{height: 28px;font-size: 15px;border-bottom: 1px solid #cccccc;font-family: 微软雅黑;line-height: 3px;}
.font_size_color{font-size: 14px;font-weight: bold;color: #0066FF;height: 26px;}
.select_css{width:325px;height:250px;float:left; border:0px #A0A0A4 outset; padding:4px;font-size: 14px;font-weight: bold;color: #0066FF;overflow: auto;}
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
     //移到右边(用户)
    function userRole_select_add(){
        $('#userId1 option:selected').appendTo('#roleId1');
    }
    //移到左边(用户)
    function userRole_select_remove(){
        $('#roleId1 option:selected').appendTo('#userId1');
    }
    //全部移到右边(用户)
    function userRole_select_add_all(){
        //获取全部的选项,删除并追加给对方
        $('#userId1 option').appendTo('#roleId1');
    }
        //全部移到左边(用户)
     function userRole_select_remove_all(){
        $('#roleId1 option').appendTo('#userId1');
    }
     //双击选项(用户)right
     function userRole_select_dblclick_right(){
       $("option:selected",$('#userId1')).appendTo('#roleId1'); //追加给对方
     }
     //双击选项(用户)left
     function userRole_select_dblclick_left(){
       $("option:selected",$('#roleId1')).appendTo('#userId1');
     }
function insertSubmit(){
    var userId = new Array(); 
	var roleId = document.getElementById('role').value;
	   //获取选择的ID
	   $('#roleId1 option').each(function(i){
           userId[i]=$(this).val();
        })
	    //给userId赋值
	  //  $("#userId").val(userId);
	var url='role!updateRoleUser.action?dbRoles.roleId='+roleId;
	for(i=0;i<userId.length;i++){
		url+='&userId='+userId[i];
	}
	window.location.href=url;
}
</script>
	</head>
	<body style="overflow: scroll; overflow-x: hidden; overflow-y: hidden;">
		<div id="admin_nr_rightg">
			<div class="canshusz_btbg_1">
				<table width="780" border="0">
					<tr style="height: 30px">
						<td width="34" align="center">
							<img src="manager/images/imgs/canshushezhi_dianntu_1.png" />
						</td>
						<td width="679" class="biaoti_zt_canshusz">
							角色管理——用户角色配置
						</td>
					</tr>
				</table>
			</div>
			    <br>
				<br>
					<form action="role!addRole.action" method="post" id="myform">
                        <div class="admin_nr_rightg">
						<table  style="width:70%;font-weight: lighter;"  >
							 <tr>
								 <th align="left" colspan="6" class="tetle_font">
									用户角色基本信息
									<input type="hidden" name="roleId"  id="role" value="${dbRoles.roleId}" />
								 </th>
							 </tr>
							 <tr>
								 <th align="right" class="font_size_color">
									角色名:
								 </th>
								 <td class="font_size_color">
									${dbRoles.name}
								 </td >
								 <th align="right" class="font_size_color">
									角色描述:
								 </th>
								 <td class="font_size_color">
									${dbRoles.depict}
								 </td>
							 </tr>
							     <tr>
							     </tr>
						</table>
						<br/>
						<br/>
						<br/>
							<table   style="width: 70%;font-weight: lighter;">
								<tr>
									<th align="left" colspan="3" class="tetle_font">
										用户角色关系配置
									</th>
								</tr>
								<tr>
									<th align="left" class="font_size_color">
										可选用户
									</th>
									<th>
									</th>
									<th align="left" class="font_size_color">
										已选用户
									</th>
								</tr>
								<tr>
									<td style="height: 50px; width: 40%;" align="right" class="lm_dlg">
                                        <select multiple="multiple" id="userId1"  ondblclick="userRole_select_dblclick_right()"  class="select_css">
								         <c:if test="${!empty roleUserLst}">
												<c:forEach items="${roleUserLst}" var="u">
													<option value="${u.seq}">${u.username}</option>
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
									<td style="height: 50px; width: 40%;" align="left" class="lm_dlg">
										<select multiple="multiple" id="roleId1" ondblclick="userRole_select_dblclick_left()" class="select_css">
										           <c:if test="${! empty userLst}">
															<c:forEach items="${userLst}" var="u">
															  <option value="${u.seq}">${u.username}</option>
															</c:forEach>
										           </c:if>
										</select>
									</td>
								</tr>
								<tr>
									<td colspan="3" align="right">
										<input type=reset styleClass="common_button"
											onclick="javascript:history.go(-1);" value="取消" class="btn blue"/>
											  &nbsp;&nbsp;&nbsp;&nbsp;
											<input type="button" onclick="insertSubmit();"
											styleClass="common_button" value="保存" class="btn blue"/>
									</td>
								</tr>
							</table>
							<br>
						</div>
					</form>
	</body>
</html>
