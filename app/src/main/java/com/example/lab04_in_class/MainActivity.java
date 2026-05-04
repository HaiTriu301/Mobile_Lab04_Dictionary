package com.example.lab04_in_class;

import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private EditText etSearch;
    private Button btnLookup;

    // Panel shown when exact match found
    private LinearLayout layoutResult;
    private TextView tvWord;
    private TextView tvDefinition;

    // Panel shown when no exact match found
    private LinearLayout layoutSuggestions;
    private TextView tvSuggestionsTitle;
    private ListView lvSuggestions;

    // Panel shown when nothing found at all
    private TextView tvNoResult;

    private DictionaryRepository repository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        repository = new DictionaryRepository(this);

        etSearch        = findViewById(R.id.etSearch);
        btnLookup       = findViewById(R.id.btnLookup);
        layoutResult    = findViewById(R.id.layoutResult);
        tvWord          = findViewById(R.id.tvWord);
        tvDefinition    = findViewById(R.id.tvDefinition);
        layoutSuggestions = findViewById(R.id.layoutSuggestions);
        tvSuggestionsTitle = findViewById(R.id.tvSuggestionsTitle);
        lvSuggestions   = findViewById(R.id.lvSuggestions);
        tvNoResult      = findViewById(R.id.tvNoResult);

        btnLookup.setOnClickListener(v -> performLookup());

        // Also trigger lookup when user presses Enter/Search on keyboard
        etSearch.setOnEditorActionListener((v, actionId, event) -> {
            if (actionId == EditorInfo.IME_ACTION_SEARCH ||
                    actionId == EditorInfo.IME_ACTION_DONE) {
                performLookup();
                return true;
            }
            return false;
        });

        // Clicking a suggestion word in the list shows its definition
        lvSuggestions.setOnItemClickListener((parent, view, position, id) -> {
            Word selected = (Word) parent.getItemAtPosition(position);
            etSearch.setText(selected.getWord());
            showExactResult(selected);
        });
    }

    private void performLookup() {
        String query = etSearch.getText().toString().trim();

        if (query.isEmpty()) {
            Toast.makeText(this, "Please enter a word to look up.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Hide all panels first
        layoutResult.setVisibility(View.GONE);
        layoutSuggestions.setVisibility(View.GONE);
        tvNoResult.setVisibility(View.GONE);

        // 1. Try exact match
        Word exactWord = repository.findExactWord(query);
        if (exactWord != null) {
            showExactResult(exactWord);
            return;
        }

        // 2. Fall back to substring search
        List<Word> suggestions = repository.findWordsContaining(query);
        if (!suggestions.isEmpty()) {
            showSuggestions(query, suggestions);
        } else {
            tvNoResult.setText("No results found for \"" + query + "\".");
            tvNoResult.setVisibility(View.VISIBLE);
        }
    }

    private void showExactResult(Word word) {
        layoutSuggestions.setVisibility(View.GONE);
        tvNoResult.setVisibility(View.GONE);

        tvWord.setText(word.getWord());
        tvDefinition.setText(word.getDefinition());
        layoutResult.setVisibility(View.VISIBLE);
    }

    private void showSuggestions(String query, List<Word> words) {
        layoutResult.setVisibility(View.GONE);
        tvNoResult.setVisibility(View.GONE);

        tvSuggestionsTitle.setText(
                "\"" + query + "\" not found. Did you mean one of these?");

        ArrayAdapter<Word> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_list_item_1,
                words
        );
        lvSuggestions.setAdapter(adapter);
        layoutSuggestions.setVisibility(View.VISIBLE);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        repository.close();
    }
}