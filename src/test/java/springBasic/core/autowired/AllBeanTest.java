package springBasic.core.autowired;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.util.Assert;
import springBasic.core.AutoAppConfig;
import springBasic.core.discount.DisCountPolicy;
import springBasic.core.member.Grade;
import springBasic.core.member.Member;

import java.util.List;
import java.util.Map;

// 같은 타입 여러빈 조회
public class AllBeanTest {

    @Test
    void findAllBean(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(AutoAppConfig.class,DiscountService.class);
        DiscountService discountService = ac.getBean(DiscountService.class);
        Member member = new Member(1L,"memberA", Grade.VIP);

        int discountPrice = discountService.discount(member, 20000, "rateDiscountPolicy");

        System.out.println("discountPrice = " + discountPrice);
        Assertions.assertThat(discountPrice).isEqualTo(2000);

    }


    static class DiscountService{
        private final Map<String, DisCountPolicy> policyMap;
        private final List<DisCountPolicy> policyList;

        @Autowired
        public DiscountService(Map<String, DisCountPolicy> policyMap, List<DisCountPolicy> policyList) {
            this.policyMap = policyMap;
            this.policyList = policyList;
        }

        public int discount(Member member, int price, String discountCode){
            DisCountPolicy disCountPolicy = policyMap.get(discountCode);
            return disCountPolicy.disCount(member, price);
        }


    }

}
