package xyz.kfdykme.kat.kat.taskdetail;
import xyz.kfdykme.kat.kat.*;
import java.util.*;
import android.util.Log;
import xyz.kfdykme.kat.kat.utils.*;
import android.widget.*;

public class TaskDetailListPresenter implements TaskDetailListContract.Presenter
{
	
	private TaskDetailListContract.View view;

	public Task task;

	public TaskDetailListPresenter(TaskDetailListContract.View view,Task task)
	{
		this.task = task;
		this.view = view;
	}
	
	@Override
	public void start()
	{
		load(task);
	}

	@Override
	public void onSave()
	{
		load(task);
	}

	@Override
	public void save(String title, String content)
	{
		if(!title.isEmpty() && !content.isEmpty()){
			TaskDetail td = new TaskDetail();
			td.createTime = System.currentTimeMillis()+"";
			td.taskId = task.id;
			td.title = title;
			td.item = content;
			td.itemType = 2;
			td.save();
			
			//
			Toast.makeText(view.getContext(),"Save as " + title,Toast.LENGTH_LONG).show();
			
			
			onSave();
			view.getEditPresenter().onSave(task);
						
		}
	}

	
	

	@Override
	public void onCancel()
	{
		
	}

	@Override
	public void load(Task task)
	{
		List<TaskDetail> all = TaskDetail.listAll(TaskDetail.class);
		List<String> titles = new ArrayList<String>();
		
		List<TaskDetail> loadDetails = new ArrayList<>();
		
		titles.add("current time");
		if(task !=null)
		for(TaskDetail t:all){
			Log.i("TaskDetail",t.toString());
			if(t.taskId.equals(task.id))loadDetails.add(t);
		}else {
			view.onLoad(all,titles);
			return;
		}
		
		
		if(loadDetails.size()!=0)
		if(!TimeUtils.isTime(loadDetails.get(loadDetails.size()-1).getTitle()))
			titles.add(0,loadDetails.get(loadDetails.size()-1).getTitle());

		for(TaskDetail td:loadDetails){
			String t = td.getTitle();

			if(!TimeUtils.isTime(t))
			{
				boolean in = false;
				for(String s:titles){
					if(s.equals(t))in = true;
				}
				if(!in)
					titles.add(1,td.getTitle());
			}
		}
		
		
		view.onLoad(loadDetails,titles);
		
	}

	@Override
	public void addTaskDetail(Task task)
	{
		view.onAddTaskDetail(task);
	}

	@Override
	public void onSelectSpinner(String s)
	{
		if(s.equals("current time"))
			view.onSelectSpinner(TimeUtils.fromat(System.currentTimeMillis()+""));
		else
			view.onSelectSpinner(s);
		
	}


	

	
	
}
