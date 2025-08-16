package za.ac.tut.encryption;

import java.awt.*;
import java.io.*;
import java.util.*;
import javax.swing.*;
import za.ac.tut.message.Message;

public class MessageEncryptor extends JFrame {

    //panels
    private JPanel headingPnl;
    private JPanel firsttxtAreaPnl;
    private JPanel secondtxtAreaPnl;
    private JPanel twoAreasPnl;
    private JPanel mainPnl;

//lables
    private JLabel headingLbl;

//text area
    private JTextArea plainArea;
    private JTextArea encryptedArea;

//scroll pane
    private JScrollPane sPlain;
    private JScrollPane sEncrypted;

//menu
    private JMenuBar menu;
    private JMenu menuFile;
    private JMenuItem openFile;
    private JMenuItem encrypt;
    private JMenuItem save;
    private JMenuItem clear;
    private JMenuItem exit;

    public MessageEncryptor() {
        setTitle("Secure Messages");
        setSize(800, 480);
        setLocationRelativeTo(this);

// create panels
        headingPnl = new JPanel(new FlowLayout());
        firsttxtAreaPnl = new JPanel(new BorderLayout());
        firsttxtAreaPnl.setBorder(BorderFactory.createTitledBorder("Plain message"));

        secondtxtAreaPnl = new JPanel(new BorderLayout());
        secondtxtAreaPnl.setBorder(BorderFactory.createTitledBorder("Encrypted message"));

        twoAreasPnl = new JPanel(new GridLayout(1, 2));
        mainPnl = new JPanel(new BorderLayout());

//create menu
        menu = new JMenuBar();
        menuFile = new JMenu("File");
        openFile = new JMenuItem("Open file");
        encrypt = new JMenuItem("Encrypt message");
        save = new JMenuItem("Save encrypted message");
        clear = new JMenuItem("Clear");
        exit = new JMenuItem("Exit");

        menuFile.add(openFile);
        menuFile.add(encrypt);
        menuFile.add(save);
        menuFile.addSeparator();
        menuFile.add(clear);
        menuFile.add(exit);
        menu.add(menuFile);
        setJMenuBar(menu);

//create heading
        headingLbl = new JLabel("Message Encryptor", JLabel.CENTER);
        headingLbl.setFont(new Font("Serif", Font.BOLD | Font.ITALIC, 22));
        headingLbl.setForeground(Color.BLUE);
        headingLbl.setBorder(BorderFactory.createEtchedBorder());

//create text areas
        plainArea = new JTextArea(20, 25);
//plain scroll pane
        sPlain = new JScrollPane(plainArea);
        sPlain.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

        encryptedArea = new JTextArea(20, 25);
//encrypted scrollpane
        sEncrypted = new JScrollPane(encryptedArea);
        sEncrypted.setBorder(BorderFactory.createLineBorder(Color.BLUE, 1));

//add components to panels
        headingPnl.add(headingLbl);
        firsttxtAreaPnl.add(sPlain);
        secondtxtAreaPnl.add(sEncrypted);
        twoAreasPnl.add(firsttxtAreaPnl);
        twoAreasPnl.add(secondtxtAreaPnl);

        mainPnl.add(headingPnl, BorderLayout.NORTH);
        mainPnl.add(twoAreasPnl, BorderLayout.CENTER);
        add(mainPnl);

        setDefaultCloseOperation(EXIT_ON_CLOSE);
        pack();
        setVisible(true);

        openFile.addActionListener((o) -> openFile());
        encrypt.addActionListener((e) -> encryptMessage());
        save.addActionListener((s) -> saveEncryptedMessage());
        clear.addActionListener((e) -> clear());
        exit.addActionListener((d) -> {
            dispose();
        });

    }

    private void openFile() {
        JFileChooser chooser = new JFileChooser();
        int res = chooser.showOpenDialog(this);

        if (res == JFileChooser.APPROVE_OPTION) {
            File selFile = chooser.getSelectedFile();
            plainArea.setText("");

            try {
                BufferedReader br = new BufferedReader(new FileReader(selFile));
                String line;

                while ((line = br.readLine()) != null) {
                    plainArea.append(line + "\n");

                }
                br.close();
            } catch (FileNotFoundException ex) {
                ex.printStackTrace();
            } catch (IOException ex) {
                ex.printStackTrace();
            }

        }

    }

    private void encryptMessage() {
        String plainTxt = plainArea.getText().trim();
        int shift = 3;

        Message me = new Message(shift);
        String encrypted = me.encrypt(plainTxt);
        encryptedArea.setText(encrypted);

    }

    private void saveEncryptedMessage() {
        JFileChooser ch = new JFileChooser();
        int res = ch.showSaveDialog(this);
        if (res == JFileChooser.APPROVE_OPTION) {
            File saveFile = ch.getSelectedFile();

            try {
                FileWriter fl = new FileWriter(saveFile);
                fl.write(encryptedArea.getText());
                fl.close();
            } catch (IOException ex) {
            }
        }
    }

    private void clear() {
        plainArea.setText("");
        encryptedArea.setText("");
    }

}
