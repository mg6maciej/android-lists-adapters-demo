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
import android.view.View.OnClickListener;
import android.view.View.OnLongClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class ButtonsProblemsExampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.buttons_example);

		ButtonsAdapter adapter = new ButtonsAdapter(this);
		Bitmap avatar = BitmapFactory.decodeResource(getResources(), R.drawable.ic_launcher);
		for (int i = 0; i < 16; i++) {
			adapter.addMessage(new ChatMessage("author " + i % 4, "message " + i, avatar));
		}

		ListView listView = (ListView) findViewById(R.id.buttons_listview);
		listView.setItemsCanFocus(true);
		listView.setAdapter(adapter);

		adapter.setOnElementClickListener(new OnElementClickListener() {

			@Override
			public void onItemClick(int position) {
				Toast.makeText(ButtonsProblemsExampleActivity.this, "Clicked item at " + position, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onReplyClick(int position) {
				Toast.makeText(ButtonsProblemsExampleActivity.this, "Clicked reply at " + position, Toast.LENGTH_SHORT).show();
			}

			@Override
			public void onDeleteLongClick(int position) {
				Toast.makeText(ButtonsProblemsExampleActivity.this, "LongClicked delete at " + position, Toast.LENGTH_SHORT).show();
			}
		});
	}

	private static class ButtonsAdapter extends BaseAdapter {

		private LayoutInflater inflater;

		private OnElementClickListener onElementClickListener;

		private List<ChatMessage> dataList = new ArrayList<ChatMessage>();

		public ButtonsAdapter(Context context) {
			this.inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
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

			// not using convertView for simplicity

			View view = inflater.inflate(R.layout.buttons_item, parent, false);

			TextView top = (TextView) view.findViewById(R.id.top);
			TextView bottom = (TextView) view.findViewById(R.id.bottom);
			ImageView image = (ImageView) view.findViewById(R.id.image);
			View buttonItem = view.findViewById(R.id.button_item);
			View buttonReply = view.findViewById(R.id.button_reply);
			View buttonDelete = view.findViewById(R.id.button_delete);

			buttonItem.setOnClickListener(itemClickListener);
			buttonReply.setOnClickListener(replyClickListener);
			buttonDelete.setOnLongClickListener(deleteClickListener);

			ChatMessage message = getItem(position);

			top.setText(message.getAuthor());
			bottom.setText(message.getMessage());
			image.setImageBitmap(message.getAvatar());

			view.setTag(position);

			return view;
		}

		public void setOnElementClickListener(OnElementClickListener l) {
			this.onElementClickListener = l;
		}
		
		private int findPosition(View view) {
			while (view.getTag() == null) {
				view = (View) view.getParent();
			}
			return (Integer) view.getTag();
		}

		private OnClickListener itemClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = findPosition(v);
				onElementClickListener.onItemClick(position);
			}
		};
		private OnClickListener replyClickListener = new OnClickListener() {
			@Override
			public void onClick(View v) {
				int position = findPosition(v);
				onElementClickListener.onReplyClick(position);
			}
		};
		private OnLongClickListener deleteClickListener = new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				int position = findPosition(v);
				onElementClickListener.onDeleteLongClick(position);
				return true;
			}
		};
	}

	public interface OnElementClickListener {

		void onItemClick(int position);
		
		void onReplyClick(int position);
		
		void onDeleteLongClick(int position);
	}
}
