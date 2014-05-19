package com.afik;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

/**
 * Application Main and only Servlet!
 * all user request in POST or GET method are first enter this servlet class.
 */
@WebServlet("/ServletController/*")
public class ServletController extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static Logger logger = null;
    private ServletUtills servletUtils;

    public ServletController() {
        super();
        servletUtils = new ServletUtills();
        logger = Logger.getLogger(ServletUtills.class.getName());
        PropertyConfigurator.configure("log4j.properties");
        BasicConfigurator.configure();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doGet method invoked" + request.toString());
        RequestDispatcher dispacher = null;
        MethodType method = null;
        String[] splitSTR = request.getPathInfo().split("/");

        // split return n+1 parameters because the string start with '/' so we
        // jump to the second index
        method = MethodType.valueOf(splitSTR[1]);
        dispacher = request.getRequestDispatcher("/" + splitSTR[1] + ".jsp");
        try {
            switch (method) {

                case addCourse:
                    servletUtils.addCourse(request, response);
                    break;
                case addHomework:
                    servletUtils.addHomework(request, response);
                    break;
                case addUser:
                    servletUtils.addUser(request, response);
                    break;
                case editCourse:
                    servletUtils.editCourse(request, response);
                    break;
                case editHomework:
                    servletUtils.editHomeWork(request, response);
                    break;
                case userExist:
                    servletUtils.userExist(request, response);
                    break;
                case getCourses:
                    servletUtils.getCourses(request, response);
                    break;
                case getHomeworks:
                    servletUtils.getHomeworks(request, response);
                    break;
                case deleteCourse:
                    servletUtils.deleteCourse(request, response);
                    break;
                case deleteHomework:
                    servletUtils.deleteHomework(request, response);
                    break;
                case about:
                    break;
                case setting:
                    break;
                default:
                    logger.error(method + " isn't recognize.");
                    break;
            }
            logger.info(method + " complete successfully, forward to the specific JSP for client result.");
            response.addHeader("Access-Control-Allow-Origin", "*");
            response.setContentType("application/json");
            dispacher.forward(request, response);
        } catch (ParseException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (NoSuchAlgorithmException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        } catch (HomeworkException e) {
            logger.error(e.getMessage());
            e.printStackTrace();
            request.getRequestDispatcher("error.jsp").forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        logger.info("doPost invoked. \n calling doGet with the same parameters.");
        doGet(request, response);
    }

}
