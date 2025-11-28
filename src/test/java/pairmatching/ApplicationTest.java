package pairmatching;

import static camp.nextstep.edu.missionutils.test.Assertions.assertShuffleTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;

import camp.nextstep.edu.missionutils.test.NsTest;
import java.util.Arrays;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pairmatching.service.MatchParser;
import pairmatching.view.ConsoleView;

@SuppressWarnings("NonAsciiCharacters")
class ApplicationTest extends NsTest {

    private static final String ERROR_MESSAGE = "[ERROR]";


    @Test
    void 짝수_인원_페어_매칭() {
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "Q");
                    assertThat(output()).contains("태웅 : 백호", "치수 : 태섭");
                },
                Arrays.asList("태웅", "백호", "치수", "태섭")
        );
    }

    @Test
    void 데이터_업데이트(){
        String a = "백호 태웅 치수 태섭 대만 준호 대협 덕규 태산 경태 수겸 현준 준섭 한나 소연 호열 대남 용팔 구식";
        String[] split = a.split(" ");
        assertShuffleTest(
                () -> {
                    run("1", "백엔드, 레벨1, 자동차경주", "1", "백엔드, 레벨1, 자동차경주", "그웨웨엑","네", "Q");
                },
                Arrays.asList(split)
        );
    }

    @Test
    void 없는_미션에_대한_예외_처리() {
        assertSimpleTest(
                () -> {
                    runException("1", "백엔드, 레벨1, 오징어게임");
                    assertThat(output()).contains(ERROR_MESSAGE);
                }
        );
    }

    @Test
    void 페어_매칭_출력() {
        ConsoleView consoleView = new ConsoleView();
        assertSimpleTest(
                consoleView::printPairMatchingView
        );
    }

    @Test
    void 파싱_페어_매치() {
        MatchParser matchParser = new MatchParser();
        CourseAndMission courseAndMission = matchParser.parse("백엔드, 레벨1, 자동차경주");
        Assertions.assertNotNull(courseAndMission);
        Assertions.assertEquals("백엔드", courseAndMission.courseType.getKorean());
        Assertions.assertEquals(1, courseAndMission.missionType.getLevel());
        Assertions.assertEquals("자동차경주", courseAndMission.missionType.getKorean());
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
