package org.team10.washcode.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.team10.washcode.domain.review.dto.ReviewResDTO;
import org.team10.washcode.domain.review.entity.Review;
import org.team10.washcode.domain.review.repository.ReviewRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;

    public List<ReviewResDTO> getReviewList(int laundryShopId) {
        List<Review> reviewEntities = reviewRepository.findAllByLaundryShopId(laundryShopId);
        List<ReviewResDTO> dtos = new ArrayList<>();

        for (Review review : reviewEntities) {
            ReviewResDTO dto = new ReviewResDTO();
            dto.setPickupId(review.getPickup().getId());
            dto.setUserName(review.getPickup().getUser().getName());
            dto.setContent(review.getContent());
            dtos.add(dto);
        }
        return dtos;
    }
}
