package springBasic.core.discount;

import org.springframework.stereotype.Component;
import springBasic.core.member.Grade;
import springBasic.core.member.Member;

@Component
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
