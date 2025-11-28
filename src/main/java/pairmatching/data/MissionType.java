package pairmatching.data;

public enum MissionType {
    CarRace(1, "자동차경주"),
    Lotto(1, "로또"),
    BaseBall(1, "숫자야구게임"),
    Bucket(2, "장바구니"),
    Payment(2, "결제"),
    SubwayMap(2, "지하철노선도"),
    Optimize(4, "성능개선"),
    Production(4, "배포");

    private final int level;
    private final String korean;

    MissionType(int level, String korean) {
        this.level = level;
        this.korean = korean;
    }

    public int getLevel() {
        return level;
    }

    public String getKorean() {
        return korean;
    }
}
