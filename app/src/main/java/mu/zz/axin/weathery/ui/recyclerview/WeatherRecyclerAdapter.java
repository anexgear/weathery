package mu.zz.axin.weathery.ui.recyclerview;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mu.zz.axin.weathery.R;
import mu.zz.axin.weathery.Util;
import mu.zz.axin.weathery.model.database.WeatherObjectBox;


public class WeatherRecyclerAdapter extends RecyclerView.Adapter<WeatherViewHolder> {
    private OnItemClickListener listener;
    private List<WeatherObjectBox> mWeatherModelList;

    public WeatherRecyclerAdapter(List<WeatherObjectBox> mWeatherObjectBox, OnItemClickListener listener) {
        this.mWeatherModelList = mWeatherObjectBox;
        this.listener = listener;


    }

    @Override
    public WeatherViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.weather_recycler_card, parent, false);
        return new WeatherViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final WeatherViewHolder holder, int position) {
        holder.bind(mWeatherModelList.get(position), listener);
        holder.mWeatherObjectBox = mWeatherModelList.get(position);
        holder.mDateTextView.setText(Util.getDateFromString(holder.mWeatherObjectBox.getDateTime()));
        holder.mTimeTextView.setText(Util.getTimeFromString(holder.mWeatherObjectBox.getDateTime()));
        Util.setWeatherIcon(holder.mIconImageView,
                holder.mWeatherObjectBox.getIcon());
    }

    @Override
    public int getItemCount() {
        return mWeatherModelList.size();
    }

    public interface OnItemClickListener {
        void onItemClick(WeatherObjectBox weatherObjectBox);
    }
}
