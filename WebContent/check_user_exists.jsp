<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="/WEB-INF/customtag" prefix="customTag" %>

<%
    String login = request.getParameter("username");
%>
<customTag:checkLogin login="<%=login%>"/>