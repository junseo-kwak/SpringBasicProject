package springBasic.core.discount;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import springBasic.core.annotation.MainDiscountPolicy;
import springBasic.core.member.Grade;
import springBasic.core.member.Member;

@Component
@MainDiscountPolicy
public class RateDiscountPolicy implements DisCountPolicy{

    @Override
    public int disCount(Member member, int price) {
        if(member.getGrade() == Grade.VIP){
            return price * 10 / 100;
        }else{
            return 0;
        }
    }
}
