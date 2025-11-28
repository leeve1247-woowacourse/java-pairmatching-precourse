package pairmatching.service;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.CourseAndMission;
import pairmatching.data.CourseType;
import pairmatching.data.MissionType;
import pairmatching.exception.PairMatchException;

public class RunMatchParser {

    public RunMatchParser() {
    }

    public CourseAndMission parse(String rawUserInput) {
        try {
            List<String> parsedUserInput = Arrays.stream(rawUserInput.split(",")).collect(Collectors.toList());
            String courseUserInput = parsedUserInput.get(0).trim();
            String missionLevelUserInput = parsedUserInput.get(1).trim().replace("레벨", "");
            String missionNameUserInput = parsedUserInput.get(2).trim();

            CourseType courseType = validateCourse(courseUserInput);
            MissionType missionType = validateMission(missionLevelUserInput, missionNameUserInput);
            return new CourseAndMission(courseType, missionType);
        } catch (RuntimeException e) {
            throw new PairMatchException("잘못된 입력입니다.");
        }
    }

    private CourseType validateCourse(String courseUserInput) {
        return Arrays.stream(CourseType.values())
                .filter(courseType -> courseType.getKorean().equals(courseUserInput))
                .findFirst()
                .orElseThrow(() -> new PairMatchException("존재하지 않는 과정입니다."));
    }

    private MissionType validateMission(String missionLevelUserInput, String missionNameUserInput) {
        return Arrays.stream(MissionType.values()).collect(Collectors.toList()).stream()
                .filter(missionType -> missionType.getKorean().equals(missionNameUserInput))
                .filter(missionType -> missionType.getLevel() == Integer.parseInt(missionLevelUserInput))
                .findFirst()
                .orElseThrow(() -> new PairMatchException("존재하지 않는 미션입니다."));
    }

    public boolean parseYesOrNo(String rawUserInput) {
        rawUserInput = rawUserInput.trim();
        if (rawUserInput.equals("네")) {
            return true;
        }
        if (rawUserInput.equals("아니오")) {
            return false;
        }
        throw new PairMatchException("잘못된 입력입니다.");
    }
}

