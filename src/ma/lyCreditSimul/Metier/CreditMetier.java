package ma.lyCreditSimul.Metier;

import lombok.Data;
import ma.lyCreditSimul.DAO.IDAO;
import ma.lyCreditSimul.Modele.Credit;
@Data
public class CreditMetier implements ICreditMetier {

    IDAO<Credit,Long> creditDao;
    @Override
    public Credit calculerMensualite(Long idCredit) throws Exception {
        System.out.println("CreditMetier.calculerMensualite()");
        Credit credit = creditDao.trouverParId(idCredit);
        if (credit != null) {
            double mensualite = credit.getCapitale_emprunte() * (credit.getTaux_interet() / 1200)
                    / (1 - Math.pow(1 + credit.getTaux_interet() / 100, -1 * credit.getNbr_mois()));
            credit.setMensualite(Math.round(mensualite * 100.0) / 100.0);
        } else {
            throw new Exception("Credit introuvable");
        }
        return credit;
    }
}