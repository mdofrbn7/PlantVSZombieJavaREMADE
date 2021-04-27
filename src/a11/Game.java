package a11;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.sql.Time;
import java.util.Random;

import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.Timer;

/**
 * A top-level panel for playing a game similar to Plants Vs Zombies.
 * 
 * This panel is primarily responsible for coordinating the various
 * aspects of the game, including:
 * - Running the game step-by-step using a timer
 * - Creating and displaying other components that make up the game
 * - Creating new plants and/or zombies, when necessary
 * - Checking for the end of the game
 * 
 * (Not all of the above behavior is provided in the starter code)
 * 
 * @author Travis Martin and David Johnson
 */
@SuppressWarnings("serial")
public class Game extends JPanel implements ActionListener{
	private static final int NUM_ROWS = 4;
	private static final int NUM_COLS = 9;
	private static final int GRID_BUFFER_PIXELS = 50;
	private static final int CELL_SIZE = 100;
	private static final int STEP_TIME= 30;
	
	private   String normalZombibody = "src/a11/Animal-Icons/Zombi.png";
	
	private   String littleZombibody = "src/a11/Animal-Icons/Zombi4.png";
	
	private  static String cherrybody="src/a11/Animal-Icons/cherryBomb.png";
	
	private  String bossZombiebody="src/a11/Animal-Icons/Zombi5.png";
	
	private static  String peaShooterBody="src/a11/Animal-Icons/peaShooter.png";
	
	private static  String potatoMineBody="src/a11/Animal-Icons/potatoMine.png";
	
	private static   String sunFlowerBody="src/a11/Animal-Icons/Sunflower.png";
	
	private static   String wallNutsBody="src/a11/Animal-Icons/wallNut.png";
	
	
	
	
	
	private DisplayUpperPanel displayup = new DisplayUpperPanel();
	/**
	 * This panel is responsible for displaying plants
	 * and zombies, and for managing their interactions.
	 */
	private static ActorDisplay actorDisplay = new ActorDisplay(
	        NUM_COLS * CELL_SIZE + GRID_BUFFER_PIXELS * 2,
	        NUM_ROWS * CELL_SIZE + GRID_BUFFER_PIXELS * 2);

	public Game() {
	    add(actorDisplay);
	    Thread thread;
	    // This layout causes all elements to be stacked vertically
	    setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
	    
		// The timer calls the actionPerformed method every STEP_TIME milliseconds
		Timer timer = new Timer(STEP_TIME, this);
		timer.start();
		
	
//		boolean isClicked = actorDisplay.getIsClicked();
//		String name = displayup.getCheckedRadioName();
//		System.out.println(name);
//		if (name == "sunFlower" && isClicked) {
//			 addwallnuts(mouseX, mouseY);
//		}
//		System.out.println(actorDisplay.getIsClicked());
		
		
		// This adds a plant to every row
		// TODO: replace this with your new functionality
//		for (int i = 0; i < NUM_ROWS; i++) {
//		    addwallnuts(0, i);
//		}
		
		
	}

