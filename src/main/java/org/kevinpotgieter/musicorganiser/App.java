package org.kevinpotgieter.musicorganiser;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
    	File file = new File("/home/kevin/temp");
    	List<String> fileFilters = new ArrayList<String>();
    	fileFilters.add("*.mp3");
        MusicFolderWalker w = new MusicFolderWalker(file, fileFilters);
        
        w.getMp3Info();
    }
}
