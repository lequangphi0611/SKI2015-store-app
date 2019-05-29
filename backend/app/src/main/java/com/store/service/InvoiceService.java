package com.store.service;

import java.util.Collection;

import com.store.model.InvoiceDTO;

public interface InvoiceService extends CrudService<InvoiceDTO, String> {

    Collection<InvoiceDTO> findByCustomerId(long id);
}