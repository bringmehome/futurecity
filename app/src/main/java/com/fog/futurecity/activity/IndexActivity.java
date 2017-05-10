package com.fog.futurecity.activity;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;

import com.fog.futurecity.R;
import com.fog.futurecity.fragment.GroupFragment;
import com.fog.futurecity.fragment.MapFragment;
import com.fog.futurecity.fragment.TaskFragment;
import com.fog.futurecity.fragment.WarnFragment;

public class IndexActivity extends AppCompatActivity {

    private Toolbar toolbar;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            changeTag(item.getItemId());
//            switch (item.getItemId()) {
//                case R.id.navigation_home:
//                    mTextMessage.setText(R.string.title_home);
//                    return true;
//                case R.id.nav_map:
//                    mTextMessage.setText(R.string.title_dashboard);
//                    return true;
//                case R.id.navigation_notifications:
//                    mTextMessage.setText(R.string.title_notifications);
//                    return true;
//                case R.id.nav_mine:
//                    mTextMessage.setText(R.string.title_mine);
//                    changeTag(R.id.nav_mine);
//                    return true;
//            }
            return true;
        }

    };

    /**
     * 切换fragment
     */
    private FragmentManager fragmentManager = getFragmentManager();
    private Fragment currentFragment;

    private void changeTag(int id) {
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Fragment fragment = fragmentManager.findFragmentByTag(String.valueOf(id));

        if (fragment == null) {
            if (currentFragment != null) {
                fragmentTransaction.hide(currentFragment);
            }
            try {
                switch (id) {
                    case R.id.nav_group:
                        fragment = GroupFragment.class.newInstance();
                        break;
                    case R.id.nav_map:
                        fragment = MapFragment.class.newInstance();
                        break;
                    case R.id.nav_task:
                        fragment = TaskFragment.class.newInstance();
                        break;
                    case R.id.nav_warn:
                        fragment = WarnFragment.class.newInstance();
                        break;
                    default:
                        fragment = GroupFragment.class.newInstance();
                        break;
                }
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            fragmentTransaction.add(R.id.flContainer, fragment, String.valueOf(id));
        } else if (fragment == currentFragment) {
        } else {
            fragmentTransaction.hide(currentFragment);
            fragmentTransaction.show(fragment);
        }

        fragmentTransaction.commit();
        currentFragment = fragment;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        changeTag(R.id.nav_group);
    }

}
