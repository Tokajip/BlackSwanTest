package com.tp.projects.moviedb.util;


import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.tp.projects.moviedb.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NetworkErrorFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NetworkErrorFragment extends Fragment {
  private static final String ERROR_TEXT = "ERROR_TEXT";
  private static final String ERROR_CODE = "ERROR_CODE";

  private String error_text;
  private String error_code;

  private Context ctx;
  private View mainView;


  public NetworkErrorFragment() {
    // Required empty public constructor
  }

  /**
   * Use this factory method to create a new instance of
   * this fragment using the provided parameters.
   *
   * @param errorText Parameter 1.
   * @param errorCode Parameter 2.
   * @return A new instance of fragment NetworkErrorFragment.
   */
  public static NetworkErrorFragment newInstance(String errorCode, String errorText) {
    NetworkErrorFragment fragment = new NetworkErrorFragment();
    Bundle args = new Bundle();
    args.putString(ERROR_TEXT, errorText);
    args.putString(ERROR_CODE, errorCode);
    fragment.setArguments(args);
    return fragment;
  }

  @Override
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    if (getArguments() != null) {
      error_text = getArguments().getString(ERROR_TEXT);
      error_code = getArguments().getString(ERROR_CODE);
    }
    ctx = getActivity();


  }

  @Override
  public View onCreateView(LayoutInflater inflater, ViewGroup container,
                           Bundle savedInstanceState) {

    mainView = inflater.inflate(R.layout.fragment_network_error, container, false);
    ((TextView) mainView.findViewById(R.id.error_message)).setText(error_text);
    ((TextView) mainView.findViewById(R.id.error_code)).setText("went:" + error_code);

    return mainView;
  }

}
