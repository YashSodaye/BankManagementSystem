package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class AdminMainPage extends JFrame implements ActionListener {
    JLabel l1,j1,j2,j3,j4,j5,j6,j7,j8,j9;
    JButton b1,b2,b3,b4;
    JMenuBar mb;
    JTable details;
    JTabbedPane tabs;
    int count,count1;

    AdminMainPage(String username) {
        setSize(800, 400);

        mb = new JMenuBar();
        mb.setBounds(0,0,200,465);
        mb.setBackground(Color.BLUE);
        mb.setLayout(new GridLayout(4,1,0,70));
        add(mb);


        details = new JTable();
        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(details);
        tabs = new JTabbedPane();
        tabs.setBounds(230,100,520,230);
        add(tabs);

        l1 = new JLabel("Admin Main Page");
        l1.setBounds(350, 10, 300, 40);
        l1.setFont(new Font("Serif", Font.BOLD, 30));
        add(l1);

        j1 =new JLabel();
        j1.setBounds(230,60,400,40);
        add(j1);

        j2 = new JLabel("Admin Username: ");
        j2.setBounds(250,110,150,30);
        j2.setFont(new Font("Serif", Font.PLAIN, 17));
        add(j2);

        j3 = new JLabel(username);
        j3.setBounds(380,110,100,30);
        j3.setFont(new Font("Serif", Font.BOLD, 17));
        add(j3);

        j4 = new JLabel("Total number of user in System : ");
        j4.setBounds(250,150,300,50);
        j4.setFont(new Font("Serif", Font.PLAIN, 17));
        add(j4);

        j5 = new JLabel();
        j5.setBounds(470,160,100,30);
        j5.setFont(new Font("Serif", Font.BOLD, 17));
        add(j5);

         j6 = new JLabel("Total number of new account in System : ");
        j6.setBounds(250,200,300,50);
        j6.setFont(new Font("Serif", Font.PLAIN, 17));
        add(j6);

        j7 = new JLabel();
        j7.setBounds(530,210,100,30);
        j7.setFont(new Font("Serif", Font.BOLD, 17));
        add(j7);

         j8 = new JLabel("Total number of active user in System : ");
        j8.setBounds(250,250,300,50);
        j8.setFont(new Font("Serif", Font.PLAIN, 17));
        add(j8);

        j9 = new JLabel();
        j9.setBounds(530,260,100,30);
        j9.setFont(new Font("Serif", Font.BOLD, 17));
        add(j9);


        b2 = new JButton("User Details");
        b2.setBounds(0, 90, 160, 50);
        b2.addActionListener(this);
        mb.add(b2);

        b3 = new JButton("Account Verification");
        b3.setBounds(0, 130, 160, 50);
        b3.addActionListener(this);
        mb.add(b3);

        b4 = new JButton("Sign/Log Out");
        b4.setBounds(0, 170, 160, 50);
        b4.addActionListener(this);
        mb.add(b4);
        Conn c = new Conn();

        try {
            String str = "SELECT count(*) FROM bankmanagementsystem.userreg; ";
            ResultSet rs = c.s.executeQuery(str);
            rs.next();
            count = rs.getInt(1);
            j5.setText(String.valueOf(count));
        }
        catch (Exception e){
            System.out.println(e);
        }

        try{
            String str1 = "SELECT count(balance) FROM bankmanagementsystem.userreg where balance = '0' ; ";
            ResultSet rs1 = c.s.executeQuery(str1);
            rs1.next();
            count1 = rs1.getInt(1);
            j7.setText(String.valueOf(count1));
        }
        catch (Exception e){
            System.out.println(e);
        }

        int count2 = count - count1;
        j9.setText(String.valueOf(count2));

        try {
            String str1 = "select * from adminlogin where Username = '" + username + "';";
            PreparedStatement ps = c.c.prepareStatement(str1);
            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                String name = rs1.getString(3);
                j1.setText("Welcome, "+name);
                j1.setFont(new Font("Serif", Font.PLAIN, 20));
            }
        }catch (Exception e){
            System.out.println(e);
        }

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("images/adminpage.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(800,400,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        i4.setBounds(0,0,800,400);
        add(i4);


        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);



    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b3){
            new accver();
        }
        else if(ae.getSource()==b2)
        {
            new userdet();
        }
        else if(ae.getSource()==b4){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new AdminMainPage("1");
    }
}