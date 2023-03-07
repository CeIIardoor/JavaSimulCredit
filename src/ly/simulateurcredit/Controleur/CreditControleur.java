package ly.simulateurcredit.Controleur;

import lombok.Data;
import ly.simulateurcredit.Metier.ICreditMetier;

@Data
public class CreditControleur implements ICreditControleur {

    ICreditMetier creditMetier;

    public CreditControleur(ICreditMetier creditMetier) {
        this.creditMetier = creditMetier;
    }

    @Override
    public void afficherMensualite(Long idCredit) {
        System.out.println(creditMetier.calculerMensualite(idCredit).getMensualite());

    }

    @Override
    public void afficherCredit(Long l) {
        creditMetier.afficherCredit(l);
    }
}