import java.awt.*;
import java.awt.event.*;

import javax.swing.*;


public class firstscreen extends JFrame implements ActionListener{
  
	
	JLabel label;
	JTextField txt;
	JButton button;
	public void firstscreen()
	{
		setSize(500, 500);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    
	    setLayout( new FlowLayout() );     
	    label = new JLabel("Name: ");
	    label.setSize(50, 5);
	    add(label);
	    
	    txt = new JTextField(10);
	    add(txt);
	    
	    button = new JButton("Next");
	    button.addActionListener(this);
	    add(button);
	    setVisible(true);
		
	}
	
	public void actionPerformed(ActionEvent e)
	{
		String name = txt.getText();
		remove(label);
  		remove(txt);
  		remove(button);
	}
	
	public static void main(String args[])
	{
		new firstscreen();
		
	}
	
	
}
