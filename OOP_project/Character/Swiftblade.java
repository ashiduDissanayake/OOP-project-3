package Character;

public class Swiftblade extends Knight implements Marshlander{
    public Swiftblade() {
        super("Swiftblade", 250, 18, 20, 17.0, 13.0, "MarshLander");
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
