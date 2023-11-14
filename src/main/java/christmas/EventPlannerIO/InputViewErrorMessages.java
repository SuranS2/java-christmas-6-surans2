package christmas.EventPlannerIO;

public enum InputViewErrorMessages {
    OUTPUT_ERROR("[ERROR]"),
    OUTPUT_SPACE(" "),
    OUTPUT_ERROR_OUTOFRANGE("유효하지 않은 날짜입니다. 다시 입력해주세요.");
    //필수

    final private String PrintInterface;

    public String getErrorMessage() {
        return PrintInterface;
    }

    InputViewErrorMessages(String printInterface) {
        this.PrintInterface = printInterface;
    }
}
