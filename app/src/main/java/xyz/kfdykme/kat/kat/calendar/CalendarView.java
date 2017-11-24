package xyz.kfdykme.kat.kat.calendar;
import android.view.*;
import android.content.*;
import xyz.kfdykme.kat.kat.R;

public class CalendarView implements CalendarContract.View
{
	Context context;

	View root;
	
	CalendarContract.Presenter presenter;
	
	public CalendarView(Context context)
	{
		this.context = context;
		root = LayoutInflater.from(context).inflate(R.layout.view_schedule,null);
	}
	
	

	@Override
	public void setPresenter(CalendarContract.Presenter presenter)
	{
		this.presenter=presenter;
	}

	@Override
	public Context getContext()
	{
		return context;
	}

	@Override
	public View getView()
	{
		return root;
	}

}
