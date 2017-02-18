package international.rst.com.rstsimplified.Custom;

import android.content.Context;
import android.support.v4.view.PagerTabStrip;
import android.support.v4.view.PagerTitleStrip;
import android.util.AttributeSet;
import android.view.MotionEvent;

/**
 * Created by Ashish on 16-02-2017.
 */

public class CustomPagerTabStrip extends PagerTabStrip{
    private boolean isTabSwitchEnabled;

    public CustomPagerTabStrip(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.isTabSwitchEnabled = true;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        System.out.println("ENable?? "+isTabSwitchEnabled); // it prints out false or true based on what I have set
        if (this.isTabSwitchEnabled) {
            return super.onInterceptTouchEvent(event);
        } else {
            return false;
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (this.isTabSwitchEnabled) {
            return super.onTouchEvent(event);
        }
        return false;
    }

    public void setTabSwitchEnabled(boolean isSwipeEnabled) {
        this.isTabSwitchEnabled = isSwipeEnabled;
    }
}
