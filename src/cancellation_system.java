import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class cancellation_system {
    private JTextField textpnr;
    private JTextField textname;
    private JTextField textage;
    private JTextField textgender;
    private JTextField textphone;
    private JTextField textemail;
    private JTextField texttn;
    private JTextField texttname;
    private JTextField textct;
    private JTextField textdoj;
    private JTextField textstart;
    private JTextField textend;
    private JButton buttoncancle;
    public JPanel cancel;
    private JButton buttonsearch;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public void clear(){
        textname.setText("");
        textage.setText("");
        textphone.setText("");
        textemail.setText("");
        textdoj.setText("");
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

    public cancellation_system(){
        connect();

        buttonsearch.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textpnr.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"PNR Number is Missing !");
                }else {
                    try {
                        String query = "SELECT * FROM reservation_system_table WHERE pnr = ?";
                        ps = con.prepareStatement(query);
                        ps.setString(1, textpnr.getText());
                        System.out.println(textpnr);
                        rs = ps.executeQuery();

                        if (rs.next()) {
                            textname.setText(rs.getString("name"));
                            textage.setText(rs.getString("age"));
                            textgender.setText(rs.getString("gender"));
                            textphone.setText(rs.getString("phone"));
                            textemail.setText(rs.getString("email"));
                            texttn.setText(rs.getString("train_number"));
                            texttname.setText(rs.getString("train_name"));
                            textct.setText(rs.getString("class_type"));
                            textdoj.setText(rs.getString("doj"));
                            textstart.setText(rs.getString("start"));
                            textend.setText(rs.getString("end"));
                        } else {
                            JOptionPane.showMessageDialog(null,"No data found for given PNR !");
                        }
                    }catch (Exception e1){
                        JOptionPane.showMessageDialog(null,e1);
                    }
                }
            }
        });

        buttoncancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(textpnr.getText().isEmpty())
                {
                    JOptionPane.showMessageDialog(null,"PNR Number is Missing !");
                }else {
                    String updateQuery = "UPDATE reservation_system_table SET status = 'Cancelled' WHERE pnr = ?";
                    try {
                        ps = con.prepareStatement(updateQuery);
                        ps.setString(1, textpnr.getText());
                        ps.executeUpdate();
                    } catch (SQLException ex) {
                        ex.printStackTrace();
                    }
                    JOptionPane.showMessageDialog(null, "Your Booking is Cancelled !");
                    clear();
                }
            }
        });

    }

}