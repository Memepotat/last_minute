package loginsystem.logreg;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
public class database {

    public static boolean Login(String N, String P)
    {
        String serverName = "DESKTOP-FNO17UP:1433"; //Include port >>serverName:port
        String databaseName = "LoginSys";
        String databaseUser = "Admin";
        String databasePassword = "Admin";
        String connectionUrl = "jdbc:sqlserver://" + serverName
                + ";database=" + databaseName
                + ";user=" + databaseUser
                + ";password=" + databasePassword
                + ";encrypt=false;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";


        Connection con;

        try {
            con = java.sql.DriverManager.getConnection(connectionUrl);

            if (con != null) {
                System.out.println("Connection Successful!");
                ResultSet resultSet = null;

                Statement statement = con.createStatement();

                String selectSql = "SELECT count(1) FROM UserAccounts WHERE Username = '" + N +"' AND Password = '" + P + "'";

                resultSet = statement.executeQuery(selectSql);

                while(resultSet.next()) {
                    if(resultSet.getInt(1) == 1)
                    {
                        return  true;
                        //TODO ACCOUNT FOUND
                    } else {
                        return false;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Error Trace in getConnection() : " + e.getMessage());

            return false;
        }
        return false;
    }

    public static boolean Register(String N, String P)
    {
        String serverName = "DESKTOP-FNO17UP:1433"; //Include port >>serverName:port
        String databaseName = "LoginSys";
        String databaseUser = "Admin";
        String databasePassword = "Admin";
        String connectionUrl = "jdbc:sqlserver://" + serverName
                + ";database=" + databaseName
                + ";user=" + databaseUser
                + ";password=" + databasePassword
                + ";encrypt=false;"
                + "trustServerCertificate=true;"
                + "loginTimeout=30;";


        Connection con;
        try {

            con = java.sql.DriverManager.getConnection(connectionUrl);

            if (con != null) {

                System.out.println("Connection Successful!");

                ResultSet resultSet = null;

                Statement statement = con.createStatement();

                String selectSql = "SELECT count(1) FROM UserAccounts WHERE Username = '" + N +"'";

                resultSet = statement.executeQuery(selectSql);

                while(resultSet.next()) {
                    if(resultSet.getInt(1) == 1)
                    {
                        System.out.println("ACCOUNT WITH THE SAME USERNAME ALREADY EXISTS");

                        return  true;

                        //TODO ACCOUNT FOUND
                    } else {
                        selectSql ="INSERT INTO [dbo].[UserAccounts] VALUES('"+ N + "' , '" + P + "')";

                        Statement statement1 = con.createStatement();

                        con.createStatement().execute(selectSql);

                        return false;
                    }
                }
            }

        } catch (Exception e) {
            e.printStackTrace();

            System.out.println("Error Trace in getConnection() : " + e.getMessage());

            return false;
        }
        return false;
    }

}
