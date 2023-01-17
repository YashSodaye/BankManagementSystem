package BankManagementSystem;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.Calendar;
import java.util.Date;

public class transfer extends JFrame implements ActionListener {
    JLabel l1,l2,l3,j1,j2,l4,l5;
    JTextField name,accno,amount;
    JButton b1,back;
    String accnumber,amt,dat;

    transfer(String accountno){
        setSize(500,500);

        l4 = new JLabel("Sender's Name");
        l4.setBounds(50,50 ,150,20);
        add(l4);

        l5 = new JLabel("Sender's Account Number");
        l5.setBounds(50,100 ,200,20);
        add(l5);

        l1 = new JLabel("Receiver's Name");
        l1.setBounds(50,150 ,150,20);
        add(l1);

        l2 = new JLabel("Receiver's Account Number");
        l2.setBounds(50,200 ,200,20);
        add(l2);

        l3 = new JLabel("Amount to Transfer");
        l3.setBounds(50,250 ,150,20);
        add(l3);

        j1=new JLabel();
        j1.setBounds(250,40,300,50);
        add(j1);

        j2=new JLabel();
        j2.setBounds(250,90,300,50);
        add(j2);

        //textfields

        name = new JTextField();
        name.setBounds(250,150,150,30);
        add(name);

        accno = new JTextField();
        accno.setBounds(250,200,150,30);
        add(accno);

        amount = new JTextField();
        amount.setBounds(250,250,150,30);
        add(amount);

        b1 = new JButton("Proceed");
        b1.setBounds(180,350,100,30);
        b1.addActionListener(this);
        add(b1);


        Conn c1= new Conn();
        try {
            String str1 = "select * from userreg where accountnumber= '" + accountno + "';";

            PreparedStatement ps = c1.c.prepareStatement(str1);

            ResultSet rs1 = ps.executeQuery();
            while (rs1.next()) {
                String  fname= rs1.getNString(1);
                String mname=rs1.getString(2);
                String lname=rs1.getString(3);
                String name=fname+" "+mname+" "+lname;
                j1.setText(name);
                accnumber=rs1.getString(9); //senders acc no
                j2.setText(accnumber);
            }
        }catch (Exception e){
            System.out.println(e);
        }

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/back.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        back = new JButton(i3);
        back.setBounds(0,0,30,30);
        back.addActionListener(this);
        add(back);

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("images/userfun.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(1000,1000,Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(0,0,1000,1000);
        add(i7);




        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);


    }
    public void actionPerformed(ActionEvent ae){
        Conn c2 = new Conn();
        String accno1 = this.accno.getText();// receivers acc no
        amt = this.amount.getText();// amount to transfer
        if(ae.getSource()==b1){
            try
            {
                String str2 = "select balance from userreg where accountnumber = '" + accno1 + "';"; // receiver acc no
                ResultSet rs = c2.s.executeQuery(str2);
                if(rs.next()){
                    if(accno.getText().compareTo(accnumber)== 0){
                        JOptionPane.showMessageDialog(null,"You cannot transfer to your own account!!");
                    }
                    else {
                        Date dateAndTime = Calendar.getInstance().getTime();
                        dat = String.valueOf(dateAndTime);
                        String status = null;
                        String des = "Fund transfer of ₹"+amt+" to Account "+accno1+" from Account "+accnumber;
                        String str3 = "insert into bankmanagementsystem.transactions  values('"+accnumber+"','"+accno1+"','"+des+"','"+amt+"','"+dat+"','"+status+"');";
                        c2.s.execute(str3);
                        this.setVisible(false);
                        new pin(accnumber,amt,accno1,dat);
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Account Number!");
                    this.setVisible(false);
                    new dashboard(accnumber);
                }

            }
            catch(Exception e){
                System.out.println(e);
            }
        }
        if(ae.getSource()==back){
            this.setVisible(false);
            new dashboard(accnumber);
        }
    }

    public static void main(String[] args) {
        new transfer("1,1,1");
    }
}
