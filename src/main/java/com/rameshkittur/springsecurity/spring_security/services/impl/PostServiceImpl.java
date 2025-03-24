package com.rameshkittur.springsecurity.spring_security.services.impl;


import com.rameshkittur.springsecurity.spring_security.dto.PostDto;
import com.rameshkittur.springsecurity.spring_security.entities.PostEntity;
import com.rameshkittur.springsecurity.spring_security.exceptions.ResourceNotFoundException;
import com.rameshkittur.springsecurity.spring_security.repositories.PostRepository;
import com.rameshkittur.springsecurity.spring_security.services.PostService;
import lombok.RequiredArgsConstructor;

import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class PostServiceImpl implements PostService {

    private final PostRepository postRepository;
    private final ModelMapper modelMapper;

    @Override
    public List<PostDto> getAllPosts() {
        return postRepository.findAll()
                .stream()
                .map(post ->modelMapper.map(post,PostDto.class))
                .toList();
    }

    @Override
    public PostDto savePost(PostDto inputPost) {
        PostEntity post = modelMapper.map(inputPost,PostEntity.class);
        return modelMapper.map(postRepository.save(post),PostDto.class);
    }

    @Override
    public PostDto getPostById(Long id) {
        PostEntity post = postRepository
                .findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post is not available for the id: " + id));

        return modelMapper.map(post,PostDto.class);
    }

    @Override
    public PostDto updatePost(PostDto post, Long postId) {
       PostEntity oldPost = postRepository
               .findById(postId)
               .orElseThrow(() -> new ResourceNotFoundException("Post is not available for the id: " + postId));
       post.setId(postId);
       modelMapper.map(post,oldPost);

       PostEntity savedPost = postRepository.save(oldPost);
       return modelMapper.map(savedPost,PostDto.class);

    }
}
