package model;

import java.io.*;

public class IO {
  private static PrintWriter fileWrite;
  private static BufferedReader fileRead;

  public static void openWriteFile(String filename)
  {
    try{
      fileWrite = new PrintWriter(new BufferedWriter(new FileWriter(filename, true)));
    }
    catch(IOException e){
      System.out.println("*** Cannot create file: " + filename + " ***");
    }
  }

  public static void write(String text)
  {
    fileWrite.print(text);
  }

  public static void writeln(String text)
  {
    fileWrite.println(text);
  }

  public static void closeWriteFile()
  {
    fileWrite.close();
  }

  public static void openReadFile(String filename)
  {
    try
		{
			fileRead = new BufferedReader(new FileReader(filename));
		}
		catch(FileNotFoundException e)
		{
			System.out.println("***Cannot open " + filename + "***");
		}
  }

  public static String readLine(){
    try{
      return fileRead.readLine();
    }
    catch(IOException e){

    }
    return null;
  }

  public static void closeReadFile(){
    try{
      fileRead.close();
    }
    catch(IOException e){

    }
  }

}
