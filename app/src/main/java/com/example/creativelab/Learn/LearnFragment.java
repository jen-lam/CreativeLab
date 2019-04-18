package com.example.creativelab.Learn;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.creativelab.R;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;

public class LearnFragment extends Fragment {
    RecyclerView verticalRVParent;
    VerticalAdapter verticalAdapter;

    String jsonString = "{\"Lesson info\":[\n" +
            "\t{\"Editor\":\"Photoshop\",\"lessons\":[\n" +
            "\t\t{\"lessonId\":\"1\",\"lessonName\":\"PS 1\"},\n" +
            "\t\t{\"lessonId\":\"2\",\"lessonName\":\"PS 2\"},\n" +
            "\t\t{\"lessonId\":\"3\",\"lessonName\":\"PS 3\"},\n" +
            "\t\t{\"lessonId\":\"4\",\"lessonName\":\"PS Test\"}]},\n" +
            "\t{\"Editor\":\"Illustrator\",\"lessons\":[\n" +
            "\t\t{\"lessonId\":\"5\",\"lessonName\":\"IL 1\"},\n" +
            "\t\t{\"lessonId\":\"6\",\"lessonName\":\"IL 2\"},\n" +
            "\t\t{\"lessonId\":\"7\",\"lessonName\":\"IL 3\"},\n" +
            "\t\t{\"lessonId\":\"8\",\"lessonName\":\"IL Test\"}]},\n" +
            "\t{\"Editor\":\"InDesign\",\"lessons\":[\n" +
            "\t\t{\"lessonId\":\"9\",\"lessonName\":\"ID 1\"},\n" +
            "\t\t{\"lessonId\":\"10\",\"lessonName\":\"ID 2\"},\n" +
            "\t\t{\"lessonId\":\"11\",\"lessonName\":\"ID 3\"},\n" +
            "\t\t{\"lessonId\":\"12\",\"lessonName\":\"ID Test\"}]},\n" +
            "\t{\"Editor\":\"Lightroom\",\"lessons\":[\n" +
            "\t\t{\"lessonId\":\"13\",\"lessonName\":\"LR 1\"},\n" +
            "\t\t{\"lessonId\":\"14\",\"lessonName\":\"LR 2\"},\n" +
            "\t\t{\"lessonId\":\"15\",\"lessonName\":\"LR 3\"},\n" +
            "\t\t{\"lessonId\":\"16\",\"lessonName\":\"LR Test\"}]},\n" +
            "\t{\"Editor\":\"Premiere Pro\",\"lessons\":[\n" +
            "\t\t{\"lessonId\":\"17\",\"lessonName\":\"PP 1\"},\n" +
            "\t\t{\"lessonId\":\"18\",\"lessonName\":\"PP 2\"},\n" +
            "\t\t{\"lessonId\":\"19\",\"lessonName\":\"PP 3\"},\n" +
            "\t\t{\"lessonId\":\"20\",\"lessonName\":\"PP Test\"}]},\n" +
            "\t{\"Editor\":\"After Effects\",\"lessons\":[\n" +
            "\t\t{\"lessonId\":\"21\",\"lessonName\":\"AE 1\"},\n" +
            "\t\t{\"lessonId\":\"22\",\"lessonName\":\"AE 2\"},\n" +
            "\t\t{\"lessonId\":\"23\",\"lessonName\":\"AE 3\"},\n" +
            "\t\t{\"lessonId\":\"24\",\"lessonName\":\"AE Test\"}]}\n" +
            "\t\t]};\t";

    ArrayList<LearnLessons> learnLessonsArrayList;
    LearnInformation learnInformation = new LearnInformation();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_learn, container, false);

        try {
            //pasing jason data
            JSONObject jsonObject = new JSONObject(jsonString);
            JSONArray jsonLessonsArray = jsonObject.getJSONArray("Lesson info");
            learnLessonsArrayList = new ArrayList<>();
            for (int i = 0; i <jsonLessonsArray.length(); i++){
                LearnLessons learnLessons = new LearnLessons();
                JSONObject jsonEditorobject = jsonLessonsArray.getJSONObject(i);
                String editor = jsonEditorobject.getString("Editor");
                learnLessons.setEditor(editor);
                JSONArray jsonArraylessons = jsonEditorobject.getJSONArray("lessons");
                ArrayList<Learn> learnArrayList = new ArrayList<>();
                for (int j = 0; j < jsonArraylessons.length(); j++){
                    Learn learn = new Learn();
                    JSONObject eventObj = jsonArraylessons.getJSONObject(j);
                    learn.setLessonId(eventObj.getString("lessonId"));
                    learn.setLessonName(eventObj.getString("lessonName"));
                    learnArrayList.add(learn);
                }
                learnLessons.setLearnArrayList(learnArrayList);
                learnLessonsArrayList.add(learnLessons);
            }
            learnInformation.setLearnLessonsList(learnLessonsArrayList);
            Log.d("message",learnInformation.toString());
        }catch (Exception e){

        }
        //parent recyclerview
        verticalRVParent = rootView.findViewById(R.id.verticalRVParent);
        verticalAdapter = new VerticalAdapter(learnInformation, this.getContext());
        verticalRVParent.setHasFixedSize(true);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this.getContext(), LinearLayoutManager.VERTICAL,false);
        verticalRVParent.setLayoutManager(mLayoutManager);
        verticalRVParent.setAdapter(verticalAdapter);

        return rootView;
    }
}