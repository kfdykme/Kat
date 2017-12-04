package xyz.kfdykme.kat.kat.tasklist;
import android.content.*;
import android.os.*;
import com.google.gson.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskdetail.*;
import xyz.kfdykme.kat.kat.taskedit.*;
import android.content.pm.*;
import android.graphics.*;
import xyz.kfdykme.kat.kat.utils.*;
import java.io.*;
import com.google.common.base.*;
import android.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;

public class TaskListPresenter implements TaskListContract.Presenter
{
	TaskListContract.View view;

	private EditPresenter mEditPresenter;

	private EditView mEditView;
	
	private Handler mHander = new Handler();

	public TaskListPresenter(TaskListContract.View view)
	{
		this.view = view;
		view.setPresenter(this);
	
	}
	
	

	@Override
	public void start()
	{
		load(Task.TYPE_DESKTOP);
	}

	@Override
	public void onEditTask(Task t)
	{


		mEditView = new EditView(view.getContext());
		mEditPresenter = new EditPresenter(mEditView);
		mEditPresenter.setTasklistPresenter(this);
		mEditPresenter.start();
		mEditPresenter.onLoad(t);
		mEditPresenter.showDialog();
		
	}

	@Override
	public void onAddTask(Task t)
	{

		mEditView = new EditView(view.getContext());
		mEditPresenter = new EditPresenter(mEditView);
		mEditPresenter.setTasklistPresenter(this);	 
		mEditPresenter.start();
		mEditPresenter.showDialog();
	}

	@Override
	public void onItemClick(Task task, int pos)
	{



		if(task.checkType(Task.TYPE_TASK))
			onEditTask(task);
		else {
			try{
				Intent resolveIntent = view.getContext().getPackageManager().getLaunchIntentForPackage(task.id);// 这里的packname就是从上面得到的目标apk的包名
				// 启动目标应用
				view.getContext().startActivity(resolveIntent);
				task.weight++;
				FileUtils.createFile("apps",task.id + ".kta",new Gson().toJson(task));
			} catch(Exception e){
				
				Toast.makeText(view.getContext(),e.getMessage(),Toast.LENGTH_SHORT).show();
				e.printStackTrace();
				try
				{
					FileUtils.deleteFile("apps", task.id + ".kta");
				}
				catch (Throwable e2)
				{
					
				}
			} 
		}
	}

	@Override
	public void onItemLongClick(final Task t, int p)
	{
		if(!t.checkType(Task.TYPE_DESKTOP)){
			Context c = view.getContext();
			new AlertDialog.Builder(c)
			.setTitle("Add "+t.text+" to desktop?")
				.setPositiveButton("Yes", new DialogInterface.OnClickListener(){

					@Override
					public void onClick(DialogInterface p1, int p2)
					{
						t.addType(Task.TYPE_DESKTOP);
						try
						{
							String redir = "desktop";
							if(t.checkType(Task.TYPE_APP)) redir = "apps";
							else redir = "task";
							FileUtils.createFile(redir, t.id + ".kta", new Gson().toJson(t));
						}
						catch (IOException e)
						{
							e.printStackTrace();
						}

					
					}
				})
			
			
			.create().show();
		}
	}

	
	

	@Override
	public void load(String s) 
	{
		try
		{
		switch(s){
			case Task.TYPE_APP:				
				view.onLoad(TaskUtils.getTaskApps());		
				break;
			case Task.TYPE_TASK:
				view.onLoad(TaskUtils.getRecordTasks());
				break;		
			case Task.TYPE_DESKTOP:
			default:
				view.setLayout(new StaggeredGridLayoutManager(5,StaggeredGridLayoutManager.HORIZONTAL));
				view.onLoad(TaskUtils.getTaskDesktop());
			break;
		}
		
		}
		catch (JsonSyntaxException e)
		{
			e.printStackTrace();
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
	}



	
	
	
	@Override
	public void onDeleteTask(Task t)
	{
		// TODO: Implement this method
	}

	@Override
	public void onReflash(Task t)
	{
		for(final PackageInfo p : view.getContext().getPackageManager().getInstalledPackages(0)){
			if(view.getContext().getPackageManager().getLaunchIntentForPackage(p.applicationInfo.packageName) ==null) continue;
			String label = p.applicationInfo.loadLabel(view.getContext().getPackageManager()).toString();
			Task appT = new Task();
			appT.id = p.packageName;
			appT.bgc = Color.rgb(getrand(),getrand(),getrand());
			appT.tc = Color.rgb(getrand(),getrand(),getrand());
			appT.text = label;
			appT.addType(Task.TYPE_APP);
			appT.className = p.applicationInfo.className;
			
			
			try
			{
				if(FileUtils.getFile("apps",appT.id+".kta") ==null)
					FileUtils.createFile("apps", appT.id + ".kta", new Gson().toJson(appT));	
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}catch (Throwable Throwables){
				Throwables.printStackTrace();
			}
			//					view.dissmissProgressDialog();
			//					view.onReflash();	
			
		}
		
		view.onReflash();			
		
	}

	private int getrand(){
		return MathUtil.rand(100,250);
	}

	@Override
	public void search(String key)
	{
		for(int i = 0 ;i <view.getTasks().size();i++){
			Task t = view.getTasks().get(i);
			if(t.text.contains(key))
				view.scrollTo(i);
		}
		
	}

	
	
	

}
