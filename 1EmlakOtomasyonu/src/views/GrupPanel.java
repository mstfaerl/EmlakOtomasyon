/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dao.GrupDao;
import entity.Grup;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author m07er
 */
public class GrupPanel extends javax.swing.JFrame {

    /**
     * Creates new form GrupPanel
     */
    DefaultTableModel tablo = new DefaultTableModel();
    GrupDao grupDao = new GrupDao();
    Grup grup = new Grup();
    ArrayList<Grup> gList;

    public GrupPanel() {
        initComponents();
        this.setLocationRelativeTo(null);
        grup_id.setVisible(false);
        listele();
    }

    void listele() {
        this.tablo = (DefaultTableModel) grupList.getModel();
        this.tablo.setRowCount(0);
        Object[] row = new Object[2];
        for (int i = 0; i < grupDao.list().size(); i++) {
            row[0] = grupDao.list().get(i).getGrup_id();
            row[1] = grupDao.list().get(i).getGrup_adi();
            tablo.addRow(row);
        }

        grupList.setModel(tablo);
    }

    void temizle() {
        gruptf.setText(null);
        this.tablo.setRowCount(0);
    }

    void ekle() {
        String grupAdi = gruptf.getText();
        boolean grupKontrol = false;

        if (grupAdi.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Lütfen boş alanları doldurunuz!");
        } else {
            for (int i = 0; i < this.grupDao.list().size(); i++) {
                if (grupAdi.equalsIgnoreCase(this.grupDao.list().get(i).getGrup_adi())) {
                    grupKontrol = true;
                    break;
                }
            }

            if (grupKontrol) {
                JOptionPane.showMessageDialog(rootPane, "Bu grup adı daha önce oluşturuldu. Lütfen farklı bir grup adı ile deneyiniz.");
            } else {
                this.grup.setGrup_adi(grupAdi);
                this.grupDao.create(this.grup);
            }

        }
    }

    void duzenle() {
        String grupAdi = gruptf.getText();
        this.grup.setGrup_adi(grupAdi);
        this.grup.setGrup_id(Long.parseLong(grup_id.getText()));
        this.grupDao.update(this.grup);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        gruptf = new javax.swing.JTextField();
        duzenleButton = new javax.swing.JButton();
        temizleButton = new javax.swing.JButton();
        listeButton = new javax.swing.JButton();
        ekleButton = new javax.swing.JButton();
        silButton = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        grupList = new javax.swing.JTable();
        grup_id = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel1.setText("Grup Adı");

        duzenleButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        duzenleButton.setText("Düzenle");
        duzenleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duzenleButtonActionPerformed(evt);
            }
        });

        temizleButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        temizleButton.setText("Temizle");
        temizleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temizleButtonActionPerformed(evt);
            }
        });

        listeButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        listeButton.setText("Listele");
        listeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeButtonActionPerformed(evt);
            }
        });

        ekleButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        ekleButton.setText("Ekle");
        ekleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ekleButtonActionPerformed(evt);
            }
        });

        silButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        silButton.setText("Sil");
        silButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silButtonActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(51, 102, 255));
        jLabel2.setText("Geri dönmek için tıklayınız.");
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });

        grupList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Grup ID", "Grup Adı"
            }
        ));
        grupList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                grupListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(grupList);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(grup_id))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(temizleButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 88, Short.MAX_VALUE)
                                    .addComponent(ekleButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(silButton, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(listeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(duzenleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(gruptf)
                                .addGap(30, 30, 30))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 199, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 503, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 269, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGap(45, 45, 45)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(gruptf, javax.swing.GroupLayout.PREFERRED_SIZE, 32, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(32, 32, 32)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(duzenleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ekleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                    .addComponent(silButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(listeButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(10, 10, 10)
                                .addComponent(grup_id)))
                        .addGap(18, 18, 18)
                        .addComponent(temizleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(29, 29, 29)
                        .addComponent(jLabel2)))
                .addContainerGap(40, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        // TODO add your handling code here:
        IslemPanel islemPanel = new IslemPanel();
        islemPanel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel2MouseClicked

    private void listeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeButtonActionPerformed
        // TODO add your handling code here:
        listele();
    }//GEN-LAST:event_listeButtonActionPerformed

    private void temizleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temizleButtonActionPerformed
        // TODO add your handling code here:
        temizle();
    }//GEN-LAST:event_temizleButtonActionPerformed

    private void ekleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ekleButtonActionPerformed
        // TODO add your handling code here:
        ekle();
        listele();
    }//GEN-LAST:event_ekleButtonActionPerformed

    private void grupListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_grupListMouseClicked
        // TODO add your handling code here:
        int i = grupList.getSelectedRow();
        TableModel model = grupList.getModel();
        grup_id.setText(String.valueOf(grupList.getValueAt(grupList.getSelectedRow(), 0)));
        gruptf.setText(model.getValueAt(i, 1).toString()); //JTable hangi satıra tıklarsak o satırı textfiela atar.
    }//GEN-LAST:event_grupListMouseClicked

    private void duzenleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duzenleButtonActionPerformed
        // TODO add your handling code here:
        duzenle();
        listele();
    }//GEN-LAST:event_duzenleButtonActionPerformed

    private void silButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silButtonActionPerformed
        // TODO add your handling code here:
        grup.setGrup_id(Long.parseLong(grup_id.getText()));
        grupDao.delete(this.grup);
        temizle();
        listele();
    }//GEN-LAST:event_silButtonActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(GrupPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(GrupPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(GrupPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(GrupPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new GrupPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton duzenleButton;
    private javax.swing.JButton ekleButton;
    private javax.swing.JTable grupList;
    private javax.swing.JLabel grup_id;
    private javax.swing.JTextField gruptf;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton listeButton;
    private javax.swing.JButton silButton;
    private javax.swing.JButton temizleButton;
    // End of variables declaration//GEN-END:variables
}
