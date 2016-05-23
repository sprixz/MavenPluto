/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto.logic.entities;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author Varga Bence
 */
@Entity
public class Subject implements Serializable {
   
    
    private static final long serialVersionUID = 112331231L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String subjectName;
    private String tName;
  
    public String getTeacherName() {
        return tName;
    }

    public void setTeacherName(String teacherName) {
        this.tName = teacherName;
    }
    private int registered_sem;
    
   
    
    private Subject requirement;

    private String room;
   
    
    public static String[] fields = new String[]{"Tárgy neve", "Terem", "Nap","Időpont","Ajánlott félév" ,"Előfeltétel","Óktató neve"};

    private String subDay;
    private String subHour;
    
    
    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    public int getRegistered_sem() {
        return registered_sem;
    }

    public void setRegistered_sem(int registered_sem) {
        this.registered_sem = registered_sem;
    }

    public Subject getRequirement() {
        return requirement;
    }

    public void setRequirement(Subject requirement) {
        this.requirement = requirement;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getSubDay() {
        return subDay;
    }

    public void setSubDay(String subDay) {
        this.subDay = subDay;
    }

    public String getSubHour() {
        return subHour;
    }

    public void setSubHour(String subHour) {
        this.subHour = subHour;
    }

    
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Subject)) {
            return false;
        }
        Subject other = (Subject) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return subjectName;
    }
    
}
