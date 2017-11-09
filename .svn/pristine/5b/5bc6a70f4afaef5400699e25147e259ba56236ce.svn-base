<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"
    contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="/WEB-INF/sksoft-struts.tld" prefix="sktag"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://"
            + request.getServerName() + ":" + request.getServerPort()
            + path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head>
        <base href="<%=basePath%>">

        <title>My JSP 'showUnControlList.jsp' starting page</title>

        <meta http-equiv="pragma" content="no-cache">
        <meta http-equiv="cache-control" content="no-cache">
        <meta http-equiv="expires" content="0">
        <meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
        <meta http-equiv="description" content="This is my page">
        <link rel="stylesheet" href="manager/css/neirong.css" type="text/css"></link>
        <link rel="stylesheet" href="manager/css/skdiv.css" type="text/css"></link>
        <link rel="stylesheet" href="manager/css/ziti.css" type="text/css"></link>
        <link rel="stylesheet" href="manager/css/common.css" type="text/css"></link>
        <link href="manager/css/annu.css" rel="stylesheet" type="text/css" />
        <script src="manager/js/annu.js" type="text/javascript"></script>
        <script src="manager/js/jquery-1.3.2.js" type="text/javascript"></script>
         <script type="text/javascript" src="manager/js/jquery-tmpl-min.js"></script>
        <script type="text/javascript" src="manager/javascript/buildStorey/buildStorey.js"></script>
        <script type="text/javascript" src="manager/js/tableTr.js"></script>
       
        <script type="text/javascript">
            function formCheck(currentPage) {
                if (currentPage != "") {
                    $('#currentPage').attr("value",currentPage);
                }
                //document.myForm.submit();
                var aciontName ="controlPage_threePoint.action";
                 $("#myForm").attr("action",aciontName);
        		$("#myForm").submit(); 
            } 
            setInterval(formCheck,30000);
       </script>
    </head>
    <body>
        <div class="itemList">
         <form id="myForm" name="myForm" action="" method="post">
            <input type="hidden" id="currentPage" name="currentPage"
				value="${currentPage }" />
         </form>
            <table width="100%" border="0" cellspacing="1"
                class="listTable">
                <thead>
                    <tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
                        <td align="center">
                            序号
                        </td>
                        <td align="center">
                           点位名称
                        </td>
                        <td align="center">
                          当前读数
                        </td>
                        <td align="center">
                            时间
                        </td>
             
                    </tr>
                </thead>
                <tbody id="stu-datas-tb">
                    <c:forEach items="${threePointList}" var="three" varStatus="idx">
                      <tr class="${idx.index%2==0?'tr1':'tr2'}">
                        <td align="center">
                            ${idx.index+1 }
                        </td>
                        <td align="center">
						    ${three.point_name }
                        </td>
                        <td align="center">
                            ${three.record }
                        </td>
                        <td align="center">
                         <f:formatDate value="${three.recordtime }" pattern="yyyy-MM-dd HH:mm:ss" /> 
                        </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
        <!-- 分页从这里开始 -->
        <c:if test="${!empty threePointList}">
            <div class="PageingEquip">
                <table width="100%">
                    <tr style="height: 50px;">
                        <td align="right">
                            <sktag:paginator showTotal="true" showAllPages="true"
                                strUnit="条记录" />
                        </td>
                    </tr>
                </table>
            </div>
        </c:if>
        <!-- 分页从这里开始 -->
        <c:if test="${empty threePointList}">
            <span style="color: red">暂无数据</span>
        </c:if>
    </body>
</html>
