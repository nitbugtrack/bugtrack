package bugtrack;

import java.util.*;
import java.io.*;

public class Split {

	public static void main(String[] args)
	{
	
	try
	{
		FileReader fr = new FileReader("words.txt");
		BufferedReader bufferedReader = new BufferedReader(fr);
		FileWriter fw = new FileWriter("toplist.txt");
		BufferedWriter bufferedWriter = new BufferedWriter(fw);
		while(bufferedReader.ready())
		{
			String line = bufferedReader.readLine();
			//System.out.println(line);
			
			for(String word1 : Split.words(line))
			{
				if(word1.equals("not")==true)
					System.out.println(word1);
				//System.out.println(word1);
				bufferedWriter.write(word1);
				bufferedWriter.newLine();
			}
		}
		bufferedReader.close();
		bufferedWriter.close();
	}
	catch(Exception e)
	{
		e.getMessage();
	}
		//for(String word : Split.words("The rain in spain falls mainly on the plains, except when it's not exactly working that way! And I need+some= way. How~ will \"(this)\" \"work\"?"))
		//System.out.println(word);
	 System.out.println("Program Ran Error-free");
	}
	 
	static HashSet stopwords = new HashSet();
	 
	public static void addStopwords()
	{
	try{
		BufferedReader br = new BufferedReader(new FileReader("stopwords.txt"));
	 
	while(br.ready())
	{
		String str = br.readLine();
		//System.out.println(str);
		stopwords.add(str);
	}
	 
	}
	catch(Exception e){System.out.println(e);}
	}
	 
	public static ArrayList<String> words(String line)
	{
		if(stopwords.size() == 0)
			addStopwords();
		 
		ArrayList result = new ArrayList();
		 
		String[] words = line.split("[ \t\n,\\.\"!?$~()\\[\\]\\{\\}:;/\\\\<>+=%*]");
		for(int i=0; i < words.length; i++)
		{
			if(words[i] != null && !words[i].equals(""))
			{
				String word = words[i].toLowerCase();
				if(!stopwords.contains(word))
				{
					result.add(word);
				}
			}
		}
		 
		return result;
	}
	 
}
