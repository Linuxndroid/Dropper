package in.baha2.copyasset;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.widget.Toast;

public class AsyncCopy extends AsyncTask<String, String, String>{
	String savePath;
	Activity ctx;
	private ProgressDialog pDialog;
	ArrayList<String> arr;
	AsyncCopy(Activity _ctx,String _savePath,ArrayList<String> Files){
		this.ctx =  _ctx;
		this.savePath  = _savePath;
		this.arr = Files;
	}
	@Override
    protected void onPreExecute() {
		pDialog = new ProgressDialog(ctx);
        pDialog.setMessage("Copying files from asset to sdcard");
        pDialog.setIndeterminate(true);
        pDialog.setCancelable(false);
        pDialog.show();
        super.onPreExecute();
    }
	@Override
	protected String doInBackground(String... urls) {
		File f = new File(savePath);
		if(!f.exists())
			f.mkdir();
		
		for(int i=0;i<arr.size();i++)
			Copy(arr.get(i));
		
		
		 return null;
	 }
	@Override
	protected void onPostExecute(String unused) {
	       Toast.makeText(ctx,"Copy Done",Toast.LENGTH_LONG).show();
	       pDialog.hide();
	}
	void Copy(String fname){
		try{
			int count;
			InputStream input= ctx.getAssets().open(fname);
			OutputStream output = new FileOutputStream(savePath+"/"+fname);
			byte data[] = new byte[1024];
	        while ((count = input.read(data))>0) {
	        	output.write(data, 0, count);
	        }
	        output.flush();
	        output.close();
	        input.close();        
			}catch(Exception e){
				 // error while copying
			}
	}
}
