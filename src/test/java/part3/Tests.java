package part3;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import part3.model.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static part3.model.Location.*;

class Tests {
    private VeeetVujhagyg veeetVujhagyg;

    @BeforeEach
    void setUp() {
        veeetVujhagyg = new VeeetVujhagyg();
    }

    @Test
    void nullDeclarationTest() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> Society.listen(null));
    }

    @Test
    void anonymousDeclarationTest() {
        Assertions.assertThrows(UnsupportedOperationException.class, () -> Society.listen(new Declaration(null, "Mars", DeclarationType.DISCOVER_PLANET)));
    }

    @Test
    void judgeSaneTest() {
        Society.judge(veeetVujhagyg);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    void judgeInsaneTest() {
        veeetVujhagyg.setSanity(3);
        Society.judge(veeetVujhagyg);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    void silentDeclarationTest() {
        Person speaker = new Person();
        Society.listen(speaker.declare("", DeclarationType.DISCOVER_PLANET));
        assertEquals(7, speaker.getSanity());
    }

    @ParameterizedTest
    @EnumSource(Location.class)
    void imprisonTest(Location location) {
        Society.imprison(veeetVujhagyg, location);
        assertEquals(location, veeetVujhagyg.getLocation());
    }

    @Test
    void discoverPlanetSanitySaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.DISCOVER_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(7, veeetVujhagyg.getSanity());
    }

    @Test
    void discoverPlanetSanityInsaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Nebuchadnezzar", DeclarationType.DISCOVER_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(10, veeetVujhagyg.getSanity());
    }

    @Test
    void discoverPlanetLocationSaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Raxacoricofallapatorius", DeclarationType.DISCOVER_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    void discoverPlanetLocationInsaneTest() {
        veeetVujhagyg.setSanity(6);
        Declaration insaneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.DISCOVER_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    void visitPlanetSanitySaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.VISIT_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(10, veeetVujhagyg.getSanity());
    }

    @Test
    void visitPlanetSanityInsaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Unknown Planet", DeclarationType.VISIT_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(7, veeetVujhagyg.getSanity());
    }

    @Test
    void visitPlanetLocationSaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Mars", DeclarationType.VISIT_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    void visitPlanetLocationInsaneTest() {
        veeetVujhagyg.setSanity(6);
        Declaration insaneDeclaration = veeetVujhagyg.declare("Unknown Planet", DeclarationType.VISIT_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    void workOnPlanetSanitySaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Human", DeclarationType.WORK_ON_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(10, veeetVujhagyg.getSanity());
    }

    @Test
    void workOnPlanetSanityInsaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Green pen", DeclarationType.WORK_ON_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(4, veeetVujhagyg.getSanity());
    }

    @Test
    void workOnPlanetLocationSaneTest() {
        Declaration saneDeclaration = veeetVujhagyg.declare("Human", DeclarationType.WORK_ON_PLANET);
        Society.listen(saneDeclaration);
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    void workOnPlanetLocationInsaneTest() {
        Declaration insaneDeclaration = veeetVujhagyg.declare("Green pen", DeclarationType.WORK_ON_PLANET);
        Society.listen(insaneDeclaration);
        assertEquals(CLOSED_ROOM, veeetVujhagyg.getLocation());
    }

    @Test
    void writeBookOnFreedomTest() {
        veeetVujhagyg.writeBook();
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());
    }

    @Test
    void writeBookInRoomTest() {
        veeetVujhagyg.setLocation(CLOSED_ROOM);
        veeetVujhagyg.writeBook();
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }

    @Test
    void writeBookInUntaxedPlace() {
        veeetVujhagyg.setLocation(UNTAXED_PLACE);
        veeetVujhagyg.writeBook();
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }


}
