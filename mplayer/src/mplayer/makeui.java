package mplayer;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;  


public class makeui{
	JButton pl, pau, st, srch, lp, rew, forw, addf, pall;
	CustomPlayer p;
	linkedList music;
	JTextField text;
	JList l;
	JList<String> fav;
	DefaultListModel<String> model;
	String t;
	JProgressBar progressBar;
	CQueue q;
	int f;
	
	public makeui(CustomPlayer p, linkedList music){
		this.p=p;
		this.music = music;
		q = new CQueue(music.sizeOf());
	}
	public void makeButton(){
		JFrame f=new JFrame();//creating instance of JFrame  
		
		//Play and pause buttons
		pl=new JButton("Play");//creating instance of JButton  
		pl.setBounds(130,100,100, 40);//x axis, y axis, width, height  
		pau=new JButton("Pause");
		pau.setBounds(260,100,100, 40);
		st=new JButton("Stop");
		st.setBounds(390,100,100, 40);
		
		
		//Rewind and Forward Buttons
		rew=new JButton("Prev");//creating instance of JButton  
		rew.setBounds(50,100,60, 40);//x axis, y axis, width, height  
		forw=new JButton("Next");
		forw.setBounds(520,100,60, 40);
		
		
		
		//Text Box and search
		text = new JTextField(20);
		text.setBounds(130, 40, 120, 40);
		text.setSize(200,20);
		srch = new JButton("Search");
		srch.setBounds(390,10,100, 40);
		
		
		//progress bar
		progressBar = new JProgressBar();
		progressBar.setMinimum( 0 );
		progressBar.setMaximum( 100000 );
		progressBar.setValue(0);
		progressBar.setBounds(240, 250, 200, 20);
		//progressBar.setSize(200,20);
		progressBar.setStringPainted(true);
		
		//Display a list of all the songs
		l = new JList(music.toArray());
		l.setBounds(130, 300, 100, 60);
		l.setSize(100, 200);
		/*lp=new JButton("Pl");//creating instance of JButton  
		lp.setBounds(300,420,100, 40);*/
		
		//add to fav
		addf = new JButton("Add to Fav");
		addf.setBounds(300, 420, 100, 40);
		model = new DefaultListModel<>();
		fav = new JList(model);
		fav.setBounds(500, 300, 100, 60);
		fav.setSize(100, 200);
		pall = new JButton("Play All");
		pall.setBounds(500, 500, 100, 40);
		JLabel label = new JLabel("Favorites",JLabel.CENTER);
		label.setSize(100,40);
		label.setLocation(500,250);
		
		//Add all to the frame
		f.add(srch);
		f.add(text);
		f.add(label);
		f.add(pl);
		f.add(pau);
		f.add(st);
		f.add(l);
		f.add(addf);
		f.add(rew);
		f.add(forw);
		f.add(addf);
		f.add(fav);
		f.add(pall);
		f.add(progressBar);
		f.setSize(1000,800);
		f.setLayout(null);//using no layout managers  
		f.setVisible(true);//making the frame visible
		Listen l=new Listen();
		srch.addActionListener(l);
		addf.addActionListener(l);
		rew.addActionListener(l);
		forw.addActionListener(l);
		pl.addActionListener(l);
		pau.addActionListener(l);
		st.addActionListener(l);
		pall.addActionListener(l);
		
		f.addWindowListener(new java.awt.event.WindowAdapter() {
		    @Override
		    public void windowClosing(java.awt.event.WindowEvent windowEvent) {
		    	p.pause();
		        if (JOptionPane.showConfirmDialog(f, 
		            "Are you sure to close this window?", "Really Closing?", 
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE) == JOptionPane.YES_OPTION){
		            System.exit(0);
		        }
		    }
		});
	}

