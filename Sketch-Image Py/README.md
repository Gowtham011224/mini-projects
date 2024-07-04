# Image Sketching App

This is a simple image processing application that allows users to select an image and apply various transformations to it, including converting to grayscale, inverting colors, and creating a pencil sketch effect. The application uses OpenCV for image processing and Tkinter for the graphical user interface (GUI).

## Requirements

- Python 3.x
- OpenCV
- Tkinter

## Installation

### Step 1: Install Python

Ensure that Python 3.x is installed on your system. You can download and install it from [python.org](https://www.python.org/).

### Step 2: Install Required Packages

Open a terminal or command prompt and run the following commands to install the required packages:

```bash
# Update package list and install dependencies
sudo apt-get update
sudo apt-get install -y libgtk2.0-dev pkg-config

# Install Python packages
pip install opencv-python opencv-contrib-python tk
```

### Step 3: Verify Installation

To verify that the installation was successful, you can run a simple OpenCV and Tkinter script:

```python
import cv2
import tkinter as tk
from tkinter import filedialog

# Simple OpenCV verification
image = cv2.imread('path/to/your/image.jpg')
cv2.imshow('Image', image)
cv2.waitKey(0)
cv2.destroyAllWindows()

# Simple Tkinter verification
root = tk.Tk()
root.title("Tkinter Test")
root.mainloop()
```

## Usage

1. **Run the Application**:
   Save the code from the file,`sketch.py`, and run it:

2. **Select an Image**:
   - Use the "Select Image" option from the "Options" menu to open a file dialog and select an image file.
   - The application will process the selected image and save the transformed images (original, grayscale, inverted, and pencil sketch) in the current directory.

3. **Exit the Application**:
   - Use the "Exit" option from the "Options" menu to close the application.

## License

This project is licensed under the MIT License. 
---
