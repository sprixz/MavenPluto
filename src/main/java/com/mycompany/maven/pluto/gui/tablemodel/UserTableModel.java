/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto.gui.tablemodel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.AbstractTableModel;
import com.mycompany.maven.pluto.logic.DataSource;
import com.mycompany.maven.pluto.logic.entities.PlutoUser;
import com.mycompany.maven.pluto.logic.entities.Subject;

/**
 *
 * @author Varga Balázs
 */
public class UserTableModel extends AbstractTableModel {

    /**
     *
     * @return
     */
    @Override
    public int getRowCount() {
        return DataSource.getInstance().getUserController().getPlutoUserCount();

    }

    /**
     *
     * @return
     */
    @Override
    public int getColumnCount() {
        return PlutoUser.fields.length;
    }

    /**
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        PlutoUser users = DataSource.getInstance().getUserController().findPlutoUserEntities().get(rowIndex);
        switch (columnIndex) {
            case 0:
                return users.getName();
            case 1:
                return users.getUsername();
            case 2:
                return users.getPassoword();
            case 3:
                return users.isTeacher();
            case 4:
                return users.isOnline();
            default:
                return null;

        }
    }

    /**
     *
     * @param column
     * @return
     */
    @Override
    public String getColumnName(int column) {
        return PlutoUser.fields[column];
    }

    /**
     *
     * @param rowIndex
     * @param columnIndex
     * @return
     */
    @Override
    public boolean isCellEditable(int rowIndex, int columnIndex) {
        return columnIndex == 2;
    }

