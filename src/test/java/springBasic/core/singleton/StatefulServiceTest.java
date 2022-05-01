package springBasic.core.singleton;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;

public class StatefulServiceTest {


    @Test
    void statefulServiceSingleton(){

        ApplicationContext ac = new AnnotationConfigApplicationContext(TestConfig.class);
        StatefulService statefulService = ac.getBean("statefulService", StatefulService.class);
        
        // ThreadA : 10000원에 주문
        int priceA = statefulService.order("userA", 10000);
        // ThreadB : 20000원에 주문
        int priceB = statefulService.order("userB", 20000);


//        System.out.println("price = " +  statefulService.getPrice());

        Assertions.assertThat(priceA).isEqualTo(10000);


    }


    static class TestConfig{

        @Bean
        public StatefulService statefulService(){
            return new StatefulService();
        }
    }

}
