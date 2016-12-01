package com.sinodata.gui;

import java.awt.GridLayout;
import java.awt.TextField;
import java.util.ArrayList;

import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import net.sf.json.JSONObject;

public class PanelTwo extends JPanel{
    /**
     * 
     */
    private static final long serialVersionUID = 1L;
    JLabel label1,label2;
    JTextField tf1,tf2;
    JRadioButton rb1,rb2;
    ButtonGroup group;
    static int count = 10;
    ArrayList<TextField> al1;
    ArrayList<TextField> al2;

    public PanelTwo(){
        label1 = new JLabel("接口请求URL地址");
        label2 = new JLabel("agentid");
        tf1 = new JTextField("/access/api/agent/login.action");
        tf2 = new JTextField("00001");
        rb1 = new JRadioButton("使用公共密钥");
        rb2 = new JRadioButton("使用商家密钥");
        group = new ButtonGroup();
        group.add(rb1);
        group.add(rb2);
        rb1.setSelected(true);

        al1 = new ArrayList<TextField>();
        al2 = new ArrayList<TextField>();
        for(int i=0;i<count;i++){
            al1.add(new TextField());
            al2.add(new TextField());
        }
        lanchPanel();
    }

    private void lanchPanel() {
        add(rb1);
        add(rb2);
        add(label1);
        add(tf1);
        add(label2);
        add(tf2);

        for(int i =0;i<count;i++){
            add(al1.get(i));
            add(al2.get(i));
        }
        setLayout(new GridLayout(count +3,2));
//        setBounds(0,100,200,400);
        validate();

    }
    
    String getArrayListTextFieldToString(){
    	JSONObject jo = new JSONObject();
    	for (int i =0;i<count;i++){
    		String key = al1.get(i).getText();
    		String value = al2.get(i).getText();
    		if(!"".equals(key)){
    			jo.put(key,value);
    		}
    	}
    	return jo.toString();
    }
    
    void setArrayListTextFieldToNull(){
    	for (int i =0;i<count;i++){
    		al1.get(i).setText(null);
    		al2.get(i).setText(null);
    	}
    }

}
