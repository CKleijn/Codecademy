package database;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;

import domain.Item;

public class ExternalPersonSQL extends ConnectToDatabase {
    public int findExternalPersonID(String externalPersonName) {
        Connection conn = getConnection();
        HashMap<Integer, Integer> externalPersonIDList = new HashMap<>();
        String query = "SELECT ExternalPersonID FROM ExternalPerson WHERE ExternalPersonName = '" + externalPersonName + "'";
        Statement st;
        ResultSet rs;
        int id = 0;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            int externalPersonID;
            while(rs.next()){
                externalPersonID = rs.getInt("externalPersonID");
                externalPersonIDList.put(id, externalPersonID);
            }
            System.out.println("got the external person id!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return externalPersonIDList.get(id);
    }

    public String getExternalPersonNameById(Item item) {
        Connection conn = getConnection();
        String query = "SELECT ExternalPersonName FROM ExternalPerson WHERE ExternalPersonID = '" + item.getExternalPerson() + "'";
        Statement st;
        ResultSet rs;
        String externalPersonName;

        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            while(rs.next()){
                externalPersonName = new String(rs.getString("ExternalPersonName"));
                return externalPersonName;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    public String[] getEmployeeExternalPersons() {
        Connection conn = getConnection();
        ArrayList<String> externalPersons = new ArrayList<>();
        String query = "SELECT ExternalPersonName FROM ExternalPerson WHERE ExternalPersonRole = 'Employee'";
        Statement st;
        ResultSet rs;
        try {
            st = conn.createStatement();
            rs = st.executeQuery(query);
            String externalPersonName;
            while(rs.next()){
                externalPersonName = new String(rs.getString("ExternalPersonName"));
                externalPersons.add(externalPersonName);
            }
            System.out.println("got the external persons!");
        } catch (Exception e) {
            e.printStackTrace();
        }

        String[] strExternalPersons = new String[externalPersons.size()];
        for(int i = 0; i < externalPersons.size(); i++){
            strExternalPersons[i] = externalPersons.get(i);
        }

        return strExternalPersons;

    }
}
