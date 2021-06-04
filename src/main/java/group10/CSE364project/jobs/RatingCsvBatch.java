package group10.CSE364project.jobs;

import group10.CSE364project.model.Rating;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.data.MongoItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.mongodb.core.MongoTemplate;

@EnableBatchProcessing
@Configuration
public class RatingCsvBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readRatingsCsvFile() {
        return jobBuilderFactory.get("readRatingsCsvFile").incrementer(new RunIdIncrementer()).start(stepRating())
                .build();
    }

    @Bean
    public Step stepRating() {
        return stepBuilderFactory.get("stepRating").<Rating, Rating>chunk(10).reader(readerRating())
                .writer(writerRating()).build();
    }

    @Bean
    public FlatFileItemReader<Rating> readerRating() {
        FlatFileItemReader<Rating> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("ratings.csv"));
        reader.setLineMapper(new DefaultLineMapper<Rating>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"userId", "movieId", "rating", "timestamp"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Rating>() {{
                setTargetType(Rating.class);
            }});
        }});
        return reader;
    }

    @Bean
    public MongoItemWriter<Rating> writerRating() {
        MongoItemWriter<Rating> writer = new MongoItemWriter<Rating>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("rating");
        return writer;
    }
}
