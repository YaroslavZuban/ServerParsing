package com.example.serverparsing.servise;

import com.example.serverparsing.entity.Language;
import com.example.serverparsing.repository.LanguageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LanguageServiceImpl implements LanguageService {
    private final LanguageRepository languageRepository;

    @Override
    public List<String> getLanguageList() {
        return languageRepository.getAllName();
    }
}
