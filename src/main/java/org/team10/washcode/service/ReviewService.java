package org.team10.washcode.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.team10.washcode.ResponseDTO.review.ReviewResDTO;
import org.team10.washcode.entity.Review;
import org.team10.washcode.repository.ReviewRepository;

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
            dto.setPickup_id(review.getPickup().getId());
            dto.setUser_name(review.getPickup().getUser().getName());
            dto.setContent(review.getContent());
            dtos.add(dto);
        }
        return dtos;
    }
}
