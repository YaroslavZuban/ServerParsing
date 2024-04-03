package com.example.serverparsing.job;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class SkillCombinations {
    public List<List<String>> getAllCombinations(List<String> skills) {
        List<List<String>> combinations = new ArrayList<>();
        generateCombinations(skills, 0, new ArrayList<>(), combinations);
        return combinations;
    }

    private void generateCombinations(List<String> skills, int index,
                                      List<String> currentCombination, List<List<String>> combinations) {
        if (index == skills.size()) {
            combinations.add(new ArrayList<>(currentCombination));
            return;
        }

        currentCombination.add(skills.get(index));
        generateCombinations(skills, index + 1, currentCombination, combinations);
        currentCombination.remove(currentCombination.size() - 1);

        generateCombinations(skills, index + 1, currentCombination, combinations);
    }
}
