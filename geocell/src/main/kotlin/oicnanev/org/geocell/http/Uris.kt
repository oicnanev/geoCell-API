package main.kotlin.oicnanev.org.geocell.http

import org.springframework.web.util.UriTemplate
import java.net.URI

object Uris {
    const val PREFIX = "/api"
    const val HOME = PREFIX

    fun home(): URI = URI(HOME)

    object Users {
        const val CREATE = "$PREFIX/users"
        const val TOKEN = "$PREFIX/users/token"
        const val LOGOUT = "$PREFIX/logout"
        const val GET_BY_ID = "$PREFIX/users/{id}"
        const val HOME = "$PREFIX/me"

        fun byId(id: Int) = UriTemplate(GET_BY_ID).expand(id)
        fun home(): URI = URI(HOME)
    }

    object Search {
        const val SIMPLE = "$PREFIX/search"
        const val ADVANCED = "$PREFIX/search/advanced"
        const val GEO = "$PREFIX/search/geo"
        const val ADMINISTRATIVE = "$PREFIX/search/administrative"
        const val NETWORK = "$PREFIX/search/network"
    }

    object Operators {
        const val OPERATORS = "$PREFIX/operators"
    }

    object Apps {
        const val APPS = "$PREFIX/apps"
    }
}
