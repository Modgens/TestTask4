package com.example.testtask3.services;

import com.example.testtask3.models.Vacancy;
import com.example.testtask3.repositories.VacancyRepository;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scraper {
    final static String prefix = "https://jobs.techstars.com";

    public static void scrap(String url, VacancyService vacancyService) {
        try {
            Document document = Jsoup.connect(url).get();
            List<Vacancy> vacancies = new ArrayList<>();
            for (Element row : document.getElementsByClass("sc-beqWaB sc-gueYoa diHipZ MYFxR")) {
                Vacancy vacancy = new Vacancy();

                // 1. Витягую лінку
                String href = row.select("a[data-testid=read-more]").first().attr("href");
                String jobPageUrl = href.startsWith("/") ? prefix + href : href;
                vacancy.setJobPageUrl(jobPageUrl);

                // 2. Витягую теги
                String tagsNames = "";
                for (Element tag: row.getElementsByClass("sc-dmqHEX dncTlc")) {
                    tagsNames = String.join(", ", tagsNames, tag.text());
                }
                tagsNames = tagsNames.substring(2);
                vacancy.setTagsNames(tagsNames);

                // 3. Витягую назву вакансії
                String positionName = row.getElementsByClass("sc-beqWaB kToBwF").text();
                vacancy.setPositionName(positionName);

                // 4. Витягую лінку та назву організації
                String organizationTitle = row.select("a[data-testid=link].sc-beqWaB").first().text();
                vacancy.setOrganizationTitle(organizationTitle);

                // Витягую все зі сторінки
                scrapFromPage(jobPageUrl, vacancy);

                // Зберігаємо до бд
                vacancyService.save(vacancy);
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    private static void scrapFromPage(String url, Vacancy vacancy) throws IOException {
        if (!url.contains("jobs.techstars.com")){
            return;
        }
        Document document = Jsoup.connect(url).get();

        // 5. Витягую лінку на лого
        String logoUrl = document.getElementsByClass("sc-dmqHEX eTCoCQ").attr("src");
        vacancy.setLogoUrl(logoUrl);

        // 6. Витягую лінку на Apply
        String organizationUrl = document.select("a[type=button].sc-dmqHEX").attr("href");
        vacancy.setOrganizationUrl(organizationUrl);

        // 7. Labor function
        String laborFunction = document.getElementsByClass("sc-beqWaB bpXRKw").get(2).text();
        vacancy.setLaborFunction(laborFunction);

        // 8. Address
        String address = document.getElementsByClass("sc-beqWaB bpXRKw").get(3).text();
        vacancy.setAddress(address);

        // 9. Posted
        String postedDate = document.getElementsByClass("sc-beqWaB gRXpLa").text();
        int commaIndex = postedDate.indexOf(',');
        postedDate = postedDate.substring(commaIndex + 2);
        vacancy.setPostedDate(postedDate);

        // 10. Description
        String description = document.select("div[data-testid=careerPage]").html();
        vacancy.setDescription(description);
    }

    private static void dumbDB() {

    }
}

