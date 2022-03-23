package ru.stqa.pft.addressbook.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupDate;
import ru.stqa.pft.addressbook.model.Groups;

import java.sql.*;

public class DBConnectionTest {
    @Test
    public void testDBConnection(){
        Connection conn = null;

        try {
            conn = DriverManager.getConnection("jdbc:mysql://localhost/addressbook?" +
                            "user=root&password=");
            Statement st = conn.createStatement();
            ResultSet rs =  st.executeQuery("select group_id, group_name, group_header, group_footer from group_list");
            Groups groups = new Groups();
            while (rs.next()){
               groups.add(new GroupDate().withId(rs.getInt("group_id")).withName(rs.getNString("group_name"))
                        .withHeader(rs.getNString("group_header")).withFooter(rs.getNString("group_footer")));
            }

            // Do something with the Connection
            rs.close();
            st.close();
            conn.close();
            System.out.println(groups);


        } catch (SQLException ex) {
            // handle any errors
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
    }
}
