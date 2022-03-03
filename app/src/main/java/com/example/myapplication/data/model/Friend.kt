package com.example.myapplication.data.model
import com.squareup.moshi.Json

data class Friend(
    @Json(name = "results")
    val results: List<FriendDetails>
)

data class FriendDetails(
    @Json(name = "gender") var gender: String?,
    @Json(name = "name") var name: FriendName,
    @Json(name = "picture") var picture: FriendPicture,
    @Json(name = "location") var location: FriendLocation,
    @Json(name = "email") var email: String?,
    @Json(name = "cell") var cell: String?
)

data class FriendName(
    @Json(name = "title") var title: String?,
    @Json(name = "first") var first: String?,
    @Json(name = "last") var last: String?
)

data class FriendPicture(
    @Json(name = "large") var large: String?,
    @Json(name = "medium") var medium: String?,
    @Json(name = "thumbnail") var thumbnail: String?
)

data class FriendLocation(
    @Json(name = "street") var street: FriendStreet,
    @Json(name = "city") var city: String?,
    @Json(name = "state") var state: String?,
    @Json(name = "country") var country: String?
)

data class FriendStreet(
    @Json(name = "number") var number: Int?,
    @Json(name = "name") var name: String?
)