package xyz.kfdykme.kat.kat;

import android.*;
import android.content.*;
import android.os.*;
import android.support.design.widget.*;
import android.support.v4.view.*;
import android.support.v4.widget.*;
import android.support.v7.app.*;
import android.support.v7.widget.*;
import android.view.*;
import com.google.gson.*;
import xyz.kfdykme.kat.kat.*;
import xyz.kfdykme.kat.kat.calendar.CalendarView;
import xyz.kfdykme.kat.kat.taskdetail.*;
import xyz.kfdykme.kat.kat.tasklist.*;
import xyz.kfdykme.kat.kat.utils.*;

import xyz.kfdykme.kat.kat.R;
import android.graphics.*;
import android.view.View.*;
import android.content.pm.PackageManager;
import android.content.pm.PackageInfo;
import android.widget.Toast;
import java.io.*;
import android.widget.EditText;
import android.util.*;
import android.graphics.drawable.*;
import com.bumptech.glide.*;
import android.widget.ImageView;


public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener
{
	
	 
	TaskListAdapter adapter;
	TaskListContract.Presenter mTaskListPresenter;	
	TaskListContract.View mTaskListView;

	int currentPos =0;
	
	ViewGroup view;
	
	Handler mHander = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
		//隐藏状态栏
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		
		setContentView(R.layout.activity_main);
       
		
        
        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
		
		PermissionUtil.initPermission(this,new String[]{								 
										  Manifest.permission.WRITE_EXTERNAL_STORAGE,
										  Manifest.permission.SYSTEM_ALERT_WINDOW										  //Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
									  });

									 
		view = (ViewGroup) findViewById(R.id.content_main);
		
		try{
			File bg = FileUtils.getFile("commen/bg","bg.jpg");
			Drawable draw =Drawable.createFromPath(bg.getAbsolutePath());
			
			DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
			
			drawer.setBackground(draw);
			
		} catch(Throwable e){
			e.printStackTrace();
			
		}
		onNavigationItemSelected(navigationView.getMenu().getItem(0));
		
    }

	
	
	
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
			try{
			mTaskListPresenter.load(Task.TYPE_DESKTOP);
			} catch(Exception e){
				e.printStackTrace();
				mTaskListView.showToat(e.getMessage(),Toast.LENGTH_SHORT);
			}
           
        }
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            selectView(0);
		} else if (id == R.id.nav_gallery) {
			
			selectView(1);
        } else if (id == R.id.nav_slideshow) {
			selectView(2);
			
        } else if (id == R.id.nav_manage) {
			if(mTaskListPresenter!=null)
				mTaskListPresenter.onAddTask(null);
        } else if (id == R.id.nav_share) {
			TaskUtils.outputTaskDetails();
        }
 		else if (id == R.id.nav_send)
 		{
			//TaskUtils.readTaskDetails(true);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
	
	private void selectView(int pos){
		currentPos = pos;
		view.removeAllViews();
		switch(pos){
			case 0:
				mTaskListView = new TaskListView(this);
				mTaskListPresenter = new TaskListPresenter(mTaskListView);
				mTaskListPresenter.start();
				view.addView(mTaskListView.getView(),ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);	
				break;
			case 1:
				view.addView(new CalendarView(this).getView(),ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);						
				break;
			case 2:
				
				break;
		}
	}
	
	

	@Override
	protected void onResume()
	{
		
		selectView(currentPos);
		super.onResume();
	}
	
	
	
}
