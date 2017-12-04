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


public class MainActivity extends AppCompatActivity
implements NavigationView.OnNavigationItemSelectedListener
{
	
	 
	TaskListAdapter adapter;
	TaskListContract.Presenter mTaskListPresenter;	
	TaskListContract.View mTaskListView;

	int currentPos =0;
	
	EditText et ;
	ViewGroup view;
	
	Handler mHander = new Handler();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

		
		
		et = (EditText) findViewById(R.id.et);
       	et.setOnKeyListener(new OnKeyListener(){

				@Override
				public boolean onKey(View p1, int p2, KeyEvent p3)
				{
					if(p3.getKeyCode() == KeyEvent.KEYCODE_ENTER){
						mTaskListPresenter.search(et.getText().toString());
						et.setText("");
					
					}
					//Toast.makeText(getApplicationContext(),p2+"/"+p3,Toast.LENGTH_SHORT).show();
					// TODO: Implement this method
					return false;
				}
			});
			
		et.setVisibility(View.GONE);		
		
		FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

				if(mTaskListPresenter!=null)
					mTaskListPresenter.onAddTask(null);
			}
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
		
		PermissionUtil.initPermission(this,new String[]{								 
										  Manifest.permission.WRITE_EXTERNAL_STORAGE,
										  Manifest.permission.SYSTEM_ALERT_WINDOW										  //Manifest.permission.MOUNT_UNMOUNT_FILESYSTEMS,
									  });

									 
		view = (ViewGroup) findViewById(R.id.content_main);
		
		onNavigationItemSelected(navigationView.getMenu().getItem(0));
    }

	
	
	
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
           // super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_app) {
			mTaskListPresenter.load(Task.TYPE_APP);
			//mTaskListPresenter.search(et.getText().toString());
			//Toast.makeText(this,et.getText().toString(),Toast.LENGTH_SHORT).show();
            return true;
        } else if(id == R.id.action_desktop){
			mTaskListPresenter.load(Task.TYPE_DESKTOP);
			return true;
		} else if (id == R.id.action_task){
			mTaskListPresenter.load(Task.TYPE_TASK);
			return true;
		}

        return super.onOptionsItemSelected(item);
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
			selectView(3);
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
