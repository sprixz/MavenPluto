/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto;

import com.mycompany.maven.pluto.gui.PlutoFrame;
import com.mycompany.maven.pluto.logic.DataSource;
import java.awt.EventQueue;

/**
 *
 * @author Varga Bence
 */
public class Main {
     public static void main(String[] args) {
        DataSource.getInstance().getEntityManagerFactory().createEntityManager().close();
        EventQueue.invokeLater(new Runnable() {

            @Override
            public void run() {
                
                PlutoFrame plutoFrame = new PlutoFrame();
            }
        });
     }
          
}
