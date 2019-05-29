package com.store.api;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

import com.store.model.DetailedInvoiceDTO;
import com.store.model.InvoiceDTO;
import com.store.service.DetailedInvoiceService;
import com.store.service.InvoiceService;
import com.store.util.RandomString;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/invoices")
public class InvoiceAPI {

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    public String generateInvoiceID(Date date) {
        String id = null;
        RandomString random = new RandomString(5);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("hhmmssddMMyyyy");

        do {
            StringBuilder builder = new StringBuilder();
            builder.append(random.next());
            builder.append(simpleDateFormat.format(date));
            id = builder.toString();
            System.out.println(id);
        } while (invoiceService.existsByID(id));

        return id;
    }

    @PostMapping
    public InvoiceDTO postMethodName(@RequestBody InvoiceDTO invoice) {
        invoice.setId(generateInvoiceID(invoice.getPaymentDate()));
        InvoiceDTO invoiceSaved = invoiceService.save(invoice);

        Set<DetailedInvoiceDTO> detailedInvoices = invoice.getDetailedInvoices().stream().map((detailedInvoice) -> {
            detailedInvoice.setInvoice(invoiceSaved);
            return detailedInvoice;
        }).collect(Collectors.toSet());

        detailedInvoices = (Set<DetailedInvoiceDTO>) detailedInvoiceService.saveAll(detailedInvoices);
        invoiceSaved.setDetailedInvoices(detailedInvoices);
        return invoiceSaved;
    }

}