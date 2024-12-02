package front;

import DAO.MessageRealization;
import entitys.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Write extends JFrame implements ActionListener {

    JTextField other_login;
    JTextField text;
    JButton send;
    User user;

    Write(User user) {
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/icon.jpg"));
        this.setIconImage(icon.getImage());
        this.user = user;
        setTitle("Send message");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(500, 350);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        this.setVisible(true);

        other_login = new JTextField(10);
        text = new JTextField(10);

        JPanel panel1 = new JPanel();
        JPanel panel2 = new JPanel();

        panel1.setBackground(Color.red);
        panel2.setBackground(Color.blue);
        panel1.setPreferredSize(new Dimension(100,100));
        panel2.setPreferredSize(new Dimension(150,100));

        JLabel txt = new JLabel("Reciver");
        txt.setFont(new Font("Times New Roman", Font.BOLD, 25));
        txt.setForeground(Color.white);

        JLabel txt2 = new JLabel("Text");
        txt2.setFont(new Font("Times New Roman", Font.BOLD, 25));
        txt2.setForeground(Color.white);

        panel1.setLayout(new FlowLayout());
        panel2.setLayout(new FlowLayout());

        panel1.add(txt,BorderLayout.NORTH);
        panel2.add(txt2,BorderLayout.NORTH);

        panel1.add(other_login,BorderLayout.CENTER);
        panel2.add(text,BorderLayout.CENTER);

        send = new JButton("Send");
        send.addActionListener(this);

        panel2.add(send,BorderLayout.SOUTH);
        this.add(panel1, BorderLayout.NORTH);
        this.add(panel2, BorderLayout.CENTER);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            MessageRealization db = new MessageRealization();
                if(other_login.getText().equals(user.login))
                    return;
                if(db.send_message(user, other_login.getText(),text.getText()))
                    this.dispose();
        }
    }
}
