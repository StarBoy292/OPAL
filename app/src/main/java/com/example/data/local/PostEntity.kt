package com.example.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "posts")
data class PostEntity(
    @PrimaryKey val id: String,
    val authorName: String,
    val authorUsername: String,
    val authorAvatarUrl: String?,
    val contentText: String,
    val imageUrl: String?,
    val likesCount: Int,
    val commentsCount: Int,
    val timestamp: Long = System.currentTimeMillis(),
    val isLikedByMe: Boolean = false
)
