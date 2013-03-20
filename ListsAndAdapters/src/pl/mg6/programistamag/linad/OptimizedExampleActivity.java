package pl.mg6.programistamag.linad;

import java.util.ArrayList;
import java.util.List;

import pl.mg6.programistamag.linad.model.ChatMessage;
import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class OptimizedExampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.optimized_example);

		OptimizedAdapter adapter = new OptimizedAdapter(this);
		Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		for (int i = 0; i < 16; i++) {
			adapter.addMessage(new ChatMessage("author " + i % 4, "message blabla bla " + i, avatar));
		}

		ListView listView = (ListView) findViewById(R.id.optimized_listview);
		listView.setAdapter(adapter);
	}

	private static class OptimizedAdapter extends BaseAdapter {
	
		private LayoutInflater inflater;
	
		private List<ChatMessage> dataList = new ArrayList<ChatMessage>();
	
		public OptimizedAdapter(Context context) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		}
	
		public void addMessage(ChatMessage chatMessage) {
			dataList.add(chatMessage);
			notifyDataSetChanged();
		}

		@Override
		public int getCount() {
			return dataList.size();
		}

		@Override
		public ChatMessage getItem(int position) {
			return dataList.get(position);
		}

		@Override
		public long getItemId(int position) {
			return position;
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			Holder holder;
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.optimized_item, parent, false);
				holder = new Holder();
				holder.top = (TextView) convertView.findViewById(R.id.top);
				holder.bottom = (TextView) convertView.findViewById(R.id.bottom);
				holder.image = (ImageView) convertView.findViewById(R.id.image);
				convertView.setTag(holder);
			} else {
				holder = (Holder) convertView.getTag();
			}
			ChatMessage message = getItem(position);
			holder.top.setText(message.getAuthor());
			holder.bottom.setText(message.getMessage());
			holder.image.setImageBitmap(message.getAvatar());
			return convertView;
		}

		private static class Holder {

			private TextView top;
			private TextView bottom;
			private ImageView image;
		}
	}
}
