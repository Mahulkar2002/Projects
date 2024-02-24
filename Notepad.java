
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.io.*;


public class Notepad extends JFrame implements ActionListener {
    JTextArea area;
    String text = "";

    //constructor
    Notepad() {

        setTitle("RNotepad");
        setExtendedState(JFrame.MAXIMIZED_BOTH);


        JMenuBar menubar = new JMenuBar();
        menubar.setBackground(Color.WHITE);


        //FILE MENU
        JMenu file = new JMenu("File");
        file.setFont(new Font("Arial", Font.PLAIN, 14));

        JMenuItem newdoc = new JMenuItem("New");
        newdoc.addActionListener(this);
        newdoc.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_N, ActionEvent.CTRL_MASK));

        JMenuItem open = new JMenuItem("open");
        open.addActionListener(this);
        open.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));

        JMenuItem save = new JMenuItem("Save");
        save.addActionListener(this);
        save.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));

        JMenuItem print = new JMenuItem("print");
        print.addActionListener(this);
        print.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem exit = new JMenuItem("exit");
        print.addActionListener(this);
        exit.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_E, ActionEvent.CTRL_MASK));

        file.add(newdoc);
        file.add(open);
        file.add(save);
        file.add(print);
        file.add(exit);

        menubar.add(file);


        //EDIT MENU
        JMenu edit = new JMenu("Edit");
        edit.setFont(new Font("Aerial", Font.PLAIN, 14));

        JMenuItem copy = new JMenuItem("Copy");
        copy.addActionListener(this);
        copy.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem paste = new JMenuItem("Paste");
        paste.addActionListener(this);
        paste.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_P, ActionEvent.CTRL_MASK));

        JMenuItem cut = new JMenuItem("Cut");
        cut.addActionListener(this);
        cut.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));

        JMenuItem selectAll = new JMenuItem("selectAll");
        selectAll.addActionListener(this);
        selectAll.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.CTRL_MASK));

        edit.add(copy);
        edit.add(paste);
        edit.add(cut);
        edit.add(selectAll);

        menubar.add(edit);


        //HELP MENU
        JMenu helpMenu = new JMenu("Help");
        helpMenu.setFont(new Font("Arial", Font.PLAIN, 14));

        JMenuItem help = new JMenuItem("about");
        selectAll.addActionListener(this);
        help.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_H, ActionEvent.CTRL_MASK));

        helpMenu.add(help);
        menubar.add(helpMenu);

        area = new JTextArea();
        area.setFont(new Font("Arial", Font.PLAIN, 14));
        area.setLineWrap(true);
        area.setWrapStyleWord(true);

  /*      JScrollPane pane = new JScrollPane(area); // Pass JTextArea 'area' to JScrollPane constructor
        pane.setBorder(BorderFactory.createEmptyBorder());
        add(pane);*/

        JScrollPane pane = new JScrollPane(area);
        pane.setBorder(BorderFactory.createEmptyBorder());
        pane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
        pane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        add(pane);


        setJMenuBar(menubar);
        setVisible(true);


    }

    @Override
    public void actionPerformed(ActionEvent ae) {
        if (ae.getActionCommand().equals("New")) {
            area.setText(" ");
        } else if (ae.getActionCommand().equals("open")) {
            JFileChooser choose = new JFileChooser();
            choose.setAcceptAllFileFilterUsed(false);
            FileNameExtensionFilter restrict = new FileNameExtensionFilter("Only .text file", "txt");
            choose.addChoosableFileFilter(restrict);

            int action = choose.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File file = choose.getSelectedFile();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                area.read(reader, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else if (ae.getActionCommand().equals("Save"))
        {
            JFileChooser saveas = new JFileChooser();
            saveas.setApproveButtonText("Save");

            int action = saveas.showOpenDialog(this);
            if (action != JFileChooser.APPROVE_OPTION) {
                return;
            }
            File filename = new File(saveas.getSelectedFile() + ".txt");
            BufferedWriter outFile = null;

            try {
                outFile = new BufferedWriter(new FileWriter(filename));
                area.write(outFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else if(ae.getActionCommand().equals("print")){
            try{
                area.print();
            }catch(Exception e){}

        }else if(ae.getActionCommand().equals("Exit")){
            System.exit(0);
        } else if (ae.getActionCommand().equals("Copy")) {
            text=area.getSelectedText();
        } else if (ae.getActionCommand().equals("Paste")) {
            area.insert(text, area.getCaretPosition());
        } else if (ae.getActionCommand().equals("Cut")) {
            text = area.getSelectedText();
            area.replaceRange("", area.getSelectionStart(), area.getSelectionEnd());
        } else if(ae.getActionCommand().equals("selectAll")){
            area.selectAll();
        } else if (ae.getActionCommand().equals("about")) {
          new About().setVisible(true);
        }

    }

        public static void main (String[]args){
            new Notepad();
        }

    }

