package xyz.kfdykme.kat.kat;

import android.app.*;
import android.content.*;
import android.view.*;

public class KatProgressDialog extends Dialog
{
	public KatProgressDialog(Context context){
		super(context);
		setContentView(R.layout.base_view_progress_dialog);
		this.getWindow().getAttributes().gravity = Gravity.CENTER;
		
	}
	
	public void showProgressDialog() {
		if (!isShowing()) {
			setCanceledOnTouchOutside(false);
			setCancelable(true);
			show();
		}
	}
}
