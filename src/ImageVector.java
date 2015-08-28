import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import javax.imageio.ImageIO;


public class ImageVector {
     int i,j;
     int subject=0;
     float imagevector[][]=new float[100][100];
     int height;
     int width;
     BufferedImage image;
     
     float storeh[][] = new float[670][670];
     float stores[][] = new float[670][670];
     float storev[][] = new float[670][670];
     
     float averageh;
     float averages;
     float averagev;
     
     float hsv[] = new float[3];
     float hsv1[] = new float[3];
     float hsv2[] = new float[3];
     float hsv3[] = new float[3];
     float hsv4[] = new float[3];
     float hsv5[] = new float[3];
     float hsv6[] = new float[3];
     float hsv7[] = new float[3];
     float hsv8[] = new float[3];
     
     
     
     public void getimagevector()
     {
    	 for(i=0;i<70;i++)
    		 for(j=0;j<40;j++)
    			 imagevector[i][j]=0;
    	 int k;
    	 for( k=1;k<64;k++)
    	 {
    		 try {
				image = ImageIO.read(new File("image"+k+".jpg"));
				
				height = image.getHeight();
	        	 width = image.getWidth();
	        	 imagevector[k][(k-1)%7]=1;
	        	 for(i=0;i<height;i++)
	        	 {
	        		 for(j=0;j<width;j++)
	        		 {
	        			 Color c = new Color(image.getRGB(j, i));
	        			 
	        			 float hsv[] = c.RGBtoHSB(c.getRed(), c.getBlue(), c.getGreen(), null);
	        			 //System.out.println("hue = "+ hsv[0] + "saturation = "+ hsv[1] + " value = "+ hsv[2]);
	        			
	        			 
	        			 if(hsv[0]<=0.2)
	        				 imagevector[k][10]++;
	        			 else if(hsv[0]<=0.4)
	        				 imagevector[k][11]++;
	        			 else if(hsv[0]<=0.6)
	        				 imagevector[k][12]++;
	        			 else if(hsv[0]<=0.8)
	        				 imagevector[k][13]++;
	        			 else
	        				 imagevector[k][14]++;
	        			 
	        			 if(hsv[1]<=0.33)
	        				 imagevector[k][15]++;
	        			 else if(hsv[1]<=0.66)
	        				 imagevector[k][16]++;
	        			 else
	        				 imagevector[k][17]++;
	        			 
	        			 if(hsv[2]<=0.33)
	        				 imagevector[k][18]++;
	        			 else if(hsv[2]<=0.66)
	        				 imagevector[k][19]++;
	        			 else
	        				 imagevector[k][20]++;
	        			 
	        			 
	        			 storeh[i][j]=hsv[0];
	        			 stores[i][j]=hsv[1];
	        			 storev[i][j]=hsv[2];
	        		 }
	        		 
	        		 
	        		 
	        	 }
	        	 float count = height*width;
	        	 
	        	 for(i=10;i<21;i++)
	        		 imagevector[k][i]=imagevector[k][i]/count;
	        	 
	        	 averageh=0;
	        	 averages=0;
	        	 averagev=0;
	        	 
	        	 for(i=0;i<height;i++)
	        	 {
	        		 for(j=0;j<width;j++)
	        		 {
	        			 if(i==0&&j==0)
	            		 {
	            			 hsv[0]=storeh[i][j];
	                		 //hsv1[0]=0;
	                		 hsv2[0]=storeh[i+1][j+1];
	                		 hsv3[0]=storeh[i][j+1];
	                		 hsv4[0]=storeh[i+1][j];
	                		 //hsv5[0]=0;
	                		 //hsv6[0]=0;
	                		 //hsv7[0]=0;
	                		 //hsv8[0]=0;
	                		 
	                		 hsv[1]=stores[i][j];
	                		 //hsv1[1]=0;
	                		 hsv2[1]=stores[i+1][j+1];
	                		 hsv3[1]=stores[i][j+1];
	                		 hsv4[1]=stores[i+1][j];
	                		// hsv5[1]=0;
	                		 //hsv6[1]=0;
	                		// hsv7[1]=0;
	                		 //hsv8[1]=0;
	                		 
	                		 
	                		 hsv[2]=storev[i][j];
	                		 //hsv1[2]=0;
	                		 hsv2[2]=storev[i+1][j+1];
	                		 hsv3[2]=storev[i][j+1];
	                		 hsv4[2]=storev[i+1][j];
	                		// hsv5[2]=0;
	                		// hsv6[2]=0;
	                		// hsv7[2]=0;
	                		 //hsv8[2]=0;
	                		 averageh+=(float) ((Math.abs(hsv[0]-hsv3[0])+Math.abs(hsv4[0]-hsv2[0])+Math.abs(hsv4[0]-hsv[0])+Math.abs(hsv2[0]-hsv3[0]))*0.25);
	                         averages+= (float) ((Math.abs(hsv[1]-hsv3[1])+Math.abs(hsv4[1]-hsv2[1])+Math.abs(hsv4[1]-hsv[1])+Math.abs(hsv2[1]-hsv3[1]))*0.25);
	                         averagev+= (float) ((Math.abs(hsv[2]-hsv3[2])+Math.abs(hsv4[2]-hsv2[2])+Math.abs(hsv4[2]-hsv[2])+Math.abs(hsv2[2]-hsv3[2]))*0.25);
	            		 }
	            		 else if(i==0&&j>0&&j!=(width-1))
	            			 {
	            				 hsv[0]=storeh[i][j];
	                    		 //hsv1[0]=0;
	                    		 hsv2[0]=storeh[i+1][j+1];
	                    		 hsv3[0]=storeh[i][j+1];
	                    		 hsv4[0]=storeh[i+1][j];
	                    		 //hsv5[0]=0;
	                    		 hsv6[0]=storeh[i][j-1];
	                    		 //hsv7[0]=0;
	                    		 hsv8[0]=storeh[i+1][j-1];
	                    		 
	                    		 
	                    		 
	                    		 hsv[1]=stores[i][j];
	                    		 //hsv1[1]=0;
	                    		 hsv2[1]=stores[i+1][j+1];
	                    		 hsv3[1]=stores[i][j+1];
	                    		 hsv4[1]=stores[i+1][j];
	                    		 //hsv5[1]=0;
	                    		 hsv6[1]=stores[i][j-1];
	                    		 //hsv7[1]=0;
	                    		 hsv8[1]=stores[i+1][j-1];
	                    		 
	                    		 
	                    		 hsv[2]=storev[i][j];
	                    		 //hsv1[2]=0;
	                    		 hsv2[2]=storev[i+1][j+1];
	                    		 hsv3[2]=storev[i][j+1];
	                    		 hsv4[2]=storev[i+1][j];
	                    		 //hsv5[2]=0;
	                    		 hsv6[2]=storev[i][j-1];
	                    		 //hsv7[2]=0;
	                    		 hsv8[2]=storev[i+1][j-1];
	                    		 averageh+=(float) (((Math.abs(hsv[0]-hsv6[0])+Math.abs(hsv[0]-hsv3[0])+Math.abs(hsv4[0]-hsv8[0])+Math.abs(hsv4[0]-hsv2[0])+Math.abs(hsv6[0]-hsv8[0])+Math.abs(hsv4[0]-hsv[0])+Math.abs(hsv2[0]-hsv3[0])))*0.14286);
	    	                     averages+=(float) ((Math.abs(hsv[1]-hsv6[1])+Math.abs(hsv[1]-hsv3[1])+Math.abs(hsv4[1]-hsv8[1])+Math.abs(hsv4[1]-hsv2[1])+Math.abs(hsv6[1]-hsv8[1])+Math.abs(hsv4[1]-hsv[1])+Math.abs(hsv2[1]-hsv3[1]))*0.14286);
	    	                     averagev+=(float) ((Math.abs(hsv[2]-hsv6[2])+Math.abs(hsv[2]-hsv3[2])+Math.abs(hsv4[2]-hsv8[2])+Math.abs(hsv4[2]-hsv2[2])+Math.abs(hsv6[2]-hsv8[2])+Math.abs(hsv4[2]-hsv[2])+Math.abs(hsv2[2]-hsv3[2]))*0.14286);
	            			 }
	            			 else if(j==0&&i>0&&i!=(height-1))
	            				 {
	            					 hsv[0]=storeh[i][j];
	            	        		 //hsv1[0]=0;
	            	        		 hsv2[0]=storeh[i+1][j+1];
	            	        		 hsv3[0]=storeh[i][j+1];
	            	        		 hsv4[0]=storeh[i+1][j];
	            	        		 hsv5[0]=storeh[i-1][j];
	            	        		// hsv6[0]=0;
	            	        		 hsv7[0]=storeh[i-1][j+1];
	            	        		 //hsv8[0]=0;
	            	        		 
	            	        		 
	            	        		 
	            	        		 hsv[1]=stores[i][j];
	            	        		 //hsv1[1]=0;
	            	        		 hsv2[1]=stores[i+1][j+1];
	            	        		 hsv3[1]=stores[i][j+1];
	            	        		 hsv4[1]=stores[i+1][j];
	            	        		 hsv5[1]=stores[i-1][j];
	            	        		 //hsv6[1]=0;
	            	        		 hsv7[1]=stores[i-1][j+1];
	            	        		// hsv8[1]=0;
	            	        		 
	            	        		 
	            	        		 hsv[2]=storev[i][j];
	            	        		// hsv1[2]=0;
	            	        		 hsv2[2]=storev[i+1][j+1];
	            	        		 hsv3[2]=storev[i][j+1];
	            	        		 hsv4[2]=storev[i+1][j];
	            	        		 hsv5[2]=storev[i-1][j];
	            	        		// hsv6[2]=0;
	            	        		 hsv7[2]=storev[i-1][j+1];
	            	        		// hsv8[2]=0;
	            	        		 
	            	        		 averageh+=(float) (((Math.abs(hsv7[0]-hsv5[0])+Math.abs(hsv[0]-hsv3[0])+Math.abs(hsv4[0]-hsv2[0])+Math.abs(hsv[0]-hsv5[0])+Math.abs(hsv3[0]-hsv7[0])+Math.abs(hsv4[0]-hsv[0])+Math.abs(hsv2[0]-hsv3[0])))*0.14286);
	        	                     averages+=(float) (((Math.abs(hsv7[1]-hsv5[1])+Math.abs(hsv[1]-hsv3[1])+Math.abs(hsv4[1]-hsv2[1])+Math.abs(hsv[1]-hsv5[1])+Math.abs(hsv3[1]-hsv7[1])+Math.abs(hsv4[1]-hsv[1])+Math.abs(hsv2[1]-hsv3[1])))*0.14286);
	        	                     averagev+=(float) (((Math.abs(hsv7[2]-hsv5[2])+Math.abs(hsv[2]-hsv3[2])+Math.abs(hsv4[2]-hsv2[2])+Math.abs(hsv[2]-hsv5[2])+Math.abs(hsv3[2]-hsv7[2])+Math.abs(hsv4[2]-hsv[2])+Math.abs(hsv2[2]-hsv3[2])))*0.14286);	 
	            				 }
	            				 else if(i>0&&j>0&&j!=(width-1)&&i!=(height-1))
	            					 {
	            						 hsv[0]=storeh[i][j];
	            		        		 hsv1[0]=storeh[i-1][j-1];
	            		        		 hsv2[0]=storeh[i+1][j+1];
	            		        		 hsv3[0]=storeh[i][j+1];
	            		        		 hsv4[0]=storeh[i+1][j];
	            		        		 hsv5[0]=storeh[i-1][j];
	            		        		 hsv6[0]=storeh[i][j-1];
	            		        		 hsv7[0]=storeh[i-1][j+1];
	            		        		 hsv8[0]=storeh[i+1][j-1];
	            		        		 
	            		        		 
	            		        		 
	            		        		 hsv[1]=stores[i][j];
	            		        		 hsv1[1]=stores[i-1][j-1];
	            		        		 hsv2[1]=stores[i+1][j+1];
	            		        		 hsv3[1]=stores[i][j+1];
	            		        		 hsv4[1]=stores[i+1][j];
	            		        		 hsv5[1]=stores[i-1][j];
	            		        		 hsv6[1]=stores[i][j-1];
	            		        		 hsv7[1]=stores[i-1][j+1];
	            		        		 hsv8[1]=stores[i+1][j-1];
	            		        		 
	            		        		 
	            		        		 hsv[2]=storev[i][j];
	            		        		 hsv1[2]=storev[i-1][j-1];
	            		        		 hsv2[2]=storev[i+1][j+1];
	            		        		 hsv3[2]=storev[i][j+1];
	            		        		 hsv4[2]=storev[i+1][j];
	            		        		 hsv5[2]=storev[i-1][j];
	            		        		 hsv6[2]=storev[i][j-1];
	            		        		 hsv7[2]=storev[i-1][j+1];
	            		        		 hsv8[2]=storev[i+1][j-1];
	            		        		 averageh+=(float) (((Math.abs(hsv5[0]-hsv1[0])+Math.abs(hsv7[0]-hsv5[0])+Math.abs(hsv[0]-hsv6[0])+Math.abs(hsv[0]-hsv3[0])+Math.abs(hsv4[0]-hsv8[0])+Math.abs(hsv4[0]-hsv2[0])+Math.abs(hsv6[0]-hsv1[0])+Math.abs(hsv[0]-hsv5[0])+Math.abs(hsv3[0]-hsv7[0])+Math.abs(hsv6[0]-hsv8[0])+Math.abs(hsv4[0]-hsv[0])+Math.abs(hsv2[0]-hsv3[0])))*0.083334);
	            	                     averages+=(float) ((Math.abs(hsv5[1]-hsv1[1])+Math.abs(hsv7[1]-hsv5[1])+Math.abs(hsv[1]-hsv6[1])+Math.abs(hsv[1]-hsv3[1])+Math.abs(hsv4[1]-hsv8[1])+Math.abs(hsv4[1]-hsv2[1])+Math.abs(hsv6[1]-hsv1[1])+Math.abs(hsv[1]-hsv5[1])+Math.abs(hsv3[1]-hsv7[1])+Math.abs(hsv6[1]-hsv8[1])+Math.abs(hsv4[1]-hsv[1])+Math.abs(hsv2[1]-hsv3[1]))*0.083334);
	            	                     averagev+=(float) ((Math.abs(hsv5[2]-hsv1[2])+Math.abs(hsv7[2]-hsv5[2])+Math.abs(hsv[2]-hsv6[2])+Math.abs(hsv[2]-hsv3[2])+Math.abs(hsv4[2]-hsv8[2])+Math.abs(hsv4[2]-hsv2[2])+Math.abs(hsv6[2]-hsv1[2])+Math.abs(hsv[2]-hsv5[2])+Math.abs(hsv3[2]-hsv7[2])+Math.abs(hsv6[2]-hsv8[2])+Math.abs(hsv4[2]-hsv[2])+Math.abs(hsv2[2]-hsv3[2]))*0.083334);
	            					 }
	            					 else if(i==height-1&&j>0&&j!=(width-1))
	            						 {
	            							 hsv[0]=storeh[i][j];
	            			        		 hsv1[0]=storeh[i-1][j-1];
	            			        		 //hsv2[0]=0;
	            			        		 hsv3[0]=storeh[i][j+1];
	            			        		 //hsv4[0]=0;
	            			        		 hsv5[0]=storeh[i-1][j];
	            			        		 hsv6[0]=storeh[i][j-1];
	            			        		 hsv7[0]=storeh[i-1][j+1];
	            			        		 //hsv8[0]=0;
	            			        		 
	            			        		 
	            			        		 
	            			        		 hsv[1]=stores[i][j];
	            			        		 hsv1[1]=stores[i-1][j-1];
	            			        		// hsv2[1]=0;
	            			        		 hsv3[1]=stores[i][j+1];
	            			        		// hsv4[1]=0;
	            			        		 hsv5[1]=stores[i-1][j];
	            			        		 hsv6[1]=stores[i][j-1];
	            			        		 hsv7[1]=stores[i-1][j+1];
	            			        		// hsv8[1]=0;
	            			        		 
	            			        		 
	            			        		 hsv[2]=storev[i][j];
	            			        		 hsv1[2]=storev[i-1][j-1];
	            			        		 //hsv2[2]=0;
	            			        		 hsv3[2]=storev[i][j+1];
	            			        		 //hsv4[2]=0;
	            			        		 hsv5[2]=storev[i-1][j];
	            			        		 hsv6[2]=storev[i][j-1];
	            			        		 hsv7[2]=storev[i-1][j+1];
	            			        		// hsv8[2]=0;
	            			        		 averageh+=(float) (((Math.abs(hsv5[0]-hsv1[0])+Math.abs(hsv7[0]-hsv5[0])+Math.abs(hsv[0]-hsv6[0])+Math.abs(hsv[0]-hsv3[0])+Math.abs(hsv6[0]-hsv1[0])+Math.abs(hsv[0]-hsv5[0])+Math.abs(hsv3[0]-hsv7[0])))*0.14286);
	                	                     averages+=(float) (((Math.abs(hsv5[1]-hsv1[1])+Math.abs(hsv7[1]-hsv5[1])+Math.abs(hsv[1]-hsv6[1])+Math.abs(hsv[1]-hsv3[1])+Math.abs(hsv6[1]-hsv1[1])+Math.abs(hsv[1]-hsv5[1])+Math.abs(hsv3[1]-hsv7[1])))*0.14286);
	                	                     averagev+=(float) (((Math.abs(hsv5[2]-hsv1[2])+Math.abs(hsv7[2]-hsv5[2])+Math.abs(hsv[2]-hsv6[2])+Math.abs(hsv[2]-hsv3[2])+Math.abs(hsv6[2]-hsv1[2])+Math.abs(hsv[2]-hsv5[2])+Math.abs(hsv3[2]-hsv7[2])))*0.14286);     		 
	            			        		 
	            						 }
	            						 else if(j==width-1&&i>0&&i!=(height-1))
	            							 {
	            								 hsv[0]=storeh[i][j];
	            				        		 hsv1[0]=storeh[i-1][j-1];
	            				        		// hsv2[0]=0;
	            				        		 //hsv3[0]=0;
	            				        		 hsv4[0]=storeh[i+1][j];
	            				        		 hsv5[0]=storeh[i-1][j];
	            				        		 hsv6[0]=storeh[i][j-1];
	            				        		// hsv7[0]=0;
	            				        		 hsv8[0]=storeh[i+1][j-1];
	            				        		 
	            				        		 
	            				        		 
	            				        		 hsv[1]=stores[i][j];
	            				        		 hsv1[1]=stores[i-1][j-1];
	            				        		 //hsv2[1]=0;
	            				        		// hsv3[1]=0;
	            				        		 hsv4[1]=stores[i+1][j];
	            				        		 hsv5[1]=stores[i-1][j];
	            				        		 hsv6[1]=stores[i][j-1];
	            				        		// hsv7[1]=0;
	            				        		 hsv8[1]=stores[i+1][j-1];
	            				        		 
	            				        		 
	            				        		 hsv[2]=storev[i][j];
	            				        		 hsv1[2]=storev[i-1][j-1];
	            				        		 //hsv2[2]=0;
	            				        		 //hsv3[2]=0;
	            				        		 hsv4[2]=storev[i+1][j];
	            				        		 hsv5[2]=storev[i-1][j];
	            				        		 hsv6[2]=storev[i][j-1];
	            				        		// hsv7[2]=0;
	            				        		 hsv8[2]=storev[i+1][j-1];
	            				        		 averageh+=(float) (((Math.abs(hsv5[0]-hsv1[0])+Math.abs(hsv[0]-hsv6[0])+Math.abs(hsv4[0]-hsv8[0])+Math.abs(hsv6[0]-hsv1[0])+Math.abs(hsv[0]-hsv5[0])+Math.abs(hsv6[0]-hsv8[0])+Math.abs(hsv4[0]-hsv[0])))*0.14286);
	                    	                     averages+=(float) ((Math.abs(hsv5[1]-hsv1[1])+Math.abs(hsv[1]-hsv6[1])+Math.abs(hsv4[1]-hsv8[1])+Math.abs(hsv6[1]-hsv1[1])+Math.abs(hsv[1]-hsv5[1])+Math.abs(hsv6[1]-hsv8[1])+Math.abs(hsv4[1]-hsv[1]))*0.14286);
	                    	                     averagev+=(float) ((Math.abs(hsv5[2]-hsv1[2])+Math.abs(hsv[2]-hsv6[2])+Math.abs(hsv4[2]-hsv8[2])+Math.abs(hsv6[2]-hsv1[2])+Math.abs(hsv[2]-hsv5[2])+Math.abs(hsv6[2]-hsv8[2])+Math.abs(hsv4[2]-hsv[2]))*0.14286);
	            							 }
	            							 else if(i==height-1&&j==width-1)
	            								 {
	            									 hsv[0]=storeh[i][j];
	            					        		 hsv1[0]=storeh[i-1][j-1];
	            					        		// hsv2[0]=0;
	            					        		 //hsv3[0]=0;
	            					        		 //hsv4[0]=0;
	            					        		 hsv5[0]=storeh[i-1][j];
	            					        		 hsv6[0]=storeh[i][j-1];
	            					        		 //hsv7[0]=0;
	            					        		 //hsv8[0]=0;
	            					        		 
	            					        		 
	            					        		 hsv[1]=stores[i][j];
	            					        		 hsv1[1]=stores[i-1][j-1];
	            					        		 //hsv2[1]=0;
	            					        		 //hsv3[1]=0;
	            					        		 //hsv4[1]=0;
	            					        		 hsv5[1]=stores[i-1][j];
	            					        		 hsv6[1]=stores[i][j-1];
	            					        		 //hsv7[1]=0;
	            					        		 //hsv8[1]=0;
	            					        		 
	            					        		 
	            					        		 hsv[2]=storev[i][j];
	            					        		 hsv1[2]=storev[i-1][j-1];
	            					        		 //hsv2[2]=0;
	            					        		 //hsv3[2]=0;
	            					        		 //hsv4[2]=0;
	            					        		 hsv5[2]=storev[i-1][j];
	            					        		 hsv6[2]=storev[i][j-1];
	            					        		 //hsv7[2]=0;
	            					        		 //hsv8[2]=0;
	            					        		 averageh+=(float) (((Math.abs(hsv5[0]-hsv1[0])+Math.abs(hsv7[0]-hsv5[0])+Math.abs(hsv[0]-hsv6[0])+Math.abs(hsv6[0]-hsv1[0])+Math.abs(hsv[0]-hsv5[0])))*0.25);
	            	        	                     averages+=(float) ((Math.abs(hsv5[1]-hsv1[1])+Math.abs(hsv7[1]-hsv5[1])+Math.abs(hsv[1]-hsv6[1])+Math.abs(hsv6[1]-hsv1[1])+Math.abs(hsv[1]-hsv5[1]))*0.25);
	            	        	                     averagev+=(float) ((Math.abs(hsv5[2]-hsv1[2])+Math.abs(hsv7[2]-hsv5[2])+Math.abs(hsv[2]-hsv6[2])+Math.abs(hsv6[2]-hsv1[2])+Math.abs(hsv[2]-hsv5[2]))*0.25);
	            								 }
	            							 
	        			 
	        			 
	        		 }
	        		 
	        		 
	        	 }
	        	 averageh=(float) averageh/(float)(height*width);
	             averages=(float) averages/(float)(height*width);
	             averagev=(float) averagev/(float)(height*width);
	             
	            // System.out.println("average = "+k +" "+ averageh+ " "+ averages + " "+ averagev);
				imagevector[k][7]=averageh;
				imagevector[k][8]=averages;
				imagevector[k][9]=averagev;
				
				
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	 
    	 }
    	 FileWriter writer;
		try {
			writer = new FileWriter("featurevector.csv",true);
		
		 for(i=1;i<64;i++)
    		 {
			   writer.append(i+".jpg");
			   for(j=0;j<21;j++)
			   {
				   writer.append(","+imagevector[i][j]);
			   }
			   writer.append("\n");
    		 }
		 
		 writer.flush();
			writer.close();
		}
		 catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
    	 for(i=1;i<64;i++)
    		 for(j=0;j<21;j++)
    			 System.out.println(i + " " + imagevector[i][j]);
    	 
    	 
    	 
    	 
     }
     public static void main(String args[])
     {
    	ImageVector object =  new ImageVector();
    	object.getimagevector();
    	 
     }
     
     
     
     
}
