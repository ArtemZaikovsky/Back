package com.trade_accounting.services.impl;

import com.trade_accounting.models.Image;
import com.trade_accounting.models.dto.ImageDto;
import com.trade_accounting.repositories.ImageRepository;
import com.trade_accounting.services.interfaces.ImageService;
import com.trade_accounting.utils.mapper.ImageMapper;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ImageServiceImpl implements ImageService {

    private final ImageRepository imageRepository;

    private final ImageMapper imageMapper;

    @Override
    public List<ImageDto> getAll() {
        return imageRepository.getAll()
                .stream()
                .map(imageMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public ImageDto getById(Long id) {
        return imageMapper.toDto(imageRepository.getOne(id));
    }

    @Override
    public ImageDto create(ImageDto dto) {
        Image image = imageMapper.toModel(dto, "picture");
        imageRepository.save(image);
        dto.setId(image.getId());
        return dto;
    }


    @Override
    public ImageDto update(ImageDto dto) {
        Image image = imageMapper.toModel(dto, "picture");
        imageRepository.save(image);
        return dto;
    }

    @SneakyThrows
    @Override
    public void deleteById(Long id) {
        Optional<Image> optional = imageRepository.findById(id);
        if (optional.isPresent() && imageRepository.countProductImage(id) == 0) {
            imageRepository.deleteById(id);
            Files.deleteIfExists(Paths.get(optional.get().getImageUrl()));
        }
    }
}
