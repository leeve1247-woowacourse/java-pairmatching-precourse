package pairmatching.controller;

import java.util.function.BiFunction;
import pairmatching.data.crew.Crew;
import pairmatching.service.ClearMatchService;
import pairmatching.service.MainMenuService;
import pairmatching.service.RunMatchService;
import pairmatching.service.ViewMatchService;

public enum ControllerPhase {
    MainMenu("0", (phase, crew)-> new MainMenuService(phase)),
    runMatch("1", RunMatchService::new),
    ClearMatch("2", ClearMatchService::new),
    viewMatch("3", ViewMatchService::new),
    Exit("Q",  null);

    private final String keyBoardInput;
    private final BiFunction<ControllerPhase, Crew, GameService> factory;

    ControllerPhase(String keyBoardInput, BiFunction<ControllerPhase, Crew, GameService> factory) {
        this.keyBoardInput = keyBoardInput;
        this.factory = factory;
    }

    public String getKeyBoardInput() {
        return keyBoardInput;
    }

    public GameService createService(Crew crew) {
        if (factory == null) {
            throw new IllegalStateException("factory has not been set");
        }
        return factory.apply(this, crew);
    }
}
