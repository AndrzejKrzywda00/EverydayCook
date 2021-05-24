package com.example.everydaycook.main;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.everydaycook.DishCreation.DishCreatorActivity;
import com.example.everydaycook.DishList.DishListActivity;
import com.example.everydaycook.History.DishHistoryActivity;
import com.example.everydaycook.R;
import com.example.everydaycook.Settings.SettingsActivity;
import com.example.everydaycook.Statistics.StatisticsActivity;
import com.example.everydaycook.TagCreation.TagCreationActivity;
import com.example.everydaycook.TodaysDish.DishChooserActivity;
import com.example.everydaycook.information.InformationActivity;

import java.util.Objects;

public class MainMenuFragment extends Fragment {

    // these constitute full menu contents
    ListView menuElements;
    String[] titles;
    String[] subtitles;
    int[] images;

    int howManyElements = 8;           // set manually

    public MainMenuFragment() {
        // Required empty public constructor
    }

    public static MainMenuFragment newInstance() {
        return new MainMenuFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        importData(howManyElements);       // loads data to lists
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        try {
            menuElements = getActivity().findViewById(R.id.menu_list);      // loading list into variable
        } catch(NullPointerException e) {
            e.printStackTrace();
        }

        MainMenuAdapter adapter = new MainMenuAdapter(this.getContext(), titles, images, subtitles);
        menuElements.setAdapter(adapter);           // setting adapter to the list
        return inflater.inflate(R.layout.fragment_main_menu, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        addMenuListeners();     // adding all listeners
    }

    private void importTitles(int howManyElements) {
        // preparing string data
        titles = new String[howManyElements];
        titles[0] = getString(R.string.create_dish);
        titles[1] = getString(R.string.propose_dish);
        titles[2] = getString(R.string.dishes_list);
        titles[3] = getString(R.string.dishes_history);
        titles[4] = getString(R.string.settings_app);
        titles[5] = getString(R.string.stats_app);
        titles[6] = getString(R.string.tags_creator);
        titles[7] = getString(R.string.information_app);
    }

    private void importSubtitles(int howManyElements) {
        // preparing string data
        subtitles = new String[howManyElements];
        subtitles[0] = getString(R.string.create_dish_subtitle);
        subtitles[1] = getString(R.string.propose_dish_subtitle);
        subtitles[2] = getString(R.string.dishes_list_subtitle);
        subtitles[3] = getString(R.string.dishes_history_subtitle);
        subtitles[4] = getString(R.string.settings_app_subtitle);
        subtitles[5] = getString(R.string.stats_app_subtitle);
        subtitles[6] = getString(R.string.tag_information);
        subtitles[7] = getString(R.string.information_app_subtitle);
    }

    private void importImages(int howManyElements) {
        // preparing images data
        images = new int[howManyElements];
        images[0] = R.drawable.plus;
        images[1] = R.drawable.dish;
        images[2] = R.drawable.list;
        images[3] = R.drawable.history;
        images[4] = R.drawable.settings;
        images[5] = R.drawable.statistics;
        images[6] = R.drawable.tag;
        images[7] = R.drawable.information;
    }

    private void importData(int howManyElements) {
        importTitles(howManyElements);
        importSubtitles(howManyElements);
        importImages(howManyElements);
    }

    /***
     * Adapter class to be used in filling data from resources into next rows of list view
     */
    class MainMenuAdapter extends ArrayAdapter<String> {

        Context context;
        String[] menuTitles;
        String[] menuSubtitles;
        int[] icons;

        MainMenuAdapter(Context c, String[] titles, int[] icons, String[] subs) {
            super(c, R.layout.main_menu_row, R.id.text1, subs);     // filling ArrayAdapter with data
            //super(c, R.layout.main_menu_row, R.id.text1, subs);
            this.context = c;
            this.icons = icons;
            this.menuTitles = titles;
            this.menuSubtitles = subs;
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            LayoutInflater layoutInflater = (LayoutInflater) requireActivity().getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            // TODO -- fix this with pattern
            @SuppressLint("ViewHolder") View row = layoutInflater.inflate(R.layout.main_menu_row, parent, false);

            ImageView image = row.findViewById(R.id.menu_icon);
            TextView title = row.findViewById(R.id.text1);
            TextView subtitle = row.findViewById(R.id.text2);

            image.setImageResource(icons[position]);    // we choose element by position
            title.setText(menuTitles[position]);
            subtitle.setText(menuSubtitles[position]);

            return row;
        }
    }

    private void addMenuListeners() {

        menuElements.setOnItemClickListener((parent, view, position, id) -> {
            // plus icon above
            if(position == 0) {     // dish creator act
                Intent intent = new Intent(requireActivity().getApplicationContext(), DishCreatorActivity.class);
                startActivity(intent);
            }
            if(position == 1) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), DishChooserActivity.class);
                startActivity(intent);
            }
            if(position == 2) {     // list of all dishes
                Intent intent = new Intent(requireActivity().getApplicationContext(), DishListActivity.class);
                startActivity(intent);
            }
            if(position == 3) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), DishHistoryActivity.class);
                startActivity(intent);
            }
            if(position == 4) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), SettingsActivity.class);
                startActivity(intent);
            }
            if(position == 5) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), StatisticsActivity.class);
                startActivity(intent);
            }
            if(position == 6) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), TagCreationActivity.class);
                startActivity(intent);
            }
            if(position == 7) {
                Intent intent = new Intent(requireActivity().getApplicationContext(), InformationActivity.class);
                startActivity(intent);
            }
        });

    }

}