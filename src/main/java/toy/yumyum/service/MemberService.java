package toy.yumyum.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import toy.yumyum.domain.Member;
import toy.yumyum.repository.MemberRepository;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {

	private final MemberRepository memberRepository;

	@Transactional
	public Long join(Member member) {
		validatedEmptyValueMember(member);
		validatedDuplicatedMember(member);

		memberRepository.save(member);
		return member.getId();
	}

	private void validatedDuplicatedMember(Member member) {
		List<Member> findMembers = memberRepository.findByEmail(member.getEmail());

		if (!findMembers.isEmpty()) {
			throw new IllegalStateException("이미 사용중인 이메일입니다.");
		}
	}

	private void validatedEmptyValueMember(Member member) {
		if (member.getEmail() == "" || member.getEmail() == null) {
			throw new IllegalStateException("이메일을 입력해주세요.");
		}

		if (member.getNickname() == "" || member.getNickname() == null) {
			throw new IllegalStateException("닉네임을 입력해주세요.");
		}
	}

}
