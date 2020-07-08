package com.example.mvvm_witharchcomponent.services.repository;

import com.example.mvvm_witharchcomponent.services.model.Project;

import java.util.List;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/*It is data provider for the ViewModel*/
public class ProjectRepository {

    private GitHubService gitHubService;
    private static ProjectRepository instance;

    private ProjectRepository(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(GitHubService.GITHUB_API_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        gitHubService = retrofit.create(GitHubService.class);
    }

    static {
        instance = new ProjectRepository();
    }

    public static ProjectRepository getInstance(){
        return instance;
    }

    public LiveData<List<Project>> getProjectList(String userId){
        final MutableLiveData<List<Project>> data = new MutableLiveData<>();

        gitHubService.getProjectList(userId).enqueue(new Callback<List<Project>>() {
            @Override
            public void onResponse(Call<List<Project>> call, Response<List<Project>> response) {
                data.setValue(response.body());
            }

            @Override
            public void onFailure(Call<List<Project>> call, Throwable t) {
                data.setValue(null);
            }
        });

        return data;
    }


}
