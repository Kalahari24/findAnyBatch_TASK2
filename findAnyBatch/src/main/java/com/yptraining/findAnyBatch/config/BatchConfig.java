package com.yptraining.findAnyBatch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.BeanWrapperFieldExtractor;
import org.springframework.batch.item.file.transform.DelimitedLineAggregator;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import com.yptraining.findAnyBatch.model.Inventory;
import com.yptraining.findAnyBatch.model.ShoppingCart;
import com.yptraining.findAnyBatch.processor.InventoryItemProcessor;
import com.yptraining.findAnyBatch.processor.ShoppingCartProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfig {
	
	
	@Autowired
	private JobBuilderFactory jobBuilderFactory;
	
	@Autowired
	private StepBuilderFactory stepBuilderFactory;
	
	@Autowired
	private DataSource dataSource;
	
	@Bean
	public JdbcCursorItemReader<Inventory> reader() {
			JdbcCursorItemReader<Inventory> cursorItemReader = new JdbcCursorItemReader<>();
			cursorItemReader.setDataSource(dataSource);
			cursorItemReader.setSql("SELECT id,itemtype,item,quantity,status,price,description FROM inventory");
			cursorItemReader.setRowMapper(new InventoryRowMapper());
			return cursorItemReader;
		
	}
	
	@Bean
	public InventoryItemProcessor processor() {
		return new InventoryItemProcessor();
	}
	
	@Bean
	public FlatFileItemWriter<Inventory> writer() {
		FlatFileItemWriter<Inventory> writer = new FlatFileItemWriter<Inventory>();
		writer.setResource(new ClassPathResource("inventory.csv"));
		
		DelimitedLineAggregator<Inventory> lineAggregator = new DelimitedLineAggregator<Inventory>();
		lineAggregator.setDelimiter(",");
		
		BeanWrapperFieldExtractor<Inventory> fieldExtractor = new BeanWrapperFieldExtractor<Inventory>();
		fieldExtractor.setNames(new String[] {"Id","ItemType","Item","Quantity","Status","ItemPrice","Description"});
		lineAggregator.setFieldExtractor(fieldExtractor);
		
		writer.setLineAggregator(lineAggregator);
		return writer;
	}
	
	@Bean
	public Step inventoryDbtoCsv() {
		return stepBuilderFactory.get("inventoryDbtoCsv").<Inventory, Inventory>chunk(100)
				.reader(reader())
				.processor(processor())
				.writer(writer())
				.build();
		
	}
	
	@Bean
	public Job CompleteClothesJob() {
		return jobBuilderFactory.get("CompleteClothesJob").incrementer(new RunIdIncrementer()).flow(inventoryDbtoCsv()).next(step2()).end().build();
	}
	
	//********************Read from Inventory to ShoppingCart*********************//
	
	
	@Bean
	public FlatFileItemReader<Inventory> reader1() {
			FlatFileItemReader<Inventory> reader = new FlatFileItemReader<Inventory>();
			reader.setResource(new ClassPathResource("inventory.csv"));
			reader.setLineMapper(new DefaultLineMapper<Inventory>() {{
				setLineTokenizer(new DelimitedLineTokenizer() {{
					setNames (new String[] {"id","itemType","item","quantity","status","itemPrice","description"});
				}});
				setFieldSetMapper(new BeanWrapperFieldSetMapper<Inventory>() {{
					setTargetType(Inventory.class);
				}});
			}});
			return reader;
		
	}
	
	@Bean
	public ShoppingCartProcessor processor1() {
		return new ShoppingCartProcessor();
	}
	
//	@Bean
//	public JdbcBatchItemWriter<ShoppingCart> writer1() {
//		JdbcBatchItemWriter<ShoppingCart> writer = new JdbcBatchItemWriter<ShoppingCart>();
//		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<ShoppingCart>());
//		writer.setSql("INSERT INTO shopping_cart(itemtype,item,itemprice,description) VALUES (:itemType,:item,:itemPrice,:description)");
//		writer.setDataSource(dataSource);
//		return writer;
//	}
	
	@Bean
	public ItemWriter<ShoppingCart> writer1() {
		return new DBShoppingwriter();
	}
	
	@Bean
	public Step step2() {
		return stepBuilderFactory.get("step2")
				.<Inventory, ShoppingCart> chunk(100)
				.reader(reader1())
				.processor(processor1())
				.writer(writer1())
				.build();
				
	}

}
