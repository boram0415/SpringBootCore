package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberRepository;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
// 중복을 제거하고, 역할에 따른 구현이 보이도록 리팩터링 (구현체를 그대로 생성하기보다는 추상체(인터페이스)의 하위 구현체들을 유동적으로 변경할 수 있도록 한다.)
// @Configuration 과 @Bean 의 조합으로 싱글톤을 보장하는 경우는 정적이지 않은 메서드일 때입니다.
public class AppConfig { // 공연 기획자 (DI Container)


    // 생성자 주입으로 인한 DIP 원칙에 위배되지 않음
    @Bean
    public MemberService memberService() {
        return new MemberServiceImpl(memberRepository());
    }

    @Bean
    public OrderService orderService() {
        System.out.println("2");
        return new OrderServiceImpl(memberRepository(), discountPolicy());
    }

    @Bean
    public MemberRepository memberRepository() {
        System.out.println("3");
        return new MemoryMemberRepository();
    }
    @Bean
    public DiscountPolicy discountPolicy() {
        System.out.println("4");
        return new RateDiscountPolicy();
    }




}


