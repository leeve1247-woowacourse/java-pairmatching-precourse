package pairmatching.service;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import pairmatching.controller.ControllerPhase;
import pairmatching.controller.GameService;
import pairmatching.exception.PairMatchException;
import pairmatching.view.ConsoleView;

public class MainMenuService implements GameService {
    ControllerPhase controllerPhase;

    public MainMenuService(ControllerPhase controllerPhase) {
        this.controllerPhase = controllerPhase;
    }

    public void run() {
        ConsoleView.printMainView();
        String userRawInput = Console.readLine().trim();
        this.controllerPhase = Arrays.stream(ControllerPhase.values())
                .filter(controllerPhase -> controllerPhase.getKeyBoardInput().equals(userRawInput))
                .findFirst()
                .orElseThrow(() -> new PairMatchException("잘못된 입력입니다"));
    }

    public ControllerPhase shiftPhase() {
        return controllerPhase;
    }
}
