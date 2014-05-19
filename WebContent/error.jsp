<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="org.json.JSONObject" %>
<%
    JSONObject json = new JSONObject();
    json.put("result", new Boolean(false));
    response.getWriter().print(json);
%>
