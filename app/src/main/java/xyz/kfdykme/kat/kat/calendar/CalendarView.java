package xyz.kfdykme.kat.kat.calendar;
import android.view.*;
import android.content.*;
import xyz.kfdykme.kat.kat.R;
import xyz.kfdykme.kat.kat.*;

public class CalendarView extends BaseViewImpl<CalendarContract.Presenter> implements CalendarContract.View
{

	
	
	public CalendarView(Context context)
	{
		this.context = context;
		view = LayoutInflater.from(context).inflate(R.layout.view_schedule,null);
	}
	
	


}
