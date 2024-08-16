package com.picpay.backend_pagamento.mappers;

import com.picpay.backend_pagamento.dto.UserDTO;
import com.picpay.backend_pagamento.entities.TypeUser;
import com.picpay.backend_pagamento.entities.User;

import com.picpay.backend_pagamento.entities.Wallet;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.Named;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mappings({
        @Mapping(source = "type", target = "type", qualifiedByName = "stringToTypeUser"),
        @Mapping(source = "ballance", target = "wallet", qualifiedByName = "longToWallet"),
    })
    User toEntity(UserDTO dto);

    @Mappings({
        @Mapping(source = "type", target = "type", qualifiedByName = "typeUserToString"),
        @Mapping(source = "wallet", target = "ballance", qualifiedByName = "walletToLong"),
        @Mapping(source = "password", target = "password", ignore = true)
    })
    UserDTO toDto(User user);

    List<UserDTO> toDtoList(List<User> users);

    @Named("stringToTypeUser")
    default TypeUser stringToTypeUser(String type) {
        if (type == null) {
            return null;
        }
        TypeUser typeUser = new TypeUser();
        typeUser.setType(type);
        return typeUser;
    }

    @Named("typeUserToString")
    default String typeUserToString(TypeUser typeUser) {
        return typeUser != null ? typeUser.getType() : null;
    }

    @Named("longToWallet")
    default Wallet longToWallet(long ballance) {
        Wallet wallet = new Wallet();
        wallet.setBallance(ballance);

        return wallet;
    }

    @Named("walletToLong")
    default Long walletToLong(Wallet wallet) {
        return wallet != null ? wallet.getBallance() : null;
    }

}
