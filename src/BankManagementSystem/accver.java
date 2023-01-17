package BankManagementSystem;

import javax.swing.*;
import net.proteanit.sql.DbUtils;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.ResultSet;

public class accver extends JFrame implements ActionListener {
    JTable t1;
    JScrollPane sp1;
    JButton check,refresh,back;
    int row;
    public static String fname,mname,lname,name;
    public static String email;
    public static String mobno;
    public static String address;
    public static String gender;
    public static String dob;
    public static String panno;
    public static String aadharno;



    accver(){
        setSize(1000,800);
        check = new JButton("Check");
        check.setBounds(400,600,100,50);
        check.addActionListener(this);
        add(check);

        refresh = new JButton("Refresh");
        refresh.setBounds(600,600,100,50);
        refresh.addActionListener(this);
        add(refresh);

        //Scrollpane

        sp1= new JScrollPane();
        sp1.setBounds(50,80,900,500);
        this.add(sp1);
        //Table code

        t1 =new JTable();
        sp1.setViewportView(t1);
        try{
            Conn c=new Conn();
            String str1 = "select firstname,middlename,lastname,email,mobilenumber,address,gender,dob,pannumber,aadharnumber from bankmanagementsystem.userver where verify ='"+0+"';";
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
    void t1Select(){
        t1.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                row = t1.getSelectedRow();
                fname = t1.getModel().getValueAt(row,0).toString();
                mname = t1.getModel().getValueAt(row,1).toString();
                lname = t1.getModel().getValueAt(row,2).toString();
                name = fname+" "+mname+" "+lname;
                email = t1.getModel().getValueAt(row, 3).toString();
                mobno = t1.getModel().getValueAt(row, 4).toString();
                address = t1.getModel().getValueAt(row , 5).toString();
                gender = t1.getModel().getValueAt(row , 6).toString();
                dob = t1.getModel().getValueAt(row , 7).toString();
                panno = t1.getModel().getValueAt(row , 8).toString();
                aadharno = t1.getModel().getValueAt(row , 9).toString();
            }
        });
    }
    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==check){
            if(t1.isShowing()) {
                t1Select();
                new detailcheck();

            }

        }
        else if(ae.getSource()==refresh){
            try{
                Conn c=new Conn();
                String str1 = "select firstname,middlename,lastname,email,mobilenumber,address,gender,dob,pannumber,aadharnumber from bankmanagementsystem.userver where verify ='"+0+"'";
                ResultSet rs= c.s.executeQuery(str1);
                t1.setModel(DbUtils.resultSetToTableModel(rs));

            }catch (Exception e){
                System.out.println(e);
            }
        }
        if(ae.getSource()== back)
        {
            this.setVisible(false);
        }
    }


    public static void main(String[] args) {
        new accver();
    }
}

