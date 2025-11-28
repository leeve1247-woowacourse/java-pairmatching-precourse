package pairmatching.service;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.CourseAndMission;
import pairmatching.controller.ControllerPhase;
import pairmatching.data.crew.Crew;
import pairmatching.view.ConsoleView;

public class ViewMatchService {
    ControllerPhase controllerPhase;
    Crew crew;

    public ViewMatchService(ControllerPhase controllerPhase, Crew crew) {
        this.controllerPhase = controllerPhase;
        this.crew = crew;
    }

    public void run() {
        CourseAndMission courseAndMission = MatchParser.parseToCourseAndMission(Console.readLine());
        List<List<String>> crewsByCourseAndMission = crew.getPairsByCourseAndMission(courseAndMission);
        ConsoleView.printPariMatchedListView(crewsByCourseAndMission);
    }

    public ControllerPhase shiftPhase() {
        return ControllerPhase.MainMenu;
    }
}
