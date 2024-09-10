package com.techlabs.bank.config;



import org.springframework.batch.item.ItemProcessor;

import com.techlabs.bank.dto.TransactionDto;

public class TransactionProcessor implements ItemProcessor<TransactionDto, TransactionDto> {

    @Override
    public TransactionDto process(TransactionDto item) throws Exception {
        // Perform any processing or transformation here
        // For example, you could add business logic or validation
        // Here, we just return the item as is
        return item;
    }
}

