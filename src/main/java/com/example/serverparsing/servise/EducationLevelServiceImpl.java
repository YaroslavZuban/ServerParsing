package com.example.serverparsing.servise;

import com.example.serverparsing.repository.EducationLevelRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EducationLevelServiceImpl implements EducationLevelService {
    private final EducationLevelRepository educationLevelRepository;

    @Override
    public List<String> getEducationLevelAll() {
        return educationLevelRepository.getEducationTypeAll();
    }
}
