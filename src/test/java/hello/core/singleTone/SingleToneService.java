package hello.core.singleTone;

public class SingleToneService {

    // static 은 클래스 레벨에 올라오기 때문에 딱 1개만 존재함
    private static final SingleToneService instance = new SingleToneService();

    public static SingleToneService getInstance() {
        return instance;
    }

    private SingleToneService(){

    }

    public void logic(){
        System.out.println("싱글톤 객체 로직 호출");
    }

}
