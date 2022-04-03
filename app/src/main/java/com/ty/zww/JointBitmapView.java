package com.ty.zww;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.view.View;

public class JointBitmapView extends View {
    private Bitmap bitmap;

    public JointBitmapView(Context context, Bitmap bit1, Bitmap bit2) {
        super(context);
        bitmap = newBitmap(bit1, bit2);
    }

    public JointBitmapView(Context context) {
        super(context);
    }

    public Bitmap makeRoundCorner(Bitmap bitmap, int px) {
        int width = bitmap.getWidth();
        int height = bitmap.getHeight();
        Bitmap output = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        int color = 0xff424242;
        Paint paint = new Paint();
        Rect rect = new Rect(0, 0, width, height);
        RectF rectF = new RectF(rect);
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, px, px, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }

    /**
     * 拼接图片
     *
     * @param bit1
     * @param bit2
     * @return 返回拼接后的Bitmap
     */
    public Bitmap newBitmap(Bitmap bit1, Bitmap bit2) {

        bit2 = zoomImg(bit2,240,240);

        int width = bit1.getWidth();
        int height = bit1.getHeight();
        //创建一个空的Bitmap(内存区域),宽度等于第一张图片的宽度，高度等于两张图片高度总和
        Bitmap bitmap = Bitmap.createBitmap(width, height, Bitmap.Config.ARGB_8888);
        //将bitmap放置到绘制区域,并将要拼接的图片绘制到指定内存区域
        Canvas canvas = new Canvas(bitmap);
        canvas.drawBitmap(bit1, 0, 0, null);
        canvas.drawBitmap(bit2, bit1.getWidth() / 2 - (bit2.getWidth() / 2), bit1.getHeight() / 3 * 2, null);
        //添加文字   长按识别二维码
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setTextSize(30);
        paint.setStrokeWidth(2);
        paint.setColor(Color.WHITE);
        //在图片上动态的添加文字
        canvas.drawText("长按识别二维码",bit1.getWidth() / 2 - (bit2.getWidth() / 2)+10, bit1.getHeight() / 3 * 2+(bit2.getHeight())+35,paint);
        return bitmap;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap, 0, 0, null);
        bitmap.recycle();
    }

    public Bitmap zoomImg(Bitmap bm, int newWidth, int newHeight) {
        // 获得图片的宽高   
        int width = bm.getWidth();
        int height = bm.getHeight();
        // 计算缩放比例   
        float scaleWidth = ((float) newWidth) / width;
        float scaleHeight = ((float) newHeight) / height;
        // 取得想要缩放的matrix参数   
        Matrix matrix = new Matrix();
        matrix.postScale(scaleWidth, scaleHeight);
        // 得到新的图片   www.2cto.com
        Bitmap newbm = Bitmap.createBitmap(bm, 0, 0, width, height, matrix, true);
        //设置圆角
        newbm = makeRoundCorner(newbm, 250);
        return newbm;
    }

}
