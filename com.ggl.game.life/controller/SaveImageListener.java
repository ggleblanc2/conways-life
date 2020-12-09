package com.ggl.game.life.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import javax.swing.filechooser.FileFilter;

import com.ggl.game.life.view.GameOfLifeFrame;

public class SaveImageListener implements ActionListener {

	private GameOfLifeFrame frame;
	
	public SaveImageListener(GameOfLifeFrame frame) {
		this.frame = frame;
	}

	@Override
	public void actionPerformed(ActionEvent event) {
		JFileChooser fc = new JFileChooser() {
			private static final long serialVersionUID = 1L;

			@Override
			public void approveSelection() {
				File f = getSelectedFile();
				if (f.exists() && getDialogType() == SAVE_DIALOG) {
					int result = JOptionPane.showConfirmDialog(
							this, f.getName() + 
							" exists, overwrite?", 
							"Existing file",
							JOptionPane.YES_NO_CANCEL_OPTION);
					switch (result) {
					case JOptionPane.YES_OPTION:
						super.approveSelection();
						return;
					case JOptionPane.NO_OPTION:
						return;
					case JOptionPane.CLOSED_OPTION:
						return;
					case JOptionPane.CANCEL_OPTION:
						cancelSelection();
						return;
					}
				}
				super.approveSelection();
			}
		};
		fc.addChoosableFileFilter(new FileTypeFilter(
				"Graphics Interchange Format", "gif"));
		fc.addChoosableFileFilter(new FileTypeFilter(
				"Joint Photographic Experts Group", "jpg"));
		fc.addChoosableFileFilter(new FileTypeFilter(
				"Portable Network Graphics", "png"));
		fc.setAcceptAllFileFilterUsed(false);
		
		int returnVal = fc.showSaveDialog(frame.getFrame());
		if (returnVal == JFileChooser.APPROVE_OPTION) {
			File file = fc.getSelectedFile();
			String extension = getExtension(file);
			if (verifyExtension(extension)) {
				BufferedImage image = frame.getDrawingPanel()
						.getBufferedImage();
				try {
					ImageIO.write(image, extension, file);
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	private String getExtension(File f) {
        String ext = null;
        String s = f.getName();
        int i = s.lastIndexOf('.');

        if (i > 0 &&  i < s.length() - 1) {
            ext = s.substring(i+1).toLowerCase();
        }
        
        return ext;
    }
	
	private boolean verifyExtension(String extension) {
		if (extension != null) {
			if (extension.equals("jpg") || 
					extension.equals("png") || 
					extension.equals("gif")) {
				return true;
			} 
		}
		return false;
	}
	
	public class FileTypeFilter extends FileFilter {
	    private String extension;
	    private String description;
	 
	    public FileTypeFilter(String description, String extension) {
	        this.extension = extension;
	        this.description = description;
	    }
	 
	    public boolean accept(File file) {
	        if (file.isDirectory()) {
	            return true;
	        }
	        return file.getName().endsWith("." + extension);
	    }
	 
	    public String getDescription() {
	        return description + 
	        		String.format(" (*.%s)", extension);
	    }
	}

}
