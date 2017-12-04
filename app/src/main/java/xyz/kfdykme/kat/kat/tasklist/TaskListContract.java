package xyz.kfdykme.kat.kat.tasklist;
import xyz.kfdykme.kat.kat.*;
import java.util.*;
import android.support.v7.widget.*;

public interface TaskListContract
{
	interface View extends BaseView<Presenter>{
		void onLoad(List<Task> list);
		void onReflash();
		List<Task> getTasks();
		void scrollTo(int pos);
		void setLayout(RecyclerView.LayoutManager lm);
	}
	
	interface Presenter extends BasePresenter{
		void onEditTask(Task t);
		void onAddTask(Task t);
		void onDeleteTask(Task t);
		void onReflash(Task t); 
		void onItemClick(Task t,int p);
		void search(String key);
		void load(String s);
		void onItemLongClick(Task t,int p);
	}
}
