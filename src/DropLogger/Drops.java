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
		"none", "Leg bottom", "Leg middle", "Leg top",
		"Eye", "Fang", "Web", "Effigy"};
	final public static String[] pets = new String[]{"Araxyte pet", "Barry", "Mallory"};

	// Instance variables for the drops of the current kill
	public static int kill_number; // current kill number
	public static int arrow_pheromone_drop; // number of arrows dropped, 0 if pheromone
	
	public static String charms_drop; // charms dropped - {quantity} {Charm type}
	public static String food_potions_drop; // food or potions dropped - {quantity} {food or potion name} 
	public static String main_loot_drop; // main loop dropped - {quantity} {name}
	public static String unique_drops_drop; // if unique or very unique drop - {name}, no quantity
	
	public static String charms_quantity;
	public static String food_potions_quantity;
	public static String main_loot_quantity;

	Drops(int kn, int apd, String cd, String fdp, String udp){
		kill_number = kn;
		arrow_pheromone_drop = apd;
		charms_drop = cd;
		food_potions_drop = fdp;
		unique_drops_drop = udp;
	}
}
