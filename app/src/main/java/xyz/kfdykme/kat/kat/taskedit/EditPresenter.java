package xyz.kfdykme.kat.kat.taskedit;
import java.util.*;
import java.util.logging.*;
import xyz.kfdykme.kat.kat.utils.*;
import java.io.*;
import com.google.gson.*;
import android.widget.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.tasklist.*;

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
				tasklistPresenter.onSaveTask(null);
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

		view.show();
	}
}
