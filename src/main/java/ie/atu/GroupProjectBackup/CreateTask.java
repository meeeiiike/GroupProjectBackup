package ie.atu.GroupProjectBackup;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Properties;

public class CreateTask {
    public static void main(String[] args) throws SQLException {

        Properties dbProps = new Properties();
        try(InputStream input = TestConnection.class.getResourceAsStream("/db.properties")){
            if(input== null){
                System.out.println("Unable to find db.properties");
                return;
            }
            dbProps.load(input);
        } catch(IOException e){
            e.printStackTrace();
            return;
        }
        String url = dbProps.getProperty("db.url");
        String username = dbProps.getProperty("db.username");
        String password = dbProps.getProperty("db.password");

        try(Connection con = DriverManager.getConnection(url, username, password)){

            PreparedStatement stmt = con.prepareStatement("INSERT INTO dept(name) VALUES (?)");
            stmt.setString(1, "ATU");
            stmt.executeUpdate();

        } catch(SQLException e){
            System.out.println("Insert Failed");
            e.printStackTrace();
        }
    }
}
