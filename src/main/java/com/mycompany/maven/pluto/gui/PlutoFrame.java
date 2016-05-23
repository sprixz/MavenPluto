/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import javax.swing.table.TableColumn;
import pluto.gui.tableModel.SubjectTableModel;
import pluto.gui.tableModel.UserTableModel;
import pluto.logic.DataSource;
import pluto.logic.entities.PlutoUser;
import pluto.logic.entities.Subject;

/**
 *
 * @author Varga Balázs
 */
public class PlutoFrame extends JFrame {

    private String currentusername;

    // MAIN SCREEN KOMPONENSEK (PLUTO)
    JTextField username = new JTextField(10);
    JPasswordField password = new JPasswordField(10);
    JPanel panel = new JPanel();
    JFrame pluto = new JFrame();
    JLabel mainlabel = new JLabel("Belépés");
    JLabel userlabel = new JLabel("Felhasználó név:");
    JLabel passwlabel = new JLabel("Jelszó:");
    ImageIcon plutojpg = new ImageIcon(getClass().getResource("pluto.jpg"));
    JLabel plutopic = new JLabel(plutojpg);
    JCheckBox isteacher = new JCheckBox("Tanár");
    JButton login = new JButton("Belépés");
    JButton registration = new JButton("Regisztráció");
    ImageIcon realpluto = new ImageIcon(getClass().getResource("plutoreal.jpg"));
    JLabel realplutopic = new JLabel(realpluto);

    //REGISZTRÁCIÓS KOMPONENSEK
    JFrame regframe = new JFrame();
    JPanel regpanel = new JPanel();
    JLabel reglabel = new JLabel("Regisztráció");
    JLabel regnamelabel = new JLabel("Neve:");
    JLabel regusernamelabel = new JLabel("Felhasználóneve:");
    JLabel regpasswordlabel = new JLabel("Jelszó:");
    JLabel studentlabel = new JLabel("(Alapértelmezett: diák.)");
    JCheckBox registeacher = new JCheckBox("Tanár");
    JTextField regusername = new JTextField(20);
    JPasswordField regpassword = new JPasswordField(10);
    JTextField regname = new JTextField(20);
    ImageIcon plutojpg2 = new ImageIcon(getClass().getResource("pluto2.jpg"));
    JLabel pluto2pic = new JLabel(plutojpg2);

    JButton regbutton = new JButton("Regisztrálok");
    JButton backbutton = new JButton("Vissza");

    private UserTableModel userModel;
    private SubjectTableModel subModel;
    private JTable usertable;

    //DIÁK FRAME KOMPONENSEI
    JFrame studentframe = new JFrame();
    JPanel studentpanel;
    JButton addsub = new JButton("Tárgy felvétele");
    JButton scedule = new JButton("Órarend");
    JButton removesub = new JButton("Tárgy leadása");
    JButton studentexit = new JButton("Kilépés");
    JPanel sudentpanel = new JPanel();

    ImageIcon studentjpg = new ImageIcon(getClass().getResource("student.jpg"));
    JLabel studentpic = new JLabel(studentjpg);

    //TANÁR FRAME KOMPONENSEI
    JFrame teacherframe = new JFrame();
    JButton addsubjectbutton = new JButton("Tárgy hozzáadása");
    JButton activstudentsbutton = new JButton("Hallgatók listája");
    JButton exitbutton = new JButton("Kilépés");
    JPanel teacherpanel = new JPanel();
    ImageIcon img1 = new ImageIcon(getClass().getResource("framebackground.jpg"));
    JLabel framepic = new JLabel(img1);
    ImageIcon img2 = new ImageIcon(getClass().getResource("teacher.jpg"));
    JLabel teacherpic = new JLabel(img2);
    JButton removesubject = new JButton("Tárgy Törlés");

    // TÁRGY HOZZÁADÁSA KOMPONENSEI
    JTextField addsubname = new JTextField(30);
    JTextField addsubroom = new JTextField(20);
    JTextField addsubtime = new JTextField(20);
    JTextField addsubregsem = new JTextField(5);
    JLabel addsubnamelabel = new JLabel("Tárgy neve");
    JLabel addsubroomlabel = new JLabel("Helyszín");
    JLabel addsubtimelabel = new JLabel("Időpont");
    JLabel addsubregsemlabel = new JLabel("Ajánlott félév");
    JLabel addsubreqlabel = new JLabel("Előfeltétele");
    JLabel teacherlabel = new JLabel("Oktató");
    JButton addsubject = new JButton("Hozzáad");
    JButton addsubjectcancel = new JButton("Vissza");
    String[] semester = {"1.félév", "2.félév", "3.félév", "4.félév", "5.félév", "6.félév"};
    String[] day = {"Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek"};
    String[] hour = {"8:00", "9:00", "10:00", "11:00", "12:00", "13:00", "14:00", "15:00", "16:00", "17:00"};
    JCheckBox reqbox;
    JComboBox<String> comboDay;
    JComboBox<String> comboHour;
    ImageIcon board2 = new ImageIcon(getClass().getResource("classboard2.jpg"));
    JLabel classboard2 = new JLabel(board2);
    JComboBox<String> comboSemester;
    String[] comboTeacherarray;
    JComboBox<String> comboTeacher;
    JPanel addpanel;
    JFrame addsubframe;

    Subject reqsub = new Subject();

