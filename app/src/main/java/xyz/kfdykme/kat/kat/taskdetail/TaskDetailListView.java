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
import android.view.View.*;
import android.widget.AdapterView.*;

public class TaskDetailListView extends BaseViewImpl<TaskDetailListContract.Presenter> implements TaskDetailListContract.View
{


	public EditViewWithoutDialog mEditView;
	public EditPresenter mEditPresenter;
	View av = null;
	EditText etTitle = null;// = (EditText) av.findViewById(R.id.et_title);
	AlertDialog d ;
	
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
		if(d ==null){
		d= new AlertDialog.Builder(getContext())
			.setView(av)
			
			.setPositiveButton("Save", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					
					EditText etContent = (EditText) av.findViewById(R.id.et_content);
					
					String title = etTitle.getText().toString();
					String content = etContent.getText().toString();
					etContent.setText("");
					presenter.save(title,content);
					
				}
			})
			
			.create();
		d.setCanceledOnTouchOutside(false);
		}
		d.show();
	}
	@Override
	public void onLoad(List<TaskDetail> ts,List<String> titles)
	{
		
		
		av = LayoutInflater.from(getContext()).inflate(R.layout.view_add_task_edit,null);
		final String ct = "current time";
	    etTitle = (EditText) av.findViewById(R.id.et_title);

		
		ArrayAdapter<String> sadapter=new ArrayAdapter<String>(getContext(), android.R.layout.simple_spinner_item,titles);

		sadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); 



		Spinner spinner = (Spinner) av.findViewById(R.id.spinner);
		spinner.setAdapter(sadapter);


		spinner.setOnItemSelectedListener(new OnItemSelectedListener(){

				@Override
				public void onItemSelected(AdapterView<?> p1, View p2, int p3, long p4)
				{

					
					String title = (String) p1.getAdapter().getItem((p3));
					
					presenter.onSelectSpinner(title);
					
				}

				@Override
				public void onNothingSelected(AdapterView<?> p1)
				{
					
				}
			});
		
		
		rv= (RecyclerView) view.findViewById(R.id.rv);
		rv.setItemAnimator(new DefaultItemAnimator());
		LinearLayoutManager lm = new LinearLayoutManager(getContext());
		//lm.setReverseLayout(true);

		rv.setLayoutManager(lm);
		TaskDetailListAdapter adapter = new TaskDetailListAdapter(getContext(),ts);
		rv.setAdapter(adapter);
		View vAddnote = view.findViewById(R.id.v_addnote);
		vAddnote.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					presenter.addTaskDetail(mTask);
				}
			});
		
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

	@Override
	public void onSelectSpinner(String s)
	{
		etTitle.setText(s);
	}

	@Override
	public EditPresenter getEditPresenter()
	{
		// TODO: Implement this method
		return mEditPresenter;
	}


	
	

	
	
	
	
}
