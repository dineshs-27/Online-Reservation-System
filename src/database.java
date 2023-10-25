import net.proteanit.sql.DbUtils;
import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableColumnModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

public class database{
    public JPanel databasepage;
    private JTable tabledb;
    private JButton buttonshowdb;

    Connection con;
    PreparedStatement ps;
    ResultSet rs;
    Statement st;

    public void ShowDb() {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/trsdb","root","1234");
            st = con.createStatement();
            rs = st.executeQuery("Select * from reservation_system_table");
            tabledb.setModel(DbUtils.resultSetToTableModel(rs));

            int[] columnWidths = {100, 200, 50, 100, 250, 450, 200, 400, 200, 200, 250, 250, 250};
            TableColumnModel columnModel = tabledb.getColumnModel();
            for (int i = 0; i < columnModel.getColumnCount(); i++) {
                TableColumn column = columnModel.getColumn(i);
                if (i < columnWidths.length) {
                    column.setPreferredWidth(columnWidths[i]);
                }
            }

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,e);
        }
    }

    public database() {
        buttonshowdb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ShowDb();
            }
        });
    }

}