// Nikolina Božović 25/075
// Milica Djurkovic 25/065
// Tina Djukanovic 25/049

public class Game {
	
private class Player {
	private String name;
	private int x;
	private int y;
	private int width;
	private int height;
	private int health;
	public Player(String name, int x, int y, int width, int height, int health) {
		setName(name);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		this.health = health;
		setHealth(health);
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		if(name.length()==0) {
			System.out.println("Ime ne smije biti prazno.");
		} else {
			String novoIme = "";
			for (int i = 0; i<name.length(); i++) {
				if (i==0) {
					novoIme=novoIme+name.charAt(i);
				} else if (name.charAt(i-1) == ' ') {
					novoIme = novoIme + name.charAt(i);
				}else {
					novoIme = novoIme + name.charAt(i);
				}
			}
			this.name = novoIme;
		}
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getHealth() {
		return health;
	}
	public void setHealth(int health) {
		 if (health < 0 || health > 100) {
	      System.out.println("Health mora biti između 0 i 100.");
		 }else {
	            		
		this.health = health;
		 }
	}
		 public String toString() {
			    return "Player[" + name + "] @ (" + x + "," + y + ") "
			            + width + "x" + height + " HP=" + health;
			}
	
}

private class Enemy {
	private String tip;
	private int x;
	private int y;
	private int width;
	private int height;
	private int damage;
	public Enemy(String tip, int x, int y, int width, int height, int damage) {
		
		setTip(tip);
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
		setDamage(damage);

	}
		public String getTip() {
			return tip;
		}
		public void setTip(String tip) {
		if (tip == null) {
			 System.out.println("Tip neprijatelja ne smije biti prazan.");
		 }else {
			 this.tip=tip;
		 }
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public int getWidth() {
		return width;
	}
	public void setWidth(int width) {
		this.width = width;
	}
	public int getHeight() {
		return height;
	}
	public void setHeight(int height) {
		this.height = height;
	}
	public int getDamage() {
		return damage;
	}
	public void setDamage(int damage) {
		 if (damage < 0 || damage > 100) {
			 System.out.println("Damage mora biti između 0 i 100.");
		 }else {
		this.damage = damage;
		 }
	}
		 public String toString() {
			    return "Enemy[" + tip + "] @ (" + x + "," + y + ") "
			            + width + "x" + height + " DMG=" + damage;
			}
}



	    private Player player;
	    private Enemy enemy;

	    public Game(Player player, Enemy enemy) {
	        this.player = player;
	        this.enemy = enemy;
	    }

	    public boolean checkCollision() {

	        if (player.getX() < enemy.getX() + enemy.getWidth()
	                && player.getX() + player.getWidth() > enemy.getX()
	                && player.getY() < enemy.getY() + enemy.getHeight()
	                && player.getY() + player.getHeight() > enemy.getY()) {

	            return true;
	        }

	        return false;
	    }

	    public void decreaseHealth() {
	        
	        int noviHealth = player.getHealth() - enemy.getDamage();

	        if (noviHealth < 0) {
	            noviHealth = 0;
	        }

	        player.setHealth(noviHealth);
	    }


	    public static void main(String[] args) {

	    }
		

	}


