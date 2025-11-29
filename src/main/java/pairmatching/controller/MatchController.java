package pairmatching.controller;

import pairmatching.data.crew.Crew;

public class MatchController {
    private final Crew crew;

    public MatchController() {
        this.crew = new Crew();
    }

    public void run() {
        ControllerPhase controllerPhase = ControllerPhase.MainMenu;
        while (controllerPhase != ControllerPhase.Exit) {
            try {
                GameService service = controllerPhase.createService(crew);
                service.run();
                controllerPhase = service.shiftPhase();
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
    }
}
