package ly.simulateurcredit.DAO;

import ly.simulateurcredit.DAO.DBFactories.CSVFactory;
import ly.simulateurcredit.DAO.DBFactories.MySQLFactory;
import ly.simulateurcredit.DAO.DBFactories.SQLServerFactory;

import java.sql.SQLException;

public class DAOFactory {

    public static MySQLFactory getDAOFactory(String whichFactory, String url, String user, String password) {
        switch (whichFactory) {
            case "MYSQL":
                try {
                    return MySQLFactory.getInstance(url, user, password);
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            case "CSV":
                return new CSVFactory();
            case "SQLSERVER":
                return new SQLServerFactory();
            default:
                return null;
        }
    }

}