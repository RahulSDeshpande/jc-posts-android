package com.rahulografy.jcposts.data.source.local.posts.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName
import com.rahulografy.jcposts.util.RoomConverter

@Entity(tableName = "post")
@TypeConverters(RoomConverter::class)
data class PostEntity(

    @PrimaryKey
    @ColumnInfo(name = "id")
    @Expose
    @SerializedName("id")
    val id: Int?,

    @ColumnInfo(name = "body")
    @Expose
    @SerializedName("body")
    val body: String?,

    @ColumnInfo(name = "title")
    @Expose
    @SerializedName("title")
    val title: String?,

    @ColumnInfo(name = "userId")
    @Expose
    @SerializedName("userId")
    val userId: Int?,

    @ColumnInfo(name = "isFavorite")
    var isFavorite: Boolean? = false
)