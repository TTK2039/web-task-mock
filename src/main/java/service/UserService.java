package service;

import java.sql.Connection;

import dao.UserDao;
import entity.User;
import util.DbUtil;
public class UserService {
    
    public User login(User user) {
        try (Connection conn = DbUtil.getConnection()) {
        	UserDao userDao = new UserDao(conn);
            return userDao.login(user);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }
    
//    public User selectFromUser(String name) {
//        try (Connection conn = DbUtil.getConnection()) {
//        	UserDao userDao = new UserDao(conn);
//        	return userDao.selectFromName(name);
//        } catch (Exception e) {
//            e.printStackTrace();
//            return null;
//        }
//
//    }
}
