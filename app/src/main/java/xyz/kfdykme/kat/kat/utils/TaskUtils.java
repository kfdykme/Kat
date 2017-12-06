package xyz.kfdykme.kat.kat.utils;

import com.google.gson.*;
import java.io.*;
import java.util.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskdetail.*;
import android.util.*;
import java.text.*;

public class TaskUtils
{
	public static List<Task> getRecordTasks() throws IOException{
		List<Task> ts= new ArrayList();
		for(String content:FileUtils.readFiles("task")){
			Task t = new Gson().fromJson(content,Task.class);
			ts.add(t);
		}
		
		return sortByChina(ts);//sortByWeight(ts);
	} 
	
	public static List<Task> getTaskApps() throws JsonSyntaxException, IOException {
		List<Task> ts = new ArrayList();
		
		for(String content:FileUtils.readFiles("apps")){
			Task t = new Gson().fromJson(content,Task.class);
			ts.add(t);
		}
		
		
		
		return sortByChina(ts);
	}
	
	public static List<Task> getTaskAction() throws JsonSyntaxException, IOException {
		List<Task> ts = new ArrayList();

		for(String content:FileUtils.readFiles("action")){
			Task t = new Gson().fromJson(content,Task.class);
			ts.add(t);
		}



		return sortByChina(ts);
	}
	
	
	public static List<Task> getTaskDesktop() throws JsonSyntaxException, IOException {
		List<Task> ts = new ArrayList();

		for(String content:FileUtils.readFiles("apps")){
			Task t = new Gson().fromJson(content,Task.class);
			if(t.checkType(Task.TYPE_DESKTOP))
				ts.add(t);
		}
		
		for(String content:FileUtils.readFiles("task")){
			Task t = new Gson().fromJson(content,Task.class);
			if(t.checkType(Task.TYPE_DESKTOP))
				ts.add(t);
		}
		
		for(String content:FileUtils.readFiles("action")){
			Task t = new Gson().fromJson(content,Task.class);
			if(t.checkType(Task.TYPE_DESKTOP))
				ts.add(t);
		}
		
		
		return sortByChina(ts);
	}
	
	public static List<Task> sortByChina(List<Task> ts){
		List<Task> nTs = new ArrayList<>();
		
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
		while(ts.size() !=0){
			int lep =0;
			for(int i = 0 ;i< ts.size();i++){
				if(cmp.compare(ts.get(lep).text,ts.get(i).text)<0){
					lep = i;
				}
			}
			nTs.add(0,ts.get(lep));	

			ts.remove(lep);
		}
		return nTs;
	}
	
	
	public static List<Task> sortByWeight(List<Task> ts){
		List<Task> nTs = new ArrayList<>();
		while(ts.size() !=0){
			int lep =0;
			for(int i = 0 ;i< ts.size();i++){
				if(ts.get(lep).weight > ts.get(i).weight){
					lep = i;
				}
			}
			nTs.add(0,ts.get(lep));	
	
			ts.remove(lep);
		}
		return nTs;
	}
	
	public static void outputTaskDetails(){
		List<Map> list = new ArrayList();
		for(TaskDetail td : TaskDetail.listAll(TaskDetail.class)){
			Map<String,String> map =new HashMap();	
			map.put("time",td.createTime);
			map.put("title",td.title);
			map.put("taskId",td.taskId);
			map.put("taskName",td.taskName);
			map.put("item",td.item);
			map.put("itemType",td.itemType+"");
			list.add(map);
		}
		try
		{
			FileUtils.createFile("taskdetail", "list.ktda", new Gson().toJson(TaskDetail.listAll(TaskDetail.class)));
		}
		catch (IOException e)
		{}
	}
	/*
	*
	* @Parame isReWrite : should rewirte database
	*/
	public static int readTaskDetails(boolean isReWrite){
		List<TaskDetail> backup = new ArrayList<>();
		for(TaskDetail td:TaskDetail.listAll(TaskDetail.class)){
			backup.add(td);
			if(true) td.delete();
		}
		try
		{
			String content = FileUtils.readFile("taskdetail", "list", "ktda");
			
			List<TaskDetail> list = new Gson().fromJson(content,List.class);
			for(TaskDetail m:list){
				
				m.save();
			}
			
		}
		catch (Throwable e)
		{
			Log.d("TaskUtils",e.getMessage());
			e.printStackTrace();
			for(TaskDetail td:backup)
			td.save();
		}
		
		return 0;
	}
}
