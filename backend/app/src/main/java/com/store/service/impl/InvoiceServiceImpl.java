package com.store.service.impl;

import com.store.entity.Invoice;
import com.store.model.InvoiceDTO;
import com.store.respository.InvoiceRepository;
import com.store.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceServiceImpl extends AbstracCrudService<InvoiceDTO, Invoice, String> implements InvoiceService {

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository repository) {
        super(repository);
    }
    
}