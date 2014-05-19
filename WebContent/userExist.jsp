<%@page import="org.json.JSONObject" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
    JSONObject ob = new JSONObject();
    if (request.getAttribute("result") == "true")
        ob.put("result", new Boolean(true));
    else
        ob.put("result", new Boolean(false));
    response.getWriter().print(ob);
%>
