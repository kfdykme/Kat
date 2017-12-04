package xyz.kfdykme.kat.kat.taskdetail;
import xyz.kfdykme.kat.kat.*;
import java.util.*;
import android.util.*;

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
	public void onCancel()
	{
		
	}

	@Override
	public void load(Task task)
	{
		List<TaskDetail> all = TaskDetail.listAll(TaskDetail.class);
		
		List<TaskDetail> loadDetails = new ArrayList<>();
		if(task !=null)
		for(TaskDetail t:all){
			Log.i("TaskDetail",t.toString());
			if(t.taskId.equals(task.id))loadDetails.add(t);
		}else {
			view.onLoad(all);
			return;
		}
		
		view.onLoad(loadDetails);
		
	}

	@Override
	public void addTaskDetail(Task task)
	{
		view.onAddTaskDetail(task);
	}

	

	
	
}
