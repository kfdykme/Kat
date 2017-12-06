package xyz.kfdykme.kat.kat;
import java.util.*;

public class Task
{
		
	public String id;
	public int tc;
	public int bgc;
	public String text;
	public int status = 1;  
	
	public List<String> type = new ArrayList<String>();
	
	public String className ="";
	
	public String action ="";
	
	public int weight = 0;
	
	public static final int ACTIVE = 1;

	public static final int SLEEP = 2;

	public static final int COMLET = 3;	
	 
	
	public static final String TYPE_TASK = "task";
	
	public static final String TYPE_APP = "app";
	
	public static final String TYPE_DESKTOP = "desktop";
	
	public static final String TYPE_ACTION = "action";
	
	public static final String ACTION_TASK = "a_to_task";	
	
	public static final String ACTION_APP = "a_to_app";	
	
	public static final String ACTION_ADD_RECORD = "a_to_add_record";	
	
	
	public Task(){}
	
	public Task(String id, int tc, int bgc, String text)
	{
		this.id = id;
		this.tc = tc;
		this.bgc = bgc;
		this.text = text;
	}

	public  boolean checkType(String key){
		for(String s:type)
			if(s.equals(key))
				return true;
		return false;
	}
	
	public void addType(String key){
		if(checkType(key))
			return;
		else type.add(key);
	}
	
	public void removeType(String key){
		if(checkType(key))
			type.remove(key);
	}
}
