package part3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import part3.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static part3.model.Location.*;

public class Tests {
    private VeeetVujhagyg veeetVujhagyg;

    @BeforeEach
    public void setUp() {
        veeetVujhagyg = new VeeetVujhagyg();
    }

    @Test
    public void nullDeclarationTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            Society.listen(null);
        });
    }

    @Test
    public void anonymousDeclarationTest() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> {
            Society.listen(new Declaration(null, "Mars", DeclarationType.DISCOVER_PLANET));
        });
    }

    @Test
    public void judgeSaneTest() {
        Society.judge(veeetVujhagyg);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void judgeInaneTest() {
        veeetVujhagyg.setSanity(3);
        Society.judge(veeetVujhagyg);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void silentDeclarationTest() {
        Person speaker = new Person();
        Society.listen(speaker.declare("", DeclarationType.DISCOVER_PLANET));
        assertEquals(7, speaker.getSanity());
    }

    @Test
    public void imprisonRoomTest() {
        Society.imprison(veeetVujhagyg, CLOSED_ROOM);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void imprisonUntaxedTest() {
        Society.imprison(veeetVujhagyg, UNTAXED_PLACE);
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }


    @Test
    public void discoverPlanetSanitySaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.DISCOVER_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(7, veeetVujhagyg.getSanity());
    }

    @Test
    public void discoverPlanetSanityInsaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Nebuchadnezzar", DeclarationType.DISCOVER_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(10, veeetVujhagyg.getSanity());
    }

    @Test
    public void discoverPlanetLocationSaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Raxacoricofallapatorius", DeclarationType.DISCOVER_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void discoverPlanetLocationInsaneTest() {
        veeetVujhagyg.setSanity(6);
        Declaration insaneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.DISCOVER_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void visitPlanetSanitySaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.VISIT_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(10, veeetVujhagyg.getSanity());
    }

    @Test
    public void visitPlanetSanityInsaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Unknown Planet", DeclarationType.VISIT_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(7, veeetVujhagyg.getSanity());
    }

    @Test
    public void visitPlanetLocationSaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.VISIT_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void visitPlanetLocationInsaneTest() {
        veeetVujhagyg.setSanity(6);
        Declaration insaneDeclaration = veeetVujhagyg.declare("Unknown Planet", DeclarationType.VISIT_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void workOnPlanetSanitySaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Human", DeclarationType.WORK_ON_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(10, veeetVujhagyg.getSanity());
    }

    @Test
    public void workOnPlanetSanityInsaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Green pen", DeclarationType.WORK_ON_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(4, veeetVujhagyg.getSanity());
    }

    @Test
    public void workOnPlanetLocationSaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Human", DeclarationType.WORK_ON_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void workOnPlanetLocationInsaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Green pen", DeclarationType.WORK_ON_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void writeBookOnFreedomTest() {
        veeetVujhagyg.writeBook();
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    public void writeBookInRoomTest() {
        veeetVujhagyg.setLocation(CLOSED_ROOM);
        veeetVujhagyg.writeBook();
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }

    @Test
    public void writeBookInUntaxedPlace() {
        veeetVujhagyg.setLocation(UNTAXED_PLACE);
        veeetVujhagyg.writeBook();
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }


}
