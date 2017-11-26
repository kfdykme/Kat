package xyz.kfdykme.kat.kat.taskdetail;
import xyz.kfdykme.kat.kat.*;
import java.util.*;
import android.util.*;

public class TaskDetailPresenter implements TaskDetailContract.Presenter
{
	
	private TaskDetailContract.View view;

	public Task task;

	public TaskDetailPresenter(TaskDetailContract.View view,Task task)
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
		// TODO: Implement this method
	}

	@Override
	public void onCancel()
	{
		// TODO: Implement this method
	}

	@Override
	public void load(Task task)
	{
		List<TaskDetail> all = TaskDetail.listAll(TaskDetail.class);
		
		List<TaskDetail> loadDetails = new ArrayList<>();
		for(TaskDetail t:all){
			if(t.taskId.equals(task.id))loadDetails.add(t);
		}
		
		view.onLoad(loadDetails);
		
	}

	@Override
	public void addTaskDetail(Task task)
	{
		view.onAddTaskDetail(task);
	}

	

	
	
}
