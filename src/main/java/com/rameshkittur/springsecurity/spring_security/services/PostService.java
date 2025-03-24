package com.rameshkittur.springsecurity.spring_security.services;



import com.rameshkittur.springsecurity.spring_security.dto.PostDto;

import java.util.List;

public interface PostService {

    List<PostDto>getAllPosts();

    PostDto savePost(PostDto inputPost);

    PostDto getPostById(Long id);

    PostDto updatePost(PostDto post, Long postId);
}
