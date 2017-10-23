package com.sabel;

import javax.print.DocFlavor;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;

public class Bildbetrachter extends JFrame {

    private JPanel jpWest, jpSouth;
    private JButton jbVorBild, jbNachBild;
    private JLabel jLabel;
    private ButtonGroup bg;
    private JRadioButton[] jradios;
    private Icon[] icons;
    private int index;
    private JScrollPane jsScroll;


    public Bildbetrachter(){
        super("Bilder");
        this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        this.initComponents();
        this.setSize(400, 400);
        this.initEvent();
        this.setVisible(true);

    }

    private void initComponents(){
        jpWest = new JPanel();
        bg = new ButtonGroup();
        jpWest.setLayout(new BoxLayout(jpWest, BoxLayout.Y_AXIS));
        jradios = new JRadioButton[4];
        for(int i = 0; i < 4; i++){
            jradios[i]= new JRadioButton("Bild" + (i+1));
            jpWest.add(jradios[i]);
            bg.add(jradios[i]);
        }
        jradios[0].setSelected(true);



        jpSouth = new JPanel();
        jbVorBild = new JButton("Vorheriges Bild");
        jbNachBild = new JButton("Nächstes Bild");
        jpSouth.add(jbNachBild);
        jpSouth.add(jbVorBild);



        icons = new Icon[4];
        for(int i = 0; i < 4; i++){
            icons[i]= new ImageIcon(getClass().getResource("/Bild"+(i+1)+".jpg"));
            jLabel = new JLabel(icons[0]);
        }

        jsScroll = new JScrollPane(jLabel);



        this.add(jsScroll, BorderLayout.CENTER);
        this.add(jpSouth, BorderLayout.SOUTH);
        this.add(jpWest, BorderLayout.WEST);


    }

    private void initEvent(){

        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if(JOptionPane.showConfirmDialog(Bildbetrachter.this,
                        "Möchten Sie das Programm jetzt beenden?",
                        "Beenden bestätigen",
                        JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION) {
                    System.exit(0); //Aktion(en) bei Klicken auf den "Ja-Button"
                }
            }
        });

        jbVorBild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                index++;
                if(index ==4){
                    index = 0;
                }
                aktualisiereFenster();

            }
        });

        jbNachBild.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int neuerIndex = index - 1;
                if(neuerIndex < 0){
                    neuerIndex = icons.length -1;
                }
                index = neuerIndex;
                aktualisiereFenster();

            }
        });

        for(int i = 0; i < jradios.length; i++){
            final int j = i;
            jradios[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    index = j;
                    aktualisiereFenster();
                }
            });
        }


    }

    private void aktualisiereFenster() {
        jLabel.setIcon(icons[index]);
        jradios[index].setSelected(true);
    }




    public static void main(String[] args){
        new Bildbetrachter();
    }


}
