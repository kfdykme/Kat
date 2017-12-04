package xyz.kfdykme.kat.kat.taskdetail;
import android.content.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import android.widget.*;
import java.util.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskedit.*;
import xyz.kfdykme.kat.kat.utils.*;

public class TaskDetailListView extends BaseViewImpl<TaskDetailListContract.Presenter> implements TaskDetailListContract.View
{


	public EditViewWithoutDialog mEditView;
	public EditPresenter mEditPresenter;

	RecyclerView rv;

	Task mTask;

	
	public TaskDetailListView(Context context,Task t){
		this.context = context;
		mTask = t;
		view = LayoutInflater.from(context).inflate(R.layout.view_task_detail,null);
	}

	@Override
	public void onAddTaskDetail(final Task task)
	{
		final String taskId = mTask.id;
		final View av = LayoutInflater.from(getContext()).inflate(R.layout.view_add_task_edit,null);
		final EditText etTitle = (EditText) av.findViewById(R.id.et_title);
		etTitle.setText(TimeUtils.fromat(System.currentTimeMillis()+""));
		AlertDialog d = new AlertDialog.Builder(getContext())
			.setView(av)
			.setPositiveButton("Save", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{

					EditText etContent = (EditText) av.findViewById(R.id.et_content);
					String title = etTitle.getText().toString();
					String content = etContent.getText().toString();
					if(!title.isEmpty() && !content.isEmpty()){
						TaskDetail td = new TaskDetail();
						td.createTime = System.currentTimeMillis()+"";
						td.taskId = taskId;
						td.title = title;
						td.item = content;
						td.itemType = 2;
						td.save();
						Toast.makeText(getContext(),"Save as " + title,Toast.LENGTH_LONG).show();
						presenter.onSave();
						mEditPresenter.onSave(mTask);
						//presenter.load(mTask);				
					}
				}
			})
			.create();
		d.show();
	}
	@Override
	public void onLoad(List<TaskDetail> ts)
	{
		rv= (RecyclerView) view.findViewById(R.id.rv);
		rv.setItemAnimator(new DefaultItemAnimator());
		LinearLayoutManager lm = new LinearLayoutManager(getContext());
		//lm.setReverseLayout(true);

		rv.setLayoutManager(lm);
		TaskDetailListAdapter adapter = new TaskDetailListAdapter(getContext(),ts);
		rv.setAdapter(adapter);
		adapter.mOnAddListener = new TaskDetailListAdapter.OnAddListener(){

			@Override
			public void onClick(View view)
			{
				presenter.addTaskDetail(mTask);
			}
		};
		mEditView = new EditViewWithoutDialog(getContext(),getView());
		mEditPresenter = new EditPresenter(mEditView);
		mEditView.setPresenter(mEditPresenter);

		if(mTask!=null){
			mEditPresenter.start();
			mEditPresenter.onLoad(mTask);
		}else{
			mEditView.cv.setVisibility(View.GONE);	
		}
		
	}

	
	
}
