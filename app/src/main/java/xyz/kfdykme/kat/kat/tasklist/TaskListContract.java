package xyz.kfdykme.kat.kat.tasklist;
import xyz.kfdykme.kat.kat.*;
import java.util.*;

public interface TaskListContract
{
	interface View extends BaseView<Presenter>{
		void onLoad();
		void onReflash();
	}
	
	interface Presenter extends BasePresenter{
		void onEditTask(Task t);
		void onAddTask(Task t);
		void onDeleteTask(Task t);
		void onReflash(Task t); 
	
	}
}
