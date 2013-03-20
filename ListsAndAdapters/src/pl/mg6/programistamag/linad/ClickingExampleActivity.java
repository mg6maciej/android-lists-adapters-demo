package pl.mg6.programistamag.linad;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class ClickingExampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clicking_example);

		final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.simple_data, android.R.layout.simple_list_item_1);

		ListView listView = (ListView) findViewById(R.id.clicking_listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Toast.makeText(ClickingExampleActivity.this, "Clicked: " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
			}
		});
		listView.setOnItemLongClickListener(new OnItemLongClickListener() {
			@Override
			public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
				if (position % 2 == 0) {
					Toast.makeText(ClickingExampleActivity.this, "LongClicked: " + adapter.getItem(position), Toast.LENGTH_SHORT).show();
					return true;
				}
				return false;
			}
		});
	}
}
