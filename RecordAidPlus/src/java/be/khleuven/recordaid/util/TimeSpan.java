package be.khleuven.recordaid.util;

import java.io.Serializable;
import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;

/**
 *
 * Encapsulates two Calendar objects to represent a period between two points in time. 
 * 
 * @author Vincent Ceulemans
 */
@Entity
public class TimeSpan implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar beginTime = Calendar.getInstance(); 
    
    @Temporal(javax.persistence.TemporalType.DATE)
    private Calendar endTime = Calendar.getInstance(); 
   
    public TimeSpan(){
    }

    public TimeSpan(Calendar beginTime, Calendar endTime) {
        this.setBeginTime(beginTime);
        this.setEndTime(endTime);
    }
   
    public void setBeginYear(int year){
        this.beginTime.set(Calendar.YEAR, year);
    } 
    
    public void setBeginHour(int hour){
        this.beginTime.set(Calendar.HOUR, hour);
    }
    
    public int getBeginHour(){
        return this.beginTime.get(Calendar.HOUR); 
    }
    
    public void setBeginMinute(int minute){
        this.beginTime.set(Calendar.MINUTE, minute);
    }
    
    public int getBeginMinute(){
        return this.beginTime.get(Calendar.MINUTE);
    }
    
    public void setEndYear(int year){
        this.endTime.set(Calendar.YEAR, year);
    }
    
    public void setEndHour(int hour){
        this.endTime.set(Calendar.HOUR, hour);
    }
     
    public int getEndHour(){
        return this.endTime.get(Calendar.HOUR); 
    } 
    
    public void setEndMinute(int minute){
        this.endTime.set(Calendar.MINUTE, minute);
    }
    
    public int getEndMinute(){
        return this.endTime.get(Calendar.MINUTE);
    }

    public Calendar getBeginTime() {
        return beginTime;
    }

    public void setBeginTime(Calendar beginTime) {
        this.beginTime = beginTime;
    }

    public Calendar getEndTime() {
        return endTime;
    }

    public void setEndTime(Calendar endTime) {
        this.endTime = endTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}