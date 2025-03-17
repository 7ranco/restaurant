package aesir.api.restaurant.domain.services;

import aesir.api.restaurant.domain.dto.RolDTO;
import aesir.api.restaurant.domain.dto.RolResponseDTO;
import aesir.api.restaurant.domain.models.Rol;
import aesir.api.restaurant.domain.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@Service
public class RolServiceImpl implements RolService{
    @Autowired
    RolRepository rolRepository;
    @Override
    public RolResponseDTO createRol(RolDTO rolDTO) throws Exception {
        Rol rol = rolRepository.save(new Rol(rolDTO));
        return new RolResponseDTO(rol.getId(), rol.getRolName());
    }

    @Override
    public List<RolResponseDTO> listRols() throws Exception {
        List<Rol> rol = rolRepository.findAll();
            return rol.stream().map(res -> {
                return new RolResponseDTO(res.getId(), res.getRolName());
            }).toList();
    }

    @Override
    public RolResponseDTO getRol(Long id) throws Exception {
        Rol rol = rolRepository.findById(id).get();
        return new RolResponseDTO(rol.getId(), rol.getRolName());
    }
    @Override
    public Rol getRolId(Long rolId) throws Exception {
        return rolRepository.findById(rolId).get();
    }



}
