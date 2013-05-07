package be.khleuven.recordaid.domain.aanvragen;

/**
 * Enum die de statussen beschrijft die een aanvraag kan hebben. De status van
 * een aanvraag beschrijft in welk stadium een aanvraag zich bevindt en wat er
 * voor die aanvraag kan gebeuren.
 *
 * @author Koen
 */
public enum Status
{
    NIEUW,
    GOEDGEKEURD, 
    AFGEHANDELD,
    AFGEKEURD;
}