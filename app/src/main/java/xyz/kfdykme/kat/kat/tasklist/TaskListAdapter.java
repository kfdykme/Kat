package xyz.kfdykme.kat.kat.tasklist;
import android.content.*;
import android.graphics.*;
import android.support.v7.widget.*;
import android.util.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import com.google.gson.*;
import java.util.*;
import xyz.kfdykme.kat.kat.taskedit.*;
import xyz.kfdykme.kat.kat.*;

public class TaskListAdapter extends RecyclerView.Adapter<TaskListAdapter.ViewHolder>
{

	public Context context;
	
	public LayoutInflater inflayer;
	public List<Task> tasks =null;
	
	
	public interface OnItemClickListener
	{
		public void click(Task task,int pos);
	}
	
	public interface OnItemLongClickListener
	{
		public boolean longClick(Task task,View view,boolean bool);
	}
	
	
	public OnItemClickListener mOnItemClickListenee;
	public OnItemLongClickListener mOnItemLongClickListener;
	public TaskListAdapter(Context context, List<Task> lists)
	{
		this.context = context;
		this.tasks = lists;
		
		inflayer = LayoutInflater.from(context);
	}
	
	
	
	@Override
	public TaskListAdapter.ViewHolder onCreateViewHolder(ViewGroup p1, int p2)
	{
	
		View v = inflayer.inflate(R.layout.rv_item,p1,false);
		TaskListAdapter.ViewHolder vh =  new TaskListAdapter.ViewHolder(v);
		
		return vh;
	}

	@Override
	public void onBindViewHolder( TaskListAdapter.ViewHolder p1,final int p2)
	{
		
		//init bgcolor, textcolor and gravity
		p1.bgc = tasks.get(p2).bgc;
		p1.tc = tasks.get(p2).tc;
		p1.tv.setGravity(Gravity.CENTER);
		
		//change colors if this is a app task
		if(tasks.get(p2).checkType(Task.TYPE_APP)){
			
			p1.tc = context.getResources().getColor(R.color.colorAccentLight);
			p1.bgc = Color.argb(69,33,33,33);
		//	p1.tv.setGravity(Gravity.LEFT);// set TextView's gravity to Left, maybe I should reset it in Other tasks.
		} 
		
		p1.tv.setText(tasks.get(p2).text);
		p1.cv.setBackgroundColor(p1.bgc);
		p1.tv.setTextColor(p1.tc);
		int tempWidth = 300 +(p2%3)*70;
		//if(tempWidth > p1.tv.getWidth())
		//	p1.tv.setWidth(tempWidth);
		p1.tv.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View v)
				{
					if(mOnItemClickListenee != null) mOnItemClickListenee.click(tasks.get(p2),p2);
				}
			});
		p1.tv.setOnLongClickListener(new OnLongClickListener(){

				@Override
				public boolean onLongClick(View p1)
				{
					if(mOnItemLongClickListener !=null)
						mOnItemLongClickListener.longClick(tasks.get(p2),p1,false);
					// TODO: Implement this method
					return false;
				}
			});
		
	}

	
	
	@Override
	public int getItemCount()
	{
		return tasks.size();
	}
	
	public class ViewHolder extends RecyclerView.ViewHolder{
		public CardView cv;
		public TextView tv;
		public int bgc;
		public int tc;
		public ViewHolder(View view){
			super(view);
			cv = (CardView) view.findViewById(R.id.cv);
			tv = (TextView) view.findViewById(R.id.tv);
			
		}
		
		private int getrand(){
			return MathUtil.rand(100,250);
		}
	}
	
}
