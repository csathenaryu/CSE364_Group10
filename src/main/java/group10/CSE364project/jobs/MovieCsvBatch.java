package group10.CSE364project.jobs;

import group10.CSE364project.model.Movie;
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
public class MovieCsvBatch {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;
    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private MongoTemplate mongoTemplate;

    @Bean
    public Job readMoviesCsvFile() {
        return jobBuilderFactory.get("readMoviesCsvFile").incrementer(new RunIdIncrementer()).start(stepMovie())
                .build();
    }

    @Bean
    public Step stepMovie() {
        return stepBuilderFactory.get("stepMovie").<Movie, Movie>chunk(10).reader(readerMovie())
                .writer(writerMovie()).build();
    }

    @Bean
    public FlatFileItemReader<Movie> readerMovie() {
        FlatFileItemReader<Movie> reader = new FlatFileItemReader<>();
        //FlatFileItemReader<Movie> reader1 = new FlatFileItemReader<>();

        reader.setResource(new ClassPathResource("movies.csv"));
        reader.setLineMapper(new DefaultLineMapper<Movie>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"movieId", "title", "genres"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Movie>() {{
                setTargetType(Movie.class);
            }});
        }});

        /*
        reader.setResource(new ClassPathResource("movie_poster.csv"));
        reader.setLineMapper(new DefaultLineMapper<Movie>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames(new String[]{"posterId", "posterURL"});
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<Movie>() {{
                setTargetType(Movie.class);
            }});
        }});

         */

        return reader;
    }

    @Bean
    public MongoItemWriter<Movie> writerMovie() {
        MongoItemWriter<Movie> writer = new MongoItemWriter<Movie>();
        writer.setTemplate(mongoTemplate);
        writer.setCollection("movie");
        return writer;
    }
}
