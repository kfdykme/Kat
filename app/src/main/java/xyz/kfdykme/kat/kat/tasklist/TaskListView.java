package xyz.kfdykme.kat.kat.tasklist;

import android.content.*;
import android.support.v7.widget.*;
import android.view.*;
import com.google.gson.*;
import java.io.*;
import java.util.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskedit.*;
import xyz.kfdykme.kat.kat.utils.*;
import android.os.*;
import xyz.kfdykme.kat.kat.taskdetail.*;
import android.widget.*;
import android.support.v7.widget.RecyclerView.*;
import android.animation.*;

public class TaskListView extends BaseViewImpl<TaskListContract.Presenter> implements TaskListContract.View
{
	

	private RecyclerView mRecyclerView;

	public List<Task> list;

	private TaskListAdapter adapter;
	

	public TaskListView(final Context context){
		this.context = context;
		view = LayoutInflater.from(context).inflate(R.layout.view_task_list,null);
		mRecyclerView = (RecyclerView) view.findViewById(R.id.rv);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
		//mRecyclerView.setlay//new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
	}

	@Override
	public void setLayout(final RecyclerView.LayoutManager lm)
	{
		ObjectAnimator alpha = ObjectAnimator.ofFloat(view,"alpha",1f,0f);
		alpha.addListener(new Animator.AnimatorListener(){

				@Override
				public void onAnimationStart(Animator p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onAnimationCancel(Animator p1)
				{
					// TODO: Implement this method
				}

				@Override
				public void onAnimationRepeat(Animator p1)
				{
					// TODO: Implement this method
				}

			

				
				@Override
				public void onAnimationEnd(Animator p1)
				{
					
					
					
					ObjectAnimator.ofFloat(view,"alpha",0f,1f)
					.setDuration(1000)
					.start();
					
					mRecyclerView.setLayoutManager(lm);
				}

				
			});
		
			alpha.setDuration(100);
			alpha.start();
		}

	
	
	
	@Override
	public void onLoad(List<Task> l)
	{
		if(l == null) return;
		list = l;
		//
		adapter =  new TaskListAdapter(context,list);
		
		adapter.mOnItemClickListenee = new TaskListAdapter.OnItemClickListener(){

			
			@Override
			public void click(Task task, int pos)
			{
				presenter.onItemClick(task,pos);
			}


		};
		
		adapter.mOnItemLongClickListener = new TaskListAdapter.OnItemLongClickListener(){

			@Override
			public boolean longClick(Task task, View view, boolean bool)
			{
				presenter.onItemLongClick(task,-1);
				return false;
			}
		};
		
		 
		mRecyclerView.setAdapter(adapter);
	}

	@Override
	public void onReflash()
	{
		/*try
		{
			for(String s:FileUtils.readFiles("task")){
				boolean add = true;
				Task map = new Gson().fromJson(s,Task.class);

				if(map !=null)
					for(Task m:list){
						if(m.id.equals(map.id)) add = false;
					}
				else add = false;
				if(add) list.add(map);
				adapter.notifyDataSetChanged();
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
			showToat(e.getMessage(),Toast.LENGTH_SHORT);
		}*/
	}

	@Override
	public List<Task> getTasks()
	{
		// TODO: Implement this method
		return list;
	}

	@Override
	public void scrollTo(int pos)
	{
		mRecyclerView.smoothScrollToPosition(pos);
	}



	
	
	
	
}
