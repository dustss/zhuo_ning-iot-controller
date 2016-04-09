package zhuoning.youthlife.cn.zhuoning.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RemoteViews;

import zhuoning.youthlife.cn.zhuoning.R;

/**
 * Created by dusts on 2016/3/18.
 * 写好了ring 播放按钮就很简单了，可以在ring的基础上画图，或者写字
 * 这里就用>>代替 播放，||代替暂停咯~~         愉快地玩耍吧。
 * 回零按钮就  直接用一个非闭合的圆环。。。 中间画个小点点
 */

@RemoteViews.RemoteView
public class TextRing extends Ring {

    //text added attrs
    private String mText;
    private float mTextSize;
    private int mTextColor;
    //paint
    private Paint mTextPaint;
    private float mTextWidth;
    private float mTextHeight;

    public TextRing(Context context) {
        this(context, null);
    }

    public TextRing(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initPaints();
        initVars();
    }

    public void setText(String text) {
        mText = text;
        postInvalidate();                  //refresh views
    }

    public void setTextSize(float textSize) {
        mTextSize = textSize;
    }

    public void setTextColor(int textColor) {
        mTextColor = textColor;
    }

    @Override
    protected void initAttrs(Context context, AttributeSet attrs) {
        super.initAttrs(context, attrs);
        TypedArray typedArray = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.TextRing, 0, 0);
        mText = typedArray.getString(R.styleable.TextRing_text);
        mTextSize = typedArray.getDimension(R.styleable.TextRing_text_size, 12);
        mTextColor = typedArray.getColor(R.styleable.TextRing_text_color, 0xffffff);
    }

    @Override
    protected void initPaints() {
        super.initPaints();
        mTextPaint = new Paint();
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void initVars() {
        super.initVars();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //textsize and color could be reset, so it should be here
        mTextPaint.setColor(mTextColor);
        mTextPaint.setTextSize(mTextSize);
        //calculate size of font
        Paint.FontMetrics fm = mTextPaint.getFontMetrics();
        mTextHeight = (int) Math.ceil(fm.descent - fm.ascent);
        mTextWidth = mTextPaint.measureText(mText, 0, mText.length());

        canvas.drawText(mText, getWidth() / 2 - mTextWidth / 2, getHeight() / 2 + mTextHeight / 4, mTextPaint);
    }
}
