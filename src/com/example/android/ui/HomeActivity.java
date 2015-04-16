package com.example.android.ui;

import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;
import butterknife.InjectView;

import com.example.android.R;
import com.example.android.base.BaseActivity;
import com.example.android.presenter.TestPresenter;
import com.example.android.ui.custom.HeaderLayout;
import com.example.android.view.IUserView;

/**
 * 程序首页
 * 
 * @author user
 * 
 */
public class HomeActivity extends BaseActivity implements IUserView {

	private HeaderLayout mTitleBar;

	@InjectView(R.id.tv_content)
	TextView tvContent;

	private TestPresenter mTestPresenter;

	@Override
	public void initContentView() {
		setContentView(R.layout.activity_main);
	}

	@Override
	public void initView() {
		initTitleBar();
	}

	/**
	 * 初始化自定义的TitleBar
	 */
	private void initTitleBar() {
		mTitleBar = getTitleBar();
		mTitleBar.setTitleBar("MVP测试页面",
				R.drawable.abc_ab_bottom_solid_light_holo,
				R.drawable.abc_ab_bottom_solid_dark_holo);
		mTitleBar.setLeftListener(new OnClickListener() {

			@Override
			public void onClick(View arg0) {
				// showToast("点击了左边");
				// MVP模式开始
				mTestPresenter.test();
			}
		});
		mTitleBar.setRightListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				showToast("点击了右边");
			}
		});
	}

	@Override
	public void initPresenter() {
		mTestPresenter = new TestPresenter(this);
	}

	@Override
	public void testSuccess(String result) {
		// view更新
		tvContent.setText(result);
	}

	@Override
	public void testFailure() {
		// view更新
		showToast("测试失败");
	}
}
