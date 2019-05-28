package com.store.service.impl;

import com.store.entity.Customer;
import com.store.entity.Invoice;
import com.store.model.CustomerDTO;
import com.store.model.InvoiceDTO;
import com.store.service.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class InvoiceMapper implements Mapper<InvoiceDTO, Invoice> {

    @Autowired
    private Mapper<CustomerDTO, Customer> customerMapper;

    @Override
    public InvoiceDTO mapDto(Invoice entity) {
        InvoiceDTO invoice = new InvoiceDTO();
        invoice.setId(entity.getId());
        invoice.setPaymentDate(entity.getPaymentDate());
        invoice.setCustomer(customerMapper.mapDto(entity.getCustomer()));
        return invoice;
    }

    @Override
    public Invoice mapEntity(InvoiceDTO dto) {
        Invoice invoice = new Invoice();
        invoice.setId(dto.getId());
        invoice.setPaymentDate(dto.getPaymentDate());
        invoice.setCustomer(customerMapper.mapEntity(dto.getCustomer()));
        return invoice;
    }

    
}