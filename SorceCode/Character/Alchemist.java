package Character;
//class for making objects of alchemist
public class Alchemist extends Healer implements Marshlander{
    public Alchemist() {
        super("Alchemist", 150, 13, 13, 13.0, 13.0, "MarshLander");
    }
    //following are buffs and debuffs when they are in homelands.

    @Override
    public void applyHillcrestDebuff() {
        this.setSpeed(this.getSpeed()-1);
    }

    @Override
    public void applyMarshlandBuff() {
        this.setDefence(this.getDefence()+2);
    }

    @Override
    public void applyDesertDebuff() {
        this.setHealth(this.getHealth()-1);
    }

    @Override
    public void applyArcaneBuff() {
        this.setSpeed(this.getSpeed()-1);
        this.setDefence(this.getDefence()-1);
    }
}
