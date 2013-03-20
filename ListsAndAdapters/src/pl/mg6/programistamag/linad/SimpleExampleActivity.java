package pl.mg6.programistamag.linad;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.Spinner;

public class SimpleExampleActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_example);

		Spinner spinner = (Spinner) findViewById(R.id.simple_spinner);
		ListView listView = (ListView) findViewById(R.id.simple_listview);
		GridView gridView = (GridView) findViewById(R.id.simple_gridview);

		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.simple_data, android.R.layout.simple_list_item_1);

		listView.setAdapter(adapter);
		gridView.setAdapter(adapter);

		ArrayAdapter<CharSequence> spinnerAdapter = ArrayAdapter.createFromResource(this, R.array.simple_data_short, android.R.layout.simple_spinner_item);
		spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

		spinner.setAdapter(spinnerAdapter);

	}
}
