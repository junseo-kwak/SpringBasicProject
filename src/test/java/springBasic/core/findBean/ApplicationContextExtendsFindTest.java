package springBasic.core.findBean;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.annotation.Testable;
import org.springframework.beans.factory.NoUniqueBeanDefinitionException;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springBasic.core.discount.DisCountPolicy;
import springBasic.core.discount.FixDiscountPolicy;
import springBasic.core.discount.RateDiscountPolicy;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

public class ApplicationContextExtendsFindTest {

    AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);



    @Test
    @DisplayName("부모타입으로 조회 시 자식이 둘 이상 있으면 에러가 발생한다")
    void findBeanByParentTypeDuplicate(){
        Assertions.assertThrows(NoUniqueBeanDefinitionException.class,
                                    () ->  ac.getBean(DisCountPolicy.class));

    }

    @Test
    @DisplayName("부모타입으로 조회 시 자식이 둘 이상 있으면, 빈 이름을 지정하면 된다")
    void findBeanByParentTypeBeanName(){
        DisCountPolicy rateDiscountPolicy = ac.getBean("rateDiscountPolicy", DisCountPolicy.class);
        assertThat(rateDiscountPolicy).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("특정 하위 타입으로 조회")
    void findBeanBySubType(){
        RateDiscountPolicy findBean = ac.getBean(RateDiscountPolicy.class);
        assertThat(findBean).isInstanceOf(RateDiscountPolicy.class);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기")
    void findAllBeanByParentType(){
        Map<String, DisCountPolicy> beansOfType = ac.getBeansOfType(DisCountPolicy.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "  value = " + beansOfType.get(key));
        }
        assertThat(beansOfType.size()).isEqualTo(2);
    }

    @Test
    @DisplayName("부모 타입으로 모두 조회하기 - Object")
    void findAllBeanByObjectType(){
        Map<String, Object> beansOfType = ac.getBeansOfType(Object.class);

        for (String key : beansOfType.keySet()) {
            System.out.println("key = " + key + "  value = " + beansOfType.get(key));
        }
    }



    @Configuration
    static class TestConfig {

        @Bean
        DisCountPolicy fixDiscountPolicy(){
            return new FixDiscountPolicy();
        }

        @Bean
        DisCountPolicy rateDiscountPolicy(){
            return new RateDiscountPolicy();
        }


    }

}
