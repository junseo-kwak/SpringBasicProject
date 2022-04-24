package springBasic.core.discount;

import springBasic.core.member.Member;

public interface DisCountPolicy {
    /*
       @return 할인금액
     */
    int disCount(Member member, int price);

}
