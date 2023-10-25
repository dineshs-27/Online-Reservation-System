import com.toedter.calendar.JDateChooser;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.SimpleDateFormat;

public class reservation_system {
    private JTextField textname;
    private JTextField textage;
    private JTextField textphone;
    private JTextField textemail;
    public JPanel rspage;
    private JTextField textstart;
    private JTextField textend;
    private JComboBox combotn;
    private JComboBox combotname;
    private JRadioButton rbfc;
    private JRadioButton rbsc;
    private JRadioButton rbslp;
    private JRadioButton rbmale;
    private JRadioButton rbfemale;
    private JButton buttonbook;
    private JDateChooser dc;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public void clear(){
        textname.setText("");
        textage.setText("");
        textphone.setText("");
        textemail.setText("");
        textstart.setText("");
        textend.setText("");
    }

    public void connect(){
        try{
            con = DriverManager.getConnection("jdbc:mysql://localhost/trsdb", "root", "1234");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void pnr(){
        try {
            String query = "SELECT LAST_INSERT_ID() AS pnr";
            st = con.createStatement();
            rs = st.executeQuery(query);

            if (rs.next()) {
                String pnr = rs.getString("pnr");
                JOptionPane.showMessageDialog(null,"Booked !" + "\nYour PNR Number is : " + pnr + "\nPlease Note the PNR Number for Cancellation Process !");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void createUIComponents() {
       dc = new JDateChooser();
    }

    public reservation_system(){
        connect();

        buttonbook.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textname.getText().isEmpty() ||textage.getText().isEmpty() ||textphone.getText().isEmpty() ||textemail.getText().isEmpty() || textstart.getText().isEmpty() || textend.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"Missing Information");
                }else{
                    try{
                        String I = "INSERT INTO reservation_system_table (name, age, gender, phone, email, train_number, train_name, class_type, doj, start, end, status) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
                        con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trsdb","root","1234");
                        ps = con.prepareStatement(I);

                        ps.setString(1, textname.getText());
                        ps.setString(2, textage.getText());
                        if (rbmale.isSelected()) {
                            ps.setString(3, "Male");
                        } else if (rbfemale.isSelected()) {
                            ps.setString(3, "Female");
                        }
                        ps.setString(4, textphone.getText());
                        ps.setString(5, textemail.getText());
                        String selectedTrainNumber = (String) combotn.getSelectedItem();
                        ps.setString(6, selectedTrainNumber);
                        String selectedTrainName = (String) combotname.getSelectedItem();
                        ps.setString(7, selectedTrainName);
                        if (rbfc.isSelected()) {
                            ps.setString(8, "First Class");
                        } else if (rbsc.isSelected()) {
                            ps.setString(8, "Second Class");
                        } else if (rbslp.isSelected()) {
                            ps.setString(8, "Sleeper");
                        }
                        ps.setString(9, new SimpleDateFormat("yyyy-MM-dd").format(dc.getDate()));
                        ps.setString(10, textstart.getText());
                        ps.setString(11, textend.getText());
                        ps.setString(12, "Booked");

                        ps.executeUpdate();
                        pnr();
                        clear();
                    }catch (Exception e1){
                        JOptionPane.showMessageDialog(null,e1);
                    }
                }
            }
        });

    }

}