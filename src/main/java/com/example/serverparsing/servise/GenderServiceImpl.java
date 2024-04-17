package com.example.serverparsing.servise;

import com.example.serverparsing.repository.GenderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GenderServiceImpl implements GenderService {
    private final GenderRepository genderRepository;

    @Override
    public List<String> getGenderAll() {
        return this.genderRepository.getGenderAll();
    }
}
