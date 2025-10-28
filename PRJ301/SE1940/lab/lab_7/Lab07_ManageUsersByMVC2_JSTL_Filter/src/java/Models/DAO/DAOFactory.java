package Models.DAO;

public class DAOFactory implements IDAOFactory {
    public DAOFactory(){
    }

    @Override
    public  IUserDAO userDAO(){
        return new UserDAO();
    }
}
