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
  
    /**
     *
     * @return
     */
    public String getTeacherName() {
        return tName;
    }

    /**
     *
     * @param teacherName
     */
    public void setTeacherName(String teacherName) {
        this.tName = teacherName;
    }
    private int registered_sem;
    
   
    
    private Subject requirement;

    private String room;
   
    /**
     *
     */
    public static String[] fields = new String[]{"Tárgy neve", "Terem", "Nap","Időpont","Ajánlott félév" ,"Előfeltétel","Óktató neve"};

    private String subDay;
    private String subHour;
    
    /**
     *
     * @return
     */
    public String getSubjectName() {
        return subjectName;
    }

    /**
     *
     * @param subjectName
     */
    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    /**
     *
     * @return
     */
    public int getRegistered_sem() {
        return registered_sem;
    }

    /**
     *
     * @param registered_sem
     */
    public void setRegistered_sem(int registered_sem) {
        this.registered_sem = registered_sem;
    }

    /**
     *
     * @return
     */
    public Subject getRequirement() {
        return requirement;
    }

    /**
     *
     * @param requirement
     */
    public void setRequirement(Subject requirement) {
        this.requirement = requirement;
    }

    /**
     *
     * @return
     */
    public String getRoom() {
        return room;
    }

    /**
     *
     * @param room
     */
    public void setRoom(String room) {
        this.room = room;
    }

    /**
     *
     * @return
     */
    public String getSubDay() {
        return subDay;
    }

    /**
     *
     * @param subDay
     */
    public void setSubDay(String subDay) {
        this.subDay = subDay;
    }

    /**
     *
     * @return
     */
    public String getSubHour() {
        return subHour;
    }

    /**
     *
     * @param subHour
     */
    public void setSubHour(String subHour) {
        this.subHour = subHour;
    }

    /**
     *
     * @return
     */
    public Integer getId() {
        return id;
    }

    /**
     *
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     *
     * @return
     */
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     *
     * @param object
     * @return
     */
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

    /**
     *
     * @return
     */
    @Override
    public String toString() {
        return subjectName;
    }
    
}
