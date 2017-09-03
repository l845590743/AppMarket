package com.lzm.appmarket.util;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.content.Context;
import android.graphics.PixelFormat;
import android.graphics.drawable.AnimationDrawable;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.WindowManager;
import android.view.animation.BounceInterpolator;
import android.widget.ImageView;

public class RocketView {
	private static final String TAG = "RocketView";
	
	private Context mContext;
	private WindowManager mWM;
	
	//火箭用的布局参数
	private final WindowManager.LayoutParams mRocketParams = new WindowManager.LayoutParams();
	private final WindowManager.LayoutParams mBottomTipsParams = new WindowManager.LayoutParams();
	private final WindowManager.LayoutParams mSmokeParams = new WindowManager.LayoutParams();
	private ImageView mIvRocket;
	private ImageView mIvBottomTips;
	
	private boolean mIsLaunchReady;
	private int mScreenWidth;
	private AnimationDrawable mTipsAnimation;
	
	public RocketView(Context context){
		mContext = context;
		
		//获取屏幕宽高
		DisplayMetrics metrics = mContext.getResources().getDisplayMetrics();
		mScreenWidth = metrics.widthPixels;
		mScreenHeight = metrics.heightPixels;
		Log.d(TAG, "屏幕宽高(" + mScreenWidth + "," + mScreenHeight + ")");
		
		//初始化WindowManager
		mWM = (WindowManager)mContext.getSystemService(Context.WINDOW_SERVICE);
		
		//1.初始化火箭图片
		initRocket();
		
		//2.初始化底部提示图片信息
		initBottomTips();
		
		//2.初始化烟雾图片
		initSmokeView();
	}
	
	//2.初始化烟雾图片
	private void initSmokeView() {
//		View view = View.inflate(mContext, R.layout.view_smoke, null);
//		//要执行alpha动画的view必须有父节点，否则无效
//		mSmokeView = view.findViewById(R.id.view_smoke);
		
		//初始化布局参数
        final WindowManager.LayoutParams params = mSmokeParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                /*| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE*/
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        //params.windowAnimations = com.android.internal.R.style.Animation_Toast;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;//不需要高优先级，知识做展示效果而已
        //params.setTitle("Toast");
        
        //设置gravity，默认值为Gravity.CENTER
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        
        //交给WindowManager展示
//	    mWM.addView(view, params);
	    
	    //默认为隐藏状态
	    mSmokeView.setVisibility(View.INVISIBLE);
	}

	//1.初始化火箭图片
	private void initRocket() {
		mIvRocket = new ImageView(mContext);
		//mIvRocket.setImageResource(R.drawable.desktop_rocket_launch_1);
		//设置监听事件
		mIvRocket.setOnTouchListener(mTouchListener);
		//火箭动画
		AnimationDrawable anim = new AnimationDrawable();
		//添加每一帧图片
//		Drawable frame1 = mContext.getResources().getDrawable(R.drawable.desktop_rocket_launch_1);
//		Drawable frame2 = mContext.getResources().getDrawable(R.drawable.desktop_rocket_launch_2);
		//addFrame(frame 动画的每一帧, duration 每一帧的持续事件);
//		anim.addFrame(frame1 , 100);
//		anim.addFrame(frame2, 100);
		
		//该方法必须放在addFrame之后以及anim.start()之前
		mIvRocket.setImageDrawable(anim);
		anim.setOneShot(false);//无限重复
		anim.start();
		
		//初始化布局参数
        final WindowManager.LayoutParams params = mRocketParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                /*| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE*/
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        //params.windowAnimations = com.android.internal.R.style.Animation_Toast;
        params.type = WindowManager.LayoutParams.TYPE_PRIORITY_PHONE;//加大优先级
        //params.setTitle("Toast");
        
        //默认gravity为Gravity.CENTER
        //params.gravity = Gravity.CENTER;
        //摆放到左上角，便于控制坐标摆放
        params.gravity = Gravity.LEFT | Gravity.TOP;
        
        //交给WindowManager展示，默认值为x、y坐标都是0， 
	    mWM.addView(mIvRocket, params);
	}

