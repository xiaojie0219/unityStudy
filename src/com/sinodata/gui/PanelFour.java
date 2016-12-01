package com.sinodata.gui;


import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

public class PanelFour extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JButton bt1,bt2,bt3;
    public PanelFour(){
        bt1 = new JButton("发送请求");
        bt2 = new JButton("重置参数");
        bt3 = new JButton("备用的");
        lanchPanel();
    }

    private void lanchPanel() {
        add(bt1);
        add(bt2);
        add(bt3);
        
        setLayout(new FlowLayout());
        validate();
        setBounds(0,0,100,30);
    }


}
