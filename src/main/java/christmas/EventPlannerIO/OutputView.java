package christmas.EventPlannerIO;

import static christmas.EventPlannerIO.OutputViewMessages.*;
import static christmas.Menu.Appetizer.*;
import static christmas.Menu.MainDish.*;
import static christmas.Menu.Drink.*;

import java.util.Map;
import java.util.Map.Entry;

public class OutputView {
    private static final String NEWLINE = "\n";
    private final OutputViewPrinter printer;
    private static final int DESEMBER = 12;
    private static final int EXAMPLE_ONE = 1;
    private static final int EXAMPLE_TWO = 2;

    public OutputView() {
        this.printer = new OutputViewPrinter();
        ;
    }

    public void printWelcomeMessage() {
        printer.printLine(OUTPUT_HELLO.getMessage() + OUTPUT_WELCOME.getFormattedMessage(DESEMBER));
    }

    public void printAskVisitDate() {
        printer.printLine(NEWLINE + OUTPUT_ASK_DATE.getFormattedMessage(DESEMBER)
                + OUTPUT_DATE_ARGUMENT_LIMIT.getMessage());
    }

    public void printAskMenu() {
        printer.printLine(NEWLINE + OUTPUT_ASK_MENU_NUMBERS.getMessage());
        printer.printLine(OUTPUT_MENU_ARGUMENT_LIMIT.getFormattedMessage(
                APPETIZER_SALAD.getAppetizerName(),
                EXAMPLE_ONE,
                MAIN_STEAK.getMainDishName(),
                EXAMPLE_ONE,
                DRINK_REDWINE.getDrinkName(),
                EXAMPLE_TWO
        ));
    }

    //맵 출력법
//    for (Entry<Integer, String> entrySet : map.entrySet()) {
//        System.out.println(entrySet.getKey() + " : " + entrySet.getValue());
//    }
    public void printMenu(int date) {
        printer.printLine(NEWLINE + OUTPUT_EVENT_PREVIEW.getFormattedMessage(DESEMBER, date));
        printer.printLine(NEWLINE + OUTPUT_ORDER_LIST_MESSAGE.getMessage());
    }

    public void printEventPreview(Map<String, Integer> menuItems) {
        for (Entry<String, Integer> entrySet : menuItems.entrySet()) {
            //맵 자료에 개수가 0이 아니라면 출력
            menuItemsEmptyCheck(entrySet);
        }
    }

    private void menuItemsEmptyCheck(Entry<String, Integer> entrySet) {
        if (entrySet.getValue() != 0) {
            printer.printLine(OUTPUT_ORDER_LIST_EACH
                    .getFormattedMessage(entrySet.getKey(), entrySet.getValue()));
        }

    }

    public void printBeforeDiscountAmount(int money) {
        printer.printLine(NEWLINE + OUTPUT_RECEIPT_BEFORE_DISCOUNT_MESSAGE.getMessage());
        printer.printLine(OUTPUT_RECEIPT_BEFORE_DISCOUNT_PRICE.getFormattedMessage(money));
    }


    public void printServiceList() {
        printer.printLine(NEWLINE + OUTPUT_SERVICE_MENU_MESSAGE.getMessage());
        printer.printLine(
                OUTPUT_SERVICE_LIST.getFormattedMessage(DRINK_CHAMPAGNE.getDrinkName(), EXAMPLE_ONE));

    }


    public void printErrorMessage(Exception exception) {
        printer.printLine(exception.getMessage());
    }


}


