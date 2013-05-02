package be.khleuven.recordaid.domain.facade;

import be.khleuven.recordaid.domain.gebruiker.*; 
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.*;
import be.khleuven.recordaid.domain.aanvragen.DagAanvraag;
import be.khleuven.recordaid.domain.aanvragen.MultiPeriodeAanvraag;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.opnames.OpnameMethode;
import be.khleuven.recordaid.domain.items.*;
import be.khleuven.recordaid.domain.mailing.*; 
import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.util.TimeSpan;
import java.util.Calendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
                this.facade.create(lector);
            }
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }

        try {
            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);
            //String wachtwoord = passwordEncoder.encode("geheim"); 
            //System.out.println(wachtwoord); 

            //Create Admin user
            String wachtwoord = "$2a$15$ThkOeJ8D.jfblYskRaxx4uOtvTUPfeY9M2r8v5VmOqZmk7TUyJe5m";
            Gebruiker g = new Gebruiker(Rollen.ADMIN, "recordaid@khleuven.be", "RecordAid", "Admin", wachtwoord);
            g.valideer();
            this.facade.create(g);

            //Create a dummy user
            String wachtwoord2 = "$2a$15$Rf1AtBbVC9jz.XUU69e1gu7alwMrsGRH0t0GVTJltHb49DP6ZlbW.";
            Gebruiker g2 = new Gebruiker(Rollen.STUDENT, "dummy@khleuven.be", "Dummy", "Dummy", wachtwoord2);
            g2.valideer();
            this.facade.create(g2);
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

        try {
            Dossier dossier = facade.getDossier(facade.getGebruiker("dummy@khleuven.be"));
            DagAanvraag aanvraag = new DagAanvraag(dossier, facade.getDepartement("G&T"));
            Calendar instance = Calendar.getInstance();
            instance.set(2013, Calendar.MARCH, 12, 0, 0);
            aanvraag.setLesDatum(instance);

            Calendar start = (Calendar) instance.clone();
            start.set(2013, Calendar.MARCH, 12, 10, 0);

            Calendar end = (Calendar) start.clone();
            end.set(2013, Calendar.MARCH, 12, 12, 0);

            TimeSpan timespan = new TimeSpan(start, end);
            Departement departement = facade.getDepartement("G&T"); 
            OpnameMethode methode = facade.getOpnameMethodes().get(0); 
            Lector lector = facade.getLector("lector@khleuven.be"); 
            aanvraag.addOpnameMoment(new OpnameMoment("Wiskunde", new Lokaal("304",departement), "3Tx2", timespan,lector,methode));
            aanvraag = facade.create(aanvraag); 
            dossier.addAanvraag(aanvraag);
            facade.edit(dossier); 
            facade.edit(aanvraag); 
        } catch (DomainException ex) {
            Logger.getLogger(StartUpDataFiller.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(Exception e){
            e.printStackTrace();
        }
        
        try {
            Calendar instance = Calendar.getInstance();
            Calendar start = (Calendar) instance.clone();
            start.set(2013, Calendar.MARCH, 12, 10, 0);
            Calendar end = (Calendar) start.clone();
            end.set(2013, Calendar.MARCH, 12, 12, 0);
           
            Dossier dossier = facade.getDossier(facade.getGebruiker("dummy@khleuven.be"));
            MultiPeriodeAanvraag aanvraag = new MultiPeriodeAanvraag(dossier, facade.getDepartement("G&T"));
                    
            aanvraag.setPeriode(new TimeSpan(start, end));

            facade.addAanvraag(aanvraag); 
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}