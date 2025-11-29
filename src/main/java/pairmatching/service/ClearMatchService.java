package pairmatching.service;

import pairmatching.controller.ControllerPhase;
import pairmatching.controller.GameService;
import pairmatching.data.crew.Crew;
import pairmatching.view.ConsoleView;

public class ClearMatchService implements GameService {
    Crew crew;
    ControllerPhase controllerPhase;
    public ClearMatchService(ControllerPhase controllerPhase, Crew crew) {
        this.controllerPhase = controllerPhase;
        this.crew = crew;
    }


    public void run() {
        crew.initList();
        ConsoleView.printInitialized();
    }

    public ControllerPhase shiftPhase() {
        return ControllerPhase.MainMenu;
    }
}
