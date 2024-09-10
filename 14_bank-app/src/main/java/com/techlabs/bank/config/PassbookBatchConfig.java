package com.techlabs.bank.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.techlabs.bank.dto.TransactionDto;

@Configuration
public class PassbookBatchConfig {

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    @StepScope
    public JdbcCursorItemReader<TransactionDto> transactionReader(DataSource dataSource,
                                                                  @Value("#{jobParameters['accountNumber']}") Long accountNumber) {
        return new JdbcCursorItemReaderBuilder<TransactionDto>()
                .dataSource(dataSource)
                .name("transactionReader")
                .sql("SELECT t.transaction_id, t.transaction_type, t.amount, a.account_number, t.transfer_account_number, t.date " +
                     "FROM transactions t " +
                     "JOIN accounts a ON t.account_id = a.account_id " +
                     "WHERE a.account_number = ?")
                .preparedStatementSetter((ps) -> ps.setLong(1, accountNumber))
                .rowMapper((rs, rowNum) -> new TransactionDto(
                        rs.getInt("transaction_id"),
                        rs.getString("transaction_type"),
                        rs.getDouble("amount"),
                        rs.getLong("account_number"),
                        rs.getLong("transfer_account_number"),
                        rs.getDate("date")
                ))
                .build();
    }

    @Bean
    @StepScope
    public FlatFileItemWriter<TransactionDto> transactionWriter(@Value("#{jobParameters['accountNumber']}") Long accountNumber) {
        String fileName = "passbook_" + accountNumber + ".csv";  // Dynamic file name based on account number
        return new FlatFileItemWriterBuilder<TransactionDto>()
                .name("transactionWriter")
                .resource(new FileSystemResource(fileName))
                 // Avoid appending to existing files
                .lineAggregator(new DelimitedLineAggregator<TransactionDto>() {{
                    setDelimiter(",");
                    setFieldExtractor(new BeanWrapperFieldExtractor<TransactionDto>() {{
                        setNames(new String[]{
                                "transactionId",
                                "transactionType",
                                "amount",
                                "accountNumber",
                                "transferAccountNumber",
                                "date"
                        });
                    }});
                }})
                .build();
    }

    @Bean
    public Step exportStep(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                           JdbcCursorItemReader<TransactionDto> transactionReader, FlatFileItemWriter<TransactionDto> transactionWriter) {
        return new StepBuilder("exportTransactions", jobRepository)
                .<TransactionDto, TransactionDto>chunk(10) 
                .reader(transactionReader)
                .writer(transactionWriter)
                .transactionManager(transactionManager)
                .build();
    }

    @Bean
    public Job exportJob(JobRepository jobRepository, Step exportStep) {
        return new JobBuilder("exportJob", jobRepository)
                .start(exportStep)
                .incrementer(new RunIdIncrementer())  
                .build();
    }
}
