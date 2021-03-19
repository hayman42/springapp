package com.hayman42.springboot.service

import com.hayman42.springboot.domain.posts.PostsRepository
import com.hayman42.springboot.web.dto.PostsListResponseDto
import com.hayman42.springboot.web.dto.PostsResponseDto
import com.hayman42.springboot.web.dto.PostsSaveRequestDto
import com.hayman42.springboot.web.dto.PostsUpdateRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.stream.Collectors
import javax.transaction.Transactional

@Service
class PostsService {
    @Autowired
    private lateinit var postsRepository: PostsRepository

    @Transactional
    fun save(requestDto: PostsSaveRequestDto): Long? {
        var post = requestDto.toEntity()
        postsRepository.save(post)
        return post.id
    }

    @Transactional
    fun update(id: Long, requestDto: PostsUpdateRequestDto): Long {
        var post = postsRepository.findById(id).orElseThrow {
            IllegalArgumentException("Wrong id, id=${id}")
        }

        post.update(requestDto.title, requestDto.content)
        return id
    }

    fun findById(id: Long): PostsResponseDto {
        var entity = postsRepository.findById(id).orElseThrow {
            IllegalArgumentException("Wrong id, id=${id}")
        }

        return PostsResponseDto(entity)
    }

    @Transactional
    fun findAllDesc(): List<PostsListResponseDto> = postsRepository.findAllDesc()
        .stream().map { post -> PostsListResponseDto(post) }.collect(Collectors.toList())

    @Transactional
    fun delete(id: Long): Long {
        var posts = postsRepository.findById(id).orElseThrow {
            IllegalArgumentException("Wring id, id=${id}")
        }

        postsRepository.delete(posts)
        return id
    }

}