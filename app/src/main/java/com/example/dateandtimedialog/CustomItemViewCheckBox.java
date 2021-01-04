package com.example.dateandtimedialog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.CheckBox;
import android.widget.Checkable;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomItemViewCheckBox extends FrameLayout implements Checkable {
    private CheckBox checkBox;

    public CustomItemViewCheckBox(@NonNull Context context) {
        super(context);
        initialize();
    }

    public CustomItemViewCheckBox(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomItemViewCheckBox(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    @Override
    public void setChecked(boolean checked) {

        checkBox.setChecked(checked);
    }

    @Override
    public boolean isChecked() {
        return checkBox.isChecked();
    }

    @Override
    public void toggle() {

    }

    private void initialize(){
        addView(inflate(getContext(),R.layout.checkbox_item_layout,null));
        checkBox = (CheckBox)findViewById(R.id.checkBox);
    }

    private void default_uncheck(){
        checkBox.setChecked(false);
    }
}
