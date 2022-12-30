package com.nidecker.testtask.util;

import com.nidecker.testtask.entity.News;
import com.nidecker.testtask.repository.NewsRepository;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.jetbrains.annotations.NotNull;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

@Service
@Slf4j
@RequiredArgsConstructor
public class StatisticalUploading {
    private final NewsRepository newsRepository;

    public static final Path DIRECTORY_FOR_STATISTICS_UPLOADING = Paths.get(System.getProperty("user.dir") + "\\statistics");

    static {
        new File(DIRECTORY_FOR_STATISTICS_UPLOADING.toUri()).mkdir();
    }


    @Scheduled(cron = "${interval-in-cron}")
    public void uploading() {
        Map<String, Map<String, Long>> map = parseNews(newsRepository.findAll());

        for (Map.Entry<String, Map<String, Long>> entry : map.entrySet()) {
            String[] statistics = entry.getValue().entrySet().toString()
                    .replaceAll("\\[|\\]", " ")
                    .replaceAll(",", "\n")
                    .replaceAll("=", ",").split("\n");

            File file = createCSVFile(DIRECTORY_FOR_STATISTICS_UPLOADING, entry.getKey());
            List<String[]> listOfLines = Arrays.stream(statistics).map(s -> s.trim().split(",")).toList();
            writeIntoCsvFile(file, listOfLines);
        }
    }

    private Map<String, Map<String, Long>> parseNews(@NotNull List<News> news) {
        return news.stream()
                .collect(
                        Collectors.groupingBy(n -> n.getSource().getName(),
                                Collectors.groupingBy(n -> n.getTopic().getName(), Collectors.counting())
                        )
                );
    }

    public File createCSVFile(@NotNull Path directory, String fileName) {
        return new File(URI.create(directory.toUri() + fileName + ".csv"));
    }

    public void writeIntoCsvFile(File file, List<String[]> listOfLines) {

        try (FileWriter fileWriter = new FileWriter(file, true);
             CSVWriter writer = new CSVWriter(fileWriter)) {
            writer.writeNext(new String[]{
                    LocalDateTime.now()
                            .truncatedTo(TimeUnit.MINUTES.toChronoUnit())
                            .toString().replaceAll("T", " ")}
            );
            writer.writeAll(listOfLines);
            writer.writeNext(new String[]{"\n"});
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
