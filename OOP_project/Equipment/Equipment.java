package Equipment;

public abstract class Equipment {
    private String name;
    private int price;
    private int attack;
    private int defence;
    private Double health;
    private Double speed;

    public Equipment(){
        name = "";
        price = 0;
        attack = 0;
        defence = 0;
        health = 0.0;
        speed = 0.0;
    }
    public Equipment(String name,int price,int attack,int defence,Double health,Double speed){
        this.name = name;
        this.price = price;
        this.attack = attack;
        this.defence = defence;
        this.health = health;
        this.speed = speed;
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

    public void setHealth(Double health) {
        this.health = health;
    }

    public Double getHealth() {
        return health;
    }

    public void setSpeed(Double speed) {
        this.speed = speed;
    }

    public Double getSpeed() {
        return speed;
    }

    public void setAttack(int attack) {
        this.attack = attack;
    }

    public int getAttack() {
        return attack;
    }
}

