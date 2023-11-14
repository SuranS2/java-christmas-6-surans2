package christmas;

public interface EnumMessageFormatter {

    //다른 클래스에 작성
    String getMessage();
    String getFormattedMessage(Object... params);
    //메세지 포멧잡기
}