	int flagpro=1, flagfav=1;
	class Listen implements ActionListener
	{
		Thread r;
		Thread thr = new Thread(){};
		int count=0;
		public void actionPerformed(ActionEvent e) { 
			if(e.getSource() ==pl){
				if(thr.isAlive()){ // or thr.getState()==Thread.State.RUNNABLE
					count=1;
				}
				else if(p.getPath() != l.getSelectedValue()){
					if(count!=0)
						p.stop();
					p.setPath(l.getSelectedValue().toString());
					//count=0;
				}
				if(p.getavailable()==p.gettotal())
					count=0;
				flagpro=1;
				flagfav=1;
				if(count==0){ 
					p.play(-1);
					count++;
					}
				else
					p.resume();
				//for the progress bar
				new Thread(){
					@Override
					public void run(){
						System.out.println(flagpro);
						while( p.getavailable()!=0 &&flagpro==1)	{
							int tem =p.gettotal();
							//System.out.println(((p.gettotal()-p.getavailable())/p.gettotal()));
							//System.out.println(((tem-p.getavailable())/tem)*100000);
							double a = p.gettotal()-p.getavailable();
							//System.out.println(a);
							double aa=a/p.gettotal();
							//System.out.println(aa);
							double aaa=aa*100000;
							Double d = new Double(aaa);
							int aaaa = d.intValue();
							System.out.println(aaaa);
							progressBar.setValue(aaaa);
							try {
								Thread.sleep(100);
							} catch (InterruptedException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}	
						}
					}
				}.start();
			}
			else if(e.getSource() ==pau){
				/*System.out.println(thr.getState());
				if(thr.isAlive()){
					p.pause();
				}*/
				if(thr.isAlive()){
					p.pause();
					flagfav=0;
				}
				
				flagpro=0;
				p.pause();
			}
			else if(e.getSource() ==st){
				p.stop();
				flagpro=4;
				progressBar.setValue(0);
				count=0;
			}
			else if(e.getSource()==srch){
				t= text.getText();
				String mus = music.search(t);
				if(mus!="NF"){
					p.setPath(mus);
					l.setSelectedValue(mus, true);
				}
				else
					System.out.println("not found");
				//System.out.println(t);
			}
			/*else if(e.getSource()==lp){
				p.pause();
				p.setPath(l.getSelectedValue().toString());
				p.play(-1);
			}*/
			else if(e.getSource()==rew){
				p.stop();
				String temp = p.getPath();
				System.out.println(temp);
				node nod = music.searchN(temp);
				node pre = nod.getPrev();
				p.setPath(pre.getData());
				progressBar.setValue(0);
				p.play(-1);
				l.setSelectedValue(pre.getData(), true);
			}
			else if(e.getSource()==forw){
				if(thr.isAlive()){
					p.pause();
				}
				else{
				p.stop();
				progressBar.setValue(0);
				String temp = p.getPath();
				System.out.println(temp);
				node nod = music.searchN(temp);
				node nex = nod.getNext();
				p.setPath(nex.getData());
				p.play(-1);
				l.setSelectedValue(nex.getData(), true);
				}
			}
			else if(e.getSource()==addf){
				String temp = l.getSelectedValue().toString();
				q.insert(temp);
				System.out.println(temp);
				model.addElement(temp);
			}
			else if(e.getSource()==pall){
				//q.f = fav.getSelectedIndex();
				//String[] playl = q.playall();
				thr = new Thread(){
					@Override
					public void run(){
						for(int i =0; i<15; i++){
							String x= q.getplay();
							System.out.print(x);
							p.setPath(x);
							if(flagfav==1){
							p.play(-1);
							l.setSelectedValue(x, true);
							}
							else{
								while(flagfav==0)
									try {
										Thread.sleep(100);
									} catch (InterruptedException e) {
										// TODO Auto-generated catch block
										e.printStackTrace();
									}
							}
							f=1;
							try {
									while(p.getavailable()!=0 && flagfav == 1)
										Thread.sleep(100);
									if(p.getavailable()==0){}
							} 
							catch (InterruptedException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						}
					}
				};
				thr.start();	
			}
		}
	}
}