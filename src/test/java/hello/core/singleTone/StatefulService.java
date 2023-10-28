package hello.core.singleTone;

public class StatefulService {

    private int price; //상태를 유지하는 필드

    public void order(String name, int price) {
        System.out.println("name = " + name + "price " + price);
        this.price = price; // 여기가 실무에서 종종 나타나는 문제
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
