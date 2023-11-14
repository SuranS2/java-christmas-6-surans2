package christmas.EventPlannerIO;



import camp.nextstep.edu.missionutils.Console;
import christmas.EventPlannerIO.OutputView;

import christmas.EventPlannerIO.InputViewValidator.*;

public class InputView {

    private final OutputView printer;
    private final InputViewValidator inputViewValidator;

    public InputView() {
        this.printer = new OutputView();
        this.inputViewValidator = new InputViewValidator();
    }

//    public static final Pattern NUMBER_FORMAT = Pattern.compile("^-?\\d+$");
//    public static final Pattern INPUT_ANSWER_LOTTO_NUMBERS_FORMAT =
//            Pattern.compile("^" + "-?\\d+,".repeat(Lotto.LOTTO_SIZE - 1) + "-?\\d+$");
//    public static final String NUMBERS_DELIMITER = ",";

    public int readDate() {
        while (true) {
            try {
                printer.printAskVisitDate();
                String inputDate = Console.readLine();
                return inputViewValidator.validateDate(inputDate);
            } catch (IllegalArgumentException | IllegalStateException e) {
                printer.printErrorMessage(e);
            }
        }
        // ...
        //int값 리턴
    }

    // ...
}
