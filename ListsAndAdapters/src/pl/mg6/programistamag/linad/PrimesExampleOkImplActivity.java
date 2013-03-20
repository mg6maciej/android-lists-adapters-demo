package pl.mg6.programistamag.linad;

import java.util.ArrayList;
import java.util.List;

import pl.mg6.programistamag.linad.adapt.ItemAdapter;
import pl.mg6.programistamag.linad.adapt.CompositeItemAdapter;
import pl.mg6.programistamag.linad.adapt.PrimeItemAdapter;
import pl.mg6.programistamag.linad.adapt.SeparatorItemAdapter;
import pl.mg6.programistamag.linad.model.Composite;
import pl.mg6.programistamag.linad.model.Prime;
import pl.mg6.programistamag.linad.utils.PrimesCalc;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;

public class PrimesExampleOkImplActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.primes_example);

		PrimesAdapter adapter = new PrimesAdapter(this, PrimesCalc.generatePrimes(100));

		ListView listView = (ListView) findViewById(R.id.primes_listview);
		listView.setAdapter(adapter);
	}

	/**
	 * Adapter with numbers and separators Prime numbers and numbers divisible
	 * by 5 are clickable. Numbers are separated in groups of size 8.
	 */
	private static class PrimesAdapter extends BaseAdapter {

		private static final int TYPE_COUNT = 5;

		private LayoutInflater inflater;

		private List<ItemAdapter<?>> data;

		public PrimesAdapter(Context context, List<Object> numbers) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			data = new ArrayList<ItemAdapter<?>>();

			for (int i = 0; i < numbers.size(); i++) {
				if (i % 8 == 0 && i != 0) {
					data.add(new SeparatorItemAdapter());
				}
				Object obj = numbers.get(i);
				if (obj instanceof Prime) {
					data.add(new PrimeItemAdapter((Prime) obj));
				} else if (obj instanceof Composite) {
					data.add(new CompositeItemAdapter((Composite) obj));
				} else {
					throw new RuntimeException();
				}
			}
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position).getItem();
		}

		@Override
		public long getItemId(int position) {
			return data.get(position).getItemId();
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEnabled(int position) {
			return data.get(position).isEnabled();
		}

		@Override
		public int getViewTypeCount() {
			return TYPE_COUNT;
		}

		@Override
		public int getItemViewType(int position) {
			return data.get(position).getItemViewType();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			return data.get(position).getView(inflater, convertView, parent);
		}
	}
}
