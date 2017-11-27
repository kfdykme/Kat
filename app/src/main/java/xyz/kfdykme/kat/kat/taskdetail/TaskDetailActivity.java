package xyz.kfdykme.kat.kat.taskdetail;
import android.os.*;
import android.support.v7.app.*;
import com.google.gson.*;
import xyz.kfdykme.kat.kat.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.view.*;
import android.support.v7.widget.*;
import xyz.kfdykme.kat.kat.taskedit.*;
import xyz.kfdykme.kat.kat.utils.TimeUtils;

public class TaskDetailActivity extends AppCompatActivity implements TaskDetailContract.View
{
	
	public TaskDetailContract.Presenter presenter;
	public EditViewWithoutDialog mEditView;
	public EditPresenter mEditPresenter;
	public View view;
	
	RecyclerView rv;

	Task mTask;
	public static final String TASK = "task";
	@Override
	public void setPresenter(TaskDetailContract.Presenter presenter)
	{
		this.presenter = presenter;
	}

	@Override
	public Context getContext()
	{
		// TODO: Implement this method
		return this;
	}

	@Override
	public View getView()
	{
		return view;
	}

	@Override
	public void onLoad(List<TaskDetail> ts)
	{
		rv= (RecyclerView) findViewById(R.id.rv);
		rv.setItemAnimator(new DefaultItemAnimator());
		LinearLayoutManager lm = new LinearLayoutManager(this);
		lm.setReverseLayout(true);
		
		rv.setLayoutManager(lm);
		TaskDetailAdapter adapter = new TaskDetailAdapter(this,ts);
		rv.setAdapter(adapter);
		adapter.mOnAddListener = new TaskDetailAdapter.OnAddListener(){

			@Override
			public void onClick(View view)
			{
				presenter.addTaskDetail(mTask);
			}
		};
	}
	

	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		view  = LayoutInflater.from(this).inflate(R.layout.view_task_detail,null);
		setContentView(view);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
			 mTask = new Gson()            // get Task from intent;
				.fromJson(b
						  .get(TaskDetailActivity.TASK).toString()
						  ,Task.class);  

			presenter = new TaskDetailPresenter(this,mTask);
			 
			presenter.start();
			mEditView = new EditViewWithoutDialog(this,getView());
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

	@Override
	public void onAddTaskDetail(final Task task)
	{
		final String taskId = mTask.id;
		final View av = LayoutInflater.from(getContext()).inflate(R.layout.view_add_task_edit,null);
		final EditText etTitle = (EditText) av.findViewById(R.id.et_title);
		etTitle.setText(TimeUtils.fromat(System.currentTimeMillis()+""));
		AlertDialog d = new AlertDialog.Builder(this)
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


	
	
	
	
	
}
