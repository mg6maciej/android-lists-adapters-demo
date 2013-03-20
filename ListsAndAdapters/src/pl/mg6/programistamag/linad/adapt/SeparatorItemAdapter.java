package pl.mg6.programistamag.linad.adapt;

import pl.mg6.programistamag.linad.R;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SeparatorItemAdapter implements ItemAdapter<Void> {
	
	public static final int TYPE = 0;

	@Override
	public Void getItem() {
		return null;
	}

	@Override
	public boolean isEnabled() {
		return false;
	}

	@Override
	public long getItemId() {
		return 0;
	}

	@Override
	public int getItemViewType() {
		return TYPE;
	}

	@Override
	public View getView(LayoutInflater inflater, View convertView, ViewGroup parent) {
		if (convertView == null) {
			convertView = inflater.inflate(R.layout.primes_separator_item, parent, false);
		}
		return convertView;
	}
}
