package xyz.kfdykme.kat.kat.taskedit;
import android.content.*;
import android.os.*;
import com.google.gson.*;
import java.io.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskdetail.*;
import xyz.kfdykme.kat.kat.tasklist.*;
import xyz.kfdykme.kat.kat.utils.*;

public class EditPresenter implements EditContract.Presenter
{

	
	EditContract.View view;

	
	TaskListContract.Presenter tasklistPresenter;
	
	public EditPresenter(EditView view){
		this.view  = view;
		view.setPresenter(this);
	}
	
	@Override
	public void onLoad(Task map)
	{
		view.onLoad(map);
	}

	@Override
	public void onSave(Task map)
	{		
		try
		{
			if(map!=null){
				FileUtils.createFile("task", map.id + ".kta", new Gson().toJson(map));
			}
			if(tasklistPresenter!=null)
				tasklistPresenter.onReflash(null);
		}
		catch (IOException e)
		{}

		onCancel();
	}

	@Override
	public void onCancel()
	{
		view = null;
		
	}
	

	public void setTasklistPresenter(TaskListContract.Presenter tasklistPresenter)
	{
		this.tasklistPresenter = tasklistPresenter;
	}

	public TaskListContract.Presenter getTasklistPresenter()
	{
		return tasklistPresenter;
	}

	@Override
	public void start()
	{

		view.attach(); 
	}
	
	public void onDetail(Task task){
		String json = new Gson().toJson(task);
		Bundle b = new Bundle();
		b.putString(TaskDetailActivity.TASK,json);
		Intent i = new Intent(view.getContext(),TaskDetailActivity.class);
		i.putExtras(b);
		view.getContext().startActivity(i);

	}
	@Override
	public void showDialog(){
		view.show();
	}

	@Override
	public void onDelete(Task t)
	{
		try
		{
			FileUtils.deleteFile("task", t.id + ".kta");
			if(tasklistPresenter!=null)
				tasklistPresenter.start();
		}
		catch (Throwable e)
		{}
	}

	
	
}
