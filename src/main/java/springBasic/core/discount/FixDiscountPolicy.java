package springBasic.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import springBasic.core.member.Grade;
import springBasic.core.member.Member;

@Component
public class FixDiscountPolicy implements DisCountPolicy {
    private final int discountFixAmount = 1000;

    @Override
    public int disCount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return discountFixAmount;
        }else{
            return 0;
        }
    }
}
