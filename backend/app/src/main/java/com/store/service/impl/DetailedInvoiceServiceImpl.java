package com.store.service.impl;

import com.store.entity.DetailedInvoice;
import com.store.model.DetailedInvoiceDTO;
import com.store.respository.DetailedInvoiceRepository;
import com.store.service.DetailedInvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedInvoiceServiceImpl extends AbstracCrudService<DetailedInvoiceDTO, DetailedInvoice, Long>
        implements DetailedInvoiceService {

    @Autowired
    public DetailedInvoiceServiceImpl(DetailedInvoiceRepository repository) {
        super(repository);
    }
            
}