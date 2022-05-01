package com.sepm.chatocity;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.sepm.chatocity.databinding.ActivityNavigationBinding;
import com.sepm.chatocity.ui.chat.ChatFragment;
import com.sepm.chatocity.ui.notes.NotesFragment;
import com.sepm.chatocity.ui.profile.ProfileFragment;
import com.sepm.chatocity.ui.settings.SettingsFragment;

public class Navigation extends AppCompatActivity {

    private ActivityNavigationBinding binding;
//        BottomNavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        replaceFragment(new ProfileFragment());

     binding.navView.setOnItemSelectedListener(item -> {
         
         switch (item.getItemId()){
             case R.id.nav_profile:
                 replaceFragment(new ProfileFragment());
                 break;
             case R.id.nav_chat:
                 replaceFragment(new ChatFragment());
                 break;
             case R.id.nav_notes:
                 replaceFragment(new NotesFragment());
                 break;
             case R.id.nav_settings:
                 replaceFragment(new SettingsFragment());
                 break;
         }

         return true;
     });
//        binding = ActivityNavigationBinding.inflate(getLayoutInflater());
//        setContentView(binding.getRoot());
//
////        setContentView(R.layout.activity_navigation);
//
//        BottomNavigationView navView = findViewById(R.id.nav_view);
//        // Passing each menu ID as a set of Ids because each
//        // menu should be considered as top level destinations.
//        AppBarConfiguration appBarConfiguration = new AppBarConfiguration.Builder(
//                R.id.navigation_profile, R.id.navigation_chat, R.id.navigation_notes,R.id.navigation_settings)
//                .build();
//        NavController navController = androidx.navigation.Navigation.findNavController(this, R.id.nav_host_fragment_activity_navigation);
//        NavigationUI.setupActionBarWithNavController(this, navController, appBarConfiguration);
//        NavigationUI.setupWithNavController(navView, navController);
//
    }

    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.container,fragment);
        fragmentTransaction.commit();
    }

}