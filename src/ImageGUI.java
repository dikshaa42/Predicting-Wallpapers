import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;


public class ImageGUI extends JFrame implements ActionListener {
     
	JLabel label,imagelabel;
	JTextField txt;
	JButton button;
	ImageIcon icon;
	JRadioButton yesbutton,nobutton,maybebutton;
	static FileWriter filewriter;
	int image_no=1;
	/**
	 * 
	 */
	public void Mainframe()
	{
		setSize(1000, 1000);
		setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
	    
	    setLayout( new FlowLayout() );
	    
	    
         
		icon = new ImageIcon("image"+image_no+".jpg");
		
		imagelabel= new JLabel(icon);
		add(imagelabel);
		
		yesbutton = new JRadioButton("Yes");
		yesbutton.setActionCommand("1");
		
		nobutton = new JRadioButton("No");
		nobutton.setActionCommand("-1");
		
		maybebutton = new JRadioButton("Maybe");
		maybebutton.setActionCommand("0");
		
		ButtonGroup group = new ButtonGroup();
		group.add(yesbutton);
		group.add(nobutton);
		group.add(maybebutton);
		
		yesbutton.addActionListener(this);
		nobutton.addActionListener(this);
		maybebutton.addActionListener(this);
		
		add(yesbutton);
		add(nobutton);
		add(maybebutton);
	
		setVisible(true);
		
	}
	
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
	    button.setActionCommand("done");
	    button.addActionListener(this);
	    add(button);
	    setVisible(true);
		
	}
	
	
	public static void writecsvfile(String insert)
	{
		try {
			filewriter.append(insert);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
   
	
	public static void main(String args[])
	{
		ImageGUI object = new ImageGUI();
		object.firstscreen();


	}


	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 if ("done".equals(e.getActionCommand()))
		{
			String name = txt.getText();
			remove(label);
	  		remove(txt);
	  		remove(button);
	  		try {
				filewriter=new FileWriter("dataset.csv");
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
	  		writecsvfile(name);
			writecsvfile(",");
			
	  		
	  		ImageGUI object = new ImageGUI();
			object.Mainframe();
		}
		else
		{
			if("1".equals(e.getActionCommand())){
				writecsvfile("1");
			}
				
			else if("-1".equals(e.getActionCommand())){
				writecsvfile("-1");
			}
			else if("0".equals(e.getActionCommand())){
				writecsvfile("0");
			}
			
			if(image_no!=64)
			{
				writecsvfile(",");
			}
			else
				writecsvfile("\n");
			
		image_no++;
		System.out.println(" "+ image_no);
		if(image_no==65)
		{
			//yesbutton.removeAll();
			//nobutton.removeAll();
			//maybebutton.removeAll();
			remove(yesbutton);
			remove(nobutton);
			remove(maybebutton);
			
			imagelabel.setIcon(null);
			imagelabel.setText("Thanks For your valuable time !");
			
			//revalidate();
            //repaint();
            
			System.out.println(" dgdgfg");
			 try {
				 
				                 filewriter.flush();
				 
				                 filewriter.close();
				 
				           } catch (IOException e1) {
				 
				                 System.out.println("Error while flushing/closing fileWriter !!!");
				 
				                 e1.printStackTrace();
				 
				             }
			
		}
		else{
			
		
		icon = new ImageIcon("image"+image_no+".jpg");
		imagelabel.setIcon(icon);
		}
		}
	
	}
	
	
	
	
}
