package joshuaj004.com.simplemathquiz;

import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {

    public int answer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        question();
    }

    public void question() {
        String[] operators = {"+", "-", "*", "/"};
        Random r = new Random();
        int first = r.nextInt(20);
        int second = r.nextInt(20);
        int opNum = new Random().nextInt(4);
        String op = operators[opNum];
        if (second == 0 && opNum == 3) {
            question();
        } else if (opNum == 3 && (first < second)) {
            question();
        } else {
            answer = calcAnswer(first, second, opNum);
            ((TextView) findViewById(R.id.firstNum)).setText(first + " " + op + " " + second);
            generateButtons();
        }
    }

    public void generateButtons() {
        Random r = new Random();
        int randomUp = r.nextInt(20);
        int randomDown = r.nextInt(20);
        int[] answers = {0, 0, 0, 0};
        answers[0] = answer;
        answers[1] = answer + randomUp;
        answers[2] = answer - randomDown;
        answers[3] = r.nextInt(40);
        shuffle(answers);
        ((TextView) findViewById(R.id.button)).setText("" + answers[0]);
        ((TextView) findViewById(R.id.button2)).setText("" + answers[1]);
        ((TextView) findViewById(R.id.button3)).setText("" + answers[2]);
        ((TextView) findViewById(R.id.button4)).setText("" + answers[3]);
    }

    static void shuffle(int[] array) {
        int n = array.length;
        for (int i = 0; i < array.length; i++) {
            int random = i + (int) (Math.random() * (n - i));
            int randomElement = array[random];
            array[random] = array[i];
            array[i] = randomElement;
        }
    }

    public int calcAnswer(int first, int second, int opNum) {
        if (opNum == 0) {
            return first + second;
        } else if (opNum == 1) {
            return first - second;
        } else if (opNum == 2) {
            return first * second;
        } else {
            return first / second;
        }
    }

    public void submit(View view) {
        int choice = Integer.parseInt("" + ((Button) view).getText());
        if (choice == answer) {
            ((TextView) findViewById(R.id.answer)).setText("Correct!");
        } else {
            ((TextView) findViewById(R.id.answer)).setText("Sorry, the answer was " + answer);
        }
        question();
    }
}
