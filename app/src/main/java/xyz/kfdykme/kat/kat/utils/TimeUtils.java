package xyz.kfdykme.kat.kat.utils;
import java.text.*;
import android.util.*;

public class TimeUtils
{
	public static String fromat(String taskDetailId){
		long millis = Long.valueOf(taskDetailId);
		SimpleDateFormat format =  new SimpleDateFormat("yy/MM/dd hh:mm");
		return format.format(millis);
	}
	
	public static boolean isTime(String s){
		int c = 0;
		for(int i = 0;i<s.length();i++){
			if(i==2 &&s.charAt(i) == '/')
				c++;
			if(i==5 &&s.charAt(i) == '/')
				c++;
			if(i==8 &&s.charAt(i) == ' ')
				c++;
			if(i==11 &&s.charAt(i) == ':')
				c++;
			
		}
		Log.i("TimeUtils","isTime - c is :"+c);
		if(c ==4) return true;
		
		return false;
	}
}
