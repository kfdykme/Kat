package xyz.kfdykme.kat.kat.taskedit;
import java.util.*;
import xyz.kfdykme.kat.kat.*;

public interface EditContract
{
	interface View extends BaseView<EditContract.Presenter>{
		void onLoad(Task map);
		void attach();
		void show();
	}
	
	interface Presenter extends BasePresenter{
		void onLoad(Task map);
		void onSave(Task map);
		void onCancel();
		void onDetail(Task t);
		void showDialog();
		void onDelete(Task t);
	}
}
