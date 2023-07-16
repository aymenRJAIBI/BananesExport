package com.exportBanane.dto;

import com.exportBanane.models.Recipient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
@AllArgsConstructor
@Builder
public class RecipientDto {



    private  Integer id;

    @NotNull(message = "The name must not be empty")
    @NotEmpty(message = "The name must not be empty")
    @NotBlank(message = "The name must not be empty")
    private String name;

    @NotNull(message = "The address must not be empty")
    @NotEmpty(message = "The address must not be empty")
    @NotBlank(message = "The address must not be empty")
    private String address;

    @NotNull(message = "The city must not be empty")
    @NotEmpty(message = "The city must not be empty")
    @NotBlank(message = "The city must not be empty")
    private String city ;

    @NotNull(message = "The country must not be empty")
    @NotEmpty(message = "The country must not be empty")
    @NotBlank(message = "The country must not be empty")
    private String country;

    public static RecipientDto fromEntity(Recipient recipient){

        return RecipientDto.builder()
                .id(recipient.getId())
                .name(recipient.getName())
                .address(recipient.getAddress())
                .city(recipient.getCity())
                .country(recipient.getCountry()).build();
    }
    public static Recipient toEntity(RecipientDto recipientDto){

        return Recipient.builder()
                .id(recipientDto.getId())
                .name(recipientDto.getName())
                .address(recipientDto.getAddress())
                .city(recipientDto.getCity())
                .country(recipientDto.getCountry()).build();

    }
}
