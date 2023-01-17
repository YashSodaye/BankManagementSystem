package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class  Main extends JFrame implements ActionListener {
    JLabel l1;
    JButton b1, b2;
    Main() {
        setSize(800, 500);

        l1 = new JLabel("Bank Management System");
        l1.setFont(new Font("Serif", Font.BOLD, 34));
        l1.setBounds(60, 20, 500, 100);
        l1.setForeground(Color.WHITE);
        add(l1);

        b1 = new JButton("Admin Login");
        setFont(new Font("Serif", Font.PLAIN, 34));
        b1.setBounds(75, 350, 130, 50);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("User Login");
        setFont(new Font("Serif", Font.PLAIN, 34));
        b2.setBounds(270, 350, 100, 50);
        b2.addActionListener(this);
        add(b2);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("images/main.jpg"));
        Image i2 = i1.getImage().getScaledInstance(800,500,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        i4.setBounds(0,0,800,500);
        add(i4);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource()== b1) {
            new AdminLogin();
            this.setVisible(false);
        }

            if (ae.getSource()== b2) {
                new login();
                this.setVisible(false);

        }
    }
        public static void main(String[]args){
            new Main();
    }

}
