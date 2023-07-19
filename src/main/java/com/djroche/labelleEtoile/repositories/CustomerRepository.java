package com.djroche.labelleEtoile.repositories;

import com.djroche.labelleEtoile.entities.Customer;
import com.djroche.labelleEtoile.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long>{
    Optional<Customer> findByLastName(String lastName);
}
