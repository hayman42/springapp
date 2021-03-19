package com.hayman42.springboot.web.dto

import com.hayman42.springboot.domain.posts.Posts

class PostsResponseDto(
    var id: Long?,
    var title: String,
    var content: String,
    var author: String
) {
    constructor(entity: Posts) : this(entity.id, entity.title, entity.content, entity.author)
}