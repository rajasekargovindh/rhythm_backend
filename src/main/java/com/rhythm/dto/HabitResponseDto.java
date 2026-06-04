package com.rhythm.dto;



import lombok.Data;

import java.time.LocalDate;

    @Data
    public class HabitResponseDto {

        private Long id;
        private String habitName;
        private String description;
        private String frequency;
        private LocalDate startDate;
        private String status;
    }

