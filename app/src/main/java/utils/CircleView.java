package utils;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;

@SuppressLint("AppCompatCustomView")
public class CircleView extends android.support.v7.widget.AppCompatImageView {

    private Bitmap mBitmap;
    private BitmapShader shader;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private Matrix matrix = new Matrix();

    public CircleView(Context context) {
        super(context);
    }

    public CircleView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        Drawable drawable = getDrawable();
        Bitmap bitmap = getBitmap(drawable);
        if (bitmap != null) {
            int width = getWidth();
            int height = getHeight();
            int minSize = Math.min(width, height);
            float rawWidth = minSize;
            float rawHeight = minSize;

            if (shader == null || !bitmap.equals(mBitmap)) {
                mBitmap = bitmap;

                shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP,
                        Shader.TileMode.CLAMP);
            }
            if (shader != null) {
                matrix.setScale(rawWidth / mBitmap.getWidth(), rawHeight / mBitmap.getHeight());
                shader.setLocalMatrix(matrix);
            }
            paint.setShader(shader);
            float radiu = minSize / 2;
            canvas.drawCircle(radiu, radiu, radiu, paint);
        } else {
            super.onDraw(canvas);
        }
    }

    private Bitmap getBitmap(Drawable drawable) {
        if (drawable instanceof BitmapDrawable) {
            return ((BitmapDrawable) drawable).getBitmap();
        } else if (drawable instanceof ColorDrawable) {
            Rect bounds = drawable.getBounds();
            int widht = bounds.width();
            int height = bounds.height();
            Bitmap bitmap = Bitmap.createBitmap(
                    widht, height, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmap);
            int color = ((ColorDrawable) drawable).getColor();
            canvas.drawARGB(
                    Color.alpha(color),
                    Color.red(color),
                    Color.green(color),
                    Color.blue(color));
            return bitmap;
        }
        return null;
    }
}
