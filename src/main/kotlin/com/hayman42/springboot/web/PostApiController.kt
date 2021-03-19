package com.hayman42.springboot.web

import com.hayman42.springboot.service.PostsService
import com.hayman42.springboot.web.dto.PostsResponseDto
import com.hayman42.springboot.web.dto.PostsSaveRequestDto
import com.hayman42.springboot.web.dto.PostsUpdateRequestDto
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*

@RestController
class PostApiController {
    @Autowired
    private lateinit var postsService: PostsService

    @PutMapping("/api/v1/posts/{id}")
    fun update(@PathVariable id: Long, @RequestBody requestDto: PostsUpdateRequestDto): Long {
        return postsService.update(id, requestDto)
    }

    @GetMapping("/api/v1/posts/{id}")
    fun findById(@PathVariable id: Long): PostsResponseDto {
        return postsService.findById(id)
    }

    @PostMapping("/api/v1/posts")
    fun save(@RequestBody requestDto: PostsSaveRequestDto): Long? = postsService.save(requestDto)

    @DeleteMapping("/api/v1/posts/{id}")
    fun delete(@PathVariable id: Long): Long = postsService.delete(id)
}