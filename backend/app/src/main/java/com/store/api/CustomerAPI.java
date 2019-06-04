package com.store.api;

import java.util.Collection;
import java.util.Set;

import com.store.model.CustomerDTO;
import com.store.model.DetailedInvoiceDTO;
import com.store.model.InvoiceDTO;
import com.store.service.CustomerService;
import com.store.service.DetailedInvoiceService;
import com.store.service.InvoiceService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
@RequestMapping("/api/customers")
public class CustomerAPI {

    @Autowired
    private CustomerService customerService;

    @Autowired
    private InvoiceService invoiceService;

    @Autowired
    private DetailedInvoiceService detailedInvoiceService;

    @GetMapping
    public Collection<CustomerDTO> getCustomers() {
        return customerService.findAll();
    }

    @GetMapping("/{email}")
    public CustomerDTO getCustomer(@PathVariable String email) {
        return customerService.findByEmail(email);
    }

    @GetMapping("/{id}/invoices")
    public Collection<InvoiceDTO> getInvoices(@PathVariable long id) {
        Set<InvoiceDTO> invoices = (Set<InvoiceDTO>) invoiceService.findByCustomerId(id);
        invoices.forEach((invoice) -> {
            Set<DetailedInvoiceDTO> detailedInvoices = (Set<DetailedInvoiceDTO>) detailedInvoiceService
                    .findByInvoiceId(invoice.getId());
            invoice.setDetailedInvoices(detailedInvoices);
        });
        return invoices;
    }

    @PostMapping
    public CustomerDTO createCustomer(@RequestBody CustomerDTO customer) {
        return customerService.save(customer);
    }

    @PutMapping("/{id}")
    public CustomerDTO updateCustomer(@PathVariable long id, @RequestBody CustomerDTO customer) {
        customer.setId(id);
        return customerService.save(customer);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable long id) {
        customerService.deleteById(id);
    }
}