package front;

import DAO.*;
import entitys.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Login extends JFrame implements ActionListener {

    JButton reg;
    JButton go;
    JTextField login;
    JTextField password;

    public Login(){
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/icon.jpg"));
        setTitle("Login");

        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(0,0));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.black);
        panel2.setBackground(Color.black);
        panel3.setBackground(Color.black);
        panel4.setBackground(Color.black);
        panel5.setBackground(Color.black);


        panel1.setPreferredSize(new Dimension(100,100));
        panel2.setPreferredSize(new Dimension(150,100));
        panel3.setPreferredSize(new Dimension(150,100));
        panel4.setPreferredSize(new Dimension(100,100));
        panel5.setPreferredSize(new Dimension(100,100));

        //------------- sub panels --------------------

        JPanel panel6 = new JPanel();
        JPanel panel7 = new JPanel();
        JPanel panel8 = new JPanel();
        JPanel panel9 = new JPanel();
        JPanel panel10 = new JPanel();

        panel6.setBackground(Color.black);
        panel7.setBackground(Color.black);
        panel8.setBackground(Color.black);
        panel9.setBackground(Color.black);
        panel10.setBackground(Color.black);

        panel5.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());

        panel6.setPreferredSize(new Dimension(50,50));
        panel7.setPreferredSize(new Dimension(50,50));
        panel8.setPreferredSize(new Dimension(600,120));
        panel9.setPreferredSize(new Dimension(50,50));
        panel10.setPreferredSize(new Dimension(50,50));

        panel5.add(panel6,BorderLayout.NORTH);
        panel5.add(panel7,BorderLayout.SOUTH);
        panel5.add(panel8,BorderLayout.WEST);
        panel5.add(panel9,BorderLayout.EAST);
        //panel5.add(panel10,BorderLayout.CENTER);

        //------------- sub panels --------------------


        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.WEST);
        this.add(panel3,BorderLayout.EAST);
        this.add(panel4,BorderLayout.SOUTH);
        this.add(panel5,BorderLayout.CENTER);

        JLabel txt = new JLabel("Authorization", JLabel.CENTER);
        txt.setVerticalAlignment(JLabel.TOP);
        txt.setFont(new Font("Times New Roman", Font.BOLD, 30));
        txt.setForeground(Color.white);


        reg = new JButton("Registration");
        reg.addActionListener(this);
        panel1.add(reg,BorderLayout.EAST);
        panel1.add(txt);

        //------------

        JLabel n = new JLabel("Login:", JLabel.CENTER);
        n.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        n.setForeground(Color.white);
        login = new JTextField(10);

        JLabel sn = new JLabel("Password:", JLabel.CENTER);
        sn.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        sn.setForeground(Color.white);
        password = new JTextField(10);

        panel8.add(n,BorderLayout.NORTH);
        panel8.add(login,BorderLayout.CENTER);

        panel8.add(sn,BorderLayout.NORTH);
        panel8.add(password,BorderLayout.CENTER);

        go = new JButton("Enter");
        go.addActionListener(this);

        panel8.add(go);

        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==reg) {
            this.dispose();
            new Register();
        }
        else if(e.getSource()==go){
                UserRealization ur = new UserRealization();
                StatusRealization sr = new StatusRealization();
                ResultSet  rs= ur.authorise(login.getText(),password.getText());
                try {
                    if (rs.next()) {
                        this.dispose();

                        User us = new User(rs.getString(3), rs.getString(4), rs.getString(1));
                        sr.set_status("Online", us);
                        new User_Menu(us);

                    } else {
                        System.out.println("Found nobody");
                    }
                }catch (Exception ex){
                    ex.printStackTrace();
                }
        }
    }
}
