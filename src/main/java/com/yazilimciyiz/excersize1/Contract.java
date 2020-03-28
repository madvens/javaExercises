package com.yazilimciyiz.excersize1;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Contract {

    private String contractId;
    private String contractName;
    private List<ContractOffer> offers;
}
