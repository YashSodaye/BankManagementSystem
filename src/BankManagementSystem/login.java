package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class login extends JFrame implements ActionListener{
    JTextField accno;
    JPasswordField pass;
    JLabel l0, l1, l2,l3,l5;
    JButton si, cn,reg,creat,approv,back;

    login(){
        setSize(400,400);

        accno = new JTextField();
        accno.setBounds(180, 100, 120, 40);
        add(accno);

        pass = new JPasswordField();
        pass.setBounds(180, 150, 120, 40);
        add(pass);

        l0 = new JLabel("User Login");
        l0.setFont(new Font("Serif", Font.BOLD, 20));
        l0.setBounds(150, 0, 150, 30);
        l0.setForeground(Color.WHITE);
        add(l0);

        l1 = new JLabel("Account Number");  //username label
        l1.setFont(new Font("SEGIO", Font.BOLD, 14));
        l1.setBounds(40, 100, 150, 50);
        l1.setForeground(Color.WHITE);
        add(l1);

        l2 = new JLabel("Password");  //password label
        l2.setBounds(40, 150, 100, 50);
        l2.setFont(new Font("SEGIO", Font.BOLD, 14));
        l2.setForeground(Color.WHITE);
        add(l2);

        l3 = new JLabel("New User to System?");
        l3.setFont(new Font("Serif", Font.BOLD, 15));
        l3.setBounds(200, 280, 250, 20);
        l3.setForeground(Color.WHITE);
        add(l3);

        creat = new JButton("Create new account?");
        creat.setFont(new Font("Serif", Font.BOLD, 15));
        creat.setBounds(10, 280, 175, 20);
        creat.addActionListener(this);
        add(creat);

        approv = new JButton("Account Approval?");
        approv.setFont(new Font("Serif", Font.BOLD, 15));
        approv.setBounds(10, 310, 175, 20);
        approv.addActionListener(this);
        add(approv);

        reg = new JButton("Register Now!");
        reg.setFont(new Font("Serif", Font.BOLD, 15));
        reg.addActionListener(this);
        reg.setBounds(200, 310, 130, 20);
        add(reg);


        si = new JButton("Log-in");
        si.setBounds(60, 220, 120, 40);
        si.addActionListener(this);
        add(si);

        cn = new JButton("Back to Home");
        cn.setBounds(200, 220, 120, 40);
        cn.addActionListener(this);
        add(cn);

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("images/loginpage.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        i4.setBounds(0,0,400,400);
        add(i4);


        ImageIcon i5 = new ImageIcon(ClassLoader.getSystemResource("images/back.png"));
        Image i6 = i5.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon i7 = new ImageIcon(i6);
        back = new JButton(i7);
        back.setBounds(0,0,30,30);
        back.addActionListener(this);
        add(back);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == si) {
            String accno =this.accno.getText();
            String pass = String.valueOf(this.pass.getPassword());
            Conn c=new Conn();
            try{
                String str1 = "select * from userreg where AccountNumber = '"+accno+"' and Password = '"+pass+"';";
                ResultSet rs = c.s.executeQuery(str1);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Login Successful!");
                    new dashboard(accno);
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Login Credentials!");
                    this.accno.setText(null);
                    this.pass.setText(null);
                }
            }catch(Exception e){
                System.out.println(e);
            }

        }

        else if(ae.getSource()== reg){
            new register();
            this.setVisible(false);
        }

        else if (ae.getSource() == cn) {
            new Main();
            this.setVisible(false);
        }

        else if(ae.getSource()==creat){
            new createacc();
            this.setVisible(false);
        }

        else if(ae.getSource()==approv){
            new approval();
            this.setVisible(false);
        }
        if(ae.getSource()==back){
            this.setVisible(false);
            new Main();
        }

    }


    public static void main(String[] args) {
        new login();

    }
}
