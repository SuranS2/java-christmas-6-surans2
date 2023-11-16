package christmas.Menu;


public enum Appetizer {
    APPETIZER_SALAD("시저샐러드", 8_000, 1),
    APPETIZER_SOUP("양송이수프", 6_000, 2),
    APPETIZER_TAPAS("타파스", 5_500, 3);
    private final String foodName;
    private final int foodPrice;
    private final int foodIndex;

    public String getAppetizerName() {
        return foodName ;
    }
    public int getAppetizerPrice() {
        return foodPrice ;
    }
    public int getAppetizerIndex() {
        return foodIndex ;
    }



    Appetizer(String foodName, int foodPrice, int foodIndex) {
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.foodIndex = foodIndex;
    }

}

