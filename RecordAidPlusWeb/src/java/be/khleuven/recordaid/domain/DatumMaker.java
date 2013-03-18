package be.khleuven.recordaid.domain;

import java.util.Calendar;
import java.util.GregorianCalendar;


/**
 * Kleine klasse om een datum voorgesteld door een String om te zetten naar een
 * datum van het type Calender.
 *
 * @author Hannes
 */
public class DatumMaker
{
    /**
     * Methode die een String uit een jsp formulier parameter omzet in een
     * object van het type Calender.
     *
     * @param datumStr String waarin een datum wordt voorgesteld in de vorm van
     * "DD/MM/YYYY".
     * @return Een object van het type Calender die dezelfde datum als de String
     * voorstelt.
     */
    public static Calendar maakDatum(String datumStr)
    {
        String[] datumSplit = datumStr.split("/");
        int dag = Integer.parseInt(datumSplit[0]);
        int maand = Integer.parseInt(datumSplit[1]);
        int jaar = Integer.parseInt(datumSplit[2]);
        maand--;
        return new GregorianCalendar(jaar, maand, dag);
    }
}


