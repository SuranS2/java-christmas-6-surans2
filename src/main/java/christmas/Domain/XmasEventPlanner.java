package christmas.Domain;


import static christmas.EventPlannerIO.OutputViewMessages.*;
import static christmas.Menu.Drink.DRINK_CHAMPAGNE;

import christmas.EventPlannerIO.*;
import christmas.Menu.Appetizer;
import christmas.Menu.Dessert;
import christmas.Menu.Drink;
import christmas.Menu.MainDish;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import christmas.EventPlannerIO.OutputView;
import christmas.EventPlannerIO.InputView;
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
    private final Map<String, Integer> apptizerMenu = new HashMap<>();
    private final Map<String, Integer> mainMenu = new HashMap<>();
    private final Map<String, Integer> dessertMenu = new HashMap<>();
    private final Map<String, Integer> drinkMenu = new HashMap<>();

    private final List<String> allMenu = new ArrayList<>();
    private final List<Integer> allPrice = new ArrayList<>();
    private final Map<String, Integer> allMenuInformation = new HashMap<>();


    public XmasEventPlanner() {
        addMenus();
        addPrices();
        getAllMenuInformation();
        getAppetizerMenu();
        getMainMenu();
        getDessertMenu();
        getDrinkMenu();
        this.inputView = new InputView();
        this.outputView = new OutputView();
        start();
        eventPreviewResult();
    }

    private Map<String, Integer> menuItems = new HashMap<>();
    private int date = 0;

    private void start() {
//        TestCode();
        outputView.printWelcomeMessage();
        outputView.printAskVisitDate();
        date = inputView.readDate();
        outputView.printAskMenu();
        // menuItems의 Integer값은 갯수임
        menuItems = inputView.readMenu(allMenu, drinkList);


    }

    private void eventPreviewResult() {
        outputView.printMenu(date);
        outputView.printEventPreview(menuItems);
        int priceAmount = calculateAmount(menuItems);
        outputView.printBeforeDiscountAmount(priceAmount);
        int servicePrice = sertviceStuff(priceAmount);
        outputView.printEventAdvantageNotice();
        int discountAmount = eventAdvantage(date, servicePrice);
        outputView.printTotalDiscountNotice();
        outputView.printEventDiscountAmount(discountAmount);
    }

    private int calculateAmount(Map<String, Integer> menuItems) {
        int priceAmount = NOT_THING;
        for (String key : menuItems.keySet()) {
//            System.out.println("작동중");
//            System.out.println(allMenuInformation.get(key));
            priceAmount += allMenuInformation.get(key);
        }
        return priceAmount;
    }

    private static final int EVENT_MINIMUM_PRICE = 120_000;
    private static final int ONE = 1;
    private static final int NOT_THING = 0;

    private int sertviceStuff(int priceAmount) {
        if (EVENT_MINIMUM_PRICE < priceAmount) {
            outputView.printServiceList();
            return DRINK_CHAMPAGNE.getDrinkPrice() * ONE;
        }
        outputView.printNotThing();
        return NOT_THING;
    }

    private final int D_DAY = 25;
    private final List<Integer> specialDay = Arrays.asList(3, 10, 17, 24, 25, 31);
    private final int ONE_THOUSAND_WON = 1000;
    private final int ONE_HUNDRED_WON = 100;
    private final List<Integer> WEEKEND = Arrays.asList(1, 7);

    private int eventAdvantage(int date, int servicePrice) {
        int discountAmount = NOT_THING;
        discountAmount=dDayDiscount(discountAmount);
        //주말이 포함되면 true 반납
        boolean checkWeekEnd = WEEKEND.contains(getDateDay("2023" + "12" + Integer.toString(date)));
        discountAmount=weekDayDiscount(checkWeekEnd,discountAmount,menuItems);
        discountAmount=weekEndDiscount(checkWeekEnd,discountAmount,menuItems);
        discountAmount=specialDayDiscount(discountAmount);
        discountAmount=servicePriceDiscount(discountAmount,servicePrice);
        if (discountAmount != NOT_THING) {
            return discountAmount;
        }
        outputView.printNotThing();
        return NOT_THING;
    }

    private int dDayDiscount(int discountAmount) {
        if (date <= D_DAY) {
            int discount = ONE_THOUSAND_WON + ONE_HUNDRED_WON * (date - ONE);
            discountAmount += discount;
            outputView.printEventAdvantageMessage(OUTPUT_CHRISTMAS_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }
    //완료

    private int weekDayDiscount(boolean checkWeekEnd, int discountAmount,Map<String, Integer> menuItems) {
        if (!checkWeekEnd) {
            int discount = calculateWeekDayDiscount(menuItems);
            discountAmount += discount;
            outputView.printEventAdvantageMessage(OUTPUT_WEEKDAY_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }

    private static final int SPECIAL_YEAR = 2023;
    //디저트 할인
    private int calculateWeekDayDiscount(Map<String, Integer> menuItems) {
        int discountAmount = NOT_THING;
        for (String key : dessertMenu.keySet()) {
            //menuItems.get으로 디저트 메뉴의 갯수를 가져옵니다.
            discountAmount += SPECIAL_YEAR*menuItems.get(key);
        }
        return discountAmount;
    }


    private int weekEndDiscount(boolean checkWeekEnd, int discountAmount, Map<String, Integer> menuItems) {
        if (checkWeekEnd) {
            int discount = calculateWeekEndDiscount(menuItems);
            discountAmount += discount;
            outputView.printEventAdvantageMessage(OUTPUT_WEEKEND_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }
    //메인 할인
    private int calculateWeekEndDiscount(Map<String, Integer> menuItems) {
        int discountAmount = NOT_THING;
        for (String key : mainMenu.keySet()) {
            //menuItems.get으로 디저트 메뉴의 갯수를 가져옵니다.
            discountAmount += SPECIAL_YEAR*menuItems.get(key);
        }
        return discountAmount;
    }

    private int specialDayDiscount(int discountAmount) {
        if (specialDay.contains(date)) {
            int discount = ONE_THOUSAND_WON;
            discountAmount += discount;
            outputView.printEventAdvantageMessage(OUTPUT_SPECIALDAY_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }
    //완료

    private int servicePriceDiscount(int discountAmount, int servicePrice) {
        if (servicePrice != NOT_THING) {
            int discount = servicePrice;
            discountAmount += discount;
            outputView.printEventAdvantageMessage(OUTPUT_GIVEAWAY_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }
    //완료

    private int getDateDay(String date) {
        Calendar cal = Calendar.getInstance();
        int dayNum = NOT_THING;
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMdd"); //date format 일치해야함
        Date nDate = null;
        try {
            nDate = dateFormat.parse(date);
            cal.setTime(nDate);
            dayNum = cal.get(Calendar.DAY_OF_WEEK);
            cal = null;
            cal = Calendar.getInstance();
        } catch (ParseException e) {
            //매개변수 date 와 pattern format 과 맞지 않으면 예외
            e.printStackTrace();
        }
        return dayNum;
    }

//    private void
    //- 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.


    private void addMenus() {
        allMenu.addAll(appetizerList);
        allMenu.addAll(mainList);
        allMenu.addAll(dessertList);
        allMenu.addAll(drinkList);
    }

    private void addPrices() {
        allPrice.addAll(appetizerPriceList);
        allPrice.addAll(mainPriceList);
        allPrice.addAll(dessertPriceList);
        allPrice.addAll(drinkPriceList);
    }

    private void getAllMenuInformation() {
        for (int i = 0; i < allMenu.size(); i++) {
            allMenuInformation.put(allMenu.get(i), allPrice.get(i));
        }
    }
    private void getAppetizerMenu() {
        for (int i = 0; i < appetizerList.size(); i++) {
            apptizerMenu.put(appetizerList.get(i), appetizerPriceList.get(i));
        }
    }
    private void getMainMenu() {
        for (int i = 0; i < mainList.size(); i++) {
            mainMenu.put(mainList.get(i), mainPriceList.get(i));
        }
    }
    private void getDessertMenu() {
        for (int i = 0; i < dessertList.size(); i++) {
            dessertMenu.put(dessertList.get(i), dessertPriceList.get(i));
        }
    }
    private void getDrinkMenu() {
        for (int i = 0; i < drinkList.size(); i++) {
            drinkMenu.put(drinkList.get(i), drinkPriceList.get(i));
        }
    }

}

