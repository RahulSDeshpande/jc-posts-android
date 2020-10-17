package com.rahulografy.jcposts.data.source.local.comments.model

import androidx.room.*
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rahulografy.jcposts.data.source.local.posts.model.PostEntity
import com.rahulografy.jcposts.util.RoomConverter

@Entity(
    tableName = "comment",
    foreignKeys = [ForeignKey(
        entity = PostEntity::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("postId"),
        onDelete = ForeignKey.CASCADE
    )]
)
@TypeConverters(RoomConverter::class)
data class CommentEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "postId")
    @Expose
    @SerializedName("postId")
    val postId: Int?,

    @ColumnInfo(name = "name")
    @Expose
    @SerializedName("name")
    val name: String?,

    @ColumnInfo(name = "email")
    @Expose
    @SerializedName("email")
    val email: String?,

    @ColumnInfo(name = "body")
    @Expose
    @SerializedName("body")
    val body: String?
)