package com.example.user.myapplication;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import layout.Member;

/**
 * Created by rick.wu on 2015/8/26.
 */
public class SearchableActivity extends Activity {
    TextView txt;
    Toolbar tool;
    String []str=new String [9];
    RecyclerView myrecyclerView;
    private MemberAdapter mAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_results);
        tool = (Toolbar)findViewById(R.id.toolbar1);
        txt = (TextView)findViewById(R.id.textView2);
        myrecyclerView = (RecyclerView) findViewById(R.id.fin);
        myrecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        1, StaggeredGridLayoutManager.VERTICAL));
        str[0] ="OREO餅乾";
        str[1]="樂事洋芋片";
        str[2]="Haagen-Dazs";
        str[3]="藍牙背光鍵盤";
        str[4]="降噪耳機";
        str[5]="小米Wifi";
        str[6]="Adidas運動鞋";
        str[7]="Adidas運動水壺";
        str[8]="Nike運動衣服";

        handleIntent(getIntent());

    }
    @Override
    protected void onNewIntent(Intent intent) {
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {

        if (Intent.ACTION_SEARCH.equals(intent.getAction())) {
            String query = intent.getStringExtra(SearchManager.QUERY);


            List<Member> myDataset = new ArrayList<>();
            if (str[0].contains(query))
            {myDataset.add(new Member(30, R.drawable.oreo, "OREO餅乾"));}
            else if(str[1].contains(query))
            {myDataset.add(new Member(35, R.drawable.cookie, "樂事洋芋片"));}
            else if(str[2].contains(query))
            {myDataset.add(new Member(100,R.drawable.ice,"Haagen-Dazs"));}
            else if(str[3].contains(query))
            {myDataset.add(new Member(1000, R.drawable.key, "藍牙背光鍵盤"));}
            else if(str[4].contains(query))
            {myDataset.add(new Member(3000, R.drawable.eye, "降噪耳機"));}
            else if(str[5].contains(query))
            {myDataset.add(new Member(200,R.drawable.ni,"小米Wifi"));}
            else if(str[6].contains(query))
            {myDataset.add(new Member(2000, R.drawable.shoe, "Adidas運動鞋"));}
            else if(str[7].contains(query))
            {myDataset.add(new Member(300, R.drawable.water, "Adidas運動水壺"));}
            else if(str[8].contains(query))
            {myDataset.add(new Member(500,R.drawable.cloth,"Nike運動衣服"));}
            else
            {txt.setText("找不到此商品");}

            mAdapter = new MemberAdapter(myDataset);
            myrecyclerView.setAdapter(mAdapter);
            tool.setTitle("「"+query.toString()+"」的搜尋結果");
            //use the query to search your data somehow
        }
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
                ivImage = (ImageView) itemView.findViewById(R.id.imageView);

                tvName = (TextView) itemView.findViewById(R.id.textView);
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
                            R.layout.item, viewGroup, false);
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
}}
