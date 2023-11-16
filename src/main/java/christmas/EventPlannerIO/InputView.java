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


    public Map<String, Integer> readMenu(List<String> allMenu, List<String> drinkList) {
        while (true) {
            try {
                String inputMenu = Console.readLine();
                InputViewValidator.validateIsEmpty(inputMenu);
                inputViewValidator.validateMenu(inputMenu);
                Map<String, Integer> menuItems = checkMenu(inputMenu, allMenu, drinkList);
                return menuItems;
            } catch (IllegalArgumentException | IllegalStateException e) {
                printer.printErrorMessage(e);
            }
        }
        // ...
        //int값 리턴
    }

    private Map<String, Integer> checkMenu(String inputMenu, List<String> allMenu, List<String> drinkList) {
        List<String> cutByCommaMenu = cutMenuList(inputMenu);
        List<String> cutByHypenMenu = cutByHypen(cutByCommaMenu);
        List<String> menuNames = getMenuNames(cutByHypenMenu);
        List<Integer> menuCount = getMenuCount(cutByHypenMenu);
        inputViewValidator.validateMenuDuplicate(menuNames);
        inputViewValidator.validateNotInMenu(menuNames, allMenu);
        inputViewValidator.validateOnlyDrink(menuNames, drinkList);
        inputViewValidator.validateMenuCount(menuCount);
        return changeToMenuItems(cutByHypenMenu);
    }

    private Map<String, Integer> changeToMenuItems(List<String> cutByHypenMenu) {
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

    public void inputStop() {
        Console.close();
    }
    // ...
}

