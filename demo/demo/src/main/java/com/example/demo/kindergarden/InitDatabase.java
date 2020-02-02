package com.example.demo.kindergarden;

import lombok.extern.slf4j.Slf4j;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.example.demo.kindergarden.repos.ChildrenModelRepository;
import com.example.demo.kindergarden.repos.KindergardenRepository;
import com.example.demo.kindergarden.model.*;

@Configuration
@Slf4j
class LoadDatabase {

        private static final java.util.logging.Logger LOG = java.util.logging.Logger
                        .getLogger(CommandLineRunner.class.getName());
/*                        @Bean
                        CommandLineRunner initChildren(ChildrenModelRepository repository) {
                                return args -> {
                                        repository.save(new ChildrenModel("Jānis", "Čakste", "111111-22221", false));
                                        repository.save(new ChildrenModel("Gustavs", "Zemgals", "111111-00000", false));
                                        repository.save(new ChildrenModel("Albers", "Kviesis", "111111-99999", false));
                                        repository.save(new ChildrenModel("Kārlis", "Ulmanis", "111111-88888", false));
                                        repository.save(new ChildrenModel("Guntis", "Ulmanis", "111111-77777", true));
                                        repository.save(new ChildrenModel("Vaira", "Vīķe-Freiberga", "111111-66666", false));
                                        repository.save(new ChildrenModel("Valdis", "Zatlers", "111111-55555", false));
                                        repository.save(new ChildrenModel("Raimonds", "Vējonis", "111111-44444", false));
                                        repository.save(new ChildrenModel("Andris", "Bērziņš", "111111-33333", false));
                                        repository.save(new ChildrenModel("Egils", "Levits", "111111-22222", false));
                                };
                        }
*/
        @Bean
        CommandLineRunner initDatabase(KindergardenRepository repository, ChildrenModelRepository childRepository) {
                return args -> {

                        String pattern = "dd-MM-yyyy";
                        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
                        List<ChildrenModel> childrenQueue = new ArrayList<ChildrenModel>();
                        ChildrenModel cm1 = new ChildrenModel("Jānis", "Čakste", "111111-22221", false, simpleDateFormat.parse("12-01-1922"));
                        ChildrenModel cm2 = new ChildrenModel("Gustavs", "Zemgals", "111111-00000", false, simpleDateFormat.parse("12-01-1927"));
                        ChildrenModel cm3 = new ChildrenModel("Albers", "Kviesis", "111111-99999", false, simpleDateFormat.parse("12-01-1930"));
                        ChildrenModel cm4 = new ChildrenModel("Kārlis", "Ulmanis", "111111-88888", false, simpleDateFormat.parse("12-01-1936"));
                        ChildrenModel cm5 = new ChildrenModel("Guntis", "Ulmanis", "111111-77777", true, simpleDateFormat.parse("12-01-1993"));
                        ChildrenModel cm6 = new ChildrenModel("Vaira", "Vīķe-Freiberga", "111111-66666", false, simpleDateFormat.parse("12-01-1993"));
                        ChildrenModel cm7 = new ChildrenModel("Valdis", "Zatlers", "111111-55555", false, simpleDateFormat.parse("12-01-2007"));
                        ChildrenModel cm8 = new ChildrenModel("Raimonds", "Vējonis", "111111-44444", false, simpleDateFormat.parse("12-01-2015"));
                        ChildrenModel cm9 = new ChildrenModel("Andris", "Bērziņš", "111111-33333", false, simpleDateFormat.parse("12-01-2011"));
                        ChildrenModel cm10 = new ChildrenModel("Egils", "Levits", "111111-22222", false, simpleDateFormat.parse("12-01-2019"));
                        /*
                        childRepository.save(cm1);
                        childRepository.save(cm2);
                        childRepository.save(cm3);
                        childRepository.save(cm4);
                        childRepository.save(cm5);
                        childRepository.save(cm6);
                        childRepository.save(cm7);
                        childRepository.save(cm8);
                        childRepository.save(cm9);
                        childRepository.save(cm10);
                        */
                        childrenQueue.add(cm1);
                        childrenQueue.add(cm2);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-1930"),
                                        cm3);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-1936"),
                                        cm4);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-1993"),
                                        cm5);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-1993"),
                                        cm6);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-2007"),
                                        cm7);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-2015"),
                                        cm8);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-2011"),
                                        cm9);
                        childrenQueue.add(//new QueueEntryModel(simpleDateFormat.parse("12-01-2019"),
                                        cm10);

                        LOG.info("Preloading " + repository.save(
                                        new KindergardenModel("PII Rūķīši", "Rīga, Tvaika iela 2", childrenQueue)));
                };
        }
}