package org.apriori;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Map.Entry;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.csvreader.CsvWriter;
import com.mysql.fabric.Response;
import com.opencsv.CSVWriter;

/**
 * Servlet implementation class apriori
 */
@WebServlet("/apriori")
public class apriori extends HttpServlet {
	private static final long serialVersionUID = 1L;
	static Connection connection;
	static PreparedStatement p;
	static double supDen;
	static double minSupp;
	static double minConf;
	Map<String, String> result = new HashMap<String, String>();
	List<String> comList = new ArrayList<String>();
	String [] combArr;
	
	public static void loadSQL(){
		// Setting up a connection with mysql server
				try {
					Class.forName("com.mysql.jdbc.Driver");
				} catch (ClassNotFoundException e) {
					e.printStackTrace();
					return;
				}
				try {
					connection = DriverManager.getConnection(
							"jdbc:mysql://localhost:3306/db_project1", "root", "0102");
					System.out.println("Database is created");

				} catch (SQLException e) {
					System.out.println("Connection Failed! Check output console. ");
					e.printStackTrace();
				}
				if (connection == null) {
					System.out.println("Failed to make connection!.. ");
				}
				else
					System.out.println("connection established");
				
		//Loading the entire dataset
				try {
					p=connection.prepareStatement("create table task3 (AGE varchar(30),SEX varchar(30),RACE varchar(30),DAY_OF_ADMISSION varchar(30),DISCHARGE_STATUS varchar(30),STAY_INDICATOR varchar(30),DRG_CODE varchar(30),LENGTH_OF_STAY varchar(30),DRG_PRICE varchar(30) ,TOTAL_CHARGES varchar(30),COVERED_CHARGES varchar(30),POA_DIAGNOSIS_INDICATOR_1 varchar(30),POA_DIAGNOSIS_INDICATOR_2 varchar(30),DIAGNOSIS_CODE_1 varchar(30),DIAGNOSIS_CODE_2 varchar(30),PROCEDURE_CODE_1 varchar(30) ,PROCEDURE_CODE_2 varchar(30),DISCHARGE_DESTINATION varchar(30),SOURCE_OF_ADMISSION varchar(30),TYPE_OF_ADMISSION varchar(30),ADMITTING_DIAGNOSIS_CODE varchar(30))" );
					p.executeUpdate();
					System.out.println("Table created");
					p=connection.prepareStatement("load data infile 'C:/_PRIYA/Academics/Adv DB/Project 2/6339_Dataset_11.csv' into table task3 fields terminated by ',' lines terminated by '\n' ignore 1 rows;");
					p.executeUpdate();
					} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	} //loadSQL() closes
	
	
	//creating csv files
		public static String createCsv(String fileName, String s1,String s2) throws SQLException, IOException{
			ResultSet rs= null;
			List<String> valList = new ArrayList<String>();
			String[] valArr;
			String COMMA_DELIMITER = ",";
			String NEW_LINE_SEPARATOR = "\n";
			FileWriter fileWriter = null;
			
			
		//Creating csv files		
			String path= "C:/Users/Priya/workspace_new/Task3Web/CSV/"+fileName+".csv";
			fileWriter = new FileWriter(path);
	//writing into csv using file writer
			p=connection.prepareStatement("select "+s1+" as a1,"+s2+" as a2 from task3;");
			rs=p.executeQuery();
			while(rs.next()){
				String v1=rs.getString("a1");
				String v2=rs.getString("a2");
				fileWriter.append(v1.trim());
				fileWriter.append(COMMA_DELIMITER);
				fileWriter.append(v2.trim());
				fileWriter.append(NEW_LINE_SEPARATOR);
			}
			valArr = valList.toArray(new String[0]);
			fileWriter.close();
	//writing into file using sql query
			System.out.println("Path to load into file"+ path);
			System.out.println("s1"+ s1);
			System.out.println("s2"+ s2);
				
			//sending the filename, path and selected attributes
			String res=fileName+"-"+path+"-"+s1+"-"+s2;
			return res;
	   }
		
		/**
		 * @param fileName
		 * @param path
		 * @param s1
		 * @param s2
		 * @throws SQLException
		 */
		
   	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
   		String value1[]=request.getParameterValues("keyword");
   		for(int i=0; i<value1.length; i++)
   		{
   		//System.out.println(value1[i]);
   		}
   		String supp=request.getParameter("support");
   		String conf=request.getParameter("conf");
   		String[] arr1;
		String[] arr2;
		
		String[] fileName = new String[100];
		
		List<String> list = new ArrayList<String>();
		Scanner stdin = new Scanner(System.in);
		
		//Calling func to load data in SQL
		loadSQL();		
		
