package com.example.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface PostDao {
    @Query("SELECT * FROM posts ORDER BY timestamp DESC")
    fun getAllPosts(): Flow<List<PostEntity>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPosts(posts: List<PostEntity>)

    @Query("UPDATE posts SET isLikedByMe = :liked, likesCount = likesCount + :delta WHERE id = :postId")
    suspend fun updateLikeStatus(postId: String, liked: Boolean, delta: Int)
    
    @Query("SELECT COUNT(*) FROM posts")
    suspend fun count(): Int
}
