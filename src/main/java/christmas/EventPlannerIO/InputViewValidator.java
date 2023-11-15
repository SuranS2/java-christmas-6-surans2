package christmas.EventPlannerIO;

import static christmas.EventPlannerIO.InputViewErrorMessages.*;
import static christmas.Menu.Appetizer.*;
import static christmas.Menu.MainDish.*;
import static christmas.Menu.Dessert.*;
import static christmas.Menu.Drink.*;

import christmas.Menu.Drink;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.IllegalFormatWidthException;
import java.util.List;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputViewValidator {


    public InputViewValidator() {
    }

    private final int ONE = 1;
    private final int THIRTYONE = 31;

    private final Pattern DATE_FORMAT = Pattern.compile("^[\\D]*$");

    //리팩시 아래에 있는 컴파일 활용.
    public int validateDate(String date) {
        if (DATE_FORMAT.matcher(date).matches()) {
            throw new IllegalStateException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_OUTOFRANGE.getErrorMessage());
        }
        int dateCopy = Integer.parseInt(date);
        if (ONE > dateCopy || dateCopy > THIRTYONE) {
            throw new IllegalArgumentException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_OUTOFRANGE.getErrorMessage());
        }
        return dateCopy;
    }


    private Pattern inputMenuFormatComplie() {
        final Pattern INPUT_MENU_FORMAT = Pattern.compile("^[ㄱ-ㅎ가-힣]*-\\d+(,[ㄱ-ㅎ가-힣]*-\\d+)*$");
        return INPUT_MENU_FORMAT;
    }



    public void validateMenu(String menu) {

//view 검증 , 메뉴의 개수는 1 이상의 숫자, 메뉴 형식 따라야함
        if (!inputMenuFormatComplie().matcher(menu).matches()) {
            throw new IllegalStateException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_NOTVALIDMENU.getErrorMessage());

        }
    }
    public void validateOnlyDrink(List<String> menuNames, List<String> drinkList) {
        menuNames.removeAll(drinkList);
        // != 연산부분 포장해서 true값으로 작업할것?
        if (menuNames.isEmpty()) {
            throw new IllegalArgumentException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_NOTVALIDMENU.getErrorMessage());
        }
    }

    public void validateNotInMenu(List<String> menuNames, List<String> appetizerList, List<String> mainList,
                                  List<String> dessertList, List<String> drinkList) {
        List<String> allMenu = new ArrayList<>();
        allMenu.addAll(appetizerList);
        allMenu.addAll(mainList);
        allMenu.addAll(dessertList);
        allMenu.addAll(drinkList);
        if (!allMenu.containsAll(menuNames)) {
            System.out.println("notinmenu");
            throw new IllegalStateException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_NOTVALIDMENU.getErrorMessage());

        }
    }


    public void validateMenuDuplicate(List<String> menuNames) {
        //형변환
        Set<String> checkDuplicateMenu = new HashSet<>(menuNames);
        if (menuNames.size() != checkDuplicateMenu.size()) {
            throw new IllegalArgumentException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_NOTVALIDMENU.getErrorMessage());
        }
    }


    private final int LIMIT_MENU_NUMBERS = 20;

    public void validateMenuCount(List<Integer> menuCount) {
        //형변환
        if (menuCount.stream().mapToInt(Integer::intValue).sum() > 20) {
            throw new IllegalArgumentException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_NOTVALIDMENU.getErrorMessage());
        }
    }

    public static void validateIsEmpty(String validateTarget) {
        if (isEmpty(validateTarget)) {
            throw new IllegalArgumentException(OUTPUT_ERROR.getErrorMessage()
                    + OUTPUT_ERROR_SPACE.getErrorMessage()
                    + OUTPUT_ERROR_NOTVALIDMENU.getErrorMessage());
        }
    }

    private static boolean isEmpty(String validateTarget) {
        return validateTarget == null || validateTarget.isBlank();
    }


}
