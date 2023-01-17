package BankManagementSystem;

import net.proteanit.sql.DbUtils;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class transations extends JFrame implements ActionListener {
    JTable t1;
    JScrollPane sp1;
    JButton back;
    String ano;

    transations(String accountno){
        setSize(1500,800);
        //Scrollpane
        ano = accountno;

        sp1= new JScrollPane();
        sp1.setBounds(50,80,1300,650);
        this.add(sp1);
        //Table code

        t1 =new JTable();
        sp1.setViewportView(t1);
        try{
            Conn c=new Conn();
            String str1 = "select sender_accountno,receiver_accountno,description,transfered_amount,dateandtime,status from bankmanagementsystem.transactions where sender_accountno = '"+accountno+"';";
            ResultSet rs= c.s.executeQuery(str1);
            t1.setModel(DbUtils.resultSetToTableModel(rs));

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
        Image i5 = i4.getImage().getScaledInstance(1500,800,Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(0,0,1500,800);
        add(i7);

        setLayout(null);
//        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==back){
            this.setVisible(false);
            new dashboard(ano);
        }
    }

    public static void main(String[] args) {
        new transations("1");
    }
}
