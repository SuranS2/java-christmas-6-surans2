package christmas.EventPlannerIO;


import camp.nextstep.edu.missionutils.Console;
import christmas.EventPlannerIO.OutputView;

import christmas.EventPlannerIO.InputViewValidator.*;
import christmas.Menu.Appetizer;
import christmas.Menu.Dessert;
import christmas.Menu.Drink;
import christmas.Menu.MainDish;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class InputView {
    private final List<String> appetizerList = Stream.of(Appetizer.values()).map(m -> m.getAppetizerName())
            .collect(Collectors.toList());
    private final List<String> mainList = Stream.of(MainDish.values()).map(m -> m.getMainDishName())
            .collect(Collectors.toList());
    private final List<String> dessertList = Stream.of(Dessert.values()).map(m -> m.getDessertName())
            .collect(Collectors.toList());
    private final List<String> drinkList = Stream.of(Drink.values()).map(m -> m.getDrinkName())
            .collect(Collectors.toList());

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
    public Map<String, Integer> readMenu() {
        while (true) {
            try {
                String inputMenu = Console.readLine();
                InputViewValidator.validateIsEmpty(inputMenu);
                inputViewValidator.validateMenu(inputMenu);

                List<String> cutByCommaMenu = cutMenuList(inputMenu);
                List<String> cutByHypenMenu = cutByHypen(cutByCommaMenu);
                List<String> menuNames = getMenuNames(cutByHypenMenu);
                List<Integer> menuCount = getMenuCount(cutByHypenMenu);
                inputViewValidator.validateMenuDuplicate(menuNames);
                inputViewValidator.validateOnlyDrink(menuNames, drinkList);
                inputViewValidator.validateNotInMenu(menuNames, appetizerList, mainList, dessertList, drinkList);
                inputViewValidator.validateMenuCount(menuCount);

                Map<String, Integer> menuItems = changeToMenuItems(cutByHypenMenu);

                return menuItems;
            } catch (IllegalArgumentException | IllegalStateException e) {
                printer.printErrorMessage(e);
            }
        }
        // ...
        //int값 리턴
    }

    public Map<String, Integer> changeToMenuItems(List<String> cutByHypenMenu) {
        Map<String, Integer> menuItems = new HashMap<String, Integer>();
        for (int i = 0; i * 2 < cutByHypenMenu.size(); i++) {
            menuItems.put(cutByHypenMenu.get(i * 2), Integer.parseInt(cutByHypenMenu.get(i * 2 + 1)));
        }
        return menuItems;
    }

    private List<String> getMenuNames(List<String> menu) {
        List<String> menuNames = new ArrayList<String>();
        for (int i = 0; i * 2 < menu.size(); i++) {
            menuNames.add(menu.get(i * 2));
        }
        return menuNames;
    }

    private List<Integer> getMenuCount(List<String> menu) {
        List<Integer> menuCount = new ArrayList<Integer>();
        for (int i = 0; i * 2 < menu.size(); i++) {
            menuCount.add(Integer.parseInt(menu.get(i * 2 + 1)));
        }
        return menuCount;
    }


    private final String cutHypenRegex = "-";

    private List<String> cutByHypen(List<String> menu) {
        List<String> menuCopy = new ArrayList<String>();
        for (int i = 0; i < menu.size(); i++) {
            menuCopy.addAll(Arrays.stream(menu.get(i).split(cutHypenRegex)).toList());
        }
        return menuCopy;
    }

    private final String cutCommaRegex = ",";

    private List<String> cutMenuList(String menu) {
        List<String> menuCopy = Arrays.stream(menu.split(cutCommaRegex)).toList();
        return menuCopy;
    }

    // ...
}

