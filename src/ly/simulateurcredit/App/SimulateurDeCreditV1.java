package ly.simulateurcredit.App;

import ly.simulateurcredit.Controleur.CreditControleur;
import ly.simulateurcredit.DAO.IDAO;
import ly.simulateurcredit.DAO.dbVolatile.CreditDAO;
import ly.simulateurcredit.Metier.CreditMetier;
import ly.simulateurcredit.Metier.ICreditMetier;
import ly.simulateurcredit.Modele.Credit;

public class SimulateurDeCreditV1 {
        public static void main(String[] args) {
            IDAO<Credit, Long> creditDao = new CreditDAO();
            ICreditMetier creditMetier = new CreditMetier(creditDao);
            CreditControleur creditControleur = new CreditControleur(creditMetier);
            creditControleur.afficherCredit(1L);
            creditControleur.afficherMensualite(1L);
        }
}