/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */

// Import library GUI, Service RFID, dan java awt
package id.smartpark;

import id.smartpark.gui.AdminPage;
import id.smartpark.gui.LoginPage;
import id.smartpark.services.SerialService;
import javax.swing.JOptionPane;


public class MainApp extends javax.swing.JFrame {
 
 // Inisialisasi logger untuk mencatat error atau log sistem jika diperlukan
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(MainApp.class.getName());

    /**
     * Constructor: Dijalankan pertama kali saat objek MainApp dibuat. 
    */
    public MainApp() {
        
        // 1. Koneksi hardware RFID sekali saja saat aplikasi start
        // Memakaii Singleton (getInstance) agar koneksi hanya ada satu di selruh aplikasi
        SerialService.getInstance().connect("COM3", 9600);

        // 2. Observer Pertama: Digunakan untuk mencetak log ke console setiap ada kartu masuk
        SerialService.getInstance().addHandler(tagId -> {
            System.out.println("Global Log [SmartPark]: Kartu " + tagId + " terdeteksi.");
        });
        
        // 3. Update UI jika ada pengetapan kartu (Opsional)
        SerialService.getInstance().addHandler(tagId -> {
            // SwingUtilities.invokeLater memastikan update UI berjalan di thread yang aman
            javax.swing.SwingUtilities.invokeLater(() -> {
                // Contoh: lblStatus.setText("Scanning: " + tagId); (untuk kode updaate status label)
            });
        });
        
        // Memanggil fungsi buatan Netbeans untuk generate komponen GUI (tombol, panel)
        initComponents();
        // Tentukan ukuran standar agar tidak gepeng saat pertama muncul
        this.setSize(1280, 720); 
        // Agar langsung Fullscreen saat dijalankan
        this.setExtendedState(javax.swing.JFrame.MAXIMIZED_BOTH); 
        this.setTitle("SmartPark ID - Management System");
        this.setLocationRelativeTo(null); // Agar muncul di tengah layar
    }

    /**
     * Logic initComponents biarkan sesuai hasil drag-and-drop NetBeans kamu.
     * Pastikan jButton1 adalah tombol "Admin Page".
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel2.setBackground(new java.awt.Color(106, 103, 139));

        jButton1.setBackground(new java.awt.Color(106, 103, 139));
        jButton1.setForeground(new java.awt.Color(255, 255, 255));
        jButton1.setText("Data Mahasiswa");
        jButton1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton3.setBackground(new java.awt.Color(106, 103, 139));
        jButton3.setForeground(new java.awt.Color(255, 255, 255));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Container_1.png"))); // NOI18N
        jButton3.setText("Pengaturan");
        jButton3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("SMARTPARK ID ");

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Container.png"))); // NOI18N

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 8)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(204, 204, 204));
        jLabel3.setText("Universitas Harkat Negeri");

        jButton2.setBackground(new java.awt.Color(106, 103, 139));
        jButton2.setForeground(new java.awt.Color(255, 255, 255));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Container (1).png"))); // NOI18N
        jButton2.setText("LOGOUT");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(106, 103, 139));
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setText("Beranda");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel3)))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(9, 9, 9)
                        .addComponent(jButton2))
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jButton5, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton3, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(13, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3)
                    .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.TRAILING))
                .addGap(102, 102, 102)
                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 23, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(59, 59, 59)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 31, Short.MAX_VALUE)
                .addComponent(jButton2)
                .addGap(20, 20, 20))
        );

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 618, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
      // 1. Bersihkan panel utama di MainApp
    jPanel1.removeAll();
    
    // 2. Gunakan BorderLayout agar isi panel melebar otomatis
    jPanel1.setLayout(new java.awt.BorderLayout());

    // 3. Inisialisasi AdminPage
    AdminPage adminPage = new AdminPage();
    
    // 4. Ambil jPanel5 (lapisan utama AdminPage)
    // Pastikan di AdminPage.java, variabel jPanel5 diset sebagai 'public static' atau 'public'
    javax.swing.JPanel lapisUtama = adminPage.getPanelUtama(); 
    
    // 5. Masukkan ke jPanel1 MainApp
    jPanel1.add(lapisUtama, java.awt.BorderLayout.CENTER);

    // 6. Refresh UI
    jPanel1.revalidate();
    jPanel1.repaint();

    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        int konfirmasi = JOptionPane.showConfirmDialog(
            this,
            "Yakin ingin logout?",
            "Konfirmasi",
            JOptionPane.YES_NO_OPTION
    );

        if (konfirmasi == JOptionPane.YES_OPTION) {
        new LoginPage().setVisible(true);
        this.dispose();
       }

    }//GEN-LAST:event_jButton2ActionPerformed

    //CEEEKKKKK MASUK GAAKK
    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set Nimbus Look and Feel agar tampilan modern */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }

        java.awt.EventQueue.invokeLater(() -> {

    id.smartpark.gui.LoginPage login =
            new id.smartpark.gui.LoginPage();

    login.setLocationRelativeTo(null);
    login.setVisible(true);

});
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    // End of variables declaration//GEN-END:variables
}
