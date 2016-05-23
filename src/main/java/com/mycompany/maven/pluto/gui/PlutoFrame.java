/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.mycompany.maven.pluto.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SpringLayout;
import javax.swing.table.AbstractTableModel;
import com.mycompany.maven.pluto.gui.tablemodel.SubjectTableModel;
import com.mycompany.maven.pluto.gui.tablemodel.UserTableModel;
import com.mycompany.maven.pluto.logic.DataSource;
import com.mycompany.maven.pluto.logic.entities.PlutoUser;
import com.mycompany.maven.pluto.logic.entities.Subject;
import java.net.URL;

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
    //ImageIcon plutojpg = createImageIcon("pluto.jpg"));
    //JLabel plutopic = new JLabel(plutojpg);
    JCheckBox isteacher = new JCheckBox("Tanár");
    JButton login = new JButton("Belépés");
    JButton registration = new JButton("Regisztráció");
    ImageIcon realpluto = createImageIcon("images/plutoreal.jpg","");
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

    ImageIcon studentjpg = createImageIcon("images/student.jpg","");
    JLabel studentpic = new JLabel(studentjpg);

    //TANÁR FRAME KOMPONENSEI
    JFrame teacherframe = new JFrame();
    JButton addsubjectbutton = new JButton("Tárgy hozzáadása");
    JButton activstudentsbutton = new JButton("Hallgatók listája");
    JButton exitbutton = new JButton("Kilépés");
    JPanel teacherpanel = new JPanel();

    ImageIcon img2 = createImageIcon("images/teacherdesk.jpg","");
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
    ImageIcon board2 = createImageIcon("images/teacherdesk.jpg","");
    JLabel classboard2 = new JLabel(board2);
    JComboBox<String> comboSemester;
    String[] comboTeacherarray;
    JComboBox<String> comboTeacher;
    JPanel addpanel = new JPanel();
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
    // JButton cancel = new JButton("Mégse");
    JLabel subdetmain = new JLabel("Válasszon tantárgyat:");
    String[] currentids;
    JComboBox<String> comboId;
    JFrame subdetailsframe;
    JPanel subdet = new JPanel();
    ImageIcon board = createImageIcon("images/teacherdesk.jpg","");
    JLabel classboard = new JLabel(board);

    JLabel reqsublabel = new JLabel("Előfeltétel:");
    String[] comboRequiredarray;
    JComboBox<String> comboReqsub;

    JFrame mysubframe;
    JPanel mysubp;

    JButton mysubject = new JButton("Felvett tárgyak");
    JPanel subdet2 = new JPanel();

    JButton myfulfilledbutton = new JButton("Teljesített tárgyaim");
    JFrame myfullsubframe;
    JPanel myfullsubp;

    JComboBox<String> comboId2;

    JList list;
    PlutoUser doneuser;

    JLabel currentsubname2;
    JLabel currentsubroom2;
    JLabel currentsubtime2;
    JLabel currentsubteacher2;

    JButton addsubtomylist;

    public PlutoFrame() {
        gui();
    }

    public void studentGui() {
        pluto.setVisible(false);
        studentframe.setVisible(true);
        studentframe.setTitle("Plutó hallgatói tanulmányirendszer - DIÁK");
        studentframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        studentframe.setSize(510, 300);
        studentframe.setResizable(false);
        studentframe.setLocationRelativeTo(null);
        studentpanel = new JPanel();

        SpringLayout l3 = new SpringLayout();
        studentpanel.setLayout(l3);

        JTabbedPane stpane = new JTabbedPane();
        stpane.addTab("Diák", studentpanel);
        stpane.addTab("Tárgyfelvétel", subdet2);

        studentframe.add(stpane);
        studentpanel.add(scedule);
        studentpanel.add(studentexit);
        studentpanel.add(removesub);
        studentpanel.add(mysubject);
        studentpanel.add(myfulfilledbutton);
        studentpanel.add(studentpic);

        l3.putConstraint(SpringLayout.NORTH, studentpic, 0, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, scedule, 95, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, scedule, 5, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, studentexit, 5, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, studentexit, 420, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, removesub, 35, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, removesub, 5, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.WEST, mysubject, 5, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, mysubject, 5, SpringLayout.NORTH, studentpanel);
        l3.putConstraint(SpringLayout.WEST, myfulfilledbutton, 5, SpringLayout.WEST, studentpanel);
        l3.putConstraint(SpringLayout.NORTH, myfulfilledbutton, 65, SpringLayout.NORTH, studentpanel);

        studentexit.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

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

// ********************************************* TÁRGY FELVÉTEL ************************************************  
        SpringLayout s1 = new SpringLayout();
        String[] currentids2 = subModel.currentSubjectsId();
        comboId2 = new JComboBox<>(currentids2);
        currentsubname2 = new JLabel("");
        currentsubroom2 = new JLabel("");
        currentsubtime2 = new JLabel("");
        currentsubteacher2 = new JLabel("");
        currentsubname2.setForeground(Color.white);
        currentsubroom2.setForeground(Color.white);
        currentsubteacher2.setForeground(Color.white);
        currentsubtime2.setForeground(Color.white);
        subdetname.setForeground(Color.white);
        subdetroom.setForeground(Color.white);
        subdettime.setForeground(Color.white);
        subdetteacher.setForeground(Color.white);
        subdetmain.setForeground(Color.white);
        addsubtomylist = new JButton("Felvesz");

        subdet2.setLayout(s1);
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
        subdet2.add(classboard);

        s1.putConstraint(SpringLayout.NORTH, classboard, 0, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, subdetmain, 250, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, subdetmain, 20, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, subdetname, 30, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, subdetname, 55, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, subdetroom, 30, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, subdetroom, 75, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, subdettime, 30, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, subdettime, 115, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, comboId2, 380, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, comboId2, 20, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, subdetteacher, 30, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, subdetteacher, 95, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, currentsubname2, 125, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, currentsubname2, 55, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, currentsubroom2, 125, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, currentsubroom2, 75, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, currentsubtime2, 125, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, currentsubtime2, 115, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, currentsubteacher2, 125, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, currentsubteacher2, 95, SpringLayout.NORTH, subdet2);
        s1.putConstraint(SpringLayout.WEST, addsubtomylist, 200, SpringLayout.WEST, subdet2);
        s1.putConstraint(SpringLayout.NORTH, addsubtomylist, 160, SpringLayout.NORTH, subdet2);

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

        addsubtomylist.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (comboId2.getItemCount() < 1) {

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
                                JOptionPane.showMessageDialog(studentframe, "Ezt a tárgyat már felvetted!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                                comboId2.removeItem(comboId2.getSelectedItem());
                            } else {

                                Subject selectedsub = subModel.getmySubject(realid);
                                if (userModel.requirementCheck(selectedsub, currentusername)) {
                                    userModel.addSubjectToMyList(currentusername, subModel.getmySubject(realid));
                                    subModel.fireTableDataChanged();
                                    userModel.fireTableDataChanged();
                                    comboId2.removeItem(comboId2.getSelectedItem());
                                    JOptionPane.showMessageDialog(studentframe, "Sikeres tárgyfelvétel");

                                } else {
                                    JOptionPane.showMessageDialog(studentframe, "Nem teljesült a tárgy előfeltétele!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                                }

                            }
                        }

                    }

                }

            }

        });

 //************************************************************** TÁRGY FELVÉTEL VÉGE ****************************************************************      
        //*************************************************************** FELVETT TÁRGYAIM ********************************************************
        mysubject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PlutoUser user = userModel.getThisUser(currentusername);
                if (user.getSubjects().isEmpty()) {
                    JOptionPane.showMessageDialog(studentframe, "Nincs fevett tárgyad!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else {
                    mysubframe = new JFrame();
                    mysubframe.setVisible(true);
                    mysubframe.setTitle("Felvett tantárgyak");
                    mysubframe.setSize(1300, 300);
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
                                    return subjects.getSubDay();
                                case 3:
                                    return subjects.getSubHour();
                                case 4:
                                    return subjects.getRegistered_sem();
                                case 5:
                                    return subjects.getRequirement();
                                case 6:
                                    return subjects.getTeacherName();
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
                                    return String.class;
                                case 4:
                                    return Integer.class;
                                case 5:
                                    return Subject.class;
                                default:
                                    return String.class;
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

            }
        });

        //*************************************************************** FELVETT TÁRGYAIM ********************************************************
        removesub.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                PlutoUser student = userModel.getThisUser(currentusername);
                Object[] mysubjects = userModel.getMySubjectArray(student.getSubjects());

                if (student.getSubjects().isEmpty()) {
                    JOptionPane.showMessageDialog(studentframe, "Nincs felvett tárgyad!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else {
                    String selectedsub = (String) JOptionPane.showInputDialog(
                            mysubframe,
                            "Felvett tárgyak",
                            "Tárgy leadása",
                            JOptionPane.PLAIN_MESSAGE,
                            null,
                            mysubjects,
                            null);

                    if (selectedsub != null) {
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
                        JOptionPane.showMessageDialog(mysubframe, "Sikeres lejelentkezés!");
                        String[] currentids2 = subModel.currentSubjectsId();
                        comboId2.setModel(new DefaultComboBoxModel<>(currentids2));

                    }
                }

            }

        });

        scedule.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame sceduleframe = new JFrame();
                sceduleframe.setVisible(true);
                sceduleframe.setTitle("Órarend");
                sceduleframe.setSize(1300, 220);

                Object[] columnname = {"Órarend", "Hétfő", "Kedd", "Szerda", "Csütörtök", "Péntek"};
                Object[][] data = new Object[10][6];

                PlutoUser user = userModel.getThisUser(currentusername);

                for (int i = 0; i < 10; i++) {
                    for (int j = 0; j < 6; j++) {
                        data[i][0] = hour[i];
                        for (int x = 0; x < user.getSubjects().size(); x++) {
                            if (user.getSubjects().get(x).getSubHour().equals(data[i][0]) && user.getSubjects().get(x).getSubDay().equals(columnname[j])) {
                                data[i][j] = user.getSubjects().get(x).getSubjectName();
                                
                            }
                        }
                    }
                }
                
                

                JTable orarend = new JTable(data, columnname);
                orarend.setPreferredScrollableViewportSize(new Dimension(450, 63));
                orarend.setFillsViewportHeight(true);
                JScrollPane scp = new JScrollPane(orarend);
                sceduleframe.add(scp);

            }

        });

        myfulfilledbutton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PlutoUser auser = userModel.getThisUser(currentusername);
                if (auser.getFulfilledsubids() == null) {
                    JOptionPane.showMessageDialog(sudentpanel, "Nincs teljesített  tantárgyad!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else {
                    myfullsubframe = new JFrame();
                    myfullsubframe.setVisible(true);
                    myfullsubframe.setTitle("Teljesített tantárgyak");
                    myfullsubframe.setSize(1300, 300);
                    myfullsubp = new JPanel(new BorderLayout());

                    JTable myfullsubtable = new JTable(new AbstractTableModel() {
                        PlutoUser thisuser = userModel.getThisUser(currentusername);

                        @Override
                        public int getRowCount() {
                            return subModel.getThisUserFulfilledSubject(thisuser).size();
                        }

                        @Override
                        public int getColumnCount() {
                            return subModel.getColumnCount();
                        }

                        @Override
                        public Object getValueAt(int rowIndex, int columnIndex) {
                            Subject subjects = subModel.getThisUserFulfilledSubject(thisuser).get(rowIndex);
                            switch (columnIndex) {
                                case 0:
                                    return subjects.getSubjectName();
                                case 1:
                                    return subjects.getRoom();
                                case 2:
                                    return subjects.getSubDay();
                                case 3:
                                    return subjects.getSubHour();
                                case 4:
                                    return subjects.getRegistered_sem();
                                case 5:
                                    return subjects.getRequirement();
                                case 6:
                                    return subjects.getTeacherName();
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
                                    return String.class;
                                case 4:
                                    return Integer.class;
                                case 5:
                                    return Subject.class;
                                default:
                                    return String.class;

                            }
                        }

                        @Override
                        public String getColumnName(int column) {
                            return subModel.getColumnName(column);
                        }
                    });
                    myfullsubframe.add(myfullsubp);
                    myfullsubp.add(add(new JScrollPane(myfullsubtable)), BorderLayout.CENTER);

                }

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
        SpringLayout s2 = new SpringLayout();
        panel.setLayout(s2);

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
        s2.putConstraint(SpringLayout.EAST, realplutopic, 0, SpringLayout.EAST, panel);
        s2.putConstraint(SpringLayout.NORTH, realplutopic, 0, SpringLayout.NORTH, panel);
        s2.putConstraint(SpringLayout.HORIZONTAL_CENTER, mainlabel, 5, SpringLayout.HORIZONTAL_CENTER, panel);
        s2.putConstraint(SpringLayout.WEST, userlabel, 10, SpringLayout.WEST, panel);
        s2.putConstraint(SpringLayout.NORTH, userlabel, 36, SpringLayout.NORTH, panel);
        s2.putConstraint(SpringLayout.WEST, username, 10, SpringLayout.WEST, panel);
        s2.putConstraint(SpringLayout.NORTH, username, 54, SpringLayout.NORTH, panel);
        s2.putConstraint(SpringLayout.WEST, passwlabel, 10, SpringLayout.WEST, panel);
        s2.putConstraint(SpringLayout.NORTH, passwlabel, 78, SpringLayout.NORTH, panel);
        s2.putConstraint(SpringLayout.WEST, password, 10, SpringLayout.WEST, panel);
        s2.putConstraint(SpringLayout.NORTH, password, 95, SpringLayout.NORTH, panel);
        s2.putConstraint(SpringLayout.WEST, isteacher, 6, SpringLayout.WEST, panel);
        s2.putConstraint(SpringLayout.NORTH, isteacher, 120, SpringLayout.NORTH, panel);
        s2.putConstraint(SpringLayout.WEST, login, 10, SpringLayout.WEST, panel);
        s2.putConstraint(SpringLayout.NORTH, login, 150, SpringLayout.NORTH, panel);
        s2.putConstraint(SpringLayout.WEST, registration, 100, SpringLayout.WEST, panel);
        s2.putConstraint(SpringLayout.NORTH, registration, 150, SpringLayout.NORTH, panel);

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
                                pluto.setVisible(false);
                                teacherGui();
                                //pluto.dispatchEvent(new WindowEvent(pluto, WindowEvent.WINDOW_CLOSING));

                            } else if (isteacher.isSelected() && compareuser.isTeacher() == false) {

                                JOptionPane.showMessageDialog(regframe, "Ön diák, nem tanár!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                            } else if (isteacher.isSelected() == false && compareuser.isTeacher()) {

                                JOptionPane.showMessageDialog(regframe, "Ön tanár, nem diák!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                            } else {
                                userModel.turnOnlie(compareuser);
                                pluto.setVisible(false);
                                studentGui();

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
        teacherframe.setVisible(true);
        teacherframe.setTitle("Plutó hallgatói tanulmányirendszer - TANÁR");
        teacherframe.setDefaultCloseOperation(EXIT_ON_CLOSE);
        teacherframe.setSize(520, 410);
        teacherframe.setLocationRelativeTo(null);
        teacherframe.setResizable(false);
        teacherpanel.setBackground(Color.WHITE);
        SpringLayout s3 = new SpringLayout();
        teacherpanel.setLayout(s3);
        currentids = subModel.currentSubjectsId();
        comboId = new JComboBox<>(currentids);

        JTabbedPane teachertab = new JTabbedPane();
        teachertab.addTab("Tanár", teacherpanel);
        teachertab.addTab("Tárgy hozzáadása", addpanel);
        teachertab.addTab("Tárgy törlése", subdet);

        teacherframe.add(teachertab);
        teacherpanel.add(exitbutton);
        teacherpanel.add(activstudentsbutton);
        teacherpanel.add(teacherpic);

        s3.putConstraint(SpringLayout.WEST, teacherpic, 0, SpringLayout.WEST, teacherpanel);
        s3.putConstraint(SpringLayout.WEST, exitbutton, 425, SpringLayout.WEST, teacherpanel);
        s3.putConstraint(SpringLayout.NORTH, exitbutton, 5, SpringLayout.NORTH, teacherpanel);
        s3.putConstraint(SpringLayout.WEST, activstudentsbutton, 5, SpringLayout.WEST, teacherpanel);
        s3.putConstraint(SpringLayout.NORTH, activstudentsbutton, 5, SpringLayout.NORTH, teacherpanel);

        //*********************************** TÁRGY HOZZÁADÁSA **********************************************************  
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

        SpringLayout s4 = new SpringLayout();
        addpanel.setLayout(s4);

        addsubnamelabel.setForeground(Color.white);
        addsubregsemlabel.setForeground(Color.white);
        addsubroomlabel.setForeground(Color.white);
        addsubtimelabel.setForeground(Color.white);
        addsubreqlabel.setForeground(Color.white);
        reqsublabel.setForeground(Color.white);
        teacherlabel.setForeground(Color.white);

        addpanel.add(addsubnamelabel);
        addpanel.add(addsubregsemlabel);
        addpanel.add(addsubroomlabel);
        addpanel.add(addsubtimelabel);
        addpanel.add(addsubname);
        addpanel.add(comboSemester);
        addpanel.add(reqsublabel);
        addpanel.add(comboReqsub);
        addpanel.add(addsubroom);

        addpanel.add(comboDay);
        addpanel.add(comboHour);
        addpanel.add(reqbox);
        addpanel.add(teacherlabel);
        addpanel.add(comboTeacher);
        addpanel.add(addsubject);

        addpanel.add(classboard2);

        s4.putConstraint(SpringLayout.WEST, classboard2, 0, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.WEST, addsubnamelabel, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubnamelabel, 25, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, addsubname, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubname, 45, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, addsubroomlabel, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubroomlabel, 66, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, addsubroom, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubroom, 85, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, addsubregsemlabel, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubregsemlabel, 108, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, comboSemester, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, comboSemester, 125, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, addsubtimelabel, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubtimelabel, 155, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, comboDay, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, comboDay, 172, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, comboHour, 125, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, comboHour, 172, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, teacherlabel, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, teacherlabel, 200, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, reqsublabel, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, reqsublabel, 250, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, comboTeacher, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, comboTeacher, 220, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, comboReqsub, 30, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, comboReqsub, 270, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, reqbox, 90, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, reqbox, 250, SpringLayout.NORTH, addpanel);
        s4.putConstraint(SpringLayout.WEST, addsubject, 220, SpringLayout.WEST, addpanel);
        s4.putConstraint(SpringLayout.NORTH, addsubject, 320, SpringLayout.NORTH, addpanel);

        reqbox.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                if (reqbox.isSelected()) {

                    comboReqsub.setVisible(true);
                } else {

                    comboReqsub.setVisible(false);
                }

            }
        });

        comboReqsub.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                subModel.fireTableDataChanged();
                if (e.getSource() == comboReqsub) {
                    JComboBox cb = (JComboBox) e.getSource();
                    String ids = (String) cb.getSelectedItem();

                    reqsub = getReqSubject(ids);
                }

            }
        });

        addsubject.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {

                if (addsubname.getText().isEmpty() || addsubroom.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(addsubframe, "Nem töltött mindent ki.", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else if (roomIsTaken()) {
                    JOptionPane.showMessageDialog(addsubframe, "A terem foglalt erre az időpontra!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else if (alreadyExistSub()) {
                    JOptionPane.showMessageDialog(addsubframe, "Már létezik ez a kurzus ebben az időpontban!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else if (teacherIsTeaching()) {
                    JOptionPane.showMessageDialog(addsubframe, "A kiválasztott tanár, ebben az időpontban már tanít!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);

                } else {
                    Subject newsub = new Subject();
                    int id = newsub.hashCode();
                    newsub.setId(id);
                    newsub.setSubjectName(addsubname.getText());
                    newsub.setRoom(addsubroom.getText());
                    newsub.setSubDay(comboDay.getSelectedItem().toString());
                    newsub.setSubHour(comboHour.getSelectedItem().toString());
                    newsub.setTeacherName(comboTeacher.getSelectedItem().toString());
                    if (reqbox.isSelected()) {
                        newsub.setRequirement(reqsub);
                    } else {
                        newsub.setRequirement(null);
                    }
                    newsub.setRegistered_sem(getSelectedSemester());

                    DataSource.getInstance().getSubjectController().create(newsub);
                    JOptionPane.showMessageDialog(addsubframe, "Tantárgy hozzáadva!", "Információ", JOptionPane.INFORMATION_MESSAGE);
                    subModel.fireTableDataChanged();

                    currentids = subModel.currentSubjectsId();
                    comboId.setModel(new DefaultComboBoxModel<>(currentids));
                    comboRequiredarray = (String[]) subModel.currentSubjects();
                    comboReqsub.setModel(new DefaultComboBoxModel<>(comboRequiredarray));
                }

            }

        });

   //*********************************** TÁRGY HOZZÁADÁSA VÉGE **********************************************************         
        //******************************** TÁRGY TÖRLÉSE  *************************************************   
        // subModel.fireTableDataChanged();
        currentids = subModel.currentSubjectsId();
        comboId = new JComboBox<>(currentids);
        currentsubname = new JLabel("");
        currentsubroom = new JLabel("");
        currentsubtime = new JLabel("");
        currentsubteacher = new JLabel("");
        currentsubname.setForeground(Color.white);
        currentsubroom.setForeground(Color.white);
        currentsubtime.setForeground(Color.white);
        currentsubteacher.setForeground(Color.white);
        subdetmain.setForeground(Color.white);
        subdetname.setForeground(Color.white);
        subdetroom.setForeground(Color.white);
        subdetteacher.setForeground(Color.white);
        subdettime.setForeground(Color.white);

        subdet.setBackground(Color.WHITE);
        SpringLayout s5 = new SpringLayout();
        subdet.setLayout(s5);
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
        subdet.add(classboard);

        s5.putConstraint(SpringLayout.NORTH, classboard, 0, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetmain, 290, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetmain, 30, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetname, 30, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetname, 75, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetroom, 30, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetroom, 94, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdettime, 30, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdettime, 135, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, comboId, 420, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, comboId, 30, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, subdetteacher, 30, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, subdetteacher, 115, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubname, 125, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubname, 75, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubroom, 125, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubroom, 95, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubtime, 125, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubtime, 135, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, currentsubteacher, 125, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, currentsubteacher, 115, SpringLayout.NORTH, subdet);
        s5.putConstraint(SpringLayout.WEST, delete, 190, SpringLayout.WEST, subdet);
        s5.putConstraint(SpringLayout.NORTH, delete, 200, SpringLayout.NORTH, subdet);

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

        delete.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String ids = (String) comboId.getSelectedItem();
                int realid = Integer.parseInt(ids);

                if (comboId.getItemCount() < 1) {
                    subdetailsframe.dispose();
                    JOptionPane.showMessageDialog(teacherframe, "Nincs több tantárgy!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else if (subModel.isThisSubejtARequirent(subModel.getmySubject(realid))) {
                    JOptionPane.showMessageDialog(teacherframe, "Ez a tárgy több másik tárgy előfeltétele! pl.:(" + subModel.whatIsThisSubject(subModel.getmySubject(realid)) + ")", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                } else {
                    ids = (String) comboId.getSelectedItem();
                    realid = Integer.parseInt(ids);

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

        //******************************** TÁRGY TÖRLÉSE VÉGE *************************************************
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
                        String[] str = s.split("-");
                        doneuser = userModel.getThisUser(str[1]);

                        if (doneuser.getSubjects().isEmpty()) {
                            JOptionPane.showMessageDialog(teacherframe, "Nincs a hallgatónak tárgya!", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                        } else {
                            JFrame frame = new JFrame();

                            DefaultListModel model = new DefaultListModel<>();
                            for (int i = 0; i < doneuser.getSubjects().size(); i++) {
                                model.addElement(doneuser.getSubjects().get(i).getId() + " - " + doneuser.getSubjects().get(i).getSubjectName() + " - " + doneuser.getSubjects().get(i).getSubDay() + " " + doneuser.getSubjects().get(i).getSubHour());
                            }

                            list = new JList(model);
                            JScrollPane asd = new JScrollPane(list);
                            frame.add(asd);

                            JButton done = new JButton("Teljesít");

                            done.addActionListener(new ActionListener() {

                                @Override
                                public void actionPerformed(ActionEvent e) {

                                    String str = list.getSelectedValue().toString();
                                    String[] strarray = str.split(" - ");
                                    Subject thiss = subModel.getmySubject(Integer.parseInt(strarray[0]));

                                    if (userModel.alreadyDoneTHisSubject(thiss.getId(), doneuser)) {
                                        JOptionPane.showMessageDialog(teacherframe, "Már teljesítette ezt a hallgató", "Hiba történt!", JOptionPane.ERROR_MESSAGE);
                                    } else {
                                        userModel.addThisIdToMyList(doneuser, thiss.getId());
                                        JOptionPane.showMessageDialog(teacherframe, "A(z) " + doneuser.getUsername() + " nevű hallgató mostmár teljesítette a(z) " + strarray[1] + " tárgyat!", "Sikeres tárgy teljesítés!", JOptionPane.INFORMATION_MESSAGE);
                                    }

                                }
                            });

                            frame.add(done, BorderLayout.PAGE_END);
                            frame.setSize(400, 200);
                            frame.setVisible(true);

                        }

                    }

                }
            }

        });

        exitbutton.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                //teacherframe.dispose();
                teacherframe.setVisible(false);
                JFrame start = new PlutoFrame();
            }
        });

    }

    public String[] delsubDetalis(String id) {
        String[] det = new String[5];
        for (int i = 0; i < subModel.getRowCount(); i++) {
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (sub.getId() == Integer.parseInt(id)) {
                det[0] = sub.getSubjectName();
                det[1] = sub.getRoom();
                det[2] = sub.getSubDay();
                det[3] = sub.getSubHour();
                det[4] = sub.getTeacherName();

            }
        }

        return det;
    }

    public boolean alreadyExistSub() {
        boolean exist = false;
        int size = subModel.getRowCount();
        int i = 0;
        while (i < size) {
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (sub.getSubjectName().equals(addsubname.getText()) && roomIsTaken() && teacherIsTeaching()) {
                exist = true;
            }
            i++;
        }
        return exist;

    }

    public boolean roomIsTaken() {
        boolean taken = false;
        int size = subModel.getRowCount();
        int i = 0;
        while (i < size) {
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (addsubroom.getText().equals(sub.getRoom()) && comboDay.getSelectedItem().toString().equals(sub.getSubDay()) && comboHour.getSelectedItem().toString().equals(sub.getSubHour())) {
                taken = true;
            }
            i++;
        }

        return taken;

    }

    public boolean teacherIsTeaching() {
        boolean teach = false;
        int size = subModel.getRowCount();
        int i = 0;

        while (i < size) {
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (comboTeacher.getSelectedItem().toString().equals(sub.getTeacherName()) && comboDay.getSelectedItem().toString().equals(sub.getSubDay()) && comboHour.getSelectedItem().toString().equals(sub.getSubHour())) {
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
        Subject reqsubb = new Subject();
        for (int i = 0; i < subModel.getRowCount(); i++) {
            Subject sub = DataSource.getInstance().getSubjectController().findSubjectEntities().get(i);
            if (sub.getSubjectName().equals(name)) {
                reqsubb = sub;
            }

        }
        return reqsubb;
    }
    
    
    protected ImageIcon createImageIcon(String path,
                                           String description) {
    java.net.URL imgURL = getClass().getResource(path);
    if (imgURL != null) {
        return new ImageIcon(imgURL, description);
    } else {
        System.err.println("Couldn't find file: " + path);
        return null;
    }
}

}