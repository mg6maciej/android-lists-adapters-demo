package pl.mg6.programistamag.linad.adapt;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import pl.mg6.programistamag.linad.model.Composite;

public class CompositeItemAdapter implements ItemAdapter<Composite> {
	
	public static final int TYPE = 2;
	
	private Composite composite;
	
	public CompositeItemAdapter(Composite composite) {
		this.composite = composite;
	}

	@Override
	public Composite getItem() {
		return composite;
	}

	@Override
	public boolean isEnabled() {
		return composite.value % 5 == 0;
	}

	@Override
	public long getItemId() {
		return composite.value;
	}

	@Override
	public int getItemViewType() {
		return TYPE;
	}

	@Override
	public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
		Holder holder;
		if (convertView == null) {
			convertView = inflater.inflate(android.R.layout.simple_list_item_2, parent, false);
			holder = new Holder();
			holder.textView1 = (TextView) convertView.findViewById(android.R.id.text1);
			holder.textView2 = (TextView) convertView.findViewById(android.R.id.text2);
			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.textView1.setText(composite.value + " is not prime. Divisors:");
		holder.textView2.setText(TextUtils.join(", ", composite.divisors));
		return convertView;
	}

	private static class Holder {
		private TextView textView1;
		private TextView textView2;
	}
}
