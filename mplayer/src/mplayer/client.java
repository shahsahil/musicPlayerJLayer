package mplayer;

public class client {
	static CustomPlayer p;
	
	public static void main(String args[]){
		p = new CustomPlayer();
		linkedList music = new linkedList();
		music.add("Demons.mp3");
		music.add("21 Guns.mp3");
		music.add("Firework.mp3");
		music.add("Radioactive.mp3");
		music.add("The Nights.mp3");
		music.add("Sky Full Of Stars.mp3");
		music.add("Aashaayen.mp3");
		music.add("Aye khuda.mp3");
		music.add("Galliyan.mp3");
		music.add("Iktara.mp3");
		music.add("Jaane kyun.mp3");
		//System.out.println(music);
		makeui m = new makeui(p, music);
		m.makeButton();
	}
}
