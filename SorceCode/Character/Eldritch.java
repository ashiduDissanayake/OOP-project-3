package Character;
//class for making objects of eldritch
public class Eldritch extends Mage implements Mystic{
    public Eldritch() {
        super("Eldritch", 270, 19, 17, 18.0, 14.0, "Mystic");
    }
    //following are buffs and debuffs when they are in homelands.

    @Override
    public void applyMarshlandDebuff() {
        this.setSpeed(this.getSpeed()-1.0);
    }

    @Override
    public void applyArcaneBuff() {
        this.setAttack(this.getAttack()+2);
    }
}
