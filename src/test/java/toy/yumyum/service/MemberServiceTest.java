package toy.yumyum.service;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import toy.yumyum.domain.Member;
import toy.yumyum.domain.MemberType;
import toy.yumyum.repository.MemberRepository;

@SpringBootTest
@Transactional
class MemberServiceTest {
	@Autowired
	MemberService memberService;
	@Autowired
	MemberRepository memberRepository;

	@Test
	@Rollback(false)
	public void 회원등록() throws Exception {
		//given
		Member member = Member.builder()
			.email("rmrdus1@naver.com")
			.nickname("서민규")
			.type(MemberType.GENERAL)
			.build();

		Member admin = Member.builder()
			.email("admin@admin.com")
			.nickname("관리자")
			.type(MemberType.ADMIN)
			.build();

		// when
		Long memberId = memberService.join(member);
		Long adminId = memberService.join(admin);

		// then
		assertEquals(member, memberRepository.findOne(memberId));
		assertEquals(admin, memberRepository.findOne(adminId));
	}

	@Test
	public void 회원중복체크() throws Exception {
		//given
		Member member1 = Member.builder()
			.email("rmrdus1@naver.com")
			.nickname("서민규")
			.type(MemberType.GENERAL)
			.build();

		Member member2 = Member.builder()
			.email("rmrdus1@naver.com")
			.nickname("서민규")
			.type(MemberType.GENERAL)
			.build();

		// when

		// then
		assertThatIllegalStateException().isThrownBy(() -> {
			memberService.join(member1);
			memberService.join(member2);
		});
	}

	@Test
	public void 회원정보빈값체크() throws Exception {
		//given
		Member member1 = Member.builder()
			.nickname("서민규")
			.type(MemberType.GENERAL)
			.build();

		Member member2 = Member.builder()
			.email("rmrdus1@naver.com")
			.type(MemberType.GENERAL)
			.build();

		// when

		// then
		assertThatIllegalStateException().isThrownBy(() -> {
			memberService.join(member1);
			memberService.join(member2);
		});
	}

}