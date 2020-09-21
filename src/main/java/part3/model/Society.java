package part3.model;

import org.springframework.util.StringUtils;

import java.util.LinkedList;
import java.util.List;

public class Society {

    private static List<String> knownPlanets = new LinkedList<String>() {
        {
            add("Mars");
            add("Venus");
            add("Septulon-1");
        }
    };


    public static void listen(Declaration declaration) {
        if (declaration == null) {
            throw new IllegalArgumentException("Crowd is rampaging without new declarations");
        }
        Person speaker = declaration.getOwner();
        DeclarationType type = declaration.getType();
        String text = declaration.getText();
        if (speaker == null) {
            throw new UnsupportedOperationException("Nobody to throw tomato at");
        }
        if (StringUtils.isEmpty(text)) {
            speaker.setSanity(speaker.getSanity() - 3);
        } else {
            switch (type) {
                case DISCOVER_PLANET:
                    if (knownPlanets.contains(text)) {
                        System.out.println("This planet is already discovered");
                        speaker.setSanity(speaker.getSanity() - 3);
                    } else {
                        knownPlanets.add(text);
                    }
                    break;
                case VISIT_PLANET:
                    if (!knownPlanets.contains(text)) {
                        System.out.println("This planet isn't real");
                        speaker.setSanity(speaker.getSanity() - 3);
                    }
                    break;
                case WORK_ON_PLANET:
                    if (!text.equalsIgnoreCase("human")) {
                        System.out.println("You can't work for anybody except humans");
                        speaker.setSanity(speaker.getSanity() - 6);
                    }
                    break;
                default:
                    speaker.setSanity(0);
            }
        }
        judge(speaker);
    }

    public static void judge(Person speaker) {
        if (speaker.getSanity() < 5) {
            System.out.println("Throw this madman to room with soft walls!");
            imprison(speaker, Location.CLOSED_ROOM);
        }

    }

    public static void imprison(Person person, Location destination) {
        System.out.println(String.format("Go to %s", destination.toString()));
        person.setLocation(destination);
    }
}
