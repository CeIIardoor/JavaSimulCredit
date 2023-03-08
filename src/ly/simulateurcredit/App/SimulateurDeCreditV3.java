package ly.simulateurcredit.App;

import ly.simulateurcredit.Controleur.ICreditControleur;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimulateurDeCreditV3 {

    static ICreditControleur creditControleur;

    public static void main(String[] args) {
        ApplicationContext context = new ClassPathXmlApplicationContext("ly/simulateurcredit/spring-ioc.xml");
        creditControleur = (ICreditControleur) context.getBean("controleur");
        creditControleur.afficherMensualite(1L);
    }

}