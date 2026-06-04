package com.rhythm.controller;



import com.rhythm.dto.HabitRequestDto;
import com.rhythm.entity.Habit;
import com.rhythm.service.HabitService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

    @RestController
    @RequestMapping("/api/habits")
    public class HabitController {

        @Autowired
        private HabitService habitService;

        @PostMapping("/{userId}")
        public Habit createHabit(
                @PathVariable Long userId,
                @Valid
                @RequestBody HabitRequestDto request
        ) {

            return habitService.createHabit(
                    request,
                    userId
            );
        }

        @GetMapping("/{userId}")
        public List<Habit> getAllHabits(
                @PathVariable Long userId
        ) {

            return habitService.getAllHabits(
                    userId
            );
        }

        @PutMapping("/{habitId}")
        public Habit updateHabit(
                @PathVariable Long habitId,
                @Valid
                @RequestBody HabitRequestDto request
        ) {

            return habitService.updateHabit(
                    habitId,
                    request
            );
        }

        @DeleteMapping("/{habitId}")
        public String deleteHabit(
                @PathVariable Long habitId
        ) {

            return habitService.deleteHabit(
                    habitId
            );
        }
    }


