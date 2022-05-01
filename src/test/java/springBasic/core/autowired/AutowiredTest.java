package springBasic.core.autowired;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.lang.Nullable;
import springBasic.core.member.Member;

import java.util.Optional;

public class AutowiredTest {

    @Test
    void autoWiredOption(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(TestBean.class);
    }

    @Configuration
    static class TestBean{

        @Autowired(required = false)
        public void setNoBean1(Member noBean1){
            System.out.println(noBean1);
        }

        @Autowired
        public void setNoBean2(@Nullable Member noBean2){
            System.out.println(noBean2);
        }


        @Autowired(required = false)
        public void setNoBean3(Optional<Member> noBean3){
            System.out.println(noBean3);
        }



    }






}
