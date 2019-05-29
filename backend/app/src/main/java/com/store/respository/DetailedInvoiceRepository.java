package com.store.respository;

import com.store.entity.DetailedInvoice;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DetailedInvoiceRepository extends JpaRepository<DetailedInvoice, Long> {

    Iterable<DetailedInvoice> findByInvoiceId(String id);
}