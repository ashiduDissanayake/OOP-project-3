package Character;

public class Saint extends Healer implements Mystic{
    public Saint() {
        super("Saint", 200, 16, 14, 17.0, 9.0, "Mystic");
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
