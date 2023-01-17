package BankManagementSystem;

import java.sql.*;

public class Conn {
    Connection c;
    Statement s;
    Conn(){
        String url = "jdbc:mysql://localhost:3306/bankmanagementsystem";
        String username = "root";
        String password = "Yash@1334";
        try{
            c = DriverManager.getConnection(url, username,password);
            s = c.createStatement();
        }catch(Exception e){
            System.out.println(e);
        }
    }
    public static void main(String[] args) {
        new Conn();
    }

}

