/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dao.BilgiDao;
import dao.EmlakBilgiDao;
import dao.EmlakDao;
import entity.BilgiDekorasyon;
import entity.Emlak;
import entity.EmlakBilgi;
import entity.BilgiDekorasyon;
import entity.EmlakYontemHesapla;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javafx.scene.control.ComboBox;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import entity.IBilgi;
import entity.KiralikEmlak;
import entity.SatilikEmlak;

/**
 *
 * @author m07er
 */
public class EmlakBilgiPanel extends javax.swing.JFrame {

    /**
     * Creates new form EmlakBilgiPanel
     */
    EmlakDao emlakDao = new EmlakDao();
    BilgiDao bilgiDao = new BilgiDao();
    EmlakBilgiDao emlakBilgiDao = new EmlakBilgiDao();
    DefaultTableModel tablo = new DefaultTableModel();
    ArrayList<Emlak> emlakList;
    ArrayList<BilgiDekorasyon> bilgiList;
    ArrayList<EmlakBilgi> emlakBilgiListe;
    EmlakBilgi emlakBilgi = new EmlakBilgi();

    public EmlakBilgiPanel() {
        initComponents();
        this.setLocationRelativeTo(null);
        emlakbilgi_id.setVisible(false);
        emlak_id.setVisible(false);
        bilgi_id.setVisible(false);
        emlakcbveri();
        kategoricbveri();
        turcbveri();
        listele();

    }

    void emlakcbveri() {
        //combobox veri çekme
        for (int i = 0; i < emlakDao.list().size(); i++) {
            emlakcb.addItem(emlakDao.list().get(i).getEmlak_adres());
        }
    }

    ArrayList<String> kontrol;

    void kategoricbveri() {
        //combobox veri çekme
        kontrol = new ArrayList<>();
        for (int i = 0; i < bilgiDao.list().size(); i++) {
            String tmp = bilgiDao.list().get(i).getBilgi_kategori();
            if (kontrol.size() >= 1) {
                boolean k = false;
                for (int j = 0; j < kontrol.size(); j++) {
                    String get = kontrol.get(j);
                    if (get.equals(tmp)) {
                        k = true;
                        break;
                    }
                }
                if (!k) {
                    kontrol.add(tmp);
                    kategoricb.addItem(tmp);
                }
            } else {
                kontrol.add(tmp);
                kategoricb.addItem(tmp);
            }
        }
    }

    void turcbveri() {
        //combobox veri çekme
        kontrol = new ArrayList<>();
        for (int i = 0; i < bilgiDao.list().size(); i++) {
            String tmp = bilgiDao.list().get(i).getBilgi_tur();
            if (kontrol.size() >= 1) {
                boolean k = false;
                for (int j = 0; j < kontrol.size(); j++) {
                    String get = kontrol.get(j);
                    if (get.equals(tmp)) {
                        k = true;
                        break;
                    }
                }
                if (!k) {
                    kontrol.add(tmp);
                    turcb.addItem(tmp);
                }
            } else {
                kontrol.add(tmp);
                turcb.addItem(tmp);
            }

        }
    }

    void temizle() {
        emlakcb.setSelectedIndex(0);
        kategoricb.setSelectedIndex(0);
        turcb.setSelectedIndex(0);
        this.tablo.setRowCount(0);
    }

