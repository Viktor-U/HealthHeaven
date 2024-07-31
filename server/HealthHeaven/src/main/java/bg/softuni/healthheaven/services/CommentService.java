package bg.softuni.healthheaven.services;

import bg.softuni.healthheaven.model.dtos.commet.CommentDTO;
import bg.softuni.healthheaven.model.dtos.commet.CommentExportDTO;
import bg.softuni.healthheaven.model.entities.Comment;
import bg.softuni.healthheaven.model.entities.User;
import bg.softuni.healthheaven.repositories.CommentRepository;
import bg.softuni.healthheaven.repositories.DoctorRepository;
import bg.softuni.healthheaven.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final DoctorRepository doctorRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public CommentDTO addComment(CommentDTO commentDTO, Long id) {

        Comment comment = modelMapper.map(commentDTO, Comment.class);
        comment.setAuthor(userRepository.findByEmail(commentDTO.getAuthor()).get());
        comment.setDoctor(doctorRepository.findById(id).get());
        comment.setTimeOnPost(Instant.now());

        commentRepository.save(comment);

        User user = userRepository.findByEmail(commentDTO.getAuthor()).get();
        commentDTO.setTimeOnPost(Instant.now().toString());
        commentDTO.setAuthor(user.getFirstName()+ " " + user.getLastName());

        return commentDTO;

    }
}
