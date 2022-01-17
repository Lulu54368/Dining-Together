package com.summerHack.diningTogether.service;
import com.summerHack.diningTogether.Converter.FoodConverter;
import com.summerHack.diningTogether.model.Food;
import com.summerHack.diningTogether.model.FoodBrief;
import com.summerHack.diningTogether.repository.FoodRepository;
import lombok.AllArgsConstructor;
import org.modelmapper.Condition;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;

import com.summerHack.diningTogether.exceptions.UnimplementedException;

import com.summerHack.diningTogether.model.FoodType;
import com.summerHack.diningTogether.model.User;

import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.Instant;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
@Service
@AllArgsConstructor
public class FoodService {
    @Autowired
    private FoodRepository foodRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private FoodConverter foodConverter;
    private final SessionService sessionService;


    public FoodBrief getFoodById(long id) throws Exception {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if (foodOptional.isEmpty()) {
            throw new Exception("Can't find food");
        }
        Food food = foodOptional.get();
        return foodConverter.FoodToFoodBriefConverter(food);
    }


    public String saveFood(Food food) {
        foodRepository.save(food);
        return "hello world";
    }

    public Food updateFood(int id, Food food) throws Exception {
        Food foodToUpdate = FoodById(id);
        Condition notNull = ctx -> ctx.getSource() != null;

        return food;


    }

    public Food deleteById(long id) throws Exception {
        Food food = FoodById(id);
        foodRepository.deleteById(id);
        return food;

    }

    public List<FoodBrief> findByCategory(String category) {
        List<Food> foodList = foodRepository.findFoodByCategory(category);

        return foodList.stream().map(food -> foodConverter.FoodToFoodBriefConverter(food)).collect(Collectors.toList());
    }

    public Food confirmFood(Integer id) throws Exception {
        Food food = FoodById(id);
        food.setCompleted(Boolean.TRUE);
        return food;
    }

    public List<FoodBrief> findAll() {
        List<FoodBrief> foodBriefList = new ArrayList<>();
        Iterable<Food> foodIterable = foodRepository.findAll();
        for (Food food : foodIterable) {
            foodBriefList.add(foodConverter.FoodToFoodBriefConverter(food));
        }
        return foodBriefList;
    }

    public Food FoodById(long id) throws Exception {
        Optional<Food> foodOptional = foodRepository.findById(id);
        if (foodOptional.isEmpty()) {
            throw new Exception("Can't find food");
        }
        Food food = foodOptional.get();
        return food;
    }

    public Food addFood(Food food) {
        final User user = sessionService.getOrThrowUnauthorized();

        food.setProvider(user);
        // TODO: time zone?
        food.setCreatedTime(Timestamp.from(Instant.now()));
        food.setCompleted(false);
        // TODO:
        food.setEndTime(Timestamp.from(Instant.now()));
        food.setConsumerNumber(10);
        food.setLocation("Somewhere");
        food.setFoodType(FoodType.DINING_IN);

        return foodRepository.save(food);
    }
}


