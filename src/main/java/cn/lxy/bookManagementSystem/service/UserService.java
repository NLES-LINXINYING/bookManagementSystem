package cn.lxy.bookManagementSystem.service;

import cn.lxy.bookManagementSystem.dao.UserDAO;
import cn.lxy.bookManagementSystem.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    UserDAO userDAO;

    public User getUser(String email) {
        try {
            return userDAO.selectUserByEmail(email);
        } catch (Exception e) {
//            logger.error(e);
            return null;
        }
    }

    public User getUser(int id) {
        try {
            return userDAO.selectUserById(id);
        } catch (Exception e) {
//            logger.error(e);
            return null;
        }
    }

    public int addUser(User user) {
        try {
            return userDAO.addUser(user);
        } catch (Exception e) {
//            logger.error(e);
            return 0;
        }
    }
}
