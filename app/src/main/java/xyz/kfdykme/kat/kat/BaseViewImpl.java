package xyz.kfdykme.kat.kat;
import android.content.*;
import android.view.*;
import android.widget.*;

public abstract class BaseViewImpl<E extends BasePresenter> implements BaseView<E> 
{
	
	public Context context;
	
	public KatProgressDialog dialog;
	
	public E presenter;

	public View view;
	
	
	@Override
	public void setPresenter(E presenter)
	{
		this.presenter = presenter;
		
	}

	@Override
	public Context getContext()
	{
		return context;
	}

	@Override
	public View getView()
	{
		return view;
	}

	@Override
	public void showProgressDialig()
	{
		if(dialog== null) dialog = new KatProgressDialog(context);
		dialog.showProgressDialog();
	}

	@Override
	public void showToat(String text, int type)
	{
		Toast.makeText(getContext(),text,type);
	}

	@Override
	public void dissmissProgressDialog()
	{
		dialog.dismiss();
	}
	
}
