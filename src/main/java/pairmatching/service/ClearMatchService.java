package pairmatching.service;

import pairmatching.controller.ControllerPhase;

public class ClearMatchService {
    public void run() {
    }

    public ControllerPhase shiftPhase() {
        return ControllerPhase.MainMenu;
    }
}
