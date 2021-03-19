package com.hayman42.springboot.web.dto

import com.hayman42.springboot.domain.posts.Posts
import java.time.LocalDateTime

class PostsListResponseDto(
    var id: Long?,
    var title: String,
    var author: String,
    var modifiedDate: LocalDateTime?
) {
    constructor(entity: Posts) : this(entity.id, entity.title, entity.author, entity.modifiedDate)
}