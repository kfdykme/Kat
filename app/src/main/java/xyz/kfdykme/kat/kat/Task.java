package xyz.kfdykme.kat.kat;

public class Task
{
		
	public String id;
	public int tc;
	public int bgc;
	public String text;
	public int status = 1; 
	
	public static final int ACTIVE = 1;

	public static final int SLEEP = 2;

	public static final int COMLET = 3;	
	
	public Task(){}
	
	public Task(String id, int tc, int bgc, String text)
	{
		this.id = id;
		this.tc = tc;
		this.bgc = bgc;
		this.text = text;
	}

	
}