package xyz.kfdykme.kat.kat.tasklist;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskedit.*;

public class TaskListPresenter implements TaskListContract.Presenter
{
	TaskListContract.View view;

	private EditPresenter mEditPresenter;

	private EditView mEditView;

	public TaskListPresenter(TaskListContract.View view)
	{
		this.view = view;
		view.setPresenter(this);
	}
	
	

	@Override
	public void start()
	{
		view.onLoad();
	}

	@Override
	public void onEditTask(Task t)
	{


		mEditView = new EditView(view.getContext());
		mEditPresenter = new EditPresenter(mEditView);
		mEditPresenter.setTasklistPresenter(this);
		mEditPresenter.onLoad(t);
		mEditPresenter.start();
	}

	@Override
	public void onAddTask(Task t)
	{

		mEditView = new EditView(view.getContext());
		mEditPresenter = new EditPresenter(mEditView);
		mEditPresenter.setTasklistPresenter(this);
		 
		mEditPresenter.start();
	}

	@Override
	public void onDeleteTask(Task t)
	{
		// TODO: Implement this method
	}

	@Override
	public void onSaveTask(Task t)
	{
		view.onReflash();
	}

	
	

}
