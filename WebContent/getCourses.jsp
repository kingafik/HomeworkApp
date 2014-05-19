<%@page import="com.afik.Course" %>
<%@page import="org.json.JSONArray" %>
<%@page import="org.json.JSONObject" %>
<%@page import="java.util.Iterator" %>
        <%@ page language="java" pageEncoding="UTF-8"%>
<%
    Course course = null;
    Iterator<Course> itr = null;
    JSONObject json = new JSONObject();
    JSONObject jsonUser = new JSONObject();
    JSONArray jsonArray = new JSONArray();

    itr = (Iterator<Course>) request.getAttribute("result");
    if (itr != null) {
        while (itr.hasNext()) {
            course = itr.next();
            JSONObject jsonCourse = new JSONObject();
            jsonCourse.put("courseName", course.getCourseName());
            jsonCourse.put("room", course.getRoom());
            jsonCourse.put("lecturer", course.getLecturer());
            jsonCourse.put("building", course.getBuilding());
            jsonCourse.put("info", course.getAdditionalInfo());
            jsonArray.put(jsonCourse);
        }
        json.put("result", new Boolean(true));
        json.put("object", jsonArray);
    } else {
        json.put("result", new Boolean(false));
    }
    response.getWriter().print(json);
%>
