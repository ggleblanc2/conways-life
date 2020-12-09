package com.ggl.game.life.view;

import java.awt.Component;
import java.awt.Container;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.text.NumberFormat;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSlider;
import javax.swing.JTextField;

import com.ggl.game.life.controller.ButtonListener;
import com.ggl.game.life.controller.DelayListener;
import com.ggl.game.life.controller.RestartListener;
import com.ggl.game.life.controller.SaveImageListener;
import com.ggl.game.life.model.GameOfLifeModel;

public class ControlPanel {

	private JButton button;

	private JPanel panel;

	private JSlider slider;

	private JTextField generationField;

	private GameOfLifeFrame frame;

	private GameOfLifeModel model;

	public ControlPanel(GameOfLifeFrame frame,
			GameOfLifeModel model) {
		this.frame = frame;
		this.model = model;
		createPartControl();
	}

	private void createPartControl() {
		panel = new JPanel();

		JPanel innerPanel = new JPanel();
		innerPanel.setLayout(new GridBagLayout());

		// top, left, bottom, right
		Insets topLeftInsets = new Insets(10, 10, 10, 10);
		Insets topInsets = new Insets(10, 0, 10, 10);
		Insets sliderInsets = new Insets(0, 4, 10, 10);

		int gridy = 0;

		JLabel generationLabel = new JLabel("Generation");
		addComponent(innerPanel, generationLabel, 0, gridy,
				1, 1, topLeftInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);


		generationField = new JTextField(8);
		generationField.setEditable(false);
		addComponent(innerPanel, generationField, 1, gridy++,
				1, 1, topInsets, GridBagConstraints.LINE_START,
				GridBagConstraints.HORIZONTAL);

		button = new JButton("Start Simulation");
		button.addActionListener(new ButtonListener(
				frame, model));
		addComponent(innerPanel, button, 0, gridy++,
				2, 1, topLeftInsets, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);

		JButton initButton = new JButton("Initalize Simulation");
		initButton.addActionListener(new RestartListener(
				frame, model));
		addComponent(innerPanel, initButton, 0, gridy++,
				2, 1, topLeftInsets, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);

		JLabel delayLabel = new JLabel("Display time in seconds");
		addComponent(innerPanel, delayLabel, 0, gridy++,
				2, 1, topLeftInsets, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);

		slider = new JSlider(JSlider.HORIZONTAL,
				2, 10, model.getDelay());
		slider.setMajorTickSpacing(1);;
		slider.setPaintTicks(true);
		slider.setPaintLabels(true);
		slider.addChangeListener(new DelayListener(frame, model));
		addComponent(innerPanel, slider, 0, gridy++,
				2, 1, sliderInsets, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);

		JButton saveButton = new JButton("Save As Image");
		saveButton.addActionListener(new SaveImageListener(frame));
		addComponent(innerPanel, saveButton, 0, gridy++,
				2, 1, topLeftInsets, GridBagConstraints.CENTER,
				GridBagConstraints.HORIZONTAL);

		panel.add(innerPanel);

		setGenerationField(model.getGeneration());
	}

	private void addComponent(Container container,
			Component component, int gridx, int gridy,
			int gridwidth, int gridheight,
			Insets insets, int anchor, int fill) {
		GridBagConstraints gbc = new GridBagConstraints(
				gridx, gridy, gridwidth, gridheight,
				1.0, 1.0, anchor, fill,
				insets, 0, 0);
		container.add(component, gbc);
	}

	public JPanel getPanel() {
		return panel;
	}

	public void setGenerationField(int generation) {
		NumberFormat nf = NumberFormat.getInstance();
		generationField.setText(nf.format(generation));
	}

	public void setButtonText(String text) {
		button.setText(text);
	}

	public int getSliderValue() {
		return slider.getValue();
	}

}
