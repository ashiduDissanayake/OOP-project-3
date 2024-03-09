package Character;

public class Hydra extends MythicalCreature implements Marshlander{
    public Hydra() {
        super("Hydra", 205, 12, 16, 15.0, 11.0, "MarshLander");
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
