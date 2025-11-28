package pairmatching.controller;

import pairmatching.data.crew.Crew;
import pairmatching.service.ClearMatchService;
import pairmatching.service.MainMenuService;
import pairmatching.service.RunMatchService;
import pairmatching.service.ViewMatchService;

public class MatchController {
    private final Crew crew;

    public MatchController() {
        this.crew = new Crew();
    }

    public void run() {
        ControllerPhase controllerPhase = ControllerPhase.MainMenu;
        while (controllerPhase != ControllerPhase.Exit) {
            try {
                if (controllerPhase == ControllerPhase.MainMenu) {
                    MainMenuService mainMenuService = new MainMenuService(controllerPhase);
                    mainMenuService.run();
                    controllerPhase = mainMenuService.shiftPhase();
                    continue;
                }

                if (controllerPhase == ControllerPhase.runMatch) {
                    RunMatchService runMatchService = new RunMatchService(controllerPhase, crew);
                    runMatchService.run();
                    controllerPhase = runMatchService.shiftPhase();
                    continue;
                }

                if (controllerPhase == ControllerPhase.viewMatch) {
                    ViewMatchService viewMatchService = new ViewMatchService(controllerPhase, crew);
                    viewMatchService.run();
                    controllerPhase = viewMatchService.shiftPhase();
                    continue;
                }

                if (controllerPhase == ControllerPhase.ClearMatch) {
                    ClearMatchService clearMatchService = new ClearMatchService();
                    clearMatchService.run();
                    controllerPhase = clearMatchService.shiftPhase();
                }
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
            }
        }
    }


    private <T>ControllerPhase run(ControllerPhase controllerPhase, T service) {

    }

}
