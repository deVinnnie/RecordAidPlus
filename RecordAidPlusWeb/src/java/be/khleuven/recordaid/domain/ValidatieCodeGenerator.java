package be.khleuven.recordaid.domain;

import java.math.BigInteger;
import java.security.SecureRandom;


/**
 * Klasse om een random unieke code te genereren die gebruikt wordt om het
 * emailadres van een gebruiker te valideren. De gebruiker krijgt na registratie
 * ene email met de gegenereerde validatiecode en zal deze moeten ingeven om zo
 * te tonen dat het emailadres van zich is.
 *
 * @author Hannes
 */
public final class ValidatieCodeGenerator
{
    /**
     * Methode om een unieke validatiecode te bekomen.
     *
     * @return Een String met een unieke validatiecode.
     */
    public static String generateValidatieCode()
    {
        SecureRandom random = new SecureRandom();
        return new BigInteger(130, random).toString(32);
    }
}