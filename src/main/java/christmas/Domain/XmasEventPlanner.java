package christmas.Domain;


import christmas.EventPlannerIO.InputViewValidator;
import christmas.Menu.Appetizer;
import christmas.Menu.Dessert;
import christmas.Menu.Drink;
import christmas.Menu.MainDish;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.EventPlannerIO.OutputView;
import christmas.EventPlannerIO.InputView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class XmasEventPlanner {
    // Menu items 객체를 활용해보자 lottos 객체처럼 생각하자.

    // 에피타이저 맵
    // 디저트맵
    // 드링크맵
    // 메인디쉬맵
    //LIST로 박아버리자



    private final InputView inputView;
    private final OutputView outputView;
    private final List<String> appetizerList = Stream.of(Appetizer.values()).map(m -> m.getAppetizerName())
            .collect(Collectors.toList());
    private final List<String> mainList = Stream.of(MainDish.values()).map(m -> m.getMainDishName())
            .collect(Collectors.toList());
    private final List<String> dessertList = Stream.of(Dessert.values()).map(m -> m.getDessertName())
            .collect(Collectors.toList());
    private final List<String> drinkList = Stream.of(Drink.values()).map(m -> m.getDrinkName())
            .collect(Collectors.toList());
    private final List<Integer> appetizerPriceList = Stream.of(Appetizer.values()).map(m -> m.getAppetizerPrice())
            .collect(Collectors.toList());
    private final List<Integer> mainPriceList = Stream.of(MainDish.values()).map(m -> m.getMainDishPrice())
            .collect(Collectors.toList());
    private final List<Integer> dessertPriceList = Stream.of(Dessert.values()).map(m -> m.getDessertPrice())
            .collect(Collectors.toList());
    private final List<Integer> drinkPriceList = Stream.of(Drink.values()).map(m -> m.getDrinkPrice())
            .collect(Collectors.toList());

    private final List<String> allMenu = new ArrayList<>();
    private final List<Integer> allPrice = new ArrayList<>();
    private final Map<String,Integer> allMenuInformation = new HashMap<>();



    public XmasEventPlanner(){
        addMenus();
        addPrices();
        getAllMenuInformation();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        start();
        stop();
    }
    private void addMenus(){
        allMenu.addAll(appetizerList);
        allMenu.addAll(mainList);
        allMenu.addAll(dessertList);
        allMenu.addAll(drinkList);
    }
    private void addPrices(){
        allPrice.addAll(appetizerPriceList);
        allPrice.addAll(mainPriceList);
        allPrice.addAll(dessertPriceList);
        allPrice.addAll(drinkPriceList);
    }
    private void getAllMenuInformation(){
        for( int i = 0 ; i < allMenu.size(); i++){
            allMenuInformation.put(allMenu.get(i), allPrice.get(i));
        }
    }
    private void start(){

//        TestCode();
        outputView.printWelcomeMessage();
        outputView.printAskVisitDate();
        int date = inputView.readDate();

        outputView.printAskMenu();
        Map<String,Integer> menuItems = inputView.readMenu(allMenu,drinkList);
        outputView.printMenu(date);
        outputView.printEventPreview(menuItems);

    }
    //- 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.





    //입력값 검증 끝나면 stop
    private void stop(){

    }

}

