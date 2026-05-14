package id.smartpark.services;

import id.smartpark.gui.AdminPage;
import id.smartpark.dao.GenericDAO;
import id.smartpark.objects.Mahasiswa;
import com.mongodb.client.model.Filters;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import org.bson.conversions.Bson;

public class MahasiswaService {

    private final GenericDAO<Mahasiswa> DAO;

    public MahasiswaService() {
        this.DAO = new GenericDAO<>("mahasiswa", Mahasiswa.class);
    }

    public void tambahMahasiswa(Mahasiswa m) {
        DAO.save(m);
    }

    public void tampilMahasiswa(JPanel panelTarget, String key) {
        List<Mahasiswa> daftar;
        if (key.isEmpty()) {
            daftar = DAO.findAll();
        } else {
            daftar = cariMahasiswa(key);
        }

        panelTarget.removeAll();
        panelTarget.setLayout(new BorderLayout());
        panelTarget.setBackground(new Color(44, 62, 80)); 

        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 15, 15));
        gridPanel.setOpaque(false);
        gridPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        for (Mahasiswa m : daftar) {
            JPanel cardPanel = new JPanel(new GridLayout(4, 1, 5, 5));
            cardPanel.setBackground(new Color(52, 152, 219)); 
            cardPanel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.WHITE, 1, true),
                BorderFactory.createEmptyBorder(15, 15, 15, 15)
            ));

            JLabel lblNama = new JLabel("Nama: " + m.getNamaLengkap());
            lblNama.setForeground(Color.WHITE);
            JLabel lblNIM = new JLabel("NIM: " + m.getNim());
            lblNIM.setForeground(Color.WHITE);
            JLabel lblProdi = new JLabel("Prodi: " + m.getProdi());
            lblProdi.setForeground(Color.WHITE);

            JPanel controlPanel = new JPanel(new GridLayout(1, 2, 10, 0));
            controlPanel.setOpaque(false);

            JButton btnEdit = new JButton("Edit");
            btnEdit.setCursor(new Cursor(Cursor.HAND_CURSOR));
            btnEdit.addActionListener((ActionEvent e) -> {
                AdminPage.txtUID.setText(m.getUidRfid());
                AdminPage.txtKRID.setText(m.getNim()); // Gunakan nama variabel yang ada di AdminPage
                AdminPage.txtKRID.setEnabled(false); 
                AdminPage.txtKRName.setText(m.getNamaLengkap());
                AdminPage.txtKRDept.setSelectedItem(m.getProdi());
                AdminPage.btnUpdate.setEnabled(true);
                AdminPage.btnSave.setEnabled(false);
            });

            JButton btnDel = new JButton("Hapus");
            btnDel.setBackground(Color.RED);
            btnDel.setForeground(Color.WHITE);
            btnDel.addActionListener((ActionEvent e) -> {
                int confirm = JOptionPane.showConfirmDialog(null, "Hapus data " + m.getNamaLengkap() + "?", "Konfirmasi", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    hapusMahasiswa(m.getNim());
                }
            });

            controlPanel.add(btnEdit);
            controlPanel.add(btnDel);

            cardPanel.add(lblNama);
            cardPanel.add(lblNIM);
            cardPanel.add(lblProdi);
            cardPanel.add(controlPanel);

            gridPanel.add(cardPanel);
        }

        panelTarget.add(gridPanel, BorderLayout.NORTH);
        panelTarget.revalidate();
        panelTarget.repaint();
    }

    public List<Mahasiswa> cariMahasiswa(String key) {
        List<Bson> filters = new ArrayList<>();
        for (Field field : Mahasiswa.class.getDeclaredFields()) {
            if (field.getName().equals("uidRfid")) continue;
            filters.add(Filters.regex(field.getName(), key, "i"));
        }
        return DAO.findMany(Filters.or(filters));
    }

    public void updateMahasiswa(Mahasiswa m) {
        Bson filter = Filters.eq("nim", m.getNim());
        DAO.update(filter, m);
        AdminPage.showData("");
        JOptionPane.showMessageDialog(null, "Data diperbarui!");
    }

    public void hapusMahasiswa(String nim) {
        DAO.delete(Filters.eq("nim", nim));
        AdminPage.showData("");
        JOptionPane.showMessageDialog(null, "Data dihapus.");
    }
}