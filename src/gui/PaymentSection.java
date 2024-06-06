/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package gui;

import static gui.AdminPanel.isNumericData;
import java.awt.Toolkit;
import java.sql.ResultSet;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Vector;
import javax.swing.ButtonModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import model.Invoice;
import model.MySQL;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author dulanjaya
 */
public class PaymentSection extends javax.swing.JFrame {

    HashMap<String, Integer> selectStudentMap = new HashMap<>();
    HashMap<String, Integer> classMap = new HashMap<>();
    HashMap<Integer, String> classMap2 = new HashMap<>();
    HashMap<String, Integer> teacherMap = new HashMap<>();
    HashMap<String, Integer> subjectMap = new HashMap<>();

    /**
     * Creates new form PaymentSection
     */
    public PaymentSection() {
        initComponents();
        loadStudents();
        loadClasses();
        loadInvoice();
    }

    public void loadStudents() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `student`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            while (resultset.next()) {
                vector.add(resultset.getString("Name"));
                selectStudentMap.put(resultset.getString("Name"), resultset.getInt("Sno"));
            }

            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector);
            jComboBox1.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadClasses() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `class` INNER JOIN `subject` ON "
                    + "`class`.`subNo`=`subject`.`Subno`");

            Vector<String> vector = new Vector<>();
            vector.add("Select");

            int counter = 0;

            while (resultset.next()) {
                vector.add(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")");
                classMap.put(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")", resultset.getInt("class.ClassNo"));
                classMap2.put(counter, resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")");
                subjectMap.put(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")", resultset.getInt("class.subNo"));
                teacherMap.put(resultset.getString("class.ClassNo") + "(" + resultset.getString("subject.Description") + ")", resultset.getInt("class.Tno"));

                counter += 1;
            }

            DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector);
            jComboBox2.setModel(model1);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void loadInvoice() {
        try {
            ResultSet resultset = MySQL.execute("SELECT * FROM `invoice` INNER JOIN `student` ON "
                    + "`invoice`.`Sno`=`student`.`Sno` INNER JOIN `subject` ON "
                    + "`invoice`.`Subno`=`subject`.`Subno` INNER JOIN `teacher` ON "
                    + "`invoice`.`Tno`=`teacher`.`Tno` ORDER BY `invoice`.`id` ASC");
            DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
            model1.setRowCount(0);

            while (resultset.next()) {
                Vector vector = new Vector();
                vector.add(resultset.getInt("invoice.id"));
                vector.add(resultset.getInt("student.Sno"));
                vector.add(resultset.getString("student.Name"));
                vector.add(resultset.getString("subject.Description"));
                vector.add(resultset.getString("teacher.Name"));
                vector.add(resultset.getString("invoice.month"));
                vector.add(resultset.getString("invoice.value"));

                model1.addRow(vector);
            }
            jTable1.setModel(model1);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void serachButtonReset() {
        jTextField1.setText("");
        jTextField2.setText("");
        jDateChooser1.setDate(null);

        jTextField1.requestFocus();
    }

    public void serachStudentReset() {
        jTextField4.setText("");
        jTextField3.setText("");

        jTextField4.requestFocus();
    }

    public void reset() {
        jComboBox1.setSelectedIndex(0);
        jComboBox2.setSelectedIndex(0);
        jDateChooser3.setDate(null);
        jTextField5.setText("");
        buttonGroup1.clearSelection();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        jPanel1 = new javax.swing.JPanel();
        jLabel12 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel18 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        jLabel19 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jLabel20 = new javax.swing.JLabel();
        jDateChooser1 = new com.toedter.calendar.JDateChooser();
        jButton11 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jTextField3 = new javax.swing.JTextField();
        jComboBox1 = new javax.swing.JComboBox<>();
        jButton12 = new javax.swing.JButton();
        jTextField4 = new javax.swing.JTextField();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        jLabel22 = new javax.swing.JLabel();
        jComboBox2 = new javax.swing.JComboBox<>();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jTextField5 = new javax.swing.JTextField();
        jLabel28 = new javax.swing.JLabel();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jDateChooser3 = new com.toedter.calendar.JDateChooser();
        jLabel25 = new javax.swing.JLabel();
        jLabel29 = new javax.swing.JLabel();
        jRadioButton1 = new javax.swing.JRadioButton();
        jRadioButton2 = new javax.swing.JRadioButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Adyapana Institute - Payment Section");
        setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/icon.png")));
        setResizable(false);

        jLabel12.setFont(new java.awt.Font("Dialog", 1, 14)); // NOI18N
        jLabel12.setText("Payment Section");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Invoice No.", "Student No.", "Student Name", "Subject", "Teacher", "Payment Date", "Amount"
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

        jLabel18.setText("Search by student no.");

        jLabel19.setText("or name");

        jLabel20.setText("or payment date");

        jDateChooser1.setDateFormatString("yyy-MM-dd");

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

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jButton12.setFont(new java.awt.Font("Dialog", 1, 10)); // NOI18N
        jButton12.setText("Find");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jLabel23.setText("ID");

        jLabel24.setText("Name");

        jLabel22.setText("Class");

        jComboBox2.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        jLabel26.setText("Payment Date");

        jLabel27.setText("Amount");

        jLabel28.setFont(new java.awt.Font("Dialog", 0, 10)); // NOI18N
        jLabel28.setText("RS.");

        jButton13.setText("Add Payment");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setText("Update Payment");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton17.setText("Done");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jDateChooser3.setDateFormatString("yyy-MM-dd");

        jLabel25.setText("Find a student for the payment process");

        jLabel29.setText("Generate Invoice");

        buttonGroup1.add(jRadioButton1);
        jRadioButton1.setText("Yes");
        jRadioButton1.setActionCommand("1");

        buttonGroup1.add(jRadioButton2);
        jRadioButton2.setText("No");
        jRadioButton2.setActionCommand("2");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel28)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField5))
                            .addComponent(jComboBox1, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jComboBox2, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jDateChooser3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel22)
                            .addComponent(jLabel26)
                            .addComponent(jLabel27)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jLabel23)
                                    .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, 61, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(jPanel1Layout.createSequentialGroup()
                                        .addComponent(jTextField3, javax.swing.GroupLayout.DEFAULT_SIZE, 84, Short.MAX_VALUE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(jLabel24)))
                            .addComponent(jLabel25)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jRadioButton1)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jRadioButton2))
                            .addComponent(jLabel29))
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(16, 16, 16)
                                .addComponent(jLabel18)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, 84, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel19)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel20)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jDateChooser1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton11)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(2, 2, 2))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 816, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jButton17, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 91, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel12)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jLabel12)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel25)
                            .addComponent(jLabel18)
                            .addComponent(jTextField1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel19)
                            .addComponent(jTextField2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel20))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel24)
                            .addComponent(jLabel23))
                        .addGap(5, 5, 5)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton12)
                            .addComponent(jTextField4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel22)
                        .addGap(6, 6, 6)
                        .addComponent(jComboBox2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel26)
                        .addGap(6, 6, 6)
                        .addComponent(jDateChooser3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(6, 6, 6)
                        .addComponent(jLabel27)
                        .addGap(6, 6, 6)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jTextField5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel28))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel29)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jRadioButton1)
                            .addComponent(jRadioButton2)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jDateChooser1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(jButton11, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jButton9)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 291, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton13)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton14))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(44, 44, 44)
                        .addComponent(jButton17)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
        // TODO add your handling code here:
        String studentId1 = jTextField1.getText();
        String studentName = jTextField2.getText();

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Date date = jDateChooser1.getDate();

        String query = "SELECT * FROM `invoice` INNER JOIN `student` ON "
                + "`invoice`.`Sno`=`student`.`Sno` INNER JOIN `subject` ON "
                + "`invoice`.`Subno`=`subject`.`Subno` INNER JOIN `teacher` ON "
                + "`invoice`.`Tno`=`teacher`.`Tno`";

        if (!isNumericData(studentId1) & !studentId1.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalied studentID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (!studentId1.isEmpty() & studentName.isEmpty() & date == null) {
                query += " WHERE `student`.`Sno`='" + studentId1 + "'";
            } else if (!studentId1.isEmpty() & !studentName.isEmpty() & date == null) {
                query += " WHERE `student`.`Sno`='" + studentId1 + "' AND `student`.`Name` LIKE '%" + studentName + "%'";
            } else if (studentId1.isEmpty() & !studentName.isEmpty() & date == null) {
                query += " WHERE `student`.`Name` LIKE '%" + studentName + "%'";
            } else if (studentId1.isEmpty() & studentName.isEmpty() & date != null) {
                String stringDate = format.format(date);
                query += " WHERE `invoice`.`month`='" + stringDate + "'";
            } else if (!studentId1.isEmpty() & studentName.isEmpty() & date != null) {
                String stringDate = format.format(date);
                query += " WHERE `student`.`Sno`='" + studentId1 + "' AND `invoice`.`month`='" + stringDate + "'";
            } else if (studentId1.isEmpty() & !studentName.isEmpty() & date != null) {
                String stringDate = format.format(date);
                query += " WHERE `student`.`Name` LIKE '%" + studentName + "%' AND `invoice`.`month`='" + stringDate + "'";
            } else if (!studentId1.isEmpty() & !studentName.isEmpty() & date != null) {
                String stringDate = format.format(date);
                query += " WHERE `student`.`Sno`='" + studentId1 + "' AND `student`.`Name` LIKE '%" + studentName + "%' AND `invoice`.`month`='" + stringDate + "'";
            }

            query += " ORDER BY `invoice`.`id` ASC";

            try {
                ResultSet resultset = MySQL.execute(query);
                DefaultTableModel model1 = (DefaultTableModel) jTable1.getModel();
                model1.setRowCount(0);

                while (resultset.next()) {
                    Vector vector = new Vector();
                    vector.add(resultset.getInt("invoice.id"));
                    vector.add(resultset.getInt("student.Sno"));
                    vector.add(resultset.getString("student.Name"));
                    vector.add(resultset.getString("subject.Description"));
                    vector.add(resultset.getString("teacher.Name"));
                    vector.add(resultset.getString("invoice.month"));
                    vector.add(resultset.getString("invoice.value"));

                    model1.addRow(vector);
                    jTable1.setModel(model1);
                }

                serachButtonReset();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        // TODO add your handling code here:
        serachButtonReset();
        loadInvoice();
        serachStudentReset();
        reset();

        jTable1.setEnabled(true);
        jComboBox1.setEnabled(true);
        jComboBox2.setEnabled(true);
        jDateChooser3.setEnabled(true);
        jButton13.setEnabled(true);
        jRadioButton1.setEnabled(true);
        jRadioButton2.setEnabled(true);
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        // TODO add your handling code here:
        String studentId2 = jTextField4.getText();
        String studentName = jTextField3.getText();

        if (!isNumericData(studentId2) & !studentId2.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalied studentID", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (studentId2.isEmpty() & studentName.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please enter a stduentId or name first", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            String query = "SELECT * FROM `student`";

            if (!studentId2.isEmpty() & studentName.isEmpty()) {
                query += " WHERE `Sno`='" + studentId2 + "'";
            } else if (studentId2.isEmpty() & !studentName.isEmpty()) {
                query += " WHERE `Name` LIKE '%" + studentName + "%'";
            } else if (!studentId2.isEmpty() & !studentName.isEmpty()) {
                query += " WHERE `Sno`='" + studentId2 + "' AND `Name` LIKE '%" + studentName + "%'";
            }

            query += " ORDER BY `Sno` ASC";

            try {
                ResultSet resultset = MySQL.execute(query);
                Vector<String> vector = new Vector<>();
                vector.add("Select");

                while (resultset.next()) {
                    vector.add(resultset.getString("Name"));
                }

                DefaultComboBoxModel model1 = new DefaultComboBoxModel(vector);
                jComboBox1.setModel(model1);

                serachStudentReset();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        // TODO add your handling code here:
        String studentName = String.valueOf(jComboBox1.getSelectedItem());
        String className = String.valueOf(jComboBox2.getSelectedItem());

        SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
        Date date = jDateChooser3.getDate();

        String amount = jTextField5.getText();
        ButtonModel buttonSelection = buttonGroup1.getSelection();

        if (studentName.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a stduent", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (className.equals("Select")) {
            JOptionPane.showMessageDialog(this, "Please select a class", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (date == null) {
            JOptionPane.showMessageDialog(this, "Please enter payment date", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (amount.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Invalied amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (!isNumericData(amount)) {
            JOptionPane.showMessageDialog(this, "Invalied amount", "Warning", JOptionPane.WARNING_MESSAGE);
        } else if (buttonSelection == null) {
            JOptionPane.showMessageDialog(this, "Please choose a generate invoice option", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {
            int studentId = selectStudentMap.get(studentName);
            int teacherId = teacherMap.get(className);
            int subjectId = subjectMap.get(className);
            int classId = classMap.get(className);
            String stringDate = format.format(date);
            boolean isAproved = true;

            try {
                ResultSet resultset = MySQL.execute("SELECT * FROM `invoice` WHERE `Sno`='" + studentId + "' AND `Tno`='" + teacherId + "' AND `Subno`='" + subjectId + "'");
                while (resultset.next()) {
                    String[] requestDateArray = stringDate.split("-", -2);
                    String targetDate = resultset.getString("month");
                    String[] targetDateArray = targetDate.split("-", -2);

                    if (requestDateArray[0].equals(targetDateArray[0]) & requestDateArray[1].equals(targetDateArray[1]) & isAproved) {
                        isAproved = false;
                        JOptionPane.showMessageDialog(this, "This student already pay the pees in this month", "Warning", JOptionPane.WARNING_MESSAGE);
                    }
                }

                if (isAproved) {
                    MySQL.execute("INSERT INTO `invoice` (`month`,`value`,`Sno`,`Tno`,`Subno`) VALUES ('" + stringDate + "','" + amount + "','" + studentId + "','" + teacherId + "','" + subjectId + "')");
                    loadInvoice();
                    reset();
                    serachButtonReset();

                    if (buttonSelection.getActionCommand().equals("1")) {
                        ResultSet resultset2 = MySQL.execute("SELECT * FROM `invoice` INNER JOIN `student` ON "
                                + "`invoice`.`Sno`=`student`.`Sno` INNER JOIN `subject` ON "
                                + "`invoice`.`Subno`=`subject`.`Subno` INNER JOIN `teacher` ON "
                                + "`invoice`.`Tno`=`teacher`.`Tno` WHERE `invoice`.`month`='" + stringDate + "' AND `student`.`Sno`='" + studentId + "' AND `teacher`.`Tno`='" + teacherId + "' AND `subject`.`Subno`='" + subjectId + "'");

                        if (resultset2.next()) {
                            HashMap<String, Object> parameters = new HashMap<>();

                            parameters.put("Parameter1", studentName);
                            parameters.put("Parameter2", resultset2.getString("student.Address"));
                            parameters.put("Parameter3", resultset2.getString("invoice.id"));
                            parameters.put("Parameter4", resultset2.getString("invoice.month"));
                            parameters.put("Parameter5", resultset2.getString("invoice.value"));

                            Invoice invoice = new Invoice();
                            invoice.setStudentName(studentName);
                            invoice.setAddress(resultset2.getString("Address"));
                            invoice.setInvoiceId(resultset2.getString("invoice.id"));
                            invoice.setDate(resultset2.getString("invoice.month"));
                            invoice.setAmount(resultset2.getString("invoice.value"));

                            Vector<Invoice> vector = new Vector<>();
                            vector.add(invoice);

                            JRBeanCollectionDataSource datasource = new JRBeanCollectionDataSource(vector);
                            JasperPrint report = JasperFillManager.fillReport("src/report/invoice.jasper", parameters, datasource);
                            JasperViewer.viewReport(report, false);
                        }

                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        // TODO add your handling code here:
        int selectedRow = jTable1.getSelectedRow();
        String amount = jTextField5.getText();
        String invoiceId = String.valueOf(jTable1.getValueAt(selectedRow, 0));

        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a row", "Warning", JOptionPane.WARNING_MESSAGE);
        } else {

            if (amount.isEmpty()) {
                JOptionPane.showMessageDialog(this, "Invalied amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else if (!isNumericData(amount)) {
                JOptionPane.showMessageDialog(this, "Invalied amount", "Warning", JOptionPane.WARNING_MESSAGE);
            } else {
                try {
                    MySQL.execute("UPDATE `invoice` SET `value`='" + amount + "' WHERE `id`='" + invoiceId + "'");

                    loadInvoice();
                    reset();
                    serachButtonReset();

                    jTable1.setEnabled(true);
                    jComboBox1.setEnabled(true);
                    jComboBox2.setEnabled(true);
                    jDateChooser3.setEnabled(true);
                    jButton13.setEnabled(true);
                    jRadioButton1.setEnabled(true);
                    jRadioButton2.setEnabled(true);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }

    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        // TODO add your handling code here:
        AdminPanel adminPanel = new AdminPanel(AdminSignin.adminMap);
        adminPanel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jTable1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable1MouseClicked
        // TODO add your handling code here:

        if (evt.getClickCount() == 2) {
            int selectedRow = jTable1.getSelectedRow();
            String studentName = String.valueOf(jTable1.getValueAt(selectedRow, 2));
            String className = classMap2.get(selectedRow);
            String paymentDate = String.valueOf(jTable1.getValueAt(selectedRow, 5));
            String amount = String.valueOf(jTable1.getValueAt(selectedRow, 6));

            jComboBox1.setSelectedItem(studentName);
            jComboBox2.setSelectedItem(className);

            SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
            Date date;
            try {
                date = format.parse(paymentDate);
                jDateChooser3.setDate(date);
            } catch (ParseException ex) {
                ex.printStackTrace();
            }

            jTextField5.setText(amount);

            jTable1.setEnabled(false);
            jComboBox1.setEnabled(false);
            jComboBox2.setEnabled(false);
            jDateChooser3.setEnabled(false);
            jButton13.setEnabled(false);
            jRadioButton1.setEnabled(false);
            jRadioButton2.setEnabled(false);

            jTextField5.requestFocus();
        }
    }//GEN-LAST:event_jTable1MouseClicked

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton9;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JComboBox<String> jComboBox2;
    private com.toedter.calendar.JDateChooser jDateChooser1;
    private com.toedter.calendar.JDateChooser jDateChooser3;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JRadioButton jRadioButton1;
    private javax.swing.JRadioButton jRadioButton2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextField jTextField3;
    private javax.swing.JTextField jTextField4;
    private javax.swing.JTextField jTextField5;
    // End of variables declaration//GEN-END:variables
}
