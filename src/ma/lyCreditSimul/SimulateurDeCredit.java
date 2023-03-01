package ma.lyCreditSimul;

import ma.lyCreditSimul.DAO.IDAO;
import ma.lyCreditSimul.Metier.CreditMetier;
import ma.lyCreditSimul.Metier.ICreditMetier;
import ma.lyCreditSimul.Modele.Credit;
import ma.lyCreditSimul.Presentation.CreditControleur;
import ma.lyCreditSimul.Presentation.ICreditControleur;

import java.io.InputStream;
import java.util.Properties;

public class SimulateurDeCredit {
    public static void main(String[] args) {
        Properties properties = new Properties();

        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        try{
            InputStream config = classLoader.getResourceAsStream("ma/lyCreditSimul/config.properties");
            if (config == null) {
                System.out.println("Fichier de configuration introuvable");
                return;
            } else {
                properties.load(config);
                System.out.println("Fichier de configuration chargé");
            }
            String daoClassName = properties.getProperty("daoClass");
            String metierClassName = properties.getProperty("metierClass");
            String controleurClassName = properties.getProperty("controleurClass");

            Class<?> daoClass = Class.forName(daoClassName);
            Class<?> metierClass = Class.forName(metierClassName);
            Class<?> controleurClass = Class.forName(controleurClassName);

            IDAO<Credit,Long> creditDao = (IDAO<Credit, Long>) daoClass.getConstructor().newInstance();
            ICreditMetier creditMetier = (ICreditMetier) metierClass.getConstructor().newInstance();
            ICreditControleur creditControlleur = (ICreditControleur) controleurClass.getConstructor().newInstance();

            ((CreditMetier) creditMetier).setCreditDao(creditDao);
            ((CreditControleur) creditControlleur).setCreditMetier(creditMetier);
            try {
                creditControlleur.afficherMensualite(1L);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } catch (Exception e) {
            System.out.println("Classe introuvable");
            e.printStackTrace();
        } finally {
            properties.clear();
        }


    }
}