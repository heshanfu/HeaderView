/*
 * The MIT License (MIT)
 *
 * Copyright (c) 2015 Raphaël Bussa
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package rebus.header.view.sample;

import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import com.squareup.picasso.Picasso;

import rebus.header.view.HeaderCompactView;
import rebus.header.view.HeaderInterface;
import rebus.header.view.HeaderView;

public class HeaderCompactActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private Toolbar toolbar;
    private NavigationView navigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_header);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle(getString(R.string.header_compact));
        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.addHeaderView(headerView());
        actionBarDrawerToggle = new ActionBarDrawerToggle(HeaderCompactActivity.this, drawerLayout, toolbar, R.string.app_name, R.string.app_name);
        drawerLayout.post(new Runnable() {
            @Override
            public void run() {
                actionBarDrawerToggle.syncState();
            }
        });
        drawerLayout.setDrawerListener(actionBarDrawerToggle);
    }

    private HeaderCompactView headerView() {
        HeaderCompactView headerView = new HeaderCompactView(HeaderCompactActivity.this, false);
        headerView.background().setBackgroundColor(getResources().getColor(R.color.primary_dark));
        Picasso.with(HeaderCompactActivity.this)
                .load("http://www.nexus-lab.com/wp-content/uploads/2014/08/image_new-material.jpeg")
                .into(headerView.background());
        Picasso.with(HeaderCompactActivity.this)
                .load("https://avatars1.githubusercontent.com/u/3964819?v=3&s=460")
                .into(headerView.avatar());
        headerView.username("Raphael Bussa");
        headerView.email("rapahelbussa@gmail.com");
        headerView.setOnHeaderClickListener(new HeaderInterface.OnHeaderClickListener() {
            @Override
            public void onClick() {
                drawerLayout.closeDrawer(GravityCompat.START);
            }
        });
        return headerView;
    }

    @Override
    public void onBackPressed() {
        if (drawerLayout != null && drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

}
