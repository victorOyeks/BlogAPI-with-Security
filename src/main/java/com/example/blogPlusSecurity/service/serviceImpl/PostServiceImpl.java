package com.example.blogPlusSecurity.service.serviceImpl;

import com.example.blogPlusSecurity.entity.Post;
import com.example.blogPlusSecurity.entity.User;
import com.example.blogPlusSecurity.exceptions.ResourceNotFoundException;
import com.example.blogPlusSecurity.DTO.PostDTO;
import com.example.blogPlusSecurity.DTO.PostResponse;
import com.example.blogPlusSecurity.jwt.JwtService;
import com.example.blogPlusSecurity.repository.PostRepository;
import com.example.blogPlusSecurity.repository.UserRepository;
import com.example.blogPlusSecurity.service.PostService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper mapper;
    private final UserRepository userRepository;

//    @Override
//    public PostDTO createPost(PostDTO postDTO, Long userId, String token) {
//        User user = userRepository.findById(userId).orElseThrow(()-> new ResourceNotFoundException("User", "userId", userId));
////        Post post = mapToEntity(postDTO);
//        String userEmail = jwtService.extractUsername(token);
//        if(!user.getEmail().equals(userEmail)){
//            new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
//        }
//        Post post = mapper.map(postDTO, Post.class);
//        post.setUser(user);
//        Post newPost = postRepository.save(post);
//        return mapToDTO(newPost);
//    }

    @Override
    public PostDTO createPost(PostDTO postDTO) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        User user = userRepository.findByEmail(userEmail).orElseThrow(() -> new ResourceNotFoundException("User", "email", userEmail));
        Post post = mapper.map(postDTO, Post.class);
        post.setUser(user);
        Post newPost = postRepository.save(post);
        return mapToDTO(newPost);
    }

    @Override
    public PostResponse getAllPosts(int pageNo, int pagSize, String sortBy, String sortDir) {

        Sort sort = sortDir.equalsIgnoreCase(Sort.Direction.ASC.name()) ? Sort.by(sortBy).ascending() : Sort.by(sortBy).descending();

        // create Pageable instance
        Pageable pageable = PageRequest.of(pageNo, pagSize, sort);
        Page<Post> posts = postRepository.findAll(pageable);

        // get content from page object
        List<Post> listOfPosts = posts.getContent();
        List<PostDTO> content = listOfPosts.stream().map(post -> mapToDTO(post)).collect(Collectors.toList());

        PostResponse postResponse = new PostResponse();
        postResponse.setContent(content);
        postResponse.setPageNo(posts.getNumber());
        postResponse.setPageSize(posts.getSize());
        postResponse.setTotalElements(posts.getTotalElements());
        postResponse.setTotalPages(posts.getTotalPages());
        postResponse.setLast(posts.isLast());

        return postResponse;
    }

    @Override
    public PostDTO getPostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        return mapToDTO(post);
    }

    @Override
    public PostDTO updatePost(PostDTO postDTO, Long id) {
        // get post by id from the database
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        post.setTitle(postDTO.getTitle());
        post.setDescription(postDTO.getDescription());
        post.setContent(postDTO.getContent());

        Post updatedPost = postRepository.save(post);
        return mapToDTO(updatedPost);
    }

    @Override
    public void deletePostById(Long id) {
        Post post = postRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Post", "id", id));
        postRepository.delete(post);
    }

    //convert entity into DTO
    private PostDTO mapToDTO(Post post) {
//        PostDTO postDTO = new PostDTO();
//        postDTO.setId(post.getId());
//        postDTO.setTitle(post.getTitle());
//        postDTO.setDescription(post.getDescription());
//        postDTO.setContent(post.getContent());
        return mapper.map(post, PostDTO.class);
    }

    //convert DTO to Entity
    private Post mapToEntity(PostDTO postDTO) {
//        Post post = new Post();
//        post.setTitle(postDTO.getTitle());
//        post.setDescription(postDTO.getDescription());
//        post.setContent(postDTO.getContent());
        return mapper.map(postDTO, Post.class);
    }
}
