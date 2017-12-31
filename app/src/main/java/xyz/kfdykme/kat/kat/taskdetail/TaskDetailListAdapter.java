package xyz.kfdykme.kat.kat.taskdetail;
import android.content.*;
import android.support.v7.widget.*;
import java.util.*;
import android.view.*;
import xyz.kfdykme.kat.kat.*;
import android.inputmethodservice.*;
import android.view.View.*;
import android.graphics.*;
import java.text.*;
import xyz.kfdykme.kat.kat.utils.*;

public class TaskDetailListAdapter extends RecyclerView.Adapter
{
	Context context;
	
	List<TaskDetail> details =new ArrayList();
	
	List<Boolean> showTitles= new ArrayList();

	public interface OnAddListener{
		void onClick(View view);
	}
	public OnAddListener mOnAddListener;
	
	public TaskDetailListAdapter(Context context, List<TaskDetail> details)
	{
		this.context = context;
		Comparator cmp = Collator.getInstance(java.util.Locale.CHINA);
		while(details.size() !=0){
			int lep =0;
			for(int i = 0 ;i< details.size();i++){
				if(cmp.compare(details.get(lep).getTitle(),details.get(i).getTitle())<0){
					lep = i;
				}
			}
			this.details.add(0,details.get(lep));	

			details.remove(lep);
		}
		
		//this.details = details;
		
		
		
		for(int i = 0;i < this.details.size();i++)showTitles.add(true);
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
					.inflate(R.layout.item_task_detail_normai,p1,false)
					);
				
		}
		return null;
	}

	@Override
	public void onBindViewHolder(RecyclerView.ViewHolder p1,final int p2)
	{
		
		
		
		int type =details.get(p2).itemType;
		String item = details.get(p2).item;
		final String title = details.get(p2).title;
		final String time = TimeUtils.fromat(details.get(p2).createTime);
		final String subTitle ;
		
			
		subTitle=title;
		final NormalViewHolder vh = (NormalViewHolder)p1;
		vh.tvTitle.setText(subTitle);
		vh.tvTitle.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					if(vh.tvTitle.getText().equals(subTitle)){
						vh.tvTitle.setText(title);
					}else{
						vh.tvTitle.setText(subTitle);
					}
				}
			});
		vh.tvContent.setText(item);
		vh.tvTime.setText(time);
//		vh.tvContent.setOnClickListener(new OnClickListener(){
//
//				@Override
//				public void onClick(View p1)
//				{
//					String craeteTime = details.get(p2).createTime;
//					TaskDetail  thisTd = null;
//					for(TaskDetail t :TaskDetail.listAll(TaskDetail.class)){
//						if(t.createTime.equals(craeteTime))
//							thisTd =t;
//					} 
//					if(thisTd!=null)
//						if(thisTd.itemType == TaskDetail.NORMAL){
//							thisTd.setItemType(TaskDetail.NORMAL_TASK);
//							thisTd.save();
//						} else if(thisTd.itemType == TaskDetail.NORMAL_TASK){
//							thisTd.setItemType(TaskDetail.NORMAL);
//							thisTd.save();
//						}	
//				}
//			});
		if(p2>=1 &&
		   details.get(p2-1).title.equals(title))
		   	showTitles.set(p2,false);
		if(!showTitles.get(p2))
			vh.tvTitle.setVisibility(View.GONE);
		else
			vh.tvTitle.setVisibility(View.VISIBLE);
		switch(type){
			case TaskDetail.LARGE:
			
				break;
			case TaskDetail.MEDIUM:
				
				((MediumViewHolder)p1).tv.setText(item);
				break;
			case TaskDetail.NORMAL:
				
				break;
			case TaskDetail.NORMAL_TASK:
				vh.tvContent.setTextColor(Color.parseColor("#ff004c"));
				break;
		}
		
	}

	@Override
	public int getItemCount()
	{
		return details.size();
	}

	@Override
	public int getItemViewType(int position)
	{
		if(position == details.size())return 1;
		return details.get(position).itemType;
	}
	
	
	
}
