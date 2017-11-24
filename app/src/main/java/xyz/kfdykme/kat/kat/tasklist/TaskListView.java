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

public class TaskListView implements TaskListContract.View
{

	
	View root;

	private RecyclerView mRecyclerView;

	private List<Task> list;

	private TaskListAdapter adapter;
	
	private Context context;
	
	private TaskListContract.Presenter presenter;

	public TaskListView(final Context context){
		this.context = context;
		root = LayoutInflater.from(context).inflate(R.layout.view_task_list,null);
		
		mRecyclerView = (RecyclerView) root.findViewById(R.id.rv);
		mRecyclerView.setItemAnimator(new DefaultItemAnimator());
		mRecyclerView.setLayoutManager(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
	}
	
	@Override
	public void setPresenter(TaskListContract.Presenter presenter)
	{
		this.presenter = presenter;
	}

	@Override
	public void onLoad()
	{
		try
		{
			list  = TaskUtils.getTasks();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}

		adapter =  new TaskListAdapter(context,list);
		
		adapter.mOnItemClickListenee = new TaskListAdapter.OnItemClickListener(){

			private EditPresenter mEditPresenter;

			private EditView mEditView;

			@Override
			public void Click(Task task, int pos)
			{

				presenter.onEditTask(task);
			}


		};
		mRecyclerView.setAdapter(adapter);
	}

	@Override
	public void onReflash()
	{
		try
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
		{}
	}
	@Override
	public Context getContext(){
		return context;
	}
	

	@Override
	public View getView()
	{
		return root;
	}
}