		//calculating the support denominator
				ResultSet res= null;
				try {
					p=connection.prepareStatement("select count(*) as tot from task3;");
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					res= p.executeQuery();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				try {
					while(res.next()){
					supDen= res.getInt("tot");
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				System.out.println("supDen: "+supDen);
		
		list=Arrays.asList(value1);
        System.out.println("List is " + list);
        arr1 = list.toArray(new String[0]);
        arr2=arr1;
        int arr1Len = arr1.length-1;
		int arr2Len = arr1.length-1;

//Gettin min support and min confidence from user
		minSupp= Double.parseDouble(supp);
		System.out.println("Support:" +minSupp);
		minConf= Double.parseDouble(conf);
		System.out.println("Confidence:" +minConf);
		
//combining the attributes
		for (int i=0; i<=arr1Len; i++){ //looping arr1
			for (int j=0;j<=arr2Len; j++){ //looping arr2
			if(i!=j)
			comList.add(arr1[i]+"-"+arr2[j]);
			} //arr2 closes
		}//arr1 closes 

	//converting combList to array
        combArr = comList.toArray(new String[0]);
        System.out.println("CombArray is " + Arrays.toString(combArr));
        
        //loading data in CSV
        for(int i=0; i<combArr.length; i++){
        	String[] splited = combArr[i].split("[\\-\\s]+");
        	fileName[i] = "file"+i;
            try {
				String val=createCsv(fileName[i],splited[0],splited[1]);
				String[] ren = val.split("[\\-\\s]+");		//fileName+"-"+path+"-"+s1+"-"+s2
				String fileName1 = ren[0];
				String path = ren[1];
				String s1 = ren[2];
				String s2 = ren[3];
				List<String> pairList = new ArrayList<String>(); //creating list to add pair
				List<String> firstvalList = new ArrayList<String>(); //creating list to add 1st value
				List<String> pair1List = new ArrayList<String>(); //creating list to add pair
				String[] pairArr = null;
				String[] firstvalArr = null;
				String[] pair1Arr = null;
				
			//Reading data from csv file
				System.out.println("File Name:" +fileName1 );
				String file=path;
				BufferedReader br = null;
				String line = "";
				String cvsSplitBy = ",";
				try {

					br = new BufferedReader(new FileReader(file));
					//int h=0;
					while ((line = br.readLine()) != null) {
						//System.out.println("h: "+h);
						//h++;
						String[] value = line.split("[\\,\\s]+"); // use comma as separator
						//System.out.println("value[0]:"+ value[0]+"value[1]:"+ value[1]);
						//System.out.println("value[1]:"+ value[1]);
						//System.out.println("pairList:"+ pairList);
						pairList.add(value[0]+"-"+value[1]); //adding the pair into list
						firstvalList.add(value[0]); //add the 1st value into list
						}
					pairArr = pairList.toArray(new String[0]);
					firstvalArr = firstvalList.toArray(new String[0]);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			//calculating support
				Map<String, Double> occSupp = new HashMap<String, Double>();
				Map<String, Double> support = new HashMap<String, Double>();
				Map<String, String> combSupp = new HashMap<String, String>();
	
				for ( String word : pairArr ) {
				   Double oldCount = occSupp.get(word);
				   if ( oldCount == null ) {
				      oldCount = 0.0;
				   }
				   double count=oldCount + 1;
				   occSupp.put(word, count);

				   double supp1=count/supDen;
				   if(supp1>=minSupp){  //filtering support based on the min support
				   support.put(word,supp1);
				   }
				}		
				
				
			//calculating count of 1st value
				Map<String, Double> occConf = new HashMap<String, Double>();
				for ( String word : firstvalArr ) {
				   Double oldCount = occConf.get(word);
				   if ( oldCount == null ) {
				      oldCount = 0.0;
				   }
				   double count=oldCount + 1;
				   occConf.put(word, count);
				   
				}
				
				//combining support adn count
				for (Entry<String, Double> mapEntry : occSupp.entrySet()) {
					for (Entry<String, Double> mapEntry1 : support.entrySet()) {
						if((mapEntry.getKey()).equals(mapEntry1.getKey())){
							String key=mapEntry.getKey();
							String val1=Double.toString(mapEntry.getValue());
							String val2=Double.toString(mapEntry1.getValue());
							String combVal=val1+"#"+val2;
							combSupp.put(key,combVal);
						}
					}
				}
				
				//converting map to array
						String[] keys = new String[occSupp.size()];
						Double[] values = new Double[occSupp.size()];
						String[] k = new String[occConf.size()];
						Double[] v = new Double[occConf.size()];
						String[] kc = new String[combSupp.size()];
						String[] vc = new String[combSupp.size()];
						String[] confFinal = null;
						//System.out.println("combSupp.size(): "+combSupp.size());
						int index = 0;
						int incong=0;
						int in=0;
						double confiVal = 0.0;
						
						//Splitting occSupp
						for (Entry<String, Double> mapEntry : occSupp.entrySet()) {
						    keys[index] = mapEntry.getKey();
						    values[index] = mapEntry.getValue();
						    index++;
						}
						
						//Splitting occConf indv count
						for (Entry<String, Double> mapEntry : occConf.entrySet()) {
						    k[incong] = mapEntry.getKey();
						    v[incong] = mapEntry.getValue();
							incong++;
						}
						
						
						//Splitting support adn count
						for (Entry<String, String> mapEntry3 : combSupp.entrySet()) {
						    kc[in] = mapEntry3.getKey();
						    vc[in] = mapEntry3.getValue();
						    in++;
						}

						//Calculating Confidence
						int a=0;
						for(int q=0; q<kc.length; q++)	{
								String[] kSplit = kc[q].split("[-]");
								String[] vSplit = vc[q].split("[#]");
									int z=0;
									for (Entry<String, Double> mapEntry1 : occConf.entrySet()) {
										if(kSplit[0].equals(k[z])){
											double neu= Double.parseDouble(vSplit[0]);
											double deo= v[z];
										confiVal=neu/deo;
										if (confiVal>=minConf){
										result.put(kc[q],"Support:"+""+vSplit[1]+" Confidence:"+""+confiVal);
										}
								}
										z++;
							}
						}//for cal to result ends
						
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        }
        for (Map.Entry<String, String> entry : result.entrySet()) {
		     System.out.println("Rule: " + entry.getKey() + "->" + entry.getValue());
		}
		
 		//send the final results to jsp
		System.out.println("parameters passing to the jsp");
		request.setAttribute("resultMap", result);
	    request.setAttribute("combList", comList);
	    RequestDispatcher rd = request.getRequestDispatcher("Result.jsp");
	    rd.forward(request, response);
	 	} //doPost() ends
   	
}//apriori class ends
