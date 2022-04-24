package springBasic.core.discount;

import org.assertj.core.api.Assertions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import springBasic.core.member.Grade;
import springBasic.core.member.Member;
class RateDiscountPolicyTest {

    private final RateDiscountPolicy rateDiscountPolicy = new RateDiscountPolicy();

    @Test
    @DisplayName("VIP는 10% 할인이 적용되어야 한다.")
    void vip_discount_O() {
        Member member = new Member(1L,"test", Grade.VIP);

        int discount = rateDiscountPolicy.disCount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(1000);
    }

    @Test
    @DisplayName("VIP가 아닌 경우 할인이 적용되지 않아야한다.")
    void not_vip_discount_X() {
        Member member = new Member(1L,"test", Grade.BASIC);

        int discount = rateDiscountPolicy.disCount(member, 10000);

        Assertions.assertThat(discount).isEqualTo(0);
    }
}