package com.fil.rouge.mapper;


import com.fil.rouge.dto.ReviewDto;
import com.fil.rouge.dto.ReviewUpdateDto;
import com.fil.rouge.dto.ReviewWithClientDto;
import com.fil.rouge.model.Review;
import org.mapstruct.*;

import java.util.List;

@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    @Mapping(source = "worker.id", target = "workerId")
    @Mapping(source = "client.id", target = "clientId")
    ReviewDto toDto(Review entity);

    @Mapping(source = "workerId", target = "worker.id")
    @Mapping(source = "clientId", target = "client.id")
    Review toEntity(ReviewDto dto);

    List<ReviewDto> toDtoList(List<Review> entityList);

    List<Review> toEntityList(List<ReviewDto> dtoList);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(ReviewUpdateDto updateDto, @MappingTarget Review entity);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    Review toEntity(ReviewUpdateDto updateDto);

    @Mapping(source = "client.name", target = "clientName")
    @Mapping(source = "worker.name", target = "workerName")
    ReviewWithClientDto toReviewWithClientDto(Review review);

    List<ReviewWithClientDto> toReviewWithClientDtoList(List<Review> reviews);
}
