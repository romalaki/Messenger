package front;
import DAO.UserRealization;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class Register extends JFrame implements ActionListener {

    JButton reg;
    JButton go;
    JTextField surname;
    JTextField name;
    JTextField login;
    JTextField password;

    Register(){
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/icon.jpg"));
        setTitle("Register");
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10,10));

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();
        JPanel panel3 = new JPanel();
        JPanel panel4 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.green);
        panel3.setBackground(Color.yellow);
        panel4.setBackground(Color.magenta);
        panel5.setBackground(Color.blue);



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
        panel7.setBackground(Color.darkGray);
        panel8.setBackground(Color.gray);
        panel9.setBackground(Color.lightGray);
        panel10.setBackground(Color.white);

        panel5.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());

        panel6.setPreferredSize(new Dimension(50,50));
        panel7.setPreferredSize(new Dimension(50,50));
        panel8.setPreferredSize(new Dimension(120,120));
        panel9.setPreferredSize(new Dimension(110,110));
        panel10.setPreferredSize(new Dimension(50,50));

        panel5.add(panel6,BorderLayout.NORTH);
        panel5.add(panel7,BorderLayout.SOUTH);
        panel5.add(panel8,BorderLayout.WEST);
        panel5.add(panel9,BorderLayout.EAST);
        panel5.add(panel10,BorderLayout.CENTER);

        //------------- sub panels+button --------------------

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel2,BorderLayout.WEST);
        this.add(panel3,BorderLayout.EAST);
        this.add(panel4,BorderLayout.SOUTH);
        this.add(panel5,BorderLayout.CENTER);

        JLabel txt = new JLabel("Registration", JLabel.CENTER);
        txt.setVerticalAlignment(JLabel.TOP);
        txt.setFont(new Font("Times New Roman", Font.BOLD, 50));
        txt.setForeground(Color.white);


        reg = new JButton("Authorization");
        reg.addActionListener(this);

        panel1.add(reg,BorderLayout.EAST);
        panel1.add(txt);

        //------------

        JLabel lg = new JLabel("Login:", JLabel.CENTER);
        lg.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
         login = new JTextField(10);

        JLabel ps = new JLabel("Password:", JLabel.CENTER);
        ps.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        password = new JTextField(10);

        JLabel n = new JLabel("Name:", JLabel.CENTER);
        n.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        name = new JTextField(10);

        JLabel sn = new JLabel("Surname:", JLabel.CENTER);
        sn.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        surname = new JTextField(10);

        panel8.add(lg,BorderLayout.NORTH);
        panel8.add(login,BorderLayout.CENTER);

        panel8.add(ps,BorderLayout.NORTH);
        panel8.add(password,BorderLayout.CENTER);

        panel8.add(n,BorderLayout.NORTH);
        panel8.add(name,BorderLayout.CENTER);

        panel8.add(sn,BorderLayout.NORTH);
        panel8.add(surname,BorderLayout.CENTER);

        go = new JButton("Register");
        go.addActionListener(this);

        panel7.add(go);

        this.setVisible(true);
    }
    public void actionPerformed(ActionEvent e){
        if(e.getSource()==reg) {
            this.dispose();
            new Login();
        }

        else if(e.getSource()==go){
                UserRealization db = new UserRealization();

                if(!db.check_reg(login.getText(),password.getText(), name.getText(), surname.getText())) {
                    System.out.println("Wrond data");
                    return;
                }
                db.signUp(login.getText(),password.getText(), name.getText(), surname.getText());
                this.dispose();
                new Login();
        }

}}
