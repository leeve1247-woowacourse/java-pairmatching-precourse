package pairmatching.data.crew;

import camp.nextstep.edu.missionutils.Randoms;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class Shuffler {
    public static List<List<String>> shuffle(List<String> defaultCrew) {
        LinkedList<String> shuffledCrew = new LinkedList<>(Randoms.shuffle(defaultCrew));
        return parsingPairs(shuffledCrew);
    }

    private static List<List<String>> parsingPairs(LinkedList<String> shuffledCrew) {
        List<List<String>> pairs = new ArrayList<>();
        while (shuffledCrew.size() >= 2) {
            List<String> pair = extractTwoFromFirst(shuffledCrew);
            pairs.add(pair);
        }
        if (!shuffledCrew.isEmpty()) {
            String lastCrew = shuffledCrew.remove(0);
            pairs.get(pairs.size() - 1).add(lastCrew);
        }
        return pairs;
    }

    private static List<String> extractTwoFromFirst(List<String> shuffledCrew) {
        List<String> pair = new ArrayList<>();
        pair.add(shuffledCrew.remove(0));
        pair.add(shuffledCrew.remove(0));
        return pair;
    }
}
