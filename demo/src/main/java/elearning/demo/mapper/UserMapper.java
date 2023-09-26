package elearning.demo.mapper;

import java.util.List;

import org.mapstruct.InheritConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;

import elearning.demo.dto.user.UserCreatedRequest;
import elearning.demo.models.User;

@Mapper
public interface UserMapper {

    User dtoToEntity(UserCreatedRequest dto);

    @InheritConfiguration(name = "dtoToEntity")
    UserCreatedRequest entityToDto(User dto);

    @InheritConfiguration()
    void updateDto(User user, @MappingTarget UserCreatedRequest req);

    @InheritConfiguration()
    void updateEntity(UserCreatedRequest req, @MappingTarget User user);

    List<User> dtoToEntities(List<UserCreatedRequest> userRequests);

    List<UserCreatedRequest> entitiesToDtos(List<User> users);
}
