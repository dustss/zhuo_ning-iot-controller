package zhuoning.youthlife.cn.zhuoning.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.widget.RemoteViews;

import zhuoning.youthlife.cn.zhuoning.R;


/**
 * Created by dusts on 2016/3/18.
 * 因为 圆形进度条，播放暂停按钮 都有圆环的结构，所以抽象出一个圆环，作为基础的UI控件
 * 写的时候也尽可能方便以后复用，把能设置的基础属性都设置了
 * 可以设置圆环的半径，环的粗细，内圆的颜色，圆环的颜色，起始角，旋转角这6个基础属性
 * size:
 * CircleRadius
 * StrokeWidth
 * color:
 * CircleColor
 * RingColor
 * angle:
 * StartAngle
 * SweepAngle
 */

@RemoteViews.RemoteView
public class Ring extends View {

    //size
    private float mCircleRadius;
    private float mStrokeWidth;
    //color
    private int mCircleColor;
    private int mRingColor;
    //angle
    private float mStartAngle;
    private float mSweepAngle;

    private Paint mCirclePaint;
    private Paint mRingPaint;

    private float mRingRadius;
    private RectF mRecf;


    // Constructors
    public Ring(Context context) {
        this(context, null);
    }

    public Ring(Context context, AttributeSet attrs) {
        super(context, attrs);
        initAttrs(context, attrs);
        initPaints();
        initVars();
    }

    //auto generated Setters  size, color, angle
    public void setCircleRadius(float circleRadius) {
        mCircleRadius = circleRadius;
    }

    public void setStrokeWidth(float strokeWidth) {
        mStrokeWidth = strokeWidth;
    }

    public void setCircleColor(int circleColor) {
        mCircleColor = circleColor;
    }

    public void setRingColor(int ringColor) {
        mRingColor = ringColor;
    }

    public void setStartAngle(float startAngle) {
        mStartAngle = startAngle;
        postInvalidate();
    }

    public void setSweepAngle(float sweepAngle) {
        mSweepAngle = sweepAngle;
        postInvalidate();
    }

    //get values from xml else use default
    protected void initAttrs(Context context, AttributeSet attrs) {
        TypedArray typedArray = context.getTheme().
                obtainStyledAttributes(attrs, R.styleable.Ring, 0, 0);
        //size   default:
        mCircleRadius = typedArray.getDimension(R.styleable.Ring_circle_radius, 80);
        mStrokeWidth = typedArray.getDimension(R.styleable.Ring_stroke_width, 5);
        //color  default:
        mCircleColor = typedArray.getColor(R.styleable.Ring_circle_color, 0x456123);
        mRingColor = typedArray.getColor(R.styleable.Ring_ring_color, 0xffffff);
        //angle  default:
        mStartAngle = typedArray.getFloat(R.styleable.Ring_start_angle, -90);
        mSweepAngle = typedArray.getFloat(R.styleable.Ring_sweep_angle, 360);
    }

    // init Paint
    protected void initPaints() {
        mCirclePaint = new Paint();
        mCirclePaint.setAntiAlias(true);
        mCirclePaint.setColor(mCircleColor);
        mCirclePaint.setStyle(Paint.Style.FILL);

        mRingPaint = new Paint();
        mRingPaint.setAntiAlias(true);
        mRingPaint.setColor(mRingColor);
        mRingPaint.setStyle(Paint.Style.STROKE);
        mRingPaint.setStrokeWidth(mStrokeWidth);
    }

    protected void initVars() {
        mRingRadius = mCircleRadius + mStrokeWidth / 2;
        mRecf = new RectF();
    }

    //paint on canvas
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth() / 2;
        int y = getHeight() / 2;
        mRecf.left = (x - mRingRadius);
        mRecf.top = (y - mRingRadius);
        mRecf.right = mRingRadius * 2 + (x - mRingRadius);
        mRecf.bottom = mRingRadius * 2 + (y - mRingRadius);

        canvas.drawCircle(x, y, mCircleRadius, mCirclePaint);
        canvas.drawArc(mRecf, mStartAngle, mSweepAngle, false, mRingPaint);
    }
}
