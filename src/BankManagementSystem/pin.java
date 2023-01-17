package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;


public class pin extends JFrame implements ActionListener {

    JLabel l1;
    JPasswordField j1;
    JButton b1;
    String ano,amount,raccno,date;
    pin(String accnumber,String amt,String accno1,String dat) {
        setSize(400,200);

        l1 = new JLabel("Enter Transaction Pin");
        l1.setBounds(20,50,180,20);
        add(l1);

        j1 = new JPasswordField();
        j1.setBounds(200,50,100,20);
        add(j1);

        b1 = new JButton("Transfer");
        b1. setBounds(150,100,100,30);
        b1.addActionListener(this);
        add(b1);

        date = dat;
        ano = accnumber;
        amount = amt;
        raccno = accno1;

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("images/userfun.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(400,200,Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(0,0,400,200);
        add(i7);



        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public void actionPerformed(ActionEvent ae){
        if(ae.getSource()==b1){
            String pass =String.valueOf(this.j1.getPassword());
            Conn c=new Conn();
            try {
                String str1 = "select * from userreg where accountnumber = '" + ano + "' and pin = '" + pass + "';";
                ResultSet rs = c.s.executeQuery(str1);
                if (rs.next()) {
                    String bal = rs.getString(12);
                    if (Integer.parseInt(amount) >= Integer.parseInt(bal)) {
                        JOptionPane.showMessageDialog(null, "You have insufficient balance!");
                        String ver2= "FAILED";
                        String str ="update transactions set status = '"+ver2   +"' where dateandtime= '"+date+"' ;";
                        c.s.executeUpdate(str);

                        this.setVisible(false);
                        new dashboard(ano);
                    } else {

                        bal = String.valueOf(Integer.parseInt(bal) - Integer.parseInt(amount));

                        String str3 = "update userreg set balance = '" + bal + "' where accountnumber= '" + ano + "' ;";
                        c.s.executeUpdate(str3);

                        String str2 = "select * from userreg where accountnumber = '"+raccno+"';";
                        ResultSet rs1 = c.s.executeQuery(str2);
                        if(rs1.next()){
                            String recbal=rs1.getString(12);
                            recbal = String.valueOf(Integer.parseInt(recbal)+Integer.parseInt(amount));
                            String str4 ="update userreg set balance = '"+recbal+"' where accountnumber= '"+raccno+"' ;";
                            c.s.executeUpdate(str4);
                            String ver = "SUCCESS";
                            String str ="update transactions set status = '"+ver+"' where dateandtime= '"+date+"' ;";
                            c.s.executeUpdate(str);
                            JOptionPane.showMessageDialog(null, "Amount Transfer Successfully!");
                            this.setVisible(false);
                            new dashboard(ano);
                        }
                    }
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Transaction Pin!");
                    String ver1= "FAILED";
                    String str ="update transactions set status = '"+ver1+"' where dateandtime= '"+date+"' ;";
                    c.s.executeUpdate(str);
                    this.setVisible(false);
                    new dashboard(ano);
                }
            }catch(Exception e){
                System.out.println(e);
            }
        }
    }

    public static void main(String[] args) {
        new pin("1","2","3","4");
    }
}
