package ly.simulateurcredit;

import ly.simulateurcredit.Metier.ICreditMetier;
import ly.simulateurcredit.Modele.Credit;
import ly.simulateurcredit.DAO.IDAO;

import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Properties;

public class SimulateurDeCredit {
    public static void main(String[] args) {
        String daoClassName = null, metierClassName = null, controleurClassName = null;
        Properties properties = new Properties();
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream("config.properties");

        if (propertiesFile != null) {
            try {
                properties.load(propertiesFile);
                daoClassName = properties.getProperty("DAO");
                metierClassName = properties.getProperty("METIER");
                controleurClassName = properties.getProperty("CONTROLEUR");
                propertiesFile.close();
            } catch (Exception e) {
                System.out.println("Erreur de chargement du fichier de configuration");
                e.printStackTrace();
            }

            try {
                Class<?> daoClass = Class.forName(daoClassName);
                Class<?> metierClass = Class.forName(metierClassName);
                Class<?> controleurClass = Class.forName(controleurClassName);

                IDAO<Credit,Long> dao = (IDAO<Credit, Long>) daoClass.getDeclaredConstructor().newInstance();
                ICreditMetier metier = (ICreditMetier) metierClass.getDeclaredConstructor(daoClass).newInstance(dao);
                ICreditMetier creditControleur = (ICreditMetier) controleurClass.getDeclaredConstructor(metierClass).newInstance(metier);

                Method setDao = metierClass.getMethod("setCreditDao", IDAO.class);
                setDao.invoke(metier, dao);

                Method setMetier = controleurClass.getMethod("setCreditMetier", ICreditMetier.class);
                setMetier.invoke(creditControleur, metier);

                System.out.println("dao = " + dao.toString());
                System.out.println("metier = " + metier);
                System.out.println("creditControleur = " + creditControleur);

                creditControleur.afficherCredit(1L);


            } catch (ClassNotFoundException | InvocationTargetException | InstantiationException |
                     IllegalAccessException | NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        } else {
            System.out.println("Fichier de configuration introuvable");
        }




    }
}