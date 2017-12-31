package xyz.kfdykme.kat.kat.taskdetail;
import java.util.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.taskedit.*;


public class TaskDetailListContract
{
	public interface View extends BaseView<Presenter>{
		void onLoad(List<TaskDetail> ts,List<String> titles);
		void onAddTaskDetail(Task task);
        void onSelectSpinner(String s);
		EditPresenter getEditPresenter();
		Task mTask;
		public static final String TASK = "task";
	}
	
	public interface Presenter extends BasePresenter{
		void onSave()
		void onCancel();
		void load(Task task);
		void addTaskDetail(Task task);
		void onSelectSpinner(String s);
		void save(String title,String content);
	}
}
