package bugtrack;

public class searchmultithread {
	
	public static void main(String args[])
	{
		Threadsearch1 t1 = new Threadsearch1("Thread 1");
		t1.start();
		Threadsearch2 t2 = new Threadsearch2("Thread 2");
		t2.start();
		Threadsearch3 t3 = new Threadsearch3("Thread 3");
		t3.start();
		Threadsearch4 t4 = new Threadsearch4("Thread 4");
		t4.start();
	}
}
