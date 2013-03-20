package pl.mg6.programistamag.linad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		final ExampleStartInfo[] data = { new ExampleStartInfo(SimpleExampleActivity.class, "Simple"),
				new ExampleStartInfo(ClickingExampleActivity.class, "Clicking"), new ExampleStartInfo(FilterExampleActivity.class, "Filter"),
				new ExampleStartInfo(CursorExampleActivity.class, "Cursor"), new ExampleStartInfo(OptimizedExampleActivity.class, "Optimized"),
				new ExampleStartInfo(ButtonsProblemsExampleActivity.class, "Buttons problems"),
				new ExampleStartInfo(PrimesExampleUglyImplActivity.class, "Primes - bad impl"),
				new ExampleStartInfo(PrimesExampleOkImplActivity.class, "Primes - ok impl") };

		ArrayAdapter<ExampleStartInfo> adapter = new ArrayAdapter<ExampleStartInfo>(this, android.R.layout.simple_list_item_1, data);

		ListView listView = (ListView) findViewById(R.id.main_listview);
		listView.setAdapter(adapter);
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
				Intent intent = new Intent(MainActivity.this, data[position].activityClass);
				startActivity(intent);
			}
		});
	}

	private static class ExampleStartInfo {

		private final Class<? extends Activity> activityClass;
		private final String displayName;

		public ExampleStartInfo(Class<? extends Activity> activityClass, String displayName) {
			this.activityClass = activityClass;
			this.displayName = displayName;
		}

		@Override
		public String toString() {
			return displayName;
		}
	}
}
