package christmas.EventPlannerIO;


import camp.nextstep.edu.missionutils.Console;
import christmas.EventPlannerIO.OutputView;

import christmas.EventPlannerIO.InputViewValidator.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class InputView {

    private final OutputView printer;
    private final InputViewValidator inputViewValidator;

    public InputView() {
        this.printer = new OutputView();
        this.inputViewValidator = new InputViewValidator();
    }

//
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

//- 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
//- 음료만 주문 시, 주문할 수 없습니다.
//- 메뉴는 한 번에 최대 20개까지만 주문할 수 있습니다.
    public List<String> readMenu() {
        while (true) {
            try {
                printer.printAskMenu();
                String inputMenu = Console.readLine();
                InputViewValidator.validateIsEmpty(inputMenu);
                List<String> inputMenuCopy = cutMenuList(inputMenu);
                inputViewValidator.validateMenu(inputMenuCopy.size(), inputMenu);
                return inputMenuCopy;
            } catch (IllegalArgumentException | IllegalStateException e) {
                printer.printErrorMessage(e);
            }
        }
        // ...
        //int값 리턴
    }
    private final String cutRegex = ",";
    private List<String> cutMenuList(String menu) {
        List<String> menuCopy = Arrays.stream(menu.split(cutRegex)).toList();
        return menuCopy;
    }

    // ...
}

// Menu items 객체를 활용해보자 lottos 객체처럼 생각하자.