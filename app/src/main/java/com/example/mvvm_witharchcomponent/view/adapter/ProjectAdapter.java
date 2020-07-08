package com.example.mvvm_witharchcomponent.view.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mvvm_witharchcomponent.R;
import com.example.mvvm_witharchcomponent.databinding.ProjectListRowBinding;
import com.example.mvvm_witharchcomponent.services.model.Project;
import com.example.mvvm_witharchcomponent.view.callback.ProjectClickListener;

import java.util.List;
import java.util.Objects;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.RecyclerView.ViewHolder;

public class ProjectAdapter extends RecyclerView.Adapter<ProjectAdapter.ProjectViewHolder> {
    List<? extends Project> projects;

    ProjectClickListener projectClickListener;

    public ProjectAdapter(ProjectClickListener projectClickListener){
        this.projectClickListener = projectClickListener;
    }

    public void setProjects(final List<? extends Project> projects){
        if(this.projects == null){
            this.projects = projects;
            notifyItemRangeInserted(0, projects.size());
        }
        else {
            DiffUtil.DiffResult diffResult = DiffUtil.calculateDiff(new DiffUtil.Callback() {
                @Override
                public int getOldListSize() {
                    return ProjectAdapter.this.projects.size();
                }

                @Override
                public int getNewListSize() {
                    return projects.size();
                }

                @Override
                public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
                    return ProjectAdapter.this.projects.get(oldItemPosition).id == projects.get(newItemPosition).id;
                }

                @Override
                public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
                    Project newProject = projects.get(newItemPosition);
                    Project oldProject = ProjectAdapter.this.projects.get(oldItemPosition);
                    return newProject.id == oldProject.id && Objects.equals(newProject.git_url, oldProject.git_url);
                }
            });

            this.projects = projects;
            diffResult.dispatchUpdatesTo(this);
        }
    }

    @NonNull
    @Override
    public ProjectViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ProjectListRowBinding rowBinding = DataBindingUtil.inflate(LayoutInflater.from(parent.getContext()),
                R.layout.project_list_row, parent,false);

        rowBinding.setCallback(projectClickListener);

        return new ProjectViewHolder(rowBinding);
    }

    @Override
    public void onBindViewHolder(@NonNull ProjectViewHolder holder, int position) {
        holder.binding.setProject(projects.get(position));
        holder.binding.executePendingBindings();
    }

    @Override
    public int getItemCount() {
        return projects != null ? projects.size() : 0;
    }


    public class ProjectViewHolder extends ViewHolder {

        ProjectListRowBinding binding = null;
        public ProjectViewHolder(ProjectListRowBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }
}
