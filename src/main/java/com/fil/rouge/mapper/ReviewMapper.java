package com.fil.rouge.mapper;


import com.fil.rouge.dto.ReviewDto;
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
    Review partialUpdate(ReviewDto dto, @MappingTarget Review entity);
}
