package christmas.Menu;

public abstract class MenuInterface {
    private final String foodName;
    private final int foodPrice;
    private final int index;
    public String getAppetizerMenu() {
        return foodName ;
    }

    public MenuInterface(String foodName, int foodPrice, int index){
        this.foodName = foodName;
        this.foodPrice = foodPrice;
        this.index = index;
    }
}
