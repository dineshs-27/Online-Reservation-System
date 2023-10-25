import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class create_new_account {
    private JTextField textname;
    private JTextField textemail;
    private JPasswordField textpassword;
    private JButton buttonsave;
    public JPanel cna;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public void clear(){
        textname.setText("");
        textemail.setText("");
        textpassword.setText("");
    }

    public void connect(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/trsdb", "root", "1234");
        }
        catch (Exception e){
            e.printStackTrace();
        }
    }

    public create_new_account() {
        connect();

        buttonsave.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textname.getText().isEmpty() ||textemail.getText().isEmpty() ||textpassword.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing Information");
                }else{
                    try{
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trsdb","root","1234");
                        ps = con.prepareStatement("INSERT INTO accounts_table (name, email, password) VALUES (?,?,?)");
                        ps.setString(1, textname.getText());
                        ps.setString(2, textemail.getText());
                        ps.setString(3, textpassword.getText());
                        ps.executeUpdate();
                        JOptionPane.showMessageDialog(null, "Account Added !");
                        clear();
                    }catch (Exception e1){
                        JOptionPane.showMessageDialog(null,e1);
                    }
                }
            }
        });
    }
}