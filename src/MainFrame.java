import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

 class imagerating{
	public int rating;
	public int image_no;
}

public class MainFrame extends JFrame implements ActionListener{
	
	 imagerating[] array = new imagerating[64];
	 
	int[][] centroidusers = new int[50][50];
	float[][] centroids = new float[6][22];
	float featurevector[][]=new float[70][25];
	float userratings[][]= new float[40][70];
	
	int flag=0;
	
	static BufferedReader filereader = null;
	
	//frame
	JLabel label,imagelabel;
	JTextField txt;
	JButton button;
	ImageIcon icon;
	JRadioButton yesbutton,nobutton,maybebutton;
	static FileWriter filewriter;
	
	int image_no=1;
	
	int n=8;
	
	int user[] = new int[8];
	float uservector[] = new float[22];
	float absratingvector[] = new float[22];
	
	int no=8;
	
	public void frame()
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
	
	public void bestimage() throws FileNotFoundException, IOException, ClassNotFoundException
	{
		String line ="";
		
		ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File ("centroidusers.DATA")));
		   centroidusers = (int[][])ois.readObject();
		   ois = new ObjectInputStream(new FileInputStream(new File ("centroids.DATA")));
		   centroids = (float[][])ois.readObject();
		   
		   
		   filereader = new BufferedReader(new FileReader("featurevector.csv"));
		   
		   int i=0;
		   int j;
		   
		   while((line = filereader.readLine())!=null)
			{
				
				String[] tokens = line.split(",");
				int length = tokens.length;
				
				for(j=1;j<length;j++)
				{
					//System.out.println(tokens[0]+ " " + tokens[1]);
					 float value = Float.parseFloat(tokens[j]);
					 featurevector[i][j]= value;
				}
				i++;
				

			}
		   
		    line ="";
		    filereader = new BufferedReader(new FileReader("dataset.csv"));
			
			 i=0;
			 j=1;
			int row=0;
		
				
				while((line = filereader.readLine())!=null)
				{
					
					String[] tokens = line.split(",");
					if(tokens.length>1)
					{
						//System.out.println(tokens[0]+ " " + tokens[1]);
						 row = Integer.parseInt(tokens[0]);
						int rating = Integer.parseInt(tokens[1]);
						userratings[j][row]= rating;
						
						
						if(row==63)
							j++;
					}
					
				}
		   
		   for(j=1;j<=21;j++)
			{
				float sum=0;
				float abssum=0;
				for(int k=1;k<=7;k++)
				{
					
					sum = sum+ (user[k]*featurevector[k][j]);
					abssum = abssum + Math.abs(user[k]*featurevector[k][j]);
				}
				
				uservector[j]=sum;
				absratingvector[j]=abssum;
			}
		   
		   for( j=1;j<=21;j++)
    	   {
    		   if(absratingvector[j]!= 0)
    		   uservector[j] = uservector[j]/absratingvector[j];
    		   //System.out.print(uservector[j] + " ");
    	
    	   }
		   
		   float sum=0;
			float minsum=10000000;
			int closestcentroid=1;
			
		   for(j=1;j<=5;j++)
			{
				sum=0;
				
				for(int k=1;k<=21;k++)
				{
					sum = sum + Math.abs(centroids[j][k]-uservector[k]);
					
				}
				
				if(sum<minsum)
				{
					minsum=sum;
					closestcentroid=j;
				}
				
			}
		   
		   
		   
		   for(int l=0;l<=63;l++)
		   {
			   array[l]=new imagerating();
		   }
		   
		   for(int l=n;l<=63;l++)
		   {
			   array[l].image_no=l;
			   array[l].rating=0;
			  
		   }
		   
		   System.out.println("Closestcentroid & centroid users " + closestcentroid);
		   
		   for(i=1;i<=5;i++)
			{
			   System.out.print(centroidusers[i][0] + " ");
				for(j=1;j<=centroidusers[i][0];j++)
				{
					System.out.print(centroidusers[i][j] + " ");
				}
				System.out.println();
			}
		   
		   j=closestcentroid;
		   int k=1;
		   int u;
		   
		   
		   
		   
		  for(int nn = centroidusers[j][0];nn>0;nn--){
			   
			   u = centroidusers[j][k];
			   
			   System.out.println("user = "+ u);
			   
			   for(int l=n;l<=63;l++)
			   {
				   //System.out.println("userrating " + userratings[u][l]);
				   array[l].rating+= userratings[u][l];
			   }
			   k++;
		   }
		   
		   System.out.println("Before sorting");
		   for(int l=n;l<=63;l++)
		   {
			   System.out.print(array[l].image_no + " ");
			   System.out.println(array[l].rating);
			  
		   }
		   
		   
		   imagerating tmp = new imagerating();
		   
		   for(int start = n;start<63;start++ )
		   {
			   for(int ss= start+1;ss<=63;ss++)
			   {
				   if(array[start].rating<array[ss].rating)
				   {
					   tmp.rating = array[start].rating;
					   tmp.image_no = array[start].image_no;
					   
					   array[start].rating = array[ss].rating;
					   array[start].image_no= array[ss].image_no;
					   
					   array[ss].rating = tmp.rating;
					   array[ss].image_no= tmp.image_no;
				   }
				   
			   }
		   }
		   
		   System.out.println("after sorting");
		   for(int l=n;l<=63;l++)
		   {
			   System.out.print(array[l].image_no + " ");
			   System.out.println(array[l].rating);
			  
		   }
		   
		   
		 
		   //System.out.println("from file " + "\n" +tmpcen[1][1] + " " +tmpcen[1][2] + " " +tmpcen[1][3] + " "+ tmpcen[1][4] + " " + tmpcen[2][1] + " "+tmpcen[2][2] + " "+tmpcen[2][3] + " ");
		
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(image_no<=7)
		{
			if("1".equals(e.getActionCommand())){
				user[image_no]= 1;
			}
				
			else if("-1".equals(e.getActionCommand())){
				user[image_no]= -1;
			}
			else if("0".equals(e.getActionCommand())){
				user[image_no]= 0;
			}
			image_no++;
		}
		if(image_no>7)
		{
			try {
				if(flag==0)
				{
					bestimage();
					flag=1;
				}
				int imageno = array[n].image_no;
				n++;
				image_no++;
				icon = new ImageIcon("image"+imageno+".jpg");
				imagelabel.setIcon(icon);
				
				
				
				
				
				
				
				
				
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (ClassNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			
		}
		else
		{
			icon = new ImageIcon("image"+image_no+".jpg");
			imagelabel.setIcon(icon);
		}
		
	}
	
	
	
	public static void main(String args[]) throws FileNotFoundException, ClassNotFoundException, IOException
	{
		MainFrame object = new MainFrame();
		object.frame();
		
	}
	
	
	
}
