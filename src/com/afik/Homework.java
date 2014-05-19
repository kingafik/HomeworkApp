package com.afik;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

/**
 * This Class represent Homework in the system.
 * Fields: homeworkId, taskName, course, user, type, priority, date, additionalInfo.
 * SQL: Table name = Homework,
 * Columns: HOMEWORK_ID(ID), TASK_NAME, TYPE, PRIORITY, DATE, INFO.
 * Description: Every user is in OneToMany relationship with homework and course
 * is in ManyToOne relationship with Homework, therefore COURSE_ID, USERNAME is a constrain key in Homework.
 */
@Entity
@Table(name = "Homework")
public class Homework implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Column(name = "HOMEWORK_ID", nullable = false, unique = true)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long homeworkId;

    @Column(name = "TASK_NAME", nullable = false)
    private String taskName;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "HOMEWORK_COURSE_ID")
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USERNAME", nullable = false)
    private User user;

    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private HomeworkType type;

    @Temporal(TemporalType.DATE)
    @Column(name = "DATE", nullable = true)
    private Date date;

    @Enumerated(EnumType.STRING)
    @Column(name = "PRIORITY", nullable = false)
    private PriorityType priority;

    @Column(name = "INFO", nullable = true, length = 100)
    private String additionalInfo;

    public Homework() {
        priority = PriorityType.LOW;
        type = HomeworkType.OTHER;
    }

    public Homework(String taskName, Course course, HomeworkType type,
                    Date date, PriorityType priority, String additionalInfo) {
        super();
        this.taskName = taskName;
        this.course = course;
        this.type = type;
        this.date = date;
        this.priority = priority;
        this.additionalInfo = additionalInfo;
    }

    public long getHomeworkId() {
        return this.homeworkId;
    }

    public void setHomeworkId(long homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public HomeworkType getType() {
        return type;
    }

    public void setType(HomeworkType type) {
        this.type = type;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public PriorityType getPriority() {
        return priority;
    }

    public void setPriority(PriorityType priority) {
        this.priority = priority;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return ("[ " + taskName + "]");
        /*
         * ", " + course.toString() + ", " + type.toString() + ", " +
		 * date.toString() + ", " + priority.toString() + ", " + additionalInfo
		 * + "]");
		 */
    }

}
