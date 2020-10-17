package com.rahulografy.jcposts.util

object Constants {

    object Network {

        object Api {
            const val URL_BASE = "https://jsonplaceholder.typicode.com/"

            const val URL_GET_POSTS = "posts"

            const val URL_PATH_POST_ID = "postId"
            const val URL_GET_COMMENTS = "$URL_GET_POSTS/{$URL_PATH_POST_ID}/comments"
        }

        object Timeout {
            const val CONNECTION = 10L
            const val WRITE = 10L
            const val READ = 30L
        }

        object Cache {
            const val NAME = "JCPostsCache"
        }
    }
}