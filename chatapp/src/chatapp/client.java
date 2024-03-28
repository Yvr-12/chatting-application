package chatapp;
import javax.swing.*;
import javax.swing.border.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.*;
import java.text.*;
import java.net.*;
import java.io.*;

public class client implements ActionListener{
    JTextField text;//declared globally to be used in ther functions
    static JPanel a1;//declared globally to be used in ther functions
    static Box vertical = Box.createVerticalBox();
    static DataOutputStream  dout;
    static JFrame f = new JFrame(); 
    client(){
        f.setLayout(null);//layout of frame
        JPanel p1 = new JPanel();//panel at top
        p1.setBackground(new Color (7, 94, 84));//panel color
        p1.setBounds(0,0,450,70);//coordinates of panel
        p1.setLayout(null);//layout of return arrow at corner
        f.add(p1);
        
        //image1-back button
        ImageIcon i1 = new ImageIcon(ClassLoader.getSystemResource("icons/3.png"));//address of image on panel
        Image i2 = i1.getImage().getScaledInstance(25, 25,  Image.SCALE_DEFAULT);//scaling of return arrow
        ImageIcon i3 = new ImageIcon(i2);
        JLabel back = new JLabel(i3); //to show the image on panel  
        back.setBounds(5, 20, 25, 25); //coordinates of the image
        p1.add(back); 
        //closing of application on back button click
        back.addMouseListener(new MouseAdapter(){ 
            public void mouseClicked(MouseEvent ae){
                f.setVisible(false);
            }
        });

        //image2-profile
        ImageIcon i4 = new ImageIcon(ClassLoader.getSystemResource("icons/download (1).png"));//address of image on panel
        Image i5 = i4.getImage().getScaledInstance(50, 50,  Image.SCALE_DEFAULT);//scaling of return arrow
        ImageIcon i6 = new ImageIcon(i5);
        JLabel profile = new JLabel(i6); //to show the image on panel  
        profile.setBounds(40, 10, 50, 50); //coordinates of the image
        p1.add(profile);

        //image3-video icon
        ImageIcon i7 = new ImageIcon(ClassLoader.getSystemResource("icons/video.png"));//address of image on panel
        Image i8 = i7.getImage().getScaledInstance(50, 50,  Image.SCALE_DEFAULT);//scaling of return arrow
        ImageIcon i9 = new ImageIcon(i8);
        JLabel video = new JLabel(i9); //to show the image on panel  
        video.setBounds(300, 20, 30, 30); //coordinates of the image
        p1.add(video);

        //image4-phone icon
        ImageIcon i10 = new ImageIcon(ClassLoader.getSystemResource("icons/phone.png"));//address of image on panel
        Image i11 = i10.getImage().getScaledInstance(50, 50,  Image.SCALE_DEFAULT);//scaling of return arrow
        ImageIcon i12 = new ImageIcon(i11);
        JLabel phone = new JLabel(i12); //to show the image on panel  
        phone.setBounds(360, 20, 35, 30); //coordinates of the image
        p1.add(phone);

        //image5-morevert(3 dots)
        ImageIcon i13 = new ImageIcon(ClassLoader.getSystemResource("icons/3icon.png"));//address of image on panel
        Image i14 = i13.getImage().getScaledInstance(10, 25,  Image.SCALE_DEFAULT);//scaling of return arrow
        ImageIcon i15 = new ImageIcon(i14);
        JLabel morevert = new JLabel(i15); //to show the image on panel  
        morevert.setBounds(420, 20, 10, 25); //coordinates of the image
        p1.add(morevert);

        //name of user
        JLabel name = new JLabel("Receiver");
        name.setBounds(110, 15,100,18);
        name.setForeground(Color.WHITE);//color of text
        name.setFont(new Font("SAN_SERIF", Font.BOLD, 18));//font size
        p1.add(name);

        //active now
        JLabel status = new JLabel("Active now");
        status.setBounds(110, 35,100,18);
        status.setForeground(Color.WHITE);//color of text
        status.setFont(new Font("SAN_SERIF", Font.BOLD, 14));//font size
        p1.add(status);

        //text area
        a1 = new JPanel();
        a1.setBounds(5, 75, 440, 570);
        f.add(a1);
        
        //text field to enter text
        text = new JTextField();
        text.setBounds(5, 655, 310, 40);  
        text.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(text);

        //Button beside text field
        JButton send = new JButton("Send");
        send.setBounds(320, 655,123,40);
        send.setBackground(new Color(7,94,84));
        send.setForeground(Color.WHITE);
        send.addActionListener(this);//action needed on pressing the button. mian action in action listener method
        send.setFont(new Font("SAN_SERIF", Font.PLAIN, 16));
        f.add(send);

        f.setSize(450, 700);//size of frame
        f.setLocation(800, 50);//location of frame
        f.setUndecorated(true);//remove header of frame
        f.getContentPane().setBackground(Color.WHITE);//color of frame
        f.setVisible(true);//visibility of frame
    }
    public void actionPerformed(ActionEvent ae){//since actionlistener contains an abstract method so we need to override it
         try{String out = text.getText();
         JPanel p2 = formatLabel(out);
         a1.setLayout(new BorderLayout());
         JPanel right = new JPanel(new BorderLayout());//box which showss the message in the panel
         right.add(p2, BorderLayout.LINE_END);
         vertical.add(right);
         vertical.add(Box.createVerticalStrut(15));
         a1.add(vertical, BorderLayout.PAGE_START);
         dout.writeUTF(out);
         text.setText("");//to empty text after sending
         f.repaint();//1
         f.invalidate();//2
         f.validate();//3. all functions for dispalying the message in the frame
    }catch(Exception e){
        e.printStackTrace();
    }
}
    public static JPanel formatLabel(String out){
            JPanel panel = new JPanel();
            panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
            JLabel output = new JLabel("<html><p style=\"width: 150px\">" + out + "</p></html>");//fixing the width of the message box using html tag. remeber = here
            output.setFont(new Font("Tahoma", Font.PLAIN, 16));
            output.setBackground(new Color(37, 211, 102));
            output.setOpaque(true);
            output.setBorder(new EmptyBorder(15, 15, 15, 15));//padding around the message
            panel.add(output);
            Calendar cal = Calendar.getInstance();//time stamp
            SimpleDateFormat sdf = new SimpleDateFormat("HH:mm");
            JLabel time = new JLabel();
            time.setText(sdf.format(cal.getTime()));
            panel.add(time);

            return panel;
    }
    public static void main(String[] args) {
        new client();
        try{
            @SuppressWarnings("resource")//quick fix for skt
            Socket s = new Socket("127.0.0.1", 6001);//localhost and port number
            DataInputStream din = new DataInputStream(s.getInputStream());
               dout = new DataOutputStream(s.getOutputStream());
               while(true){
                a1.setLayout(new BorderLayout());
                String msg = din.readUTF();
                JPanel panel = formatLabel(msg);
                JPanel  left = new JPanel(new BorderLayout());
                left.add(panel, BorderLayout.LINE_START);
                vertical.add(left);
                vertical.add(Box.createVerticalStrut(15));
                a1.add(vertical, BorderLayout.PAGE_START);
                f.validate();


               }
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}