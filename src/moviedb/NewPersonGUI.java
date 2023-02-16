/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package moviedb;

import moviedb.models.Person;
import moviedb.service.MovieDBService;
import moviedb.sqlService.DbAdapter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author david.omoregie
 */
public class NewPersonGUI extends javax.swing.JFrame {

    /**
     * Creates new form NewPerson
     */
    public NewPersonGUI() {
        initComponents();
    }

    public DbAdapter dbAdapter = new DbAdapter();
    public MovieDBService service = new MovieDBService();

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        name = new javax.swing.JLabel();
        birthday = new javax.swing.JLabel();
        btnSafe = new javax.swing.JButton();
        btnCancle = new javax.swing.JButton();
        txtName = new javax.swing.JTextField();
        txtDate = new javax.swing.JFormattedTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        name.setText("Vor- und Nachname");

        birthday.setText("Geburtstag");

        btnSafe.setText("Speichern");
        btnSafe.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSafe(evt);
            }
        });

        btnCancle.setText("Abbrechen");
        btnCancle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancle(evt);
            }
        });

        txtDate.setFormatterFactory(new javax.swing.text.DefaultFormatterFactory(new javax.swing.text.DateFormatter()));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btnSafe, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 38, Short.MAX_VALUE)
                                                .addComponent(btnCancle, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                        .addComponent(txtDate)
                                                        .addComponent(txtName, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(name, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(birthday, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(name)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtName, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(44, 44, 44)
                                .addComponent(birthday)
                                .addGap(18, 18, 18)
                                .addComponent(txtDate, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(41, 41, 41)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btnSafe)
                                        .addComponent(btnCancle))
                                .addContainerGap(25, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancle(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancle
        dispose();
    }//GEN-LAST:event_btnCancle

    private void btnSafe(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSafe
        String txtDateText = txtDate.getText();
        Date dateFormatted = null;
        try {
            dateFormatted = new SimpleDateFormat("dd/MM/yyyy").parse(txtDateText);
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        Person person = new Person(txtName.getText(), dateFormatted);
        service.addPerson(person);
    }//GEN-LAST:event_btnSafe

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel birthday;
    private javax.swing.JButton btnCancle;
    private javax.swing.JButton btnSafe;
    private javax.swing.JLabel name;
    private javax.swing.JFormattedTextField txtDate;
    private javax.swing.JTextField txtName;
    // End of variables declaration//GEN-END:variables
}
