import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;

public class login extends JFrame{
    private JPanel loginpage;
    private JPasswordField textpassword;
    private JTextField textemail;
    private JLabel createaccount;
    private JLabel Admin;
    private JButton buttonlogin;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public void clear(){
        textemail.setText("");
        textpassword.setText("");
    }

    public static void main(String[] args) {
        JFrame frame = new JFrame("login");
        frame.setContentPane(new login().loginpage);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(600,200,300,300);
    }

    public void openAdminWindow() {
        JFrame frame = new JFrame("Admin Login");
        frame.setContentPane(new admin().adminpage);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(600,200,300,350);
    }

    public void openCreateAccountWindow() {
        JFrame frame = new JFrame("New Account");
        frame.setContentPane(new create_new_account().cna);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(600,200,300,450);
    }

    public void openBookingWindow() {
        JFrame frame = new JFrame("booking");
        frame.setContentPane(new booking().bookingpage);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(600, 200, 300, 350);
    }

    public boolean checkCredentials (String email, String password){
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost/trsdb", "root", "1234");
            String query = "SELECT * FROM accounts_table WHERE email = ? AND password = ?";
            ps = con.prepareStatement(query);
            ps.setString(1, email);
            ps.setString(2, password);
            rs = ps.executeQuery();
            return rs.next();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public login() {

        Admin.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openAdminWindow();
            }
        });

        createaccount.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                openCreateAccountWindow();
            }
        });

        buttonlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String Email = textemail.getText();
                String Password = new String(textpassword.getPassword());
                if (checkCredentials(Email, Password)) {
                    openBookingWindow();
                    clear();
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid email or password.");
                }
            }
        });
    }

}