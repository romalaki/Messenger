package front;

import DAO.MessageRealization;
import DAO.StatusRealization;
import DAO.UserRealization;
import entitys.User;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

public class User_Menu extends JFrame implements ActionListener {

    JButton back;
    JButton mail;
    User user;
    JButton reset;
    JButton change_info;
    JButton change_message;
    JPanel panel_send;
    JPanel panel_get;


        public User_Menu(User user){
        this.user = user;
        ImageIcon icon = new ImageIcon(this.getClass().getResource("/img/icon.jpg"));
        this.setIconImage(icon.getImage());
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(1000, 700);
        this.setLocationRelativeTo(null);
        this.setLayout(new BorderLayout(10,10));
        setTitle(user.name+" "+ user.surname);

        JPanel panel1 = new JPanel();
        JPanel panel5 = new JPanel();

        panel1.setBackground(Color.red);
        panel5.setBackground(Color.blue);

        panel1.setPreferredSize(new Dimension(100,100));
        panel5.setPreferredSize(new Dimension(100,100));

        //------------- sub panels --------------------

        panel_get = new JPanel();
        panel_send = new JPanel();

        panel_get.setBackground(Color.DARK_GRAY);
        panel_send.setBackground(Color.BLACK);

        panel5.setLayout(new BorderLayout());
        panel1.setLayout(new BorderLayout());

        //------------- text --------------------

        this.add(panel1,BorderLayout.NORTH);
        this.add(panel5,BorderLayout.CENTER);

        back = new JButton("Back");
        back.addActionListener(this);
        panel1.add(back,BorderLayout.WEST);

        mail = new JButton("Message");
        mail.addActionListener(this);
        panel1.add(mail,BorderLayout.EAST);

        JLabel text_posht = new JLabel("Email", JLabel.CENTER);
        text_posht.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
        text_posht.setForeground(Color.white);
        panel1.add(text_posht);

        JPanel panel_top = new JPanel();
        panel_top.setPreferredSize(new Dimension(this.getWidth()/2,50));
        panel_top.setBackground(Color.DARK_GRAY);
        panel_get.add(panel_top,BorderLayout.NORTH);

        JPanel panel_top2 = new JPanel();
        panel_top2.setPreferredSize(new Dimension(this.getWidth()/2,50));
        panel_top2.setBackground(Color.BLACK);
        panel_send.add(panel_top2,BorderLayout.NORTH);

        JLabel text_send = new JLabel("Received", JLabel.CENTER);
        text_send.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
        text_send.setForeground(Color.white);
        panel_top.add(text_send,BorderLayout.NORTH);

        JLabel text_get = new JLabel("Sended", JLabel.CENTER);
        text_get.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
        text_get.setForeground(Color.white);
        panel_top2.add(text_get,BorderLayout.NORTH);

        reset = new JButton("Update");
        reset.addActionListener(this);
        panel5.add(reset,BorderLayout.NORTH);

        change_info = new JButton("Change User Info");
        change_info.addActionListener(this);
        panel1.add(change_info,BorderLayout.NORTH);

        change_message = new JButton("Change Message");
        change_message.addActionListener(this);
        panel5.add(change_message,BorderLayout.SOUTH);

            //------------- CHAT_GET --------------------

            StatusRealization sr = new StatusRealization();
            UserRealization ur = new UserRealization();
            MessageRealization mr = new MessageRealization();
            ResultSet rs = mr.get_owned(user.login);

            int j=0;

            try {
            while(rs.next()){
                j++;
                ResultSet temp = ur.get_name(rs.getString(2));
                ResultSet temp2 = sr.get_status(rs.getString(2));

                temp.next();
                temp2.next();



                ImageIcon img =null;
                if(temp2.getString(2).equals("online")){
                    img = new ImageIcon(this.getClass().getResource("/img/green.png"));
                }else if(temp2.getString(2).equals("offline")){
                    img = new ImageIcon(this.getClass().getResource("/img/red.png"));
                }

                JLabel text_name = new JLabel("From: "+temp.getString(3), JLabel.CENTER);
                text_name.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
                text_name.setForeground(Color.white);

                JLabel text_surname = new JLabel(temp.getString(4)+" ", JLabel.CENTER);
                text_surname.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
                text_surname.setForeground(Color.white);

                JLabel text_text = new JLabel(rs.getString(4), JLabel.CENTER);
                text_text.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 30));
                text_text.setForeground(Color.white);

                JPanel t = new JPanel();
                t.setPreferredSize(new Dimension(this.getWidth()/2,50));

                JLabel l = new JLabel();
                l.setIcon(img);
                l.setHorizontalAlignment(SwingConstants.LEFT);
                l.setPreferredSize(new Dimension(25,25));
                t.add(l);

                t.setBackground(Color.BLACK);
                t.add(text_name,BorderLayout.CENTER);
                t.add(text_surname,BorderLayout.CENTER);
                t.add(text_text,BorderLayout.CENTER);

                panel_get.add(t,BorderLayout.NORTH);
            }}catch (Exception e){
                e.printStackTrace();
            }

            panel_get.setPreferredSize(new Dimension(this.getWidth()/2, 50+j*60));
            JScrollPane scrollSideBar2 = new JScrollPane(panel_get,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            panel5.add(scrollSideBar2,BorderLayout.WEST);


            //------------- CHAT_SENT --------------------

            ResultSet rr = mr.get_sented(user.login);
            int i=0;

            try{
            while(rr.next()){
                i++;
                ResultSet temp = ur.get_name(rr.getString(3));
                ResultSet temp2 = sr.get_status(rr.getString(3));

                temp.next();
                temp2.next();

                ImageIcon img =null;
                if(temp2.getString(2).equals("online")){
                    img = new ImageIcon(this.getClass().getResource("/img/green.png"));
                }else if(temp2.getString(2).equals("offline")){
                    img = new ImageIcon(this.getClass().getResource("/img/red.png"));
                }

                JLabel text_name = new JLabel("To: "+temp.getString(3), JLabel.CENTER);
                text_name.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
                text_name.setForeground(Color.black);

                JLabel text_surname = new JLabel(temp.getString(4)+" ", JLabel.CENTER);
                text_surname.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 25));
                text_surname.setForeground(Color.black);

                JLabel text_text = new JLabel(rr.getString(4), JLabel.CENTER);
                text_text.setFont(new Font("Times New Roman", Font.ROMAN_BASELINE, 30));
                text_text.setForeground(Color.black);

                JPanel t = new JPanel();
                t.setPreferredSize(new Dimension(this.getWidth()/2,50));

                JLabel l = new JLabel();
                l.setIcon(img);
                l.setHorizontalAlignment(SwingConstants.LEFT);
                l.setPreferredSize(new Dimension(25,25));
                t.add(l);

                t.setBackground(Color.white);
                t.add(text_name,BorderLayout.CENTER);
                t.add(text_surname,BorderLayout.CENTER);
                t.add(text_text,BorderLayout.CENTER);

                panel_send.add(t,BorderLayout.NORTH);

                panel_send.setPreferredSize(new Dimension(this.getWidth()/2,text_text.getHeight()));

            }}catch (Exception e){
                e.printStackTrace();
            }


            panel_send.setPreferredSize(new Dimension(this.getWidth()/2, 50+i*60));
            JScrollPane scrollSideBar = new JScrollPane(panel_send,JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);

            panel5.add(scrollSideBar,BorderLayout.CENTER);


        this.setVisible(true);
    }

    public void paint(Graphics g){
            super.paint(g);

    }


    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == back){
            this.dispose();
            StatusRealization db = new StatusRealization();
                db.set_status("Offline",user);
            new Login();
        }
        else if(e.getSource() == mail){
            new Write(user);
        }
        else if(e.getSource() == reset){
                this.dispose();
                new User_Menu(user);
        }
        else if(e.getSource()==change_info){
            this.dispose();
            new ChangeUser(user);
        }
        else if(e.getSource()==change_message){
            this.dispose();
            new ChangeMessage(user);

        }
    }

}
