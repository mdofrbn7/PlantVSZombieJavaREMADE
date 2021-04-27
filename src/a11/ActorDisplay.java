
package a11;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.beans.EventHandler;
import java.util.ArrayList;
import java.util.TimerTask;

import javax.management.timer.Timer;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;







@SuppressWarnings("serial")
public class ActorDisplay extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	
	
	private static Image backgroundimage;
	DisplayUpperPanel displayContent ;
	
	private JLabel mouseAxis ;
    private int mouseX, mouseY;
    private boolean isMouseClicked;
    private JLabel  scoreLabel;
	private static JLabel sunCount;
	private JLabel sunCountJLabel;
	private static JLabel score;
    public static int scoreNum =0;
	public static int sunNum = 100;
	public static int flowerCount=0;
    public static final int SUN_FLOWER_PRICE= 25 , CHERRY_PRICE = 150 , PEA_SHOOTER_PRICE = 100, POTATO_MINE_PRICE = 25  ,
    		WAL_NUT_PRICE=50;
    private String notEnoughCoin = "You dont have enough SUN_COIN";
//    static private Game game = new Game();
    public static Timer t ;
	

    
    
    
//    public Game game = new Game();
	
    /** Contains all plants and zombies in this game. */
	private ArrayList<Actor> actors = new ArrayList<>();

	/**
	 * Creates a canvas upon which all actors will live.
	 * @param colPixels the number of pixels that this panel is wide
	 * @param rowPixels the number of pixels that this panel is high
	 */
	public ActorDisplay(int colPixels, int rowPixels) {
		setPreferredSize(new Dimension(colPixels, 40+rowPixels));
		backgroundimage = Toolkit.getDefaultToolkit().createImage("src/a11/Animal-Icons/backdrop.png");
		setBackground(Color.GRAY);
		t= new Timer();
		   
     
        scoreLabel = new JLabel("UR Score: ");
        scoreLabel.setFont(new Font("Serif", Font.BOLD, 20));
        scoreLabel.setSize(10, 20);
        add(scoreLabel);
        
        
        score = new JLabel();
        score.setBounds(15, 25, 30, 30);
        score.setFont(new Font("Serif", Font.BOLD, 30));
        score.setText(""+scoreNum);
        add(score);
        
       
        sunCountJLabel = new JLabel();
        sunCountJLabel.setToolTipText("you have "+sunNum+ " sunny-GOLD");
        sunCountJLabel.setBackground(Color.red);
        Image img = Toolkit.getDefaultToolkit().createImage("src/a11/Animal-Icons/sun.png");
        ImageIcon labeliicon =new ImageIcon(DisplayUpperPanel.scaleImage(70,80 , img));
        sunCountJLabel.setIcon(labeliicon);
        sunCountJLabel.setSize(10, 20);
        add(sunCountJLabel);
       
        
        sunCount = new JLabel();
        sunCount.setBackground(Color.red);
        sunCount.setBounds(15, 25, 30, 30);
        sunCount.setFont(new Font("Serif", Font.BOLD, 30));
        sunCount.setText(""+sunNum);
        add(sunCount);
		
		
		
		
		 displayContent = new DisplayUpperPanel();
		add(displayContent);
		
		
		
		
		
		
		
		
		mouseAxis = new JLabel();
        mouseAxis.setBounds(50, 20, 50,50);
        mouseAxis.setText(" x,y axis of mouse");

        add(mouseAxis);
        addMouseListener(this);
		addMouseMotionListener(this);
		
		
	
	}
	

	
	/**
	 * Adds an actor to the master list of actors ONLY IF
	 * the provided actor is not colliding with any of the existing
	 * actors.
	 * @param actor the object to add
	 * @return false if something prevents the actor from being added, true otherwise
	 */
	public boolean addActor(Actor actor) {
	    if (actor.isCollidingAny(actors)) {
	        return false;
	    }
        actors.add(actor);
        return true;
	}

	/**
	 * This overrided method draws the details of this particular panel,
	 * including all actors that are contained within.
	 */
	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		 g.drawImage(backgroundimage, 0, 100, null);
		for (Actor actor : actors) {
			actor.draw(g);
		}
		
		
		
	}

	/**
	 * Executes all of the actor logic that happens in one turn, including
	 * moving actors, checking for collisions, managing attacks, and more.
	 */
	public void step() {
		// Increment actor cooldowns.
		for (Actor actor : actors) {
			actor.update();
		}

		// Allow all actors to interact with all other actors.
		// This is where attacks, healing, etc happen.
		for (Actor actor : actors) {
			for (Actor other : actors) {
					actor.actOn(other);
			}
		}

		// Remove plants and zombies with low health
		ArrayList<Actor> nextTurnActors = new ArrayList<>();
		for (Actor actor : actors) {
			if (actor.isAlive())
				nextTurnActors.add(actor);
			else
				actor.removeAction(actors); // Execute any special effects for dead actors
		}
		actors = nextTurnActors;

		// Move the (alive) actors that are not colliding.
		for (Actor actor : actors) {
		    if (!actor.isCollidingAny(actors)) {
		        actor.move();
		    }
		}
		
		
		for (Actor actor : actors){
            if (actor.getXPosition()==0){
                JOptionPane.showMessageDialog(null, "Gaeme Over");
                
                System.exit(0);
            }
        }
		
	// Redraw the scene.
		
	
		
		repaint();
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
	
		mouseX = e.getX()-10;
        mouseY = e.getY()-10;
        mouseAxis.setText("X: "+mouseX+" \nY:"+mouseY);

	
//        mouseDragged = false;
//        System.out.println(displayContent.RadioButtonName );
//       System.out.println(e.getID());
        e.consume();
		
		
		
		
	}
	public void setMouseXY(int x, int y , boolean isClicked){
		this.mouseX = x;
		this.mouseY= y;
		this.isMouseClicked =isClicked;
	}
	
	public int getMouseX() {
		return this.mouseX;
	}
	public int getMouseY() {
		return this.mouseY;
	}
	public boolean getIsClicked() {
		return this.isMouseClicked;
	}


	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		 mouseX = e.getX();
	     mouseY = e.getY();
	     isMouseClicked =  true;
		
	     
	     if (displayContent.RadioButtonName == "sunFlower") {
	    	 if(sunNum<25) {
	    		 try {
					
					 System.out.println(notEnoughCoin);
					 JOptionPane.showMessageDialog(null, notEnoughCoin);
					 Thread.sleep(1000);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
	    		
	    		 return;
	    	 }else {
	    		 sunNum = sunNum - SUN_FLOWER_PRICE;
		    	 sunCount.setText(""+sunNum);
		    	 flowerCount++;
		    	 Game.addSunFlowerPlant(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));

				System.out.println(displayContent.RadioButtonName);
	    	 }
	    	
		}
	     
	     else if (displayContent.RadioButtonName == "cherry") {
	    	 
	    	 if(sunNum<150) {
	    		 try {
						
						 System.out.println(notEnoughCoin);
						 JOptionPane.showMessageDialog(null, notEnoughCoin);
						 Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    	
	    		 return;
	    	 }
	    	 else { 
	    		 sunNum = sunNum - CHERRY_PRICE;
		    	 sunCount.setText(""+sunNum);
		    	 flowerCount++;
	    		  Game.addCherryBomb(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
	    		  System.out.println(displayContent.RadioButtonName);
	    	 }
	    	
		}
	     else  if (displayContent.RadioButtonName == "PeaShooter") {
	    	 if(sunNum<100) {
	    		 try {
						
						 System.out.println(notEnoughCoin);
						 JOptionPane.showMessageDialog(null, notEnoughCoin);
						 Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		 return;
	    	 }else {
	    		 sunNum = sunNum - PEA_SHOOTER_PRICE;
		    	 sunCount.setText(""+sunNum);
		    	 flowerCount++;
		    	 Game.addPeaShooter(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
		    	 
				System.out.println(displayContent.RadioButtonName);
	    	 }
	    	 
	    	
		}
	     else  if (displayContent.RadioButtonName == "potatoMine") {
	    	
	    	 if(sunNum<25) {
	    		 try {
					
						 System.out.println(notEnoughCoin);
						 JOptionPane.showMessageDialog(null, notEnoughCoin);
							Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		 return;
	    	 }else {
	    		  sunNum = sunNum - POTATO_MINE_PRICE;
	    		  sunCount.setText(""+sunNum);
	    		  flowerCount++;
	    		  Game.addPotatoMine(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
	    		  System.out.println(displayContent.RadioButtonName);
	    	 }

	    	
			
		}
	     else if (displayContent.RadioButtonName == "WallNuts") {
	    	 if(sunNum<50) {
	    		 try {
						
						 System.out.println(notEnoughCoin);
						 JOptionPane.showMessageDialog(null, notEnoughCoin);
						 Thread.sleep(1000);
					} catch (InterruptedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
	    		 return;
	    	 }else {
	    		 sunNum = sunNum - WAL_NUT_PRICE;
	    		 sunCount.setText(""+sunNum);
	    		 flowerCount++;
	    		 
	    		 Game.addwallnuts(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
	    		 System.out.println(displayContent.RadioButtonName);
	    		
	    	 }
	    	
	    	 
		}
	     
	    	 repaint();
    
	     
	}
	
	public static void generateSunCoin(int time) {
		if(time%100 == 0) {
			scoreNum = flowerCount+5;
			score.setText(""+scoreNum);
			sunNum= sunNum + flowerCount+10;
			sunCount.setText(""+sunNum);
		}
			
	}
	
	

	@Override
	public void mousePressed(MouseEvent e) {

		
	}

	
	@Override
	public void mouseReleased(MouseEvent e) {
//		 TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	
		
	}




}