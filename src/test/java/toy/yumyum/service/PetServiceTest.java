package toy.yumyum.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import toy.yumyum.domain.FeedFood;
import toy.yumyum.domain.Food;
import toy.yumyum.domain.Kind;
import toy.yumyum.domain.Member;
import toy.yumyum.domain.MemberType;
import toy.yumyum.domain.Pet;
import toy.yumyum.domain.PetType;
import toy.yumyum.repository.FoodRepository;
import toy.yumyum.repository.KindRepository;
import toy.yumyum.repository.MemberRepository;
import toy.yumyum.repository.PetRepository;

@SpringBootTest
@Transactional
@Rollback(false)
class PetServiceTest {

	@Autowired
	EntityManager em;

	@Autowired
	PetRepository petRepository;
	@Autowired
	PetService petService;

	@Test
	public void 반려동물등록() throws Exception {
		//given
		Long memberId = createMember();
		List<Long> foodIds = createFood();
		Long kindId = createKind();

		// when
		Long petId = petService.savePet("구름이", PetType.CAT, kindId, memberId, foodIds);

		// then
		petService.findOne(petId);
	}

	private Long createKind() {
		Kind kind = Kind.builder()
			.name("브리티쉬 숏헤어")
			.type(PetType.CAT)
			.build();

		// when
		em.persist(kind);
		return kind.getId();
	}

	private List<Long> createFood() {
		Food food = Food.builder()
			.name("아카나 와일드 프레이리 캣")
			.ingredients("[{\"name\":\"닭고기\",\"concern\":\"1\"}, {\"name\":\"돼지고기\",\"concern\":\"1\"}]")
			.type(PetType.CAT)
			.build();

		em.persist(food);

		List<Long> foodIds = new ArrayList<>();
		foodIds.add(food.getId());

		return foodIds;
	}

	private Long createMember() {
		Member member = Member.builder()
			.email("rmrdus1@naver.com")
			.nickname("서민규")
			.type(MemberType.GENERAL)
			.build();

		em.persist(member);
		return member.getId();
	}

}