package part3;

import part3.model.*;
import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static part3.model.Location.*;

public class Tests {

    @Test(expected = IllegalArgumentException.class)
    public void nullDeclarationTest() {
        Society.listen(null);
    }

    @Test(expected = UnsupportedOperationException.class)
    public void anonymousDeclarationTest() {
        Society.listen(new Declaration(null, "Mars", DeclarationType.DISCOVER_PLANET));
    }

    @Test
    public void judgeTest() {
        Person madman = new Person();
        Person common = new Person();
        madman.setSanity(3);
        Society.judge(madman);
        Society.judge(common);
        assertEquals(FREEDOM, common.getLocation());
        assertEquals(CLOSED_ROOM, madman.getLocation());
    }

    @Test
    public void silentDeclarationTest() {
        Person speaker = new Person();
        Society.listen(speaker.declare("", DeclarationType.DISCOVER_PLANET));
        assertEquals(7, speaker.getSanity());
    }

    @Test
    public void imprisonTest() {
        Person speaker = new Person();
        Society.imprison(speaker, CLOSED_ROOM);
        assertEquals(CLOSED_ROOM, speaker.getLocation());
        Society.imprison(speaker, UNTAXED_PLACE);
        assertEquals(UNTAXED_PLACE, speaker.getLocation());
    }

    @Test
    public void discoverPlanetSanityTest() {
        Person madman = new Person();
        Person common = new Person();
        Declaration insaneDeclaration = madman.declare("Mars", DeclarationType.DISCOVER_PLANET);
        Declaration saneDeclaration = common.declare("Nevaudenzaaar", DeclarationType.DISCOVER_PLANET);
        Society.listen(insaneDeclaration);
        Society.listen(saneDeclaration);
        assertEquals(7, madman.getSanity());
        assertEquals(10, common.getSanity());
    }

    @Test
    public void discoverPlanetLocationTest() {
        Person madman = new Person();
        madman.setSanity(6);
        Person common = new Person();
        Declaration insaneDeclaration = madman.declare("Mars", DeclarationType.DISCOVER_PLANET);
        Declaration saneDeclaration = common.declare("Raxacoricafallopatoruis", DeclarationType.DISCOVER_PLANET);
        Society.listen(insaneDeclaration);
        Society.listen(saneDeclaration);
        assertEquals(CLOSED_ROOM, madman.getLocation());
        assertEquals(FREEDOM, common.getLocation());
    }

    @Test
    public void visitPlanetSanityTest() {
        Person madman = new Person();
        Person common = new Person();
        Declaration insaneDeclaration = madman.declare("Unknown Planet", DeclarationType.VISIT_PLANET);
        Declaration saneDeclaration = common.declare("Mars", DeclarationType.VISIT_PLANET);
        Society.listen(insaneDeclaration);
        Society.listen(saneDeclaration);
        assertEquals(7, madman.getSanity());
        assertEquals(10, common.getSanity());
    }

    @Test
    public void visitPlanetLocationTest() {
        Person madman = new Person();
        madman.setSanity(6);
        Person common = new Person();
        Declaration insaneDeclaration = madman.declare("Unknown Planet", DeclarationType.VISIT_PLANET);
        Declaration saneDeclaration = common.declare("Mars", DeclarationType.VISIT_PLANET);
        Society.listen(insaneDeclaration);
        Society.listen(saneDeclaration);
        assertEquals(CLOSED_ROOM, madman.getLocation());
        assertEquals(FREEDOM, common.getLocation());
    }

    @Test
    public void workOnPlanetSanityTest() {
        Person madman = new Person();
        Person common = new Person();
        Declaration insaneDeclaration = madman.declare("Green pen", DeclarationType.WORK_ON_PLANET);
        Declaration saneDeclaration = common.declare("Human", DeclarationType.WORK_ON_PLANET);
        Society.listen(insaneDeclaration);
        Society.listen(saneDeclaration);
        assertEquals(4, madman.getSanity());
        assertEquals(10, common.getSanity());
    }

    @Test
    public void workOnPlanetLocationTest() {
        Person madman = new Person();
        Person common = new Person();
        Declaration insaneDeclaration = madman.declare("Green pen", DeclarationType.WORK_ON_PLANET);
        Declaration saneDeclaration = common.declare("Human", DeclarationType.WORK_ON_PLANET);
        Society.listen(insaneDeclaration);
        Society.listen(saneDeclaration);
        assertEquals(CLOSED_ROOM, madman.getLocation());
        assertEquals(FREEDOM, common.getLocation());
    }

    @Test
    public void writeBookOnFreedomTest() {
        VeeetVujhagyg veeetVujhagyg = new VeeetVujhagyg();
        veeetVujhagyg.writeBook();
        assertEquals(FREEDOM, veeetVujhagyg.getLocation());

        veeetVujhagyg.setLocation(UNTAXED_PLACE);
        veeetVujhagyg.writeBook();
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }

    @Test
    public void writeBookInRoomTest(){
        VeeetVujhagyg veeetVujhagyg = new VeeetVujhagyg();
        veeetVujhagyg.setLocation(CLOSED_ROOM);
        veeetVujhagyg.writeBook();
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }

    @Test
    public void writeBookInUntaxedPlace(){
        VeeetVujhagyg veeetVujhagyg = new VeeetVujhagyg();
        veeetVujhagyg.setLocation(UNTAXED_PLACE);
        veeetVujhagyg.writeBook();
        assertEquals(UNTAXED_PLACE, veeetVujhagyg.getLocation());
    }


}
