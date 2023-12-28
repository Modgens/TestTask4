package com.example.testtask3.services;

import com.example.testtask3.models.Vacancy;
import com.example.testtask3.repositories.VacancyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VacancyService {
    private final VacancyRepository vacancyRepository;

    public void save(Vacancy vacancy) {
        vacancyRepository.save(vacancy);
    }

    // Метод для експорту даних в CSV
    public void exportDataToCSV() {
        List<Vacancy> data = vacancyRepository.findAll();

        try (FileWriter writer = new FileWriter("output.csv")) {
            // Запис даних в CSV
            for (Vacancy entity : data) {
                writer.write(
                        "\"" + entity.getJobPageUrl() + "\"," +
                                "\"" + entity.getPositionName() + "\"," +
                                "\"" + entity.getOrganizationUrl() + "\"," +
                                "\"" + entity.getLogoUrl() + "\"," +
                                "\"" + entity.getOrganizationTitle() + "\"," +
                                "\"" + entity.getLaborFunction() + "\"," +
                                "\"" + entity.getAddress() + "\"," +
                                "\"" + entity.getPostedDate() + "\"," +
                                "\"" + entity.getDescription() + "\"," +
                                "\"" + entity.getTagsNames() + "\"\n"
                );
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
