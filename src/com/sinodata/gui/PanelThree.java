package com.sinodata.gui;

import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class PanelThree extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JLabel label1,label2,label3,label4;
    JTextArea tf1,tf2,tf3,tf4;

    public PanelThree(){
        label1 = new JLabel("请求加密前，paramcontent=");
        label2 = new JLabel("请求加密后，paramcontent=");
        label3 = new JLabel("响应解密前：");
        label4 = new JLabel("响应解密后：");
        tf1 = new JTextArea();
        tf2 = new JTextArea();
        tf3 = new JTextArea();
        tf4 = new JTextArea();

        lanchPanel();
    }

    private void lanchPanel() {
        add(label1);
        add(new JScrollPane(tf1));//加入滚动条功能
        add(label2);
        add(new JScrollPane(tf2));
        add(label3);
        add(new JScrollPane(tf3));
        add(label4);
        add(new JScrollPane(tf4));
        tf1.setLineWrap(true);//设置自动换行
        tf2.setLineWrap(true);
        tf3.setLineWrap(true);
        tf4.setLineWrap(true);
        setLayout(new GridLayout(8,1));
//        setBounds(0,0,200,100);
        validate();
    }


}
