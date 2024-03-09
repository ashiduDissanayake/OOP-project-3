package Character;

import Equipment.Armour;
import Equipment.Artefact;
import Player.Player;

public abstract class Character {
    private String name;
    private int price;
    private int attack;
    private int defence;
    private double health;
    private double speed;
    private Armour armour;
    private Artefact artefact;
    private String category;

    public Character(){
        name = "";
        price = 0;
        attack = 0;
        defence = 0;
        health = 0.0;
        speed = 0.0;
        armour = new Armour();
        artefact = new Artefact();
        category = "";
    }
    public Character(String name, int price, int attack, int defence, Double health, Double speed, String category){
        this.name = name;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
        this.armour = new Armour();
        this.artefact = new Artefact();
        this.category = category;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getPrice() {
        return price;
    }

    public void setDefence(int defence) {
        this.defence = defence;
    }

    public int getDefence() {
        return defence;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public double getHealth() {
        return health;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    /*public void groundCheck(String PlayGround){
        if(PlayGround == "Hilcrist"){

        }
    }*/

    public Armour getArmour(){
        return this.armour;
    }

    public Artefact getArtefact(){
        return this.artefact;
    }

    public void buyArmour(Armour armour, Player user){
        this.price += ((armour.getPrice()) / 5);
        this.attack += (armour.getAttack());
        this.defence += (armour.getDefence());
        this.health += (armour.getHealth());
        this.speed += (armour.getSpeed());
        this.armour = armour;
        user.setArmoursUsed(1);
    }

    public void buyArtefact(Artefact artefact, Player user){
        this.price += ((artefact.getPrice()) / 5);
        this.attack += (artefact.getAttack());
        this.defence += (artefact.getDefence());
        this.health += (artefact.getHealth());
        this.speed += (artefact.getSpeed());
        this.artefact = artefact;
        user.setArmoursUsed(1);
    }

    public void discardArmour(Armour armour, Player user){
        this.price -= ((armour.getPrice()) / 5);
        this.attack -= (armour.getAttack());
        this.defence -= (armour.getDefence());
        this.health -= (armour.getHealth());
        this.speed -= (armour.getSpeed());
        this.armour = new Armour();
        user.setArmoursUsed(0);
    }

    public void discardArtefact(Artefact artefact, Player user){
        this.price -= ((artefact.getPrice()) / 5);
        this.attack -= (artefact.getAttack());
        this.defence -= (artefact.getDefence());
        this.health -= (artefact.getHealth());
        this.speed -= (artefact.getSpeed());
        this.artefact = new Artefact();
        user.setArmoursUsed(0);
    }
}
