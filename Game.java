// Nikolina Božović 25/075
// Milica Djurkovic 25/065
// Tina Djukanovic 25/049

import java.util.ArrayList;

public class Game {
	private static class Player {
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
			setHealth(health);
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
		
			if(name==null) {
				System.out.println("Ime ne smije biti prazno.");
				return;
			}
			name=name.trim();
			if (name.length() == 0) {
				System.out.println("Ime ne smije biti prazno.");
			}else {
				String novoIme = "";
				for (int i = 0; i<name.length(); i++) {
					if (i==0) {
						novoIme=novoIme+Character.toUpperCase(name.charAt(i));
					} else if (name.charAt(i-1) == ' ' && name.charAt(i) != ' ') {
						novoIme = novoIme + Character.toUpperCase(name.charAt(i));
					}else if (name.charAt(i) == ' ' && name.charAt(i-1) == ' '){
						
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
			this.health=health;
			 }
		}
			 public String toString() {
				    return "Player[" + name + "] @ (" + x + "," + y + ") "
				            + width + "x" + height + " HP=" + health;
				}
		
	}

	private static class Enemy {
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
			if (tip == null || tip.trim().length() == 0) {
				 System.out.println("Tip neprijatelja ne smije biti prazan.");
			 }else {
				 this.tip=tip.trim();
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
    private Enemy [] enemies = new Enemy[100];
    private int brojNeprijatelja = 0;
    private ArrayList<String> eventLog = new ArrayList<String>();

    public Game(Player player) {
        this.player = player;
        
    }
    
    public void addEnemy(Enemy e) {
    	if (brojNeprijatelja < enemies.length) {
    		enemies[brojNeprijatelja] = e;
    		brojNeprijatelja++;
    		
    		eventLog.add("Dodat neprijatelj: " + e);
    	}else {
    		System.out.println("Nema mjesta za nove neprijatelje.");
    	}
    }
    
    public ArrayList<Enemy>findByType(String query) {
    	ArrayList<Enemy>pronadjeni = new ArrayList<Enemy>();
    	
    	query = query.trim().toLowerCase();
    	
    	for (int i = 0; i < brojNeprijatelja; i++) {
    		if (enemies[i].getTip().toLowerCase().contains(query)) {
    			pronadjeni.add(enemies[i]);
    		}
    	}
    	return pronadjeni;
    }

    public boolean checkCollisions(Player p, Enemy e) {

        if (p.getX() < e.getX() + e.getWidth()
                && p.getX() + p.getWidth() > e.getX()
                && p.getY() < e.getY() + e.getHeight()
                && p.getY() + p.getHeight() > e.getY()) {

            return true;
        }

        return false;
    }

    public void decreaseHealth(Player p, Enemy e) {
        int stariHealth = p.getHealth();
        int noviHealth = p.getHealth() - e.getDamage();

        if (noviHealth < 0) {
            noviHealth = 0;
        }

        p.setHealth(noviHealth);
        
        eventLog.add("HIT: Player by " + e.getTip() + " for " + e.getDamage() + " -> HP " + stariHealth + " -> " + noviHealth);
    }
    
    
    public ArrayList<Enemy>collidingWithPlayer() {
    	ArrayList<Enemy> sudari = new ArrayList<Enemy>();
    	
    	for (int i = 0; i < brojNeprijatelja; i++) {
    		if (checkCollisions(player, enemies[i])) {
    			sudari.add(enemies[i]);
    		}
    	}
    	return sudari;
    }
    
    public void resolveCollisions () {
    	ArrayList<Enemy>sudari = collidingWithPlayer();
    	
    	for (int i = 0; i<sudari.size(); i++) {
    		decreaseHealth(player, sudari.get(i));
    	}
    }

	public static void main(String[] args) {
	    Player player = new Player("Player1", 10, 5, 32, 32, 85);

	    Game game = new Game(player);

	    Enemy e1 = new Enemy("Orc", 100, 100, 16, 16, 15);
	    game.addEnemy(e1);

	    String podaci = "Goblin;12;5;16x16;20";

	    int p1 = podaci.indexOf(';');
	    String tip = podaci.substring(0, p1);

	    int p2 = podaci.indexOf(';', p1 + 1);
	    int x = Integer.parseInt(podaci.substring(p1 + 1, p2));

	    int p3 = podaci.indexOf(';', p2 + 1);
	    int y = Integer.parseInt(podaci.substring(p2 + 1, p3));

	    int p4 = podaci.indexOf(';', p3 + 1);
	    String dimenzije = podaci.substring(p3 + 1, p4);

	    int px = dimenzije.indexOf('x');
	    int width = Integer.parseInt(dimenzije.substring(0, px));
	    int height = Integer.parseInt(dimenzije.substring(px + 1));

	    int damage = Integer.parseInt(podaci.substring(p4 + 1));

	    Enemy e2 = new Enemy(tip, x, y, width, height, damage);
	    game.addEnemy(e2);

	    
	    System.out.println("Svi neprijatelji:");

	    for (int i = 0; i < game.brojNeprijatelja; i++) {
	        System.out.println(game.enemies[i]);
	    }

	    
	    System.out.println("\nNeprijatelji koji sadrze gob:");

	    ArrayList<Enemy> pronadjeni = game.findByType("gob");

	    for (int i = 0; i < pronadjeni.size(); i++) {
	        System.out.println(pronadjeni.get(i));
	    }

	    
	    System.out.println("\nNeprijatelji koji se sudaraju sa igracem:");

	    ArrayList<Enemy> sudari = game.collidingWithPlayer();

	    if (sudari.size() == 0) {
	        System.out.println("Nema sudara.");
	    } else {
	        for (int i = 0; i < sudari.size(); i++) {
	            System.out.println(sudari.get(i));
	        }
	    }


	    System.out.println("\nZdravlje prije sudara: " + player.getHealth());

	    game.resolveCollisions();

	    System.out.println("Zdravlje poslije sudara: " + player.getHealth());

	   
	    
	    System.out.println("\nDnevnik dogadjaja:");

	    for (int i = 0; i < game.eventLog.size(); i++) {
	        System.out.println(game.eventLog.get(i));
	    }
	}


	}

