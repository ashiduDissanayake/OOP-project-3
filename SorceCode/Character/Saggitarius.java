package Character;

public class Saggitarius extends Archer implements Mystic{
    public Saggitarius() {
        super("saggitarius", 230, 18, 7, 12.0, 17.0, "Mystics");
    }

    @Override
    public void applyMarshlandDebuff() {
        this.setSpeed(this.getSpeed()-1);
    }

    @Override
    public void applyArcaneBuff() {
        this.setAttack(this.getAttack()+2);
    }
}
