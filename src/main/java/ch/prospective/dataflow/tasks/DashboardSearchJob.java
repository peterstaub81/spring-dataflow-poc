package ch.prospective.proanalytics.jobs.dashboardsearch;

import javax.sql.DataSource;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import com.google.common.io.Resources;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.task.configuration.EnableTask;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import ch.prospective.dataflow.dao.PublicationsDao;
import ch.prospective.dataflow.model.DashboardSearchRecord;

@Configuration
@EnableTask
@EnableBatchProcessing
public class DashboardSearchJob {

    private static final String DASHBOARD_SEARCH_JOB = "dashboardSearch";

    private static final String STEP_INIT = "initJob";
    private static final String STEP_CLEANUP = "cleanUp";
    private static final String STEP_FETCH_PUBLICATIONS_FROM_JOBBOOSTER = "fetchPublicationsFromJobbooster";
    private static final String STEP_CREATE_MATERIALIZED_VIEW = "createMaterializedView";
    private static final String STEP_RESET_INDEX = "resetElasticIndex";
    private static final String STEP_INDEXING = "buildElasticIndex";

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

//    @Autowired
//    private PublicationsDao publicationsDao;

    @Autowired
    private DataSource dataSource;

    @Bean
    public Job dashboardSearch() {
        return jobs.get(DASHBOARD_SEARCH_JOB)
                .incrementer(new RunIdIncrementer())
                .flow(
                        steps.get(STEP_INIT).tasklet(initDashboardSearch()).allowStartIfComplete(true).build()
                )
                .next(
                        steps.get(STEP_FETCH_PUBLICATIONS_FROM_JOBBOOSTER).allowStartIfComplete(true)
                                .<DashboardSearchRecord, DashboardSearchRecord>chunk(25000)
                                .reader(jbPublicationsReader())
                                .writer(jbPublicationsWriter())
                                .build()
                )
                .next(
                        steps.get(STEP_CREATE_MATERIALIZED_VIEW).tasklet(createMaterializedView()).allowStartIfComplete(true).build()
                )
                .next(cleanUp())
                .end()
                .build();
    }


    @Bean
    @StepScope
    public Tasklet initDashboardSearch() {
        return (contribution, chunkContext) -> {
            Resource dashboardScript = new ClassPathResource("/ch/prospective/proanalytics/db/init/psql/create-tables-dashboard-search.sql");
            String content = Resources.toString(dashboardScript.getURL(), Charset.defaultCharset());
            //dslPA.execute(content);
            return RepeatStatus.FINISHED;
        };
    }


    @Bean
    public Tasklet createMaterializedView() {
        return (contribution, chunkContext) -> {
//            dslPA.execute("drop materialized view if exists materializedviews.dashboard_auto_suggest");
//            dslPA.execute("create materialized view materializedviews.dashboard_auto_suggest as select * from temp_dashboard_search");
            return RepeatStatus.FINISHED;
        };
    }

    private Step cleanUp() {
        Tasklet tasklet = (contribution, chunkContext) -> {
//            dslPA.execute("delete from temp_dashboard_search");
            return RepeatStatus.FINISHED;
        };
        return steps.get(STEP_CLEANUP).tasklet(tasklet).build();
    }

    @Bean
    public ItemReader<DashboardSearchRecord> jbPublicationsReader() {
        List<DashboardSearchRecord> list = new ArrayList<>();
        return new IteratorItemReader<>(list);
    }

    @Bean
    public ItemWriter<DashboardSearchRecord> jbPublicationsWriter() {
        String sql = "insert into temp_dashboard_search (" +
                "stelle_id, auftrag_id, publication_id, inserat_id, status, sprache, start_date, end_date, medien_typ, medium_id, medium_name, interner_stellentitel, externer_stellentitel, kontakt, stellen_inhaber, recruiter_name, recruiter_id, hk_id, h1_id, h2_id, h3_id, h4_id, h5_id, h6_id, h7_id, h_name" +
                ") values(" +
                ":stelleId, :auftragId, :publicationId, :inseratId, :status, :sprache, :startDate, :endDate, :medienTyp, :mediumId, :mediumName, :internerStellentitel, :externerStellentitel, :kontakt, :stellenInhaber, :recruiterName, :recruiterId, :hkId, :h1Id, :h2Id, :h3Id, :h4Id, :h5Id, :h6Id, :h7Id, :hName" +
                ")";

        JdbcBatchItemWriter<DashboardSearchRecord> itemWriter = new JdbcBatchItemWriter<>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql(sql);
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>());
        return itemWriter;
    }

}

//    @Bean
//    public Job job1(ItemReader<Usage> reader,
//                    ItemProcessor<Usage,Bill> itemProcessor, ItemWriter<Bill> writer) {
//        Step step = stepBuilderFactory.get("BillProcessing")
//                .<Usage, Bill>chunk(1)
//                .reader(reader)
//                .processor(itemProcessor)
//                .writer(writer)
//                .build();
//
//        return jobBuilderFactory.get("BillJob")
//                .incrementer(new RunIdIncrementer())
//                .start(step)
//                .build();
//    }

//    @Bean
//    public JsonItemReader<Usage> jsonItemReader() {
//
//        ObjectMapper objectMapper = new ObjectMapper();
//        JacksonJsonObjectReader<Usage> jsonObjectReader =
//                new JacksonJsonObjectReader<>(Usage.class);
//        jsonObjectReader.setMapper(objectMapper);
//
//        return new JsonItemReaderBuilder<Usage>()
//                .jsonObjectReader(jsonObjectReader)
//                .resource(usageResource)
//                .name("UsageJsonItemReader")
//                .build();
//    }
//
//    @Bean
//    public ItemWriter<Bill> jdbcBillWriter(DataSource dataSource) {
//        JdbcBatchItemWriter<Bill> writer = new JdbcBatchItemWriterBuilder<Bill>()
//                .beanMapped()
//                .dataSource(dataSource)
//                .sql("INSERT INTO BILL_STATEMENTS (id, first_name, " +
//                        "last_name, minutes, data_usage,bill_amount) VALUES " +
//                        "(:id, :firstName, :lastName, :minutes, :dataUsage, " +
//                        ":billAmount)")
//                .build();
//        return writer;
//    }



