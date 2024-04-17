package com.example.serverparsing.servise;

import com.example.serverparsing.repository.LicenceCategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LicenceCategoryServiceImpl implements LicenceCategoryService {
    private final LicenceCategoryRepository repository;

    @Override
    public List<String> getLicenceCategoryAll() {
        return repository.getLicenceCategoriesAll();
    }
}
