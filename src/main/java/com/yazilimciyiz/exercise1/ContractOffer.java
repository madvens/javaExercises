package com.yazilimciyiz.exercise1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ContractOffer {

    private Long offerId;
    private String offerName;
}
