package pairmatching.controller;

public enum ControllerPhase {
    MainMenu("0"),
    runMatch("1"),
    ClearMatch("2"),
    viewMatch("3"),
    Exit("Q");

    private final String keyBoardInput;

    ControllerPhase(String keyBoardInput) {
        this.keyBoardInput = keyBoardInput;
    }

    public String getKeyBoardInput() {
        return keyBoardInput;
    }
}
