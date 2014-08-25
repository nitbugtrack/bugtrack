package bugtrack;

import java.util.*;
import java.io.*;

public class Split {

	public static void main(String[] args)
	{
		
	for(String word : Split.words("The rain in spain falls mainly on the plains, except when it's not exactly working that way! And I need+some= way. How~ will \"(this)\" \"work\"?"))
	System.out.println(word);
	 
	 
	}
	 
	static HashSet stopwords = new HashSet();
	 
	public static void addStopwords()
	{
	try{
	BufferedReader br = new BufferedReader(new FileReader("/Users/AdityaPurandare/Desktop/project/bugtrack/bugtrack/bugtrack/stopwords.txt"));
	 
	while(br.ready())
	{
	stopwords.add(br.readLine());
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
