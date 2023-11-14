package christmas.domain;

public enum Menu {
    양송이수프("Appetizer", "양송이수프", 6000),
    타파스("Appetizer", "타파스", 5500),
    시저샐러드("Appetizer", "시저샐러드", 8000),

    티본스테이크("main", "티본스테이크", 55000),
    바비큐립("main", "바비큐립", 54000),
    해산물파스타("main", "해산물파스타", 35000),
    크리스마스파스타("main", "크리스마스파스타", 25000),

    초코케이크("dessert", "초코케이크", 15000),
    아이스크림("dessert", "아이스크림", 5000),

    제로콜라("beverage", "제로콜라", 3000),
    레드와인("beverage", "레드와인", 60000),
    샴페인("beverage", "샴페인", 25000);

    private final String type;
    private final String name;
    private final int price;

    Menu(String type, String name, int price) {
        this.type = type;
        this.name = name;
        this.price = price;
    }

    public String getType(){
        return type;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }
}