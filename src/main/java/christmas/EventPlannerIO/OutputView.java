package christmas.EventPlannerIO;

import static christmas.EventPlannerIO.OutputViewMessages.*;

public class OutputView {
    private static final String NEWLINE = "\n";
    private final OutputViewPrinter printer;
    private static final int DESEMBER = 12;

    public OutputView() {
        this.printer = new OutputViewPrinter();
        ;
    }

    public void printWelcomeMessage() {
        printer.printLine(OUTPUT_HELLO.getMessage() + OUTPUT_WELCOME.getFormattedMessage(DESEMBER));
    }

    public void printAskVisitDate() {
        printer.printLine(OUTPUT_ASK_DATE.getFormattedMessage(DESEMBER) + OUTPUT_DATE_ARGUMENT_LIMIT.getMessage());
    }


    public void printMenu() {
        System.out.println("<주문 메뉴>");
        // ...
    }
    // ...

}

//    public void printErrorMessage(Exception exception) {
//        printer.printLine(exception.getMessage());
//    }

