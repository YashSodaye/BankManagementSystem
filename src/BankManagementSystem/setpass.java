package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import static BankManagementSystem.approval.ano;

public class setpass extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4;
    JLabel j1;
    JTextField pass1,pin;
    JButton b1;

    setpass(String ano){
        setSize(400,400);

        l1 = new JLabel("Your Account Number: ");
        l1.setBounds(40, 150, 200, 25);
        l1.setFont(new Font("Serif", Font.BOLD, 15));
        add(l1);

        j1 = new JLabel();
        j1.setBounds(200, 150, 200, 25);
        j1.setFont(new Font("Serif", Font.BOLD, 15));
        add(j1);

        l2 = new JLabel("Set Password ");
        l2.setBounds(40, 190, 200, 25);
        l2.setFont(new Font("Serif", Font.BOLD, 15));
        add(l2);

        l4 = new JLabel("Set Transaction Pin ");
        l4.setBounds(40, 240, 200, 25);
        l4.setFont(new Font("Serif", Font.BOLD, 15));
        add(l4);


        l3 = new JLabel("Please Note Account Number ");
        l3.setBounds(40, 50, 400, 25);
        l3.setFont(new Font("Serif", Font.BOLD, 25));
        add(l3);

        pass1 = new JTextField();
        pass1.setBounds(200,190,120,25);
        add(pass1);

        pin = new JTextField();
        pin.setBounds(200,240,120,25);
        add(pin);


        b1=new JButton("Confirm");
        b1.setBounds(150,300,100,40);
        b1.addActionListener(this);
        add(b1);




        Conn c= new Conn();
        try {
            String str1 = "select * from userver where aadharnumber= '" + ano + "';";
            PreparedStatement ps = c.c.prepareStatement(str1);

            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                String accountno=rs1.getString(12);
                j1.setText(accountno);
            }
        }
        catch (Exception e){
            System.out.println(e);
        }

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

    public void actionPerformed(ActionEvent ae){
        Conn c=new Conn();
        String pass= this.pass1.getText();
        String pin= this.pin.getText();
        if(ae.getSource()==b1){
            try{
                    String str = "select * from userver where aadharnumber = '"+ano+"'";
                    PreparedStatement ps=c.c.prepareStatement(str);
                    ResultSet rs = ps.executeQuery();
                    while(rs.next()) {
                        String fname = rs.getString(1);
                        String mname=rs.getString(2);
                        String lname=rs.getString(3);
                        String email=rs.getString(4);
                        String phone=rs.getString(5);
                        String address=rs.getString(6);
                        String gen=rs.getString(7);
                        String dob= rs.getString(8);
                        String aadharno=rs.getString(10);
                        String accountno=rs.getString(12);
                        String bal="0";
                        String str1 = "insert into bankmanagementsystem.userreg values ('"+fname+"','"+mname+"','"+lname+"','"+email+"','"+phone+"','"+address+"','"+gen+"','"+dob+"','"+accountno+"','"+aadharno+"','"+pass+"','"+bal+"','"+pin+"');";
                        c.s.execute(str1);
                        String str2 = "delete from userver where aadharnumber = '"+ano+"'";
                        c.s.executeUpdate(str2);

                    }
                    JOptionPane.showMessageDialog(null,"Password Set Successfully!");
                    new login();
                    this.setVisible(false);
            }
            catch (Exception e){
                System.out.println(e);
            }

        }
    }

    public static void main(String[] args) {
        new setpass("1");
    }

}
