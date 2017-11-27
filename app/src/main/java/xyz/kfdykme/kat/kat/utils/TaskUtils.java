package xyz.kfdykme.kat.kat.utils;

import com.google.gson.*;
import java.io.*;
import java.util.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskdetail.*;
import android.util.*;

public class TaskUtils
{
	public static List<Task> getTasks() throws IOException{
		List<Task> ts= new ArrayList();
		for(String content:FileUtils.readFiles("task")){
			ts.add(new Gson().fromJson(content,Task.class));
		}
		for(String content:FileUtils.readFiles("apps")){
			ts.add(new Gson().fromJson(content,Task.class));
		}
		return sortByWeight(ts);
	} 
	
	public static List<Task> sortByWeight(List<Task> ts){
		List<Task> nTs = new ArrayList<>();
		while(ts.size() !=0){
			int lep =0;
			for(int i = 0 ;i< ts.size()-1;i++){
				if(ts.get(lep).weight > ts.get(i).weight){
					lep = i;
				}
			}
			if(ts.size() == 1)
				nTs.add(nTs.size(),ts.get(lep));
			else
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
