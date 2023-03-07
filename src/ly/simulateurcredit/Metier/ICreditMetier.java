package ly.simulateurcredit.Metier;

import ly.simulateurcredit.Modele.Credit;

public interface ICreditMetier {
    public Credit calculerMensualite(Long idCredit);

    void afficherCredit(Long l);
}