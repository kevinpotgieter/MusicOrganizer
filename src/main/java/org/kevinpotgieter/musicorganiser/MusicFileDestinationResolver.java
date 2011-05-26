package org.kevinpotgieter.musicorganiser;

import java.io.File;

/**
 * This class is responsoble for working out what the destination folder and file name will be, based on info in the MP3 info tag,
 * and if there's nothing there, then it will resort to finding the format #Artist# - #SongName#.mp3 approach. Failing that, it will 
 * dump it in an unresolvable bin 
 * 
 * @author kevin
 *
 */
public class MusicFileDestinationResolver {
	
	private File sourceFile;
	
	public MusicFileDestinationResolver(File sourceFile){
		this.sourceFile = sourceFile;
	}
}
