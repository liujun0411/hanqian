<%
response.Write("<b>±íµ¥ÄÚÈÝ£º</b><br><br>")
response.Write(server.HTMLEncode(request.Form("content")))
%>