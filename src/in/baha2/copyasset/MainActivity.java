package in.baha2.copyasset;

import java.io.File;
import java.util.ArrayList;

import android.os.Bundle;
import android.os.Environment;
import android.app.Activity;
import android.view.Menu;
import android.widget.Toast;

public class MainActivity extends Activity {
	// this where your file will be stored in sdcard in this case in folder (YOUR_FILE)
	static String BASE_FILE = Environment.getExternalStorageDirectory()+"/MYFile/";
	ArrayList<String> TmpList = new ArrayList<String>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // create dynamic array to add your files
        ArrayList<String> MyFiles = new ArrayList<String>();
        MyFiles.add("My.mp4");
        MyFiles.add("My.png");
        // add your files here
      
        // create base folder if not exists
        File f = new File(BASE_FILE);
		if(!f.exists())
			f.mkdir();
        // this loop to check if files already coped or not  or any file delete
        for(int i=0;i<MyFiles.size();i++){
        	File check = new File(BASE_FILE,MyFiles.get(i));
        	if(!check.exists())
        		TmpList.add(MyFiles.get(i)); // copy not coped items to other list 
        }
        // now check if not all files copy or something remove
        if(TmpList.size()>0)
        	new AsyncCopy(this, BASE_FILE, TmpList).execute("");
        else
        	Toast.makeText(getApplicationContext(), "all files coped ",Toast.LENGTH_LONG).show();
        	
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
    
}
