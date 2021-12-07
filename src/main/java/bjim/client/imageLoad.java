package bjim.client;

import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.Frame;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
class imageLoad extends Canvas
{

    Image img;
    public imageLoad(Image img)
    {
        this.img = img;
    }

    public void paint(Graphics g)
    {
        if (img != null)
        {
            g.drawImage(img,250,20, this);
        }
    }

    public void setImage(Image img)
    {
        this.img = img;
    }

}







