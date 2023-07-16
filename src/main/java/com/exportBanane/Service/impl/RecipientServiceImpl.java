package com.exportBanane.Service.impl;

import com.exportBanane.Service.RecipientService;
import com.exportBanane.dto.RecipientDto;
import com.exportBanane.exceptions.ReciptientNoPermittedException;
import com.exportBanane.models.Recipient;
import com.exportBanane.repositories.RecipientRepository;
import com.exportBanane.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class RecipientServiceImpl implements RecipientService {

    @Autowired
    private final RecipientRepository repository;
    private final ObjectsValidator<RecipientDto> validator;


    @Override
    public Integer save(RecipientDto dto) {
        validator.validate(dto);
        Recipient recipient = RecipientDto.toEntity(dto);
        if (isExistRecipient(recipient)) {
            throw new ReciptientNoPermittedException("Object exist in DB", "Save operation", "RecipientServciceImpl");
        }

        return repository.save(recipient).getId();
    }

    @Override
    public List<RecipientDto> findAll() {
        return repository.findAll().stream().map(RecipientDto::fromEntity).collect(Collectors.toList());
    }

    @Override
    public RecipientDto findById(Integer id) {
        return repository.findById(id).map(RecipientDto::fromEntity)
                .orElseThrow(() -> new EntityNotFoundException("Recipient not found with the id : " + id));
    }

    @Override
    public void delete(Integer id) {
        repository.deleteById(id);

    }

    @Override
    public Integer update(RecipientDto dto) {
        validator.validate(dto);
        Recipient recipient = RecipientDto.toEntity(dto);
        Recipient existingRecipient = repository.findById(recipient.getId()).get();
        if (existingRecipient == null) {
            throw new EntityNotFoundException("destinataire not found with the Id:" + recipient.getId());
        }
        existingRecipient.setAddress(dto.getAddress());
        existingRecipient.setName(dto.getName());
        existingRecipient.setCountry(dto.getCountry());
        existingRecipient.setCity(dto.getCity());

        return repository.save(existingRecipient).getId();


    }

    boolean isExistRecipient(Recipient recipient) {
        String name = recipient.getName();
        String address = recipient.getAddress();
        String city = recipient.getCity();
        String country = recipient.getCountry();
        return repository.existsByNameAndAddressAndCityAndCountry(name, address, city, country);
    }

}
