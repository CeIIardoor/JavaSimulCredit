package ly.simulateurcredit.DAO.dbVolatile;


import ly.simulateurcredit.Modele.Credit;
import ly.simulateurcredit.DAO.IDAO;

import java.util.Set;

public class CreditDAO implements IDAO<Credit,Long> {
    @Override
    public Credit trouverParId(Long id) {
        return DB_Credits().stream()
                .filter(c -> c.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    Set<Credit> DB_Credits() {
        return Set.of(
                new Credit(1L, 300_000D, 12, 2.5, "M. Ahmed", 25_000.0),
                new Credit(2L, 300_000D, 24, 2.5, "M. Baydidi", 12_500.0),
                new Credit(3L, 300_000D, 36, 2.5, "M. Cascade", 8_333.33)
                );
    }
}