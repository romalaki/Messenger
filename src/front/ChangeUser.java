package front;

import DAO.UserRealization;
import entitys.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class ChangeUser extends JFrame implements ActionListener {

    JTextField surname;
    JTextField name;
    JButton send;
    JButton delete;
    User user;

    public ChangeUser(User user) {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/icon.jpg"));
        this.setIconImage(icon.getImage());
        this.user = user;
        setTitle("Change information");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(150, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        JPanel panel1 = new JPanel();

        panel1.setPreferredSize(new Dimension(150, 700));
        panel1.setBackground(Color.BLACK);


        JLabel login = new JLabel("Login:", JLabel.CENTER);
        login.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        login.setForeground(Color.white);
        JTextField l = new JTextField(10);
        l.setText(user.login);
        l.setEditable(false);

        JLabel n = new JLabel("Name:", JLabel.CENTER);
        n.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        n.setForeground(Color.white);
        name = new JTextField(10);
        name.setText(user.name);

        JLabel sn = new JLabel("SurName:", JLabel.CENTER);
        sn.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        sn.setForeground(Color.white);
        surname = new JTextField(10);
        surname.setText(user.surname);

        panel1.add(login,BorderLayout.NORTH);
        panel1.add(l,BorderLayout.CENTER);

        panel1.add(n,BorderLayout.NORTH);
        panel1.add(name,BorderLayout.CENTER);

        panel1.add(sn,BorderLayout.NORTH);
        panel1.add(surname,BorderLayout.CENTER);

        send = new JButton("Enter");
        send.addActionListener(this);

        delete = new JButton("Delete User");
        delete.addActionListener(this);

        panel1.add(send);
        add(delete, BorderLayout.SOUTH);
        this.add(panel1, BorderLayout.CENTER);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            UserRealization db = new UserRealization();

                if(!db.change_user(user,name.getText(),surname.getText()))
                    return;

                user.name = name.getText();
                user.surname = surname.getText();

                new User_Menu(user);
                this.dispose();

        }else if (e.getSource() == delete) {
            UserRealization db = new UserRealization();
            db.delete_user(user);

            user=null;

            new Login();
            this.dispose();
        }
    }
}
