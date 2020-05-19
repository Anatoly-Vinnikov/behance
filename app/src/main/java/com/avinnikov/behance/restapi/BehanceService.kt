package com.avinnikov.behance.restapi

import com.avinnikov.behance.data.network.ProjectResponse
import com.avinnikov.behance.data.network.ProjectsResponse
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface BehanceService {
    @GET("projects?sort=appreciations")
    suspend fun getProjects(
        @Query("page") page: Int
    ): ProjectsResponse

    @GET("projects/{project_id}")
    suspend fun getProject(
        @Path("project_id") id: Int
    ): ProjectResponse
}