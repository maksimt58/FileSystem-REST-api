package com.maxisoft.rest.service;

import com.maxisoft.rest.model.File;
import com.maxisoft.rest.repository.impHibernate.FileHibernateRepoImpl;

import java.util.List;

public class FileService implements GenericService<File, Long> {
    private final FileHibernateRepoImpl fileRepository;

    public FileService() {
        fileRepository = new FileHibernateRepoImpl();
    }

    @Override
    public File getById(Long id) {
        return fileRepository.getById(id);
    }

    @Override
    public boolean delete(Long id) {
        return fileRepository.delete(id);
    }

    @Override
    public File update(File file) {
        return fileRepository.update(file);
    }

    @Override
    public List<File> getAll() {
        return fileRepository.getAll();
    }

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }
}
