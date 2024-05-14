package com.example.simpledrawingapp;

import android.app.Activity;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;


//import com.flask.colorpicker.ColorPickerDialog;
//import com.flask.colorpicker.OnColorSelectedListener;

public class MainActivity extends Activity {

    private DrawingView drawingView;
    private SeekBar seekBarStrokeWidth;
    private Button btnColor, btnShape, btnClear, btnSave;

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
        btnSave = findViewById(R.id.saveButton);
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

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveDrawing();
            }
        });

        // Other listeners...
    }

    private void saveDrawing() {
        // Get the bitmap from the DrawingView
        Bitmap bitmap = drawingView.getBitmap();

        // Check if the bitmap is not null
        if (bitmap != null) {
            // Save the bitmap to a file
            saveBitmapToFile(bitmap);
        } else {
            // Show a message if the bitmap is null
            Toast.makeText(this, "Failed to save drawing", Toast.LENGTH_SHORT).show();
        }
    }

    private void saveBitmapToFile(Bitmap bitmap) {
        // Create a directory for saving the drawing images
        File directory = new File(Environment.getExternalStoragePublicDirectory(
                Environment.DIRECTORY_PICTURES), "SimpleDrawingApp");

        // Create the directory if it doesn't exist
        if (!directory.exists()) {
            directory.mkdirs();
        }

        // Create a file name for the drawing image
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String fileName = "Drawing_" + timeStamp + ".jpg";

        // Create the file
        File file = new File(directory, fileName);

        try {
            // Create a file output stream
            FileOutputStream fos = new FileOutputStream(file);

            // Compress the bitmap and save it to the file
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);

            // Close the file output stream
            fos.close();

            // Show a message indicating the file path
            Toast.makeText(this, "Drawing saved to: " + file.getAbsolutePath(), Toast.LENGTH_LONG).show();
        } catch (IOException e) {
            // Show an error message if there's an error saving the file
            Toast.makeText(this, "Failed to save drawing", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
    }
}


