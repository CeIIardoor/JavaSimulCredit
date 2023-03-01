package ma.lyCreditSimul.Metier;

import ma.lyCreditSimul.Modele.Credit;

public interface ICreditMetier {
    public Credit calculerMensualite(Long idCredit) throws Exception;
}