package ma.lyCreditSimul.DAO.dbVolatile;


import ma.lyCreditSimul.DAO.IDAO;
import ma.lyCreditSimul.Modele.Credit;
import org.w3c.dom.ls.LSOutput;

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