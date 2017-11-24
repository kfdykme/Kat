package xyz.kfdykme.kat.kat.taskedit;
import java.util.*;
import android.view.*;
import android.content.*;
import android.text.*;
import xyz.kfdykme.kat.kat.*;
import android.widget.*;
import android.support.v7.widget.*;
import android.graphics.*;
import android.app.*;
import android.view.View.*;


public class EditView implements EditContract.View
{
	public EditContract.Presenter mPresenter;

	public Task task;
	
	
	
	Context context;

	
	
	public View view;
	public CardView cv;
	public TextView tv;
	public Button btCc;
	public int bgc;
	public int tc;
	public EditText et;
	
	public AlertDialog dialog;
	
	
	public EditView(Context context){
		this.context=context;
		view = LayoutInflater.from(context).inflate(R.layout.view_task_edit,null);
		cv = (CardView) view.findViewById(R.id.cv);
		tv = (TextView) view.findViewById(R.id.tv);
		et = (EditText) view.findViewById(R.id.et);
		btCc = (Button) view.findViewById(R.id.bt_cc);
		
		btCc.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					reflashColors();
				}
			});		
		et.addTextChangedListener(new TextWatcher(){

				@Override
				public void beforeTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
					// TODO: Implement this method
				}

				@Override
				public void onTextChanged(CharSequence p1, int p2, int p3, int p4)
				{
					reflash(p1.toString());
				}

				@Override
				public void afterTextChanged(Editable p1)
				{
					// TODO: Implement this method
				}
			});
		bgc = Color.rgb(getrand(),getrand(),getrand());
		tc = Color.rgb(getrand(),getrand(),getrand());
		cv.setBackgroundColor(bgc);
		tv.setTextColor(tc);
		dialog = new AlertDialog.Builder(context)
			.setView(view)
			.setNegativeButton("Cancel", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					mPresenter.onCancel();
				}
			})
			.setPositiveButton("Save", new DialogInterface.OnClickListener(){

				@Override
				public void onClick(DialogInterface p1, int p2)
				{
					mPresenter.onSave(task);
				}

			})
			.setOnDismissListener(new DialogInterface.OnDismissListener(){

				@Override
				public void onDismiss(DialogInterface p1)
				{
					
					mPresenter = null;
				}
			})
			.create();
	}
	private int getrand(){
		return MathUtil.rand(100,250);
	}

	@Override
	public void onLoad(Task task)
	{
		this.task = task;
		bgc = task.bgc;
		tc = task.tc;
		cv.setBackgroundColor(bgc);
		tv.setTextColor(tc);
		tv.setText(task.text);
		et.setText(task.text);
	}
	
	@Override
	public void show(){
		dialog.show();
	}
	
	private void reflash(String s){
		tv.setText(s);
		if(task ==null)
		{
			task = new Task();
			final String id= System.currentTimeMillis()+""; 
			task.id = id;
		}
		task.text  = et.getText().toString();
		task.bgc = bgc;
		task.tc  = tc;
	}
	
	private void reflashColors(){

		bgc = Color.rgb(getrand(),getrand(),getrand());
		tc = Color.rgb(getrand(),getrand(),getrand());
		cv.setBackgroundColor(bgc);
		tv.setTextColor(tc);
		task.bgc = bgc;
		task.tc = tc;
	}

	@Override
	public View getView()
	{
		return view;
	}

	@Override
	public Context getContext()
	{
		return context;
	}

	@Override
	public void setPresenter(EditContract.Presenter presenter)
	{
		mPresenter=  presenter;
	}
	
	
}
