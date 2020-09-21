package part3;

import part3.model.DeclarationType;
import part3.model.Society;
import part3.model.VeeetVujhagyg;

public class Main {
    public static void main(String[] args) {
        VeeetVujhagyg veeetVujhagyg = new VeeetVujhagyg();
        Society.listen(veeetVujhagyg.declare("Earth", DeclarationType.DISCOVER_PLANET));
        Society.listen(veeetVujhagyg.declare("Earth", DeclarationType.VISIT_PLANET));
        Society.listen(veeetVujhagyg.declare("Green pen", DeclarationType.WORK_ON_PLANET));

    }
}
