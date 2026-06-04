package com.rhythm.dto;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.time.LocalDate;

    @Data
    public class HabitRequestDto {

        @NotBlank(message = "Habit name is required")
        private String habitName;

        private String description;

        private String frequency;

        private LocalDate startDate;

        private String status;
    }

