package ly.simulateurcredit.App;

import ly.simulateurcredit.Controleur.ICreditControleur;
import ly.simulateurcredit.DAO.dbVolatile.CreditDAO;
import ly.simulateurcredit.Metier.ICreditMetier;
import ly.simulateurcredit.Modele.Credit;
import ly.simulateurcredit.DAO.IDAO;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.Properties;

public class SimulateurDeCreditV2 {
    public static void main(String[] args) {
        String daoClassName = null, metierClassName = null, controleurClassName = null;
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("ly/simulateurcredit/config.properties");

        if (propertiesFile == null) {
            System.out.println("Fichier de configuration introuvable");
        } else {
            try {
                properties.load(propertiesFile);
                daoClassName = properties.getProperty("DAO");
                metierClassName = properties.getProperty("METIER");
                controleurClassName = properties.getProperty("CONTROLEUR");
                propertiesFile.close();
            } catch (Exception e) {
                System.out.println("Erreur de chargement du fichier de configuration");
                e.printStackTrace();
            } finally {
                properties.clear();
            }

            try {
                Class<?> daoClass = Class.forName(daoClassName);
                Class<?> metierClass = Class.forName(metierClassName);
                Class<?> controleurClass = Class.forName(controleurClassName);

                IDAO<Credit,Long> dao = (CreditDAO) daoClass.getDeclaredConstructor().newInstance();
                ICreditMetier metier = (ICreditMetier) metierClass.getDeclaredConstructor(IDAO.class).newInstance(dao);
                ICreditControleur creditControleur = (ICreditControleur) controleurClass.getDeclaredConstructor(ICreditMetier.class).newInstance(metier);

                //                Dependency injection using setter
//                Method setDao = metierClass.getMethod("setCreditDao", IDAO.class);
//                setDao.invoke(metier, dao);

                Method setMetier = controleurClass.getMethod("setCreditMetier", ICreditMetier.class);
                setMetier.invoke(creditControleur, metier);

                creditControleur.afficherCredit(1L);
                creditControleur.afficherMensualite(1L);
                creditControleur.afficherMensualite(2L);
                creditControleur.afficherMensualite(3L);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}