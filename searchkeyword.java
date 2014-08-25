package bugtrack;
import java.sql.*;

public class searchkeyword {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		System.out.println("Hello World!");
	
		String url = "jdbc:mysql://localhost:3306/"; 
		String dbName = "mozilla_db"; 
		String driver = "com.mysql.jdbc.Driver"; 
		String userName = "root";
		String password = ""; 
		try 
		{ 
			Class.forName(driver).newInstance(); 
			Connection conn = DriverManager.getConnection(url+dbName,userName,password); 
			Statement st = conn.createStatement(); 
			ResultSet res = st.executeQuery("SELECT bug_id, short_desc FROM bugs WHERE bug_id <=10000 "); 
			int WordCount;
			while(res.next()) 
			{ 
				int bug_id = res.getInt("bug_id"); 
				
				//Captured Summary for a row
				//Tested on 6th Aug 11:39pm | Aditya | Working
				String summary = res.getString("short_desc"); 
				System.out.println(bug_id + "\t" + summary);
				
				
				//Splitting into Words
				String delims ="[ \t\n,\\.\"!?$~()\\[\\]\\{\\}:;/\\\\<>+=%*]+";
				String tokens[] = summary.split(delims);
				
				//Converting to LowerCase Characters
				for(int loop_var=0; loop_var<tokens.length; ++loop_var)
				{	
					
					String word = tokens[loop_var].toLowerCase();
					
					//Ignoring 2 characters & blank words 
					//Tested on 6th Aug 11:40pm | Aditya | Working
					if((word.equals(""))==true || (word.length()<3)) 
							continue;
					
					System.out.println(word+","+tokens.length);
					
					Statement st2 = conn.createStatement();
					ResultSet res2 = st2.executeQuery("SELECT Count AS counter FROM mozilla_1 WHERE Word='"+word+"'");
					//System.out.println(res2);
					
					WordCount=0;
					while(res2.next())
					{
						WordCount = res2.getInt("counter");
					}					
					//System.out.println(WordCount);
					//Queries re-tested on 6th Aug | 11:43pm | Aditya | working
					//System.out.println("Word="+word+", Count="+WordCount);
					if(WordCount==0)
					{
						++WordCount;
						st2.executeUpdate("INSERT into mozilla_1(Word, Count) VALUES('"+word+"', '"+WordCount+"')");
					}
					else						
					{
						++WordCount;
						st2.executeUpdate("UPDATE mozilla_1 SET Count='"+WordCount+"' WHERE Word ='"+word+"'");
					}
				} 
			}

			System.out.println("Ran Successfully");
			conn.close(); 
		}
		catch (Exception e) 
		{ 
			//e.getMessage();
			e.printStackTrace(); 
		} 
	}
 } 
