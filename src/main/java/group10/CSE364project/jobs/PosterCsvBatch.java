package group10.CSE364project.jobs;

import group10.CSE364project.model.Poster;
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
public class PosterCsvBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readPosterCsvBatch() {
        return jobBuilderFactory.get("readPosterCsvBatch").incrementer(new RunIdIncrementer()).start(stepPoster())
                .build();
    }

    @Bean
    public Step stepPoster() {
        return stepBuilderFactory.get("stepPoster").<Poster, Poster>chunk(10).reader(readerPoster())
                .writer(writerPoster()).build();
    }

    @Bean
    public FlatFileItemReader<Poster> readerPoster() {
        FlatFileItemReader<Poster> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("movie_poster.csv"));
        reader.setLineMapper(new DefaultLineMapper<Poster>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"posterId", "posterURL"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Poster>() {{
                setTargetType(Poster.class);
            }});
        }});
        return reader;
    }

    @Bean
    public MongoItemWriter<Poster> writerPoster() {
        MongoItemWriter<Poster> writer = new MongoItemWriter<Poster>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("Poster");
        return writer;
    }
}
