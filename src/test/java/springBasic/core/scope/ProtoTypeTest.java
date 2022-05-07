package springBasic.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class ProtoTypeTest {

    @Test
    public void protoTypeBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ProtoTypeBean.class);

        System.out.println("create protoTypeBean1");
        ProtoTypeBean protoTypeBean1 = ac.getBean(ProtoTypeBean.class);
        System.out.println("create protoTypeBean2");
        ProtoTypeBean protoTypeBean2 = ac.getBean(ProtoTypeBean.class);

        Assertions.assertThat(protoTypeBean1).isNotSameAs(protoTypeBean2);

        ac.close();
    }

    @Scope("prototype")
    static class ProtoTypeBean{

        @PostConstruct
        void init(){
            System.out.println("ProtoTypeBean.init");
        }

        @PreDestroy
        void destroy(){
            System.out.println("ProtoTypeBean.destroy");
        }
    }



}
