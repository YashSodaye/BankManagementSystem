package BankManagementSystem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;

public class AdminLogin extends JFrame implements ActionListener {
    JLabel l0,l1,l2;
    JTextField username;
    JButton login,home,back;
    JPasswordField password;

    AdminLogin(){
        setSize(500,300);

        l0 = new JLabel("Admin Login");
        l0.setFont(new Font("Serif", Font.BOLD, 20));
        l0.setBounds(180, 0, 150, 25);
        l0.setForeground(Color.WHITE);
        add(l0);

        l1 = new JLabel("Username");
        l1.setBounds(50,50,100,40);
        l1.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l1.setForeground(Color.WHITE);
        add(l1);

        l2 = new JLabel("Password");
        l2.setBounds(50,120,100,40);
        l2.setFont(new Font("Tahoma", Font.PLAIN, 16));
        l2.setForeground(Color.WHITE);
        add(l2);

        username = new JTextField();
        username.setBounds(150,50,150,40);
        add(username);

        password = new JPasswordField();
        password.setBounds(150,120,150,40);
        add(password);

        login = new JButton("Login Now");
        login.setBounds(70, 200, 120, 40);
        login.addActionListener(this);
        add(login);

        home = new JButton("Back to Home");
        home.setBounds(250, 200, 150, 40);
        home.addActionListener(this);
        add(home);

        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("images/back.png"));
        Image i2 = i1.getImage().getScaledInstance(25,25,Image.SCALE_SMOOTH);
        ImageIcon i3 = new ImageIcon(i2);
        back = new JButton(i3);
        back.setBounds(0,0,30,30);
        back.addActionListener(this);
        add(back);

        ImageIcon i4=new ImageIcon(ClassLoader.getSystemResource("images/adminlogin.jpeg"));
        Image i5 = i4.getImage().getScaledInstance(500,300,Image.SCALE_SMOOTH);
        ImageIcon i6 = new ImageIcon(i5);
        JLabel i7 = new JLabel(i6);
        i7.setBounds(0,0,500,300);
        add(i7);

        setLayout(null);
        setLocationRelativeTo(null);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {
        if (ae.getSource() == login) {
            String username = this.username.getText();
            String password = String.valueOf(this.password.getPassword());
            Conn c = new Conn();
            try{
                String str1 = "select * from adminlogin where Username = '"+username+"' and Password = '"+password+"';";
                ResultSet rs = c.s.executeQuery(str1);
                if(rs.next()){
                    JOptionPane.showMessageDialog(null,"Admin Logined Successfully!");
                    new AdminMainPage(username);
                    this.setVisible(false);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Invalid Login Credentials!");
                    this.username.setText(null);
                    this.password.setText(null);
                }
            }catch (Exception e){
                System.out.println(e);
            }

        }

        if (ae.getSource() == home) {
            new Main();
            this.setVisible(false);
        }

        if(ae.getSource()==back){
            this.setVisible(false);
            new Main();
        }
    }

    public static void main(String[] args) {
        new AdminLogin();
    }


}
