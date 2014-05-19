package com.afik;

import java.util.Iterator;

/**
 * Interface that allow to run basic database methods.
 * such as: add, edit and more...
 */
public interface IHomework {
    /**
     * Add an object to the database.
     *
     * @param ob can only be one of each classes, User, Course, Homework.
     * @return true is the operation is success, false otherwise.
     * @throws ClassCastException when the input object isn't one above.
     */
    public boolean add(Object ob) throws HomeworkException;

    /**
     * Get the requested course from the database.
     *
     * @param id - String that represent the courseName.
     * @param user       - User that reference user in the specific course.
     * @return the requested course or null when method failed.
     */
    public Course getCourse(String id, User user) throws HomeworkException;

    /**
     * Get the requested homework from the database.
     *
     * @param id - String that represent the task/homework name.
     * @param user     - User that reference user in the specific homework.
     * @param course   - Course that reference course in the specific homework.
     * @return the requested homework or null when method failed.
     */
    public Homework getHomework(String id, User user, Course course) throws HomeworkException;

    /**
     * Get the requested user from the database.
     *
     * @param id - String that represent the username.
     * @return the requested user or null when method failed.
     */
    public User getUser(String id) throws HomeworkException;

    /**
     * Edit the requested course/homework into the database.
     *
     * @param ob - Course/Homework that you want to edit with the new values.
     * @return true is the operation is success, false otherwise.
     * @throws ClassCastException when the input object isn't one above.
     */
    public boolean edit(Object ob) throws HomeworkException;

    /**
     * Get all courses the registers on the input user.
     *
     * @param user - User that all courses are wanted.
     * @return the requested iterator of courses for the specific user or null when method failed.
     */
    public Iterator<Course> getCourses(User user) throws HomeworkException;

    /**
     * Get all homeworks the registers on the input user.
     *
     * @param user - - User that all homeworks are wanted.
     * @return the requested iterator of homeworks for the specific user or null when method failed.
     */
    public Iterator<Homework> getHomeworks(User user) throws HomeworkException;

    /**
     * Delete an Object from the database.
     * @param ob can only be one of each classes Course and Homework.
     * @return true is the operation is success, false otherwise.
     */
    public boolean delete(Object ob) throws HomeworkException;
}
