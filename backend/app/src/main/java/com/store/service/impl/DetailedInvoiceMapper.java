package com.store.service.impl;

import com.store.entity.DetailedInvoice;
import com.store.entity.Invoice;
import com.store.entity.Product;
import com.store.model.DetailedInvoiceDTO;
import com.store.model.InvoiceDTO;
import com.store.model.ProductDTO;
import com.store.service.Mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DetailedInvoiceMapper implements Mapper<DetailedInvoiceDTO, DetailedInvoice> {

    @Autowired
    private Mapper<InvoiceDTO, Invoice> invoiceMapper;

    @Autowired
    private Mapper<ProductDTO, Product> productMapper;

    @Override
    public DetailedInvoiceDTO mapDto(DetailedInvoice entity) {
        DetailedInvoiceDTO detailedInvoice = new DetailedInvoiceDTO();
        detailedInvoice.setId(entity.getId());
        detailedInvoice.setInvoice(invoiceMapper.mapDto(entity.getInvoice()));
        detailedInvoice.setProduct(productMapper.mapDto(entity.getProduct()));
        return detailedInvoice;
    }

    @Override
    public DetailedInvoice mapEntity(DetailedInvoiceDTO dto) {
        DetailedInvoice detailedInvoice = new DetailedInvoice();
        detailedInvoice.setId(dto.getId());
        detailedInvoice.setInvoice(invoiceMapper.mapEntity(dto.getInvoice()));
        detailedInvoice.setProduct(productMapper.mapEntity(dto.getProduct()));
        return detailedInvoice;
    }

    
}