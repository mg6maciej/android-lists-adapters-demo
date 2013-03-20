package pl.mg6.programistamag.linad.adapt;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public interface ItemAdapter<T> {

	T getItem();
	
	boolean isEnabled();
	
	long getItemId();
	
	int getItemViewType();
	
	View getView(LayoutInflater inflater, View convertView, ViewGroup parent);
}
