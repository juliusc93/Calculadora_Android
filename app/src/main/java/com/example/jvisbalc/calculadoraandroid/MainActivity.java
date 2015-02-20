package com.example.jvisbalc.calculadoraandroid;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity {

    String op1=null, op2=null, op=null;
    double total= Double.MAX_VALUE;
    boolean negative = false;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TextView screen = (TextView) findViewById(R.id.Screen);
        screen.setText("0");
    }
    public void Operation(View v) {
        TextView Result = (TextView) findViewById(R.id.Screen);
        Button bot=(Button) findViewById(v.getId());
        if (op == null)
        {   
            if(Result.getText().equals("Infinity")) Result.setText("0");
            op1=Result.getText().toString();
            op = bot.getText().toString();
            Result.setText(Result.getText().toString() + op);
        }
        else
        {
            char last = Result.getText().charAt(Result.getText().length() - 1);
            if(!IsOperator(last)){
                op2 = Result.getText().toString().substring(Result.getText().toString().indexOf(op)+1);
                double num1, num2;
                num1 = Double.parseDouble(op1);
                num2 = Double.parseDouble(op2);
                switch (op)
                {
                    case "+":
                        total = num1 + num2;
                        break;
                    case "*":
                        total = num1 * num2;
                        break;
                    case "/":
                        total = num1 / num2;
                        break;
                    case "-":
                        total = num1 - num2;
                        break;
                }
                Result.setText(String.valueOf(total));
            }
            //else delete the operator
            else{
                Result.setText(Result.getText().toString().substring(0, Result.getText().length() - 1));
            }
            if(Result.getText().equals("Infinity")) op1 = null;
            op2 = op = null;
            Operation(v);
        }
    }
    public void Delete_All(View V){
        TextView screen = (TextView) findViewById(R.id.Screen);
        screen.setText("0");
        op1 = op2 = op = null;
        total = Double.MAX_VALUE;
    }

    public void Compute(View v)
    {   TextView Result = (TextView) findViewById(R.id.Screen);
        if(op != null){

            double num1,num2;
            op2 = Result.getText().toString().substring(Result.getText().toString().indexOf(op)+1);
            try {
                num1 = Double.parseDouble(op1);
                num2 = Double.parseDouble(op2);
                switch (op) {
                    case "+":
                        total = num1 + num2;
                        break;
                    case "*":
                        total = num1 * num2;
                        break;
                    case "/":
                        total = num1 / num2;
                        break;
                    case "-":
                        total = num1 - num2;
                        break;
                }
                Result.setText("" + total);
                if (Result.getText().toString().equals("Infinity")) op1 = null;
                op2 = null;
                op = null;
            }catch(Exception e){
                
            }
        }
    }
    public void Write(View v)
    {
        Button Bt;
        TextView screen = (TextView) findViewById(R.id.Screen);
        if (screen.getText().toString().equalsIgnoreCase("0")){

            screen.setText(v.getTag().toString());
        }else{
            screen.setText(screen.getText() + v.getTag().toString());
        }
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private boolean IsOperator(char s)
    {
        if (s == '+' || s == '-' || s == '*' || s == '/') return true;
        else return false;
    }
}
