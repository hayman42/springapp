package com.hayman42.springboot.domain.posts

import javax.persistence.*

@Entity
class Posts(title: String, content: String, author: String) : BaseTimeEntity() {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null

    @Column(length = 500)
    var title = title

    @Column(columnDefinition = "TEXT")
    var content = content
    var author = author

    fun update(title: String, content: String) {
        this.title = title
        this.content = content

    }
}