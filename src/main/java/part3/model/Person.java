package part3.model;

import lombok.Data;

@Data
public class Person {
    private Location location;
    private int sanity;

    public Person() {
        location = Location.FREEDOM;
        sanity = 10;
    }

    public Declaration declare(String text, DeclarationType declarationType) {
        return new Declaration(this, text, declarationType);
    }
}