    void listele() {
        ResultSet rsList;
        rsList = emlakBilgiDao.rsList();
        this.tablo = (DefaultTableModel) emlakBilgiList.getModel();
        this.tablo.setRowCount(0);
        try {
            while (rsList.next()) {
                Object[] row = new Object[14];
                for (int i = 1; i <= 14; i++) {
                    row[i - 1] = rsList.getObject(i);
                }
                tablo.addRow(row);
            }
            emlakBilgiList.setModel(tablo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hata!!" + ex);
        }
    }

    void ekle() {

        Long emlakID = null;
        Long bilgiID = null;
        Double emlakFiyat = null;
        String bilgiTur = "";
        Double yeniFiyat = null;

        emlakList = emlakDao.list(); //gönderdiğimiz adresin idsini bulur
        for (int i = 0; i < emlakList.size(); i++) {
            if (emlakList.get(i).getEmlak_adres().contains((CharSequence) emlakcb.getSelectedItem())) {
                emlakID = emlakList.get(i).getEmlak_id();
                emlakFiyat = emlakList.get(i).getEmlak_fiyat();
                
            }
        }

        bilgiList = bilgiDao.list(); //gönderdiğimiz kategorinin idsini bulur
        for (int i = 0; i < bilgiList.size(); i++) {
            if (bilgiList.get(i).getBilgi_kategori().contains((CharSequence) kategoricb.getSelectedItem()) && bilgiList.get(i).getBilgi_tur().contains((CharSequence) turcb.getSelectedItem())) {
                bilgiID = bilgiList.get(i).getBilgi_id();
                bilgiTur = bilgiList.get(i).getBilgi_tur();             
            }
        }


        if (bilgiTur.equalsIgnoreCase("Kiralık")) {
            EmlakYontemHesapla yntm = new EmlakYontemHesapla(new KiralikEmlak());
            yeniFiyat = yntm.hesapla(emlakFiyat);
            this.emlakBilgi.setEmlak(this.emlakDao.detail(emlakID));
            this.emlakBilgi.setBilgi(this.bilgiDao.detail(bilgiID));
            this.emlakBilgi.setYeniFiyat(yeniFiyat);
            this.emlakBilgiDao.create(emlakBilgi);
        }

        if (bilgiTur.equalsIgnoreCase("Satılık")) {
            EmlakYontemHesapla yntm = new EmlakYontemHesapla(new SatilikEmlak());
            yeniFiyat = yntm.hesapla(emlakFiyat);
            this.emlakBilgi.setEmlak(this.emlakDao.detail(emlakID));
            this.emlakBilgi.setBilgi(this.bilgiDao.detail(bilgiID));
            this.emlakBilgi.setYeniFiyat(yeniFiyat);
            this.emlakBilgiDao.create(emlakBilgi);
        }

    }

    void duzenle() {

        Long emlakID = null;
        Long bilgiID = null;
        Double emlakFiyat = null;
        String bilgiTur = "";
        Double yeniFiyat = null;

        emlakList = emlakDao.list(); //gönderdiğimiz adresin idsini bulur
        for (int i = 0; i < emlakList.size(); i++) {
            if (emlakList.get(i).getEmlak_adres().contains((CharSequence) emlakcb.getSelectedItem())) {
                emlakID = emlakList.get(i).getEmlak_id();
                emlakFiyat = emlakList.get(i).getEmlak_fiyat();
                
            }
        }

        bilgiList = bilgiDao.list(); //gönderdiğimiz kategorinin idsini bulur
        for (int i = 0; i < bilgiList.size(); i++) {
            if (bilgiList.get(i).getBilgi_kategori().contains((CharSequence) kategoricb.getSelectedItem()) && bilgiList.get(i).getBilgi_tur().contains((CharSequence) turcb.getSelectedItem())) {
                bilgiID = bilgiList.get(i).getBilgi_id();
                bilgiTur = bilgiList.get(i).getBilgi_tur();
            }
        }

        if (bilgiTur.equalsIgnoreCase("Kiralık")) {
            EmlakYontemHesapla yntm = new EmlakYontemHesapla(new KiralikEmlak());
            yeniFiyat = yntm.hesapla(emlakFiyat);
            this.emlakBilgi.setEmlak(this.emlakDao.detail(emlakID));
            this.emlakBilgi.setBilgi(this.bilgiDao.detail(bilgiID));
            this.emlakBilgi.setEmlakBilgi_id(Long.parseLong(emlakbilgi_id.getText()));
            this.emlakBilgi.setYeniFiyat(yeniFiyat);
            this.emlakBilgiDao.update(this.emlakBilgi);
        }

        if (bilgiTur.equalsIgnoreCase("Satılık")) {
            EmlakYontemHesapla yntm = new EmlakYontemHesapla(new SatilikEmlak());
            yeniFiyat = yntm.hesapla(emlakFiyat);
            this.emlakBilgi.setEmlak(this.emlakDao.detail(emlakID));
            this.emlakBilgi.setBilgi(this.bilgiDao.detail(bilgiID));
            this.emlakBilgi.setEmlakBilgi_id(Long.parseLong(emlakbilgi_id.getText()));
            this.emlakBilgi.setYeniFiyat(yeniFiyat);
            this.emlakBilgiDao.update(this.emlakBilgi);
        }

    }

    void sil() {
        emlakBilgi.setEmlakBilgi_id(Long.parseLong(emlakbilgi_id.getText()));
        emlakBilgiDao.delete(this.emlakBilgi);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        emlakcb = new javax.swing.JComboBox<>();
        kategoricb = new javax.swing.JComboBox<>();
        turcb = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        emlakBilgiList = new javax.swing.JTable();
        duzenleButton = new javax.swing.JButton();
        silButton = new javax.swing.JButton();
        ekleButton = new javax.swing.JButton();
        temizleButton = new javax.swing.JButton();
        listeleButton = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        emlak_id = new javax.swing.JLabel();
        bilgi_id = new javax.swing.JLabel();
        emlakbilgi_id = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Emlak");

        jLabel2.setText("Kategori");

        jLabel4.setText("Tür");

        emlakcb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emlakcbMouseClicked(evt);
            }
        });
        emlakcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emlakcbActionPerformed(evt);
            }
        });

        emlakBilgiList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Emlak Bilgi ID", "Emlak ID", "m2", "Oda sayısı", "Adres", "Isıtma", "Aidat", "Eşyalı", "Fiyat", "Bilgi ID", "Kategori", "Tür", "Tarih", "Yeni Fiyat"
            }
        ));
        emlakBilgiList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                emlakBilgiListMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                emlakBilgiListMouseEntered(evt);
            }
        });
        jScrollPane1.setViewportView(emlakBilgiList);

        duzenleButton.setText("Düzenle");
        duzenleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duzenleButtonActionPerformed(evt);
            }
        });

        silButton.setText("Sil");
        silButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                silButtonActionPerformed(evt);
            }
        });

        ekleButton.setText("Ekle");
        ekleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ekleButtonActionPerformed(evt);
            }
        });

        temizleButton.setText("Temizle");
        temizleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temizleButtonActionPerformed(evt);
            }
        });

        listeleButton.setText("Listele");
        listeleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeleButtonActionPerformed(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(51, 102, 255));
        jLabel3.setText("Geri dönmek için tıklayınız.");
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(50, 50, 50)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel3)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addGap(243, 243, 243)
                            .addComponent(ekleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(duzenleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(silButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(listeleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(temizleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(18, 18, 18)
                            .addComponent(emlakcb, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(36, 36, 36)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(41, 41, 41)
                            .addComponent(kategoricb, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(37, 37, 37)
                            .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 71, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(turcb, javax.swing.GroupLayout.PREFERRED_SIZE, 300, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 1257, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(emlakbilgi_id, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(emlak_id)
                    .addComponent(bilgi_id))
                .addGap(46, 46, 46))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(34, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(emlakcb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(kategoricb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(turcb, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(duzenleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(silButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(temizleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(listeleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(ekleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 37, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 474, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(emlak_id)
                        .addGap(18, 18, 18)
                        .addComponent(bilgi_id)
                        .addGap(18, 18, 18)
                        .addComponent(emlakbilgi_id)))
                .addGap(18, 18, 18)
                .addComponent(jLabel3)
                .addGap(8, 8, 8))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        // TODO add your handling code here:
        IslemPanel islemPanel = new IslemPanel();
        islemPanel.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jLabel3MouseClicked

    private void listeleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeleButtonActionPerformed
        // TODO add your handling code here:
        listele();
    }//GEN-LAST:event_listeleButtonActionPerformed

    private void temizleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temizleButtonActionPerformed
        // TODO add your handling code here:
        temizle();
    }//GEN-LAST:event_temizleButtonActionPerformed

    private void ekleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ekleButtonActionPerformed
        // TODO add your handling code here:
        ekle();
        listele();
    }//GEN-LAST:event_ekleButtonActionPerformed

    private void emlakcbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emlakcbMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_emlakcbMouseClicked

    private void silButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silButtonActionPerformed
        // TODO add your handling code here:
        sil();
        temizle();
        listele();
    }//GEN-LAST:event_silButtonActionPerformed

    private void emlakBilgiListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emlakBilgiListMouseClicked
        // TODO add your handling code here:
        int i = emlakBilgiList.getSelectedRow();
        TableModel model = emlakBilgiList.getModel();
        emlakbilgi_id.setText(String.valueOf(emlakBilgiList.getValueAt(emlakBilgiList.getSelectedRow(), 0)));
        emlak_id.setText(String.valueOf(emlakBilgiList.getValueAt(emlakBilgiList.getSelectedRow(), 1)));
        bilgi_id.setText(String.valueOf(emlakBilgiList.getValueAt(emlakBilgiList.getSelectedRow(), 9)));
        emlakcb.setSelectedItem(emlakBilgiList.getValueAt(emlakBilgiList.getSelectedRow(), 4));
        kategoricb.setSelectedItem(emlakBilgiList.getValueAt(emlakBilgiList.getSelectedRow(), 10));
        turcb.setSelectedItem(emlakBilgiList.getValueAt(emlakBilgiList.getSelectedRow(), 11));

    }//GEN-LAST:event_emlakBilgiListMouseClicked

    private void duzenleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duzenleButtonActionPerformed
        // TODO add your handling code here:
        duzenle();
        listele();
    }//GEN-LAST:event_duzenleButtonActionPerformed

    private void emlakcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emlakcbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_emlakcbActionPerformed

    private void emlakBilgiListMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_emlakBilgiListMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_emlakBilgiListMouseEntered

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
            java.util.logging.Logger.getLogger(EmlakBilgiPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(EmlakBilgiPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(EmlakBilgiPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(EmlakBilgiPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new EmlakBilgiPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel bilgi_id;
    private javax.swing.JButton duzenleButton;
    private javax.swing.JButton ekleButton;
    private javax.swing.JTable emlakBilgiList;
    private javax.swing.JLabel emlak_id;
    private javax.swing.JLabel emlakbilgi_id;
    private javax.swing.JComboBox<String> emlakcb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox<String> kategoricb;
    private javax.swing.JButton listeleButton;
    private javax.swing.JButton silButton;
    private javax.swing.JButton temizleButton;
    private javax.swing.JComboBox<String> turcb;
    // End of variables declaration//GEN-END:variables
}
