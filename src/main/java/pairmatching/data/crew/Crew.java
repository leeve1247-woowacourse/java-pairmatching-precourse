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

    public void initList(){
        pairedCrewMap = new HashMap<>();
        pairedCrewMapByLevel = new HashMap<>();
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

    public List<List<String>> getPairsByCourseAndMission(CourseAndMission courseAndMission) {
        pairedCrewMap.putIfAbsent(courseAndMission, new ArrayList<>());
        return pairedCrewMap.get(courseAndMission);
    }

    public List<List<String>> getPairsByLevel(CourseAndMission courseAndMission) {
        pairedCrewMapByLevel.putIfAbsent(courseAndMission.missionType.getLevel(), new ArrayList<>());
        return pairedCrewMapByLevel.get(courseAndMission.missionType.getLevel());
    }

    public boolean isThereMatchedInfo(CourseAndMission courseAndMission) {
        return !pairedCrewMap.getOrDefault(courseAndMission, new ArrayList<>()).isEmpty();
    }

    public void putPairsByCourseAndMission(CourseAndMission courseAndMission, List<List<String>> generatedCrew) {
        pairedCrewMap.put(courseAndMission, generatedCrew);
        getPairsByLevel(courseAndMission).addAll(generatedCrew);
    }
}
