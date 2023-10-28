package hello.core.beanFind;

import hello.core.AppConfig;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class ApplicationContextInfoTest {


    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(AppConfig.class);

    @Test
    @DisplayName("모든 빈 출력하기")
    void findAllBean() {
        String[] getBeanDefinitionNames = ac.getBeanDefinitionNames();
        for (String bc : getBeanDefinitionNames) {
            Object bean = ac.getBean(bc);
            System.out.println("name = " + bc + " object =" + bean);
        }
    }

    @Test
    @DisplayName("애플리케이션 빈 출력하기")
        // spring 내부 bean 제외 후 내가 설정한 bean 만 볼 수 있음
    void findApplicationBean() {
        String[] beanDefinitionNames = ac.getBeanDefinitionNames();
        for (String bc : beanDefinitionNames) {
            BeanDefinition beanDefinition = ac.getBeanDefinition(bc);

            //Role ROLE_APPLICATION: 직접 등록한 애플리케이션 빈
            //Role ROLE_INFRASTRUCTURE: 스프링이 내부에서 사용하는 빈
            if (beanDefinition.getRole() == BeanDefinition.ROLE_APPLICATION) {
                Object bean = ac.getBean(bc);
                System.out.println("name = " + bc + " object =" + bean);
            }

        }
    }
}
