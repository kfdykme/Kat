package xyz.kfdykme.kat.kat.taskdetail;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import xyz.kfdykme.kat.kat.*;

public class MediumViewHolder extends RecyclerView.ViewHolder
{
	public TextView tv;
	
	public MediumViewHolder(View v){
		super(v);
		tv = (TextView) v.findViewById(R.id.tv);
	}
	
}
