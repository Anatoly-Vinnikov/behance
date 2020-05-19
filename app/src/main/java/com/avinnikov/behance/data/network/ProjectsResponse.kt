package com.avinnikov.behance.data.network

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ProjectsResponse(
    val projects: List<ProjectItem>
)