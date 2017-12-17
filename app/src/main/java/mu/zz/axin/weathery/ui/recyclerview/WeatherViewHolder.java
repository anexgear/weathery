package mu.zz.axin.weathery.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;


public class WeatherViewHolder extends RecyclerView.ViewHolder {
    protected View view;
    TextView mDateTextView, mTimeTextView;
    ImageView mIconImageView;
    WeatherObjectBox mWeatherObjectBox;


    WeatherViewHolder(View itemView) {
        super(itemView);
        view = itemView;
        mTimeTextView = view.findViewById(R.id.upperCardTextView);
        mIconImageView = view.findViewById(R.id.iconCardImageView);
        mDateTextView = view.findViewById(R.id.lowerCardTextView);
    }

    void bind(final WeatherObjectBox item, final WeatherRecyclerAdapter.OnItemClickListener listener) {
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onItemClick(item);
            }
        });
    }

}
