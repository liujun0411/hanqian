/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.47
 * Generated at: 2017-09-26 06:30:44 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.manager.monitoring.control.monitoringPlatform;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.util.*;

public final class alarmCollect_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("\r\n");

	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";

      out.write("\r\n");
      out.write("<!DOCTYPE html PUBLIC \"-//W3C//DTD XHTML 1.0 Transitional//EN\" \"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd\">\r\n");
      out.write("<html>\r\n");
      out.write("\t<head>\r\n");
      out.write("\t\t<base href=\"");
      out.print(basePath);
      out.write("\" />\r\n");
      out.write("\t\t<meta http-equiv=\"Content-Type\" content=\"text/html; charset=utf-8\" />\r\n");
      out.write("\t\t<title>医院后勤智能化管理平台——告警</title>\r\n");
      out.write("\t\t<link href=\"manager/css/skdiv.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\t\t<link href=\"manager/css/ziti.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\t\t<link href=\"manager/css/neirong.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\t\t<link href=\"manager/css/shishijiankong.css\" rel=\"stylesheet\" type=\"text/css\" />\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"manager/js/jquery-1.3.2.js\"></script>\r\n");
      out.write("\t\t<script type=\"text/javascript\" src=\"manager/voice/js/swfobject.js\"></script>\r\n");
      out.write("\r\n");
      out.write("\t\t<script type=\"text/javascript\" >\r\n");
      out.write("\t\t\tvar timerS = 0;\r\n");
      out.write("\t\t\tvar timerT = 0;\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tvar initiVal=function(){\r\n");
      out.write("\t\t\t\ttry{\r\n");
      out.write("\t\t\t\t\t$(\"#levelo\").html(\"<font color='blue' id='alarmOne'>0</font>\");\r\n");
      out.write("\t\t\t\t\t$(\"#levelt\").html(\"<font color='blue' id='alarmTwo'>0</font>\");\r\n");
      out.write("\t\t\t\t\t$(\"#levelth\").html(\"<font color='blue' id='alarmThree'>0</font>\");\r\n");
      out.write("\t\t\t\t\t$(\"#marcount\").html(\"<font color = 'blue'>0</font>\");\r\n");
      out.write("\t\t\t\t}catch(e){}\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\tvar updateRcount=function(){\r\n");
      out.write("\t\t\t\tinitiVal();\r\n");
      out.write("\t\t\t\tvar jsonDoc=$.ajax({\r\n");
      out.write("\t\t\t\t\t  type: \"POST\",\r\n");
      out.write("\t\t\t\t\t  url: \"alarm_findCurrentAlarmCount.action\",\r\n");
      out.write("\t\t\t\t\t  dataType: \"json\",\r\n");
      out.write("\t\t\t\t\t  cache: false,\r\n");
      out.write("\t\t\t\t\t  async:false,\r\n");
      out.write("\t\t\t\t\t  error: function(jsonObj){  \r\n");
      out.write("\t\t\t\t\t  },\r\n");
      out.write("\t\t\t\t\t  success: function(jsonObj) {\r\n");
      out.write("\t\t\t\t\t  \t\tvar levelOne = jsonObj.levelOne;\r\n");
      out.write("\t\t\t\t\t  \t\tvar levelTwo = jsonObj.levelTwo;\r\n");
      out.write("\t\t\t\t\t  \t\tvar levelThree = jsonObj.levelThree;\r\n");
      out.write("\t\t\t\t\t  \t\t//var repairNum = jsonObj.repairNum;\r\n");
      out.write("\t\t\t\t\t  \t   \tvar countStr = \"\";\r\n");
      out.write("\t\t\t\t\t  \t  \tvar countStrEnd = \"\";\r\n");
      out.write("\t\t\t\t               if(levelOne!=0){\r\n");
      out.write("\t\t\t\t            \t   countStr=\"<font color='red' id='alarmOne'>\"+levelOne+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t   }else{\r\n");
      out.write("\t\t\t\t\t\t\t\t   countStr=\"<font color='blue' id='alarmOne'>\"+levelOne+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t   }\r\n");
      out.write("\t\t\t\t               countStrEnd ='<a  style=\"cursor:pointer\" href=\"javascript:void(0);\" onclick=\"submitHiddenForm(\\'alarm_findCurrentAlarmInfoLess.action?eqcurrentPageLess=1&level=1&flag=true\\');\" >'+countStr+'</a>';\r\n");
      out.write("\t\t\t\t               $(\"#levelo\").html(countStrEnd);\r\n");
      out.write("\t\t\t\t               if(levelTwo!=0){\r\n");
      out.write("\t\t\t\t            \t   countStr=\"<font color='red' id='alarmTwo'>\"+levelTwo+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t   }else{\r\n");
      out.write("\t\t\t\t\t\t\t\t   countStr=\"<font color='blue' id='alarmTwo'>\"+levelTwo+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t   }\r\n");
      out.write("\t\t\t\t               countStrEnd ='<a  style=\"cursor:pointer\" href=\"javascript:void(0);\" onclick=\"submitHiddenForm(\\'alarm_findCurrentAlarmInfoLess.action?eqcurrentPageLess=1&level=2&flag=true\\')\" >'+countStr+'</a>';\r\n");
      out.write("\t\t\t\t\t           $(\"#levelt\").html(countStrEnd);\r\n");
      out.write("\t\t\t\t               if(levelThree!=0){\r\n");
      out.write("\t\t\t\t            \t   countStr=\"<font color='red' id='alarmThree'>\"+levelThree+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t   }else{\r\n");
      out.write("\t\t\t\t\t\t\t\t   countStr=\"<font color='blue' id='alarmThree'>\"+levelThree+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t   }\r\n");
      out.write("\t\t\t\t               countStrEnd ='<a  style=\"cursor:pointer\" href=\"javascript:void(0);\" onclick=\"submitHiddenForm(\\'alarm_findCurrentAlarmInfoLess.action?eqcurrentPageLess=1&level=3&flag=true\\')\" >'+countStr+'</a>';\r\n");
      out.write("\t\t\t\t\t           $(\"#levelth\").html(countStrEnd);\r\n");
      out.write("\t\t\t\t\t        \ttheAlert();\r\n");
      out.write("\t\t\t\t\t           //需要维护的设备数\r\n");
      out.write("\t\t\t\t\t        //   var repairStr = \"\";\r\n");
      out.write("\t\t\t\t\t        //  var repairStrEnd = \"\";\r\n");
      out.write("\t\t\t\t\t        //  if(repairNum>0){\r\n");
      out.write("\t\t\t\t\t        //\t   repairStr=\"<font color='red'>\"+repairNum+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t//  }else{\r\n");
      out.write("\t\t\t\t\t\t\t//\t   repairStr=\"<font color='blue'>\"+repairNum+\"</font>\";\r\n");
      out.write("\t\t\t\t\t\t\t//   }\r\n");
      out.write("\t\t\t\t\t        //   repairStrEnd ='<a  style=\"cursor:pointer;text-decoration:none\" href=\"javascript:void(0);\" onclick=\"javascript:window.open(\\'maintenance_findServeMaintenance.action?type=alert\\',\\'\\',\\'height=430,width=780,top=\\'+(screen.height-430)/2+\\',left=\\'+(screen.width-780-10)/2+\\',status=no,toolbar=no,menubar=no,location=no\\');\" >'+repairStr+'</a>';\r\n");
      out.write("\t\t\t\t\t\t\t  \t\r\n");
      out.write("\t\t\t\t\t        //   $(\"#marcount\").html(repairStrEnd);\r\n");
      out.write("\t\t\t\t\t          \r\n");
      out.write("\t\t\t\t\t             \r\n");
      out.write("\t\t\t\t\t        }\r\n");
      out.write("\t\t\t\t\t\t\t\t\t  \r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t        });  \r\n");
      out.write("\t\t\t\t\t       \r\n");
      out.write("\t\t\t\tsetTimeout(updateRcount,30*1000);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\tvar submitHiddenForm = function(url){\r\n");
      out.write("\t\t\t\t$(window.parent.document).find(\"#deployCollect\").attr(\"src\",url);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//告警音乐\r\n");
      out.write("\t\t\tvar theAlert=function(){\r\n");
      out.write("\t\t\t\t\t$(\"#emDivId\").html('');\r\n");
      out.write("\t\t\t\t\tvar fobj = $(\"#alarmOne\").html();\r\n");
      out.write("\t\t\t\t\tvar sobj = $(\"#alarmTwo\").html();\r\n");
      out.write("\t\t\t\t\tvar tobj = $(\"#alarmThree\").html();\r\n");
      out.write("\t\t\t\t\tvar divId = \"emDivId\";\r\n");
      out.write("\t\t\t\t\ttry{\r\n");
      out.write("\t\t\t\t\t\tif(fobj>0){\r\n");
      out.write("\t\t\t\t\t\t\tvoiceUrl= \"images/muisc/alarn/1.mp3\";\r\n");
      out.write("\t\t\t\t\t    }else if(sobj>0){\r\n");
      out.write("\t\t\t\t\t    \tvoiceUrl= \"images/muisc/alarn/2.mp3\";\r\n");
      out.write("\t\t\t\t\t    }else if(tobj>0){\r\n");
      out.write("\t\t\t\t\t    \tvoiceUrl= \"images/muisc/alarn/3.mp3\";\r\n");
      out.write("\t\t\t\t\t    }\r\n");
      out.write("\t\t\t\t\t\tvar cacheBuster = \"?t=\" + Date.parse(new Date());\r\n");
      out.write("\t\t\t            // swf path\r\n");
      out.write("\t\t\t            var swfPath = \"manager/voice/preview.swf\";\r\n");
      out.write("\t\t\t\t\t\tvar stageW = 1;//560;//\"100%\";\r\n");
      out.write("\t\t\t\t\t\tvar stageH = 1;//300;//\"100%\";\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t// ATTRIBUTES\r\n");
      out.write("\t\t\t\t\t    var attributes = {};\r\n");
      out.write("\t\t\t\t\t    attributes.id = divId;\r\n");
      out.write("\t\t\t\t\t    attributes.name = attributes.id;\r\n");
      out.write("\t\t\t\t\t    \r\n");
      out.write("\t\t\t\t\t\t// PARAMS\r\n");
      out.write("\t\t\t\t\t\tvar params = {};\r\n");
      out.write("\t\t\t\t\t\tparams.allowfullscreen = \"true\";\r\n");
      out.write("\t\t\t\t\t\tparams.allowScriptAccess = \"always\";\r\n");
      out.write("\t\t\t\t\t\tparams.bgcolor = \"#ffffff\";\r\n");
      out.write("\t\t\t\t\t\tparams.wmode = \"transparent\";\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t\t\t    /* FLASH VARS */\r\n");
      out.write("\t\t\t\t\t\tvar flashvars = {};\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t/// if commented / delete these lines, the component will take the stage dimensions defined \r\n");
      out.write("\t\t\t\t\t\t/// above in \"JAVASCRIPT SECTIONS\" section or those defined in the settings xml\t\t\t\r\n");
      out.write("\t\t\t\t\t\tflashvars.componentWidth = stageW-1;//281; // define these dimensions different then the stage dimension only for preview because the component is samller then the stage\r\n");
      out.write("\t\t\t\t\t\tflashvars.componentHeight = stageH-1;//78;\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t/// path to the content folder(where the xml files, images or video are nested)\r\n");
      out.write("\t\t\t\t\t\t/// if you want to use absolute paths(like \"http://domain.com/images/....\") then leave it empty(\"\")\r\n");
      out.write("\t\t\t\t\t \tflashvars.pathToFiles = \"manager/\";\r\n");
      out.write("\t\t\t\t\t\tflashvars.xmlPath = \"voice/player/xml/settings.xml\";\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t\t// other vars\r\n");
      out.write("\t\t\t\t\t\tflashvars.songURL = voiceUrl;\r\n");
      out.write("\t\t\t\t\t\t/** EMBED THE SWF**/\r\n");
      out.write("\t\t\t\t\t\tswfobject.embedSWF(swfPath, attributes.id, stageW, stageH, \"9.0.124\", \"manager/voice/js/expressInstall.swf\", flashvars, params);\r\n");
      out.write("\t\t\t\t\t}catch (e1){\r\n");
      out.write("\t\t\t\t\t\t\r\n");
      out.write("\t\t\t\t\t}\r\n");
      out.write("\t\t\t\t\tsetTimeout(theAlert,8*1000);\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//告警播放\r\n");
      out.write("\t\t\tvar palySalter=function(obj){\r\n");
      out.write("\t\t\t\tobj.play();\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//停止三级告警播放\r\n");
      out.write("\t\t\tvar stopTimerTPlay=function(){\r\n");
      out.write("\t\t\t\tclearInterval(timerT);\r\n");
      out.write("\t\t\t\ttimerT = 0;\r\n");
      out.write("\t\t\t}\r\n");
      out.write("\t\t\t\r\n");
      out.write("\t\t\t//停止二级告警播放\r\n");
      out.write("\t\t\tvar stopTimerSPlay=function(){\r\n");
      out.write("\t\t\t\tclearInterval(timerS);\r\n");
      out.write("\t\t\t\ttimerS = 0;\r\n");
      out.write("\t\t\t}\r\n");
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("\t\t\t\t$(\"#emDivId\").show();\r\n");
      out.write("\r\n");
      out.write("\t\t</script>\r\n");
      out.write("\t\t\r\n");
      out.write("</head>\r\n");
      out.write("\r\n");
      out.write("<body onload=\"updateRcount();\" style=\" background-color: white;\">\r\n");
      out.write("<form action=\"\" method=\"get\" id=\"hiddenForm\" name = \"hiddenForm\">\r\n");
      out.write("</form>\r\n");
      out.write("<table width=\"100%\" border=\"0\" cellspacing=\"1\" bgcolor=\"#999999\"  class=\"shishijiankong_guolu_zt13\"  style=\"text-align: center; border: none;\">\r\n");
      out.write("  <tr bgcolor=\"#DCDDDD\" class=\"shishijiankongtai_313_zt\" style=\" background-color: #DCDDDD; font-size: 13px; font-family: '黑体'; color:#00487A;\"  valign=\"bottom\">\r\n");
      out.write("    <td>告警等级</td>\r\n");
      out.write("    <td>告警数量</td>\r\n");
      out.write("    <td>提&nbsp;&nbsp;示</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr style=\"height:25px; background-color: #F1F1F1;\">\r\n");
      out.write("    <td style=\" color:#404041; font-family: '华文细黑'; font-size: 13px;\">一级告警</td>\r\n");
      out.write("    <td class=\"biao_lianj_1\"><span class=\"ss_zhm_lh_zt\" id=\"levelo\">&nbsp;</span></td>\r\n");
      out.write("    <td style=\" color:#404041; font-family: '华文细黑'; font-size: 13px;\">紧急处理</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr style=\"height:25px; background-color: #F7F8F8;\">\r\n");
      out.write("    <td style=\" color:#404041; font-family: '华文细黑'; font-size: 13px;\">二级告警</td>\r\n");
      out.write("    <td class=\"biao_lianj_1\"><span class=\"ss_zhm_lh_zt\" id=\"levelt\">&nbsp;</span></td>\r\n");
      out.write("    <td style=\" color:#404041; font-family: '华文细黑'; font-size: 13px;\">尽快处理</td>\r\n");
      out.write("  </tr>\r\n");
      out.write("  <tr style=\"height:25px; background-color: #F1F1F1;\">\r\n");
      out.write("    <td style=\" color:#404041; font-family: '华文细黑'; font-size: 13px;\">三级告警</td>\r\n");
      out.write("    <td class=\"biao_lianj_1\"><span class=\"ss_zhm_lh_zt\" id=\"levelth\">&nbsp;</span></td>\r\n");
      out.write("    <td style=\" color:#404041; font-family: '华文细黑'; font-size: 13px;\">安排处理</td>\r\n");
      out.write("  </tr>\r\n");
      out.write('\r');
      out.write('\n');
      out.write('\r');
      out.write('\n');
      out.write("\r\n");
      out.write("</table>\r\n");
      out.write("\r\n");
      out.write(" <div id=\"emDivId\" style=\"display:none\">\r\n");
      out.write("</div>\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
