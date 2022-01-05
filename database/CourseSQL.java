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

    public String[] getCourses() {
        Connection conn = getConnection();
        ArrayList<String> courses = new ArrayList<>();
        String query = "SELECT CourseName FROM Course";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            String courseName;
            while(rs.next()){
                courseName = new String(rs.getString("CourseName"));
                courses.add(courseName);
            }
            System.out.println("got the courses!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strCourses = new String[courses.size()];
        for(int i = 0; i < courses.size(); i++){
            strCourses[i] = courses.get(i);
        }

        return strCourses;

    }

    public String[] getModules() {
        Connection conn = getConnection();
        ArrayList<String> modules = new ArrayList<>();
        String query = "SELECT ItemTitle FROM Item JOIN Module ON Item.ItemID = Module.ItemID WHERE CourseName IS NULL";
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

    public String[] getWebcasts() {
        Connection conn = getConnection();
        ArrayList<String> webcasts = new ArrayList<>();
        String query = "SELECT ItemTitle FROM Item JOIN Webcast ON Item.ItemID = Webcast.ItemID WHERE CourseName IS NULL";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            String webcastTitle;
            while(rs.next()){
                webcastTitle = new String(rs.getString("ItemTitle"));
                webcasts.add(webcastTitle);
            }
            System.out.println("got the webcasts!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strModules = new String[webcasts.size()];
        for(int i = 0; i < webcasts.size(); i++){
            strModules[i] = webcasts.get(i);
        }

        return strModules;
    }

    public String[] getSpecificModules(Course course) {
        Connection conn = getConnection();
        ArrayList<String> modules = new ArrayList<>();
        String query = "SELECT ItemTitle FROM Item JOIN Module ON Item.ItemID = Module.ItemID WHERE CourseName = '" + course.getName() + "'";
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

    public String[] getSpecificWebcasts(Course course) {
        Connection conn = getConnection();
        ArrayList<String> modules = new ArrayList<>();
        String query = "SELECT ItemTitle FROM Item JOIN Webcast ON Item.ItemID = Webcast.ItemID WHERE CourseName = '" + course.getName() + "'";
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
            System.out.println("got the webcasts!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strModules = new String[modules.size()];
        for(int i = 0; i < modules.size(); i++){
            strModules[i] = modules.get(i);
        }

        return strModules;

    }

    
    public void setCourseName(String itemTitle, String CourseName){
        Connection conn = getConnection();
        String query = "UPDATE Item SET CourseName = '" + CourseName + "' WHERE ItemTitle = '" + itemTitle + "'";
        Statement st;
        
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("CourseName added!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setNull(String itemTitle){
        Connection conn = getConnection();
        String query = "UPDATE Item SET CourseName = NULL WHERE ItemTitle = ('" + itemTitle + "')";
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("CourseName is NULL!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public String[] relevantCourses(Course course){
        Connection conn = getConnection();
        ArrayList<String> relCourses = new ArrayList<>();
        String query = "SELECT CourseName FROM Course WHERE CourseTopic = '" + course.getTopic() + "'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            String relcourse;
            while(rs.next()){
                relcourse = new String(rs.getString("CourseName"));
                if(!relcourse.equals(course.getName())){
                    relCourses.add(relcourse);
                }
            }
            System.out.println("got the webcasts!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strRelcourse = new String[relCourses.size()];
        for(int i = 0; i < relCourses.size(); i++){
            strRelcourse[i] = relCourses.get(i);
        }

        return strRelcourse;
    }

    public int obtainedCertificates(Course course) {
        Connection conn = getConnection();
        String query = "SELECT Course.CourseName, COUNT(Registration.CertificateID) AS Total FROM Course INNER JOIN Registration ON Course.CourseName = Registration.CourseName GROUP BY Course.CourseName HAVING Course.CourseName = '" + course.getName() + "' ORDER BY Total DESC";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int obtainedCertificates;
            while(rs.next()){
                obtainedCertificates = rs.getInt("Total");
                return obtainedCertificates;
            }
            System.out.println("got the webcasts!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return 0;
    }
}
