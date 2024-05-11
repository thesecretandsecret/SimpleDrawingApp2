package com.example.simpledrawingapp;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;

//import com.flask.colorpicker.ColorPickerDialog;
//import com.flask.colorpicker.OnColorSelectedListener;

public class MainActivity extends Activity {

    private DrawingView drawingView;
    private SeekBar seekBarStrokeWidth;
    private Button btnColor, btnShape, btnClear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize views
        drawingView = findViewById(R.id.drawingView);
        btnColor = findViewById(R.id.btnColor);
        btnShape = findViewById(R.id.btnShape);
        btnClear = findViewById(R.id.btnClear);
        seekBarStrokeWidth = findViewById(R.id.seekBarStrokeWidth);

        // Set listeners
        btnColor.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View v) {
                                            // Open color picker dialog
                                            openColorPickerDialog();
                                        }

                                        // Method to open color picker dialog
                                        private void openColorPickerDialog() {
                                            // Show color picker dialog
                                            ColorPickerDialog.show(MainActivity.this, drawingView.getCurrentColor(), new ColorPickerDialog.OnColorSelectedListener() {
                                                @Override
                                                public void onColorSelected(int color) {
                                                    // Handle color selection
                                                    drawingView.setColor(color);
                                                }
                                            });
                                        }
                                    });

        btnShape.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle shape selection
                // For demonstration purposes, let's assume there's a radio group for shape selection
                // You can implement your logic based on the selected shape
            }
        });

        btnClear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Handle clearing the canvas
                drawingView.clearCanvas();
            }
        });

        seekBarStrokeWidth.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                // Handle stroke width adjustment
                drawingView.setStrokeWidth(progress);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // Not used in this example
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // Not used in this example
            }
        });
    }
}
