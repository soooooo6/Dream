package kr.ac.kyonggi.dream.dream;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

@SuppressLint("ValidFragment")
public class Tab1 extends Fragment {
		Context mContext;
        static int RECEIVE_EVENT = 1000;
        int btnCount = 8;
        int i=0;
        String title[] = {"피자", "치킨", "중국집", "한식",
                "분식", "도시락/밥버거", "족발/보쌈","기타", ""};

		public Tab1(Context context) {
			mContext = context;
        }
		
		@Override
		public View onCreateView(LayoutInflater inflater,
				ViewGroup container, Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.tab1, null);

            Button btn[] = new Button[btnCount];
            LinearLayout mainLayout = (LinearLayout) view.findViewById(R.id.listLinearLayer);
            mainLayout.setOrientation(LinearLayout.VERTICAL);

            LinearLayout currentLayout = new LinearLayout(getActivity());
            currentLayout.setOrientation(LinearLayout.HORIZONTAL);

            mainLayout.addView(currentLayout);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            for(i = 0 ; i < btn.length; i++) {

                btn[i] = new Button(getActivity());
                btn[i].setText(title[i]);
                btn[i].setLayoutParams(params);
                btn[i].setWidth(600);
                btn[i].setHeight(600);
                btn[i].setTextSize(11);
                btn[i].setId(i);

                currentLayout.addView(btn[i]);
                if((i+1) % 2 == 0) {
                    currentLayout = new LinearLayout(getActivity());
                    currentLayout.setOrientation(LinearLayout.HORIZONTAL);
                    mainLayout.addView(currentLayout);
                }

                final String title1 = title[i];
                final int category = i;

                //intent
                btn[category].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getActivity(), View_list.class);
                        intent.putExtra("PARAM1", title1);
                        intent.putExtra("TITLE", title1);
                        intent.putExtra("Category", category+1);
                        Log.d("category", String.valueOf(category + 1));
                        startActivityForResult(intent, RECEIVE_EVENT);
                    }
                });
            }
            return view;
		}
}