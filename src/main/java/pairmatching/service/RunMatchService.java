package pairmatching.service;

import camp.nextstep.edu.missionutils.Console;
import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import pairmatching.CourseAndMission;
import pairmatching.controller.ControllerPhase;
import pairmatching.data.crew.Crew;
import pairmatching.view.ConsoleView;

public class RunMatchService {
    ConsoleView consoleView;
    ControllerPhase controllerPhase;
    MatchParser matchParser;

    Crew crew;

    public RunMatchService(ConsoleView consoleView, ControllerPhase controllerPhase, Crew crew) {
        this.consoleView = consoleView;
        this.controllerPhase = controllerPhase;
        this.matchParser = new MatchParser();
        this.crew = crew;
    }

    private static List<String> extractTwoFromFirst(List<String> shuffledCrew) {
        List<String> pair = new ArrayList<>();
        String remove = shuffledCrew.remove(0);
        pair.add(
                remove
        );
        pair.add(shuffledCrew.remove(0));
        return pair;
    }

    public void run() {
        consoleView.printPairMatchingView();
        CourseAndMission courseAndMission = matchParser.parse(Console.readLine());

        boolean playShuffle = decideToShuffle(courseAndMission);
        if (playShuffle) {
            List<List<String>> shuffledList = shuffle(courseAndMission);
            consoleView.printPariMatchedListView(shuffledList);
        }

        controllerPhase = ControllerPhase.MainMenu;
    }

    private boolean decideToShuffle(CourseAndMission courseAndMission) {
        boolean playShuffle = true;
        if (crew.isThereMatchedInfo(courseAndMission)) {
            while (true) {
                try {
                    consoleView.printMatchedListAlreadyExist();
                    return matchParser.parseYesOrNo(Console.readLine());
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        }
        return playShuffle;
    }

    public List<List<String>> shuffle(CourseAndMission courseAndMission) {
        Map<CourseAndMission, List<List<String>>> pairedCrewMap = crew.getPairedCrewMap();

        pairedCrewMap.getOrDefault(courseAndMission, new ArrayList<>()).clear();

        List<String> defaultCrew = crew.getCrewsByCourseType(courseAndMission.courseType);
        LinkedList<String> shuffledCrew = new LinkedList<>(Randoms.shuffle(defaultCrew));

        while (shuffledCrew.size() >= 2) {
            List<String> pair = extractTwoFromFirst(shuffledCrew);
            crew.addPairsToList(courseAndMission, pair);
        }
        if (!shuffledCrew.isEmpty()) {
            String lastCrew = shuffledCrew.remove(0);
            crew.addCrewToLastPair(courseAndMission, lastCrew);
        }

        return crew.getCrewsByCourseAndMission(courseAndMission);
    }

    public ControllerPhase shiftPhase() {
        return ControllerPhase.MainMenu;
    }
}
