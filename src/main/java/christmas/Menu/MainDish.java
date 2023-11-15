package christmas.Menu;

public enum MainDish {
    MAIN_STEAK("티본스테이크", 55_000, 1),
    MAIN_SOUP("바비큐립", 54_000, 2),
    MAIN_SEAFOOD_PASTA("해산물파스타", 35_000, 3),
    MAIN_CHRISTMAS_PASTA("크리스마스파스타", 25_000, 4);
    private final String foodName;
    private final int foodPrice;
    private final int foodIndex;

    public String getMainDishName() {
        return foodName ;
    }
    public int getMainDishPrice() {
        return foodPrice ;
    }
    public int getMainDishIndex() {
        return foodIndex ;
    }



    MainDish(String foodName, int foodPrice, int foodIndex) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodIndex = foodIndex;
    }

}
