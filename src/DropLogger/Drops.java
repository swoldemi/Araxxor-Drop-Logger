package DropLogger;

public class Drops {
	// Drop name constants
	final public static String[] arrow_pheromone = new String[]{"Araxyte pheromone", "Araxyte arrow"}; 
	final public static String[] main_loot = new String[]{
		"Coins", "Black dragonhide", "Crystal triskelion fragment 1", 
		"Crystal triskelion fragment 2", "Crystal triskelion fragment 3",
		"Sirenic scale", "Hydrix bolt tips", "Onyx bolts (e)",
		"Rune platebody", "Uncut onyx", "Grimy avantoe", "Grimy dwarf weed",
		"Grimy lantadyme", "Magic logs", "Yew logs", "Adamantite ore", "Coal",
		"Adamantite ore", "Runite ore", "Dwarf weed seed", "Magic seed",
		"Water talisman", "Battlestaff"};
	final public static String[] charms = new String[]{"Gold charm", "Green charm", "Red charm", "Blue charm"};
	final public static String[] food_potions = new String[]{
		"Rocktail", "Saradomin brew flask (6)", "Super restore flask (6)",
		"Overload flask (6)"};
	final public static String[] unique_drops = new String[]{
		"Spider leg bottom", "Spider leg middle", "Spider leg top",
		"Araxxi's eye", "Araxxi's fang", "Araxxi's web"};
	final public static String[] very_unique_drops = new String[]{"Starved ancient effigy", "Araxyte pet", "Araxyte pet"};

	// Instance variables for the drops of the current kill
	int kill_number; // current kill number
	int arrow_pheromone_drop; // number of arrows dropped, 0 if pheromone
	String charms_drop; // charms dropped - {quantity} {Charm type}
	String food_potions_drop; // food or potions dropped - {quantity} {food or potion name} 
	String main_loot_drop; // main loop dropped - {quantity} {name}
	String unique_drops_drop; // if unique or very unique drop - {name}, no quantity
	
	Drops(int kn, int apd, String cd, String fdp, String udp){
		this.kill_number = kn;
		this.arrow_pheromone_drop = apd;
		this.charms_drop = cd;
		this.food_potions_drop = fdp;
		this.unique_drops_drop = udp;
	}
}
