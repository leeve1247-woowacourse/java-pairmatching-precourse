package pairmatching;

import java.util.Objects;
import pairmatching.data.CourseType;
import pairmatching.data.MissionType;

public class CourseAndMission {
    public MissionType missionType;
    public CourseType courseType;

    public CourseAndMission(CourseType courseType, MissionType missionType) {
        this.courseType = courseType;
        this.missionType = missionType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(missionType, courseType);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        CourseAndMission other = (CourseAndMission) obj;
        return Objects.equals(missionType, other.missionType) && Objects.equals(courseType, other.courseType);
    }

    @Override
    public String toString() {
        return "CourseAndMission [missionType=" + missionType + ", courseType=" + courseType + "]";
    }
}
