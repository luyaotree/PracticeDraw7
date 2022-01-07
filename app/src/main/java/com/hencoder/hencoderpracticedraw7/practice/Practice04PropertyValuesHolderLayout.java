package com.hencoder.hencoderpracticedraw7.practice;

import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import com.hencoder.hencoderpracticedraw7.R;

/**
 * 多个属性合并，可以做动画组合
 */
public class Practice04PropertyValuesHolderLayout extends RelativeLayout {
    View view;
    Button animateBt;

    public Practice04PropertyValuesHolderLayout(Context context) {
        super(context);
    }

    public Practice04PropertyValuesHolderLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice04PropertyValuesHolderLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();

        view = findViewById(R.id.objectAnimatorView);
        animateBt = (Button) findViewById(R.id.animateBt);
        animateBt.setOnClickListener(new OnClickListener() {
            /**
             * 方案一：利用ViewPropertyAnimator：例如 view.animate().scaleX(1).scaleY(1).alpha(1).setDuration(1000).start();
             * 如果用ObjectAnimator.ofFloat 只能一次使用一个属性，不能组合：例如 ObjectAnimator.ofFloat(view, "scaleX", 0, 1);
             */
            @Override
            public void onClick(View v) {
                // 使用 PropertyValuesHolder.ofFloat() 来创建不同属性的动画值方案
                // 第一个： scaleX 从 0 到 1
                // 第二个： scaleY 从 0 到 1
                // 第三个： alpha 从 0 到 1

                // 然后，用 ObjectAnimator.ofPropertyValuesHolder() 把三个属性合并，创建 Animator 然后执行
                PropertyValuesHolder holder1 = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
                PropertyValuesHolder holder2 = PropertyValuesHolder.ofFloat("scaleY", 0, 1);
                PropertyValuesHolder holder3 = PropertyValuesHolder.ofFloat("alpha", 0, 1);
                ObjectAnimator animator = ObjectAnimator.ofPropertyValuesHolder(view, holder1, holder2, holder3);
                animator.start();
            }
        });
    }
}
