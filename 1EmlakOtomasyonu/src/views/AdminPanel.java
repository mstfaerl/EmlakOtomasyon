/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package views;

import dao.GrupDao;
import dao.KullaniciDao;
import entity.Grup;
import entity.Kullanici;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;

/**
 *
 * @author m07er
 */
public class AdminPanel extends javax.swing.JFrame {

    /**
     * Creates new form AdminPanel
     */
    DefaultTableModel tablo = new DefaultTableModel();
    KullaniciDao kullaniciDao = new KullaniciDao();
    Kullanici kullanici = new Kullanici();
    GrupDao grupDao = new GrupDao();
    ArrayList<Grup> gList;

    public AdminPanel() {
        initComponents();
        this.setLocationRelativeTo(null);
        kullanici_id.setVisible(false);
        cbveri();
        listele();
    }

    void cbveri() {
        //combobox veri çekme
        for (int i = 0; i < grupDao.list().size(); i++) {
            grupcb.addItem(grupDao.list().get(i).getGrup_adi());
        }
    }

    void listele() {
        ResultSet rsList;
        rsList = kullaniciDao.rsList();
        this.tablo = (DefaultTableModel) kullaniciList.getModel();
        this.tablo.setRowCount(0);
        try {
            while (rsList.next()) {
                Object[] row = new Object[6];
                for (int i = 1; i <= 6; i++) {
                    row[i - 1] = rsList.getObject(i);
                }
                tablo.addRow(row);
            }
            kullaniciList.setModel(tablo);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Hata!!" + ex);
        }
    }

    void ekle() {

        Long k_id = null;
        gList = grupDao.list();

        for (int i = 0; i < gList.size(); i++) {
            if (gList.get(i).getGrup_adi().contains((CharSequence) grupcb.getSelectedItem())) {
                k_id = gList.get(i).getGrup_id();
            }
        }

        String adi = aditf.getText();
        String email = emailtf.getText();
        String sifre = sifretf.getText();
        String telno = telnotf.getText();

        boolean emailKontrol = false;
        boolean telnoKontrol = false;

        if (adi.equalsIgnoreCase("") || email.equalsIgnoreCase("") || sifre.equalsIgnoreCase("") || telno.equalsIgnoreCase("")) {
            JOptionPane.showMessageDialog(rootPane, "Lütfen boş alanları doldurunuz.");
        } else {
            for (int i = 0; i < this.kullaniciDao.list().size(); i++) {
                if (email.equalsIgnoreCase(this.kullaniciDao.list().get(i).getKullanici_email())) {
                    emailKontrol = true;
                }
                if (telno.equalsIgnoreCase(this.kullaniciDao.list().get(i).getKullanici_telno())) {
                    telnoKontrol = true;
                }
            }
            if (emailKontrol && telnoKontrol) {
                JOptionPane.showMessageDialog(rootPane, "Bu email ve telefon numarası için bir kullanici zaten mevcut. Lütfen faklı bir email ve telefon numarası ile deneyiniz!");
            } else if (telnoKontrol) {
                JOptionPane.showMessageDialog(rootPane, "Bu telefon numarası için bir kullanici zaten mevcut. Lütfen faklı bir telefon numarası ile deneyiniz!");
            } else if (emailKontrol) {
                JOptionPane.showMessageDialog(rootPane, "Bu email için bir kullanici zaten mevcut. Lütfen faklı bir email ile deneyiniz!");
            } else {
                this.kullanici.setKullanici_adi(adi);
                this.kullanici.setKullanici_email(email);
                this.kullanici.setKullanici_sifre(sifre);
                this.kullanici.setKullanici_telno(telno);
                this.kullanici.setGrup(this.grupDao.detail(k_id));
                this.kullaniciDao.create(kullanici);

                for (int i = 0; i < this.kullaniciDao.list().size(); i++) {
                    if (emailtf.getText().equalsIgnoreCase(this.kullaniciDao.list().get(i).getKullanici_email())) {
                        JOptionPane.showMessageDialog(rootPane, "Başarıyla oluşturuldu.");
                    }
                }
            }
        }

    }

    void temizle() {
        aditf.setText(null);
        emailtf.setText(null);
        sifretf.setText(null);
        telnotf.setText(null);
        grupcb.setSelectedIndex(0);
        this.tablo.setRowCount(0);
    }

