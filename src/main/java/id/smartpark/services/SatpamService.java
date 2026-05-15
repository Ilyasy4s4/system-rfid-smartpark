/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package id.smartpark.services;

import id.smartpark.gui.DataSatpam;
import id.smartpark.dao.GenericDAO;
import id.smartpark.objects.Satpam;
import com.mongodb.client.model.Filters;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bson.conversions.Bson;

/**
 * SatpamService - Menangani logika CRUD untuk data Petugas Jaga.
 * Target: Koleksi "satpam" di MongoDB.
 */
public class SatpamService {

    private final GenericDAO<Satpam> DAO;

    public SatpamService() {
        // Inisialisasi DAO untuk objek Satpam
        this.DAO = new GenericDAO<>("satpam", Satpam.class);
    }

    public void tambahSatpam(Satpam s) {
        DAO.save(s);
        JOptionPane.showMessageDialog(null, "Petugas berhasil didaftarkan!");
    }

    public void tampilSatpam(JPanel panelTarget, String key) {
        List<Satpam> daftar;
        if (key.isEmpty()) {
            daftar = DAO.findAll();
        } else {
            daftar = cariSatpam(key);
        }

        panelTarget.removeAll();
        panelTarget.setLayout(new BorderLayout());
        panelTarget.setBackground(Color.WHITE); 

        // Membuat Panel Grid (3 kolom)
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 20, 20));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Satpam s : daftar) {
            // Desain Card Satpam
            JPanel cardPanel = new JPanel(new GridLayout(4, 1, 5, 5));
            cardPanel.setBackground(new Color(245, 245, 245)); 
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(200, 200, 200), 1, true),
                BorderFactory.createEmptyBorder(12, 12, 12, 12)
            ));

            JLabel lblNama = new JLabel(s.getNamaLengkap());
            lblNama.setFont(new Font("Segoe UI", Font.BOLD, 13));
            
            JLabel lblUser = new JLabel("User: " + s.getUsername());
            lblUser.setForeground(Color.GRAY);
            
            JLabel lblPass = new JLabel("Pass: ********"); // Password disembunyikan demi keamanan
            lblPass.setForeground(Color.LIGHT_GRAY);

            JPanel controlPanel = new JPanel(new GridLayout(1, 2, 8, 0));
            controlPanel.setOpaque(false);

            JButton btnEdit = new JButton("Edit");
            btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnEdit.addActionListener((ActionEvent e) -> {
                DataSatpam.txtUID.setText(s.getUsername());
                DataSatpam.txtKRID.setText(s.getPassword()); 
                DataSatpam.txtUID.setEnabled(false); // Username tidak boleh diganti saat edit
                DataSatpam.txtKRName.setText(s.getNamaLengkap());
                DataSatpam.btnUpdate.setEnabled(true);
                DataSatpam.btnSave.setEnabled(false);
            });

            JButton btnDel = new JButton("Hapus");
            btnDel.setBackground(new Color(231, 76, 60));
            btnDel.setForeground(Color.WHITE);
            btnDel.addActionListener((ActionEvent e) -> {
                int confirm = JOptionPane.showConfirmDialog(null, 
                        "Hapus akun " + s.getUsername() + "?", "Konfirmasi", 
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    hapusSatpam(s.getUsername());
                }
            });

            controlPanel.add(btnEdit);
            controlPanel.add(btnDel);

            cardPanel.add(lblNama);
            cardPanel.add(lblUser);
            cardPanel.add(lblPass);
            cardPanel.add(controlPanel);

            gridPanel.add(cardPanel);
        }

        panelTarget.add(gridPanel, BorderLayout.NORTH);
        panelTarget.revalidate();
        panelTarget.repaint();
    }

    public List<Satpam> cariSatpam(String key) {
        Bson filter = Filters.or(
            Filters.regex("username", key, "i"),
            Filters.regex("namaLengkap", key, "i")
        );
        return DAO.findMany(filter);
    }

    public void updateSatpam(Satpam s) {
        Bson filter = Filters.eq("username", s.getUsername());
        DAO.update(filter, s);
        DataSatpam.showData(""); 
        JOptionPane.showMessageDialog(null, "Akun petugas diperbarui!");
    }

    public void hapusSatpam(String username) {
        DAO.delete(Filters.eq("username", username));
        DataSatpam.showData(""); 
        JOptionPane.showMessageDialog(null, "Akun telah dihapus.");
    }
}
