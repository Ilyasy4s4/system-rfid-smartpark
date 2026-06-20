package id.smartpark.services;

import id.smartpark.objects.User;
import id.smartpark.dao.GenericDAO;
import id.smartpark.gui.AdminPage;
import id.smartpark.gui.LoginPage;

import com.mongodb.client.model.Filters;
import id.smartpark.MainApp;
import id.smartpark.util.SecurityUtils;
import java.awt.Frame;
import java.time.LocalDateTime;
import javax.swing.JOptionPane;

public class AuthService {

    // Collection admin_users
    private final GenericDAO<User> userDAO =
            new GenericDAO<>("admin_users", User.class);

    /**
     * Login Admin
     */
    public void login(String username,
                      String plainPassword,
                      LoginPage loginPage) {

 String hashedPassword = SecurityUtils.getHash(
        plainPassword,
        SecurityUtils.SHA_256
);

User user = userDAO.findOne(
        Filters.and(
                Filters.eq("username", username),
                Filters.eq("password", hashedPassword)
        )
);

        if (user != null) {

            // Update waktu login terakhir
            user.setLastLogin(LocalDateTime.now());

            userDAO.update(
                    Filters.eq("username", username),
                    user
            );

            JOptionPane.showMessageDialog(
                    null,
                    "Selamat Datang, " + user.getFullname()
            );

           MainApp mainApp = new MainApp();
            mainApp.setLocationRelativeTo(null);
            mainApp.setVisible(true);
            mainApp.setExtendedState(Frame.MAXIMIZED_BOTH);

            loginPage.dispose();

        } else {

            JOptionPane.showMessageDialog(
                    null,
                    "Username atau Password Salah!",
                    "Login Gagal",
                    JOptionPane.ERROR_MESSAGE
            );
        }
    }

    /**
     * Menambahkan Admin Baru
     */
    public void registerUser(String fullname,
                             String username,
                             String password) {

String hashedPassword = SecurityUtils.getHash(
        password,
        SecurityUtils.SHA_256
);

User newUser = new User(
        fullname,
        username,
        hashedPassword,
        null
);

        try {

            // Cek username sudah ada atau belum
            User existingUser = userDAO.findOne(
                    Filters.eq("username", username)
            );

            if (existingUser != null) {
                JOptionPane.showMessageDialog(
                        null,
                        "Username sudah digunakan!"
                );
                return;
            }

            userDAO.save(newUser);

            JOptionPane.showMessageDialog(
                    null,
                    "Admin berhasil ditambahkan!"
            );

        } catch (Exception e) {

            JOptionPane.showMessageDialog(
                    null,
                    "Gagal menyimpan admin : " + e.getMessage()
            );
        }
    }
}