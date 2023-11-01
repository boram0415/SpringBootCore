package hello.core.singleTon;

import hello.core.AppConfig;
import hello.core.member.MemberService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

public class SingleToneTest {


    @Test
    @DisplayName("스프링 없는 순수한 DI 컨테이너")
    void pureContainer() {
        AppConfig appConfig = new AppConfig();
        //1.조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = appConfig.memberService();
        //2.조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = appConfig.memberService();
        // 참조 값이 다른것을 확인
        System.out.println("memberService1 = " + memberService1);
        System.out.println("memberService1 = " + memberService2);
        // 호출할 때마나 새로운 객체 생성 함
        assertThat(memberService1).isNotSameAs(memberService2);

    }

    @Test
    @DisplayName("싱글톤 패턴을 적용한 객체 사용")
    void singleToneServiceTest() {
        SingleToneService instance1 = SingleToneService.getInstance();
        SingleToneService instance2 = SingleToneService.getInstance();
        assertThat(instance1).isEqualTo(instance2);
    }
    @Test
    @DisplayName("스프링 컨테이너와 싱글톤")
    void springContainer() {
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        //1.조회 : 호출할 때 마다 객체를 생성
        MemberService memberService1 = ac.getBean("memberService",MemberService.class);
        //2.조회 : 호출할 때 마다 객체를 생성
        MemberService memberService2 = ac.getBean("memberService",MemberService.class);
        // 호출할 때마나 새로운 객체 생성 함
        assertThat(memberService1).isSameAs(memberService2);

    }


}
