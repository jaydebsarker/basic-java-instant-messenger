package bjim.client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class profilePictureWindow implements ActionListener {
    JFrame profileprofileFrameame = new JFrame("Set Profile Picture");
    Image Image1;
    imageLoad Canvas1;
    FileDialog fd = new FileDialog(profileprofileFrameame , "Open", FileDialog.LOAD);
    Label Label1 = new Label("Select Your Photo");

    Button PofilePicButton = new Button("Upload");

    public profilePictureWindow() {
        profileprofileFrameame.setSize(500, 500);
        profileprofileFrameame.setLocation(200, 200);
        profileprofileFrameame.setBackground(Color.lightGray);
        profileprofileFrameame.setLayout(new FlowLayout());
        profileprofileFrameame.add(Label1);
        profileprofileFrameame.add(PofilePicButton);
        profileprofileFrameame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        PofilePicButton.addActionListener(this);
        Canvas1 = new imageLoad(null);
        Canvas1.setSize(1000, 1000);
        profileprofileFrameame.add(Canvas1);
        profileprofileFrameame.show();

    }

    void imageload() {
        fd.show();
        if (fd.getFile() == null) {
            Label1.setText("You have not select");
        } else {
            String d = (fd.getDirectory() + fd.getFile());
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            Image1 = toolkit.getImage(d);
            Canvas1.setImage(Image1);
            Canvas1.repaint();
        }
    }
    public void actionPerformed(ActionEvent event)
    {
        Button b = (Button) event.getSource();
        if (b == PofilePicButton)
        {
            imageload();
        }

    }

public  static void main(String args[])
{
    profilePictureWindow pc= new profilePictureWindow();
}



}
