package xyz.kfdykme.kat.kat.utils;
import android.os.*;
import android.util.*;
import java.io.*;
import java.util.*;

public class FileUtils
{
	public static final String baseDir ="/Kat/";

	private static Throwable FileNotFoundException;
	
	public static void createFile(String reDir,String name,String content) throws IOException {
		File root = Environment.getExternalStorageDirectory();
		File reFile = new File(root.getCanonicalPath()+baseDir+reDir);
		if(!reFile.exists())
			reFile.mkdirs();
		File tFile = new File(reFile.getCanonicalPath()+"/"+name);
		if(tFile.exists())
			tFile.delete();
		RandomAccessFile raf = new RandomAccessFile(tFile,"rw");
		raf.write(content.getBytes());
		raf.close();
		Log.i("FileUtils","create or edit file : "+name+"/"+content);
	}
	
	public static String readFile( String reDir,String name,String fileType) throws IOException, Throwable {
        File root = Environment.getExternalStorageDirectory();

        File dir = new File(root.getCanonicalPath()+baseDir+reDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
        File targetFile = new File(dir.getCanonicalPath()+"/"+name+"."+fileType);

        String content = "";
        if(targetFile.isFile() && targetFile.exists()){

            InputStreamReader read = null;

			read = new InputStreamReader(new FileInputStream(targetFile),"utf8");

            BufferedReader bufferedReader = new BufferedReader(read);
			String line = null;

			while((line = bufferedReader.readLine()) != null) content+=line;


        }else{
         
			throw FileNotFoundException;
        }
        return content;
    }
	
	public static List<String> readFiles(String reDir) throws IOException{
		List<String> list = new ArrayList();
		
		File root = Environment.getExternalStorageDirectory();

        File dir = new File(root.getCanonicalPath()+baseDir+reDir);
        if(!dir.exists()){
            dir.mkdirs();
        }
		
		String content = "";
		for(File f:dir.listFiles())
		{
			content="";
			Log.i("FileUtil","Find file :"+f.getName());
			InputStreamReader read = null;

			read = new InputStreamReader(new FileInputStream(f),"utf8");

            BufferedReader bufferedReader = new BufferedReader(read);
			String line = null;

			while((line = bufferedReader.readLine()) != null) content+=line;
			list.add(content);
			
		}
		
		return list;
	}
}
