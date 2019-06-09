package com.store.service.impl;

import java.util.Collection;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.store.entity.Invoice;
import com.store.model.InvoiceDTO;
import com.store.respository.InvoiceRepository;
import com.store.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class InvoiceServiceImpl extends AbstracCrudService<InvoiceDTO, Invoice, String> implements InvoiceService {

    private InvoiceRepository invoiceRepository;

    @Autowired
    public InvoiceServiceImpl(InvoiceRepository repository) {
        super(repository);
        this.invoiceRepository = (InvoiceRepository) super.repository;
    }

    @Override
    public Collection<InvoiceDTO> findByCustomerId(long id) {
        return ((Collection<Invoice>) invoiceRepository.findByCustomerId(id)).stream().map(mapper::mapDto)
                .collect(Collectors.toSet());
    }

    @Override
    public InvoiceDTO findByIdAndCustomerId(String invoiceId, long customerId) {
        return mapper.mapDto(invoiceRepository.findByIdAndCustomerId(invoiceId, customerId));
    }
}