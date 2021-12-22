package database;

import domain.ExternalPerson;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ExternalPersonSQL extends ConnectToDatabase {
    public ObservableList<ExternalPerson> getExternalPersonList() {
        ObservableList<ExternalPerson> externalPersonList = FXCollections.observableArrayList();
        Connection conn = getConnection();
        String query = "SELECT * FROM ExternalPerson";
        Statement st;
        ResultSet rs;
        
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            ExternalPerson externalPerson;
            while(rs.next()) {
                externalPerson = new ExternalPerson(rs.getInt("ExternalPersonID"), rs.getString("ExternalPersonName"), rs.getString("ExternalPersonEmail"), rs.getString("ExternalPersonOrganisation"), rs.getString("ExternalPersonRole"));
                externalPersonList.add(externalPerson);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return externalPersonList;
    }

    public void createExternalPerson(ExternalPerson externalPerson) {
        Connection conn = getConnection();
        String query = "INSERT INTO ExternalPerson VALUES ('" + externalPerson.getName() + "', '" + externalPerson.getEmail() + "', '" + externalPerson.getOrganisation() + "', '" + externalPerson.getRole() + "')";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("External person created!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void updateExternalPerson(ExternalPerson externalPerson) {
        Connection conn = getConnection();
        String query = "UPDATE ExternalPerson SET ExternalPersonName = '" + externalPerson.getName() + "', ExternalPersonEmail = '" + externalPerson.getEmail() + "', ExternalPersonOrganisation = '" + externalPerson.getOrganisation() + "', ExternalPersonRole = '" + externalPerson.getRole() + "' WHERE ExternalPersonID = '" + externalPerson.getExternalPersonId() + "'";
        Statement st;

        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("External person updated!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void deleteExternalPerson(ExternalPerson externalPerson) {
        Connection conn = getConnection();
        String query = "DELETE FROM ExternalPerson WHERE ExternalPersonID = '" + externalPerson.getExternalPersonId() + "'";
        Statement st;
        try {
            st = conn.createStatement();
            st.executeQuery(query);
            System.out.println("External person deleted!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
