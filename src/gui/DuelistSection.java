/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import static gui.AdminPanel.isNumericData;
import java.awt.Toolkit;
import java.sql.ResultSet;
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
public class DuelistSection extends javax.swing.JFrame {
    HashMap<String, String> classMap = new HashMap<>();
    /**
     * Creates new form DuelistSection
     */
    public DuelistSection() {
        initComponents();
        loadClasses();
        loadStudents();
    }
    
    public void loadStudents() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `student` INNER JOIN `class_has_student` ON "
                    + "`student`.`Sno`=`class_has_student`.`student_sno` INNER JOIN `class` ON "
                    + "`class_has_student`.`class_classno`=`class`.`ClassNo` INNER JOIN `teacher` ON "
                    + "`class`.`Tno`=`teacher`.`Tno` INNER JOIN `attendance` ON "
                    + "`student`.`Sno`=`attendance`.`student_sno` INNER JOIN `attendance_type` ON "
                    + "`attendance`.`attendance_type_id`=`attendance_type`.`id` INNER JOIN `subject` ON "
                    + "`class`.`subNo`=`subject`.`Subno` ORDER BY `student`.`Sno` ASC");
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            model1.setRowCount(0);

            while (resultset.next()) {
                Vector<String> v = new Vector();
                v.add(resultset.getString("student.Sno"));
                v.add(resultset.getString("student.Name"));
                v.add(resultset.getString("subject.Description"));
                v.add(resultset.getString("teacher.Name"));
                v.add(resultset.getString("attendance.datetime"));
                v.add(resultset.getString("attendance_type.name"));
                v.add(resultset.getString("class.timeslot"));

                model1.addRow(v);
                jTable1.setModel(model1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void loadClasses() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `class` INNER JOIN `subject` ON "
                    + "`class`.`subNo`=`subject`.`Subno`");
            Vector<String> v = new Vector();
            v.add("Select");
            classMap.put("Select", "Select");

            while (resultset.next()) {
                v.add(resultset.getString("ClassNo") + " (" + resultset.getString("subject.Description") + ")");
                classMap.put(resultset.getString("ClassNo") + " (" + resultset.getString("subject.Description") + ")", resultset.getString("ClassNo"));

                DefaultComboBoxModel model1 = new DefaultComboBoxModel(v);
                jComboBox1.setModel(model1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void searchButtonReset() {
        loadStudents();
        
        jTextField5.setText("");
        jTextField6.setText("");
        jComboBox1.setSelectedIndex(0);

        jTextField5.requestFocus();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jButton5 = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel10 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jTextField6 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adyapana Institute - Search Due List");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
        setResizable(false);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Student ID", "Name", "Subject", "Teacher", "Date", "Attendance", "Timeslot"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable1MouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jButton5.setText("Search");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel10.setText("or Class");

        jLabel11.setText("Search by student ID");

        jLabel6.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel6.setText("Search Due List");

        jLabel12.setText("or name");

        jButton6.setText("Reset");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton17.setText("Done");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel11)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField5, javax.swing.GroupLayout.DEFAULT_SIZE, 143, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, 176, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, 147, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton5)
                    .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10)
                    .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11)
                    .addComponent(jLabel12)
                    .addComponent(jButton6)
                    .addComponent(jTextField6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton17)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_jTable1MouseClicked

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // TODO add your handling code here:
        String studentId = jTextField5.getText();
        String studentName = jTextField6.getText();
        String classId = String.valueOf(jComboBox1.getSelectedItem());

        String query = "SELECT * FROM `student` INNER JOIN `class_has_student` ON "
                + "`student`.`Sno`=`class_has_student`.`student_sno` INNER JOIN `class` ON "
                + "`class_has_student`.`class_classno`=`class`.`ClassNo` INNER JOIN `teacher` ON "
                + "`class`.`Tno`=`teacher`.`Tno` INNER JOIN `attendance` ON "
                + "`student`.`Sno`=`attendance`.`student_sno` INNER JOIN `attendance_type` ON "
                + "`attendance`.`attendance_type_id`=`attendance_type`.`id` INNER JOIN `subject` ON "
                + "`class`.`subNo`=`subject`.`Subno`";

        if (!isNumericData(studentId) & !studentId.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalied studentID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (!studentId.isEmpty() & studentName.isEmpty() & classId.equals("Select")) {
                query += " WHERE `student`.`Sno`='" + studentId + "'";
            } else if (!studentId.isEmpty() & !studentName.isEmpty() & classId.equals("Select")) {
                query += " WHERE `student`.`Sno`='" + studentId + "' AND `student`.`Name` LIKE '%" + studentName + "%'";
            } else if (studentId.isEmpty() & !studentName.isEmpty() & classId.equals("Select")) {
                query += " WHERE `student`.`Name` LIKE '%" + studentName + "%'";
            } else if (studentId.isEmpty() & studentName.isEmpty() & !classId.equals("Select")) {
                query += " WHERE `class`.`ClassNo`='" + classId + "'";
            } else if (!studentId.isEmpty() & studentName.isEmpty() & !classId.equals("Select")) {
                query += " WHERE `student`.`Sno`='" + studentId + "' AND `class`.`ClassNo`='" + classId + "'";
            } else if (studentId.isEmpty() & !studentName.isEmpty() & !classId.equals("Select")) {
                query += " WHERE `student`.`Name` LIKE '%" + studentName + "%' AND `class`.`ClassNo`='" + classId + "'";
            } else if (!studentId.isEmpty() & !studentName.isEmpty() & !classId.equals("Select")) {
                query += " WHERE `student`.`Sno`='" + studentId + "' AND `student`.`Name` LIKE '%" + studentName + "%' AND `class`.`ClassNo`='" + classId + "'";
            }

            query += " ORDER BY `student`.`Sno` ASC";

            try {
                ResultSet resultset = MySQL.execute(query);
                DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
                model1.setRowCount(0);

                while (resultset.next()) {
                    Vector<String> v = new Vector<>();
                    v.add(resultset.getString("student.Sno"));
                    v.add(resultset.getString("student.Name"));
                    v.add(resultset.getString("subject.Description"));
                    v.add(resultset.getString("teacher.Name"));
                    v.add(resultset.getString("attendance.datetime"));
                    v.add(resultset.getString("attendance_type.name"));
                    v.add(resultset.getString("class.timeslot"));

                    model1.addRow(v);
                    jTable1.setModel(model1);
                    
                    jTextField5.requestFocus();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
        searchButtonReset();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        AdminPanel adminPanel = new AdminPanel(AdminSignin.adminMap);
        adminPanel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField5;
    private javax.swing.JTextField jTextField6;
    // End of variables declaration//GEN-END:variables
}
