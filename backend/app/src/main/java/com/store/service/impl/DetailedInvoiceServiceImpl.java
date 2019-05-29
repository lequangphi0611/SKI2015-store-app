package com.store.service.impl;

import java.util.Collection;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import com.store.entity.DetailedInvoice;
import com.store.model.DetailedInvoiceDTO;
import com.store.model.InvoiceDTO;
import com.store.respository.DetailedInvoiceRepository;
import com.store.service.DetailedInvoiceService;
import com.store.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class DetailedInvoiceServiceImpl extends AbstracCrudService<DetailedInvoiceDTO, DetailedInvoice, Long>
        implements DetailedInvoiceService {

    private DetailedInvoiceRepository detailedInvoiceRepository;

    @Autowired
    private ProductService productService;

    @Autowired
    public DetailedInvoiceServiceImpl(DetailedInvoiceRepository repository) {
        super(repository);
        detailedInvoiceRepository = (DetailedInvoiceRepository) super.repository;
    }

    @Override
    public Collection<DetailedInvoiceDTO> findByInvoiceId(String id) {
        return ((Collection<DetailedInvoice>) detailedInvoiceRepository.findByInvoiceId(id)).stream()
                .map(mapper::mapDto).collect(Collectors.toSet());
    }

    private Function<DetailedInvoice, DetailedInvoiceDTO> mapDetailedInvoiceToDTO() {
        return (detailedInvoice) -> {
            DetailedInvoiceDTO detailedInvoiceDTO = new DetailedInvoiceDTO();
            detailedInvoiceDTO.setId(detailedInvoice.getId());
            InvoiceDTO invoice = new InvoiceDTO();
            invoice.setId(detailedInvoice.getInvoice().getId());
            detailedInvoiceDTO.setInvoice(invoice);
            detailedInvoiceDTO.setProduct(productService.getOneById(detailedInvoice.getProduct().getId()));
            detailedInvoiceDTO.setPrice(detailedInvoice.getPrice());
            detailedInvoiceDTO.setQuantity(detailedInvoice.getQuantity());
            return detailedInvoiceDTO;
        };
    }

    @Override
    public Collection<DetailedInvoiceDTO> saveAll(Collection<DetailedInvoiceDTO> dtos) {
        List<DetailedInvoice> detailedInvoices = dtos.stream().map(mapper::mapEntity).collect(Collectors.toList());
        return detailedInvoiceRepository.saveAll(detailedInvoices).stream().map(mapDetailedInvoiceToDTO())
                .collect(Collectors.toSet());
    }

}