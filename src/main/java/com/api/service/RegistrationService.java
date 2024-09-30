package com.api.service;

import com.api.entity.Registration;
import com.api.exception.ResourceNotFoundException;
import com.api.payload.RegistrationDto;
import com.api.repository.RegistrationRepository;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegistrationService {

    private RegistrationRepository registrationRepository;
    private ModelMapper modelMapper;


    public RegistrationService(RegistrationRepository registrationRepository, ModelMapper modelMapper) {
        this.registrationRepository = registrationRepository;
        this.modelMapper = modelMapper;
    }

    public List<RegistrationDto> getAllRegistration(){
        List<Registration> registrations = registrationRepository.findAll();
        List<RegistrationDto> dtos = registrations.stream().map(x -> mapToDto(x)).collect(Collectors.toList());
        return dtos;
    }

    public RegistrationDto createRegistration(RegistrationDto registrationDto) {
        // copy dto to Entity
        Registration registration = mapToEntity(registrationDto);
        Registration savedEntity = registrationRepository.save(registration);

        // copy entity to Dto
        RegistrationDto dto = mapToDto(registration);
        return dto;
    }

    public void deleteRegistration(long id) {
        registrationRepository.deleteById(id);
    }


    public Registration updateRegistration(long id, Registration registration) {
        Registration r = registrationRepository.findById(id).get();
        r.setName(registration.getName());
        r.setMobile(registration.getMobile());
        r.setEmail(registration.getEmail());
        Registration savedEntity = registrationRepository.save(r);
        return savedEntity;
    }
        //mapToEntity
        Registration mapToEntity(RegistrationDto registrationDto){
            Registration registration = modelMapper.map(registrationDto, Registration.class);
            return registration ;
    }
        //mapToDto
        RegistrationDto mapToDto(Registration registration){
            RegistrationDto dto = modelMapper.map(registration, RegistrationDto.class);
            return dto;

        }

    public RegistrationDto getRegistrationById(long id) {
        Registration registration = registrationRepository.findById(id).orElseThrow(()->new ResourceNotFoundException("Record not found"));
        return mapToDto(registration);
    }
}
