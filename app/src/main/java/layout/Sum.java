package layout;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.NotificationCompat;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.user.myapplication.MainActivity;
import com.example.user.myapplication.R;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Sum.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Sum#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Sum extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "summ1";
    private static final String ARG_PARAM2 = "summ2";
    private static Double j=1.0,k=1.0,c;
    // TODO: Rename and change types of parameters
    private String summ1;
    private String summ2;

    private OnFragmentInteractionListener mListener;


    public Sum() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *

     * @return A new instance of fragment Sum.
     */
    // TODO: Rename and change types and number of parameters
    public static Sum newInstance(String summ1, String summ2) {
        Sum fragment = new Sum();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, summ1);
        args.putString(ARG_PARAM2, summ2);
        fragment.setArguments(args);

        return fragment;
    }




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            summ1 = getArguments().getString(ARG_PARAM1);
            summ2 = getArguments().getString(ARG_PARAM2);
        }

    }

    private MemberAdapter mAdapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_sum, container, false);
        RecyclerView myrecyclerView = (RecyclerView) view.findViewById(R.id.recyclerView2);
        myrecyclerView.setLayoutManager(
                new StaggeredGridLayoutManager(
                        1, StaggeredGridLayoutManager.VERTICAL));
        List<Member> myDataset = new ArrayList<>();
        myDataset.add(new Member(2000, R.drawable.shoe, "Adidas運動鞋"));
        myDataset.add(new Member(300, R.drawable.water, "Adidas運動水壺"));
        myDataset.add(new Member(500,R.drawable.cloth,"Nike運動衣服"));
        mAdapter = new MemberAdapter(myDataset);
        myrecyclerView.setAdapter(mAdapter);

        return view;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(String Tag, String number) {
        if (mListener != null) {
            mListener.onFragmentInteraction(Tag, number);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
         void onFragmentInteraction(String tag, String number);
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
