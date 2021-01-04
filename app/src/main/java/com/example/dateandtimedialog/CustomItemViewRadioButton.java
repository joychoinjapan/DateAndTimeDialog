package com.example.dateandtimedialog;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.Checkable;
import android.widget.FrameLayout;
import android.widget.RadioButton;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class CustomItemViewRadioButton extends FrameLayout implements Checkable {
    private RadioButton mRadioButton;
    public CustomItemViewRadioButton(@NonNull Context context) {

        super(context);
        initialize();
    }

    public CustomItemViewRadioButton(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initialize();
    }

    public CustomItemViewRadioButton(@NonNull Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize();
    }

    private void initialize(){
        addView(inflate(getContext(),R.layout.option_item_layout,null));
        mRadioButton = (RadioButton)findViewById(R.id.radioButton);
    }



    @Override
    public void setChecked(boolean checked) {
        mRadioButton.setChecked(checked);

    }

    @Override
    public boolean isChecked() {

        return mRadioButton.isChecked();
    }

    @Override
    public void toggle() {

    }
}
