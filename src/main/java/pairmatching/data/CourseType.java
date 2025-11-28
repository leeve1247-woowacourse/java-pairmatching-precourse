package pairmatching.data;

public enum CourseType {
    BACKEND("백엔드"),
    FRONTEND("프론트엔드");

    private final String korean;

    CourseType(String korean) {
        this.korean = korean;
    }

    public String getKorean(){
        return this.korean;
    }
}
