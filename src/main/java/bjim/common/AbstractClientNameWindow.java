package bjim.common;


import bjim.client.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class AbstractClientNameWindow extends JFrame implements ActionListener{
        Container container=getContentPane();
        JLabel userLabel=new JLabel("Client Name");

        JTextField userTextField=new JTextField();

        JButton loginButton=new JButton("Connect");


public AbstractClientNameWindow()
        {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
        }

    public AbstractClientNameWindow(String username) {
        setLayoutManager();
        setLocationAndSize();
        addComponentsToContainer();
        addActionEvent();
    }


    public void setLayoutManager(){
        container.setLayout(null);
        }

public void setLocationAndSize(){
        userLabel.setBounds(50,150,100,30);

        userTextField.setBounds(150,150,150,30);

        loginButton.setBounds(50,300,100,30);



        }

public void addComponentsToContainer(){
        container.add(userLabel);

        container.add(userTextField);


        container.add(loginButton);

        }

public void addActionEvent(){
        loginButton.addActionListener(this);

        }




        String userText=null;
public void actionPerformed(ActionEvent e){
        //Coding Part of LOGIN button
        if(e.getSource()==loginButton){

        String pwdText;
        userText=userTextField.getText();

        System.out.println(userText);


        }
        }


public String getkey()
        {
        userText=userTextField.getText();
        return userText;
        }

}
