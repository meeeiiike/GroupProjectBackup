package ie.atu.GroupProjectBackup;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class TestConnection {
    public static void main(String[] args){

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
            System.out.println("Connected");
        } catch(SQLException e){
            e.printStackTrace();
            System.out.println("Could not Connect");
        }

    }
}
