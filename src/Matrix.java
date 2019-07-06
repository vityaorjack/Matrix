import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.*;
public class Matrix {
	public static void main(String[] args) {
		MyFrame frame = new MyFrame();
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setTitle("Матрикс");			
		frame.show();
}}
class MyFrame extends JFrame{	
	public MyFrame() {
		setSize(1280,730);		
		Container pane = getContentPane();
		pane.add(new MyPanel());		
}}
class MyPanel extends JPanel{
	boolean run,left,right,back;	
	Player Players=new Player();
	Way way =new Way();
	ArrayList <Tomato> tomatos=new ArrayList<Tomato>();
	int map;
	Timer timer =new Timer(16,new Driver());
	
	
	 
	
	MyPanel(){		
		addKeyListener(new MyKey());
		setFocusable(true);
		timer.start();	
		//тест помидора
		tomatos.add(new Tomato(650,250,1,1, way));
		
	}
	public void paintComponent(Graphics g){
		super.paintComponent(g);			
		setBackground(new Color(160,220,255));		
		g.setColor(Color.gray);g.fillPolygon(way.x, way.y, 4);//дорога
		g.setColor(Color.yellow);g.fillOval(50, 50, 50, 50);//солнце		
				
		g.setColor(Color.red);for(Tomato tomato:tomatos){ g.fillOval((int) (tomato.x+map),(int)(tomato.y),(int)(tomato.width),(int)(tomato.height)); }
		g.setColor(Color.blue);g.fillRoundRect(640, 530, 50, 150,20,20);//игрок-колесо
	}	
	//int random(){return (int)(Math.random()*500);}
	
	
	
	class Driver implements ActionListener{
		
		public void actionPerformed(ActionEvent arg0) {
			repaint();			
			if(left){way.left();     for(Tomato tomato:tomatos)tomato.move(10); }
			if(right){way.right();   for(Tomato tomato:tomatos)tomato.move(-10);}
			//помидоры
			for(Tomato tomato:tomatos){
				tomato.run();
				if(tomato.y>800){ tomatos.remove(tomato); tomatos.add(new Tomato(650,250,1,1, way));}
				
			}
			
			
			
		}		
	}
	
	
	
	private class MyKey implements KeyListener
	{
		
		public void keyPressed(KeyEvent arg0) {
			//System.out.println("P="+arg0.getKeyCode());
			switch(arg0.getKeyCode()){
			case 87:{run=true;break;}
			case 65:{left=true;break;}
			case 68:{right=true;break;}
			case 83:{back=true;break;}
			}}
		public void keyReleased(KeyEvent arg0) {
			switch(arg0.getKeyCode()){
			case 87:{run=false;break;}
			case 65:{left=false;break;}
			case 68:{right=false;break;}
			case 83:{back=false;break;}
			}}		
		public void keyTyped(KeyEvent arg0) {
			//System.out.println("T="+arg0.getKeyChar());			
		}
}}

class Player{
	int x=600,y=700;
	//Rectangle rectangle; 
	/*Player(int y){
		this.y=y;		
	}
	Rectangle getRect(int x0,int y0){
		//System.out.println(x+"-x;y-"+y);
		return new Rectangle((int)x,(int)y,100,100);				
			}*/
}
class Way{
	int x[]={200,645,655,1100};
	int y[]={750,250,250,750};
	
	void left(){  x[0]+=10; x[3]+=10; }
	void right(){ x[0]-=10; x[3]-=10;}
}
class Tomato{
	float x,y,width,height, a;
	Way way;
	
	public Tomato(float x,float y,float width,float height,      Way way){
		this.x=x;this.y=y;this.width=width;this.height=height;
		this.way=way;
	}
	void run(){
		
		if(y<400){
		width+=0.01; height+=0.01;		
		y+=a;    a+=0.001;     
		}else{
			width+=0.5; height+=0.5;		
			y+=a;    a+=0.1; 
		}
	}
	void move(int map){
		
		x+=map*(y-250)/500; //System.out.println((y-250));
		
	}
}
