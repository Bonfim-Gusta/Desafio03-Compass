package com.desafio03.msusers.web.dto.mapper;

import com.desafio03.msusers.entities.User;
import com.desafio03.msusers.web.dto.AddressResponse;
import com.desafio03.msusers.web.dto.AddressResponseTranslated;
import com.desafio03.msusers.web.dto.UserAddressResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

public class UserMapper {
    public static UserAddressResponseDto toDto(User user, AddressResponse addressResponse){
        AddressResponseTranslated addressResponseTranslated = toDtoTranslated(addressResponse);
        PropertyMap<User, UserAddressResponseDto> props = new PropertyMap<User, UserAddressResponseDto>() {
            @Override
            protected void configure() {
                map().setAddress(addressResponseTranslated);
            }
        };

        ModelMapper mapper = new ModelMapper();
        mapper.addMappings(props);
        return mapper.map(user, UserAddressResponseDto.class);
    }

    private static AddressResponseTranslated toDtoTranslated(AddressResponse dto){
        return new AddressResponseTranslated(
            dto.getCep(),
                dto.getLogradouro(),
                dto.getComplemento(),
                dto.getBairro(),
                dto.getLocalidade(),
                dto.getUf()
        );
    }
}
