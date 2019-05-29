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
        detailedInvoice.setPrice(entity.getPrice());
        detailedInvoice.setQuantity(entity.getQuantity());
        return detailedInvoice;
    }

    @Override
    public DetailedInvoice mapEntity(DetailedInvoiceDTO dto) {
        DetailedInvoice detailedInvoice = new DetailedInvoice();
        Invoice invoice = new Invoice();
        Product product = new Product();
        invoice.setId(dto.getInvoice().getId());
        product.setId(dto.getProduct().getId());
        detailedInvoice.setId(dto.getId());
        detailedInvoice.setInvoice(invoice);
        detailedInvoice.setProduct(product);
        detailedInvoice.setPrice(dto.getPrice());
        detailedInvoice.setQuantity(dto.getQuantity());
        return detailedInvoice;
    }

    
}