package ma.lyCreditSimul.Presentation;

import lombok.Data;
import ma.lyCreditSimul.Metier.ICreditMetier;
@Data
public class CreditControleur implements ICreditControleur {

    ICreditMetier creditMetier;

    @Override
    public void afficherMensualite(Long idCredit) throws Exception {
        System.out.println("CreditControleur.afficherMensualite()");
        System.out.println(creditMetier.calculerMensualite(idCredit).getMensualite());

    }
}