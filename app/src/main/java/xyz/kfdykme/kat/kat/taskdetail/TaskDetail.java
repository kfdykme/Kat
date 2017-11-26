package xyz.kfdykme.kat.kat.taskdetail;
import com.orm.SugarRecord;
import com.orm.dsl.*;

public class TaskDetail  extends SugarRecord
{
	
	
	String id;
	String taskId;
	
	@Column(name = "taskName")
    String taskName;
	
	@Column(name = "content")
    String item;
	
	@Column(name = "title")
	String title;
	
	@Column(name = "itemType")
    int itemType;
	
	public final static int LARGE =0;
	
	public final static int MEDIUM = 1;
	
	public final static int NORMAL = 2;
	
	
	
	public TaskDetail(){};
}
