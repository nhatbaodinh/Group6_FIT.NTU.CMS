package group6.fit_ntu_cms.services;

import group6.fit_ntu_cms.models.EventModel;
import group6.fit_ntu_cms.models.UsersModel;
import group6.fit_ntu_cms.repositories.EventRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class EventService {
  @Autowired
  private EventRepository eventRepository;

  public List<EventModel> getAllEvents() {
    return eventRepository.findAll();
  }

  public void saveEvent(EventModel event, HttpSession session) {
    UsersModel user = (UsersModel) session.getAttribute("user");
    if (user == null) {
      throw new IllegalStateException("Người dùng chưa đăng nhập.");
    }
    event.setCreateDate(LocalDateTime.now());
    event.setUser(user);
    eventRepository.save(event);
  }

  public void deleteEvent(Long eventId) {
    eventRepository.deleteById(eventId);
  }

  public Optional<EventModel> getEventById(Long eventId) {
    return eventRepository.findById(eventId);
  }
}