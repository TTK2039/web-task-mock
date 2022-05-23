package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import entity.User;

public class UserDao {

    private static final String SQL_SELECT_ID_PASS= "SELECT * FROM users where login_id = ? AND password = ?";
//    private static final String SQL_SELECT_FROM_NAME= "SELECT * FROM users where user_name = ?";
//    private static final String SQL_SERCH = "select count(*) count from users where user_name = ?";
    
    private Connection connection;

    public UserDao(Connection connection) {
        this.connection = connection;
    }

    public User login(User user) {
    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_ID_PASS)) {
        	stmt.setString(1, user.getLoginId());
            stmt.setString(2, user.getPassword());
            ResultSet rs =  stmt.executeQuery();
            User a = null;
            while (rs.next()) {
//            a = new User(rs);
            a = new User(rs.getInt("id"), rs.getString("login_id"), rs.getString("password"), rs.getString("name"),rs.getInt("role"), rs.getInt("created_at"), rs.getInt("updated_at"));
            }
            return a;
    	} catch (SQLException e) {
    		e.printStackTrace();
    		return null;
    	}	
    }
    
//    public User selectFromName(String name) {
//    	try (PreparedStatement stmt = connection.prepareStatement(SQL_SELECT_FROM_NAME)) {
//        	//int idInt = Integer.parseInt(pass);
//        	stmt.setString(1, name);
//            ResultSet rs = stmt.executeQuery();
//            User a = null;
//            while (rs.next()) {
//            	a =  new User(rs.getInt("user_id"), rs.getString("user_name"), rs.getString("password"));
//            	
//            }
//        	return a;
//    	} catch (SQLException e) {
//        throw new RuntimeException(e);
//    	}
//    }
    
}