package xyz.kfdykme.kat.kat.taskdetail;

import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import xyz.kfdykme.kat.kat.*;

public class NormalViewHolder extends RecyclerView.ViewHolder
{
	TextView tvTitle;
	TextView tvContent;
	
	public NormalViewHolder(View view){
		super(view);
		tvTitle = (TextView) view.findViewById(R.id.tv_title);
		tvContent = (TextView) view.findViewById(R.id.tv_content);
	}
}
