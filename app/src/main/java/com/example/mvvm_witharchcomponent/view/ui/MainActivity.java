package com.example.mvvm_witharchcomponent.view.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import android.os.Bundle;

import com.example.mvvm_witharchcomponent.R;
import com.example.mvvm_witharchcomponent.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    ActivityMainBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);

        binding = DataBindingUtil.setContentView(this, R.layout.activity_main);

//        Adding project list fragment as first fragment. This can be done from xml as well
        if(savedInstanceState == null){
            ProjectListFragment fragment = ProjectListFragment.newInstance(null, null);

            getSupportFragmentManager()
                    .beginTransaction()
                    .add(binding.fragmentContainer.getId(), fragment, fragment.getClass().getSimpleName())
                    .commit();
        }
    }
}
