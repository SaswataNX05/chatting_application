package whatsaapp;

import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.*;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class Client implements ActionListener{
    
    JButton send;
    JTextField textbox;
    static JPanel text;
    static Box vertical = Box.createVerticalBox(); 
    static JFrame f = new JFrame();
    static DataOutputStream dout;
    
    Client(){
        
        JPanel pan = new JPanel();
        pan.setBounds(0, 0, 450, 70);
        pan.setBackground(new Color(7, 94, 84));
        pan.setLayout(null);
        f.add(pan);
        
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/back.png"));
        Image i2 = i1.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3);
        back.setBounds(5, 20, 25, 25);
        pan.add(back);
        
        back.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                System.exit(0);
            }
        });
        
        ImageIcon i11 = new ImageIcon(ClassLoader.getSystemResource("icons/user2.png"));
        Image i12 = i11.getImage().getScaledInstance(35, 35, Image.SCALE_DEFAULT);
        ImageIcon i13 = new ImageIcon(i12);
        JLabel dp = new JLabel(i13);
        dp.setBounds(55, 15, 35, 35);
        pan.add(dp);
        
        JLabel name = new JLabel("White Devil");
        name.setBounds(105, 15, 200, 25);
        name.setFont(new Font("Arial", Font.BOLD, 13));
        name.setForeground(Color.WHITE);
        pan.add(name);
        
        JLabel sts = new JLabel("Active Now");
        sts.setBounds(105, 35, 200, 20);
        sts.setFont(new Font("Arial", Font.PLAIN, 13));
        sts.setForeground(Color.WHITE);
        pan.add(sts);
        
        ImageIcon i21 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));
        Image i22 = i21.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i23 = new ImageIcon(i22);
        JLabel vdo = new JLabel(i23);
        vdo.setBounds(300, 20, 25, 25);
        pan.add(vdo);
        
        vdo.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(null, "This Option is Not available right now...!");
            }
    });
        
        ImageIcon i31 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));
        Image i32 = i31.getImage().getScaledInstance(25, 25, Image.SCALE_DEFAULT);
        ImageIcon i33 = new ImageIcon(i32);
        JLabel phone = new JLabel(i33);
        phone.setBounds(360, 20, 25, 25);
        pan.add(phone);
        
        phone.addMouseListener(new MouseAdapter(){
            public void mouseClicked(MouseEvent e){
                JOptionPane.showMessageDialog(null, "This Option is Not available right now...!");
            }
    });
        
        ImageIcon i41 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));
        Image i42 = i41.getImage().getScaledInstance(10, 25, Image.SCALE_DEFAULT);
        ImageIcon i43 = new ImageIcon(i42);
        JLabel dot = new JLabel(i43);
        dot.setBounds(420, 20, 10, 25);
        pan.add(dot);
        
        text = new JPanel();
        text.setBounds(5, 75, 440, 540);
        f.add(text);
        
        textbox = new JTextField();
        textbox.setBounds(10, 620, 325, 40);
        textbox.setBackground(Color.WHITE);
        textbox.setFont(new Font("Arial", Font.PLAIN, 15));
        f.add(textbox);
        
        send = new JButton("Send");
        send.setBounds(350, 620, 95, 40);
        send.setBackground(new Color(7, 108, 84));
        send.setForeground(Color.WHITE);
        send.setFont(new Font("Arial", Font.BOLD, 16));
        send.addActionListener(this);
        f.add(send);
       
        ImageIcon icon1 = new ImageIcon(ClassLoader.getSystemResource("icons/icon.png"));
        Image icon2 = icon1.getImage();
        f.setIconImage(icon2);
        
        f.setTitle("WhatsApp");
        f.setBounds(800, 100, 450, 700);
        f.getContentPane().setBackground(Color.WHITE);
        f.setLayout(null);
    }
    
    public void actionPerformed(ActionEvent ae){
       if(ae.getSource() == send){
           try{
                String str = textbox.getText();           
                JPanel msg = sendframe(str);

                text.setLayout(new BorderLayout());
                JPanel right = new JPanel(new BorderLayout());
                right.add(msg, BorderLayout.LINE_END);
                vertical.add(right);
                vertical.add(Box.createVerticalStrut(14));
                text.add(vertical, BorderLayout.PAGE_START);

                dout.writeUTF(str);

                textbox.setText("");

                f.repaint();
                f.invalidate();
                f.validate();
           }catch(Exception e){
               e.printStackTrace();
           }
            
        }
    }
    
    public static JPanel sendframe(String msg){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel out = new JLabel("<html><p style=\"width: 150px\">" +msg+" </p></html>");
        out.setBackground(new Color(37, 211, 102));
        out.setFont(new Font("Railway", Font.PLAIN, 16));
        out.setOpaque(true);
        out.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.add(out);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(dt.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    
    public static JPanel reciveframe(String msg){
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        
        JLabel out = new JLabel("<html><p style=\"width: 150px\">" +msg+" </p></html>");
        out.setBackground(Color.DARK_GRAY);
        out.setForeground(Color.WHITE);
        out.setFont(new Font("Railway", Font.PLAIN, 16));
        out.setOpaque(true);
        out.setBorder(new EmptyBorder(15, 15, 15, 15));
        panel.add(out);
        
        Calendar cal = Calendar.getInstance();
        SimpleDateFormat dt = new SimpleDateFormat("HH:mm");
        
        JLabel time = new JLabel();
        time.setText(dt.format(cal.getTime()));
        panel.add(time);
        
        return panel;
    }
    
    public static void main(String args[]){
        new Client().f.setVisible(true);
        
        try{
            
            Socket s = new Socket("127.0.0.1", 3001);
            DataInputStream din = new DataInputStream(s.getInputStream());
            dout = new DataOutputStream(s.getOutputStream());
            
            while(true){
              text.setLayout(new BorderLayout());
              String msg = din.readUTF();
              JPanel panel = reciveframe(msg);
              
              JPanel left = new JPanel(new BorderLayout());
              left.add(panel, BorderLayout.LINE_START);
              vertical.add(left);
              vertical.add(Box.createVerticalStrut(14));
              text.add(vertical, BorderLayout.PAGE_START);             
              f.validate();
              
            }
            
        }catch(Exception e){
            e.printStackTrace();
        }
    }
    
}
