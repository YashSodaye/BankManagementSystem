package BankManagementSystem;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;

public class createacc extends JFrame implements ActionListener{
        JLabel l0,l1,l2,l3,l4,l5,l6,l7,l8,l9,l30;
        JTextField fn,mn,ln,mobno,address,panno,aadharno,email;
        JDateChooser dob;
        ButtonGroup b1;
        JRadioButton male,female,others;
        JButton b2,back;
        String ver,acc;
        int labX= 60;
        int labW= 110;
        int labH= 50;
        int txtX= 200;
        int txtW= 150;
        int txtH= 40;

        createacc(){
            setSize(800,800);

            l0 = new JLabel("Create an Account");
            l0.setFont(new Font("Serif", Font.BOLD, 15));
            l0.setBounds(340, 0, 300, 20);
            add(l0);

            l1 = new JLabel("First Name");
            l1.setBounds(labX,50,labW,labH);
            add(l1);

            l2 = new JLabel("Middle Name");
            l2.setBounds(labX,100,labW,labH);
            add(l2);

            l3 = new JLabel("Last Name");
            l3.setBounds(labX,150,labW,labH);
            add(l3);

            l30 = new JLabel("Email");
            l30.setBounds(labX,200,labW,labH);
            add(l30);

            l4 = new JLabel("Mobile Number");
            l4.setBounds(labX,250,labW,labH);
            add(l4);

            l5 = new JLabel("Address");
            l5.setBounds(labX,300,labW,labH);
            add(l5);

            l6 = new JLabel("Gender");
            l6.setBounds(labX,350,labW,labH);
            add(l6);

            l7 = new JLabel("Date of Birth");
            l7.setBounds(labX,400,labW,labH);
            add(l7);

            l8 = new JLabel("PAN number");
            l8.setBounds(labX,450,labW,labH);
            add(l8);

            l9 = new JLabel("Aadhar number");
            l9.setBounds(labX,500,labW,labH);
            add(l9);

            //TEXTFIELDS

            fn = new JTextField();
            fn.setBounds(txtX,50,txtW,txtH);
            add(fn);

            mn = new JTextField();
            mn.setBounds(txtX,100,txtW,txtH);
            add(mn);

            ln = new JTextField();
            ln.setBounds(txtX,150,txtW,txtH);
            add(ln);

            email = new JTextField();
            email.setBounds(txtX,200,txtW,txtH);
            add(email);

            mobno = new JTextField();
            mobno.setBounds(txtX,250,txtW,txtH);
            add(mobno);

            address = new JTextField();
            address.setBounds(txtX,300,txtW,txtH);
            add(address);

            panno = new JTextField();
            panno.setBounds(txtX,450,txtW,txtH);
            add(panno);

            aadharno = new JTextField();
            aadharno.setBounds(txtX,500,txtW,txtH);
            add(aadharno);


            b1 = new ButtonGroup(); // creationg a button group for radio buttons to make only one selection at a time

            male = new JRadioButton("Male");
            male.setBounds(200,350,70,40);
            b1.add(male);
            add(male);

            female = new JRadioButton("Female");
            female.setBounds(275,350,70,40);
            b1.add(female);
            add(female);

            others = new JRadioButton("Others");
            others.setBounds(350,350,70,40);
            b1.add(others);
            add(others);

            b2 =new JButton("Submit");
            b2.setBounds(350,650,100,50);
            b2.addActionListener(this);
            add(b2);

            dob = new JDateChooser();
            dob.setBounds(txtX, 400, txtW, txtH);
            add(dob);

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
            setLocationRelativeTo(null);
            setVisible(true);
        }

        public void actionPerformed(ActionEvent ae){
            if(ae.getSource()==b2){
                String fn = this.fn.getText();
                String mn = this.mn.getText();
                String ln = this.ln.getText();
                String mobno = this.mobno.getText();
                String address = this.address.getText();
                String panno = this.panno.getText();
                String aadharno = this.aadharno.getText();
                String email = this.email.getText();
                ver="0";
                acc = "NULL";



                String gender = null;
                if(male.isSelected()){
                    gender = "Male";
                }
                else if(female.isSelected()) {
                    gender = "Female";
                }
                else if(others.isSelected()){
                    gender = "Others";
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-YYYY");
                String dob = dateFormat.format(this.dob.getDate());


                Conn c = new Conn();
                try {
                    String str = "insert into bankmanagementsystem.userver values ('"+fn+"','"+mn+"','"+ln+"','"+email+"','"+mobno+"','"+address+"','"+gender+"','"+dob+"','"+panno+"','"+aadharno+"','"+ver+"','"+acc+"');";
                    c.s.execute(str);
                    JOptionPane.showMessageDialog(null,"Data Send for Verification!!Wait for Approval");
                    this.setVisible(false);
                    new login();
                }
                catch(Exception e){
                    System.out.println(e);
                }



            }
            if(ae.getSource()==back){
                this.setVisible(false);
                new login();
            }
        }


        public static void main(String[] args) {
            new createacc();
        }
    }

