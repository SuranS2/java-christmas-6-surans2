package christmas.Domain;


import static christmas.EventPlannerIO.OutputViewMessages.*;
import static christmas.Menu.Drink.DRINK_CHAMPAGNE;

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
        eventInput();
        eventPreviewResult();
    }

    private Map<String, Integer> menuItems = new HashMap<>();
    private int date = 0;

    private void eventInput() {
//        TestCode();
        outputView.printWelcomeMessage();
        outputView.printAskVisitDate();
        date = inputView.readDate();
        outputView.printAskMenu();
        // menuItems의 Integer값은 갯수임
        menuItems = inputView.readMenu(allMenu, drinkList);

    }

    //    private void
    //- 총주문 금액 10,000원 이상부터 이벤트가 적용됩니다.
    private void eventPreviewResult() {
        outputView.printMenu(date);
        outputView.printEventPreview(menuItems);
        int priceAmount = calculateAmount(menuItems);
        outputView.printBeforeDiscountAmount(priceAmount);
        int giveAwayPrice = giveAwayStuff(priceAmount);
        outputView.printEventAdvantageNotice();
        int discountAmount =checkEventMinimumPriceAmount(priceAmount,giveAwayPrice);
        eventPredictResult(priceAmount,discountAmount,giveAwayPrice);
    }
    private int checkEventMinimumPriceAmount(int priceAmount,int giveAwayPrice){
        if(TEN_K_WON<=priceAmount){
            return eventAdvantage(priceAmount, date, giveAwayPrice);
        }
        outputView.printNotThing();
        return NOT_THING;
    }
    private void eventPredictResult(int priceAmount, int discountAmount,int giveAwayPrice){
        outputView.printTotalDiscountNotice();
        outputView.printEventDiscountAmount(NOT_THING-discountAmount);
        outputView.printPredictReceiptNotice();
        outputView.printPredictPrice(priceAmount - discountAmount + giveAwayPrice);
        outputView.printPredictBadgeNotice();
        outputView.printPredictBadge(checkBadge(discountAmount));
        inputView.inputStop();
    }

    private int calculateAmount(Map<String, Integer> menuItems) {
        int priceAmount = NOT_THING;
        for (String key : menuItems.keySet()) {
            priceAmount += allMenuInformation.get(key)*menuItems.get(key);
        }
        return priceAmount;
    }

    private static final int EVENT_MINIMUM_PRICE = 120_000;
    private static final int ONE = 1;
    private static final int NOT_THING = 0;

    //증정메뉴
    private int giveAwayStuff(int priceAmount) {
        outputView.printServiceListNotice();
        if (EVENT_MINIMUM_PRICE < priceAmount) {

            outputView.printServiceList();
            return DRINK_CHAMPAGNE.getDrinkPrice() * ONE;
        }
        outputView.printNotThing();
        return NOT_THING;
    }

    private static final int D_DAY = 25;
    private static final List<Integer> specialDay = Arrays.asList(3, 10, 17, 24, 25, 31);
    private static final int ONE_THOUSAND_WON = 1000;
    private static final int ONE_HUNDRED_WON = 100;
    private static final List<Integer> WEEKEND = Arrays.asList(1, 7);

    //혜택내역
    private int eventAdvantage(int priceAmount, int date, int servicePrice) {
        int discountAmount = NOT_THING;
        if (priceAmount <= TEN_K_WON){
            outputView.printNotThing();
            return discountAmount;
        }
        discountAmount = dDayDiscount(discountAmount);
        //주말이 포함되면 true 반납
        boolean checkWeekEnd = WEEKEND.contains(getDateDay("2023" + "12" + Integer.toString(date)));
        discountAmount = weekDayDiscount(checkWeekEnd, discountAmount, menuItems);
        discountAmount = weekEndDiscount(checkWeekEnd, discountAmount, menuItems);
        discountAmount = specialDayDiscount(discountAmount);
        discountAmount = giveAwayPriceDiscount(discountAmount, servicePrice);
        if (discountAmount == NOT_THING) {
            outputView.printNotThing();
        }
        return discountAmount;
    }



    private int dDayDiscount(int discountAmount) {
        int discount = NOT_THING;
        if (date <= D_DAY) {
            discount = ONE_THOUSAND_WON + ONE_HUNDRED_WON * (date - ONE);
            discountAmount += discount;
        }
        if(discountAmount !=NOT_THING){
            outputView.printEventAdvantageMessage(OUTPUT_CHRISTMAS_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }

    //평일할인 디저트
    private int weekDayDiscount(boolean checkWeekEnd, int discountAmount, Map<String, Integer> menuItems) {
        int discount = NOT_THING;
        if (!checkWeekEnd) {
            discount = calculateWeekDayDiscount(menuItems);
            discountAmount += discount;
        }
        if(discount !=NOT_THING){
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
            discountAmount += checkIsNotNULL(menuItems,key);
        }
        return discountAmount;
    }
    private int checkIsNotNULL(Map<String, Integer> menuItems, String key){
        if(menuItems.get(key) == null) {
            return NOT_THING;
        }
        return SPECIAL_YEAR * menuItems.get(key);
    }

    //주말할인 메인메뉴
    private int weekEndDiscount(boolean checkWeekEnd, int discountAmount, Map<String, Integer> menuItems) {
        int discount = NOT_THING;
        if (checkWeekEnd) {
            discount = calculateWeekEndDiscount(menuItems);
            discountAmount += discount;
        }
        if(discount !=NOT_THING){
            outputView.printEventAdvantageMessage(OUTPUT_WEEKEND_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }

    //메인 할인
    private int calculateWeekEndDiscount(Map<String, Integer> menuItems) {
        int discountAmount = NOT_THING;
        for (String key : mainMenu.keySet()) {
            //menuItems.get으로 디저트 메뉴의 갯수를 가져옵니다.
            discountAmount += checkIsNotNULL(menuItems, key);
        }
        return discountAmount;
    }

    private int specialDayDiscount(int discountAmount) {
        int discount = NOT_THING;
        if (specialDay.contains(date)) {
            discount = ONE_THOUSAND_WON;
            discountAmount += discount;
        }
        if(discount !=NOT_THING){
            outputView.printEventAdvantageMessage(OUTPUT_SPECIALDAY_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }

    private int giveAwayPriceDiscount(int discountAmount, int servicePrice) {
        if (servicePrice != NOT_THING) {
            int discount = servicePrice;
            discountAmount += discount;
            outputView.printEventAdvantageMessage(OUTPUT_GIVEAWAY_DISCOUNT_MESSAGE.getMessage(), discount);
        }
        return discountAmount;
    }

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

    private static final int TWENTY_K_WON = 20_000;
    private static final int TEN_K_WON = 10_000;
    private static final int FIVE_K_WON = 5_000;

    private String checkBadge(int discountAmount) {
        if (TWENTY_K_WON <= discountAmount) {
            return OUTPUT_EVENT_BADGE_SANTA.getMessage();
        }
        if (TEN_K_WON <= discountAmount) {
            return OUTPUT_EVENT_BADGE_TREE.getMessage();
        }
        if (FIVE_K_WON <= discountAmount) {
            return OUTPUT_EVENT_BADGE_STAR.getMessage();
        }
        return OUTPUT_NOTTHING.getMessage();
    }



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

