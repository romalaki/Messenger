package front;

import DAO.MessageRealization;
import DAO.StatusRealization;
import DAO.UserRealization;
import entitys.User;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.nio.charset.StandardCharsets;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ChangeMessage extends JFrame implements ActionListener {

    JTextField surname;
    JTextField name;
    JButton back;
    User user;

    public ChangeMessage(User user){
        this.user = user;
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/icon.jpg"));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10,10));
        setTitle("Change message");

        JPanel panel1 = new JPanel();
        panel1.setBackground(Color.black);

        MessageRealization mr = new MessageRealization();
        UserRealization ur = new UserRealization();
        StatusRealization sr = new StatusRealization();
        ResultSet rr = mr.get_sented(user.login);

        int i=0;

        try {
        while(rr.next()){
            i++;
            ResultSet temp = ur.get_name(rr.getString(2));

            ResultSet temp2 = sr.get_status(rr.getString(2));

            temp.next();
            temp2.next();

            JPanel png = new JPanel();
            panel1.setPreferredSize(new Dimension(50,50));

            JLabel text_name = new JLabel("To: "+rr.getString(3), JLabel.CENTER);
            text_name.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
            text_name.setForeground(Color.black);

            JLabel text_text = new JLabel(rr.getString(4), JLabel.CENTER);
            text_text.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 30));
            text_text.setForeground(Color.black);

            JPanel t = new JPanel();
            t.setPreferredSize(new Dimension(this.getWidth()/2,50));

            JLabel l = new JLabel();
            l.setHorizontalAlignment(SwingConstants.LEFT);
            l.setPreferredSize(new Dimension(25,25));
            t.add(l);

            t.setBackground(Color.white);
            t.add(text_name,BorderLayout.CENTER);
            t.add(text_text,BorderLayout.CENTER);

            panel1.add(t,BorderLayout.NORTH);

            panel1.setPreferredSize(new Dimension(this.getWidth()/2,text_text.getHeight()));

            JButton a = new JButton("Change message");
            a.setName(String.valueOf(rr.getString(1)));
            a.setBackground(Color.white);
            a.setForeground(Color.black);
            a.setFont(new Font("Times New Roman", Font.BOLD,25));
            a.addActionListener(this);
            panel1.add(a,BorderLayout.SOUTH);

            this.add(panel1,BorderLayout.CENTER);

        }
        }catch (SQLException e){
            e.printStackTrace();
        }

        panel1.setPreferredSize(new Dimension(this.getWidth()/2, 50+i*60));
        JScrollPane scrollSideBar = new JScrollPane(panel1,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

        back = new JButton("Back");
        back.addActionListener(this);
        back.setBackground(Color.white);
        back.setFont(new Font("Times New Roman", Font.BOLD, 25));
        back.setForeground(Color.black);
        this.add(back, BorderLayout.SOUTH);

        this.add(scrollSideBar,BorderLayout.CENTER);
        setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            this.dispose();
            new User_Menu(user);
        }else{
            JButton jb = (JButton)e.getSource();
            this.dispose();
            new MessageInfo(user,Integer.parseInt(jb.getName()));

        }
        }
    }
