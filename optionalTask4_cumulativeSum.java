 import java.io.FileWriter;
import java.util.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
 
       public class cusum{
    	   @SuppressWarnings("resource")
		public static void main(String[] args) throws IOException {
    	        List<Integer> list = new ArrayList<Integer>();
    	        List<Integer> cusumList = new ArrayList<Integer>();
    	        List<Integer> cusumAboveThreList = new ArrayList<Integer>();
    	        Integer[] obs = list.toArray(new Integer[0]);
    	        String path= "C:/Users/Priya/workspace_new/DBTast3Rule/CSV/Cusum.csv";
    	        FileWriter fileWriter = null;
    	        fileWriter= new FileWriter(path);
    	        BufferedWriter bw=new BufferedWriter(fileWriter);
    			String COMMA_DELIMITER = ",";
    			String NEW_LINE_SEPARATOR = "\n";
    	        int[] obsExp = new int[20];
    	        int[] cusum = new int[20];
    	        int mean;
    	        Double sd;
    	        int sigma;
    	        double thres;
    	        Scanner stdin = new Scanner(System.in);
    	        for(int i=0; i<20; i++){
    	        	int q=i;
    	        	System.out.println("Enter value "+(++q)+": ");
    	        	list.add(stdin.nextInt());    	        	
    	        }
    	        obs = list.toArray(new Integer[0]);
    	        System.out.println("Enter Mean: ");
    	        mean=stdin.nextInt();
    	        System.out.println("Enter Standard Deviation: ");
    	        sd=stdin.nextDouble();
    	        System.out.println("Enter Sigma: ");
    	        sigma=stdin.nextInt();
    	        thres=sd*sigma;
    	        //finding the obv-exp 
    	        for(int i=0; i<20; i++){
    	        	obsExp[i]=obs[i]-mean; 	        	
    	        }
    	        
    	        //Calculating Cusum
    	        cusum[0]=obsExp[0];
    	        cusumList.add(cusum[0]);
    	        for(int i=1; i<20; i++){
    	        	cusum[i]=obsExp[i]+cusum[i-1];
    	        	cusumList.add(cusum[i]);
    	        }
    	        
    	      //Displayng Above_Threshold cusum
    	        System.out.println("Above_Threshold Values:");
    	        for(int i=0; i<20; i++){
    	        	if(cusum[i]>thres){
    	        		cusumAboveThreList.add(cusum[i]);
    	        		//System.out.println(cusum[i]);
    	        	}
    	        	
    	        }
    	        
    	        //Displaying all values
    	        System.out.println("Observed value");
    	        System.out.println(list);
    	        System.out.println("Mean: " +mean);
    	        System.out.println("Standard Deviation: " +sd);
    	        System.out.println("Sigma: " +sigma);
    	        System.out.println("Calculated Cusum");
    	        System.out.println(cusumList);
    	        System.out.println("Cusum Above Threshold Values");
    	        System.out.println(cusumAboveThreList);

    	        //Writing into File
    	        fileWriter.write("OBS_MAX");
    	        fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("MEAN");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("OBSERVER_EXPECTED");
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append("CUSUM");
				fileWriter.append(NEW_LINE_SEPARATOR);
				
				for(int i=0; i<20; i++){
					fileWriter.append(""+obs[i]);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(""+mean);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(""+obsExp[i]);
					fileWriter.append(COMMA_DELIMITER);
					fileWriter.append(""+cusum[i]);
					fileWriter.append(NEW_LINE_SEPARATOR);
				}
    	        
    	        bw.close();
      	    } //main() closes
    	   
    	}//cusum class closes