<%@ page language="java" pageEncoding="UTF-8"%>
<%@ page import="com.afik.Course" %>
<%@ page import="org.json.JSONObject" %>
<%
    JSONObject json = new JSONObject();
    JSONObject jsonCourse = new JSONObject();
    JSONObject jsonUser = new JSONObject();
    Course course = (Course) request.getAttribute("result");

    if (course != null) {
        json.put("result", new Boolean(true));
        jsonCourse.put("info", course.getAdditionalInfo());
        jsonCourse.put("building", course.getBuilding());
        jsonCourse.put("courseName", course.getCourseName());
        jsonCourse.put("lecturer", course.getLecturer());
        jsonCourse.put("room", course.getRoom());
        json.put("object", jsonCourse);
    } else {
        json.put("result", new Boolean(false));
    }
    response.getWriter().print(json);
%>
