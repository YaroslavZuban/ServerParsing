package com.example.serverparsing.servise;

import com.example.serverparsing.repository.LevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageLevelServiceImpl implements LanguageLevelService {
    private final LevelRepository levelRepository;

    @Override
    public List<String> getLanguageLevelAll() {
        return this.levelRepository.getLanguageLevelAll();
    }
}
