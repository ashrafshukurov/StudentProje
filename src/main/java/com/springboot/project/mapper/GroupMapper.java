package com.springboot.project.mapper;

import com.springboot.project.dto.request.StudentRequest;
import com.springboot.project.model.StudentGroup;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
@Component
public interface GroupMapper {

    StudentGroup requestToGroup(StudentRequest studentRequest);
}
