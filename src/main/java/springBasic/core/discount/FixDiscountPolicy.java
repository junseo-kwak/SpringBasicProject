package springBasic.core.discount;

import springBasic.core.member.Grade;
import springBasic.core.member.Member;

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
