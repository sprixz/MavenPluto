/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto.logic;

import javax.persistence.Cache;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import com.mycompany.maven.pluto.controllers.PlutoUserJpaController;
import com.mycompany.maven.pluto.controllers.SubjectJpaController;

/**
 *
 * @author Varga Bence
 */
public class DataSource {
    
    private static DataSource dataSource = null;
    private EntityManagerFactory emf;
    private final Cache cache = getEntityManagerFactory().getCache();
    
    private PlutoUserJpaController userController;
    private SubjectJpaController subjectController;
    
    
     public static DataSource getInstance() {
        if (dataSource == null) {
            dataSource = new DataSource();
            
        }
        return dataSource;
    }
     
    private DataSource(){
        userController = new PlutoUserJpaController(getEntityManagerFactory());
        subjectController = new SubjectJpaController(getEntityManagerFactory());   
    }

    public PlutoUserJpaController getUserController() {
        return userController;
    }

    public SubjectJpaController getSubjectController() {
        return subjectController;
    }
    
    
     
     
    public final EntityManagerFactory getEntityManagerFactory() {
        emf = Persistence.createEntityManagerFactory("PlutoPU");
        return emf;
    }

    public EntityManagerFactory getEmf() {
        return emf;
    }

    
   
    
    
}
