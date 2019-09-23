package pl.potera.hotel.service;

import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import pl.potera.hotel.model.dto.ReservationDto;
import pl.potera.hotel.repository.ReservationsRepository;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ModelMapper modelMapper;
    private final ReservationsRepository repository;

    public List<ReservationDto> findAll() {
        return repository.findAll().stream()
                .map(reservation -> modelMapper.map(reservation, ReservationDto.class))
                .collect(Collectors.toList());
    }

}
