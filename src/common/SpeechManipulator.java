package common;

import java.io.File;
import java.util.concurrent.TimeUnit;

import voce.SpeechInterface;

public class SpeechManipulator {
	
	private String vocePath = "C:/Users/arari/Documents/Files/Code/Java/Speech Manipulation/voce-0.9.1/lib";
	
	public SpeechManipulator(){
		new SpeechInterface();
		String grammarPath = "";
		try {//used to get proper file path (throws errors otherwise...)
		    grammarPath = new File("C:/Users/arari/Documents/GitHub/Common/lib/grammar").toURI().toURL().toString();
		} catch (Exception e) {
		    e.printStackTrace();
		}
		System.out.println(grammarPath);
		SpeechInterface.init(vocePath, true, true, grammarPath, "faol");
	}
	
	public void userSpeak(){
		boolean end = false;
		// Speech recognition in Java
		while(!end){
			
			// Normally, applications would do application-specific things 
			// here.  For this sample, we'll just sleep for a little bit.
			try
			{
				Thread.sleep(200);
			}
			catch (InterruptedException e)
			{
			}
			
			while (voce.SpeechInterface.getRecognizerQueueSize() > 0)
			{
			      String s = "you said " + voce.SpeechInterface.popRecognizedString();

			      if(s.equalsIgnoreCase("end"))
			    	  System.exit(0);
			      System.out.println(s);
			      userListen(s);
			      System.out.println("looping");
			}
		}
	}
	
	public void userListen(String input){
		// Speech synthesis in Java
		voce.SpeechInterface.synthesize(input);
	}
	
	public static void main(String[] args){
		SpeechManipulator speechManip = new SpeechManipulator();
		SpeechInterface.setRecognizerEnabled(true);
		System.out.println("Saying \"Hello World\"");
		speechManip.userListen("Hello World");
		System.out.println("Said \"Hello World\"");
		try {
			TimeUnit.SECONDS.sleep(5);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println("Start speaking...");
		speechManip.userSpeak();
		System.out.println("Stopped speaking...");
	}
	
}
