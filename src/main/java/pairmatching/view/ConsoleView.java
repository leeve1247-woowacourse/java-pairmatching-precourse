package pairmatching.view;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import pairmatching.data.CourseType;
import pairmatching.data.MissionType;

public class ConsoleView {
    public static void printMainView(){
        System.out.println("기능을 선택하세요.");
        System.out.println("1. 페어 매칭");
        System.out.println("2. 페어 조회");
        System.out.println("3. 페어 초기화");
        System.out.println("Q. 종료");
    }

    public static void printPairMatchingView(){
        System.out.println("#############################################");
        printCourseView();
        printMissionsView();
        System.out.println("#############################################");
        printExampleView();
    }

    private static void printCourseView() {
        CourseType[] courseTypes = CourseType.values();
        String joinedCoursesName = Arrays.stream(courseTypes)
                .map(CourseType::getKorean)
                .collect(Collectors.joining(" | "));
        System.out.printf("과정: %s\n", joinedCoursesName);
    }

    private static void printMissionsView() {
        System.out.println("미션:");
        for (int level = 1; level <= 5; level++) {
            printMissionView(level);
        }
    }

    private static void printMissionView(int level) {
        MissionType[] missionTypes = MissionType.values();
        String joinedLevelName = Arrays.stream(missionTypes)
                .filter(missionType -> missionType.getLevel() == level)
                .map(MissionType::getKorean)
                .collect(Collectors.joining(" | "));
        System.out.printf("- 레벨%d: %s\n", level, joinedLevelName);
    }

    private static void printExampleView(){
        System.out.println("과정, 레벨, 미션을 선택하세요.");
        CourseType courseType = CourseType.values()[0];
        MissionType missionType = MissionType.values()[0];
        System.out.printf("ex) %s, 레벨%d, %s\n", courseType.getKorean(), missionType.getLevel(), missionType.getKorean());
    }

    public static void printPariMatchedListView(List<List<String>> matchedPairs) {
        System.out.println("페어 매칭 결과입니다.");
        for (List<String> matchedPair : matchedPairs) {
            String joinedPairs = String.join(" : ", matchedPair);
            System.out.printf("%s\n", joinedPairs);
        }
    }

    public static void printMatchedListAlreadyExist() {
        System.out.println("매칭 정보가 있습니다. 다시 매칭하시겠습니까?");
        System.out.println("네 | 아니오");
    }
}
