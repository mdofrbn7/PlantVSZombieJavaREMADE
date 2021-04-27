package a11;

import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Menu;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class DisplayUpperPanel extends JPanel implements ActionListener, MouseMotionListener, MouseListener{
	
	 private static Image backgroundimage;
     public Menu menu;
   
      private JRadioButton cardSunflower, cardCherry, cardPeaShooter, cardPotatoMine, cardWallnut;
      private Image image;
      private ImageIcon icon;
      private ButtonGroup cardButtonGroup;
      private JLabel mouseAxis;
      private int mouseX, mouseY;
      private boolean mouseDragged;
      private Point selectedPlantcorner,previousPoint ,currentPoint;
      private Game game;
      private JMenuBar menubar;
      private JLabel  scoreLabel, score , sunCount, sunCountJLabel;
      public static int scoreNum  , sunNum;
      private boolean isMouseClicked;
      public String RadioButtonName;
      
//      private String buttonName;
//      private final String RadioButtonToolTipText = "Select this button to get "+buttonName+ " for "+buy+"sun";
      
     
	
	
	
	public DisplayUpperPanel() {
		// TODO Auto-generated constructor stub
		setBackground(Color.gray);
		initComponents();
	}
	
	
	public void initComponents() {
		
	 
        
		
		

        cardButtonGroup = new ButtonGroup();
        
        cardSunflower = new JRadioButton();     
        cardSunflower.setToolTipText("Plant flower for 25 sunny-Gold");
        setRadioButtonIcon("src/a11/Animal-Icons/cardSunflower.png", image, icon, cardSunflower,cardButtonGroup);
        cardSunflower.setSize(10, 2);
        cardSunflower.setName("sunFlower");
        cardSunflower.addActionListener(this);
        add(cardSunflower);
        
        
        cardCherry = new JRadioButton(); 
        cardCherry.setToolTipText("Plant cherryBomb for 150 sunny-Gold");
        setRadioButtonIcon("src/a11/Animal-Icons/CardCherry.png", image, icon, cardCherry,cardButtonGroup);
        cardCherry.setSize(10, 2);
        cardCherry.setName("cherry");
        cardCherry.addActionListener(this);
        add(cardCherry);
        
        cardPeaShooter = new JRadioButton();  
        cardPeaShooter.setToolTipText( "Plant pea shooter for 100 sunny-Gold");
        setRadioButtonIcon("src/a11/Animal-Icons/CardPeaShooter.png", image, icon, cardPeaShooter,cardButtonGroup);
        cardPeaShooter.setSize(35, 2);
        cardPeaShooter.setName("PeaShooter");
        cardPeaShooter.addActionListener(this);
        add(cardPeaShooter);

        cardPotatoMine= new JRadioButton();  
        cardPotatoMine.setToolTipText("Plant POTATO MINE for 25 sunny-Gold");
        setRadioButtonIcon("src/a11/Animal-Icons/CardPotatoMine.png", image, icon, cardPotatoMine,cardButtonGroup);
        cardPotatoMine.setSize(50, 2);
        cardPotatoMine.setName("potatoMine");
        cardPotatoMine.addActionListener(this);
        add(cardPotatoMine);

        cardWallnut = new JRadioButton(); 
        cardWallnut.setToolTipText("Plant wall nut for 50 sunny-Gold");
        setRadioButtonIcon("src/a11/Animal-Icons/CardWallnut.png", image, icon, cardWallnut,cardButtonGroup);
        cardWallnut.setSize(75, 2);
        cardWallnut.setName("WallNuts");
        cardWallnut.addActionListener(this);
        add(cardWallnut);
	}
	
	
	 public void setRadioButtonIcon(String path, Image image, ImageIcon icon, JRadioButton button, ButtonGroup groupe){
//       image = Toolkit.getDefaultToolkit().createImage(path);
       image = setImage(image, path);
       icon = new ImageIcon(scaleImage(70,80 , image));
       button.setIcon(icon);
       button.setBorder(null);
       groupe.add(button);
       }
	
	
	
	 public static Image setImage(Image image, String path){
         image = Toolkit.getDefaultToolkit().createImage(path);
         return image;
         
     }
     
     public static Image scaleImage(int width, int height, Image image){
         Image scalledImage = image.getScaledInstance(width,height, image.SCALE_DEFAULT);
         return scalledImage;
         
     }
     
     
	 
	 
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
      
		
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
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
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	        e.consume();

		
		
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
//	
//		mouseX = e.getX()-10;
//        mouseY = e.getY()-10;
//        mouseAxis.setText("X: "+mouseX+" \nY:"+mouseY);
//        mouseDragged = false;
        
       
        e.consume();
		
		
		
	}
	
	
	
	
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		 try{
	            if(cardSunflower.isSelected()){ 
	            	
	            	RadioButtonName = cardSunflower.getName();
	            	
//	            	System.out.println(RadioButtonName);
	          
	              
	            }  if(cardCherry.isSelected()){
	            	
	            	
	            	RadioButtonName = cardCherry.getName();
	            	
	        
//	            System.out.println(RadioButtonName);
	            
	         } if(cardPeaShooter.isSelected()){
	        	 
	        	 RadioButtonName = cardPeaShooter.getName();
	                
	             
//	                System.out.println(RadioButtonName);
	            }
	            if(cardPotatoMine.isSelected()){
	            	
	            RadioButtonName = cardPotatoMine.getName();
	               
	        
//	                System.out.println(RadioButtonName);
	            }
	            if(cardWallnut.isSelected()){
	            	
	           RadioButtonName = cardWallnut.getName();
	                
	              
//	                System.out.println(RadioButtonName);
	            }
	        
	        }catch (Exception ex){
	            System.out.println(ex);
	        }
	        
		
		
		
	}
	

}
