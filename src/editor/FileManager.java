package editor;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

public class FileManager {
    public static void openFile(TextEditor textEditor, JTextArea textArea) {
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(textEditor) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try {
                Scanner scn = new Scanner(file);
                StringBuilder content = new StringBuilder();
                while(scn.hasNextLine()) {
                    content.append(scn.nextLine()).append("\n");
                }
                scn.close();
                textArea.setText(content.toString());
                textEditor.currentFile = file;
            } catch (Exception e) {
                JOptionPane.showMessageDialog(textEditor ,"Error reading file!");
            }
        }
    }

    public static void saveFile(TextEditor textEditor, JTextArea textArea) {
        try {
            File file;
            if (textEditor.currentFile == null) {
                JFileChooser fileChooser = new JFileChooser();
                if (fileChooser.showSaveDialog(textEditor) == JFileChooser.APPROVE_OPTION)
                    return;

                file = fileChooser.getSelectedFile();
                textEditor.currentFile = file;
            } else {
                file = textEditor.currentFile;
            }

            PrintWriter writer = new PrintWriter(file);
            writer.println(textArea.getText());
            writer.close();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(textEditor, "Error reading file!");
        }
    }

    public static void newFile(TextEditor textEditor, JTextArea textArea) {
        textArea.setText("");
        textEditor.currentFile = null;
    }
}
