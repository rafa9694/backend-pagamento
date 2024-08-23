package com.picpay.backend_pagamento.services;

import com.picpay.backend_pagamento.dto.UserDTO;
import com.picpay.backend_pagamento.entities.User;
import com.picpay.backend_pagamento.entities.Wallet;
import com.picpay.backend_pagamento.exceptions.UserNotFoundException;
import com.picpay.backend_pagamento.exceptions.DuplicateFieldException;
import com.picpay.backend_pagamento.mappers.UserMapper;
import com.picpay.backend_pagamento.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private WalletService walletService;

    @Autowired
    private UserMapper userMapper;

    public UserDTO newUser(UserDTO newUserDTO) {
        this.validateUniqueFields(newUserDTO);

        Wallet wallet = new Wallet();
        Wallet newWallet = walletService.newWallet(wallet);

        User newUser = userMapper.toEntity(newUserDTO);
        newUser.setWallet(newWallet);

        userRepository.save(newUser);

        return userMapper.toDto(newUser);
    }

    public List<UserDTO> findAllUserDto() {
        List<User> users = userRepository.findAll();
        return userMapper.toDtoList(users);
    }

    public UserDTO findUserDto(Long id) {

        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            return userMapper.toDto(userOptional.get());
        } else {
            throw new UserNotFoundException("User not found with id: " + id);
        }
    }

    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

    public UserDTO updateUser (UserDTO updateUserDto){

        this.validateUniqueFieldsForUpdate(updateUserDto);

        Optional<User> userOptional = userRepository.findById(updateUserDto.getIdUser());

        if (userOptional.isPresent()) {
            Optional<User> updateUser = userOptional.map(user -> {
                user.setName(updateUserDto.getName());
                user.setEmail(updateUserDto.getEmail());
                user.setPassword(updateUserDto.getPassword());
                user.setCpf(updateUserDto.getCpf());
                user.setCnpj(updateUserDto.getCnpj());
                return userRepository.save(user);
            });
            return userMapper.toDto(updateUser.get());
        } else {
            throw new UserNotFoundException("User not found with id: " + updateUserDto.getIdUser());
        }
    }

    private void validateUniqueFields(UserDTO userDTO) {
        if (userRepository.existsByEmail(userDTO.getEmail())) {
            throw new DuplicateFieldException("email", userDTO.getEmail());
        }

        if (userDTO.getType().equals("CLIENTE") && userRepository.existsByCpf(userDTO.getCpf())) {
            throw new DuplicateFieldException("cpf", userDTO.getCpf());
        }

        if (userDTO.getType().equals("COMERCIANTE") && userRepository.existsByCnpj(userDTO.getCnpj())) {
            throw new DuplicateFieldException("cnpj", userDTO.getCnpj());
        }
    }

    private void validateUniqueFieldsForUpdate(UserDTO userDTO) {
        if (userRepository.existsByEmailAndIdUserNot(userDTO.getEmail(), userDTO.getIdUser())) {
            throw new DuplicateFieldException("email", userDTO.getEmail());
        }

        if (userDTO.getType().equals("CLIENTE") && userRepository.existsByCpfAndIdUserNot(userDTO.getCpf(), userDTO.getIdUser())) {
            throw new DuplicateFieldException("cpf", userDTO.getCpf());
        }

        if (userDTO.getType().equals("COMERCIANTE") && userRepository.existsByCnpjAndIdUserNot(userDTO.getCnpj(), userDTO.getIdUser())) {
            throw new DuplicateFieldException("cnpj", userDTO.getCnpj());
        }
    }
}
