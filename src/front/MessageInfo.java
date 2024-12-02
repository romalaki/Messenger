package front;

import DAO.MessageRealization;
import DAO.UserRealization;
import entitys.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class MessageInfo extends JFrame implements ActionListener {

    JTextField reciver;
    JTextField text;
    JButton send;
    JButton back;
    int id;
    User user;

    public MessageInfo(User user,int id){
        this.user = user;
        this.id = id;
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/icon.jpg"));
        this.setIconImage(icon.getImage());
        setTitle("Change information");

        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        this.setSize(150, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10, 10));

        JPanel panel1 = new JPanel();

        panel1.setPreferredSize(new Dimension(150, 700));
        panel1.setBackground(Color.BLACK);

        MessageRealization mr = new MessageRealization();
        ResultSet rs = mr.get_message(id);
        try {
            rs.next();
        }catch (SQLException e){
            e.printStackTrace();
        }

        JLabel rec = new JLabel("Receiver:", JLabel.CENTER);
        rec.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        rec.setForeground(Color.white);
        reciver = new JTextField(10);

        try {
            reciver.setText(rs.getString(3));
        }catch (SQLException e){
            e.printStackTrace();
        }

        JLabel txt = new JLabel("Text:", JLabel.CENTER);
        txt.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 15));
        txt.setForeground(Color.white);
        text = new JTextField(10);
        try {
            text.setText(rs.getString(4));
        }catch (SQLException e){
            e.printStackTrace();
        }

        panel1.add(rec,BorderLayout.NORTH);
        panel1.add(reciver,BorderLayout.CENTER);

        panel1.add(txt,BorderLayout.NORTH);
        panel1.add(text,BorderLayout.CENTER);

        send = new JButton("Enter");
        send.addActionListener(this);

        panel1.add(send);
        this.add(panel1, BorderLayout.CENTER);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBackground(Color.white);
        back.setFont(new Font("Times New Roman", Font.BOLD, 25));
        back.setForeground(Color.black);
        this.add(back, BorderLayout.SOUTH);

        this.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == send) {
            MessageRealization db = new MessageRealization();
                if(reciver.getText().equals(user.login))
                    return;
                if(!db.change_message(id, reciver.getText(), text.getText()))
                    return;
                new ChangeMessage(user);
                this.dispose();

        }else if(e.getSource() == back){
            this.dispose();
                new ChangeMessage(user);
        }
    }
}
