package org.kevinpotgieter.musicorganiser;

import java.io.File;
import java.io.IOException;
import java.text.MessageFormat;
import java.util.Collection;
import java.util.List;

import org.apache.commons.io.DirectoryWalker;
import org.apache.commons.io.filefilter.FileFilterUtils;
import org.apache.commons.io.filefilter.IOFileFilter;
import org.farng.mp3.MP3File;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import ch.qos.logback.core.rolling.helper.FileFilterUtil;



public class MusicFolderWalker extends DirectoryWalker{

	private volatile boolean isOperationCancelled = false;
	private static final Logger logger = LoggerFactory.getLogger(MusicFolderWalker.class);
	
	
	private File rootPath = null;
	private List<String> fileFilters = null;
	
	public MusicFolderWalker(File rootPath, List<String> fileFilters){
		this.rootPath = rootPath;
		this.fileFilters = fileFilters;
	}
	
	public void getMp3Info(){
		
		IOFileFilter ioFileFilters = null;
		for(String filterString : this.fileFilters){
			if(ioFileFilters == null){
				ioFileFilters = FileFilterUtils.suffixFileFilter(filterString);
			}
			else{
				ioFileFilters = FileFilterUtils.andFileFilter(ioFileFilters, FileFilterUtils.suffixFileFilter(filterString));
			}
		}
		
		
		try {
			walk(rootPath, fileFilters);
		} catch (IOException e) {
			logger.error("Error Walking Directory.", e);
		}

	}
	
	
	@Override
	protected void handleFile(File file, int depth, Collection results)	throws IOException {
		super.handleFile(file, depth, results);
		logger.debug(MessageFormat.format("Handling File: [{0}] Directory Depth: [{1}]", file.getAbsolutePath(), depth));
		MusicFileDestinationResolver resolver = new MusicFileDestinationResolver(file);
	}
	
	
	public void cancelOperation(){
		this.isOperationCancelled = true;
	}
	
	@Override
	protected boolean handleIsCancelled(File file, int depth, Collection results) throws IOException {
		return super.handleIsCancelled(file, depth, results) && this.isOperationCancelled;
	}
	
	@Override
	protected void handleCancelled(File startDirectory, Collection results,
			CancelException cancel) throws IOException {
		super.handleCancelled(startDirectory, results, cancel);
		
		logger.debug("Music Folder Walker operation has been successfully cancelled.");
	}
	
	
	
}
