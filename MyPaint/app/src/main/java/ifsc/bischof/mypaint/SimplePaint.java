package ifsc.bischof.mypaint;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.drawable.ColorDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

enum EStyleType {
    eLine  ,
    eCircle,
    eSquare
}

public class SimplePaint extends View {
    List<Paint> m_aPaint;
    List<Path>  m_aPath ;

    Paint currentPaint;
    Path  currentPath ;

    ColorDrawable currentColor;

    EStyleType currentStyle = EStyleType.eLine;

    float lxAuxIni, lxAuxFin, lyAuxIni, lyAuxFin = 0;

    public SimplePaint(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        m_aPaint = new ArrayList<Paint>();
        m_aPath  = new ArrayList<Path> ();

        currentColor = new ColorDrawable();
        currentColor.setColor(Color.BLACK);

        initLayerDraw();
    }

    public void initLayerDraw() {
        currentPaint = new Paint();
        currentPath  = new Path ();

        currentPaint.setStyle(Paint.Style.STROKE);
        currentPaint.setStrokeWidth(20);
        currentPaint.setColor(currentColor.getColor());
    }

    public void setColor(Color color) {
        currentColor.setColor(color.toArgb());
        currentPaint.setColor(color.toArgb());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int idx = 0; idx < m_aPaint.size(); ++idx)
            canvas.drawPath(m_aPath.get(idx), m_aPaint.get(idx));

        canvas.drawPath(currentPath, currentPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float lx, ly = 0;

        lx = event.getX();
        ly = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                currentPath.moveTo(lx, ly);
                currentPath.lineTo(lx, ly);

                lxAuxIni = lx;
                lyAuxIni = ly;

                break;

            case MotionEvent.ACTION_MOVE:
                lxAuxFin = lx;
                lyAuxFin = ly;

                switch (currentStyle) {
                    case eLine:
                        currentPath.lineTo(lx, ly);
                        break;

                    case eCircle:
                        double dDiameter = Math.sqrt(Math.pow(lxAuxFin - lxAuxIni, 2) + Math.pow(lyAuxFin - lyAuxIni, 2));
                        float radius = (float)(dDiameter / 2);
                        float x = (lxAuxIni + lxAuxFin) / 2;
                        float y = (lyAuxIni + lyAuxFin) / 2;

                        currentPath.reset();
                        currentPath.addCircle(x, y, radius, Path.Direction.CW);

                        break;

                    case eSquare:
                        currentPath.reset();
                        currentPath.addRect(lxAuxIni, lyAuxIni, lxAuxFin, lyAuxFin, Path.Direction.CCW);
                        break;
                }

                break;

            case MotionEvent.ACTION_UP:
                lxAuxFin = lx;
                lyAuxFin = ly;

                switch (currentStyle) {
                    case eLine:
                        currentPath.lineTo(lx, ly);
                        break;

                    case eCircle:
                        double dDiameter = Math.sqrt(Math.pow(lxAuxFin - lxAuxIni, 2) + Math.pow(lyAuxFin - lyAuxIni, 2));
                        float radius = (float)(dDiameter / 2);
                        float x = (lxAuxIni + lxAuxFin) / 2;
                        float y = (lyAuxIni + lyAuxFin) / 2;

                        currentPath.addCircle(x, y, radius, Path.Direction.CW);
                        break;

                    case eSquare:
                        currentPath.addRect(lxAuxIni, lyAuxIni, lxAuxFin, lyAuxFin, Path.Direction.CCW);
                        break;
                }

                m_aPaint.add(currentPaint);
                m_aPath .add(currentPath );

                initLayerDraw();

                break;
        }

        invalidate();
        return true;
    }

    public void backDraw() {
        if (m_aPaint.isEmpty())
            return;

        m_aPaint.remove(m_aPaint.size() - 1);
        m_aPath .remove(m_aPath .size() - 1);

        invalidate();
    }

    public void removeDraw() {
        if (m_aPaint.isEmpty())
            return;

        m_aPaint.clear();
        m_aPath .clear();

        invalidate();
    }

    public void setStyleType(EStyleType style) { this.currentStyle = style; }
}
