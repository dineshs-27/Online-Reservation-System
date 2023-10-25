import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class booking {
    private JButton buttonrs;
    public JPanel bookingpage;
    private JButton buttoncancle;

    public void openReservationSystemWindow(){
        JFrame frame = new JFrame("reservation_system");
        frame.setContentPane(new reservation_system().rspage);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(500,125,550,600);
    }

    public void openCancellationSystemWindow(){
        JFrame frame = new JFrame("Cancellation");
        frame.setContentPane(new cancellation_system().cancel);
        frame.pack();
        frame.setVisible(true);
        frame.setBounds(500,125,580,600);
    }

    public booking() {

        buttonrs.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openReservationSystemWindow();
            }
        });

        buttoncancle.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openCancellationSystemWindow();
            }
        });
    }

}