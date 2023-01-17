package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class approval extends JFrame implements ActionListener {
    JLabel l1;
    JTextField aadharno;
    JButton b1,back;
    public static String ano;
    approval(){
        setSize(400,400);

        l1 = new JLabel("Aadhar Number");
        l1.setFont(new Font("SEGIO", Font.BOLD, 14));
        l1.setBounds(40, 100, 150, 50);
        add(l1);

        aadharno = new JTextField();
        aadharno.setBounds(180, 100, 120, 40);
        add(aadharno);

        b1=new JButton("Check");
        b1.setBounds(150,250,70,40);
        b1.addActionListener(this);
        add(b1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/back.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        back = new JButton(i3);
        back.setBounds(0,0,30,30);
        back.addActionListener(this);
        add(back);


        ImageIcon i7=new ImageIcon(ClassLoader.getSystemResource("images/userfun.jpeg"));
        Image i8 = i7.getImage().getScaledInstance(1500,800,Image.SCALE_SMOOTH);
        ImageIcon i9 = new ImageIcon(i8);
        JLabel i10 = new JLabel(i9);
        i10.setBounds(0,0,1500,800);
        add(i10);



        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        ano= this.aadharno.getText();
        Conn c=new Conn();
        if(ae.getSource()==b1){
            try {
                String str1 = "select * from userver where aadharnumber = '"+ano+"' and verify = '"+0+"'";
                ResultSet rs = c.s.executeQuery(str1);
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "Data not verified yet!");
                    new login();
                    this.setVisible(false);
                }

                String str2 = "select * from userver where aadharnumber = '"+ano+"' and verify = '"+1+"'";
                ResultSet rs1 = c.s.executeQuery(str2);
                if(rs1.next()) {
                    JOptionPane.showMessageDialog(null, "Data Verification Approved!");
                    new setpass(ano);
                    this.setVisible(false);
                }

                String str3 = "select * from userver where aadharnumber = '"+ano+"' and verify = '"+2+"'";
                ResultSet rs2 = c.s.executeQuery(str3);
                if (rs2.next()) {
                    JOptionPane.showMessageDialog(null, "Data Denied!Please Re-apply");
                    new createacc();
                    this.setVisible(false);
                }

            }

            catch(Exception e){
                System.out.println(e);
            }



        }
        if(ae.getSource()== back)
        {
            new login();
            this.setVisible(false);
        }
    }

    public static void main(String[] args) {
        new approval();
    }

}
