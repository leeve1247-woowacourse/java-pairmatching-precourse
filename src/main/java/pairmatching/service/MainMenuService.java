package pairmatching.service;

import camp.nextstep.edu.missionutils.Console;
import java.util.Arrays;
import pairmatching.controller.ControllerPhase;
import pairmatching.exception.PairMatchException;
import pairmatching.view.ConsoleView;

public class MainMenuService {
    ConsoleView consoleView;
    ControllerPhase controllerPhase;

    public MainMenuService(ConsoleView consoleView, ControllerPhase controllerPhase) {
        this.consoleView = consoleView;
    }

    public void run() {
        consoleView.printMainView();
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
