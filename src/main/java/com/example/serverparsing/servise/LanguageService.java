package com.example.serverparsing.servise;

import com.example.serverparsing.entity.Language;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

public interface LanguageService {
    List<String> getLanguageList();
}
