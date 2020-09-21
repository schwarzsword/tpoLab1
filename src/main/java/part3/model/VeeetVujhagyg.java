package part3.model;


public class VeeetVujhagyg extends Person {
    public void writeBook() {
        if (this.getLocation().equals(Location.CLOSED_ROOM)) {
            Society.imprison(this, Location.UNTAXED_PLACE);
        }
    }

}
