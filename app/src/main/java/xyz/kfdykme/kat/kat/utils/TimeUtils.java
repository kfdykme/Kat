package xyz.kfdykme.kat.kat.utils;
import java.text.*;

public class TimeUtils
{
	public static String fromat(String taskDetailId){
		long millis = Long.valueOf(taskDetailId);
		SimpleDateFormat format =  new SimpleDateFormat("yy/MM/dd hh:mm");
		return format.format(millis);
	}
}
