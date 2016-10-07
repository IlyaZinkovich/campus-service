package com.camp.campus.service.impl;

import com.camp.campus.dto.StudentDTO;
import com.camp.campus.dto.RoomDTO;
import com.camp.campus.model.Student;
import com.camp.campus.model.Room;
import com.camp.campus.repository.RoomRepository;
import com.camp.campus.repository.implementation.RoomSpecification;
import com.camp.campus.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class RoomServiceImpl implements RoomService {

    @Autowired
    private RoomRepository roomRepository;

    @Override
    public List<RoomDTO> findAll(Integer floor, Integer page, Integer size) {
        List<Room> rooms;
        if (page != null && size != null) {
            PageRequest request = new PageRequest(page, size, Sort.Direction.DESC, "roomNumber");
            rooms = roomRepository.findAll(new RoomSpecification(floor), request).getContent();
        } else {
            rooms = roomRepository.findAll(new RoomSpecification(floor));
        }
        return rooms.stream().map(this::roomToDTO).collect(Collectors.toList());
    }

    private RoomDTO roomToDTO(Room room) {
        RoomDTO dto = new RoomDTO();
        dto.setId(room.getId());
        dto.setCapacity(room.getCapacity());
        dto.setRoomNumber(room.getRoomNumber());
        dto.setFloor(room.getFloor());
        dto.setStudents(room.getStudents().stream().map(this::profileToDto).collect(Collectors.toList()));
        return dto;
    }

    private StudentDTO profileToDto(Student student) {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setId(student.getId());
        studentDTO.setFirstName(student.getFirstName());
        studentDTO.setLastName(student.getLastName());
        studentDTO.setGender(student.getGender().name());
        studentDTO.setBirthDate(student.getBirthDate());
        studentDTO.setFaculty(student.getFaculty());
        studentDTO.setSpeciality(student.getSpeciality());
        studentDTO.setCourse(student.getCourse());
        studentDTO.setGroup(student.getGroup());
        studentDTO.setRoomNumber(student.getRoom().getRoomNumber());
        studentDTO.setImagePath(student.getImagePath());
        return studentDTO;
    }
}