    void duzenle() {
        
        this.tablo = (DefaultTableModel) kullaniciList.getModel();
        Long k_id = null;
        gList = grupDao.list();
        // seçilen itemin id sini bulma 
        for (int i = 0; i < gList.size(); i++) {
            if (gList.get(i).getGrup_adi().contains((CharSequence) grupcb.getSelectedItem())) {
                k_id = gList.get(i).getGrup_id();
            }
        }
        String adi = aditf.getText();
        String email = emailtf.getText();
        String sifre = sifretf.getText();
        String telno = telnotf.getText();

        this.kullanici.setKullanici_adi(adi);
        this.kullanici.setKullanici_email(email);
        this.kullanici.setKullanici_sifre(sifre);
        this.kullanici.setKullanici_telno(telno);
        this.kullanici.setGrup(this.grupDao.detail(k_id));
        this.kullanici.setKullanici_id(Long.parseLong(kullanici_id.getText()));
        this.kullaniciDao.update(this.kullanici);

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
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        emailtf = new javax.swing.JTextField();
        telnotf = new javax.swing.JTextField();
        sifretf = new javax.swing.JPasswordField();
        aditf = new javax.swing.JTextField();
        grupcb = new javax.swing.JComboBox<>();
        ekleButton = new javax.swing.JButton();
        silButton = new javax.swing.JButton();
        duzenleButton = new javax.swing.JButton();
        listeleButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        kullaniciList = new javax.swing.JTable();
        temizleButton = new javax.swing.JButton();
        kullanici_id = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 16)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(51, 102, 255));
        jLabel1.setText("Geri dönmek için tıklayınız.");
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("Email");

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel3.setText("Sifre");

        jLabel4.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel4.setText("Telefon Numarası");

        jLabel5.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel5.setText("Adı Soyadı");

        jLabel6.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel6.setText("Grup");

        grupcb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                grupcbActionPerformed(evt);
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

        duzenleButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        duzenleButton.setText("Düzenle");
        duzenleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                duzenleButtonActionPerformed(evt);
            }
        });

        listeleButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        listeleButton.setText("Listele");
        listeleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listeleButtonActionPerformed(evt);
            }
        });

        kullaniciList.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Kullanıcı ID", "Adı Soyadı", "Email", "Sifre", "Telefon No", "Grup"
            }
        ));
        kullaniciList.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                kullaniciListMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(kullaniciList);

        temizleButton.setFont(new java.awt.Font("Tahoma", 0, 15)); // NOI18N
        temizleButton.setText("Temizle");
        temizleButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                temizleButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 138, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(kullanici_id))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(silButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(ekleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(temizleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(duzenleButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listeleButton, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(sifretf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(grupcb, javax.swing.GroupLayout.Alignment.TRAILING, 0, 200, Short.MAX_VALUE)
                        .addComponent(telnotf, javax.swing.GroupLayout.Alignment.TRAILING)
                        .addComponent(aditf, javax.swing.GroupLayout.Alignment.TRAILING))
                    .addComponent(emailtf, javax.swing.GroupLayout.PREFERRED_SIZE, 200, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 723, Short.MAX_VALUE)
                .addGap(25, 25, 25))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(7, 7, 7)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(jLabel5)
                            .addComponent(aditf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(9, 9, 9)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2)
                            .addComponent(emailtf, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(13, 13, 13)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel3)
                            .addComponent(sifretf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(telnotf, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(jLabel4))
                                .addGap(18, 18, 18)
                                .addComponent(grupcb, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.TRAILING))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(ekleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(duzenleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(silButton, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(listeleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(kullanici_id))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(temizleButton, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 446, Short.MAX_VALUE))
                .addGap(41, 41, 41))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        // TODO add your handling code here:
        IslemPanel islemPanel = new IslemPanel();
        islemPanel.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jLabel1MouseClicked

    private void listeleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listeleButtonActionPerformed
        listele();
    }//GEN-LAST:event_listeleButtonActionPerformed

    private void ekleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ekleButtonActionPerformed
        // TODO add your handling code here:
        ekle();
        listele();
    }//GEN-LAST:event_ekleButtonActionPerformed

    private void temizleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_temizleButtonActionPerformed
        // TODO add your handling code here:
        temizle();
    }//GEN-LAST:event_temizleButtonActionPerformed

    private void kullaniciListMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_kullaniciListMouseClicked
        // TODO add your handling code here:
        int i = kullaniciList.getSelectedRow();
        TableModel model = kullaniciList.getModel();
        kullanici_id.setText(String.valueOf(kullaniciList.getValueAt(kullaniciList.getSelectedRow(), 0)));
        aditf.setText(model.getValueAt(i, 1).toString()); //JTable hangi satıra tıklarsak o satırı textfiela atar.
        emailtf.setText(model.getValueAt(i, 2).toString());
        sifretf.setText(model.getValueAt(i, 3).toString());
        telnotf.setText(model.getValueAt(i, 4).toString());
        grupcb.setSelectedItem(kullaniciList.getValueAt(kullaniciList.getSelectedRow(), 5));
        /*String grup_adi = model.getValueAt(i, 5).toString();  
        switch (grup_adi) {
            case "Admin":
                grupcb.setSelectedIndex(0);
                break;
            case "Kullanıcı":
                grupcb.setSelectedIndex(1);
                break;
        }*/

    }//GEN-LAST:event_kullaniciListMouseClicked

    private void silButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_silButtonActionPerformed
        // TODO add your handling code here:
        kullanici.setKullanici_id(Long.parseLong(kullanici_id.getText()));
        kullaniciDao.delete(this.kullanici);
        temizle();
        listele();
    }//GEN-LAST:event_silButtonActionPerformed

    private void duzenleButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_duzenleButtonActionPerformed
        // TODO add your handling code here:
        duzenle();
        listele();
    }//GEN-LAST:event_duzenleButtonActionPerformed

    private void grupcbActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_grupcbActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_grupcbActionPerformed

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
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminPanel.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminPanel().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField aditf;
    private javax.swing.JButton duzenleButton;
    private javax.swing.JButton ekleButton;
    private javax.swing.JTextField emailtf;
    private javax.swing.JComboBox<String> grupcb;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable kullaniciList;
    private javax.swing.JLabel kullanici_id;
    private javax.swing.JButton listeleButton;
    private javax.swing.JPasswordField sifretf;
    private javax.swing.JButton silButton;
    private javax.swing.JTextField telnotf;
    private javax.swing.JButton temizleButton;
    // End of variables declaration//GEN-END:variables
}
