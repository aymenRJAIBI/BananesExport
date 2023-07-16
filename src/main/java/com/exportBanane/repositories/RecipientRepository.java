package com.exportBanane.repositories;

import com.exportBanane.models.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecipientRepository extends JpaRepository<Recipient, Integer> {
    boolean existsByNameAndAddressAndCityAndCountry(String name, String address, String city, String country);

}
