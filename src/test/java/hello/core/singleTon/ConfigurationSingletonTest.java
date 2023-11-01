package hello.core.singleTon;

import hello.core.AppConfig;
import hello.core.member.MemberRepository;
import hello.core.member.MemberServiceImpl;
import hello.core.order.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;


public class ConfigurationSingletonTest {

    @Test
    void configurationTest() {
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

        MemberServiceImpl memberService = ac.getBean("memberService", MemberServiceImpl.class);
        OrderServiceImpl orderService = ac.getBean("orderService", OrderServiceImpl.class);
        MemberRepository memberRepository = ac.getBean("memberRepository", MemberRepository.class);

        MemberRepository bean1 = memberService.getMemberRepository();
        MemberRepository bean2 = orderService.getMemberRepository();

        assertThat(bean1).isEqualTo(bean2);
        assertThat(bean2).isSameAs(memberRepository);

    }

    @Test
    void name() {
        // AppConfig 설정 파일도 spring bean 으로 등록됨
        ApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);
        AppConfig bean = ac.getBean(AppConfig.class);

        System.out.println("bean : " + bean.getClass()); // 출력 결과 : bean =class hello.core.AppConfig$$EnhancerBySpringCGLIB$$d05c737
        /*순수 class 파일의 경우 class hello.core.AppConfig 이렇게 출력 되어야 함.
        * 이것은 내가 만든 클래스가 아니라 스프링이 CGLIB 라는 바이트코드 조작 라이브러리를 사용해서
        * AppConfig 클래스를 상속받은 임의의 다른 클래스를 만들고, 그 다른 클래스를 스프링 빈으로 등록한 것
        * */
    }
}
