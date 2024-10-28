package com.kingjinho.ecommerce.service;

import com.kingjinho.ecommerce.domain.Member;
import com.kingjinho.ecommerce.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
//jpa의 데이터 변경은 가급적이면 transaction안에서 실행되어야 한다.
//클래스 레벨에서의 Transactional 어노테이션은 public 함수들을 기본적으로 transaction으로 실행
//readOnly는 true는 조회하는 곳에서 성능 최적화(아래 두개 찾기 함수는 readOnly적용, 가입은 적용 X)
@Transactional(readOnly = true)

//lombok의 RequiredArgsConstructor는 객체 생성시 필요한 필드를 가지고 생성자를 만들어줌
@RequiredArgsConstructor

//lombok의 AllArgsConstructor는 필드를 가지고 알아서 생성자를 만들어줌
//@AllArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    //생성자가 하나인 경우는 알아서 autowired
//    public MemberService(MemberRepository memberRepository) {
//        this.memberRepository = memberRepository;
//    }

    @Transactional
    public Long join(Member member) {
        validateDuplicateMember(member); //중복회원검증
        memberRepository.save(member);
        return member.getId(); // persist할때 무조건 값이 있다고 보장되어있음
    }

    //비즈니스 로직
    private void validateDuplicateMember(Member member) {
        List<Member> findMembers = memberRepository.findByName(member.getName());
        if (!findMembers.isEmpty()) {
            throw new IllegalStateException("이미 존재하는 회원입니다.");
        }
    }

    public List<Member> findMembers() {
        return memberRepository.findAll();
    }

    public Member findOne(Long memberId) {
        return memberRepository.findOne(memberId);
    }
}