	//2.初始化底部提示图片信息
	private void initBottomTips() {
		mIvBottomTips = new ImageView(mContext);
		//提示动画
		mTipsAnimation = new AnimationDrawable();
		//添加每一帧图片
//		Drawable frame1 = mContext.getResources().getDrawable(R.drawable.desktop_bg_tips_1);
//		Drawable frame2 = mContext.getResources().getDrawable(R.drawable.desktop_bg_tips_2);
//		//addFrame(frame 动画的每一帧, duration 每一帧的持续事件);
//		mTipsAnimation.addFrame(frame1 , 100);
//		mTipsAnimation.addFrame(frame2, 100);
		
		//该方法必须放在addFrame之后以及anim.start()之前
		mIvBottomTips.setImageDrawable(mTipsAnimation);
		mTipsAnimation.setOneShot(false);//无限重复
		mTipsAnimation.start();
		
		//初始化布局参数
        final WindowManager.LayoutParams params = mBottomTipsParams;
        params.height = WindowManager.LayoutParams.WRAP_CONTENT;
        params.width = WindowManager.LayoutParams.WRAP_CONTENT;
        params.flags = WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE
                /*| WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE*/
                | WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON;
        params.format = PixelFormat.TRANSLUCENT;
        //params.windowAnimations = com.android.internal.R.style.Animation_Toast;
        params.type = WindowManager.LayoutParams.TYPE_TOAST;//不需要高优先级，知识做展示效果而已
        //params.setTitle("Toast");
        
        //设置gravity，默认值为Gravity.CENTER
        params.gravity = Gravity.BOTTOM | Gravity.CENTER_HORIZONTAL;
        
        //交给WindowManager展示
	    mWM.addView(mIvBottomTips, params);
	    
	    //默认为隐藏状态
	    mIvBottomTips.setVisibility(View.INVISIBLE);
	}

	public void show(){
		
	}
	
	public void hide(){
		if (mIvRocket.getParent() != null) {
			mWM.removeView(mIvRocket);
		}
	}
	
	private OnTouchListener mTouchListener = new OnTouchListener() {
		private float startX;
		private float startY;
		
		@Override
		public boolean onTouch(View v, MotionEvent event) {
			int action = event.getAction();
			switch (action) {
			case MotionEvent.ACTION_DOWN:
				//按下
				startX = event.getRawX();
				startY = event.getRawY();
				
				//展示底部提示图片
				showBottomTips();
				break;
				
			case MotionEvent.ACTION_MOVE:
				//按下
				float x = event.getRawX();
				float y = event.getRawY();
				//计算滑动偏移量
				float offsetX = x - startX;
				float offsetY = y - startY;
				
				//增量更新位置
				mRocketParams.x += offsetX; 
				mRocketParams.y += offsetY; 
				//更新布局
				mWM.updateViewLayout(mIvRocket, mRocketParams);
				
				//记录新的起始坐标
				startX = x;
				startY = y;
				
				//判断火箭是否进入准备区域
				mIsLaunchReady = isReady();
				if (mIsLaunchReady) {
					//展示底部准备发射提示信息
					Log.w(TAG, "火箭进入准备区域！");
					setReadyStatusImage(true);
				}else{
					//切换回原有提示动画图片
					setReadyStatusImage(false);
				}
				
				break;
				
			case MotionEvent.ACTION_UP:
				//手指抬起
				
				//隐藏底部提示图片
				hideBottomTips();
				
				//让火箭摆放在发射位置
				if (mIsLaunchReady) {
					launch();
				}
				
				break;

			default:
				break;
			}
			
			return true;//消费掉事件，不继续传递
		}
	};

	private int mScreenHeight;

	private View mSmokeView;

	//展示底部提示图片
	private void showBottomTips() {
		mIvBottomTips.setVisibility(View.VISIBLE);
	}

