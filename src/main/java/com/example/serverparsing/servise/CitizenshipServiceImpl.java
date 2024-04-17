package com.example.serverparsing.servise;

import com.example.serverparsing.repository.CitizenshipRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CitizenshipServiceImpl implements CitizenshipService {
    private final CitizenshipRepository citizenshipRepository;

    @Override
    public List<String> getCitizenshipAll() {
        return citizenshipRepository.getCitizenshipAll();
    }
}
