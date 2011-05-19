package org.kevinpotgieter.musicorganiser;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        FolderWalker w = new FolderWalker();
        w.getMp3Info();
    }
}
