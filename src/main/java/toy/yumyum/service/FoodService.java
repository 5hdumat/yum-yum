package toy.yumyum.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import toy.yumyum.domain.Food;
import toy.yumyum.repository.FoodRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class FoodService {

	private final FoodRepository foodRepository;

	@Transactional
	public Long saveFood(Food food) {
		validatedEmptyValueFood(food);
		validatedDuplicatedFood(food);

		foodRepository.save(food);
		return food.getId();
	}

	private void validatedEmptyValueFood(Food food) {
		if (food.getName() == "" || food.getName() == null) {
			throw new IllegalStateException("사료명을 입력해주세요.");
		}
	}

	private void validatedDuplicatedFood(Food food) {
		List<Food> findFoods = foodRepository.findByName(food.getName());

		if (!findFoods.isEmpty()) {
			throw new IllegalStateException("이미 등록된 사료입니다.");
		}
	}

	@Transactional
	public void deleteFood(Long id) {
		foodRepository.delete(id);
	}

}
