package ly.simulateurcredit.DAO.DBFactories;

import ly.simulateurcredit.DAO.DAOFactory;
import ly.simulateurcredit.DAO.IDAO;

import static java.lang.System.exit;

public class CSVFactory extends DAOFactory implements IDAO {
    public CSVFactory() {
        System.out.println("CSVFactory not implemented yet!");
        exit(1);
    }

    @Override
    public Object trouverParId(Object o) {
        return null;
    }
}