package com.bmob.lostfound.base;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.TextView;

import com.bmob.lostfound.R;
import com.bmob.lostfound.i.IPopupItemClick;

/** ³¤°´±à¼­µ¯´°
  * @ClassName: EditPopupWindow
  * @Description: TODO
  * @author smile
  * @date 2014-5-21 ÏÂÎç3:46:26
  */
public class EditPopupWindow extends BasePopupWindow implements OnClickListener {

	private TextView mEdit;
	private TextView mDelete;
	private IPopupItemClick mOnPopupItemClickListner;

	public EditPopupWindow(Context context, int width, int height) {
		super(LayoutInflater.from(context).inflate(
				R.layout.pop_device, null), dpToPx(context,width), dpToPx(context,height));
		setAnimationStyle(R.style.PopupAnimation);
	}
	
	private static int dpToPx(Context context, int dp) {
        return (int) (context.getResources().getDisplayMetrics().density * dp + 0.5f);
    }

	@Override
	public void initViews() {
		mEdit = (TextView) findViewById(R.id.tv_pop_edit);
		mDelete = (TextView) findViewById(R.id.tv_pop_delete);
	}

	@Override
	public void initEvents() {
		mEdit.setOnClickListener(this);
		mDelete.setOnClickListener(this);
	}

	@Override
	public void init() {

	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.tv_pop_edit:
			if (mOnPopupItemClickListner != null) {
				mOnPopupItemClickListner.onEdit(v);
			}
			break;

		case R.id.tv_pop_delete:
			if (mOnPopupItemClickListner != null) {
				mOnPopupItemClickListner.onDelete(v);
			}
			break;
		}
		dismiss();
	}

	public void setOnPopupItemClickListner(IPopupItemClick l) {
		mOnPopupItemClickListner = l;
	}

}
