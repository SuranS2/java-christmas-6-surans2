package christmas.Domain;

public enum XmasEventPlannerErrorMessages {

    OUTPUT_ERROR("[ERROR]"),
    OUTPUT_SPACE(" "),
    OUTPUT_NO_MENU_ARGUMENT_DUPLICATE("유효하지 않은 주문입니다. 다시 입력해 주세요.");

    final private String PrintInterface;

    public String getErrorMessage() {
        return PrintInterface;
    }

    XmasEventPlannerErrorMessages(String printInterface) {
        this.PrintInterface = printInterface;
    }
}
