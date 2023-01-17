package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class changepin extends JFrame implements ActionListener {
    JLabel l1,l2,l3;
    JTextField j1,j2;
    JPasswordField j3;
    JButton b1,back;
    String accno,pin1,npin,rnpin;

    changepin(String accountno){
        setSize(400,400);
        accno = accountno;

        l1 = new JLabel("Enter current pin");
        l1.setBounds(50,50,100,30);
        add(l1);

        l2 = new JLabel("Enter new pin");
        l2.setBounds(50,100,100,30);
        add(l2);

        l3 = new JLabel("Re-enter new pin");
        l3.setBounds(50,150,100,30);
        add(l3);

        j1 = new JTextField();
        j1.setBounds(200,50,100,30);
        add(j1);

        j2 = new JTextField();
        j2.setBounds(200,100,100,30);
        add(j2);

        j3 = new JPasswordField();
        j3.setBounds(200,150,100,30);
        add(j3);

        b1= new JButton("Change");
        b1.setBounds(150,250,100,30);
        b1.addActionListener(this);
        add(b1);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/back.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        back = new JButton(i3);
        back.setBounds(0,0,30,30);
        back.addActionListener(this);
        add(back);

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("images/userfun.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(400,400,Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(0,0,400,400);
        add(i7);


        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae) {
        Conn c = new Conn();
        if (ae.getSource() == b1) {
             String pin = this.j1.getText();
              npin = this.j2.getText();
              rnpin = String.valueOf(this.j3.getPassword());
            try {
                String str = "select * from userreg where accountnumber= '" + accno + "';";
                PreparedStatement ps = c.c.prepareStatement(str);
                ResultSet rs = ps.executeQuery();
                while (rs.next()) {
                    String pass = rs.getString(13);
                    pin1 = pass;

                }
            }
            catch(Exception e) {
                System.out.println(e);
            }

            try{
                    if (pin.compareTo(pin1)==0){
                        if(npin.compareTo(rnpin)==0){
                            String str1 = "update userreg set pin = '" + rnpin + "' where accountnumber = '" + accno + "'";
                            c.s.executeUpdate(str1);
                            JOptionPane.showMessageDialog(null, "Pin changed Successfully");
                            new dashboard(accno);
                        } else {
                            JOptionPane.showMessageDialog(null, "Wrong New Pin!! ");
                            this.j1.setText(null);
                            this.j2.setText(null);
                            this.j3.setText(null);
                        }
                    }else {
                        JOptionPane.showMessageDialog(null,"Wrong Current Pin!");
                        this.j1.setText(null);
                        this.j2.setText(null);
                        this.j3.setText(null);
                    }
                    }

            catch(Exception e){
            System.out.println(e);
        }
    }
        if(ae.getSource()==back){
            this.setVisible(false);
            new dashboard(accno);
        }
    }


    public static void main(String[] args) {
        new changepin("1");
    }
}
