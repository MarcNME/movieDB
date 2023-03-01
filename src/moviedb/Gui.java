package moviedb;

import moviedb.renderer.NumberRenderer;
import moviedb.service.MovieDBService;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.text.NumberFormat;
import moviedb.models.Movie;
import moviedb.sqlService.DbAdapter;

public final class Gui extends javax.swing.JFrame {

    private final MovieDBService service;
    private final DefaultTableModel tableModel;

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnNewMovie;
    private javax.swing.JButton btnNewReview;
    private javax.swing.JButton btnShowDetails;
    private javax.swing.JButton btnTest;
    private javax.swing.JButton jButton1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable tblMovies;
    // End of variables declaration//GEN-END:variables

    public Gui() {
        initComponents();

        service = new MovieDBService();
        String[] col = {"ID", "Title", "Regisseur", "Studio", "Rating"};
        tableModel = new DefaultTableModel(col, 0);
        tblMovies.setModel(tableModel);
        setTableProperties();

        refresh();
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException
                | InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gui.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gui().setVisible(true);
            }
        });

    }

    private void printData() {
        int rowCount = tableModel.getRowCount();
        for (int i = rowCount - 1; i >= 0; i--) {
            tableModel.removeRow(i);
        }

        service.getMovies().forEach(movie -> {
            int directorId = movie.getContributorsIDsByRole("Regisseur").stream().findAny().orElse(-1);
            String directorName = "";
            if (directorId > 0) {
                directorName = service.getPersonByID(directorId).getName();
            }

            Object[] objs = {movie.getId(),
                movie.getTitle(),
                directorName,
                service.getStudioByID(movie.getStudioID()).getName(),
                String.format("%.1f", movie.getAverageRating())};
            tableModel.addRow(objs);
        });
    }

    private void refresh() {
        service.refresh();
        printData();
    }

    public void setTableProperties() {
        TableColumnModel columnModel = tblMovies.getColumnModel();

        //Column width for the ID
        columnModel.getColumn(0).setMaxWidth(35);
        columnModel.getColumn(0).setMinWidth(35);

        //Column width for the Regisseur
        columnModel.getColumn(2).setMaxWidth(120);
        columnModel.getColumn(2).setMinWidth(120);

        //Column width for the Studio
        columnModel.getColumn(3).setMaxWidth(100);
        columnModel.getColumn(3).setMinWidth(100);

        //Column Width for the rating
        columnModel.getColumn(4).setMaxWidth(55);
        columnModel.getColumn(4).setMinWidth(55);

        NumberFormat format = NumberFormat.getInstance();
        format.setMaximumFractionDigits(2);

        columnModel.getColumn(3).setCellRenderer(new NumberRenderer(format));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        btnNewMovie = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovies = new javax.swing.JTable();
        btnShowDetails = new javax.swing.JButton();
        btnNewReview = new javax.swing.JButton();
        btnTest = new javax.swing.JButton();

        jButton1.setText("jButton1");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Movie DB");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 103, Short.MAX_VALUE)
        );

        btnNewMovie.setText("Neuer Datensatz");
        btnNewMovie.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewMovieActionPerformed(evt);
            }
        });

        btnDelete.setText("Delete record");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        tblMovies.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Title", "Rating"
            }
        ));
        jScrollPane1.setViewportView(tblMovies);
        if (tblMovies.getColumnModel().getColumnCount() > 0) {
            tblMovies.getColumnModel().getColumn(0).setMaxWidth(20);
        }

        btnShowDetails.setText("Show details");
        btnShowDetails.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnShowDetailsActionPerformed(evt);
            }
        });

        btnNewReview.setText("New Review");
        btnNewReview.setMaximumSize(new java.awt.Dimension(105, 25));
        btnNewReview.setMinimumSize(new java.awt.Dimension(105, 25));
        btnNewReview.setPreferredSize(new java.awt.Dimension(105, 25));
        btnNewReview.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNewReviewActionPerformed(evt);
            }
        });

        btnTest.setText("Run Tests");
        btnTest.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTestActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnShowDetails)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnNewReview)
                        .addGap(359, 359, 359)
                        .addComponent(btnTest)
                        .addGap(18, 18, 18)
                        .addComponent(btnDelete)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnNewMovie))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btnDelete)
                            .addComponent(btnNewMovie)
                            .addComponent(btnShowDetails)
                            .addComponent(btnNewReview)
                            .addComponent(btnTest))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );

        btnNewReview.getAccessibleContext().setAccessibleParent(this);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnNewMovieActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewMovieActionPerformed
        NewEntryGui frame = new NewEntryGui(service, this);
        frame.setVisible(true);

        refresh(); //Refresh after Dialog is finished
    }//GEN-LAST:event_btnNewMovieActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        int selectedRow = tblMovies.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a Movie first");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);

        service.deleteMovie(id);
        refresh();
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void btnShowDetailsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnShowDetailsActionPerformed
        int selectedRow = tblMovies.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a Movie first");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        MovieDetailsGUI frame = new MovieDetailsGUI(id, service, this);
        frame.setVisible(true);

        refresh(); //Refresh after Dialog is finished
    }//GEN-LAST:event_btnShowDetailsActionPerformed

    private void btnNewReviewActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNewReviewActionPerformed
        int selectedRow = tblMovies.getSelectedRow();
        if (selectedRow < 0) {
            JOptionPane.showMessageDialog(this, "Please select a Movie first");
            return;
        }

        int id = (int) tableModel.getValueAt(selectedRow, 0);
        NewReviewGUI frame = new NewReviewGUI(id, service, this);
        frame.setVisible(true);

        refresh(); //Refresh after Dialog is finished
    }//GEN-LAST:event_btnNewReviewActionPerformed

    private void btnTestActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTestActionPerformed
        DbAdapter adapter = new DbAdapter();
        adapter.testConnection();
        System.out.println("Connection successfull");

        System.out.println("Adding Test Movie");
        Movie movie = new Movie("Test", 0, "test", "test");
        adapter.addMovie(movie);

        System.out.println("Getting Test Movie Id");
        int id = adapter.getMovieID(movie);
        
        if(id <= 0){
            JOptionPane.showMessageDialog(this, "Error getting Id");
            return;
        }

        System.out.println("Delete Test Movie");
        adapter.deleteMovie(id);

        System.out.println("Finished Tests");
        JOptionPane.showMessageDialog(this, "All tests run successfull");
    }//GEN-LAST:event_btnTestActionPerformed
}
