package ly.simulateurcredit.Metier;

import lombok.Data;
import ly.simulateurcredit.Modele.Credit;
import ly.simulateurcredit.DAO.IDAO;

@Data
public class CreditMetier implements ICreditMetier {

    IDAO<Credit,Long> creditDao;

    public CreditMetier(IDAO<Credit,Long> creditDao) {
        this.creditDao = creditDao;
    }

    @Override
    public Credit calculerMensualite(Long idCredit) {
        Credit credit = creditDao.trouverParId(idCredit);
        if (credit != null) {
            double mensualite = credit.getCapitale_emprunte() * (credit.getTaux_interet() / 1200)
                    / (1 - Math.pow(1 + credit.getTaux_interet() / 100, -1 * credit.getNbr_mois()));
            credit.setMensualite(Math.round(mensualite * 100.0) / 100.0);
        } else {
            System.out.println("Credit introuvable");
        }
        return credit;
    }

    @Override
    public void afficherCredit(Long idCredit) {
        System.out.println(creditDao.trouverParId(idCredit));
    }
}