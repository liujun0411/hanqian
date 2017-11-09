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
        <base href="<%=basePath%>"/>

            <title>组织管理- ${empty update?'组织添加':'组织修改'}</title>

            <meta http-equiv="pragma" content="no-cache"/>
            <meta http-equiv="cache-control" content="no-cache"/>
            <meta http-equiv="expires" content="0"/>
            <meta http-equiv="keywords" content="keyword1,keyword2,keyword3"/>
            <meta http-equiv="description" content="This is my page"/>
            <link href="manager/css/fenzdiv.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
            <link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
       
            <script type="text/javascript"  src="manager/js/jquery-1.3.2.js"></script>
           <script type="text/javascript" src="manager/js/jquery.validate.js"></script>

            
<script language="JavaScript">
    
         
         //验证成功后事件 添加
         function addUserCheck(){
            var name=document.getElementById('name');
            var principal=document.getElementById('principal');
 
            
            
            if(name.value==null || name.value==''){
                alert('请填写组织名称');
                userid.focus();
                return false;
            }
    
            if(principal.value==null ||principal.value==''){
                alert('请填写组织负责人');
                username.focus();
                return false;
            }
 
            document.forms[0].submit();
         }
         
         function updateDeptCheck(){
        
            var name=document.getElementById('name');
            var principal=document.getElementById('principal');
 

            if(name.value==null || name.value==''){
                alert('请填写组织名称');
                userid.focus();
                return false;
            }
    
            if(principal.value==null ||principal.value==''){
                alert('请填写组织负责人');
                username.focus();
                return false;
            }
            
            var form = $("#myform");
            form.attr("action", "dept!updateDept.action");
            form.submit();
         }
    
        
 </script> 
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
                            组织管理——    ${empty update?'组织添加':'组织修改'}
                        </td>
                    </tr>
                </table>
            </div>
            
            <s:form action="dept!addDept.action" theme="simple"  method="post" id="myform" name="addBuildingFrom">
                
                <input type="hidden" name="dbDept.${empty update?'dbUsersByOper.seq':'dbUsersByUpdater.seq'}" value="${empty sessionScope.users?'':sessionScope.users.seq}" />
                
                <input type="hidden" name="dbDept.seq" value="${dbDept.seq}"/>      
                <table width="770" border="0" cellspacing="1" class="gai_left_duiqi">
                    <tr class=" biaog_kan2 biaog_zt2 admin_bgclor_fff">
                    <td class="admin_bgclor_e3f admin_zt_14">
                            组织名称<span class="admin_clor_f00">*</span>
                        </td>
                        <td class="admin_bgclor_f1f">                                               
                            <input type="text" id="name" name="dbDept.name" value="${dbDept.name}"/>
                            
                        </td>
                        <td class="admin_bgclor_e3f admin_zt_14">
                            负责人<span class="admin_clor_f00">*</span>
                        </td>
                        <td class="admin_bgclor_f1f">                                               
                            <input type="text" id="principal" name="dbDept.principal" 
                             value="${dbDept.principal}"/>
                        </td>
                    </tr>


                </table>
                <table width="770" border="0" class="gai_left_duiqi">
                    <tr>
                        <td></td>
                        <td  width="60"><!--a href="javascript:f_check();" class="btn blue">下一步</a-->   
                        <td width="60" align="left">
                        <c:if test="${empty update}">
                        <a onclick="addUserCheck();" class="btn blue">添  加</a>
                        </c:if>
                        <c:if test="${!empty update}">
                        <a onclick="updateDeptCheck();" class="btn blue">修  改</a>
                        </c:if>
                        </td>                                   
                        </td>
                        <td  width="65"><a href="javascript:history.go(-1);" class="btn blue">取 消</a>                                                               
                        </td>
                    </tr>
                </table>
            </s:form>
        </div>
    </body>
</html>
