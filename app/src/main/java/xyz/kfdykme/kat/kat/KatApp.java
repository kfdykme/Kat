package xyz.kfdykme.kat.kat;
import android.app.*;
import com.orm.*;


public class KatApp extends Application
{

	@Override
	public void onCreate()
	{
		super.onCreate();
		SugarContext.init(this);
		
	}

	@Override
	public void onTerminate()
	{
		SugarContext.terminate();
		super.onTerminate();
	}
	
	
	
}
