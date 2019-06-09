package com.store.respository;

import com.store.entity.Invoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InvoiceRepository extends JpaRepository<Invoice, String> {

    Iterable<Invoice> findByCustomerId(long id);

    Invoice findByIdAndCustomerId(String id, long customerId);
}