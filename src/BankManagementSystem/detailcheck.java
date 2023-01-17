package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class detailcheck extends JFrame implements ActionListener {
    JLabel l1, l2, l3, l4, l5, l6, l7, l8;
    JLabel j1, j2, j3, j4, j5, j6, j7, j8;
    JButton b1, b2,back;
    String ver,ver1;
    JTable details;
    JTabbedPane tabs;

    detailcheck() {
        setSize(750, 800);

        details = new JTable();
        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(details);
        tabs = new JTabbedPane();
        tabs.setBounds(100,120,520,430);
        add(tabs);

        l1 = new JLabel("Name:");
        l1.setBounds(120, 150, 100, 25);
        l1.setFont(new Font("Serif", Font.BOLD, 15));
        add(l1);

        l2 = new JLabel("Email:");
        l2.setBounds(120, 200, 100, 25);
        l2.setFont(new Font("Serif", Font.BOLD, 15));
        add(l2);

        l3 = new JLabel("Mobile Number:");
        l3.setBounds(120, 250, 150, 25);
        l3.setFont(new Font("Serif", Font.BOLD, 15));
        add(l3);

        l4 = new JLabel("Address:");
        l4.setBounds(120, 300, 100, 25);
        l4.setFont(new Font("Serif", Font.BOLD, 15));
        add(l4);

        l5 = new JLabel("Gender:");
        l5.setBounds(120, 350, 100, 25);
        l5.setFont(new Font("Serif", Font.BOLD, 15));
        add(l5);

        l6 = new JLabel("Date of Birth:");
        l6.setBounds(120, 400, 150, 25);
        l6.setFont(new Font("Serif", Font.BOLD, 15));
        add(l6);

        l7 = new JLabel("PAN Number:");
        l7.setBounds(120, 450, 150, 25);
        l7.setFont(new Font("Serif", Font.BOLD, 15));
        add(l7);

        l8 = new JLabel("Aadhar Number:");
        l8.setBounds(120, 500, 150, 25);
        l8.setFont(new Font("Serif", Font.BOLD, 15));
        add(l8);


        j1 = new JLabel(accver.name);
        j1.setBounds(350, 140, 300, 50);
        add(j1);

        j2 = new JLabel(accver.email);
        j2.setBounds(350, 190, 300, 50);
        add(j2);

        j3 = new JLabel(accver.mobno);
        j3.setBounds(350, 240, 300, 50);
        add(j3);

        j4 = new JLabel(accver.address);
        j4.setBounds(350, 290, 450, 50);
        add(j4);

        j5 = new JLabel(accver.gender);
        j5.setBounds(350, 340, 300, 50);
        add(j5);

        j6 = new JLabel(accver.dob);
        j6.setBounds(350, 390, 300, 50);
        add(j6);

        j7 = new JLabel(accver.panno);
        j7.setBounds(350, 440, 300, 50);
        add(j7);

        j8 = new JLabel(accver.aadharno);
        j8.setBounds(350, 500, 300, 25);
        add(j8);

        b1 = new JButton("Request Approve");
        b1.setBounds(200, 570, 140, 25);
        b1.addActionListener(this);
        add(b1);

        b2 = new JButton("Request Deny");
        b2.setBounds(400, 570, 140, 25);
        b2.addActionListener(this);
        add(b2);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/back.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        back = new JButton(i3);
        back.setBounds(0,0,30,30);
        back.addActionListener(this);
        add(back);

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("images/userfun.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(1500,800,Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(0,0,1500,800);
        add(i7);


        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        Conn c=new Conn();
        if (ae.getSource() == b1) {
//          For random account number
            Random r = new Random();
            String a = "278701";
            String b = String.valueOf(r.nextInt(1000, 9999));
            String acc = a + b;
            String accno = acc;
             ver="1";

            try {
                String str ="update userver set verify = '"+ver+"', accountnumber = '"+accno+"' where aadharnumber= '"+accver.aadharno+"' ;";
                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Data Verified!!");
                this.setVisible(false);
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        if(ae.getSource()==b2) {
             ver1="2";
            try {
                String str ="update userver set verify = '"+ver1+"' where aadharnumber= '"+accver.aadharno+"' ;";
                c.s.executeUpdate(str);
                JOptionPane.showMessageDialog(null, "Data Denied!");
                this.setVisible(false);
            }
            catch (Exception e){
                System.out.println(e);
            }
        }
        if(ae.getSource()== back)
        {
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new detailcheck();
    }
}

