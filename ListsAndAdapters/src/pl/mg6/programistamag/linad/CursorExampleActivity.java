package pl.mg6.programistamag.linad;

import android.database.Cursor;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Phone;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.LoaderManager.LoaderCallbacks;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v4.widget.SimpleCursorAdapter;
import android.widget.ListView;

public class CursorExampleActivity extends FragmentActivity {

	private SimpleCursorAdapter adapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.cursor_example);

		String[] from = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER };
		int[] to = new int[] { android.R.id.text1, android.R.id.text2 };
		adapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_2, null, from, to, 0);

		ListView listView = (ListView) findViewById(R.id.cursor_listview);
		listView.setAdapter(adapter);

		getSupportLoaderManager().initLoader(0, null, callback);
	}

	private LoaderCallbacks<Cursor> callback = new LoaderCallbacks<Cursor>() {
		
		public Loader<Cursor> onCreateLoader(int id, Bundle args) {
			String[] projection = new String[] { Phone.DISPLAY_NAME, Phone.NUMBER, Phone._ID };
			String orderBy = Phone.DISPLAY_NAME_ALTERNATIVE + " COLLATE LOCALIZED ASC";
			return new CursorLoader(CursorExampleActivity.this, Phone.CONTENT_URI, projection, null, null, orderBy);
		}

		public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
			adapter.swapCursor(data);
		}

		public void onLoaderReset(Loader<Cursor> loader) {
			adapter.swapCursor(null);
		}
	};
}
