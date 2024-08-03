package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.entities.Comment;
import bg.softuni.healthheaven.model.entities.Doctor;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.CommentRepository;
import bg.softuni.healthheaven.repositories.DoctorRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public CommentExportDTO addComment(CommentDTO commentDTO, Long id) {

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setAuthor(userRepository.findByEmail(commentDTO.getAuthor()).get());
        comment.setDoctor(doctorRepository.findById(id).get());
        comment.setTimeOnPost(Instant.now());

        Comment save = commentRepository.save(comment);

        CommentExportDTO result = modelMapper.map(save, CommentExportDTO.class);
        User user = userRepository.findByEmail(commentDTO.getAuthor()).get();
        result.setTimeOnPost(Instant.now().toString());
        result.setAuthor(user.getFirstName()+ " " + user.getLastName());


        return result;

    }

    public List<CommentExportDTO> deleteComment(Long commentId, Long doctorId) {

        commentRepository.deleteById(commentId);

        Doctor doctor = doctorRepository.findById(doctorId).get();
        List<CommentExportDTO> result = new ArrayList<>();

        for (Comment comment : doctor.getComments()) {
            CommentExportDTO map = modelMapper.map(comment, CommentExportDTO.class);
            result.add(map);
        }
        return result;

    }
}
