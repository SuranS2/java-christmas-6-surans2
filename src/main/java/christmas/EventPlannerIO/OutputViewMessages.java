package christmas.EventPlannerIO;
import christmas.EnumMessageFormatter;

public enum OutputViewMessages implements EnumMessageFormatter {
    //enum은 추상클래스라 따로 인터페이스로 작업해야하나?

    OUTPUT_HELLO("안녕하세요! "),
    OUTPUT_SPACE(" "),
    OUTPUT_WELCOME("우테코 식당 %d월 이벤트 플래너입니다."),
    OUTPUT_ASK_DATE("%d월 중 식당 예상 방문 날짜는 언제인가요? "),
    OUTPUT_DATE_ARGUMENT_LIMIT("("+"숫자만 입력해 주세요!"+")"),
    // 구입 금액 입력부분 확인
    OUTPUT_ASK_MENU_NUMBERS("주문하실 메뉴를 메뉴와 개수를 알려 주세요. "),
    OUTPUT_MENU_ARGUMENT_LIMIT("("+"e.g. %s-%d,%s-%d,%s-%d"+")"),
    // 보너스 번호, 당첨번호 공통 확인 OUTPUT_ERROR
    // print 출력값 포장할 것
    OUTPUT_EVENT_PREVIEW("%d월 %d일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!"),
    OUTPUT_ORDER_LIST_MESSAGE("<주문 메뉴>"),
    OUTPUT_ORDER_LIST_EACH("%s %d개"),
    OUTPUT_RECEIPT_BEFORE_DISCOUNT_MESSAGE("<할인 전 총주문 금액>"),
    OUTPUT_RECEIPT_BEFORE_DISCOUNT_PRICE("%,d원"),
    OUTPUT_SERVICE_MENU_MESSAGE("<증정 메뉴>"),
    OUTPUT_SERVICE_LIST("%s %d개"),
    OUTPUT_EVENT_ADVANTAGE_NOTICE("<혜택 내역>"),

    OUTPUT_CHRISTMAS_DISCOUNT_MESSAGE("크리스마스 디데이 할인"),
    OUTPUT_WEEKDAY_DISCOUNT_MESSAGE("평일 할인"),
    OUTPUT_WEEKEND_DISCOUNT_MESSAGE("주말 할인"),
    OUTPUT_SPECIALDAY_DISCOUNT_MESSAGE("특별 할인"),
    OUTPUT_GIVEAWAY_DISCOUNT_MESSAGE("증정 이벤트"),
    OUTPUT_COLON(": "),
    OUTPUT_EVENT_ADVANTAGE_MESSAGE("%s" + OUTPUT_COLON + "%,d원"),
    OUTPUT_TOTAL_DISCOUNT_MESSAGE("<총혜택 금액>"),
    OUTPUT_TOTAL_DISCOUNT_PRICE("-%,d원"),
    OUTPUT_EVENT_BADGE_MESSAGE("<%d월 이벤트 배지>"),
    OUTPUT_EVENT_BADGE_SANTA("산타"),
    OUTPUT_EVENT_BADGE_TREE("트리"),
    OUTPUT_EVENT_BADGE_STAR("별"),
    OUTPUT_NOTTHING("없음");


    final private String message;

    // 선정관님 lotto 코드의 enum 메세지 부분 확인
    @Override
    public String getMessage() {
        return message;
    }

    OutputViewMessages(String message) {
        this.message = message;
    }

    @Override
    public String getFormattedMessage(Object... params) {
        return String.format(getMessage(), params);
    }


}
