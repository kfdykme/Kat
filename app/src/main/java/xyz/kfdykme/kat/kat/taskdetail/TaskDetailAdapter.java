package xyz.kfdykme.kat.kat.taskdetail;
import android.content.*;
import android.support.v7.widget.*;
import java.util.*;
import android.view.*;
import xyz.kfdykme.kat.kat.*;
import android.inputmethodservice.*;
import android.view.View.*;

public class TaskDetailAdapter extends RecyclerView.Adapter
{
	Context context;
	
	List<TaskDetail> details;

	public interface OnAddListener{
		void onClick(View view);
	}
	public OnAddListener mOnAddListener;
	
	public TaskDetailAdapter(Context context, List<TaskDetail> details)
	{
		this.context = context;
		this.details = details;
	}


	@Override
	public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
		switch(p2){
			case TaskDetail.LARGE:
				return new LargeViewHolder(
					LayoutInflater.from(context).inflate(R.layout.item_task_detail_title,null)
					);
			case TaskDetail.MEDIUM:
				return new MediumViewHolder(
					LayoutInflater.from(context)
					.inflate(R.layout.item_task_detail_subtitle,null)
					);
			case TaskDetail.NORMAL:
				return new NormalViewHolder(
					LayoutInflater.from(context)
					.inflate(R.layout.item_task_detail_normai,null)
					);
				
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder p1, int p2)
	{
		
		if(p2 == details.size()){
			((MediumViewHolder)p1).tv.setText("ADD");
			((MediumViewHolder)p1).tv.setOnClickListener(new OnClickListener(){

					@Override
					public void onClick(View p1)
					{
						mOnAddListener.onClick(p1);
					}
				});
			
			return ;
		}
		
		
		int type =details.get(p2).itemType;
		String item = details.get(p2).item;
		String title = details.get(p2).title;
		switch(type){
			case TaskDetail.LARGE:
				((LargeViewHolder)p1).tv.setText(item);
				break;
			case TaskDetail.MEDIUM:
				
				((MediumViewHolder)p1).tv.setText(item);
				break;
			case TaskDetail.NORMAL:
				NormalViewHolder vh = (NormalViewHolder)p1;
				vh.tvTitle.setText(title);
				vh.tvContent.setText(item);
				break;
		}
		
	}

	@Override
	public int getItemCount()
	{
		return details.size()+1;
	}

	@Override
	public int getItemViewType(int position)
	{
		if(position == details.size())return 1;
		return details.get(position).itemType;
	}
	
	
	
}
