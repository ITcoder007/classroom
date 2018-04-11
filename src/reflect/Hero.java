package reflect;

public class Hero {
    public String name;
    public float hp;
    public int damage;
    public int id;
 
    static String copyright;
    static {
	    	System.out.println("初始化 copyright");
	    	copyright = "版权由Riot Games公司所有";
    }
    
    public Hero() {
    	
    }
    public Hero(String name) {
    		this.name = name;
    }
    
    @Override
	public String toString() {
		return "Hero's name is" + name;
	}
    
    public void attack(Hero h) {
    		System.out.println(this.name+" 正在攻击 " + h.getName());
    }
    
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
}
