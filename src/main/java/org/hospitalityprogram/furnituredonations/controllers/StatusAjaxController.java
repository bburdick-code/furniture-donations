package org.hospitalityprogram.furnituredonations.controllers;

import org.hospitalityprogram.furnituredonations.data.DonationRepository;
import org.hospitalityprogram.furnituredonations.data.ItemRepository;
import org.hospitalityprogram.furnituredonations.models.Donation;
import org.hospitalityprogram.furnituredonations.models.Item;
import org.hospitalityprogram.furnituredonations.models.dto.DonationStatusChangedDTO;
import org.hospitalityprogram.furnituredonations.models.dto.ItemStatusChangeDTO;
import org.hospitalityprogram.furnituredonations.models.enums.DonationStatus;
import org.hospitalityprogram.furnituredonations.models.enums.ItemStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
public class StatusAjaxController {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private DonationRepository donationRepository;

    @PostMapping("volunteer/requests/postStatus")
    public String postStatus(@RequestBody ItemStatusChangeDTO newItemStatus) {

        int itemId = Integer.parseInt(newItemStatus.getItemId());
        Optional<Item> result = itemRepository.findById(itemId);
        Item existingItem = result.get();

        String textResponse;
        if (newItemStatus.getItemStatus().equals("REQUESTED")) {
            existingItem.setItemStatus(ItemStatus.DELIVERED);
            textResponse = "DELIVERED";
        } else {
            existingItem.setItemStatus(ItemStatus.REQUESTED);
            textResponse = "REQUESTED";
        }

        itemRepository.save(existingItem);

        return textResponse;
    }

    @PostMapping("volunteer/requests/postDonationStatus")
    public String postDonationStatus(@RequestBody DonationStatusChangedDTO newDonationStatus) {

        int itemId = Integer.parseInt(newDonationStatus.getDonationId());
        Optional<Donation> result = donationRepository.findById(itemId);
        Donation existingDonation = result.get();

        String textResponse;
        if (newDonationStatus.getDonationStatus().equals("POSTED")) {
            existingDonation.setDonationStatus(DonationStatus.DELIVERED);
            textResponse = "DELIVERED";
        } else {
            existingDonation.setDonationStatus(DonationStatus.POSTED);
            textResponse = "POSTED";
        }

        donationRepository.save(existingDonation);

        return textResponse;
    }

}
