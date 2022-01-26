package com.reemsd.day.network

data class PlanData(
//    this is id for object to edite and delete.
    val id: String? = null,
//    this is user id to show user own data
    val userid: String? = null,
    val title: String? = null, val place: String? = null,
    val cost: String? = null, val invited: String? = null, val details: String? = null
)
