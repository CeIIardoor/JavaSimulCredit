package ly.simulateurcredit.DAO.dbVolatile;


import ly.simulateurcredit.Modele.Credit;
import ly.simulateurcredit.DAO.IDAO;

import java.util.Set;


public class CreditDAO implements IDAO<Credit,Long> {

    Set<Credit> credits = null;
    public CreditDAO() {
        credits = DB_Credits();
    }
    @Override
    public Credit trouverParId(Long id) {
        return DB_Credits().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    public static Set<Credit> DB_Credits() {
        return Set.of(
                new Credit(1L, 300_000D, 12, 2.5, "Ahmed", 0.0),
                new Credit(2L, 300_000D, 24, 2.5, "Bourkadi", 0.0),
                new Credit(3L, 300_000D, 36, 2.5, "Cascade", 0.0)
                );
    }
}