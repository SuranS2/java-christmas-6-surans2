package christmas.EventPlannerIO;

import static christmas.EventPlannerIO.OutputViewMessages.*;
import static christmas.Menu.Appetizer.*;
import static christmas.Menu.MainDish.*;
import static christmas.Menu.Dessert.*;
import static christmas.Menu.Drink.*;

public class OutputView {
    private static final String NEWLINE = "\n";
    private final OutputViewPrinter printer;
    private static final int DESEMBER = 12;
    private final int ONE = 1;
    private final int TWO = 2;

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
                ONE,
                MAIN_STEAK.getMainDishName(),
                ONE,
                MAIN_REDWINE.getDrinkName(),
                TWO
                ));
    }


    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }
    // ...

        public void printErrorMessage(Exception exception) {
        printer.printLine(exception.getMessage());
    }


}


