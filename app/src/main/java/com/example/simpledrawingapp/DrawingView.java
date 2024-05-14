package com.example.simpledrawingapp;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class DrawingView extends SurfaceView implements SurfaceHolder.Callback {

    private Path path;
    private Paint paint;
    private int currentColor;
    private int strokeWidth;
    private ShapeType currentShape;
    private float startX, startY, endX, endY;

    public enum ShapeType {
        LINE,
        RECTANGLE,
        CIRCLE
    }

    public DrawingView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);

        path = new Path();
        currentColor = Color.BLACK;
        strokeWidth = 5;
        currentShape = ShapeType.LINE;

        initPaint();
    }

    private void initPaint() {
        paint = new Paint();
        paint.setColor(currentColor);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(strokeWidth);
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        // Empty implementation
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // Empty implementation
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        // Empty implementation
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = x;
                startY = y;
                path.moveTo(x, y);
                return true;
            case MotionEvent.ACTION_MOVE:
                path.lineTo(x, y);
                break;
            case MotionEvent.ACTION_UP:
                endX = x;
                endY = y;
                drawShape();
                break;
            default:
                return false;
        }

        draw();
        return true;
    }

    private void drawShape() {
        switch (currentShape) {
            case LINE:
                // Draw line from start to end points
                break;
            case RECTANGLE:
                // Draw rectangle using start and end points
                break;
            case CIRCLE:
                // Draw circle using start and end points
                break;
        }
    }

    private void draw() {
        Canvas canvas = getHolder().lockCanvas();
        if (canvas != null) {
            canvas.drawColor(Color.WHITE);
            canvas.drawPath(path, paint);
            getHolder().unlockCanvasAndPost(canvas);
        }
    }

    public void setColor(int color) {
        currentColor = color;
        paint.setColor(currentColor);
    }

    public void setStrokeWidth(int width) {
        strokeWidth = width;
        paint.setStrokeWidth(strokeWidth);
    }

    public void setShapeType(ShapeType shape) {
        currentShape = shape;
    }

    public void clearCanvas() {
        path.reset();
        draw();
    }


    public int getCurrentColor() {
        return currentColor;
    }

    public Bitmap getBitmap() {
        // Create a bitmap of the current drawing content
        Bitmap bitmap = Bitmap.createBitmap(getWidth(), getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);
        draw(canvas); // Draw the content of the view onto the canvas
        return bitmap;
    }

}