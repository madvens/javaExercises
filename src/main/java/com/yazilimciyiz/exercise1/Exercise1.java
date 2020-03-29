package com.yazilimciyiz.exercise1;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Exercise1 {

    public static void main(String[] args) throws IOException {
        Offers offers = getOffers();
        Map<String, Contract> contractMap = fillContractMap(offers);
        printResult(contractMap);
    }

    private static Offers getOffers() throws IOException {
        String offersJson = new String(Exercise1.class.getClassLoader().getResourceAsStream("offers.json").readAllBytes());

//        URL url = Resources.getResource("offers.json");
//        String offers = Resources.toString(url, StandardCharsets.UTF_8);

        return new ObjectMapper().readValue(offersJson, Offers.class);
    }

    private static Map<String, Contract> fillContractMap(Offers offers) {
        List<Offer> offerList = offers.getOffers();

        Map<String, Contract> contractMap = new HashMap<>();

        for (Offer offer : offerList) {
            String contractId = offer.getContractId();

            if (!contractMap.containsKey(contractId)){
                List<ContractOffer> contractOfferList = new ArrayList<>();
                ContractOffer contractOffer = ContractOffer.builder()
                        .offerId(offer.getOfferId())
                        .offerName(offer.getOfferName())
                        .build();
                contractOfferList.add(contractOffer);

                Contract contract = Contract.builder()
                        .contractId(offer.getContractId())
                        .contractName(offer.getContractName())
                        .offers(contractOfferList)
                        .build();

                contractMap.put(contractId, contract);
            }
            else {
                Contract contract = contractMap.get(contractId);
                List<ContractOffer> contractOfferList = contract.getOffers();
                ContractOffer contractOffer = ContractOffer.builder()
                        .offerId(offer.getOfferId())
                        .offerName(offer.getOfferName())
                        .build();
                contractOfferList.add(contractOffer);
            }

        }
        return contractMap;
    }

    private static void printResult(Map<String, Contract> contractMap) throws JsonProcessingException {
        List<Contract> contractList = new ArrayList<>(contractMap.values());
        Contracts contracts = Contracts.builder().contracts(contractList).build();

        String result = new ObjectMapper().writeValueAsString(contracts);
        System.out.println(result);
    }

}
