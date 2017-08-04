package layout;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;


import com.example.user.myapplication.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by billy on 2017/5/15.
 */

public class Sale extends Fragment {

    private MemberAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final View view=inflater.inflate(R.layout.fragment_buy, container, false);

        RecyclerView myrecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView);
        myrecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        1, StaggeredGridLayoutManager.VERTICAL));
        List<Member> myDataset = new ArrayList<>();
       
        mAdapter = new MemberAdapter(myDataset);
        myrecyclerView.setAdapter(mAdapter);

        return view;

    }
    private class MemberAdapter extends
            RecyclerView.Adapter<MemberAdapter.ViewHolder> {

        private List<Member> Dataset;



        public MemberAdapter(List<Member> myDataset) {

            this.Dataset = myDataset;
        }


        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView ivImage;
            TextView  tvName;

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
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            View itemView = LayoutInflater.from(viewGroup.getContext())
                    .inflate(
                            R.layout.item, viewGroup, false);
            return new ViewHolder(itemView);
        }

        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {
            final Member member =Dataset.get(position);
            viewHolder.ivImage.setImageResource(member.getImage());

            viewHolder.tvName.setText(member.getName());
            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(v.getContext(),"",Toast.LENGTH_SHORT).show();
                }
            });
        }

    }
}
