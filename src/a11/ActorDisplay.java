
package a11;

import java.awt.Color;
import java.awt.Dimension;
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

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;





@SuppressWarnings("serial")
public class ActorDisplay extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	
	
	private static Image backgroundimage;
	DisplayUpperPanel displayContent ;
	
	private JLabel mouseAxis;
    private int mouseX, mouseY;
    private boolean isMouseClicked;
//    static private Game game = new Game();
	

    
    
    
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
//	     System.out.println(mouseX+" "+mouseY);
//	     setMouseXY(mouseX,mouseY,isMouseClicked);
	     
	     
//	     System.out.println(displayContent.RadioButtonName );
	     
	     
	     if (displayContent.RadioButtonName == "sunFlower") {
	    	 Game.addSunFlowerPlant(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
			System.out.println(displayContent.RadioButtonName);
		}
	     
	     else if (displayContent.RadioButtonName == "cherry") {
	    	 Game.addCherryBomb(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
			System.out.println(displayContent.RadioButtonName);
		}
	     else  if (displayContent.RadioButtonName == "PeaShooter") {
	    	 Game.addPeaShooter(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
			System.out.println(displayContent.RadioButtonName);
		}
	     else  if (displayContent.RadioButtonName == "potatoMine") {
	    	 Game.addPotatoMine(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
			System.out.println(displayContent.RadioButtonName);
		}
	     else if (displayContent.RadioButtonName == "WallNuts") {
	    	 Game.addwallnuts(Game.pixelToGrid(mouseX), Game.pixelToGrid(mouseY));
			System.out.println(displayContent.RadioButtonName);
		}
	     
	    	 repaint();
	     
		
	     
	     
	     
	     
	     
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
//		 mouseX = e.getX();
//	     mouseY = e.getY();
//	     isMouseClicked =  true;
//	     setMouseXY(mouseX,mouseY,isMouseClicked);
//	     
	     
	  
//	     System.out.println(displayContent.RadioButtonName );
		
		
	}
// I am trying to add mouse listener on Game class,. or game class on here. , 
//	MouseListener getMouseListener() {
//		return new MouseAdapter() {
//			
//			@Override
//			public void mouseClicked(MouseEvent e) {
//				// TODO Auto-generated method stub
//				super.mouseClicked(e);
//				System.out.println("hu");
//				
//				
//			}		
//		};
//		
//	}
	
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