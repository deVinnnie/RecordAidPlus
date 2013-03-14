package be.khleuven.eindwerk.domain.exception;

/**
 * Een klasse die overerft van de klasse "Exception", deze klasse kan nu
 * gebruikt worden om een eigen soort exception te gooien wanneer nodig.
 *
 * @author Maxime Van den Kerkhof
 */
public class DomainException extends Exception
{
    /**
     * Constructor om een nieuwe DomainException aan te maken zonder parameters.
     */
    public DomainException()
    {
        super();
    }


    /**
     * Constructor om een nieuwe DomainException aan te maken.
     *
     * @param message String die de reden omschrijft waarom de exception werd
     * gegooid.
     */
    public DomainException(String message)
    {
        super(message);
    }


    /**
     * Constructor om een nieuwe DomainException aan te maken.
     *
     * @param message String die de reden omschrijft waarom de exception werd
     * gegooid.
     * @param exception Een andere exception die vaak de reden was waarom deze
     * exception werd gegooid en die meegegeven kan worden aan de exception.
     */
    public DomainException(String message, Exception exception)
    {
        super(message, exception);
    }
}


