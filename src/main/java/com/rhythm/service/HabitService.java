package com.rhythm.service;



import com.rhythm.dto.HabitRequestDto;
import com.rhythm.entity.Habit;
import com.rhythm.entity.User;
import com.rhythm.repository.HabitRepository;
import com.rhythm.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

    @Service
    public class HabitService {

        @Autowired
        private HabitRepository habitRepository;

        @Autowired
        private UserRepository userRepository;

        public Habit createHabit(
                HabitRequestDto request,
                Long userId
        ) {

            User user =
                    userRepository.findById(userId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "User not found"
                                    )
                            );

            Habit habit = new Habit();

            habit.setHabitName(
                    request.getHabitName()
            );

            habit.setDescription(
                    request.getDescription()
            );

            habit.setFrequency(
                    request.getFrequency()
            );

            habit.setStartDate(
                    request.getStartDate()
            );

            habit.setStatus(
                    request.getStatus()
            );

            habit.setUser(user);

            return habitRepository.save(habit);
        }

        public List<Habit> getAllHabits(
                Long userId
        ) {

            User user =
                    userRepository.findById(userId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "User not found"
                                    )
                            );

            return habitRepository.findByUser(user);
        }

        public Habit updateHabit(
                Long habitId,
                HabitRequestDto request
        ) {

            Habit habit =
                    habitRepository.findById(habitId)
                            .orElseThrow(() ->
                                    new RuntimeException(
                                            "Habit not found"
                                    )
                            );

            habit.setHabitName(
                    request.getHabitName()
            );

            habit.setDescription(
                    request.getDescription()
            );

            habit.setFrequency(
                    request.getFrequency()
            );

            habit.setStartDate(
                    request.getStartDate()
            );

            habit.setStatus(
                    request.getStatus()
            );

            return habitRepository.save(habit);
        }

        public String deleteHabit(
                Long habitId
        ) {

            habitRepository.deleteById(
                    habitId
            );

            return "Habit Deleted Successfully";
        }
    }

