package pl.mg6.programistamag.linad;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;

public class FilterExampleActivity extends Activity {

	private ArrayAdapter<CharSequence> adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.filter_example);

		adapter = ArrayAdapter.createFromResource(this, R.array.filter_data, android.R.layout.simple_list_item_1);

		EditText editText = (EditText) findViewById(R.id.filter_edit);
		editText.addTextChangedListener(filterWatcher);

		ListView listView = (ListView) findViewById(R.id.filter_listview);
		listView.setAdapter(adapter);
	}

	private TextWatcher filterWatcher = new TextWatcher() {

		@Override
		public void onTextChanged(CharSequence s, int start, int before, int count) {
			adapter.getFilter().filter(s);
		}

		@Override
		public void beforeTextChanged(CharSequence s, int start, int count, int after) {
		}

		@Override
		public void afterTextChanged(Editable s) {
		}
	};
}
