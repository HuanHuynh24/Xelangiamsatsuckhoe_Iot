package com.example.gimstsckho_iot.util;

import com.example.gimstsckho_iot.model.userInformation;

public interface OnGetDataListener<T> {
    void onSuccess(userInformation data);
    void onFailed(Exception e);
}
