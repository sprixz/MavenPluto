/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto.logic.controllers;

import com.mycompany.maven.pluto.logic.controllers.exceptions.NonexistentEntityException;
import com.mycompany.maven.pluto.logic.entities.PlutoUser;
import java.io.Serializable;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Kicsiny Viktor
 */
public class PlutoUserJpaController implements Serializable {

    public PlutoUserJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(PlutoUser plutoUser) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            em.persist(plutoUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(PlutoUser plutoUser) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            plutoUser = em.merge(plutoUser);
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = plutoUser.getId();
                if (findPlutoUser(id) == null) {
                    throw new NonexistentEntityException("The plutoUser with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            PlutoUser plutoUser;
            try {
                plutoUser = em.getReference(PlutoUser.class, id);
                plutoUser.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The plutoUser with id " + id + " no longer exists.", enfe);
            }
            em.remove(plutoUser);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<PlutoUser> findPlutoUserEntities() {
        return findPlutoUserEntities(true, -1, -1);
    }

    public List<PlutoUser> findPlutoUserEntities(int maxResults, int firstResult) {
        return findPlutoUserEntities(false, maxResults, firstResult);
    }

    private List<PlutoUser> findPlutoUserEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(PlutoUser.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public PlutoUser findPlutoUser(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(PlutoUser.class, id);
        } finally {
            em.close();
        }
    }

    public int getPlutoUserCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<PlutoUser> rt = cq.from(PlutoUser.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
