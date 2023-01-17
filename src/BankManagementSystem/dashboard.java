package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class dashboard extends JFrame implements ActionListener {
    JLabel l1,l2,l3,l4,l5,l6,l7,l8;
    JLabel j1,j2,j3,j4,j5,j6,j7,j8;
    JButton b1,b2,b3,b4;
    JMenuBar mb;
    JTable details;
    JTabbedPane tabs;
    String accountno;
    dashboard(String accno){
        setSize(1000,1000);

        mb = new JMenuBar();
        mb.setBounds(0,0,150,1000);
        mb.setBackground(Color.BLUE);
        mb.setLayout(new GridLayout(5,1,0,50));
        add(mb);


        l1 = new JLabel("Dashboard");
        l1.setBounds(10,10,100,25);
        l1.setFont(new Font("Serif", Font.BOLD, 20));
        add(l1);

        b1 = new JButton("Transfer");
        setFont(new Font("Serif", Font.PLAIN, 34));
        b1.addActionListener(this);
        mb.add(b1);

        b2 = new JButton("Transaction History");
        b2.addActionListener(this);
        mb.add(b2);

        b3 = new JButton("Change Pin");
        b3.addActionListener(this);
        mb.add(b3);

        b4 = new JButton("Log Out");
        b4.addActionListener(this);
        mb.add(b4);

        //User Details to Display

        details = new JTable();
        JScrollPane sp1 = new JScrollPane();
        sp1.setViewportView(details);
        tabs = new JTabbedPane();
        tabs.setBounds(200,80,700,600);
        add(tabs);


        l2 = new JLabel("Name:");
        l2.setBounds(220,150,100,25);
        l2.setFont(new Font("Serif", Font.BOLD, 15));
        add(l2);

        l7 = new JLabel("Email:");
        l7.setBounds(220,200,100,25);
        l7.setFont(new Font("Serif", Font.BOLD, 15));
        add(l7);

        l3 = new JLabel("Phone Number:");
        l3.setBounds(220,250,150,25);
        l3.setFont(new Font("Serif", Font.BOLD, 15));
        add(l3);

        l4 = new JLabel("Address:");
        l4.setBounds(220,300,100,25);
        l4.setFont(new Font("Serif", Font.BOLD, 15));
        add(l4);

        l8 = new JLabel("Gender:");
        l8.setBounds(220,350,100,25);
        l8.setFont(new Font("Serif", Font.BOLD, 15));
        add(l8);

        l5 = new JLabel("Account Number:");
        l5.setBounds(220,400,150,25);
        l5.setFont(new Font("Serif", Font.BOLD, 15));
        add(l5);

        l6 = new JLabel("Account Balance:");
        l6.setBounds(220,450,150,25);
        l6.setFont(new Font("Serif", Font.BOLD, 15));
        add(l6);


        j1=new JLabel();
        j1.setBounds(350,140,300,50);
        add(j1);

        j2=new JLabel();
        j2.setBounds(350,190,300,50);
        add(j2);

        j3=new JLabel();
        j3.setBounds(350,240,300,50);
        add(j3);

        j4=new JLabel();
        j4.setBounds(350,290,450,50);
        add(j4);

        j5=new JLabel();
        j5.setBounds(350,340,300,50);
        add(j5);

        j6=new JLabel();
        j6.setBounds(350,390,300,50);
        add(j6);

        j7=new JLabel();
        j7.setBounds(350,440,300,50);
        add(j7);

        j8=new JLabel();
        j8.setBounds(200,50,300,25);
        add(j8);



        Conn c= new Conn();
        try{
            String str1 = "select * from userreg where accountnumber= '"+accno+"';";
            PreparedStatement ps=c.c.prepareStatement(str1);

            ResultSet rs1 = ps.executeQuery();
            while(rs1.next())
            {
                String  fname= rs1.getNString(1);
                String mname=rs1.getString(2);
                String lname=rs1.getString(3);
                String name=fname+" "+mname+" "+lname;
                j1.setText(name);
                j8.setText("Welcome, "+name);
                j8.setFont(new Font("Serif", Font.BOLD, 20));
                String email=rs1.getString(4);
                j2.setText(email);
                String phone=rs1.getString(5);
                j3.setText(phone);
                String address=rs1.getString(6);
                j4.setText(address);
                String gen=rs1.getString(7);
                j5.setText(gen);
                accountno=rs1.getString(9);
                j6.setText(accountno);
                String bal=rs1.getString(12);
                j7.setText(bal);
            }




        }catch (Exception e){
            System.out.println(e);
        }

        ImageIcon i1=new ImageIcon(ClassLoader.getSystemResource("images/userdashboard.jpeg"));
        Image i2 = i1.getImage().getScaledInstance(1000,1000,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel i4 = new JLabel(i3);
        i4.setBounds(0,0,1000,1000);
        add(i4);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);




    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()== b1){
            new transfer(accountno);
            this.setVisible(false);
        }
        if(ae.getSource()==b2){
            new transations(accountno);
            this.setVisible(false);
        }
        if(ae.getSource()==b3){
            new changepin(accountno);
            this.setVisible(false);
        }
        if(ae.getSource()==b4){
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        new dashboard("1");
    }
}
