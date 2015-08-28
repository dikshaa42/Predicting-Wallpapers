import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;


public class UserVector {

	
	
	
	static float featurevector[][]=new float[70][25];
	static float userratings[][]= new float[40][70];
	static float uservector[][]=new float[40][25];
	
	static BufferedReader filereader = null;
	
	static int no_of_users=0;
	static int no_of_features;
	
	public static void getfiles(String filename)
	{
		try {
			filereader = new BufferedReader(new FileReader(filename));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
	public static void creatematrix()
	{
		getfiles("dataset.csv");
		String line ="";
		
		int i=0;
		int j=1;
		int row=0;
		try {
			
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
			no_of_users=j-1;
			
		getfiles("featurevector.csv");
		line = "";
		
	    i=1;
		j=0;
		row=1;
		
			
			while((line = filereader.readLine())!=null)
			{
				
				String[] tokens = line.split(",");
				int length = tokens.length;
				no_of_features=length-1;
				for(j=1;j<length;j++)
				{
					//System.out.println(tokens[0]+ " " + tokens[1]);
					 float value = Float.parseFloat(tokens[j]);
					 featurevector[i][j]= value;
				}
				i++;
				

			}
			System.out.println(no_of_users + " " + no_of_features);
			
		}
		 catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void createuservector()
	{
		creatematrix();
		
		float absratingvector[][]= new float[40][25];
		for(int i =1;i<=no_of_users;i++)
		{
			
			for(int j=1;j<=21;j++)
			{
				float sum=0;
				float abssum=0;
				for(int k=1;k<=63;k++)
				{
					
					sum = sum+ (userratings[i][k]*featurevector[k][j]);
					abssum = abssum + Math.abs(userratings[i][k]*featurevector[k][j]);
				}
				
				uservector[i][j]=sum;
				absratingvector[i][j]=abssum;
			}
			
		}
		
		for(int i = 1; i<=no_of_users;i++)
	       {
	    	   for(int j=1;j<=21;j++)
	    	   {
	    		   if(absratingvector[i][j]!= 0)
	    		   uservector[i][j] = uservector[i][j]/absratingvector[i][j];
	    		   //System.out.print(uservector[i][j] + " ");
	    	
	    	   }
	    	   //System.out.println();
	       }
		/*System.out.println("ratings ");
		for(int i=1;i<=no_of_users;i++)
		{
			for(int j=1;j<=63;j++)
			{
				System.out.print(userratings[i][j]+ " ");
			}
			System.out.print("\n");
		}
		System.out.println("feature ");
		for(int i=1;i<=63;i++)
		{
			for(int j=1;j<=no_of_features;j++)
			{
				System.out.print(featurevector[i][j]+ " ");
			}
			System.out.print("\n");
		}
		System.out.println("uservector ");
		for(int i=1;i<=no_of_users;i++)
			{//System.out.print(i+ "    ");
			for(int j=1;j<=21;j++)
			{ 
				if(i==4||i==5)
				System.out.print(uservector[i][j]+ " ");
			}
			System.out.print("\n");
			}*/
	}
	
	public static void main(String args[])
	{
		UserVector object = new UserVector();
		object.createuservector();
		kmeansclustering newobject = new kmeansclustering();
		try {
			newobject.kmeans(uservector,no_of_users);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
