package com.example.mvvm_witharchcomponent.services.repository;

import com.example.mvvm_witharchcomponent.services.model.Project;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

/*Interfrace for calling GitHub services*/
public interface GitHubService {
    String GITHUB_API_URL = "https://api.github.com/";

    @GET("users/{user}/repos")
    Call<List<Project>> getProjectList(@Path("user") String user);

    @GET("/repo/{user}/{reponame}")
    Call<Project> getProjectDetails(@Path("user") String user, @Path("reponame") String reponame);
}
