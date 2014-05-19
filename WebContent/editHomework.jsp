<%@ page language="java" pageEncoding="UTF-8" %>
<%@ page import="com.afik.Homework" %>
<%@ page import="org.json.JSONObject" %>
<%
    JSONObject json = new JSONObject();
    JSONObject jsonHomework = new JSONObject();
    JSONObject jsonCourse = new JSONObject();
    JSONObject jsonUser = new JSONObject();
    Homework homework = (Homework) request.getAttribute("result");
    if (homework != null) {
        json.put("result", new Boolean(true));
        jsonHomework.put("info", homework.getAdditionalInfo());
        if (homework.getDate() != null) {
            jsonHomework.put("date", homework.getDate().toString());
        }
        if (homework.getPriority() != null) {
            jsonHomework.put("priority", homework.getPriority().toString());
        }
        jsonHomework.put("taskName", homework.getTaskName());
        if (homework.getType() != null) {
            jsonHomework.put("type", homework.getType().toString());
        }
        if (homework.getCourse() != null) {
            jsonHomework.put("courseName", homework.getCourse().getCourseName());
        }
        json.put("object", jsonHomework);
    } else {
        json.put("result", new Boolean(false));
    }
    response.getWriter().print(json);
%>
