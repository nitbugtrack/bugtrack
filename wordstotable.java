package bugtrack;
import java.sql.*;
import java.io.*;
import java.util.Scanner;

public class wordstotable {

	public static void main(String[] args) {
	
		String url = "jdbc:mysql://localhost:3306/"; 
		String dbName = "mozilla_db"; 
		String driver = "com.mysql.jdbc.Driver"; 
		String userName = "root";
		String password = ""; 
		
		FileInputStream inputStream = null;
		Scanner sc = null;
		
		try 
		{ 
			Class.forName(driver).newInstance(); 
			Connection conn = DriverManager.getConnection(url+dbName,userName,password); 
			Statement st = conn.createStatement(); 
			int WordCount;
			//FileReader fr = new FileReader("toplist.txt");
			//BufferedReader br = new BufferedReader(fr);
			
			inputStream = new FileInputStream("toplist.txt");
		    sc = new Scanner(inputStream, "UTF-8");
		    while (sc.hasNextLine()) 
		    {
		        String word = sc.nextLine();
		        //System.out.println(line);
				System.out.println(word);
				ResultSet res = st.executeQuery("SELECT Count AS counter FROM mozilla_1 WHERE Word='"+word+"'"); 		
				word = word.replaceAll("(?m)^[ \t]*\r?\n ", "");
				WordCount=0;
				while(res.next() && (!(word.equals(""))==true))
				{
					WordCount = res.getInt("counter");
				}
				if(WordCount==0)
				{
					++WordCount;
					st.executeUpdate("INSERT into mozilla_1(Word, Count) VALUES('"+word+"', '"+WordCount+"')");
				}
				else						
				{
					++WordCount;
					st.executeUpdate("UPDATE mozilla_1 SET Count='"+WordCount+"' WHERE Word ='"+word+"'");
				}
			}	

			System.out.println("Ran Successfully");
			conn.close(); 
			//br.close();
			//fr.close();
			if (sc.ioException() != null) {
		        throw sc.ioException();
		    }
			inputStream.close();
			sc.close();
		}
		catch (Exception e) 
		{ 
			//e.getMessage();
			e.printStackTrace(); 
		} 
	}
 } 
