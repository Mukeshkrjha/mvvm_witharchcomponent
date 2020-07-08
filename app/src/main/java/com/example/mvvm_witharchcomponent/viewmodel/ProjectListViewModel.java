package com.example.mvvm_witharchcomponent.viewmodel;

import android.app.Application;

import com.example.mvvm_witharchcomponent.services.model.Project;
import com.example.mvvm_witharchcomponent.services.repository.ProjectRepository;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

public class ProjectListViewModel extends AndroidViewModel {

    private LiveData<List<Project>> projectListObservable;

    public ProjectListViewModel(Application application) {
        super(application);

        projectListObservable = ProjectRepository.getInstance().getProjectList("Facebook");
    }

    /*
    * Exposing LiveData project query so that UI can observe it.
    * */
    public LiveData<List<Project>> getProjectListObservable(){
        return projectListObservable;
    }
}
