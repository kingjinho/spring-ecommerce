package com.kingjinho.ecommerce.service;

import com.kingjinho.ecommerce.domain.Member;
import com.kingjinho.ecommerce.repository.MemberRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

//스프링과 테스트 통합
@RunWith(SpringRunner.class)

//스프링 부트를 띄우고 테스트 진행(없으면 @Autowired 실패)
@SpringBootTest

// 반복 가능한 테스트 지원.
// 각 테스트를 실행 할 때 마다 트랜잭션을 실행하고, 테스트가 끝나면 트랜잭션 강제 롤백(테스트일 경우에만 롤백)
@Transactional
public class MemberServiceTest {

    @Autowired
    MemberRepository memberRepository;
    @Autowired
    MemberService memberService;

    @Test
    @Rollback(value = false)
    public void 회원가입() throws Exception {
        //given
        Member member = new Member();
        member.setName("kim");

        //when
        Long saveId = memberService.join(member);

        //then
        assertEquals(member, memberRepository.findOne(saveId));
    }

    @Test(expected = IllegalStateException.class)
    public void 중복_회원_예외() throws Exception {
        //given
        Member member1 = new Member();
        member1.setName("kim");

        Member member2 = new Member();
        member2.setName("kim");

        //when
        memberService.join(member1);
        memberService.join(member2); //예외가 발생해야 한다

        //then
        fail("예외가 발생해야 한다.");
    }

}