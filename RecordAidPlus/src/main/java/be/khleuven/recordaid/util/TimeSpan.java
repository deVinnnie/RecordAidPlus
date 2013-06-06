package be.khleuven.recordaid.util;

import be.khleuven.recordaid.domain.*; 
import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.*;

/**
 *
 * Encapsulates two Calendar objects to represent a period between two points in
 * time.
 *
 * @author Vincent Ceulemans
 */
@Entity
public class TimeSpan extends Identifiable implements Serializable {
    //@Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "ENDTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar endTime;
    
    //@Temporal(javax.persistence.TemporalType.DATE)
    @Column(name = "BEGINTIME")
    @Temporal(TemporalType.TIMESTAMP)
    private Calendar beginTime; 
    
    public TimeSpan() {}

    public TimeSpan(Calendar beginTime, Calendar endTime) throws DomainException {
        this.setEndTime(endTime);
        this.setBeginTime(beginTime);
    }

    //<editor-fold defaultstate="collapsed" desc="Getters & Setters">
    public void setBeginYear(int year) throws DomainException {
        if (year > this.getEndTime().get(Calendar.YEAR)) {
            throw new DomainException("Begintijdstip kan niet later zijn dan eindtijdstip.");
        }
        this.beginTime.set(Calendar.YEAR, year);
    }

    public void setBeginHour(int hour) throws DomainException {
        Calendar clone = (Calendar) this.beginTime.clone(); 
        clone.set(Calendar.HOUR, hour);
        
        if (!(this.getEndTime().get(Calendar.HOUR) ==0) && clone.after(this.getEndTime())) {
            throw new DomainException("Begintijdstip kan niet later zijn dan eindtijdstip.");
        }
        this.beginTime.set(Calendar.HOUR, hour);
    }

    public int getBeginHour() {
        return this.beginTime.get(Calendar.HOUR);
    }

    public void setBeginMinute(int minute) throws DomainException {
        Calendar clone = (Calendar) this.beginTime.clone(); 
        clone.set(Calendar.MINUTE, minute);
        if (!(this.endTime.get(Calendar.HOUR) == 0) && clone.after(this.getEndTime())){
            throw new DomainException("Begintijdstip kan niet later zijn dan eindtijdstip.");
        }
        this.beginTime.set(Calendar.MINUTE, minute);
    }

    public int getBeginMinute() {
        return this.beginTime.get(Calendar.MINUTE);
    }

    public void setEndYear(int year) throws DomainException {
        if (year < this.getBeginTime().get(Calendar.YEAR)) {
            throw new DomainException("Eindtijdstip kan niet vroeger zijn dan begintijdstip.");
        }
        this.endTime.set(Calendar.YEAR, year);
        
    }

    public void setEndHour(int hour) throws DomainException {
        Calendar clone = (Calendar) this.endTime.clone(); 
        clone.set(Calendar.HOUR, hour);
        if (clone.before(this.beginTime)) {
            throw new DomainException("Eindtijdstip kan niet vroeger zijn dan begintijdstip.");
        }
        this.endTime.set(Calendar.HOUR, hour);
    }

    public int getEndHour() {
        return this.endTime.get(Calendar.HOUR);
    }

    public void setEndMinute(int minute) throws DomainException {
        Calendar clone = (Calendar) this.endTime.clone(); 
        clone.set(Calendar.MINUTE, minute);
        if (clone.before(this.beginTime)) {
            throw new DomainException("Eindtijdstip kan niet vroeger zijn dan begintijdstip.");
        }
        this.endTime.set(Calendar.MINUTE, minute);
    }

    public int getEndMinute() {
        return this.endTime.get(Calendar.MINUTE);
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) throws DomainException {
        if (beginTime.after(this.endTime)) {
            throw new DomainException("Begintijdstip kan niet later zijn dan eindtijdstip.");
        }
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) throws DomainException {
        if (endTime.before(this.beginTime)) {
            throw new DomainException("Eindtijdstip kan niet vroeger zijn dan begintijdstip.");
        }
        this.endTime = endTime;
    }
    //</editor-fold>

    /**
     * Determine whether two TimeSpan instances overlap each other.
     *
     * @param otherTimeSpan
     * @return True when
     */
    public boolean isOverlapping(TimeSpan otherTimeSpan) {
        boolean isOverlapping = true;

        int compareBeginToBegin = this.getBeginTime().compareTo(otherTimeSpan.getBeginTime());
        int compareEndToEnd = this.getEndTime().compareTo(otherTimeSpan.getEndTime());
        int compareBeginToEnd = this.getBeginTime().compareTo(otherTimeSpan.getEndTime());
        int compareEndToBegin = this.getEndTime().compareTo(otherTimeSpan.getBeginTime());

        if (compareBeginToBegin < 0 && compareEndToBegin <= 0 && compareBeginToEnd <= 0 && compareEndToEnd < 0) {
            isOverlapping = false;
        } else if (compareBeginToBegin > 0 && compareEndToBegin >= 0 && compareBeginToEnd >= 0 && compareEndToEnd > 0) {
            isOverlapping = false;
        }
        return isOverlapping;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + (this.beginTime != null ? this.beginTime.hashCode() : 0);
        hash = 29 * hash + (this.endTime != null ? this.endTime.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TimeSpan other = (TimeSpan) obj;
        if (this.beginTime != other.beginTime && (this.beginTime == null || !this.beginTime.equals(other.beginTime))) {
            return false;
        }
        if (this.endTime != other.endTime && (this.endTime == null || !this.endTime.equals(other.endTime))) {
            return false;
        }
        return true;
    }
}