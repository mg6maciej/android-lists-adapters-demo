package pl.mg6.programistamag.linad;

import java.util.ArrayList;
import java.util.List;

import pl.mg6.programistamag.linad.model.Composite;
import pl.mg6.programistamag.linad.model.Prime;
import pl.mg6.programistamag.linad.utils.PrimesCalc;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

public class PrimesExampleUglyImplActivity extends Activity {

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

		private static final int TYPE_SEPARATOR = 0;
		private static final int TYPE_PRIME = 1;
		private static final int TYPE_COMPOSITE = 2;
		private static final int TYPE_COUNT = 3;

		private LayoutInflater inflater;

		private List<Object> data;

		public PrimesAdapter(Context context, List<Object> numbers) {
			inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			data = new ArrayList<Object>(numbers);
			int lastSeparatorIndex = (data.size() - 1) - (data.size() - 1) % 8;
			for (int i = lastSeparatorIndex; i > 0; i -= 8) {
				data.add(i, null);
			}
		}

		@Override
		public int getCount() {
			return data.size();
		}

		@Override
		public Object getItem(int position) {
			return data.get(position);
		}

		@Override
		public long getItemId(int position) {
			int type = getItemViewType(position);
			if (type == TYPE_SEPARATOR) {
				return 0;
			} else if (type == TYPE_PRIME) {
				Prime prime = (Prime) getItem(position);
				return prime.value;
			} else {
				Composite composite = (Composite) getItem(position);
				return composite.value;
			}
		}

		@Override
		public boolean areAllItemsEnabled() {
			return false;
		}

		@Override
		public boolean isEnabled(int position) {
			int type = getItemViewType(position);
			if (type == TYPE_SEPARATOR) {
				return false;
			} else if (type == TYPE_PRIME) {
				return true;
			} else {
				Composite composite = (Composite) getItem(position);
				return composite.value % 5 == 0;
			}
		}

		@Override
		public int getViewTypeCount() {
			return TYPE_COUNT;
		}

		@Override
		public int getItemViewType(int position) {
			Object item = getItem(position);
			if (item == null) {
				return TYPE_SEPARATOR;
			} else if (item instanceof Prime) {
				return TYPE_PRIME;
			} else if (item instanceof Composite) {
				return TYPE_COMPOSITE;
			}
			throw new RuntimeException();
		}

		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			int type = getItemViewType(position);
			if (type == TYPE_SEPARATOR) {
				return getSeparatorView(convertView, parent);
			} else if (type == TYPE_PRIME) {
				return getPrimeView(position, convertView, parent);
			} else {
				return getCompositeView(position, convertView, parent);
			}
		}

		private View getSeparatorView(View convertView, ViewGroup parent) {
			if (convertView == null) {
				convertView = inflater.inflate(R.layout.primes_separator_item, parent, false);
			}
			return convertView;
		}

		private View getPrimeView(int position, View convertView, ViewGroup parent) {
			Prime prime = (Prime) getItem(position);
			PrimesHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
				holder = new PrimesHolder();
				holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
				convertView.setTag(holder);
			} else {
				holder = (PrimesHolder) convertView.getTag();
			}
			holder.textView.setText(prime.value + " is prime!");
			return convertView;
		}

		private static class PrimesHolder {
			private TextView textView;
		}

		private View getCompositeView(int position, View convertView, ViewGroup parent) {
			Composite composite = (Composite) getItem(position);
			CompositesHolder holder;
			if (convertView == null) {
				convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
				holder = new CompositesHolder();
				holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
				holder.textView2 = (TextView) convertView.findViewById(android.R.id.text2);
				convertView.setTag(holder);
			} else {
				holder = (CompositesHolder) convertView.getTag();
			}
			holder.textView1.setText(composite.value + " is not prime. Divisors:");
			holder.textView2.setText(TextUtils.join(", ", composite.divisors));
			return convertView;
		}

		private static class CompositesHolder {
			private TextView textView1;
			private TextView textView2;
		}
	}
}
