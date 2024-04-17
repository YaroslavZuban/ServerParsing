package com.example.serverparsing.servise;

import com.example.serverparsing.repository.HabitationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HabitationServiceImpl implements HabitationService {
    private final HabitationRepository habitationRepository;

    @Override
    public List<String> getCityAll() {
        return this.habitationRepository.getCityAll();
    }
}
