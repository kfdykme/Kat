package xyz.kfdykme.kat.kat.taskdetail;
import java.util.*;
import xyz.kfdykme.kat.kat.*;


public class TaskDetailContract
{
	public interface View extends BaseView<Presenter>{
		void onLoad(List<TaskDetail> ts);
		void onAddTaskDetail(Task task);

		Task mTask;
		public static final String TASK = "task";
	}
	
	public interface Presenter extends BasePresenter{
		void onSave()
		void onCancel();
		void load(Task task);
		void addTaskDetail(Task task);
	}
}
