package com.example.testmobileqti;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;

public class CircleAvatarUser extends View {

    private Bitmap bitmap;

    public CircleAvatarUser(Context context) {
        super(context);
        init();
    }

    public CircleAvatarUser(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        // Mendapatkan gambar yang ingin ditampilkan
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.images_adam);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int centerX = getWidth() / 2;
        int centerY = getHeight() / 2;
        int radius = Math.min(centerX, centerY);

        Paint paint = new Paint();
        paint.setAntiAlias(true);

        // Menggunakan BitmapShader untuk menggambar di dalam bentuk lingkaran
        BitmapShader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);

        // Mengukur skala gambar agar sesuai dengan lingkaran
        float scale;
        if (bitmap.getWidth() >= bitmap.getHeight()) {
            scale = (float) radius * 2 / bitmap.getHeight();
        } else {
            scale = (float) radius * 2 / bitmap.getWidth();
        }

        Matrix matrix = new Matrix();
        matrix.setTranslate(-bitmap.getWidth() / 2f, -bitmap.getHeight() / 2f); // Memusatkan gambar ke tengah
        matrix.postScale(scale, scale); // Menyesuaikan skala
        matrix.postTranslate(centerX, centerY); // Menggeser gambar ke posisi yang benar

        shader.setLocalMatrix(matrix);
        paint.setShader(shader);

        // Menggambar lingkaran dengan gambar di dalamnya
        canvas.drawCircle(centerX, centerY, radius, paint);
    }

}
