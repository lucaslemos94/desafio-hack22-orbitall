package com.orbitallpayments.hack22.repositories;

import com.orbitallpayments.hack22.domains.Card;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository  extends CrudRepository<Card,Long> {
}
