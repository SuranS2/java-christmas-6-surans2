package christmas.Domain;


import christmas.EventPlannerIO.InputViewValidator;
import christmas.Menu.Appetizer;
import christmas.Menu.Dessert;
import christmas.Menu.Drink;
import christmas.Menu.MainDish;
import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;

import christmas.EventPlannerIO.OutputView;
import christmas.EventPlannerIO.InputView;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class XmasEventPlanner {
    // Menu items 객체를 활용해보자 lottos 객체처럼 생각하자.

    // 에피타이저 맵
    // 디저트맵
    // 드링크맵
    // 메인디쉬맵
    //LIST로 박아버리자
    private final Map<Appetizer,Integer> appetizerIntegerMap=  new EnumMap<>(Appetizer.class);
    private final Map<MainDish,Integer> mainDishIntegerMap=  new EnumMap<>(MainDish.class);
    private final Map<Dessert,Integer> dessertIntegerMap=  new EnumMap<>(Dessert.class);
    private final Map<Drink,Integer> drinkIntegerMap=  new EnumMap<>(Drink.class);
    private final List<Map> menuItems =  new ArrayList<>();

    private final InputView inputView;
    private final OutputView outputView;

    public XmasEventPlanner(){
        this.inputView = new InputView();
        this.outputView = new OutputView();
        menuItems.add(appetizerIntegerMap);
        menuItems.add(mainDishIntegerMap);
        menuItems.add(dessertIntegerMap);
        menuItems.add(drinkIntegerMap);
        start();
        stop();
    }

    private void start(){
//        TestCode();
        outputView.printWelcomeMessage();
        outputView.printAskVisitDate();
        int date = inputView.readDate();
        outputView.printAskMenu();
        List<String> menu = inputView.readMenu();
        outputView.printMenu(date);;

    }
    //입력값 검증 끝나면 stop
    private void stop(){

    }

}

//    public void TestCode() {
//        String menuString = "시저샐러드-1,티본스테이크-1,레드와인-2";
//
//        if (validateSteakFormat(menuString)) {
//            System.out.println("입력된 스테이크 형식이 올바릅니다.");
//        } else {
//            System.out.println("입력된 스테이크 형식이 올바르지 않습니다.");
//        }
//    }
//
//    private boolean validateSteakFormat(String steakString) {
//        // 정규식 패턴
//        String regex = "^[ㄱ-ㅎ가-힣]*-\\d+(,[ㄱ-ㅎ가-힣]*-\\d+)*$";
//
//        // 패턴 컴파일
//        Pattern pattern = Pattern.compile(regex);
//
//        // 매처 생성
//        Matcher matcher = pattern.matcher(steakString);
//
//        // 패턴과 일치하는지 확인
//        return matcher.matches();
//    }

