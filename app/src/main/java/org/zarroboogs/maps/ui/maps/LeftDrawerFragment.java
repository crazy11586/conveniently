package org.zarroboogs.maps.ui.maps;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.amap.api.maps.AMap;
import com.squareup.picasso.Picasso;

import org.zarroboogs.maps.R;

import org.zarroboogs.maps.utils.SettingUtils;
import org.zarroboogs.maps.widget.BounceCircle;
import org.zarroboogs.maps.widget.CircleImageTransformation;
import org.zarroboogs.maps.widget.Util;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link LeftDrawerFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link LeftDrawerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class LeftDrawerFragment extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;


    private Button mSatelliteBtn;
    private Button mSettingBtn;

    private View mLeftTopView;

    private ImageView iv_userHead;

    private TextView NumberView;

    private OnFragmentInteractionListener mListener;

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment LeftDrawerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static LeftDrawerFragment newInstance(String param1, String param2) {
        LeftDrawerFragment fragment = new LeftDrawerFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    public LeftDrawerFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    /**
     * message
     */
    private RelativeLayout root_message;
    private BounceCircle messageCount;
    private ImageView messageIcon;

    /**
     * friend
     */
    private RelativeLayout root_friend;
    private BounceCircle friendCount;
    private ImageView friendIcon;

    /**
     * circle
     */
    private RelativeLayout root_circle;
    private BounceCircle circleCount;
    private ImageView circleIcon;


    private int radius = 30; // 圆形半径

    private boolean init = true;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_left_drawer, container, false);
        iv_userHead = (ImageView) view.findViewById(R.id.iv_userHead);

        Picasso.with(getActivity()).load("http://pic44.nipic.com/20140717/12432466_121957328000_2.jpg").transform(new CircleImageTransformation()).into(iv_userHead);

        AddPoint(view,R.id.root_message,R.id.message_icon ,root_message , messageCount ,messageIcon );
        AddPoint(view,R.id.root_circle,R.id.circle_icon ,root_circle , circleCount ,circleIcon );
        AddPoint(view,R.id.root_friend,R.id.friend_icon ,root_friend , friendCount ,friendIcon );

        return view;
    }

    private void AddPoint(View view , int id_root , int id_icon ,
                          RelativeLayout root,  BounceCircle circle , ImageView icon) {

        root = (RelativeLayout) view.findViewById(id_root);
        icon = (ImageView) view.findViewById(id_icon);

        int[] position = new int[2];
        icon.getLocationOnScreen(position);

        circle = new BounceCircle(getActivity(), radius,700,70);
        circle.setNumber("2");
        circle.setPadding(10,10,10,10);

        circle.setFinishListener(new BounceCircle.FinishListener() {
            @Override
            public void onFinish() {
                Toast.makeText(getActivity(), "message count dismiss", Toast.LENGTH_LONG).show();
            }
        });

        root.addView(circle,0);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
     //   mOfflineBtn = (Button) view.findViewById(R.id.leftDrawerOfflineBtn);

     //   mOfflineBtn.setOnClickListener(this);

        mSatelliteBtn = (Button) view.findViewById(R.id.left_drawer_satellite);
        mSatelliteBtn.setOnClickListener(this);

        mSettingBtn = (Button) view.findViewById(R.id.left_drawer_setting);
        mSettingBtn.setOnClickListener(this);

      //  mCameraBtn = (Button) view.findViewById(R.id.left_drawer_camera);
    //    mCameraBtn.setOnClickListener(this);

    //    mAboutBtn = (Button)view.findViewById(R.id.leftDrawerAbout);
    //    mAboutBtn.setOnClickListener(this);

        mLeftTopView = view.findViewById(R.id.left_top_banner);

        initViewAfterViewCreated();
    }

    private void initViewAfterViewCreated() {

/*
        if (SettingUtils.readCurrentCameraState() == SettingUtils.SWITCH_ON) {
            mCameraBtn.setTextColor(getResources().getColor(R.color.drawer_text_color_blue));
        } else {
            mCameraBtn.setTextColor(getResources().getColor(R.color.drawer_text_color_normal));
        }*/
    }

    @Override
    public void onResume() {
        super.onResume();
      //  mCameraBtn.setVisibility(SettingUtils.isEnableBeijingCamera() ? View.VISIBLE : View.GONE);
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(int id) {
        if (mListener != null) {
            mListener.onFragmentInteraction(id);
        }
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        ViewGroup.LayoutParams layoutParams = mLeftTopView.getLayoutParams();
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            mLeftTopView.setBackgroundResource(R.drawable.left_drawer_bg_land);
            layoutParams.height = (int) getResources().getDimension(R.dimen.left_drawer_top_view_height_land);
        } else if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_PORTRAIT) {
            mLeftTopView.setBackgroundResource(R.drawable.left_drawer_bg);
            layoutParams.height = (int) getResources().getDimension(R.dimen.left_drawer_top_view_height);
        }

        mLeftTopView.setLayoutParams(layoutParams);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        try {
            mListener = (OnFragmentInteractionListener) activity;
        } catch (ClassCastException e) {
            throw new ClassCastException(activity.toString() + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.left_drawer_satellite) {

            if (SettingUtils.readCurrentMapsStyle() == AMap.MAP_TYPE_NORMAL) {

                mSatelliteBtn.setText("使用平面地图");

            } else {

                mSatelliteBtn.setText("使用卫星地图");

            }

        }

        onButtonPressed(id);


    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        public void onFragmentInteraction(int id);
    }

}
