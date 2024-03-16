package Character;

public class Illusionist extends Mage implements Mystic{
    public Illusionist() {
        super("Illusionist", 120, 13, 8, 12.0, 14.0, "Mystic");
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
