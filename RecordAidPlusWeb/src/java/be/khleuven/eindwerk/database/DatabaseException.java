package be.khleuven.eindwerk.database;

public class DatabaseException extends Exception
{
    /**
     * Constructor zonder argumenten.
     */
    public DatabaseException()
    {
        super();
    }


    /**
     * Constructor waar een Throwable object aan meegegeven kan worden.
     *
     * @param throwable Het object dat meegegeven wordt.
     */
    public DatabaseException(Throwable throwable)
    {
        super(throwable);
    }


    /**
     * Constructor waar een String aan meegegeven kan worden, dit is een
     * boodschap over wat er mis ging.
     *
     * @param message De boodschap over wat er mis ging.
     */
    public DatabaseException(String message)
    {
        super(message);
    }


    /**
     * Constructor waar een Throwable object aan meegegeven kan worden en waar
     * een String aan meegegeven kan worden, dit is een boodschap over wat er
     * mis ging.
     *
     * @param throwable Het object dat meegegeven wordt.
     * @param message De boodschap over wat er mis ging.
     */
    public DatabaseException(Throwable throwable, String message)
    {
        super(message, throwable);
    }
}


