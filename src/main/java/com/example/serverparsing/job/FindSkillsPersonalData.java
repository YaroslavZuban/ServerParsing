package com.example.serverparsing.job;

import com.example.serverparsing.entity.Information;
import com.example.serverparsing.entity.PersonalData;

import java.util.ArrayList;
import java.util.List;


public class FindSkillsPersonalData {
    public List<PersonalData> findPersonalData(List<PersonalData> personalDataList, List<List<String>> skills) {
        List<PersonalData> result = new ArrayList<>();

        for (List<String> skill : skills) {
            for (PersonalData personalData : personalDataList) {
                if (personalData.getInformation() == null) {
                    continue;
                }

                Information information = personalData.getInformation();

                if (containsTaskIgnoreCase(information.getCourses(), skill) ||
                        containsTaskIgnoreCase(information.getSkills(), skill) ||
                        containsTaskIgnoreCase(information.getAboutMe(), skill)) {

                    if (result.stream().noneMatch(data -> data.getId() == personalData.getId())) {
                        result.add(personalData);
                    }
                }
            }
        }

        return result;
    }

    private boolean containsTaskIgnoreCase(String text, List<String> skills) {
        if (text != null && !skills.isEmpty()) {
            for (String task : skills) {
                if (!text.toLowerCase().contains(task.toLowerCase())) {
                    return false;
                }
            }

            return true;
        }

        return false;
    }
}
