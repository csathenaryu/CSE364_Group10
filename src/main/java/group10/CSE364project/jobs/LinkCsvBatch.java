package group10.CSE364project.jobs;

import group10.CSE364project.model.Link;
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
public class LinkCsvBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readLinksCsvFile() {
        return jobBuilderFactory.get("readLinksCsvFile").incrementer(new RunIdIncrementer()).start(stepLink())
                .build();
    }

    @Bean
    public Step stepLink() {
        return stepBuilderFactory.get("stepLink").<Link, Link>chunk(10).reader(readerLink())
                .writer(writerLink()).build();
    }

    @Bean
    public FlatFileItemReader<Link> readerLink() {
        FlatFileItemReader<Link> reader = new FlatFileItemReader<>();
        reader.setResource(new ClassPathResource("links.csv"));
        reader.setLineMapper(new DefaultLineMapper<Link>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"movieId", "imdbId"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Link>() {{
                setTargetType(Link.class);
            }});
        }});
        return reader;
    }

    @Bean
    public MongoItemWriter<Link> writerLink() {
        MongoItemWriter<Link> writer = new MongoItemWriter<Link>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("link");
        return writer;
    }
}
