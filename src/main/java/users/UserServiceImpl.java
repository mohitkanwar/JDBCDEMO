package users;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserServiceImpl implements UserService {

    private String url = "jdbc:mysql://localhost:3306/demo";
    private String driverClass = "com.mysql.jdbc.Driver";
    private String username = "root";
    private String password = "root";

    public void create(User user) {
        PreparedStatement statement = null;
        try {
            statement = getConnection ()
                    .prepareStatement("INSERT INTO USERS (USERNAME,FIRSTNAME,LASTNAME,DOJ) VALUES (?,?,?,?)");
            statement.setString(1, user.getUsername());
            statement.setString(2, user.getFirstName());
            statement.setString(3, user.getLastName());
            statement.setDate(4, new Date(user.getDateOfJoining().toEpochDay()));
            statement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public List<User> getAllUsers() {
        List<User> users = new ArrayList<>();
        try {
            PreparedStatement fetchStatement = getConnection ()
                    .prepareStatement("SELECT USERNAME,FIRSTNAME,LASTNAME,DOJ FROM USERS ");
            fetchStatement.execute();
            ResultSet rs = fetchStatement.getResultSet();

            while (rs.next()) {

                User user = new User();
                user.setUsername(rs.getString(1));
                user.setFirstName(rs.getString(2));
                user.setLastName(rs.getString(3));
                user.setDateOfJoining(rs.getDate(4).toLocalDate());
                users.add(user);
            }
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return users;
    }

    private Connection getConnection (){
        Connection connection = null;
        try{
            Class.forName(driverClass).newInstance();
            connection = DriverManager.getConnection(url,username,password);

        }catch (ClassNotFoundException e){
            System.out.println("db driver class ["+driverClass+"] not found");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (InstantiationException|IllegalAccessException e){
            System.out.println("db driver class ["+driverClass+"] not instantiable");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        catch (SQLException e){
            System.out.println("unable to obtain connection");
            e.printStackTrace();
            throw new RuntimeException(e);
        }
        return connection;
    }
}
