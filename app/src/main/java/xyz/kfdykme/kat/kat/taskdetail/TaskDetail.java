package xyz.kfdykme.kat.kat.taskdetail;
import com.orm.SugarRecord;
import com.orm.dsl.*;

public class TaskDetail  extends SugarRecord
{
	
	
	public String createTime;
	public String taskId;
	
	@Column(name = "taskName")
    public String taskName;
	
	@Column(name = "content")
    public String item;
	
	@Column(name = "title")
	public  String title;
	
	@Column(name = "itemType")
    public  int itemType;
	
	public final static int LARGE =0;
	
	public final static int MEDIUM = 1;
	
	public final static int NORMAL = 2;
	
	public final static int NORMAL_TASK=3;
	
	
	
	public TaskDetail()
	{}

	public void setCreateTime(String createTime)
	{
		this.createTime = createTime;
	}

	public String getCreateTime()
	{
		return createTime;
	}

	public void setTaskId(String taskId)
	{
		this.taskId = taskId;
	}

	public String getTaskId()
	{
		return taskId;
	}

	public void setTaskName(String taskName)
	{
		this.taskName = taskName;
	}

	public String getTaskName()
	{
		return taskName;
	}

	public void setItem(String item)
	{
		this.item = item;
	}

	public String getItem()
	{
		return item;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getTitle()
	{
		return title;
	}

	public void setItemType(int itemType)
	{
		this.itemType = itemType;
	}

	public int getItemType()
	{
		return itemType;
	};
}
