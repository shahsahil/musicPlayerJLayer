package mplayer;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;

import javax.swing.JOptionPane;

import javazoom.jl.player.Player;


public class CustomPlayer {

private Player player;
private FileInputStream FIS;
private BufferedInputStream BIS;
private boolean canResume, canStop;
private String path;
private int total;
private int stopped;
private boolean valid;

public CustomPlayer(){
    player = null;
    FIS = null;
    valid = false;
    BIS = null;
    path = null;
    total = 0;
    stopped = 0;
    canResume = false;
}

public boolean canResume(){
    return canResume;
}

public void setPath(String path){
    this.path = path;
}
public String getPath(){
	return path;
}
public int getavailable(){
	try {
		if(player!=null)
			return FIS.available();
		else
			return 0;
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
}
public int gettotal(){
	try {
		if(player!=null)
			return total;
		else
			return 0;
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return 0;
}



public void pause(){
    try{
    stopped = FIS.available();
    player.close();
    FIS = null;
    BIS = null;
    player = null;
    if(valid) canResume = true;
    canStop = false;
    }catch(Exception e){

    }
}
public void stop(){
	try{
		stopped=0;
		if(canStop == true){
			player.close();
			canStop = false;
		}
		if(valid) canResume = false;
		FIS = null;
	    BIS = null;
	    player = null;
	    
	}
	catch(Exception e){
		e.printStackTrace();
	}
}
public void resume(){
    if(!canResume) return;
    if(play(total-stopped)) canResume = false;
}

public boolean play(int pos){
    valid = true;
    canStop = true;
    canResume = false;
    try{
	    FIS = new FileInputStream(path);
	    total = FIS.available();
	    if(pos > -1) FIS.skip(pos);
	    BIS = new BufferedInputStream(FIS);
	    player = new Player(BIS);
	    new Thread(
	    		new Runnable(){
	                public void run(){
	                    try{
	                        player.play();
	                        
	                    }catch(Exception e){
	                        JOptionPane.showMessageDialog(null, "Error playing mp3 file");
	                        valid = false;
	                        
	                    }
	                }
	            }
	    ).start();
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "Error playing mp3 file");
        valid = false;
    }
    return valid;
}

}