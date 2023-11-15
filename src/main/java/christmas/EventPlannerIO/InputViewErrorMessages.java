package christmas.EventPlannerIO;

public enum InputViewErrorMessages {
    OUTPUT_ERROR("[ERROR]"),
    OUTPUT_ERROR_SPACE(" "),
    OUTPUT_ERROR_OUTOFRANGE("유효하지 않은 날짜입니다. 다시 입력해주세요."),
    OUTPUT_ERROR_NOTVALIDMENU("유효하지 않은 주문입니다. 다시 입력해주세요.");
    //필수
    //필수

    final private String PrintInterface;

    public String getErrorMessage() {
        return PrintInterface;
    }

    InputViewErrorMessages(String printInterface) {
        this.PrintInterface = printInterface;
    }
}
