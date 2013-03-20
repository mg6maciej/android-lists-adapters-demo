package pl.mg6.programistamag.linad.adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.mg6.programistamag.linad.model.Prime;

public class PrimeItemAdapter implements ItemAdapter<Prime> {

	public static final int TYPE = 1;

	private final Prime prime;

	public PrimeItemAdapter(Prime prime) {
		this.prime = prime;
	}

	@Override
	public Prime getItem() {
		return prime;
	}

	@Override
	public boolean isEnabled() {
		return true;
	}

	@Override
	public long getItemId() {
		return prime.value;
	}

	@Override
	public int getItemViewType() {
		return TYPE;
	}

	@Override
	public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
			holder = new Holder();
			holder.textView = (TextView) convertView.findViewById(android.R.id.text1);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.textView.setText(prime.value + " is prime!");
		return convertView;
	}

	private static class Holder {
		private TextView textView;
	}
}
