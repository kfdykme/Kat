package xyz.kfdykme.kat.kat.utils;

import com.google.gson.*;
import java.io.*;
import java.util.*;
import xyz.kfdykme.kat.kat.*;

public class TaskUtils
{
	public static List<Task> getTasks() throws IOException{
		List<Task> ts= new ArrayList();
		for(String content:FileUtils.readFiles("task")){
			ts.add(new Gson().fromJson(content,Task.class));
		}
		return ts;
	} 
}
