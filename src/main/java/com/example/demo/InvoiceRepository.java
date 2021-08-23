package com.example.demo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;


public interface InvoiceRepository extends CrudRepository<Invoice,Long> {
}
