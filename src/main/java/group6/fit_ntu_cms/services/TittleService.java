package group6.fit_ntu_cms.services;

import group6.fit_ntu_cms.models.TittleModel;
import group6.fit_ntu_cms.repositories.TittleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TittleService {

    @Autowired
    private TittleRepository tittleRepository;

    // Create or Update a Tittle (Category)
    @Transactional
    public TittleModel saveTittle(TittleModel tittle) {
        return tittleRepository.save(tittle);
    }

    // Read a Tittle by ID
    public Optional<TittleModel> getTittleById(Long id) {
        return tittleRepository.findById(id);
    }

    // Read all Tittles
    public List<TittleModel> getAllTittles() {
        return tittleRepository.findAll();
    }

    // Delete a Tittle by ID
    @Transactional
    public void deleteTittle(Long id) {
        tittleRepository.deleteById(id);
    }
    public List<TittleModel> getTittles(String search, int page, int pageSize) {
        PageRequest pageRequest = PageRequest.of(page - 1, pageSize);
        if (search != null && !search.isEmpty()) {
            return tittleRepository.findByTitleNameContainingIgnoreCase(search, pageRequest).getContent();
        }
        return tittleRepository.findAll(pageRequest).getContent();
    }

    public int countTittles(String search) {
        if (search != null && !search.isEmpty()) {
            return (int) tittleRepository.countByTitleNameContainingIgnoreCase(search);
        }
        return (int) tittleRepository.count();
    }
}