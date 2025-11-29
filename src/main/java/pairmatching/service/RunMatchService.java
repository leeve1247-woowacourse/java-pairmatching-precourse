package pairmatching.service;

import camp.nextstep.edu.missionutils.Console;
import java.util.List;
import pairmatching.CourseAndMission;
import pairmatching.controller.ControllerPhase;
import pairmatching.controller.GameService;
import pairmatching.data.crew.Crew;
import pairmatching.data.crew.Shuffler;
import pairmatching.exception.PairMatchException;
import pairmatching.validator.PairValidator;
import pairmatching.view.ConsoleView;

public class RunMatchService implements GameService {
    ControllerPhase controllerPhase;
    Crew crew;

    public RunMatchService(ControllerPhase controllerPhase, Crew crew) {
        this.controllerPhase = controllerPhase;
        this.crew = crew;
    }

    public void run() {
        ConsoleView.printPairMatchingView();
        CourseAndMission courseAndMission = MatchParser.parseToCourseAndMission(Console.readLine());
        boolean isGeneratable = decideToGeneratePairs(courseAndMission);
        if (isGeneratable) {
            List<List<String>> shuffledList = generatePairs(courseAndMission);
            ConsoleView.printPariMatchedListView(shuffledList);
        }
        controllerPhase = ControllerPhase.MainMenu;
    }

    private boolean decideToGeneratePairs(CourseAndMission courseAndMission) {
        if (crew.isThereMatchedInfo(courseAndMission)) {
            return scanUserYesOrNo();
        }
        return true;
    }

    public List<List<String>> generatePairs(CourseAndMission courseAndMission) {
        List<String> defaultCrew = crew.getCrewsByCourseType(courseAndMission.courseType);

        saveNewPairList(courseAndMission, defaultCrew);

        return crew.getPairsByCourseAndMission(courseAndMission);
    }

    private boolean scanUserYesOrNo() {
        while (true) {
            try {
                ConsoleView.printMatchedListAlreadyExist();
                return MatchParser.parseYesOrNo(Console.readLine());
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private void saveNewPairList(CourseAndMission courseAndMission, List<String> defaultCrew) throws PairMatchException {
        int counter = 0;
        while (counter < 3) {
            try {
                List<List<String>> newPairs = Shuffler.shuffle(defaultCrew);
                PairValidator.validate(newPairs, crew.getPairsByLevel(courseAndMission));
                crew.putPairsByCourseAndMission(courseAndMission, newPairs);
                return;
            } catch (IllegalArgumentException illegalArgumentException) {
                System.out.println(illegalArgumentException.getMessage());
                counter++;
            }
        }
        throw new PairMatchException("새로운 페어 생성에 실패했습니다.");
    }

    public ControllerPhase shiftPhase() {
        return ControllerPhase.MainMenu;
    }
}
