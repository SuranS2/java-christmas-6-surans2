package christmas.Menu;

public enum Dessert {
    DESSERT_CAKE("초코케이크", 15_000, 1),
    DESSERT_ICECREAM("아이스크림", 5_000, 2);
    private final String foodName;
    private final int foodPrice;
    private final int foodIndex;

    public String getDessertName() {
        return foodName ;
    }
    public int getDessertPrice() {
        return foodPrice ;
    }
    public int getDessertIndex() {
        return foodIndex ;
    }



    Dessert(String foodName, int foodPrice, int foodIndex) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodIndex = foodIndex;
    }
}
