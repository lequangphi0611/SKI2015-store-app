package com.store.service;

import java.util.Collection;

import com.store.model.DetailedInvoiceDTO;

/**
 * DetailedInvoiceService
 */
public interface DetailedInvoiceService extends CrudService<DetailedInvoiceDTO, Long> {

    Collection<DetailedInvoiceDTO> findByInvoiceId(String id);
    
}