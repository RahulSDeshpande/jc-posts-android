package com.rahulografy.jcposts.util

object Constants {

    object Network {

        object Api {

            const val URL_BASE = "https://jsonplaceholder.typicode.com/"
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