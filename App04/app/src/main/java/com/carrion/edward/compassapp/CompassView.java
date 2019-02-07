package com.carrion.edward.compassapp;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

public class CompassView extends View {

    public static final String N = "N";
    public static final String S = "S";
    public static final String W = "W";
    public static final String E = "E";
    private Paint paint;
    private float position = 0;

    public CompassView(Context context) {
        this(context, null);
    }

    public CompassView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CompassView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(4);
        paint.setStyle(Paint.Style.STROKE);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int xPoint = getMeasuredWidth() / 2;
        int yPoint = getMeasuredHeight() / 2;
        float radius = (float) (Math.max(xPoint, yPoint) * 0.6);

        getCircleStroke(canvas, xPoint, yPoint, radius);

        drawCoordinates(canvas, xPoint, yPoint, radius);

        drawIndicator(canvas, xPoint, yPoint, radius);
    }

    private void drawIndicator(Canvas canvas, int xPoint, int yPoint, float radius) {
        canvas.translate(xPoint, yPoint);

        //Red indicator
        paint.setColor(Color.RED);

        Rect boundsRed = new Rect();
        paint.getTextBounds(N, 0, N.length(), boundsRed);

        Point aRed = new Point(-20, 0);
        Point bRed = new Point(0, (int) -(radius - boundsRed.height()));
        Point cRed = new Point(20, 0);

        Path pathRed = new Path();
        pathRed.setFillType(Path.FillType.EVEN_ODD);
        pathRed.lineTo(aRed.x, aRed.y);
        pathRed.lineTo(bRed.x, bRed.y);
        pathRed.lineTo(cRed.x, cRed.y);
        pathRed.close();

        canvas.drawPath(pathRed, paint);

        //Blue indicator
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.FILL);

        Rect boundsBlue = new Rect();
        paint.getTextBounds(S, 0, S.length(), boundsBlue);

        Point aBlue = new Point(-20, 0);
        Point bBlue = new Point(0, (int) (radius - boundsBlue.height()));
        Point cBlue = new Point(20, 0);

        Path path = new Path();
        path.setFillType(Path.FillType.EVEN_ODD);
        path.lineTo(aBlue.x, aBlue.y);
        path.lineTo(bBlue.x, bBlue.y);
        path.lineTo(cBlue.x, cBlue.y);
        path.close();

        canvas.drawPath(path, paint);
    }

    private void getCircleStroke(Canvas canvas, int xPoint, int yPoint, float radius) {
        paint.setColor(Color.BLACK);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawCircle(xPoint, yPoint, radius, paint);
    }

    private void drawCoordinates(Canvas canvas, int xPoint, int yPoint, float radius) {
        paint.setStyle(Paint.Style.FILL);

        paint.setTextSize(70);

        paint.setColor(Color.RED);
        float measureTextWidthN = paint.measureText(N);
        canvas.drawText(N, xPoint - measureTextWidthN / 2, (yPoint - radius) + measureTextWidthN, paint);

        paint.setColor(Color.BLACK);

        float measureTextWidthS = paint.measureText(S);
        canvas.drawText(S, xPoint - measureTextWidthS / 2, (yPoint + radius), paint);

        float measureTextWidthW = paint.measureText(W);
        canvas.drawText(W, xPoint - radius, yPoint + measureTextWidthW / 2, paint);

        float measureTextWidthE = paint.measureText(E);
        canvas.drawText(E, (xPoint + radius) - measureTextWidthE, yPoint + measureTextWidthE / 2, paint);
    }

    public void updateData(float position) {
        this.position = position;
        invalidate();
    }

}