package com.example.serverparsing.servise;

import com.example.serverparsing.repository.WorkTypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WorkTypeServiceImpl implements WorkTypeService {
    private final WorkTypeRepository workTypeRepository;

    @Override
    public List<String> getWortTypeAll() {
        return workTypeRepository.getWorkScheduleAll();
    }
}
