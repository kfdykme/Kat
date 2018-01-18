package xyz.kfdykme.kat.kat.taskdetail;
import android.os.*;
import android.support.v7.app.*;
import com.google.gson.*;
import xyz.kfdykme.kat.kat.*;
import android.widget.*;
import java.util.*;
import android.content.*;
import android.view.*;
import android.support.v7.widget.Toolbar;
import xyz.kfdykme.kat.kat.taskedit.*;
import xyz.kfdykme.kat.kat.utils.TimeUtils;

public class TaskDetailActivity extends AppCompatActivity
{

	
	TaskDetailListView mView;
	
	public static final String TASK = "task";

	private TaskDetailListPresenter presenter;

	private EditPresenter mEditPresenter;

	private EditViewWithoutDialog mEditView;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		//setSupportActionBar((Toolbar)findViewById(R.id.toolBar));
		super.onCreate(savedInstanceState);
		
		Bundle b = this.getIntent().getExtras();
		if(b!=null){
			Task mTask = new Gson()            // get Task from intent;
				.fromJson(b
						  .get(TaskDetailActivity.TASK).toString()
						  ,Task.class);  

			mView = new TaskDetailListView(this,mTask);
			
			presenter = new TaskDetailListPresenter(mView,mTask);
			setContentView(mView.getView());
			mView.setPresenter(presenter);
			presenter.start();
			
			
			
			
		}
	}



	
	
	
	
	
}
