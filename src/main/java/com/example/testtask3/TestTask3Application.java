package com.example.testtask3;

import com.example.testtask3.services.VacancyService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import java.util.List;
import static com.example.testtask3.models.enums.JobFunctions.*;
import static com.example.testtask3.services.Base64Encode.getUrlFromList;
import static com.example.testtask3.services.Scraper.scrap;

@SpringBootApplication
public class TestTask3Application {

    public static void main(String[] args) {
        SpringApplication.run(TestTask3Application.class, args);
    }
    @Bean
    public CommandLineRunner demo(VacancyService vacancyService) {
        return (args) -> {
            // Створюємо список значень з енаму JobFunctions
            List<String> jobFunctionsList = List.of(
                    ACCOUNTING_FINANCE.getValue(),
                    IT.getValue(),
                    OPERATIONS.getValue()

            );

            // Передаємо потрібний url та робимо скрап
            scrap(getUrlFromList(jobFunctionsList), vacancyService);

            //Тут відбувається dump database
            vacancyService.exportDataToCSV();
        };
    }
}
