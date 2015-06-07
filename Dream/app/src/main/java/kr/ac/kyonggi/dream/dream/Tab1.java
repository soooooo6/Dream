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
import android.widget.ImageButton;
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

            ImageButton btn[] = new ImageButton[btnCount];
            btn[0] = (ImageButton) view.findViewById(R.id.button01);
            btn[1] = (ImageButton) view.findViewById(R.id.button02);
            btn[2] = (ImageButton) view.findViewById(R.id.button03);
            btn[3] = (ImageButton) view.findViewById(R.id.button04);
            btn[4] = (ImageButton) view.findViewById(R.id.button05);
            btn[5] = (ImageButton) view.findViewById(R.id.button06);
            btn[6] = (ImageButton) view.findViewById(R.id.button07);
            btn[7] = (ImageButton) view.findViewById(R.id.button08);

            for(i = 0 ; i < btn.length; i++) {

//                btn[i] = new ImageButton(getActivity());

                btn[i].setId(i);

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