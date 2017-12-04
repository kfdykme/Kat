package xyz.kfdykme.kat.kat;
import android.content.*;
import android.view.*;

public interface BaseView<P extends BasePresenter>
{
	void setPresenter(P presenter);
	Context getContext();
	View getView();
	abstract void showProgressDialig();
	abstract void dissmissProgressDialog();
	void showToat(String text,int type)
}
