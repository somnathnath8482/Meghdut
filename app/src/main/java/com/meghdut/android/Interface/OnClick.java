package com.meghdut.android.Interface;

import java.util.List;

public interface OnClick {
    void isClicked(boolean is);
    void Clicked(String item);
    void OnSuccess(String code, String message);
    void OnSuccess(List<String> list);
}
