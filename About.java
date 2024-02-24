import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class About extends JFrame implements ActionListener {
    JButton b1;
    About() {
        setBounds(400, 100, 600, 500);
        //  ImageIcon il= new ImageIcon(ClassLoader.getSystemResource("NotepadIcon.png"));


        JLabel l3 = new JLabel("<html>Code for Interview<br>Youtube Channel Version 2021<br>2021 Code for Interview. All rights reserved<br><br>Notepad is a word processing program, <br>which allows changing of text in a computer file.<br>Notepad is a simple text editor for basic text-editing program<br> which enables computer users to create documents. </html>");
        l3.setFont(new Font("Arial", Font.PLAIN, 12));
        l3.setBounds(250, 230, 500, 300);
        add(l3);

        b1 = new JButton("OK");
        b1.setBounds(580, 500, 80, 25);
        b1.addActionListener(this);
        add(b1);


        setVisible(true);
    }
    public void actionPerformed(ActionEvent ae){
        this.setVisible(false);
    }

    public static void main(String[] args)  {
     new About().setVisible(true);
    }

}
