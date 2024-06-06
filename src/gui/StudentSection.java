/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import java.awt.Toolkit;
import static gui.AdminPanel.isNumericData;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.MySQL;

/**
 *
 * @author dulanjaya
 */
public class StudentSection extends javax.swing.JFrame {

    public HashMap<String, Integer> subjectMap = new HashMap<>();
    public HashMap<String, Integer> classMap = new HashMap<>();
    public HashMap<String, Integer> studentClassMap = new HashMap<>();
    public HashMap<String, Integer> selectedStudent = new HashMap<>();

    /**
     * Creates new form StudentSection
     */
    public StudentSection() {
        initComponents();
        loadClasses();
        loadSubjects();
        loadStudents();
    }

    public void loadClasses() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `class` INNER JOIN `subject` ON "
                    + "`class`.`subNo`=`subject`.`Subno`");

            Vector<String> vector = new Vector();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")");
                classMap.put(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")", resultset.getInt("class.ClassNo"));
            }

            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector);
            jComboBox5.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSubjects() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `subject` WHERE `Description`<>'Not Assigned'");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("Description"));
                subjectMap.put(resultset.getString("Description"), resultset.getInt("Subno"));
            }

            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadStudents() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `student`");
            DefaultTableModel model1 = (DefaultTableModel) jTable4.getModel();
            model1.setRowCount(0);

            while (resultset.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("Sno"));
                vector.add(resultset.getString("Name"));
                vector.add(resultset.getString("Address"));
                vector.add(resultset.getString("dob"));

                model1.addRow(vector);
                jTable4.setModel(model1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadSelectedClasses(int studentId) {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `class_has_student` INNER JOIN `class` ON "
                    + "`class_has_student`.`class_classno`=`class`.`ClassNo` INNER JOIN `subject` ON "
                    + "`class`.`subNo`=`subject`.`Subno` WHERE `student_sno`='" + studentId + "' ORDER BY `class_has_student`.`id` ASC");

            DefaultTableModel model1 = (DefaultTableModel) jTable7.getModel();
            model1.setRowCount(0);

            while (resultset.next()) {
                Vector<String> vector = new Vector<>();
                vector.add(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")");
                studentClassMap.put(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")", resultset.getInt("class_has_student.id"));
                model1.addRow(vector);
            }
            jTable7.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void searchButtonReset() {
        jTextField8.setText("");
        jTextField9.setText("");
        jComboBox2.setSelectedIndex(0);
        jTextField8.requestFocus();
    }

    public void studentReset() {
        jTextField10.setText("");
        jTextField11.setText("");
        jDateChooser1.setDate(null);

        jTextField10.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jTextField8 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField9 = new javax.swing.JTextField();
        jLabel17 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jScrollPane4 = new javax.swing.JScrollPane();
        jTable4 = new javax.swing.JTable();
        jLabel22 = new javax.swing.JLabel();
        jTextField10 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jTextField11 = new javax.swing.JTextField();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jLabel25 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jScrollPane7 = new javax.swing.JScrollPane();
        jTable7 = new javax.swing.JTable();
        jLabel21 = new javax.swing.JLabel();
        jComboBox5 = new javax.swing.JComboBox<>();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adyapana Institute - Student Section");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
        setResizable(false);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Student Section");

        jLabel18.setText("Search by ID");

        jLabel19.setText("or name");

        jLabel17.setText("or subject");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton11.setText("Search");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton9.setText("Reset");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jTable4.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student No.", "Name", "Address", "Date of birth"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable4.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable4AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable4MouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(jTable4);

        jLabel22.setText("Name");

        jLabel23.setText("Address");

        jButton6.setText("Add");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Update");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton8.setText("Delete");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel25.setText("Date of birth");

        jDateChooser1.setDateFormatString("yyy-MM-dd");

        jTable7.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Classes"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable7.addAncestorListener(new javax.swing.event.AncestorListener() {
            public void ancestorMoved(javax.swing.event.AncestorEvent evt) {
            }
            public void ancestorAdded(javax.swing.event.AncestorEvent evt) {
                jTable7AncestorAdded(evt);
            }
            public void ancestorRemoved(javax.swing.event.AncestorEvent evt) {
            }
        });
        jTable7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable7MouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(jTable7);

        jLabel21.setText("Class");

        jComboBox5.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton14.setText("Add");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setText("Update");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setText("Delete");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setText("Done");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(jTextField10, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jTextField11, javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton8, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton7, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                    .addComponent(jButton6, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jLabel22, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(11, 11, 11)))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jScrollPane4, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 697, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addGap(1, 1, 1)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel17)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, 119, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(312, 312, 312)
                                .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jLabel21, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jComboBox5, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton15, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                                    .addComponent(jButton16, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 73, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel12)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jButton9)
                            .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel17)
                            .addComponent(jTextField8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel18)
                            .addComponent(jLabel19)
                            .addComponent(jTextField9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton11)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(24, 24, 24)
                        .addComponent(jLabel22)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jTextField10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel23)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel25)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDateChooser1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton8))
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel21)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jButton14)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton15)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton16))
                    .addComponent(jScrollPane7, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        String studentId = jTextField8.getText();
        String studentName = jTextField9.getText();
        String subject = String.valueOf(jComboBox2.getSelectedItem());

        String query = "SELECT * FROM `student` INNER JOIN `subject` ON "
                + "`student`.`Sno`=`subject`.`Subno`";

        if (!isNumericData(studentId) & !studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalied studentID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (!studentId.isEmpty() & studentName.isEmpty() & subject.equals("Select")) {
                query += " WHERE `student`.`Sno`='" + studentId + "'";
            } else if (!studentId.isEmpty() & !studentName.isEmpty() & subject.equals("Select")) {
                query += " WHERE `student`.`Sno`='" + studentId + "' AND `student`.`Name` LIKE '%" + studentName + "%'";
            } else if (studentId.isEmpty() & !studentName.isEmpty() & subject.equals("Select")) {
                query += " WHERE `student`.`Name` LIKE '%" + studentName + "%'";
            } else if (studentId.isEmpty() & studentName.isEmpty() & !subject.equals("Select")) {
                int subjectId = subjectMap.get(subject);
                query += " WHERE `subject`.`Subno`='" + subjectId + "'";
            } else if (!studentId.isEmpty() & studentName.isEmpty() & !subject.equals("Select")) {
                int subjectId = subjectMap.get(subject);
                query += " WHERE `student`.`Sno`='" + studentId + "' AND `subject`.`Subno`='" + subjectId + "'";
            } else if (studentId.isEmpty() & !studentName.isEmpty() & !subject.equals("Select")) {
                int subjectId = subjectMap.get(subject);
                query += " WHERE `student`.`Name` LIKE '%" + studentName + "%' AND `subject`.`Subno`='" + subjectId + "'";
            } else if (!studentId.isEmpty() & !studentName.isEmpty() & !subject.equals("Select")) {
                int subjectId = subjectMap.get(subject);
                query += " WHERE `student`.`Sno`='" + studentId + "' AND `student`.`Name` LIKE '%" + studentName + "%' AND `subject`.`Subno`='" + subjectId + "'";
            }

            query += " ORDER BY `student`.`Sno` ASC";

            try {
                ResultSet resultset = MySQL.execute(query);
                DefaultTableModel model1 = (DefaultTableModel) jTable4.getModel();
                model1.setRowCount(0);

                while (resultset.next()) {
                    Vector<String> v = new Vector<>();
                    v.add(resultset.getString("student.Sno"));
                    v.add(resultset.getString("student.Name"));
                    v.add(resultset.getString("student.Address"));
                    v.add(resultset.getString("student.dob"));

                    model1.addRow(v);
                    jTable4.setModel(model1);

                }
                searchButtonReset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        jTable4.setEnabled(true);
        jTable7.setEnabled(true);
        DefaultTableModel model1 = (DefaultTableModel) jTable7.getModel();
        model1.setRowCount(0);
        jButton14.setEnabled(true);
        jButton6.setEnabled(true);
        jComboBox5.setSelectedIndex(0);
        loadStudents();
        searchButtonReset();
        studentReset();
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jTable4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable4MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int selectedRow = jTable4.getSelectedRow();

            String studentId = String.valueOf(jTable4.getValueAt(selectedRow, 0));
            String studentName = String.valueOf(jTable4.getValueAt(selectedRow, 1));
            String address = String.valueOf(jTable4.getValueAt(selectedRow, 2));
            String dateOfBirth = String.valueOf(jTable4.getValueAt(selectedRow, 3));

            selectedStudent.put("selectedStudentId", Integer.parseInt(studentId));

            SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
            Date date;
            try {
                date = format.parse(dateOfBirth);
                jDateChooser1.setDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            jTextField10.setText(studentName);
            jTextField11.setText(address);

            loadSelectedClasses(Integer.parseInt(studentId));

            jTable4.setEnabled(false);
            jButton6.setEnabled(false);
        }
    }//GEN-LAST:event_jTable4MouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        String studentName = jTextField10.getText();
        String address = jTextField11.getText();
        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Date date = jDateChooser1.getDate();
        String stringDate = format.format(date);

        if (studentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter student name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (studentName.length() > 100) {
            JOptionPane.showMessageDialog(this, "Student name must have <100 characters", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.length() > 100) {
            JOptionPane.showMessageDialog(this, "Address must have <100 characters", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (stringDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter date of birth", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                MySQL.execute("INSERT INTO `student` (`Name`,`Address`,`dob`) VALUES ('" + studentName + "','" + address + "','" + stringDate + "')");

                loadStudents();
                studentReset();
                jTable4.setEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable4.getSelectedRow();

        String studentId = String.valueOf(jTable4.getValueAt(selectedRow, 0));
        String studentName = jTextField10.getText();
        String address = jTextField11.getText();

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Date date = jDateChooser1.getDate();
        String stringDate = format.format(date);

        if (studentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter student name", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (studentName.length() > 100) {
            JOptionPane.showMessageDialog(this, "Student name must have <100 characters", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter address", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (address.length() > 100) {
            JOptionPane.showMessageDialog(this, "Address must have <100 characters", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (stringDate.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter date of birth", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            try {
                MySQL.execute("UPDATE `student` SET `Name`='" + studentName + "',`Address`='" + address + "',`dob`='" + stringDate + "' WHERE `Sno`='" + studentId + "'");
                loadStudents();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void jTable7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable7MouseClicked
        // TODO add your handling code here:
        if (evt.getClickCount() == 2) {
            int selectedRow = jTable7.getSelectedRow();
            String className = String.valueOf(jTable7.getValueAt(selectedRow, 0));

            jComboBox5.setSelectedItem(className);

            jTable7.setEnabled(false);
            jButton14.setEnabled(false);
        }
    }//GEN-LAST:event_jTable7MouseClicked

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        String className = String.valueOf(jComboBox5.getSelectedItem());
        int studentId = selectedStudent.get("selectedStudentId");

        if (className.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a class", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int classId = classMap.get(className);
            try {
                MySQL.execute("INSERT INTO `class_has_student` (`class_classno`,`student_sno`) VALUES ('" + classId + "','" + studentId + "')");

                loadSelectedClasses(studentId);
                jComboBox5.setSelectedIndex(0);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable7.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showConfirmDialog(this, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String className = String.valueOf(jComboBox5.getSelectedItem());
            int classId = classMap.get(className);
            int studentId = selectedStudent.get("selectedStudentId");

            String selectedClassName = String.valueOf(jTable7.getValueAt(selectedRow, 0));
            int studentClassId = studentClassMap.get(selectedClassName);

            boolean isOperationOk = true;

            if (className.equals("Select")) {
                JOptionPane.showMessageDialog(this, "Please select a class", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                for (int x = 0; x < jTable7.getRowCount(); x++) {
                    if (className.equals(String.valueOf(jTable7.getValueAt(x, 0)))) {
                        isOperationOk = false;
                        JOptionPane.showConfirmDialog(this, "Invalied class", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if (isOperationOk) {
                    try {
                        MySQL.execute("UPDATE `class_has_student` SET `class_classno`='" + classId + "' WHERE `id`='" + studentClassId + "'");

                        loadSelectedClasses(studentId);
                        jComboBox5.setSelectedIndex(0);

                        jTable7.setEnabled(true);
                        jButton14.setEnabled(true);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable7.getSelectedRow();

        if (selectedRow == -1) {
            JOptionPane.showConfirmDialog(this, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String selectedClassName = String.valueOf(jTable7.getValueAt(selectedRow, 0));
            int studentClassId = studentClassMap.get(selectedClassName);
            int studentId = selectedStudent.get("selectedStudentId");

            try {
                MySQL.execute("DELETE FROM `class_has_student` WHERE `id`='" + studentClassId + "'");

                loadSelectedClasses(studentId);
                jComboBox5.setSelectedIndex(0);

                jTable7.setEnabled(true);
                jButton14.setEnabled(true);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        AdminPanel adminPanel = new AdminPanel(AdminSignin.adminMap);
        adminPanel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTable7AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable7AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable7AncestorAdded

    private void jTable4AncestorAdded(javax.swing.event.AncestorEvent evt) {//GEN-FIRST:event_jTable4AncestorAdded
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable4AncestorAdded

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox2;
    private javax.swing.JComboBox<String> jComboBox5;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JTable jTable4;
    private javax.swing.JTable jTable7;
    private javax.swing.JTextField jTextField10;
    private javax.swing.JTextField jTextField11;
    private javax.swing.JTextField jTextField8;
    private javax.swing.JTextField jTextField9;
    // End of variables declaration//GEN-END:variables
}
