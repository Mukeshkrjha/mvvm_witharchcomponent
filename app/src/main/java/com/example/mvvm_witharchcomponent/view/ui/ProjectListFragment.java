package com.example.mvvm_witharchcomponent.view.ui;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.databinding.DataBindingUtil;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_witharchcomponent.R;
import com.example.mvvm_witharchcomponent.databinding.FragmentProjectListBinding;
import com.example.mvvm_witharchcomponent.services.model.Project;
import com.example.mvvm_witharchcomponent.view.adapter.ProjectAdapter;
import com.example.mvvm_witharchcomponent.view.callback.ProjectClickListener;
import com.example.mvvm_witharchcomponent.viewmodel.ProjectListViewModel;

import java.util.List;

/**
 * Display list of git repository
 */
public class ProjectListFragment extends Fragment implements ProjectClickListener {

    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    private String mParam1;
    private String mParam2;

    FragmentProjectListBinding binding;
    ProjectAdapter adapter;

    public ProjectListFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProjectListFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProjectListFragment newInstance(String param1, String param2) {
        ProjectListFragment fragment = new ProjectListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_project_list, container, false);

        adapter = new ProjectAdapter(this);
        binding.rvProjectList.setAdapter(adapter);
        binding.setIsLoading(true);

        return binding.getRoot();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Creating ViewModel reference in view
        ProjectListViewModel projectListViewModel = ViewModelProviders.of(this).get(ProjectListViewModel.class);
        observeViewModel(projectListViewModel);
    }

//    ViewModel observer
    private void observeViewModel(ProjectListViewModel projectListViewModel){
        projectListViewModel.getProjectListObservable().observe(this, new Observer<List<Project>>() {
            @Override
            public void onChanged(List<Project> projects) {
                if(projects != null){
                    adapter.setProjects(projects);
                    binding.setIsLoading(false);
                }
            }
        });
    }

    @Override
    public void onProjectClick(Project project) {

    }
}
