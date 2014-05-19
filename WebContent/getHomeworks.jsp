<%@page import="com.afik.Homework" %>
<%@page import="org.json.JSONArray" %>
<%@ page import="org.json.JSONObject" %>
<%@ page import="java.util.Iterator" %>
<%@ page language="java" pageEncoding="UTF-8"%>
<%
    Homework homework = null;
    Iterator<Homework> itr = null;
    JSONObject json = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    itr = (Iterator<Homework>) request.getAttribute("result");
    if (itr != null) {
        while (itr.hasNext()) {
            homework = itr.next();
            JSONObject jsonHomework = new JSONObject();
            jsonHomework.put("info", homework.getAdditionalInfo());
            if (homework.getType() != null) {
                jsonHomework.put("type", homework.getType().toString());
            }
            jsonHomework.put("taskName", homework.getTaskName());
            if (homework.getPriority() != null) {
                jsonHomework.put("priority", homework.getPriority().toString());
            }
            if (homework.getDate() != null) {
                jsonHomework.put("date", homework.getDate().toString());
            }
            if (homework.getCourse() != null) {
                jsonHomework.put("courseName", homework.getCourse().getCourseName());
            }
            jsonArray.put(jsonHomework);
        }
        json.put("result", new Boolean(true));
        json.put("object", jsonArray);
    } else {
        json.put("result", new Boolean(false));
    }
    response.getWriter().print(json);

%>
