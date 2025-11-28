package pairmatching;

import pairmatching.controller.MatchController;

public class Application {
    public static void main(String[] args) {
        MatchController matchController = new MatchController();
        matchController.run();

    }
}