    /**
     *
     * @param columnIndex
     * @return
     */
    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0:
                return String.class;
            case 1:
                return String.class;
            case 2:
                return String.class;
            default:
                return Boolean.class;
        }

    }

    /**
     *
     * @return
     */
    public int amountofstudent() {
        int db = 0;
        for (int i = 0; i < getRowCount(); i++) {
            PlutoUser student = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (!(student.isTeacher())) {
                db++;
            }
        }
        return db;

    }

    /**
     *
     * @return
     */
    public Object[] currentstudent() {

        int arraysize = amountofstudent();
        Object[] subarray = new String[arraysize];
        int currentsize = 0;

        for (int i = 0; i < getRowCount(); i++) {

            PlutoUser currentstud = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (!(currentstud.isTeacher())) {
                subarray[currentsize] = currentstud.getName() + "-" + currentstud.getUsername();
                currentsize++;
            }

        }
        return subarray;

    }

    /**
     *
     * @return
     */
    public int amountofteacher() {
        int db = 0;
        for (int i = 0; i < getRowCount(); i++) {
            PlutoUser student = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if ((student.isTeacher())) {
                db++;
            }
        }
        return db;

    }

    /**
     *
     * @return
     */
    public String[] currentteahcer() {

        int arraysize = amountofteacher();
        String[] subarray = new String[arraysize];
        int currentsize = 0;

        for (int i = 0; i < getRowCount(); i++) {

            PlutoUser currentstud = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if ((currentstud.isTeacher())) {
                subarray[currentsize] = currentstud.getName() + "-" + currentstud.getUsername();
                currentsize++;
            }

        }
        return subarray;

    }

    /**
     *
     * @param user
     */
    public void turnOnlie(PlutoUser user) {
        PlutoUser newuser = new PlutoUser();
        newuser = user;
        newuser.setOnline(true);

        for (int i = 0; i < getRowCount(); i++) {
            PlutoUser user2 = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (newuser.getUsername().equals(user2.getUsername())) {
                try {
                    DataSource.getInstance().getUserController().edit(newuser);
                } catch (Exception ex) {
                    Logger.getLogger(UserTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     *
     * @param user
     */
    public void turnOffline(PlutoUser user) {
        PlutoUser newuser = new PlutoUser();
        newuser = user;
        newuser.setOnline(false);

        for (int i = 0; i < getRowCount(); i++) {
            PlutoUser user2 = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (newuser.getUsername().equals(user2.getUsername())) {
                try {
                    DataSource.getInstance().getUserController().edit(newuser);
                } catch (Exception ex) {
                    Logger.getLogger(UserTableModel.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    /**
     *
     * @return
     */
    public ArrayList<PlutoUser> whoIsOnline() {
        ArrayList<PlutoUser> online = new ArrayList<>();
        for (int i = 0; i < getRowCount(); i++) {
            PlutoUser user = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (user.isOnline() && user.isTeacher() == false) {
                online.add(user);
            }
        }

        return online;

    }

    /**
     *
     * @param user
     * @param subject
     */
    public void addSubjectToMyList(String user, Subject subject) {
        PlutoUser thisuser = getThisUser(user);
      
        
       
        if (thisuser.getSubjects().isEmpty()) {
            List<Subject> mynewsubject = new ArrayList<>();
           
            mynewsubject.add(subject);
            
            
            thisuser.setSubjects(mynewsubject);
            try {
                DataSource.getInstance().getUserController().edit(thisuser);
                fireTableDataChanged();
            } catch (Exception ex) {
                Logger.getLogger(UserTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        } else {
            List<Subject> alreadyhavealist = thisuser.getSubjects();
            alreadyhavealist.add(subject);
            
            List<Subject> targy = alreadyhavealist;
            thisuser.setSubjects(targy);

            try {
                DataSource.getInstance().getUserController().edit(thisuser);
                fireTableDataChanged();
            } catch (Exception ex) {
                Logger.getLogger(UserTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

        

    }

    /**
     *
     * @param name
     * @param list
     * @return
     */
    public boolean youAlreadyHaveThisSubject(String name, List<Integer> list) {
        
        PlutoUser thisuser = getThisUser(name);
        List<Subject> thislist = getThisSubject(list);
        if (thislist.isEmpty()) {
            return false;
        } else {
            int i=0;
            while(i<thisuser.getSubjects().size()){
                if(thisuser.getSubjects().get(i).equals(thislist.get(i))){
                    return true;
                }
                i++;
            }
            
           
        }

        return false;

    }

    /**
     *
     * @param username
     * @return
     */
    public PlutoUser getThisUser(String username) {
        PlutoUser thisuser = new PlutoUser();

        for (int i = 0; i < getRowCount(); i++) {
            PlutoUser compare = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (compare.getUsername().equals(username)) {
                thisuser = compare;
            }
        }
        return thisuser;
    }

    /**
     *
     * @param mysubject
     * @return
     */
    public List<Subject> getThisSubject(List<Integer> mysubject) {
        List<Subject> iwts = new ArrayList<>();

        for (int i = 0; i < mysubject.size() ; i++) {
            Subject compare = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (Objects.equals(compare.getId(), mysubject.get(i))) {
                iwts.add(compare);
            }

        }

        return iwts;

    }

    /**
     *
     * @param user
     * @param id
     */
    public void updateHisList(PlutoUser user, int id) {
        List<Subject> alreadyhavealist = user.getSubjects();
        Subject deletethis = new Subject();
        for (int i = 0; i < alreadyhavealist.size(); i++) {
            if (alreadyhavealist.get(i).getId() == id) {
                deletethis = alreadyhavealist.get(i);
            }
        }
        alreadyhavealist.remove(deletethis);
        user.setSubjects(alreadyhavealist);
        try {
            DataSource.getInstance().getUserController().edit(user);
        } catch (Exception ex) {
            Logger.getLogger(UserTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        fireTableDataChanged();

    }
    
    /**
     *
     * @param subid
     * @return
     */
    public List<PlutoUser> theyHaveThisSubject(int subid){
      List<PlutoUser> students = new ArrayList<>();
      
      for(int i=0;i<getRowCount();i++){
          PlutoUser compare = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
          if(compare.getSubjects().isEmpty()){}
          else{
              for(int j=0;j<compare.getSubjects().size();j++){
                  if(compare.getSubjects().get(j).getId() == subid){
                  students.add(compare);
              }
              }
              
          }
      }    
        return students;
    }
    
    /**
     *
     * @param selectedsubject
     * @param studentname
     * @return
     */
    public boolean requirementCheck(Subject selectedsubject, String studentname){
        PlutoUser thisuser = getThisUser(studentname);
        if(selectedsubject.getRequirement()==null){
            return true;
        }else if(thisuser.getFulfilledsubids() == null){
            return false;
        }else{
            int i=0;
            while(i<thisuser.getFulfilledsubids().size()){
            if(Objects.equals(thisuser.getFulfilledsubids().get(i), selectedsubject.getRequirement().getId())){
                return true;
            }
            i++;
        }
            
        }
        
        
 
        return false;
    }
    
    /**
     *
     * @param mysub
     * @return
     */
    public String[] getMySubjectArray(List<Subject> mysub){
        String[] mys = new String[mysub.size()];
        
        for(int i=0;i<mysub.size();i++){
           mys[i] = mysub.get(i).getSubjectName(); 
        }
        
        return mys;
    }
    
    /**
     *
     * @param selectedsub
     * @param student
     * @return
     */
    public Subject getThisSubjectFromMyList(String selectedsub, PlutoUser student){
        Subject thissubject = new Subject();
        for(int i=0;i<student.getSubjects().size();i++){
            if(student.getSubjects().get(i).getSubjectName().equals(selectedsub)){
                thissubject = student.getSubjects().get(i);
            }
        }
        
        return thissubject;
    }
    
    /**
     *
     * @param user
     * @param id
     */
    public void addThisIdToMyList(PlutoUser user, int id){
        List<Integer> ids = user.getFulfilledsubids();
        
        if(ids==null){
            List<Integer> newids = new ArrayList<>();
            newids.add(id);
            user.setFulfilledsubids(newids);
            try {
                DataSource.getInstance().getUserController().edit(user);
                fireTableDataChanged();
            } catch (Exception ex) {
                Logger.getLogger(UserTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        else{
            ids.add(id);
            user.setFulfilledsubids(ids);
            try {
                DataSource.getInstance().getUserController().edit(user);
            } catch (Exception ex) {
                Logger.getLogger(UserTableModel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        
        
    }
    
    /**
     *
     * @param id
     * @param user
     * @return
     */
    public boolean alreadyDoneTHisSubject(int id,PlutoUser user){
        int i=0;
        if(user.getFulfilledsubids()== null){
            return false;
        }
        else{
             while(i<user.getFulfilledsubids().size()){
            if(user.getFulfilledsubids().get(i)==id){
                return true;
            }
            i++;
        }
        }
       
        
        return false;
    }
   
    
  
    

}