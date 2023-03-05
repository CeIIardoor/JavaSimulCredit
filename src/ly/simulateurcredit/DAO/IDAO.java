package ly.simulateurcredit.DAO;

public interface IDAO<T,ID> {
    T trouverParId(ID id);
}