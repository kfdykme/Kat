package xyz.kfdykme.kat.kat.taskedit;

import android.content.*;
import android.graphics.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.text.*;
import android.view.*;
import android.view.View.*;
import android.widget.*;
import xyz.kfdykme.kat.kat.*;

public class EditViewWithoutDialog extends EditView implements EditContract.View
{ 
	public Button btCancel;
	public Button btSave;
 

	public EditViewWithoutDialog(Context context,View view){
		super(context);
		this.view = view;
		btCancel = (Button) view.findViewById(R.id.bt_cancel);
		btSave = (Button) view.findViewById(R.id.bt_save);
		btCancel.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					mPresenter.onCancel();
					
				}
			});
		btSave.setOnClickListener(new OnClickListener(){

				@Override
				public void onClick(View p1)
				{
					mPresenter.onSave(task);
				}

			});
			 
		 
	} 
	@Override
	public void onLoad(Task task)
	{
		initView(view);
		this.task = task;
		bgc = task.bgc;
		tc = task.tc;
		cv.setBackgroundColor(bgc);
		tv.setTextColor(tc);
		tv.setText(task.text);
		et.setText(task.text);
		btDetail.setVisibility(View.GONE);
	}

	@Override
	public void show(){ 
	}
 
}
