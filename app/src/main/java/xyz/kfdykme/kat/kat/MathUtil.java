package xyz.kfdykme.kat.kat;
import android.util.*;

public class MathUtil
{
	
	public static int rand(int min,int max){
		int rand =  (int)(Math.random() *(max-min)) + min;
		Log.i("MathUtil","rand -- " + rand);
		return rand;
	}
}
