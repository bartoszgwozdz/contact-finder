package dev.gwozdz.contactfinder.model.openai;

import java.util.ArrayList;
import java.util.List;

public class ChatResponseOld {

    private List<Choice> choices = new ArrayList<>();


    public List<Choice> getChoices() {
        return choices;
    }

    public void setChoices(List<Choice> choices) {
        this.choices = choices;
    }

    public static class Choice {

        private String text;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }
    }
}
