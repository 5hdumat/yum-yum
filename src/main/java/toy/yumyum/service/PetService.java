package toy.yumyum.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import toy.yumyum.domain.FeedFood;
import toy.yumyum.domain.Food;
import toy.yumyum.domain.Kind;
import toy.yumyum.domain.Member;
import toy.yumyum.domain.Pet;
import toy.yumyum.domain.PetType;
import toy.yumyum.repository.FoodRepository;
import toy.yumyum.repository.KindRepository;
import toy.yumyum.repository.MemberRepository;
import toy.yumyum.repository.PetRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class PetService {

	private final MemberRepository memberRepository;
	private final KindRepository kindRepository;
	private final FoodRepository foodRepository;
	private final PetRepository petRepository;

	@Transactional
	public Long savePet(String name, PetType type, Long kindId, Long memberId, List<Long> foodIds) {
		Member member = memberRepository.findOne(memberId);
		Kind kind = kindRepository.findOne(kindId);
		List<Food> foods = foodRepository.findIds(foodIds);

		// 일단 1개만
		FeedFood[] feedFoods = new FeedFood[1];

		for (Food food : foods) {
			feedFoods[0] = FeedFood.builder()
				.food(food)
				.build();
		}

		Pet pet = Pet.builder()
			.name(name)
			.member(member)
			.type(type)
			.kind(kind)
			.feedFoods(feedFoods)
			.build();

		petRepository.save(pet);
		return pet.getId();
	}

	public List<Pet> findPets(Long memberId) {
		return petRepository.findByMemberId(memberId);
	}

	public Pet findOne(Long id) {
		return petRepository.findOne(id);
	}

	@Transactional
	public void deletePet(Long id) {
		petRepository.delete(id);
	}

	private void validatedDuplicatedPet(Pet pet) {
		if (pet.getName() == "" || pet.getName() == null) {
			throw new IllegalStateException("반려동물의 이름을 입력해주세요.");
		}
	}

}
