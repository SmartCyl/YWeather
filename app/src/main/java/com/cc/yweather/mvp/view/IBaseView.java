package com.cc.yweather.mvp.view;

import com.cc.yweather.util.ErrorUtils;

/**
 * Created by CC on 2017/7/20.
 */

public interface IBaseView {
    void getInformationFailed(ErrorUtils.ErrorMessage errorMessage);
}
