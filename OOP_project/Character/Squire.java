package Character;

public class Squire extends Knight implements Marshlander{
    public Squire() {
        super("Squire", 85, 8, 9, 7.0, 8.0, "MarshLander");

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
