package com.fil.rouge.mapper;

import com.fil.rouge.dto.ReviewDto;
import com.fil.rouge.model.Review;
import org.mapstruct.*;

import java.util.List;


@Mapper(componentModel = "spring", unmappedTargetPolicy = ReportingPolicy.IGNORE)
public interface ReviewMapper {

    @Mapping(source = "worker.id", target = "workerId")
    @Mapping(source = "client.id", target = "clientId")
    @Mapping(source = "client.name", target = "clientName") // Extract client name
    @Mapping(source = "worker.name", target = "workerName") // Extract worker name
    ReviewDto toDto(Review review);

    @Mapping(source = "workerId", target = "worker.id")
    @Mapping(source = "clientId", target = "client.id")
    Review toEntity(ReviewDto dto);

    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(ReviewDto updateDto, @MappingTarget Review entity);

    List<ReviewDto> toDtoList(List<Review> reviews);
}