    // tárgyak adatai -- TÖRLÉSNÉL
    JLabel subdetname = new JLabel("Tantárgy neve:");
    JLabel currentsubname;
    JLabel subdetroom = new JLabel("Terem:");
    JLabel currentsubroom;
    JLabel subdettime = new JLabel("Időpont:");
    JLabel currentsubtime;
    JLabel subdetteacher = new JLabel("Tanár neve:");
    JLabel currentsubteacher;
    JButton delete = new JButton("Tárgy törlése");
    JButton cancel = new JButton("Mégse");
    JLabel subdetmain = new JLabel("Válasszon tantárgyat:");
    String[] currentids;
    JComboBox<String> comboId;
    JFrame subdetailsframe;
    JPanel subdet;
    ImageIcon board = new ImageIcon(getClass().getResource("classboard.jpg"));
    JLabel classboard = new JLabel(board);

    JLabel reqsublabel = new JLabel("Előfeltétel:");
    String[] comboRequiredarray;
    JComboBox<String> comboReqsub;

    JFrame mysubframe;
    JPanel mysubp;
    
    public PlutoFrame() {
        gui();
    }

    public void addsubjectGui() {
        addsubframe = new JFrame();
        addsubframe.setVisible(true);
        addsubframe.setSize(500, 350);
        addsubframe.setLocationRelativeTo(null);
        addsubframe.setResizable(false);

        comboSemester = new JComboBox<>(semester);
        comboHour = new JComboBox<>(hour);
        comboDay = new JComboBox<>(day);
        reqbox = new JCheckBox();
        reqbox.setOpaque(false);
        reqbox.setSelected(false);

        comboTeacherarray = userModel.currentteahcer();
        comboTeacher = new JComboBox<>(comboTeacherarray);

        comboRequiredarray = (String[]) subModel.currentSubjects();
        comboReqsub = new JComboBox<>(comboRequiredarray);
        comboReqsub.setVisible(false);

        addsubname.setText("");
        addsubroom.setText("");

        //String[] comboReqarray = subModel.currentreqsubject(addsubname.getText());
        //JComboBox<String> comboReq = new JComboBox<String>(comboReqarray);
        addpanel = new JPanel();
        SpringLayout s5 = new SpringLayout();
        addpanel.setLayout(s5);

        addsubframe.add(addpanel);
        addpanel.add(addsubnamelabel);
        addpanel.add(addsubregsemlabel);
        addpanel.add(addsubreqlabel);
        addpanel.add(addsubroomlabel);
        addpanel.add(addsubtimelabel);
        addpanel.add(addsubname);
        addpanel.add(comboSemester);
        addpanel.add(reqsublabel);
        addpanel.add(comboReqsub);
        addpanel.add(addsubroom);
        //addpanel.add(addsubtime);
        addpanel.add(comboDay);
        addpanel.add(comboHour);
        addpanel.add(reqbox);
        addpanel.add(teacherlabel);
        addpanel.add(comboTeacher);
        addpanel.add(addsubject);
        addpanel.add(addsubjectcancel);
        addpanel.add(classboard2);

        //s5.putConstraint(SpringLayout.HORIZONTAL_CENTER, reglabel, 5, SpringLayout.HORIZONTAL_CENTER, regpanel);
        s5.putConstraint(SpringLayout.WEST, classboard2, 0, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubnamelabel, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubnamelabel, 15, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubname, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubname, 35, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubroomlabel, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubroomlabel, 55, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubroom, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubroom, 75, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubregsemlabel, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubregsemlabel, 95, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, comboSemester, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, comboSemester, 115, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubtimelabel, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubtimelabel, 145, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, comboDay, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, comboDay, 165, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, comboHour, 100, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, comboHour, 165, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, teacherlabel, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, teacherlabel, 195, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, reqsublabel, 100, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, reqsublabel, 195, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, comboTeacher, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, comboTeacher, 215, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, comboReqsub, 100, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, comboReqsub, 215, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubreqlabel, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubreqlabel, 245, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, reqbox, 160, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, reqbox, 193, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubject, 10, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubject, 265, SpringLayout.NORTH, addpanel);
        s5.putConstraint(SpringLayout.WEST, addsubjectcancel, 110, SpringLayout.WEST, addpanel);
        s5.putConstraint(SpringLayout.NORTH, addsubjectcancel, 265, SpringLayout.NORTH, addpanel);
        // s5.putConstraint(SpringLayout.WEST, comboReq, 10, SpringLayout.WEST, addpanel);
        //s5.putConstraint(SpringLayout.NORTH, comboReq, 250, SpringLayout.NORTH, addpanel);

        reqbox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (reqbox.isSelected()) {
                    //reqbox.setVisible(false);
                    comboReqsub.setVisible(true);
                } else {
                    // reqbox.setVisible(true);
                    comboReqsub.setVisible(false);
                }

            }
        });

        comboReqsub.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == comboReqsub) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String ids = (String) cb.getSelectedItem();
                    System.out.println("Ez az ef.: " + getReqSubject(ids).getSubjectName());
                    reqsub = getReqSubject(ids);
                }

            }
        });

        addsubject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (addsubname.getText().isEmpty() || addsubroom.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(addsubframe, "Nem töltött mindent ki.", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else if (alreadyExistSub()) {
                    JOptionPane.showMessageDialog(addsubframe, "Már létezik ez a kurzus ebben az időpontban!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else if (roomIsTaken()) {
                    JOptionPane.showMessageDialog(addsubframe, "A terem foglalt erre az időpontra!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else if (teacherIsTeaching()) {
                    JOptionPane.showMessageDialog(addsubframe, "A kiválasztott tanár, ebben az időpontban már tanít!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else {
                    Subject newsub = new Subject();
                    int id = newsub.hashCode();
                    newsub.setId(id);
                    newsub.setSubjectName(addsubname.getText());
                    newsub.setRoom(addsubroom.getText());
                    newsub.setTime(getSubjectTime());
                    newsub.setTeacherName(comboTeacher.getSelectedItem().toString());
                    if (reqbox.isSelected()) {
                        newsub.setRequirement(reqsub);
                    } else {
                        newsub.setRequirement(null);
                    }
                    newsub.setRegistered_sem(getSelectedSemester());
                    newsub.setFulfilled(false);

                    DataSource.getInstance().getSubjectController().create(newsub);

                    JOptionPane.showMessageDialog(addsubframe, "Tantárgy hozzáadva!", "Információ", JOptionPane.INFORMATION_MESSAGE);
                    int reply = JOptionPane.showConfirmDialog(addsubframe, "Akar további tárgyakat hozzáadni a rendszerhez?", "Tárgyhozzáadás", JOptionPane.YES_NO_OPTION);
                    if (reply == JOptionPane.YES_OPTION) {
                        addsubframe.dispose();
                        addsubjectGui();

                    } else {
                        addsubframe.dispose();
                        teacherframe.setVisible(true);

                    }

                }

            }
        });

        addsubjectcancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addsubframe.dispose();
                teacherframe.setVisible(true);

            }
        });
        subModel.fireTableDataChanged();
    }

    public void studentGui() {
        pluto.setVisible(false);
        studentframe.setVisible(true);
        studentframe.setTitle("Plutó hallgatói tanulmányirendszer - DIÁK");
        studentframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        studentframe.setSize(500, 250);
        studentframe.setLocationRelativeTo(null);
        studentpanel = new JPanel();

        SpringLayout l3 = new SpringLayout();
        studentpanel.setLayout(l3);
        JButton mysubject = new JButton("Felvett tárgyak");

        studentframe.add(studentpanel);
        studentpanel.add(addsub);
        studentpanel.add(scedule);
        studentpanel.add(studentexit);
        studentpanel.add(removesub);
        studentpanel.add(mysubject);
        studentpanel.add(studentpic);
        

        l3.putConstraint(SpringLayout.NORTH, studentpic, 0, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, addsub, 180, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, addsub, 5, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, scedule, 5, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, scedule, 5, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, studentexit, 5, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, studentexit, 405, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, removesub, 180, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, removesub, 365, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.WEST, mysubject, 180, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, mysubject, 5, SpringLayout.NORTH, studentpanel);

        studentexit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                //ArrayList<PlutoUser> online = userModel.whoIsOnline();
                //System.out.println("Ő VAN BELÉPBE: "+currentusername);
                for (int i = 0; i < userModel.getRowCount(); i++) {
                    PlutoUser compareuser = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
                    if (currentusername.equals(compareuser.getUsername())) {
                        userModel.turnOffline(compareuser);
                        userModel.fireTableDataChanged();
                    }

                }
                studentframe.dispose();
                new PlutoFrame();
            }
        });

        addsub.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                JFrame subdetailsframe2 = new JFrame();
                subdetailsframe2.setVisible(true);
                subdetailsframe2.setTitle("Plutó hallgatói tanulmányirendszer - Tárgyfelvétel");
                subdetailsframe2.setDefaultCloseOperation(EXIT_ON_CLOSE);
                subdetailsframe2.setSize(400, 205);
                subdetailsframe2.setLocationRelativeTo(null);
                subdetailsframe2.setResizable(false);
                JPanel subdet2 = new JPanel();
                subdet2.setBackground(Color.WHITE);
                SpringLayout s6 = new SpringLayout();
                String[] currentids2 = subModel.currentSubjectsId();
                JComboBox<String> comboId2 = new JComboBox<>(currentids2);
                //comboId2.setSelectedIndex(0);
                JLabel currentsubname2 = new JLabel("");
                JLabel currentsubroom2 = new JLabel("");
                JLabel currentsubtime2 = new JLabel("");
                JLabel currentsubteacher2 = new JLabel("");

                JButton addsubtomylist = new JButton("Felvesz");
                JButton addsubcancel = new JButton("Vissza");
                JButton mysubject = new JButton("Felvett tárgyak");

                subdet2.setLayout(s6);
                subdetailsframe2.add(subdet2);
                subdet2.add(subdetname);
                subdet2.add(subdetroom);
                subdet2.add(subdettime);
                subdet2.add(comboId2);
                subdet2.add(subdetmain);
                subdet2.add(subdetteacher);
                subdet2.add(currentsubname2);
                subdet2.add(currentsubroom2);
                subdet2.add(currentsubtime2);
                subdet2.add(currentsubteacher2);
                subdet2.add(addsubtomylist);
                subdet2.add(addsubcancel);
                subdet2.add(classboard);

                s6.putConstraint(SpringLayout.NORTH, classboard, 0, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, subdetmain, 120, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, subdetmain, 20, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, subdetname, 20, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, subdetname, 55, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, subdetroom, 20, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, subdetroom, 75, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, subdettime, 20, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, subdettime, 115, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, comboId2, 250, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, comboId2, 20, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, subdetteacher, 20, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, subdetteacher, 95, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, currentsubname2, 115, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, currentsubname2, 55, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, currentsubroom2, 115, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, currentsubroom2, 75, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, currentsubtime2, 115, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, currentsubtime2, 115, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, currentsubteacher2, 115, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, currentsubteacher2, 95, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, addsubtomylist, 20, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, addsubtomylist, 140, SpringLayout.NORTH, subdet2);
                s6.putConstraint(SpringLayout.WEST, addsubcancel, 140, SpringLayout.WEST, subdet2);
                s6.putConstraint(SpringLayout.NORTH, addsubcancel, 140, SpringLayout.NORTH, subdet2);
                

                comboId2.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        if (e.getSource() == comboId2) {
                            JComboBox cb = (JComboBox) e.getSource();
                            String ids = (String) cb.getSelectedItem();
                            String[] seged = delsubDetalis(ids);
                            currentsubname2.setText(seged[0]);
                            currentsubroom2.setText(seged[1]);
                            currentsubtime2.setText(seged[2]);
                            currentsubteacher2.setText(seged[3]);

                        }

                    }
                });

                addsubcancel.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        subdetailsframe2.dispose();
                        studentframe.setVisible(true);
                    }
                });

                addsubtomylist.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {

                        if (comboId2.getItemCount() < 1) {
                            subdetailsframe2.dispose();
                            JOptionPane.showMessageDialog(studentframe, "Nincs több tantárgy!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                        } else {

                            String ids = (String) comboId2.getSelectedItem();
                            int realid = Integer.parseInt(ids);

                            for (int i = 0; i < subModel.getRowCount(); i++) {
                                Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);

                                if (sub.getId() == realid) {
                                    List<Integer> addsubids = new ArrayList<>();
                                    addsubids.add(realid);

                                    if (userModel.youAlreadyHaveThisSubject(currentusername, addsubids)) {
                                        JOptionPane.showMessageDialog(subdetailsframe2, "Ezt a tárgyat már felvetted!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                                        comboId2.removeItem(comboId2.getSelectedItem());
                                    } else {
                                        System.out.println(comboId2.getSelectedItem());
                                        Subject selectedsub = subModel.getmySubject(realid);
                                        if(userModel.requirementCheck(selectedsub, currentusername)==true){
                                            userModel.addSubjectToMyList(currentusername, subModel.getmySubject(realid));
                                            subModel.fireTableDataChanged();
                                            userModel.fireTableDataChanged();
                                            comboId2.removeItem(comboId2.getSelectedItem());
                                            JOptionPane.showMessageDialog(subdetailsframe2, "Sikeres tárgyfelvétel");
                                        }
                                        else{
                                            JOptionPane.showMessageDialog(subdetailsframe2, "Nem teljesült a tárgy előfeltétele!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                                        }
                                        

                                    }
                                }

                            }

                        }

                    }

                });

            }
        });

        mysubject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                mysubframe = new JFrame();
                mysubframe.setVisible(true);
                mysubframe.setTitle("Felvett tantárgyak");
                mysubframe.setSize(800, 300);
                mysubp = new JPanel(new BorderLayout());
                
                JTable mysubtable = new JTable(new AbstractTableModel() {
                 PlutoUser thisuser = userModel.getThisUser(currentusername);
                    
                    @Override
                    public int getRowCount() {
                        return thisuser.getSubjects().size();
                    }

                    @Override
                    public int getColumnCount() {
                       return subModel.getColumnCount();
                    }

                    @Override
                            public Object getValueAt(int rowIndex, int columnIndex) {
                                Subject subjects = thisuser.getSubjects().get(rowIndex);
                                switch (columnIndex) {
                                    case 0:
                                        return subjects.getSubjectName();
                                    case 1:
                                        return subjects.getRoom();
                                    case 2:
                                        return subjects.getTime();
                                    case 3:
                                        return subjects.getRegistered_sem();
                                    case 4:
                                        return subjects.getRequirement();
                                    case 5:
                                        return subjects.getTeacherName();
                                    case 6:
                                        return subjects.isFulfilled();
                                    default:
                                        return null;
                                }
                            }

                            

                            @Override
                            public Class<?> getColumnClass(int columnIndex) {
                                switch (columnIndex) {
                                    case 0:
                                        return String.class;
                                    case 1:
                                        return String.class;
                                    case 2:
                                        return String.class;
                                    case 3:
                                        return Integer.class;
                                    case 4:
                                        return Subject.class;
                                    case 5:
                                        return String.class;
                                    default:
                                        return Boolean.class;
                                }
                            }

                            @Override
                            public String getColumnName(int column) {
                                return subModel.getColumnName(column);
                            }
                });
                mysubframe.add(mysubp);
                mysubp.add(add(new JScrollPane(mysubtable)), BorderLayout.CENTER);
            }
        });
        
        removesub.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PlutoUser student = userModel.getThisUser(currentusername);
                Object[] mysubjects = userModel.getMySubjectArray(student.getSubjects());
                
                
                String selectedsub = (String)JOptionPane.showInputDialog(
                   mysubframe,
                    "Felvett tárgyak",
                    "Tárgy leadása",
                    JOptionPane.PLAIN_MESSAGE,
                    null,
                    mysubjects,
                    null);
                
                
                if(selectedsub != null){
                    List<Subject> studentsubjects = student.getSubjects();
                    Subject thissub = userModel.getThisSubjectFromMyList(selectedsub, student);
                    student.getSubjects().remove(thissub);
                    student.setSubjects(studentsubjects);
                    try {
                        DataSource.getInstance().getUserController().edit(student);
                    } catch (Exception ex) {
                        Logger.getLogger(PlutoFrame.class.getName()).log(Level.SEVERE, null, ex);
                    }
                    userModel.fireTableDataChanged();
                    JOptionPane.showMessageDialog(mysubframe,"Sikeres lejelentkezés!");
                }
                
            }
    
        });
        
        scedule.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame sceduleframe = new JFrame();
                JPanel scedulepanel = new JPanel(new BorderLayout());
                sceduleframe.setVisible(true);
                sceduleframe.setTitle("Órarend");
                sceduleframe.setSize(800, 400);
                
                sceduleframe.add(scedulepanel);
                //scedulepanel.add(classscedule);
                
            }
        });
        
    }
    
    

 
    private void gui() {

        // FRAME beállítás
        pluto.setVisible(true);
        pluto.setTitle("Plutó hallgatói tanulmányirendszer");
        pluto.setDefaultCloseOperation(EXIT_ON_CLOSE);
        pluto.setSize(500, 250);
        pluto.setLocationRelativeTo(null);
        pluto.setResizable(false);
        SpringLayout sl = new SpringLayout();
        panel.setLayout(sl);

        //panel.setBackground(new Color(72, 161, 239));
        //isteacher.setBackground(new Color(72, 161, 239));
        // User megjelenítő a regisztrálok gombnál
        userModel = new UserTableModel();
        subModel = new SubjectTableModel();
        usertable = new JTable(userModel);
        JPanel userpanel = new JPanel(new BorderLayout());
        userpanel.add(add(new JScrollPane(usertable)), BorderLayout.CENTER);
        //jtabPane.addTab("Hallgatók", userpanel);

        mainlabel.setFont(new Font("Cooper Black", Font.PLAIN, 18));
        isteacher.setOpaque(false);
        mainlabel.setForeground(Color.WHITE);
        userlabel.setForeground(Color.WHITE);
        passwlabel.setForeground(Color.WHITE);
        isteacher.setForeground(Color.WHITE);
        //userlabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));
        //passwlabel.setFont(new Font("Cooper Black", Font.PLAIN, 12));

        //Komponensek hozzáadása
        panel.add(mainlabel);
        panel.add(userlabel);
        panel.add(username);
        panel.add(passwlabel);
        panel.add(password);
        //panel.add(plutopic);
        panel.add(isteacher);
        panel.add(login);
        panel.add(registration);
        panel.add(realplutopic);
        pluto.add(panel);

        // Komponenesek elhelyezkedésének beállítása
        sl.putConstraint(SpringLayout.EAST, realplutopic, 0, SpringLayout.EAST, panel);
        sl.putConstraint(SpringLayout.NORTH, realplutopic, 0, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainlabel, 5, SpringLayout.HORIZONTAL_CENTER, panel);
        sl.putConstraint(SpringLayout.WEST, userlabel, 10, SpringLayout.WEST, panel);
        sl.putConstraint(SpringLayout.NORTH, userlabel, 36, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.WEST, username, 10, SpringLayout.WEST, panel);
        sl.putConstraint(SpringLayout.NORTH, username, 54, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.WEST, passwlabel, 10, SpringLayout.WEST, panel);
        sl.putConstraint(SpringLayout.NORTH, passwlabel, 78, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.WEST, password, 10, SpringLayout.WEST, panel);
        sl.putConstraint(SpringLayout.NORTH, password, 95, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.EAST, plutopic, -30, SpringLayout.EAST, panel);
        sl.putConstraint(SpringLayout.NORTH, plutopic, 10, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.WEST, isteacher, 6, SpringLayout.WEST, panel);
        sl.putConstraint(SpringLayout.NORTH, isteacher, 120, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.WEST, login, 10, SpringLayout.WEST, panel);
        sl.putConstraint(SpringLayout.NORTH, login, 150, SpringLayout.NORTH, panel);
        sl.putConstraint(SpringLayout.WEST, registration, 100, SpringLayout.WEST, panel);
        sl.putConstraint(SpringLayout.NORTH, registration, 150, SpringLayout.NORTH, panel);

        // REGISZTRÁLOK GOMB ESEMÉNYEI
        //  - ÚJ FRAMEE ABLAK AHOL TUDUNK REGISZTRÁLNI
        registration.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                pluto.setVisible(false);
                regframe.setVisible(true);
                regframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
                regframe.setTitle("Plutó hallgatói tanulmányirendszer - Regisztráció");
                regframe.setSize(600, 400);
                regframe.setLocationRelativeTo(null);
                regframe.setResizable(false);
                //regpanel.setBackground(new Color(72, 161, 239));
                //registeacher.setBackground(new Color(72, 161, 239));
                reglabel.setFont(new Font("Cooper Black", Font.PLAIN, 18));
                SpringLayout s2 = new SpringLayout();
                regpanel.setLayout(s2);

                reglabel.setForeground(Color.WHITE);
                regnamelabel.setForeground(Color.WHITE);
                regusernamelabel.setForeground(Color.WHITE);
                regpasswordlabel.setForeground(Color.WHITE);
                registeacher.setOpaque(false);
                registeacher.setForeground(Color.WHITE);
                studentlabel.setForeground(Color.WHITE);

                regframe.add(regpanel);
                regpanel.add(reglabel);
                regpanel.add(regnamelabel);
                regpanel.add(regusernamelabel);
                regpanel.add(regpasswordlabel);
                regpanel.add(regname);
                regpanel.add(regusername);
                regpanel.add(regpassword);
                regpanel.add(registeacher);
                regpanel.add(studentlabel);
                // regpanel.add(pluto2pic);
                regpanel.add(regbutton);
                regpanel.add(backbutton);
                regpanel.add(realplutopic);

                s2.putConstraint(SpringLayout.HORIZONTAL_CENTER, reglabel, 5, SpringLayout.HORIZONTAL_CENTER, regpanel);
                s2.putConstraint(SpringLayout.WEST, regnamelabel, 10, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, regnamelabel, 55, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, regname, 10, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, regname, 75, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, regusernamelabel, 10, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, regusernamelabel, 100, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, regusername, 10, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, regusername, 120, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, regpasswordlabel, 10, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, regpasswordlabel, 145, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, regpassword, 10, SpringLayout.WEST, panel);
                s2.putConstraint(SpringLayout.NORTH, regpassword, 165, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, registeacher, 10, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, registeacher, 190, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, studentlabel, 70, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, studentlabel, 194, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.EAST, pluto2pic, -20, SpringLayout.EAST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, pluto2pic, 10, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, regbutton, 10, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, regbutton, 225, SpringLayout.NORTH, regpanel);
                s2.putConstraint(SpringLayout.WEST, backbutton, 125, SpringLayout.WEST, regpanel);
                s2.putConstraint(SpringLayout.NORTH, backbutton, 225, SpringLayout.NORTH, regpanel);

                // VISSZA GOMB ESEMÉNYEI
                backbutton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        regframe.setVisible(false);
                        new PlutoFrame();
                    }
                });

                // REGISZTRÁL GOMB ESEMÉNYEI
                regbutton.addActionListener(new ActionListener() {

                    @Override
                    public void actionPerformed(ActionEvent e) {
                        JFrame userdb = new JFrame();

                        userdb.setVisible(false);
                        userdb.setDefaultCloseOperation(EXIT_ON_CLOSE);
                        userdb.setTitle("Felhasználók");
                        userdb.setSize(600, 400);
                        userdb.setLocationRelativeTo(null);
                        userdb.setResizable(false);

                        if (regname.getText().isEmpty() || regusername.getText().isEmpty() || getCurrentPassWord(regpassword.getPassword()).isEmpty()) {
                            JOptionPane.showMessageDialog(regframe, "Nem töltötte ki valamelyik opciót!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                        } else if (alreadyExist()) {
                            JOptionPane.showMessageDialog(regframe, "Ön már szerepel az adatbázisba", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            PlutoUser newuser = new PlutoUser();
                            int id = userModel.getRowCount();
                            newuser.setId(id + 1);
                            newuser.setName(regname.getText());
                            newuser.setUsername(regusername.getText());
                            char[] pw = regpassword.getPassword();
                            newuser.setPassoword(getCurrentPassWord(pw));
                            if (registeacher.isSelected()) {
                                newuser.setTeacher(true);
                            }
                            newuser.setOnline(false);
                            DataSource.getInstance().getUserController().create(newuser);
                            userModel.fireTableDataChanged();
                            JOptionPane.showMessageDialog(regframe, "Köszönjük, hogy regisztrált, most már beléphet a rendszerbe.", "Információ", JOptionPane.INFORMATION_MESSAGE);
                            //userdb.setVisible(true);
                        }

                        //userdb.add(jtabPane);
                    }
                });

            }
        });

        // ###########  BELÉPÉS GOMB ESEMÉNYE  ##########
        login.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (username.getText().isEmpty() || getCurrentPassWord(password.getPassword()).isEmpty()) {
                    JOptionPane.showMessageDialog(regframe, "Nem töltötte ki valamelyik opciót!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else {

                    int dbsize = userModel.getRowCount();

                    for (int i = 0; i < dbsize; i++) {
                        PlutoUser compareuser = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
                        if (username.getText().equals(compareuser.getUsername()) && getCurrentPassWord(password.getPassword()).equals(compareuser.getPassoword())) {
                            if (isteacher.isSelected() && compareuser.isTeacher()) {
                                teacherGui();
                                System.out.println("Tanár belépett.");

                            } else if (isteacher.isSelected() && compareuser.isTeacher() == false) {
                                System.out.println("Te nem tanár vagy.");
                                JOptionPane.showMessageDialog(regframe, "Ön diák, nem tanár!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                            } else if (isteacher.isSelected() == false && compareuser.isTeacher()) {
                                System.out.println("Ön tanár, nem diák.");
                                JOptionPane.showMessageDialog(regframe, "Ön tanár, nem diák!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                userModel.turnOnlie(compareuser);
                                studentGui();
                                System.out.println("Diák belépett.");
                                currentusername = username.getText();
                                userModel.fireTableDataChanged();

                            }

                        }

                    }
                    if (wrongDetails()) {
                        JOptionPane.showMessageDialog(regframe, "Hibás adatok! Ha még nem regisztrált a regisztráció gomb segítségével megteheti.", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                    }

                }
            }
        });
    }

    private String getCurrentPassWord(char[] pw) {
        String currentpw = "";
        for (int i = 0; i < pw.length; i++) {
            currentpw += pw[i];

        }
        return currentpw;
    }

    private boolean alreadyExist() {
        boolean same = false;
        int dbsize = userModel.getRowCount();
        for (int i = 0; i < dbsize; i++) {
            PlutoUser compareuser = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (regusername.getText().equals(compareuser.getName())) {
                same = true;
            }
        }

        return same;

    }

    private boolean wrongDetails() {
        boolean wrong = false;
        int dbsize = userModel.getRowCount();
        int all = 0;
        for (int i = 0; i < dbsize; i++) {
            PlutoUser compareuser = DataSource.getInstance().getUserController().findPlutoUserEntities().get(i);
            if (!(username.getText().equals(compareuser.getUsername())) || (!getCurrentPassWord(password.getPassword()).equals(compareuser.getPassoword()))) {
                all++;
            }

        }
        if (all == dbsize) {
            wrong = true;
        }
        return wrong;
    }

    public void teacherGui() {
        pluto.setVisible(false);
        teacherframe.setVisible(true);
        teacherframe.setTitle("Plutó hallgatói tanulmányirendszer - TANÁR");
        teacherframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        teacherframe.setSize(400, 205);
        teacherframe.setLocationRelativeTo(null);
        teacherframe.setResizable(false);
        teacherpanel.setBackground(Color.WHITE);
        SpringLayout s4 = new SpringLayout();
        teacherpanel.setLayout(s4);

        teacherframe.add(teacherpanel);
        teacherpanel.add(addsubjectbutton);
        teacherpanel.add(activstudentsbutton);
        //teacherpanel.add(framepic);
        teacherpanel.add(teacherpic);
        teacherpanel.add(exitbutton);
        teacherpanel.add(removesubject);

        s4.putConstraint(SpringLayout.WEST, teacherpic, 0, SpringLayout.WEST, teacherpanel);
        s4.putConstraint(SpringLayout.NORTH, teacherpic, 0, SpringLayout.NORTH, teacherpanel);
        s4.putConstraint(SpringLayout.WEST, addsubjectbutton, 5, SpringLayout.WEST, teacherpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubjectbutton, 145, SpringLayout.NORTH, teacherpanel);
        s4.putConstraint(SpringLayout.WEST, activstudentsbutton, 265, SpringLayout.WEST, teacherpanel);
        s4.putConstraint(SpringLayout.NORTH, activstudentsbutton, 145, SpringLayout.NORTH, teacherpanel);
        s4.putConstraint(SpringLayout.WEST, exitbutton, 317, SpringLayout.WEST, teacherpanel);
        s4.putConstraint(SpringLayout.NORTH, exitbutton, 0, SpringLayout.NORTH, teacherpanel);
        s4.putConstraint(SpringLayout.WEST, removesubject, 150, SpringLayout.WEST, teacherpanel);
        s4.putConstraint(SpringLayout.NORTH, removesubject, 145, SpringLayout.NORTH, teacherpanel);

        activstudentsbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (userModel.currentstudent().length == 0) {
                    JOptionPane.showMessageDialog(teacherframe, "Nincs aktív hallgató az adatbázisban!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else {
                    Object[] currentstudent = userModel.currentstudent();
                    String s = (String) JOptionPane.showInputDialog(
                            teacherframe,
                            "Válasszon hallgatót: ",
                            "Hallgatók",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            currentstudent,
                            currentstudent[0]);

                    if (s != null) {
                        JFrame studsubject = new JFrame();
                        studsubject.setVisible(true);
                        studsubject.setTitle(s + " hallgató tárgyai");
                        studsubject.setSize(1000, 400);
                        JPanel jp = new JPanel(new BorderLayout());
                        studsubject.add(jp);

                        String[] strarray = s.split("-");

                        JTable jt = new JTable(new AbstractTableModel() {
                            PlutoUser selecteduser = userModel.getThisUser(strarray[1]);

                            @Override
                            public int getRowCount() {
                                return selecteduser.getSubjects().size();
                            }

                            @Override
                            public int getColumnCount() {
                                return subModel.getColumnCount();
                            }
                            
                            @Override
                            public boolean isCellEditable(int rowIndex, int columnIndex) {
                                return columnIndex == 6;
                            }

                            @Override
                            public Object getValueAt(int rowIndex, int columnIndex) {
                                Subject subjects = selecteduser.getSubjects().get(rowIndex);
                                switch (columnIndex) {
                                    case 0:
                                        return subjects.getSubjectName();
                                    case 1:
                                        return subjects.getRoom();
                                    case 2:
                                        return subjects.getTime();
                                    case 3:
                                        return subjects.getRegistered_sem();
                                    case 4:
                                        return subjects.getRequirement();
                                    case 5:
                                        return subjects.getTeacherName();
                                    case 6:
                                        return subjects.isFulfilled();
                                    default:
                                        return null;
                                }
                            }

                            

                            @Override
                            public Class<?> getColumnClass(int columnIndex) {
                                switch (columnIndex) {
                                    case 0:
                                        return String.class;
                                    case 1:
                                        return String.class;
                                    case 2:
                                        return String.class;
                                    case 3:
                                        return Integer.class;
                                    case 4:
                                        return Subject.class;
                                    case 5:
                                        return String.class;
                                    default:
                                        return Boolean.class;
                                }
                            }

                            @Override
                            public String getColumnName(int column) {
                                return subModel.getColumnName(column);
                            }
                            @Override
                            public void setValueAt(Object inValue, int inRow, int inCol) {
                                Subject selectedsubject = selecteduser.getSubjects().get(inRow);
                                
                                if (selectedsubject.isFulfilled()) {
                                    selectedsubject.setFulfilled(false);

                                } else {
                                    selectedsubject.setFulfilled(true);
                                }

                                try {
                                    DataSource.getInstance().getSubjectController().edit(selectedsubject);
                                } catch (Exception ex) {
                                    Logger.getLogger(PlutoFrame.class.getName()).log(Level.SEVERE, null, ex);
                                }
                                subModel.fireTableCellUpdated(inRow, inCol);
                            }

                        });
                        jp.add(add(new JScrollPane(jt)), BorderLayout.CENTER);
                    }

                }
            }

        });

        exitbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                teacherframe.dispose();
                JFrame start = new PlutoFrame();
            }
        });

        removesubject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (subModel.getRowCount() == 0) {
                    JOptionPane.showMessageDialog(teacherframe, "Nincs még tantárgy!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else {
                    subdeleteGui();
                    subModel.fireTableDataChanged();
                }

            }

        });

        addsubjectbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                addsubjectGui();
                teacherframe.setVisible(false);

            }
        });

    }

    /* private void activeStudentGui() {
     JFrame astudframe = new JFrame();
     astudframe.setVisible(true);
     astudframe.setSize(400, 200);
     JPanel aspanel = new JPanel(new BorderLayout());
     astudframe.add(aspanel);
     JTable studentTable = new JTable(userModel);
     aspanel.add(add(new JScrollPane(studentTable)), BorderLayout.CENTER);
     TableColumn delete = studentTable.getColumn("Password");
     studentTable.removeColumn(delete);
     }*/
    public void subdeleteGui() {
        subdetailsframe = new JFrame();
        subdetailsframe.setVisible(true);
        subdetailsframe.setTitle("Plutó hallgatói tanulmányirendszer - TANÁR");
        subdetailsframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        subdetailsframe.setSize(400, 205);
        subdetailsframe.setLocationRelativeTo(null);
        subdetailsframe.setResizable(false);
        subdet = new JPanel();
        subdet.setBackground(Color.WHITE);
        SpringLayout s5 = new SpringLayout();
        currentids = subModel.currentSubjectsId();
        comboId = new JComboBox<>(currentids);
        comboId.setSelectedIndex(0);
        currentsubname = new JLabel("");
        currentsubroom = new JLabel("");
        currentsubtime = new JLabel("");
        currentsubteacher = new JLabel("");

        subdet.setLayout(s5);
        subdetailsframe.add(subdet);
        subdet.add(subdetname);
        subdet.add(subdetroom);
        subdet.add(subdettime);
        subdet.add(comboId);
        subdet.add(subdetmain);
        subdet.add(subdetteacher);
        subdet.add(currentsubname);
        subdet.add(currentsubroom);
        subdet.add(currentsubtime);
        subdet.add(currentsubteacher);
        subdet.add(delete);
        subdet.add(cancel);
        subdet.add(classboard);

        s5.putConstraint(SpringLayout.NORTH, classboard, 0, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetmain, 120, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetmain, 20, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetname, 20, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetname, 55, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetroom, 20, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetroom, 75, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdettime, 20, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdettime, 115, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, comboId, 250, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, comboId, 20, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetteacher, 20, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetteacher, 95, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubname, 115, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubname, 55, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubroom, 115, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubroom, 75, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubtime, 115, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubtime, 115, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubteacher, 115, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubteacher, 95, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, delete, 20, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, delete, 140, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, cancel, 140, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, cancel, 140, SpringLayout.NORTH, subdet);

        comboId.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (e.getSource() == comboId) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String ids = (String) cb.getSelectedItem();
                    String[] seged = delsubDetalis(ids);
                    currentsubname.setText(seged[0]);
                    currentsubroom.setText(seged[1]);
                    currentsubtime.setText(seged[2]);
                    currentsubteacher.setText(seged[3]);

                }

            }
        });

        cancel.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                subdetailsframe.dispose();
                teacherframe.setVisible(true);
            }

        });

        delete.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                String ids = (String) comboId.getSelectedItem();
                int realid = Integer.parseInt(ids);
                System.out.println(subModel.getmySubject(realid));
                
                if (comboId.getItemCount() < 1) {
                    subdetailsframe.dispose();
                    JOptionPane.showMessageDialog(teacherframe, "Nincs több tantárgy!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                }
                
                else{
                    ids = (String) comboId.getSelectedItem();
                    realid = Integer.parseInt(ids);
                    System.out.println(subModel.getmySubject(realid)+" rfje: "+subModel.getmySubject(realid).getRequirement());
                    List<PlutoUser> theyhaveit = userModel.theyHaveThisSubject(realid);

                    for (int i = 0; i < subModel.getRowCount(); i++) {
                        Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);

                        if (sub.getId() == realid) {
                            if (theyhaveit.isEmpty()) {
                                subModel.deleteThisSubject(Integer.parseInt(comboId.getSelectedItem().toString()));
                                comboId.removeItem(comboId.getSelectedItem());
                                subModel.fireTableDataChanged();
                            } else {
                                for (int j = 0; j < theyhaveit.size(); j++) {
                                    userModel.updateHisList(theyhaveit.get(j), sub.getId());
                                    subModel.fireTableDataChanged();
                                    userModel.fireTableDataChanged();
                                }
                                subModel.deleteThisSubject(realid);
                                comboId.removeItem(comboId.getSelectedItem());
                            }

                        }

                    }

                }

            }
        });

    }

    public String[] delsubDetalis(String id) {
        String[] det = new String[4];
        for (int i = 0; i < subModel.getRowCount(); i++) {
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (sub.getId() == Integer.parseInt(id)) {
                det[0] = sub.getSubjectName();
                det[1] = sub.getRoom();
                det[2] = sub.getTime();
                det[3] = sub.getTeacherName();

            }
        }

        return det;
    }

    public boolean alreadyExistSub() {
        boolean exist = false;
        int size = subModel.getRowCount();
        int i=0;
        while(i<size){
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if(sub.getSubjectName().equals(addsubname.getText()) && roomIsTaken() && teacherIsTeaching()){
                exist = true;   
            }
            i++;
        }
        return exist;

    }

    public boolean roomIsTaken() {
        boolean taken = false;
        int size = subModel.getRowCount();
        int i=0;
        while(i<size){
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (addsubroom.getText().equals(sub.getRoom()) && getSubjectTime().equals(sub.getTime())) {
                taken = true;
            }
            i++;
        }
        
        return taken;

    }

    public boolean teacherIsTeaching() {
        boolean teach = false;
        int size = subModel.getRowCount();
        int i=0;
        
        while(i<size){
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (comboTeacher.getSelectedItem().toString().equals(sub.getTeacherName()) && getSubjectTime().equals(sub.getTime())) {
                teach = true;
            }
            i++;
        }
        return teach;
    }

    public int getSelectedSemester() {
        int sem = 0;

        if (comboSemester.getSelectedItem().toString().equals("1.félév")) {
            sem = 1;
        } else if (comboSemester.getSelectedItem().toString().equals("2.félév")) {
            sem = 2;
        } else if (comboSemester.getSelectedItem().toString().equals("3.félév")) {
            sem = 3;
        } else if (comboSemester.getSelectedItem().toString().equals("4.félév")) {
            sem = 4;
        } else if (comboSemester.getSelectedItem().toString().equals("5.félév")) {
            sem = 5;
        } else {
            sem = 6;
        }

        return sem;

    }

    public String getSubjectTime() {
        return comboDay.getSelectedItem() + " " + comboHour.getSelectedItem();
    }

    public Subject getReqSubject(String name) {
        Subject reqsub = new Subject();
        for (int i = 0; i < subModel.getRowCount(); i++) {
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (sub.getSubjectName().equals(name)) {
                reqsub = sub;
            }

        }
        return reqsub;
    }

}
