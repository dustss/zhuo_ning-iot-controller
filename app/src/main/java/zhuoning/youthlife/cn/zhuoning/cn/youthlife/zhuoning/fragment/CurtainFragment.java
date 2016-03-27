package zhuoning.youthlife.cn.zhuoning.cn.youthlife.zhuoning.fragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016/3/16.
 */
public class CurtainFragment extends Fragment{



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        return inflater.inflate(R.layout.drawer_right_curtain, container, false);
    }
}
