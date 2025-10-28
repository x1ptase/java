package Models.DAO.Services;

import Models.DAO.DAOFactory;
import Models.DAO.IUserDAO;
import Models.DTO.User;
import java.util.List;

public class UserService implements IService<User>{
    IUserDAO userDAO;

    public UserService(){
        this.userDAO=new DAOFactory().userDAO();
    }

    @Override
    public User checkAccount(String userName, String password) throws Exception {
        return userDAO.login(userName, password);
    }

    @Override
    public User getUserByUserName(String userName) throws Exception {
        return userDAO.getUserByUserName(userName);
    }

    @Override
    public List<User> getUsersByKeywordOfLastName(String value) throws Exception {
        return userDAO.searchUserByLastName(value);
    }
    
    @Override
    public boolean addNewUser(User user) throws Exception {
        return userDAO.addUser(user);
    }

    @Override
    public boolean removeUser(String userName) throws Exception {
        return userDAO.deleteUser(userName);
    }

    @Override
    public boolean updateUser(User user) throws Exception {
        return userDAO.updateUser(user);
    }
}