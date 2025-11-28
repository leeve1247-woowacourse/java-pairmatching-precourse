package pairmatching;

import pairmatching.controller.MatchController;

public class Application {
    public static void main(String[] args) {
        // TODO 구현 진행
        // List<String> 형태로 준비한다.
        // 크루 목록의 순서를 랜덤으로 섞는다.(library) 활용
        // 랜덤으로 섞인 페어 목록에서 페어 매칭을 할 때, 앞에서 부터 순서대로 두 명씩 페어를 맺는다.
        // 홄수인 경우 마지막 남은 크루는 마지막 페어에 포함시킨다.
        // 같은 레벨에서 이미 페어로 만난 적이 있는 크루끼리 다시 페어로 매칭된다면 크루 목록의 순서를 다시 랜덤으로 섞어서 매칭을 시도한다.
        // 3회 시도까지 매칭이 되지 않거나 매칭을 할 수 있는 경우의 수가 없으면 에러 메시지를 출력한다.
        MatchController matchController = new MatchController();
        matchController.run();

    }
}
