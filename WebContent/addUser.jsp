<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.afik.User" %>
<%@ page import="org.json.JSONObject" %>
        <%
            JSONObject json = new JSONObject();
            JSONObject jsonUser = new JSONObject();
            User user = (User) request.getAttribute("result");
            if (user != null) {
                json.put("result", new Boolean(true));
                jsonUser.put("username", user.getUsername());
                jsonUser.put("password", user.getPassword());
                json.put("object", jsonUser);
            } else {
                json.put("result", new Boolean(false));
            }
            response.getWriter().print(json);
        %>
