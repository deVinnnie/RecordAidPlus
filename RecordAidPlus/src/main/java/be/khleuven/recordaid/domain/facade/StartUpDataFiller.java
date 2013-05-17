package be.khleuven.recordaid.domain.facade;

import be.khleuven.recordaid.domain.departement.*;
import be.khleuven.recordaid.domain.gebruiker.*;
import be.khleuven.recordaid.database.DatabaseException;
import be.khleuven.recordaid.domain.aanvragen.*;
import be.khleuven.recordaid.domain.gebruiker.Dossier;
import be.khleuven.recordaid.opnames.OpnameMethode;
import be.khleuven.recordaid.domain.items.*;
import be.khleuven.recordaid.domain.mailing.*;
import be.khleuven.recordaid.opnames.OpnameMoment;
import be.khleuven.recordaid.util.TimeSpan;
import java.util.*;
import java.util.logging.*;

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
        //<editor-fold defaultstate="collapsed" desc="Lectoren">
        try {
            String[] mails = {
                "severus.snape@khleuven.be",
                "filius.flitwick@khleuven.be",
                "minerva.mcgonagall@khleuven.be",
                "remus.lupin@khleuven.be"
            };

            for (String mail : mails) {
                Lector lector = new Lector(mail);
                this.facade.create(lector);
            }
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Dummy gebruiker">
        try {
            //BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(15);
            //String hash = passwordEncoder.encode("geheim"); 

            //Create a dummy user
            String hash = "$2a$15$Rf1AtBbVC9jz.XUU69e1gu7alwMrsGRH0t0GVTJltHb49DP6ZlbW.";
            Gebruiker gebruiker = new Gebruiker(Rollen.BEGELEIDER, "dummy@khleuven.be", "Dummy", "Dummy", hash);
            gebruiker.valideer();
            this.facade.create(gebruiker);
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Opnamemethodes">
        try {
            OpnameMethode opnameMethode = new OpnameMethode("Scherm", "Scherm");
            OpnameMethode opnameMethode2 = new OpnameMethode("Scherm/Bord en Lector", "Scherm/Bord en Lector");
            facade.create(opnameMethode);
            facade.create(opnameMethode2);
        } catch (Exception e) {
            System.out.println("**Exception Occured:");
            e.printStackTrace();
        }
        //</editor-fold>
        
        //<editor-fold defaultstate="collapsed" desc="Items">
        try {
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
        //</editor-fold>

        //<editor-fold defaultstate="collapsed" desc="Mailing">
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
        //</editor-fold>

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
            Lector lector = facade.getLector("lector@khleuven.be");
            aanvraag.addOpnameMoment(new OpnameMoment("Wiskunde", new Lokaal("304", departement), "3Tx2", timespan, lector));
            aanvraag = facade.create(aanvraag);
            dossier.addAanvraag(aanvraag);
            facade.edit(dossier);
            facade.edit(aanvraag);
        } catch (Exception ex) {
            Logger.getLogger(StartUpDataFiller.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            Calendar instance = Calendar.getInstance();
            Calendar start = (Calendar) instance.clone();
            start.set(2013, Calendar.MARCH, 12, 10, 0);
            Calendar end = (Calendar) start.clone();
            end.set(2013, Calendar.MARCH, 12, 12, 0);

            Gebruiker dummy = facade.getGebruiker("dummy@khleuven.be");
            Dossier dossier = facade.getDossier(dummy);
            MultiPeriodeAanvraag aanvraag = new MultiPeriodeAanvraag(dossier, facade.getDepartement("G&T"));
            aanvraag.setBegeleider(dummy);
            aanvraag.setPeriode(new TimeSpan(start, end));
            List<Lector> lectoren = new ArrayList<Lector>();
            lectoren.add(new Lector("lector2@khleuven.be"));
            aanvraag.setLectoren(lectoren);

            facade.addMultiPeriodeAanvraag(aanvraag, dummy);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}