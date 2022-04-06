package toy.yumyum.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member {

	@Id
	@GeneratedValue
	@Column(name = "member_id")
	private Long id;

	private String email;

	private String nickname;

	@Enumerated(EnumType.STRING)
	private MemberType type;

	@OneToMany(mappedBy = "member")
	private List<Pet> pets = new ArrayList<>();

	@Builder
	public Member(String email, String nickname, MemberType type) {
		this.email = email;
		this.nickname = nickname;
		this.type = type;
	}
}
