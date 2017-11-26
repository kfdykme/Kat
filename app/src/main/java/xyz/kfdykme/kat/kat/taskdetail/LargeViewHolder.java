package xyz.kfdykme.kat.kat.taskdetail;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import xyz.kfdykme.kat.kat.*;

public class LargeViewHolder extends RecyclerView.ViewHolder
{
	public TextView tv;
	
	
	public LargeViewHolder(View v)
	{
		super(v);
		tv = (TextView) v.findViewById(R.id.tv);
	}
}
