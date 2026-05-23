package com.example.data.repository

import com.example.data.local.PostDao
import com.example.data.local.PostEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

class PostRepository(private val postDao: PostDao) {
    val allPosts: Flow<List<PostEntity>> = postDao.getAllPosts()
    
    suspend fun toggleLike(post: PostEntity) {
        val delta = if (post.isLikedByMe) -1 else 1
        postDao.updateLikeStatus(post.id, !post.isLikedByMe, delta)
    }

    suspend fun populateFakeData() {
        if (postDao.count() == 0) {
            val fakes = listOf(
                PostEntity(
                    id = UUID.randomUUID().toString(),
                    authorName = "Alex Rivera",
                    authorUsername = "@arivera",
                    authorAvatarUrl = null,
                    contentText = "Just saw the most incredible sunset at the beach today. The colors were absolutely surreal! 🌅🌊 #nature #vibes",
                    imageUrl = null,
                    likesCount = 234,
                    commentsCount = 12
                ),
                PostEntity(
                    id = UUID.randomUUID().toString(),
                    authorName = "Jordan Lee",
                    authorUsername = "@jlee_creative",
                    authorAvatarUrl = null,
                    contentText = "Working on a new generative art series. Here's a sneak peek... ✨🤖",
                    imageUrl = null, // In a real app we'd load an image
                    likesCount = 89,
                    commentsCount = 4
                ),
                PostEntity(
                    id = UUID.randomUUID().toString(),
                    authorName = "Samira Khan",
                    authorUsername = "@samira_k",
                    authorAvatarUrl = null,
                    contentText = "Anyone else feel like this week lasted a month? Finally Friday! 🙌",
                    imageUrl = null,
                    likesCount = 452,
                    commentsCount = 56
                )
            )
            postDao.insertPosts(fakes)
        }
    }
}
