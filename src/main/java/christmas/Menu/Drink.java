package christmas.Menu;

public enum Drink {
    MAIN_REDWINE("레드와인", 60_000, 1),
    MAIN_CHAMPAGNE("샴페인", 25_000, 2),
    MAIN_ZERO_COKE("제로콜라", 3_000, 3),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25_000, 4);
    private final String foodName;
    private final int foodPrice;
    private final int foodIndex;

    public String getDrinkName() {
        return foodName ;
    }
    public int getDrinkPrice() {
        return foodPrice ;
    }
    public int getDrinkIndex() {
        return foodIndex ;
    }



    Drink(String foodName, int foodPrice, int foodIndex) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodIndex = foodIndex;
    }

}
