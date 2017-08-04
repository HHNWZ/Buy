package com.example.user.myapplication;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import layout.Member;
import layout.Membercentre;
import layout.Over;
import layout.SecondFragment;
import layout.Sell;
import layout.Sum;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener ,Over.OnFragmentInteractionListener,SecondFragment.OnFragmentInteractionListener, Sum.OnFragmentInteractionListener,Membercentre.OnFragmentInteractionListener,Sell.OnFragmentInteractionListener{

    private MemberAdapter mAdapter;
    Button btn1,btn2,btn3,btn4;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        RecyclerView myrecyclerView = (RecyclerView) findViewById(R.id.rec);
        myrecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        2, StaggeredGridLayoutManager.VERTICAL));
        List<Member> myDataset = new ArrayList<>();
        myDataset.add(new Member(30, R.drawable.oreo, "OREO餅乾"));
        myDataset.add(new Member(35, R.drawable.cookie, "樂事洋芋片"));
        myDataset.add(new Member(100,R.drawable.ice,"Haagen-Dazs"));
        myDataset.add(new Member(1000, R.drawable.key, "藍牙背光鍵盤"));
        myDataset.add(new Member(3000, R.drawable.eye, "降噪耳機"));
        myDataset.add(new Member(200,R.drawable.ni,"小米Wifi"));
        myDataset.add(new Member(2000, R.drawable.shoe, "Adidas運動鞋"));
        myDataset.add(new Member(300, R.drawable.water, "Adidas運動水壺"));
        myDataset.add(new Member(500,R.drawable.cloth,"Nike運動衣服"));
        mAdapter = new MemberAdapter(myDataset);
        myrecyclerView.setAdapter(mAdapter);
        btn1 = (Button)findViewById(R.id.button1);
        btn2 = (Button)findViewById(R.id.button2);
        btn3 = (Button)findViewById(R.id.button3);
        btn4 = (Button)findViewById(R.id.button4);
        btn2.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, FinanceActivity.class));
            }

        });
        btn3.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Sell sell=Sell.newInstance("param1","param2");
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction().replace(
                        R.id.content_main,
                        sell,
                        sell.getTag()
                ).commit();
            }

        });
        btn4.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View v) {
                Membercentre mem=Membercentre.newInstance("param1","param2");
                FragmentManager manager=getSupportFragmentManager();
                manager.beginTransaction().replace(
                        R.id.content_main,
                        mem,
                        mem.getTag()
                ).commit();
            }

        });
    }

    private class MemberAdapter extends
            RecyclerView.Adapter<MemberAdapter.ViewHolder> {

        private List<Member> Dataset;
        public MemberAdapter(List<Member> myDataset) {
            this.Dataset = myDataset;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImage;
            TextView tvName;

            public ViewHolder(View itemView) {
                super(itemView);
                ivImage = (ImageView) itemView.findViewById(R.id.imageView1);

                tvName = (TextView) itemView.findViewById(R.id.textView1);
            }
        }

        @Override
        public int getItemCount() {
            return Dataset.size();
        }

        @Override
        public MemberAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(
                            R.layout.main_item, viewGroup, false);
            return new MemberAdapter.ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(MemberAdapter.ViewHolder viewHolder, final int position) {
            final Member member =Dataset.get(position);
            viewHolder.ivImage.setImageResource(member.getImage());

            viewHolder.tvName.setText(member.getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),Dataset.get(position).getName()+" $"+Dataset.get(position).getId(),Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        MenuInflater inflater =getMenuInflater();
        inflater.inflate(R.menu.options_menu, menu);
        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        SearchView searchView = (SearchView) menu.findItem(R.id.my_search).getActionView();
        searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));

        searchView.setSubmitButtonEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
            Over blankFragment=Over.newInstance("param1","param2");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_main,
                    blankFragment,
                    blankFragment.getTag()
            ).commit();

        } else if (id == R.id.nav_gallery) {
            SecondFragment secondFragment=SecondFragment.newInstance("some1","some2");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_main,
                    secondFragment,
                    secondFragment.getTag()
            ).commit();
        }
        else if(id == R.id.nav_slideshow) {

            Sum ssn=Sum.newInstance("summ1","summ2");
            FragmentManager manager=getSupportFragmentManager();
            manager.beginTransaction().replace(
                    R.id.content_main,
                    ssn,
                    ssn.getTag()
            ).commit();
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public void onFragmentInteraction(String Tag, String number) {

    }


}
