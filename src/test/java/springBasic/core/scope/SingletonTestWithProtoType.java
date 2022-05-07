package springBasic.core.scope;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.ObjectProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

public class SingletonTestWithProtoType {

    @Test
    void protoTypeFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(ClientBean.class,ProtoTypeBean.class);

        ClientBean clientBean1 = ac.getBean(ClientBean.class);
        int count1 = clientBean1.logic();
        ClientBean clientBean2 = ac.getBean(ClientBean.class);
        int count2 = clientBean2.logic();

        Assertions.assertThat(count1).isEqualTo(1);
        Assertions.assertThat(count2).isEqualTo(1);


    }

    @Scope("singleton")
    static class ClientBean{
        @Autowired
        private ObjectProvider<ProtoTypeBean> protoTypeBeanProvider;

        public int logic(){
            ProtoTypeBean protoTypeBean = protoTypeBeanProvider.getObject();
            protoTypeBean.addCount();
            return protoTypeBean.getCount();
        }


    }



    @Scope("prototype")
    static class ProtoTypeBean{
        private int count = 0;

        public void addCount(){
            count++;
        }

        public int getCount(){
            return count;
        }

        @PostConstruct
        public void init(){
            System.out.println("protoTypeBean.init  " + this);
        }

        @PreDestroy
        public void destroy(){
            System.out.println("protoTypeBean.destroy");
        }

    }


}
