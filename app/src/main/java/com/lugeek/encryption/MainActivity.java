package com.lugeek.encryption;

import android.app.Activity;

import android.app.ActionBar;


import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.support.v4.widget.DrawerLayout;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class MainActivity extends FragmentActivity
        implements NavigationDrawerFragment.NavigationDrawerCallbacks {

    /**
     * Fragment managing the behaviors, interactions and presentation of the navigation drawer.
     */
    private NavigationDrawerFragment mNavigationDrawerFragment;

    /**
     * Used to store the last screen title. For use in {@link #restoreActionBar()}.
     */
    private CharSequence mTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mNavigationDrawerFragment = (NavigationDrawerFragment)
                getSupportFragmentManager().findFragmentById(R.id.navigation_drawer);
        mTitle = getTitle();

        // Set up the drawer.
        mNavigationDrawerFragment.setUp(
                R.id.navigation_drawer,
                (DrawerLayout) findViewById(R.id.drawer_layout));
    }

    /**
     * 实现接口申明的方法。
     * @param position :the position of item in drawer
     */
    @Override
    public void onNavigationDrawerItemSelected(int position) {
        // update the main content by replacing fragments
        FragmentManager fragmentManager = getSupportFragmentManager();

        switch (position){
            case 0:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, Base64Fragment.newInstance(position + 1))
                        .commit();
                break;
            case 1:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, MD5Fragment.newInstance(position + 1))
                        .commit();
                break;
            case 2:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SHAFragment.newInstance(position + 1,"SHA-1"))
                        .commit();
                break;
            case 3:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SHAFragment.newInstance(position + 1,"SHA-256"))
                        .commit();
                break;
            case 4:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SHAFragment.newInstance(position + 1,"SHA-384"))
                        .commit();
                break;
            case 5:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, SHAFragment.newInstance(position + 1,"SHA-512"))
                        .commit();
                break;
            case 6:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, HmacFragment.newInstance(position + 1,"HmacMD5"))
                        .commit();
                break;
            case 7:
                fragmentManager.beginTransaction()
                        .replace(R.id.container,HmacFragment.newInstance(position + 1,"HmacSHA1"))
                        .commit();
                break;
            case 8:
                fragmentManager.beginTransaction()
                        .replace(R.id.container,HmacFragment.newInstance(position + 1,"HmacSHA256"))
                        .commit();
                break;
            case 9:
                fragmentManager.beginTransaction()
                        .replace(R.id.container,HmacFragment.newInstance(position + 1,"HmacSHA384"))
                        .commit();
                break;
            case 10:
                fragmentManager.beginTransaction()
                        .replace(R.id.container,HmacFragment.newInstance(position + 1,"HmacSHA512"))
                        .commit();
                break;
            case 11:
                fragmentManager.beginTransaction()
                        .replace(R.id.container,DESFragment.newInstance(position + 1))
                        .commit();
                break;
            case 12:
                fragmentManager.beginTransaction()
                        .replace(R.id.container,AESFragment.newInstance(position + 1))
                        .commit();
                break;
            case 13:
                fragmentManager.beginTransaction()
                        .replace(R.id.container,RSAFragment.newInstance(position + 1))
                        .commit();
                break;
            default:
                fragmentManager.beginTransaction()
                        .replace(R.id.container, PlaceholderFragment.newInstance(position + 1))
                        .commit();
                break;
        }
    }

    public void onSectionAttached(int number) {
        switch (number) {
            case 1:
                mTitle = getString(R.string.title_section1);
                break;
            case 2:
                mTitle = getString(R.string.title_section2);
                break;
            case 3:
                mTitle = getString(R.string.title_section3);
                break;
            case 4:
                mTitle = getString(R.string.title_section5);
                break;
            case 5:
                mTitle = getString(R.string.title_section6);
                break;
            case 6:
                mTitle = getString(R.string.title_section7);
                break;
            case 7:
                mTitle = getString(R.string.title_section8);
                break;
            case 8:
                mTitle = getString(R.string.title_section9);
                break;
            case 9:
                mTitle = getString(R.string.title_section11);
                break;
            case 10:
                mTitle = getString(R.string.title_section12);
                break;
            case 11:
                mTitle = getString(R.string.title_section13);
                break;
            case 12:
                mTitle = "DES";
                break;
            case 13:
                mTitle = "AES";
                break;
            case 14:
                mTitle = "RSA";
                break;

        }
    }

    public void restoreActionBar() {
        ActionBar actionBar = getActionBar();
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
        actionBar.setDisplayShowTitleEnabled(true);
        actionBar.setTitle(mTitle);
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (!mNavigationDrawerFragment.isDrawerOpen()) {
            // Only show items in the action bar relevant to this screen
            // if the drawer is not showing. Otherwise, let the drawer
            // decide what to show in the action bar.
            getMenuInflater().inflate(R.menu.global, menu);
            restoreActionBar();
            return true;
        }
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Uri uri = Uri.parse("http://lugeek.com");
            Intent intent = new Intent(Intent.ACTION_VIEW,uri);
            startActivity(intent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber) {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        public PlaceholderFragment() {
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                Bundle savedInstanceState) {
            View rootView = inflater.inflate(R.layout.fragment_main, container, false);
            return rootView;
        }

        @Override
        public void onAttach(Activity activity) {
            super.onAttach(activity);
            ((MainActivity) activity).onSectionAttached(
                    getArguments().getInt(ARG_SECTION_NUMBER));
        }
    }

}
