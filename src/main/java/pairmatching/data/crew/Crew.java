package pairmatching.data.crew;

import static pairmatching.data.crew.ReadResourceFile.readResourceFile;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import pairmatching.CourseAndMission;
import pairmatching.data.CourseType;

public class Crew {
    Map<CourseType, List<String>> crewsByCourseType = new HashMap<>();
    Map<CourseAndMission,List<List<String>>> pairedCrewMap = new HashMap<>();
    Map<Integer, List<List<String>>> pairedCrewMapByLevel = new HashMap<>();

    public Crew() {
        loadCrewList();
    }

    private void loadCrewList() {
        List<String> backendCrewsName = readResourceFile("backend-crew.md");
        List<String> frontendCrewsName = readResourceFile("frontend-crew.md");
        crewsByCourseType.put(CourseType.BACKEND, backendCrewsName);
        crewsByCourseType.put(CourseType.FRONTEND, frontendCrewsName);
    }

    public List<String> getCrewsByCourseType(CourseType courseType) {
        return crewsByCourseType.get(courseType);
    }

    public Map<CourseAndMission, List<List<String>>> getPairedCrewMap() {
        return pairedCrewMap;
    }

    public Map<Integer, List<List<String>>> getPairedCrewMapByLevel() {
        return pairedCrewMapByLevel;
    }

    public List<List<String>> getCrewsByCourseAndMission(CourseAndMission courseAndMission) {
        pairedCrewMap.putIfAbsent(courseAndMission, new ArrayList<>());
        return pairedCrewMap.get(courseAndMission);
    }

    public void addPairsToList(CourseAndMission courseAndMission, List<String> pair) {
        pairedCrewMap.putIfAbsent(courseAndMission, new ArrayList<>());
        pairedCrewMap.get(courseAndMission).add(pair);
    }

    public void addCrewToLastPair(CourseAndMission courseAndMission, String lastCrew) {
        pairedCrewMap.putIfAbsent(courseAndMission, new ArrayList<>());
        List<List<String>> pairs = pairedCrewMap.get(courseAndMission);
        pairs.get(pairs.size()-1).add(lastCrew);
    }

    public boolean isThereMatchedInfo(CourseAndMission courseAndMission) {
        return !pairedCrewMap.getOrDefault(courseAndMission, new ArrayList<>()).isEmpty();
    }
}
