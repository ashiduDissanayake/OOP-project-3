package Character;

public class Basilisk extends MythicalCreature implements Marshlander{
    public Basilisk() {
        super("Basilisk", 165, 15, 11, 10.0, 12.0, "MarshLander");
    }

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
