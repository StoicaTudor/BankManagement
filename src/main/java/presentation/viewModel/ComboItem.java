package presentation.viewModel;

import lombok.Getter;

@Getter
public class ComboItem {

    private int key;
    private String value;

    public ComboItem(int key, String value) {
        this.key = key;
        this.value = value;
    }
}
