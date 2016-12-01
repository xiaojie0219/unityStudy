package com.sinodata.gui;


import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class PanelOne extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JLabel label1,label2;
    JTextField tf1,tf2;

    public PanelOne(){
        label1 = new JLabel("公共密钥");
        label2 = new JLabel("商家密钥");

        tf1 = new JTextField("C3F1E6B25312648DDF122DB8");
        tf2 = new JTextField("81AE3BD1FE8D93D6B1E1D624");

        lanchPanel();
    }

    private void lanchPanel() {
        add(label1);
        add(tf1);
        add(label2);
        add(tf2);
        setLayout(new GridLayout(2,2));
        validate();
        setBounds(0,0,100,10);
    }

}
