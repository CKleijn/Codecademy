package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

public class StatisticSQL extends ConnectToDatabase {
    // public String calculateGenderCertificates(String gender){
    //     Connection conn = getConnection();
    //     String query = "SELECT (SELECT COUNT(*) FROM Registration INNER JOIN Student ON Registration.StudentEmail = Student.StudentEmail WHERE Student.StudentGender = '" + gender + "' AND Registration.CertificateID IS NOT NULL) / (SELECT COUNT(*) FROM Registration INNER JOIN Student ON Registration.StudentEmail = Student.StudentEmail WHERE Student.StudentGender = '" + gender + "') * 100";
    //     Statement st;
    //     ResultSet rs;
    //     try {
    //         st = conn.createStatement();
    //         rs = st.executeQuery(query);
    //         String percentage;
    //         while(rs.next()){
    //             percentage = rs.getString();
    //             return percentage;
    //         }
    //         System.out.println("got the webcasts!");
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //     }

    //     return null;
    // }

    public String[] getTop3MostViewedWebcasts() {
        Connection conn = getConnection();
        ArrayList<String> webcasts = new ArrayList<>();
        String query = "SELECT TOP 3 Item.ItemTitle, SUM(Student_View_Item.ItemViews) AS Total FROM Webcast INNER JOIN Item ON Webcast.ItemID = Item.ItemID INNER JOIN Student_View_Item ON Item.ItemID = Student_View_Item.ItemID GROUP BY Item.ItemTitle ORDER BY Total DESC";
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

        String[] strWebcasts = new String[webcasts.size()];
        for(int i = 0; i < webcasts.size(); i++){
            strWebcasts[i] = webcasts.get(i);
        }

        return strWebcasts;
    }

    public String[] getTop3MostCertificateCourses() {
        Connection conn = getConnection();
        ArrayList<String> courses = new ArrayList<>();
        String query = "SELECT TOP 3 Course.CourseName, COUNT(Registration.CertificateID) AS Total FROM Course INNER JOIN Registration ON Course.CourseName = Registration.CourseName GROUP BY Course.CourseName ORDER BY Total DESC";
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
}
