<%@ page import="org.json.JSONObject" %>
<%@ page language="java" %>
<%
    JSONObject json = new JSONObject();

    if (request.getAttribute("result") == "true") {
        json.put("result", new Boolean(true));
    }
    else{
        json.put("result", new Boolean(false));
    }
    response.getWriter().print(json);
%>