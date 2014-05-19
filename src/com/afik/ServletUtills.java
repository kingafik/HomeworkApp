package com.afik;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.math.BigInteger;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;

/**
 * This class is responsible the connect the database to the servlet
 * and handle the request and response.
 */
public class ServletUtills {
    private static Logger logger = null;
    private IHomework controller = null;

    public ServletUtills() {
        controller = DBController.createHibernateDAOManager();
        logger = Logger.getLogger(ServletUtills.class.getName());
        PropertyConfigurator.configure("log4j.properties");
        BasicConfigurator.configure();
    }

    /**
     * Add user to the database.
     *
     * @param request  should include parameters like: username and password.
     * @param response pass/fail, when pass is the result it also return the user object.
     * @throws HomeworkException, NoSuchAlgorithmException
     */
    public void addUser(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, HomeworkException {
        logger.info("<---> AddUser method has been Invoke! <--->");

        String username, password, md5;
        User user = new User();
        MessageDigest digest = MessageDigest.getInstance("MD5");

        if ((username = request.getParameter("username")) != null)
            user.setUsername(username);
        if ((password = request.getParameter("password")) != null) {
            digest.update(password.getBytes(), 0, password.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
            user.setPassword(md5);
        }
        if (controller.add(user)) {
            request.setAttribute("result", user);
            logger.info("<---> Add User PASS! <--->");
        } else {
            logger.error("<---> Add User Failed! <--->");
        }
        logger.info("<---> AddUser method has been Ended! <--->");
    }

    /**
     * Add course to the database.
     *
     * @param request  should include parameters like: username, courseName, lecturer, building, room, info.
     * @param response pass/fail, when pass is the result it also return the course object.
     * @throws HomeworkException
     */
    public void addCourse(HttpServletRequest request, HttpServletResponse response) throws HomeworkException {
        String courseName, lecturer, building, room, info, username;
        Course course = new Course();

        logger.info("<---> AddCourse method has been Invoke! <--->");
        if ((username = request.getParameter("username")) != null)
            course.setUser(controller.getUser(username));
        if ((courseName = request.getParameter("courseName")) != null)
            course.setCourseName(courseName);
        if ((lecturer = request.getParameter("lecturer")) != null)
            course.setLecturer(lecturer);
        if ((building = request.getParameter("building")) != null)
            course.setBuilding(building);
        if ((room = request.getParameter("room")) != null)
            course.setRoom(room);
        if ((info = request.getParameter("info")) != null)
            course.setAdditionalInfo(info);
        if (controller.add(course)) {
            request.setAttribute("result", course);
            logger.info("<---> Add Course PASS! <--->");
        } else {
            logger.error("<---> Add Course Failed! <--->");
        }
        logger.info("<---> AddCourse method has been Ended! <--->");
    }

    /**
     * Add homework to the database.
     *
     * @param request  should include parameters like: username, course, date, taskName, priority, type, info.
     * @param response pass/fail, when pass is the result it also return the homework object.
     * @throws HomeworkException, ParseException
     */
    public void addHomework(HttpServletRequest request, HttpServletResponse response) throws ParseException, HomeworkException {
        logger.info("<---> addHomework method has been Invoke! <--->");

        String taskName, priority, type, course, info, username, dateStr;
        Homework homework = new Homework();

        if ((username = request.getParameter("username")) != null)
            homework.setUser(controller.getUser(username));
        if ((course = request.getParameter("course")) != null)
            homework.setCourse(controller.getCourse(course, homework.getUser()));
        if ((taskName = request.getParameter("taskName")) != null)
            homework.setTaskName(taskName);
        if ((priority = request.getParameter("priority")) != null)
            homework.setPriority(PriorityType.valueOf(priority));
        if ((type = request.getParameter("type")) != null)
            homework.setType(HomeworkType.valueOf(type));
        if ((dateStr = request.getParameter("date")) != null) {
            DateFormat df = new SimpleDateFormat("yyyy-mm-dd");
            homework.setDate(df.parse(dateStr));
        }
        if ((info = request.getParameter("info")) != null)
            homework.setAdditionalInfo(info);

        if (controller.add(homework)) {
            request.setAttribute("result", homework);
            logger.info("<---> Add Homework PASS! <--->");
        } else {
            logger.error("<---> Add Homework Failed! <--->");
        }
        logger.info("<---> addHomework method has been Ended! <--->");
    }

    /**
     * Edit homework in the database.
     *
     * @param request  should include parameters like: username, courseName, taskName, date, priority, type, info.
     * @param response pass/fail, when pass is the result it also return the homework object.
     * @throws HomeworkException, ParseException
     */
    public void editHomeWork(HttpServletRequest request, HttpServletResponse response) throws ParseException, HomeworkException {
        logger.info("<---> editHomework method has been Invoke! <--->");

        String priority, type, info, dateStr;
        Homework homework = null;
        User user = controller.getUser(request.getParameter("username"));
        Course course = controller.getCourse(request.getParameter("courseName"), user);

        homework = controller.getHomework(request.getParameter("taskName"), user, course);
        if ((priority = request.getParameter("priority")) != null)
            homework.setPriority(PriorityType.valueOf(priority));
        if ((type = request.getParameter("type")) != null)
            homework.setType(HomeworkType.valueOf(type));
        if ((dateStr = request.getParameter("date")) != null) {
            DateFormat df = new SimpleDateFormat("yyyy MM dd");
            homework.setDate(df.parse(dateStr));
        }
        if ((info = request.getParameter("info")) != null)
            homework.setAdditionalInfo(info);

        if (controller.edit(homework)) {
            request.setAttribute("result", homework);
            logger.info("<---> edit Homework PASS! <--->");
        } else {
            logger.error("<---> edit Homework Failed! <--->");
        }
        logger.info("<---> editHomework method has been Ended! <--->");
    }

    /**
     * Edit course in the database.
     *
     * @param request  should include parameters like: username, courseName, lecturer, building, room, info.
     * @param response pass/fail, when pass is the result it also return the course object.
     * @throws HomeworkException
     */
    public void editCourse(HttpServletRequest request, HttpServletResponse response) throws HomeworkException {
        String lecturer, building, room, info;
        Course course = null;
        User user = controller.getUser(request.getParameter("username"));

        logger.info("<---> editCourse method has been Invoke! <--->");
        course = controller.getCourse(request.getParameter("courseName"), user);
        if ((lecturer = request.getParameter("lecturer")) != null)
            course.setLecturer(lecturer);
        if ((building = request.getParameter("building")) != null)
            course.setBuilding(building);
        if ((room = request.getParameter("room")) != null)
            course.setRoom(room);
        if ((info = request.getParameter("info")) != null)
            course.setAdditionalInfo(info);
        if (controller.edit(course)) {
            request.setAttribute("result", course);
            logger.info("<---> edit Course PASS! <--->");
        } else {
            logger.error("<---> edit Course Failed! <--->");
        }
        logger.info("<---> editCourse method has been Ended! <--->");
    }

    /**
     * Check if the user is already exist in the database.
     *
     * @param request  should include parameters like: username and password.
     * @param response pass/fail.
     * @throws HomeworkException, NoSuchAlgorithmException
     */
    public void userExist(HttpServletRequest request, HttpServletResponse response) throws NoSuchAlgorithmException, HomeworkException {
        logger.info("<---> userExist method has been Invoke! <--->");

        String username, password, md5;
        User user = null;
        MessageDigest digest = MessageDigest.getInstance("MD5");

        if ((username = request.getParameter("username")) != null)
            user = controller.getUser(username);
        if ((password = request.getParameter("password")) != null && user != null) {
            digest.update(password.getBytes(), 0, password.length());
            md5 = new BigInteger(1, digest.digest()).toString(16);
            if (user.getPassword().equals(md5)) {
                request.setAttribute("result", "true");
                logger.info("<---> User Exist PASS! <--->");
            } else {
                request.setAttribute("result", "false");
            }
        } else {
            logger.error("<---> User Exist Failed! <--->");
        }
        logger.info("<---> userExist method has been Ended! <--->");
    }

    /**
     * Get all user registers courses as an iterator.
     *
     * @param request  should include parameters like: username.
     * @param response iterator of courses.
     * @throws HomeworkException
     */
    public void getCourses(HttpServletRequest request, HttpServletResponse response) throws HomeworkException {
        logger.info("<---> getCourses method has been Invoke! <--->");

        String username;
        User user = null;

        if ((username = request.getParameter("username")) != null)
            if ((user = controller.getUser(username)) != null) {
                Iterator<Course> courses = controller.getCourses(user);
                request.setAttribute("result", courses);
                logger.info("<---> Get Courses PASS! <--->");
            } else {
                logger.error("<---> Get Courses Failed! <--->");
            }
        logger.info("<---> getCourses method has been Ended! <--->");

    }

    /**
     * Get all user registers homeworks as an iterator.
     *
     * @param request should include parameters like: username.
     * @param response iterator of homeworks.
     * @throws HomeworkException
     */
    public void getHomeworks(HttpServletRequest request, HttpServletResponse response) throws HomeworkException {
        logger.info("<---> getHomeworks method has been Invoke! <--->");

        String username;
        User user = null;

        if ((username = request.getParameter("username")) != null)
            if ((user = controller.getUser(username)) != null) {
                Iterator<Homework> homeworkIterator = controller.getHomeworks(user);
                request.setAttribute("result", homeworkIterator);
                logger.info("<---> Get Homeworks PASS! <--->");
            } else {
                logger.error("<---> Get Homeworks Failed! <--->");
            }
        logger.info("<---> getHomeworks method has been Ended! <--->");

    }

    /**
     * Delete the given course from the database and delete all homework reference for the same user.
     * @param request should include parameters like: username and courseName.
     * @param response pass/fail.
     * @throws HomeworkException
     */
    public void deleteCourse(HttpServletRequest request, HttpServletResponse response) throws HomeworkException {

        boolean passFlag = false;
        String courseName, username;
        User user = null;

        if ((user = controller.getUser(request.getParameter("username"))) != null) {
            if ((courseName = request.getParameter("courseName")) != null) {
                if (controller.delete(controller.getCourse(courseName, user))) {
                    request.setAttribute("result", "true");
                    passFlag = true;
                }
            }
        }
        if (!passFlag) {
            request.setAttribute("result", "false");
        }
    }

    /**
     * Delete the given homework from the database.
     * @param request should include parameters like: username, taskName and courseName.
     * @param response pass/fail.
     * @throws HomeworkException
     */
    public void deleteHomework(HttpServletRequest request, HttpServletResponse response) throws HomeworkException {

        boolean passFlag = false;
        String taskName, username, courseName;
        User user = null;
        Course course = null;

        if ((user = controller.getUser(request.getParameter("username"))) != null) {
            if ((course = controller.getCourse(request.getParameter("courseName"), user)) != null) {
                if (controller.delete(controller.getHomework(request.getParameter("taskName"), user, course))) {
                    request.setAttribute("result", "true");
                    passFlag = true;
                }
            }
        }
        if (!passFlag) {
            request.setAttribute("result", "false");
        }
    }
}