	/**
	 * Executes game logic every time the timer ticks.
	 */
    @Override
    public void actionPerformed(ActionEvent e) {
        actorDisplay.step();
        Random rand = new Random();
     
        if (rand.nextInt(100) > 98) {
        	try {
        		if(rand.nextInt(3)==1) {
        			Thread.sleep(rand.nextInt(3000));
    				addnormalZombie(NUM_COLS - 1, rand.nextInt(NUM_ROWS));
        			
        		}else if (rand.nextInt(3)==2) {
        			Thread.sleep(rand.nextInt(3000));
        			addBossZombie(NUM_COLS - 1, rand.nextInt(NUM_ROWS));

        		}else {
        			Thread.sleep(rand.nextInt(3000));
    				addLittleZombie(NUM_COLS - 1, rand.nextInt(NUM_ROWS));
    				}
        		
			} catch (InterruptedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        	
        	
        	
        	
        
        }
        
        
//    	
//		int mouseX  = actorDisplay.getMouseX();
//	int mouseY  = actorDisplay.getMouseY();
//	 addwallnuts(mouseX, mouseY);
//	 repaint();
        
//        int mouseX  = actorDisplay.getMouseX();
//		int mouseY  = actorDisplay.getMouseY();
//		System.out.println(mouseX+" "+mouseY);
//		boolean isClicked = actorDisplay.getIsClicked();
//		String name = displayup.getCheckedRadioName();
//		System.out.println(name);
//		System.out.println(isClicked);
//		if (name == "sunFlower" && isClicked) {
//			 addwallnuts(mouseX, mouseY);
//		}
    }
	
  
    
    
    
    /**
     * Adds a plant to the official game grid & display panel.
     */
    
	public static void addSunFlowerPlant(int col, int row) {

		int Planthealth = 150;
		int coolDown=5;
		int attackDamage = 1;
	    // The magic numbers below define various hardcoded plant properties
        actorDisplay.addActor(new Plant(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
                sunFlowerBody, Planthealth, coolDown, attackDamage));
	}
	
	

	public static void addPotatoMine(int col, int row) {

		int Planthealth = 30;
		int coolDown=5;
		int attackDamage = 10;
		
	    // The magic numbers below define various hardcoded plant properties
        actorDisplay.addActor(new PotatoMine(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
                potatoMineBody, Planthealth, coolDown, attackDamage));
	}
	
	
	
	public static void addPeaShooter(int col, int row) {

		int Planthealth = 40;
		int coolDown=5;
		int attackDamage = 15;
		
	    // The magic numbers below define various hardcoded plant properties
        actorDisplay.addActor(new PotatoMine(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
                peaShooterBody, Planthealth, coolDown, attackDamage));
	}
	
	
	
	public static void addCherryBomb(int col, int row) {

		int Planthealth = 15;
		int coolDown=5;
		int attackDamage = 50;
		
	    // The magic numbers below define various hardcoded plant properties
        actorDisplay.addActor(new PotatoMine(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
                cherrybody, Planthealth,coolDown, attackDamage));
	}
	
	
	public static void addwallnuts(int col, int row) {

		int Planthealth = 200;
		int coolDown=5;
		int attackDamage = 0;
		
	    // The magic numbers below define various hardcoded plant properties
        actorDisplay.addActor(new PotatoMine(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
                wallNutsBody, Planthealth, coolDown, attackDamage));
	}

	
    /**
     * Adds a zombie to the official game grid & display panel.
     */
	public  void addnormalZombie(int col, int row) {
		row++;
		int Zombiehealth = 100;
		int coolDown=50;
		int speed = -2;
		int attackDamage = 15;
		
	    // The magic numbers below define various hardcoded zombie properties
        actorDisplay.addActor(new Zombie(
                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
				normalZombibody,
				Zombiehealth, coolDown, speed, attackDamage));
	}
	
	public void addLittleZombie(int col, int row) {
		row++;
			int Zombiehealth = 50;
			int coolDown=50;
			int speed = -5;
			int attackDamage = 10;
			
		    // The magic numbers below define various hardcoded zombie properties
	        actorDisplay.addActor(new Zombie(
	                gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
					littleZombibody,
					Zombiehealth, coolDown, speed, attackDamage));
		}
		

	public void addBossZombie(int col, int row) {
		row++;
		int Zombiehealth = 150;
		int coolDown=50;
		int speed = -1;
		int attackDamage = 50;
		
	    // The magic numbers below define various hardcoded zombie properties
	    actorDisplay.addActor(new Zombie(
	            gridToPixel(col), gridToPixel(row), CELL_SIZE * 4 / 5, 
				bossZombiebody,
				Zombiehealth, coolDown, speed, attackDamage));
	}
	
		

	
	
	
	
	/**
	 * Converts a row or column to its exact pixel location in the grid.
	 */
	public static int gridToPixel(int rowOrCol) {
	    return rowOrCol * CELL_SIZE + GRID_BUFFER_PIXELS;
	}
	
	/**
	 * The inverse of gridToPixel
	 */
	public static int pixelToGrid(int xOrY) {
	    return (xOrY - GRID_BUFFER_PIXELS) / CELL_SIZE;
	}
	
	/**
	 * Create, start, and run the game.
	 */
	public static void main(String[] args) {
        JFrame app = new JFrame("Plant and Zombie Test");
        app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        app.setResizable(false);
        app.add(new Game());
        app.pack();
        app.setVisible(true);
        
        
	}
}