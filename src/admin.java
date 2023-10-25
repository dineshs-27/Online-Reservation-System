import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class admin {
    public JPanel adminpage;
    private JTextField textname;
    private JTextField textpassword;
    private JButton buttonlogin;

    public void clear(){
        textname.setText("");
        textpassword.setText("");
    }

    public void openAdminDbWindow(){
        JFrame frame = new JFrame("DataBase");
        frame.setContentPane(new database().databasepage);
        frame.pack();
        frame.setBounds(100,80,1300,700);
        frame.setVisible(true);
    }

    public admin() {
        buttonlogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = textname.getText();
                String password = textpassword.getText();

                if (name.isEmpty() || password.isEmpty()){
                    JOptionPane.showMessageDialog(null,"Invalid Name or Password ");
                } else if (name.equals("admin") && password.equals("98765")){
                    openAdminDbWindow();
                    clear();
                }
            }
        });
    }

}