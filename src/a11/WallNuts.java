package a11;

public class WallNuts extends Plant{

	public WallNuts(int xPosition, int yPosition, int size, String imgPath, int health, int coolDown,
			int attackDamage) {
		super(xPosition, yPosition, size, imgPath, health, coolDown, attackDamage);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void actOn(Zombie other) {
		if (isColliding(other)) {
			if (isReadyForAction()) {
				
				other.changeHealth(-attackDamage);
				resetCoolDown();
			}
		}
	}
	

}
