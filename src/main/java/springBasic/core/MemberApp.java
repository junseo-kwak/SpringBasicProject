package springBasic.core;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import springBasic.core.member.Grade;
import springBasic.core.member.Member;
import springBasic.core.member.MemberService;

public class MemberApp {

    public static void main(String[] args) {

        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);


        Member member = new Member(1L, "곽준서", Grade.VIP);

        memberService.join(member);
        Member findMember = memberService.findById(member.getId());

        System.out.println(" new member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
        System.out.println("is equal? " + (member == findMember));

    }
}
