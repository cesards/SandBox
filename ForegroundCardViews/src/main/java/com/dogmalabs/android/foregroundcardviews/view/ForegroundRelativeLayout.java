package com.dogmalabs.android.foregroundcardviews.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.NinePatchDrawable;
import android.util.AttributeSet;
import android.widget.RelativeLayout;
import com.dogmalabs.android.foregroundcardviews.R;

/**
 * Created by menor on 01/01/14.
 */
public class ForegroundRelativeLayout extends RelativeLayout {

    private Drawable mForegroundSelector;
    private Rect mRectPadding;
    private boolean mUseBackgroundPadding = false;

    public ForegroundRelativeLayout(Context context) {
        super(context);
    }

    public ForegroundRelativeLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ForegroundRelativeLayout(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ForegroundRelativeLayout, defStyle, 0);

        final Drawable d = a.getDrawable(R.styleable.ForegroundRelativeLayout_foreground);
        if (d != null) {
            setForeground(d);
        }

        a.recycle();

        if (this.getBackground() instanceof NinePatchDrawable) {
            final NinePatchDrawable npd = (NinePatchDrawable) this.getBackground();
            if (npd != null) {
                mRectPadding = new Rect();
                if (npd.getPadding(mRectPadding)) {
                    mUseBackgroundPadding = true;
                }
            }
        }
    }

    @Override
    protected void drawableStateChanged() {
        super.drawableStateChanged();

        if (mForegroundSelector != null && mForegroundSelector.isStateful()) {
            mForegroundSelector.setState(getDrawableState());
        }
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);

        if (mForegroundSelector != null) {
            if (mUseBackgroundPadding) {
                mForegroundSelector.setBounds(mRectPadding.left, mRectPadding.top, w - mRectPadding.right, h - mRectPadding.bottom);
            } else {
                mForegroundSelector.setBounds(0, 0, w, h);
            }
        }
    }

    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        if (mForegroundSelector != null) {
            mForegroundSelector.draw(canvas);
        }
    }

    @Override
    protected boolean verifyDrawable(Drawable who) {
        return super.verifyDrawable(who) || (who == mForegroundSelector);
    }

    @Override
    public void jumpDrawablesToCurrentState() {
        super.jumpDrawablesToCurrentState();
        if (mForegroundSelector != null) mForegroundSelector.jumpToCurrentState();
    }

    public void setForeground(Drawable drawable) {
        if (mForegroundSelector != drawable) {
            if (mForegroundSelector != null) {
                mForegroundSelector.setCallback(null);
                unscheduleDrawable(mForegroundSelector);
            }

            mForegroundSelector = drawable;

            if (drawable != null) {
                setWillNotDraw(false);
                drawable.setCallback(this);
                if (drawable.isStateful()) {
                    drawable.setState(getDrawableState());
                }
            } else {
                setWillNotDraw(true);
            }
            requestLayout();
            invalidate();
        }
    }

}
