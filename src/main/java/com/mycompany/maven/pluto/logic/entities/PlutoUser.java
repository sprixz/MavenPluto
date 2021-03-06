/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto.logic.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 *
 * @author Varga Bence
 */
@Entity
public class PlutoUser implements Serializable {
   
    

    private static final long serialVersionUID = 15545L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String username;
    private String name;
    private String passoword;
    private boolean teacher;
    private boolean online;
    
    @OneToMany
    private List<Subject> subjects;
    
    private List<Integer> fulfilledsubids;

    /**
     *
     */
    public static String[] fields = new String[]{"Name", "Username","Password","Teacher","Online"};

    /**
     *
     * @return
     */
    public List<Integer> getFulfilledsubids() {
        return fulfilledsubids;
    }

    /**
     *
     * @param fulfilledsubids
     */
    public void setFulfilledsubids(List<Integer> fulfilledsubids) {
        this.fulfilledsubids = fulfilledsubids;
    }
     
    /**
     *
     * @return
     */
    public String getUsername() {
        return username;
    }

    /**
     *
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     *
     * @return
     */
    public String getPassoword() {
        return passoword;
    }

    /**
     *
     * @param passoword
     */
    public void setPassoword(String passoword) {
        this.passoword = passoword;
    }

    /**
     *
     * @return
     */
    public List<Subject> getSubjects() {
        return subjects;
    }

    /**
     *
     * @param subjects
     */
    public void setSubjects(List<Subject> subjects) {
        this.subjects = subjects;
    }

    /**
     *
     * @return
     */
    public boolean isOnline() {
        return online;
    }

    /**
     *
     * @param online
     */
    public void setOnline(boolean online) {
        this.online = online;
    }

    /**
     *
     * @return
     */
    public boolean isTeacher() {
        return teacher;
    }

    /**
     *
     * @param Teacher
     */
    public void setTeacher(boolean Teacher) {
        this.teacher = Teacher;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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
        if (!(object instanceof PlutoUser)) {
            return false;
        }
        PlutoUser other = (PlutoUser) object;
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
        return username+" " ;
    }

}
