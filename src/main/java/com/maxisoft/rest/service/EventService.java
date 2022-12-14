package com.maxisoft.rest.service;

import com.maxisoft.rest.model.Event;
import com.maxisoft.rest.repository.impHibernate.EventHibernateRepoImpl;

import java.util.List;

public class EventService implements GenericService<Event, Long> {

    private final EventHibernateRepoImpl eventRepository;

    public EventService() {
        eventRepository = new EventHibernateRepoImpl();
    }

    @Override
    public Event getById(Long id) {
        return eventRepository.getById(id);
    }

    @Override
    public boolean delete(Long id) {
        return eventRepository.delete(id);
    }

    @Override
    public Event update(Event event) {
        return eventRepository.update(event);
    }

    @Override
    public List<Event> getAll() {
        return eventRepository.getAll();
    }

    @Override
    public Event save(Event event) {
        return eventRepository.save(event);
    }
}
