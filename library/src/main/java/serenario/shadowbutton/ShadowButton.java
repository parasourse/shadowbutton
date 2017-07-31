package serenario.shadowbutton;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Color;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Ahmed on 24/01/2017.
 */

public class ShadowButton extends FrameLayout {

    public static final int DEFAULT_TEXT_COLOR = Color.parseColor("#FFFFFF");
    private ImageView mBackgroundShadow;
    private ImageView mBackground;

    private TextView mText;


    public ShadowButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        setupCornerButton(context, attrs);
        setupAttributes(context, attrs);
    }

    public ShadowButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setupCornerButton(context, attrs);
        setupAttributes(context, attrs);
    }

    private void setupAttributes(Context context, AttributeSet attrs) {
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.ShadowButton);

        setShadowResource(ta.getResourceId(R.styleable.ShadowButton_shadow_resource, -1));
        setBackgroundResource(ta.getResourceId(R.styleable.ShadowButton_background_resource, -1));

        setTextColor(ta.getColor(R.styleable.ShadowButton_text_color, DEFAULT_TEXT_COLOR));
        setTextPadding((int) ta.getDimension(R.styleable.ShadowButton_text_padding, 0));

        setShadowDistance((int) ta.getDimension(R.styleable.ShadowButton_shadow_distance, 0));
        setShadowEnlargement((int) ta.getDimension(R.styleable.ShadowButton_shadow_enlargement, 0));

        setEnabled(ta.getBoolean(R.styleable.ShadowButton_is_enabled, true));

        setAllCaps(ta.getBoolean(R.styleable.ShadowButton_text_all_caps, false));

        ta.recycle();
    }

    public void setAllCaps(boolean allCaps){
        mText.setAllCaps(allCaps);
    }

    private void setTextPadding(int dimension) {
        mText.setPadding(mText.getPaddingLeft() + dimension, mText.getPaddingTop() + dimension / 2, mText.getPaddingRight() + dimension, mText.getPaddingBottom() + dimension / 2);
    }

    private void setShadowEnlargement(int dimension) {
        if (dimension < 0) {
            mBackground.setPadding(mBackground.getPaddingLeft() - dimension
                    , mBackground.getPaddingTop() - dimension
                    , mBackground.getPaddingRight() - dimension
                    , mBackground.getPaddingBottom() - dimension);
        } else {
            mBackgroundShadow.setPadding(mBackgroundShadow.getPaddingLeft() + dimension
                    , mBackgroundShadow.getPaddingTop() + dimension
                    , mBackgroundShadow.getPaddingRight() + dimension
                    , mBackgroundShadow.getPaddingBottom() + dimension);
        }
    }

    private void setShadowDistance(int dimension) {
        mBackground.setPadding(mBackground.getPaddingLeft()
                , mBackground.getPaddingTop()
                , mBackground.getPaddingRight()
                , mBackground.getPaddingBottom() + dimension);

        mBackgroundShadow.setPadding(mBackgroundShadow.getPaddingLeft()
                , mBackgroundShadow.getPaddingTop() + dimension
                , mBackgroundShadow.getPaddingRight()
                , mBackgroundShadow.getPaddingBottom());

        mText.setPadding(mText.getPaddingLeft(), mText.getPaddingTop(), mText.getPaddingRight(), mText.getPaddingBottom() + dimension);
    }

    public void setShadowResource(int resource) {
        if (resource != -1) {
            mBackgroundShadow.setImageResource(resource);
        }
    }

    public void setBackgroundResource(int resource) {
        if (resource != -1) {
            mBackground.setImageResource(resource);
        }
    }

    public void setText(String text) {
        if (text != null) {
            mText.setText(text);
        }
    }

    public void setText(int text) {
        mText.setText(text);
    }


    private void setupCornerButton(Context context, AttributeSet attrs) {
        mBackgroundShadow = new ImageView(context);
        mBackgroundShadow.setScaleType(ImageView.ScaleType.FIT_XY);

        mBackground = new ImageView(context);
        mBackground.setScaleType(ImageView.ScaleType.FIT_XY);

        mText = new TextView(context, attrs);
        mText.setGravity(Gravity.CENTER);

        LayoutParams params = new LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);

        mBackgroundShadow.setLayoutParams(params);
        mBackground.setLayoutParams(params);
        mText.setLayoutParams(params);

        addView(mBackgroundShadow);
        addView(mBackground);
        addView(mText);
    }

    @Override
    public void setEnabled(boolean enabled) {
        mBackground.setEnabled(enabled);
        mBackgroundShadow.setEnabled(enabled);
        mText.setEnabled(enabled);
        super.setEnabled(enabled);
    }

    public void setTextColor(int color) {
        mText.setTextColor(color);
    }

}
