package database;

import domain.Course;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

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
                course = new Course(rs.getString("CourseName"), rs.getString("CourseTopic"), rs.getString("CourseIntroduction"), rs.getString("CourseLevel"));
                courseList.add(course);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return courseList;
    }

    public void createCourse(Course course) {
        Connection conn = getConnection();
        String query = "INSERT INTO Course VALUES ('" + course.getName() + "', '" + course.getTopic() + "', '" + course.getIntroduction() + "', '" + course.getLevel() + "')";
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
        String query = "UPDATE Course SET CourseName = '" + course.getName() + "', CourseTopic = '" + course.getTopic() + "', CourseIntroduction = '" + course.getIntroduction() + "', CourseLevel = '" + course.getLevel() + "' WHERE CourseName = '" + course.getName() + "'";
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

    public String[] getSpecificModules(Course course) {
        Connection conn = getConnection();
        ArrayList<String> modules = new ArrayList<>();
        String query = "SELECT ItemTitle FROM Item LEFT JOIN Module ON Item.ItemID = Module.ItemID WHERE Module.CourseName = '" + course.getName() + "'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            String moduleTitle;
            while(rs.next()){
                moduleTitle = new String(rs.getString("ItemTitle"));
                modules.add(moduleTitle);
            }
            System.out.println("got the modules!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strModules = new String[modules.size()];
        for(int i = 0; i < modules.size(); i++){
            strModules[i] = modules.get(i);
        }

        return strModules;

    }

    public String[] getModules() {
        Connection conn = getConnection();
        ArrayList<String> modules = new ArrayList<>();
        String query = "SELECT Item.ItemTitle FROM Module LEFT JOIN Item ON Item.ItemID = Module.ItemID";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            String moduleTitle;
            while(rs.next()){
                moduleTitle = new String(rs.getString("ItemTitle"));
                modules.add(moduleTitle);
            }
            System.out.println("got the modules!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strModules = new String[modules.size()];
        for(int i = 0; i < modules.size(); i++){
            strModules[i] = modules.get(i);
        }

        return strModules;
    }

    public void setModulesCourseName(String itemTitle, String CourseName){
        Connection conn = getConnection();
        String query = "SELECT ItemID FROM Item WHERE ItemTitle = " + itemTitle;
        String query2 = "UPDATE Module SET CourseName = '" + CourseName + "' WHERE ItemID = (" + query + ")";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query2);
            System.out.println("CourseName added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
