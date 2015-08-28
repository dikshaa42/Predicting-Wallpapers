import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class kmeansclustering {

	
	 
	public void kmeans(float uservector[][],int no_of_users) throws ClassNotFoundException
	{
		
		float centroids[][] = new float[6][22];
		
		for(int j=1;j<=21;j++)
			centroids[1][j]=uservector[30][j];
		
		for(int j=1;j<=21;j++)
			centroids[2][j]=uservector[33][j];
		
		for(int j=1;j<=21;j++)
			centroids[3][j]=uservector[34][j];
		
		for(int j=1;j<=21;j++)
			centroids[4][j]=uservector[9][j];
		
		for(int j=1;j<=21;j++)
			centroids[5][j]=uservector[29][j];
		
		
		int centroidusers[][] = new int[50][50];
		
		int centroidindex[] = new int[6];
		for(int l=1;l<=5;l++)
		{
			centroidindex[l]=1;
		}
		
		
		float sum=0;
		float minsum=10000000;
		int closestcentroid=1;
		
		for(int i=1;i<=no_of_users;i++)
		{
			minsum=10000000;
			for(int j=1;j<=5;j++)
			{
				sum=0;
				
				for(int k=1;k<=21;k++)
				{
					sum = sum + Math.abs(centroids[j][k]-uservector[i][k]);
					
				}
				
				if(sum<minsum)
				{
					minsum=sum;
					closestcentroid=j;
				}
				
			}
			centroidusers[closestcentroid][centroidindex[closestcentroid]++]=i;
			
		}
		
		/*for(int i=1;i<=5;i++)
		{
			for(int j=1;j<=centroidindex[i];j++)
			{
				System.out.print(centroidusers[i][j] + " ");
			}
			System.out.println();
		}*/
		
		
		sum =0;
		for(int k=1;k<=5;k++)
		{
		for(int i=1;i<=21;i++)
		{
			sum=0;
			for(int j=1;j<centroidindex[k];j++)
			{
				int user = centroidusers[k][j];
				sum = sum + uservector[user][i];
			}
			centroids[k][i]= sum/(centroidindex[k]-1);
			
		}
		
		}
	
		
		
		int tmpcentroidusers[][] = new int[50][50];
		
		int flag=0;
		
		int centroidindex2[] = new int[6];
		
		while(flag!=1)
		{
			
			for(int i=1;i<=5;i++)
			{
				for(int j=1;j<centroidindex[i];j++)
				{
					System.out.print(centroidusers[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			
			for(int l=1;l<=5;l++)
			{
				centroidindex2[l]=1;
			}
			
			
			sum=0;
			 minsum=10000000;
			closestcentroid=1;
			
			for(int i=1;i<=no_of_users;i++)
			{
				minsum=10000000;
				for(int j=1;j<=5;j++)
				{
					sum=0;
					
					for(int k=1;k<=21;k++)
					{
						sum = sum + Math.abs(centroids[j][k]-uservector[i][k]);
						
					}
					if(sum<minsum)
					{
						minsum=sum;
						closestcentroid=j;
					}
					
				}
				tmpcentroidusers[closestcentroid][centroidindex2[closestcentroid]++]=i;
				
			}
			
			for(int i=1;i<=5;i++)
			{
				for(int j=1;j<centroidindex2[i];j++)
				{
					System.out.print(tmpcentroidusers[i][j] + " ");
				}
				System.out.println();
			}
			System.out.println();
			
			 flag = compare(centroidusers,centroidindex,centroidindex2,tmpcentroidusers);
			 
			 for(int i=1;i<=5;i++)
			 {
				 for(int j=1;j<centroidindex2[i];j++)
				 {
					 centroidusers[i][j]=tmpcentroidusers[i][j];
				 }
				 
			 }
			 
			 for(int i=1;i<=5;i++)
			 {
				 centroidindex[i]=centroidindex2[i];
				 
			 }
			 
			 for(int k=1;k<=5;k++)
				{
				for(int i=1;i<=21;i++)
				{
					sum=0;
					for(int j=1;j<centroidindex[k];j++)
					{
						int user = centroidusers[k][j];
						sum = sum + uservector[user][i];
					}
					centroids[k][i]= sum/(centroidindex[k]-1);
					
				}
				
				}
		
		}
		
		for(int k=1;k<=5;k++)
		{
			centroidusers[k][0]=centroidindex[k]-1;
		}
		
		
		
		 ObjectOutputStream oos;
		try {
			int tmp[][] = new int[50][50];
			oos = new ObjectOutputStream(new FileOutputStream("centroidusers.DATA"));
			 oos.writeObject(centroidusers);
			 oos = new ObjectOutputStream(new FileOutputStream("centroids.DATA"));
			 oos.writeObject(centroids);
			 oos.close();
			 
			 ObjectInputStream ois = new ObjectInputStream(new FileInputStream(new File ("centroidusers.DATA")));
			   tmp = (int[][])ois.readObject();
			 
			   
			   
			   
			
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
		
		
	}
	
	int compare(int centroiduser[][],int n1[],int n2[],int tmpcentroiduser[][])
	{
		int i=1,j=1;
		for(;i<=5;i++)
		{
			j=n1[i];
			int k=n2[i];
			if(j!=k)
				return 0;
			for(j=1;j<k;j++)
			{
			  if(centroiduser[i][j]!=tmpcentroiduser[i][j])
				  return 0;
			  j++;
				
			}
		}
		
		
		return 1;
		
	}
	
}
