package pairmatching.service;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.CourseAndMission;
import pairmatching.controller.ControllerPhase;
import pairmatching.data.crew.Crew;
import pairmatching.view.ConsoleView;

public class ViewMatchService {
    ConsoleView consoleView;
    ControllerPhase controllerPhase;
    Crew crew;

    public ViewMatchService(ConsoleView consoleView, ControllerPhase controllerPhase, Crew crew) {
        this.consoleView = consoleView;
        this.controllerPhase = controllerPhase;
        this.crew = crew;
    }

    public void run() {
        CourseAndMission courseAndMission = MatchParser.parse(Console.readLine());
        List<List<String>> crewsByCourseAndMission = crew.getCrewsByCourseAndMission(courseAndMission);
        consoleView.printPariMatchedListView(crewsByCourseAndMission);
    }

    public ControllerPhase shiftPhase() {
        return ControllerPhase.MainMenu;
    }
}
