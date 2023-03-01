package ma.lyCreditSimul.DAO;

public interface IDAO<T,ID> {
    T trouverParId(ID id);
}