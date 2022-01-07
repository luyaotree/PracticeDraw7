package com.hencoder.hencoderpracticedraw7.practice.practice03;

import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw7.R;

public class Practice03OfObjectLayout extends RelativeLayout {
    Practice03OfObjectView view;
    Button animateBt;

    public Practice03OfObjectLayout(Context context) {
        super(context);
    }

    public Practice03OfObjectLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice03OfObjectLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = (Practice03OfObjectView) findViewById(R.id.objectAnimatorView);
        animateBt = (Button) findViewById(R.id.animateBt);

        animateBt.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                ObjectAnimator animator = ObjectAnimator.ofObject(view, "position",
                        new PointFEvaluator(), new PointF(0, 0), new PointF(2, 2));
                animator.setInterpolator(new LinearInterpolator());
                animator.setDuration(1000);
                animator.start();
            }
        });
    }

    private class PointFEvaluator implements TypeEvaluator<PointF> {

        /**
         * 重写 evaluate() 方法，让 PointF 可以作为属性来做动画
         * @param fraction：当前动画完成度，从0，0.01,0.02...一直到1，将近100个
         * @param startValue：ObjectAnimator.ofObject的参数第一个
         * @param endValue：ObjectAnimator.ofObject的参数第二个
         * @return：当动画的进度为 fraction 时，对应的PointF对象
         */
        @Override
        public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
            float x = startValue.x + (endValue.x - startValue.x) * fraction;
            float y = startValue.y + (endValue.y - startValue.y) * fraction;
            Log.e("luy", "fraction:" + fraction + ",start:[" + startValue.x + "," + startValue.y + "]" +
                    ", end:[" + endValue.x + "," + endValue.y + "]" + x + " + y:" + y);
            return new PointF(x, y);
        }
    }
}