	//发射火箭
	private void launch() {
		//计算火箭发射位置x坐标：屏幕宽的一半-火箭宽的一半
		int launchX = (int) (mScreenWidth * 0.5f - mIvRocket.getWidth() * 0.5);
		//计算火箭发射位置y坐标：屏幕高-火箭的高
		int launchY = mScreenHeight - mIvRocket.getHeight();
		Log.d(TAG, "火箭发射位置(" + launchX + ", " + launchY + ")");
		
		//1.设置火箭发射位置
		mRocketParams.x = launchX;
		mRocketParams.y = launchY;
		mWM.updateViewLayout(mIvRocket, mRocketParams);
		
		//2.射出移动动画
		//火箭y坐标的值变道0位置
		int initY = mRocketParams.y;
		//注意这里必须是ofInt
		ValueAnimator animator = ValueAnimator.ofInt(initY, 0);
		
		//插值器
		//Interpolator 接口类，ctrl+T查看实现类
		//加速效果
		//animator.setInterpolator(new AccelerateInterpolator());
		//弹簧效果
		animator.setInterpolator(new BounceInterpolator());
		
		animator.setDuration(1000);
		animator.addUpdateListener(new AnimatorUpdateListener() {
			@Override
			public void onAnimationUpdate(ValueAnimator animation) {
				//获取动画更新的值
				mRocketParams.y = (Integer) animation.getAnimatedValue();
				//刷新火箭位置
				mWM.updateViewLayout(mIvRocket, mRocketParams);
			}
		});
		animator.start();
		
		//3.烟雾动画:渐进->淡出
		mSmokeView.setVisibility(View.VISIBLE);
		//AlphaAnimation fadeIn = new AlphaAnimation(0, 1);
		//渐进动画
		ObjectAnimator fadeIn = ObjectAnimator.ofFloat(mSmokeView, "alpha", 0, 1);
		fadeIn.setDuration(700);
		//淡出动画
		ObjectAnimator fadeOut = ObjectAnimator.ofFloat(mSmokeView, "alpha", 1, 0);
		fadeOut.setDuration(700);
		//动画集合
		AnimatorSet set = new AnimatorSet();
		//线性执行动画
		set.playSequentially(fadeIn, fadeOut);
		//开始动画
		set.start();
	}

	//展示准备状态的图片
	private void setReadyStatusImage(boolean isReady) {
		if (isReady) {
			//展示静态提示图片
			mTipsAnimation.stop();//切换图片后，需要手动停止动画，不然可能会状态错乱
//			mIvBottomTips.setImageResource(R.drawable.desktop_bg_tips_3);
		}else{
			//展示提示动画图片
			mIvBottomTips.setImageDrawable(mTipsAnimation);
			mTipsAnimation.start();
		}
	}

	//判断火箭是否进入准备区域
	private boolean isReady() {
		//获取火箭坐标
		//float x = mIvRocket.getX();//获取失败
		//float y = mIvRocket.getY();
		int[] location = new int[2];
		//mIvRocket.getLocationInWindow(location);//获取失败
		mIvRocket.getLocationOnScreen(location);
		float rocketX = location[0];
		float rocketY = location[1];
		Log.d(TAG, "火箭坐标(" + rocketX + ", " + rocketY + ")");
		
		// 获取火箭的y轴中线位置：火箭y坐标+火箭高的一半
		float rocketCenterY = location[1] + mIvRocket.getHeight() * 0.5f;
		Log.d(TAG, "rocketCenterY = " + rocketCenterY);

		// 获取底部提示信息的y坐标
		mIvBottomTips.getLocationOnScreen(location);
		Log.d(TAG, "底部tips坐标(" + location[0] + ", " + location[1] + ")");
		float tipY = location[1];

		// 对比是否y轴方向上进入准备状态: 火箭y轴中心>底部提示图片的y坐标
		if (rocketCenterY <= tipY) {
			//Log.w(TAG, "y轴方向进入准备区域");
			return false;
		}
		
		//获取火箭x轴中心坐标：火箭x轴+火箭宽的一半
		float rocketCenterX = rocketX + mIvRocket.getWidth() * 0.5f;
		//获取底部提示左线x坐标：屏幕宽的一半-提示图片宽的一半
		float tipsXLeft = mScreenWidth * 0.5f - mIvBottomTips.getWidth() * 0.5f;
		//判断
		if (rocketCenterX < tipsXLeft) {
			return false;
		}
		
		//获取底部提示又线x坐标：屏幕宽的一半+提示图片宽的一半
		float tipsXRight = mScreenWidth * 0.5f + mIvBottomTips.getWidth() * 0.5f;
		if (rocketCenterX > tipsXRight) {
			return false;
		}

		return true;
	}

	//隐藏底部提示图片
	private void hideBottomTips() {
		mIvBottomTips.setVisibility(View.INVISIBLE);
	}
}
