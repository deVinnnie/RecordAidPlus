package be.khleuven.recordaid.domain.facade;

import be.khleuven.recordaid.domain.gebruiker.Gebruiker;
import be.khleuven.recordaid.domain.gebruiker.Rollen;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.domain.aanvragen.OpnameMethode;
import be.khleuven.recordaid.domain.items.*;
import be.khleuven.recordaid.domain.mailing.MailMessage;
import be.khleuven.recordaid.domain.mailing.MailMessageFactory;
import be.khleuven.recordaid.domain.mailing.SubjectPrefix;
import be.khleuven.recordaid.util.TimeSpan;
import java.util.Calendar;
import java.util.List;

/**
 *
 * @author Vincent Ceulemans
 */
public class StartUpDataFiller {

    private RecordAidDomainFacade facade;

    public StartUpDataFiller(RecordAidDomainFacade facade) {
        this.facade = facade;
    }

    public void init() throws DatabaseException {
        try {
            //Departementen
            facade.addDepartement(new Departement("G&T", true));
            facade.addDepartement(new Departement("SSH", false));
            facade.addDepartement(new Departement("DLO Diest", false));
            facade.addDepartement(new Departement("Naamse steenweg", false));
            facade.addDepartement(new Departement("Hertogstraat", false));

            //Lectoren mails
            String[] mails = {
                "luc.janssens@khleuven.be",
                "elke.steegmans@khleuven.be",
                "mieke.kemme@khleuven.be",
                "jan.van.hee@khleuven.be"
            };

            for (String mail : mails) {
                Lector lector = new Lector(mail);
                this.facade.addLector(lector);
            }
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }

        try {
            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);
            //String wachtwoord = passwordEncoder.encode("geheim"); 
            //System.out.println(wachtwoord); 
            String wachtwoord = "$2a$15$ThkOeJ8D.jfblYskRaxx4uOtvTUPfeY9M2r8v5VmOqZmk7TUyJe5m";
            //Create Admin user
            Gebruiker g = new Gebruiker(Rollen.ADMIN, "recordaid@khleuven.be", "RecordAid", "Admin", wachtwoord);
            g.valideer();
            this.facade.addGebruiker(g);

            //Create a dummy user
            Gebruiker g2 = new Gebruiker(Rollen.STUDENT, "dummy", "Dummy", "Dummy", wachtwoord);
            g2.valideer();
            this.facade.addGebruiker(g2);
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }

        try {
            //Opnamemethodes
            OpnameMethode opnameMethode = new OpnameMethode("Scherm");
            OpnameMethode opnameMethode2 = new OpnameMethode("Scherm/Bord en Lector");
            facade.create(opnameMethode);
            facade.create(opnameMethode2);
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }
        
        try {
            //Items
            Item item1 = new Item("Item1");
            Item item2 = new Item("Item2");

            Calendar calendar = Calendar.getInstance();
            calendar.set(2013, 3, 23, 11, 0, 0);

            Calendar calendar2 = Calendar.getInstance();
            calendar2.set(2013, 3, 23, 14, 0, 0);

            Reservatie reservatie = new Reservatie(new TimeSpan(calendar, calendar2), facade.getGebruiker("recordaid@khleuven.be"));

            item1.addReservatie(reservatie);

            facade.create(item1);
            facade.create(item2);
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }
        
        try {
            SubjectPrefix prefix = new SubjectPrefix(); 
            prefix.setId(1);
            facade.create(prefix);
            prefix = facade.getSubjectPrefix();
            
            MailMessageFactory factory = new MailMessageFactory();
            List<MailMessage> messages = factory.createMailMessages();

            for (MailMessage message : messages) {
                message.setSubjectPrefix(prefix);
                facade.create(message);
            }
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }
    }
}