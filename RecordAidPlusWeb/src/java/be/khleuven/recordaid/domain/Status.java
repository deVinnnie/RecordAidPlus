package be.khleuven.recordaid.domain;

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
    GOEDGEKEURD_DOOR_KERN,
    GOEDGEKEURD_VOOR_OPNAME,
    OPGENOMEN,
    BESCHIKBAAR,
    AFGEKEURD;
}