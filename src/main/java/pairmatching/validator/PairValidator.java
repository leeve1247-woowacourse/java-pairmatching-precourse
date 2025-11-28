package pairmatching.validator;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import pairmatching.exception.PairMatchException;

public class PairValidator {
    public static void validate(List<List<String>> generatedCrew, List<List<String>> usedPairsByLevel) throws PairMatchException {
        checkIfPairDuplicated(generatedCrew, usedPairsByLevel);
    }

    private static void checkIfPairDuplicated(List<List<String>> generatedPairs, List<List<String>> pairsByLevel) {
        for (List<String> pairByLevel : pairsByLevel) {
            Set<String> pairSet = new HashSet<>(pairByLevel);
            checkIfPairDuplicatedPerPairSet(generatedPairs, pairSet);
        }
    }

    private static void checkIfPairDuplicatedPerPairSet(List<List<String>> generatedCrew, Set<String> pairSet) throws PairMatchException {
        for (List<String> generatedPair : generatedCrew) {
            Set<String> generatedPairSet = new HashSet<>(generatedPair);
            if (pairSet.containsAll(generatedPairSet) || generatedPairSet.containsAll(pairSet)) {
                throw new PairMatchException("동일 레벨에서 겹치는 페어 발견");
            }
        }
    }
}
