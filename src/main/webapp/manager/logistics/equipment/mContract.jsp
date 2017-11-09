<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'mContract.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
		<link href="manager/common/equip/css/ligerui-all.css" rel="stylesheet"
			type="text/css" />
		<link href="manager/css/skdiv.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/ziti.css" rel="stylesheet" type="text/css" />
		<link href="manager/css/neirong.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="manager/js/jquery-1.3.2.js">
</script>
		<script type="text/javascript" src="manager/js/jquery-tmpl-min.js">
</script>
		<script src="manager/js/reqi.js" type="text/javascript">
</script>
		<script type="text/javascript" src="manager/js/ceng.js">
</script>
		<script src="manager/js/biaoge_1.js" type="text/javascript"></script>
  </head>
  
  <body>
 		 <table width="100%" border="0" cellspacing="1"
				class="biaoge_bg1 gai_left_duiqi" align="center">
				<thead>
					<tr class="biaoge_tr0 biaog_kan1 biaog_zt1">
						<th width="4%" scope="col">
							序号
						</th>
						<th width="15%" scope="col">
							设备名称
						</th>
						<th width="20%" scope="col">
							保养单位
						</th>
						<th width="10%" scope="col">
							保养费
						</th>
						<th width="12%" scope="col">
							合同签订日期
						</th>
						<th width="10%" scope="col">
							合同年限
						</th>
						<th width="12%" scope="col">
							合同到期日期
						</th>
						<th width="10%" scope="col">
							备注
						</th>
					</tr>
				</thead>
				<tbody id="stu-datas-tb">
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">一</td>
						<td align="center">溴化锂机组</td>
						<td align="center">江苏双良集团公司</td>
						<td align="center">3.4万元/年</td>
						<td align="center">2012.6.30</td>
						<td align="center">1年</td>
						<td align="center">2013.6.30.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">二</td>
						<td align="center">水质保养</td>
						<td align="center">江苏双良集团公司</td>
						<td align="center">4.69万元/年</td>
						<td align="center">2012.10.9</td>
						<td align="center">1年</td>
						<td align="center">2013.10.9.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">三</td>
						<td align="center">螺杆机组</td>
						<td align="center">上海依菲逊公司</td>
						<td align="center">1万元/年</td>
						<td align="center">2012.7.24</td>
						<td align="center">1年</td>
						<td align="center">2013.7.24.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">四</td>
						<td align="center">开利空调</td>
						<td align="center">开利销售公司</td>
						<td align="center">2万元/年</td>
						<td align="center">2012.8.31</td>
						<td align="center">1年</td>
						<td align="center">2013.8.31.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">五</td>
						<td align="center">大金空调</td>
						<td align="center">上海信晖制冷公司</td>
						<td align="center">4.5万元/年</td>
						<td align="center">2013.1.31</td>
						<td align="center">1年</td>
						<td align="center">2014.1.31.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">六</td>
						<td align="center">自动门</td>
						<td align="center">上海岱欧公司</td>
						<td align="center">5.4万元/年</td>
						<td align="center">2012.7.31</td>
						<td align="center">1年</td>
						<td align="center">2013.7.31.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">七</td>
						<td align="center">各类泵</td>
						<td align="center">上海营平公司</td>
						<td align="center">8.64万元/年</td>
						<td align="center">2012.4.30</td>
						<td align="center">1年</td>
						<td align="center">2013.4.30.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">八</td>
						<td align="center">空压机</td>
						<td align="center">上海欧杰公司</td>
						<td align="center">2.2万元/年</td>
						<td align="center">2012.2.28</td>
						<td align="center">1年</td>
						<td align="center">2013.2.28.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">九</td>
						<td align="center">冷库</td>
						<td align="center">上海冰泉制冷公司</td>
						<td align="center">1.44万元/年</td>
						<td align="center">2013.1.31</td>
						<td align="center">1年</td>
						<td align="center">2014.1.31.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">十</td>
						<td align="center">超净水维保</td>
						<td align="center">上海纯源公司</td>
						<td align="center">2.2万元/年</td>
						<td align="center"></td>
						<td align="center"></td>
						<td align="center"></td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">十一</td>
						<td align="center">BA维保</td>
						<td align="center">上海务升电子公司</td>
						<td align="center">10万元/年</td>
						<td align="center">2012.9.30</td>
						<td align="center">1年</td>
						<td align="center">2013.9.30.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">十二</td>
						<td align="center">EPS维保</td>
						<td align="center">上海天递公司</td>
						<td align="center">5.44万元/年</td>
						<td align="center">2012.5.31</td>
						<td align="center">1年</td>
						<td align="center">2013.5.31.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
					<tr class=" biaog_kan2 biaog_zt2">
						<td align="center">十三</td>
						<td align="center">各类过滤器</td>
						<td align="center">上海良成净化公司</td>
						<td align="center">18万元/年</td>
						<td align="center">2012.7.23</td>
						<td align="center">1年</td>
						<td align="center">2013.7.23.到期</td>
						<td align="center">门诊医技楼</td>
					</tr>
				</tbody>
  		</table>
  
  
  </body>
</html>
