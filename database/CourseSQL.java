package database;

import domain.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class CourseSQL extends ConnectToDatabase {

    public ObservableList<Course> getCourseList() {
        ObservableList<Course> courseList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM Course";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            Course course;
            while(rs.next()) {
                course = new Course(rs.getString("CourseName"), rs.getString("CourseTopic"), rs.getString("CourseIntroduction"), rs.getString("CourseLevel"), rs.getString("HasRelevantCourse"));
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public void createCourse(Course course) {
        Connection conn = getConnection();
        String query = "INSERT INTO Course VALUES ('" + course.getName() + "', '" + course.getTopic() + "', '" + course.getIntroduction() + "', '" + course.getLevel() + "', '" + course.getRelCourse() + "')";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Course created!");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void updateCourse(Course course) {
        Connection conn = getConnection();
        String query = "UPDATE Course SET CourseName = '" + course.getName() + "', CourseTopic = '" + course.getTopic() + "', CourseIntroduction = '" + course.getIntroduction() + "', CourseLevel = '" + course.getLevel() + "', HasRelevantCourse = '" + course.getRelCourse() + "' WHERE CourseName = '" + course.getName() + "'";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Course updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteCourse(Course course) {
        Connection conn = getConnection();
        String query = "DELETE FROM Course WHERE CourseName = '" + course.getName() + "'";
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("Course deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
