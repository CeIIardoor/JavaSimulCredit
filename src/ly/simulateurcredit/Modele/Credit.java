package ly.simulateurcredit.Modele;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @AllArgsConstructor @NoArgsConstructor
public class Credit {
    private Long id;
    private Double capitale_emprunte;
    private Integer nbr_mois;
    private Double taux_interet;
    private String demandeur;
    private Double mensualite;
}