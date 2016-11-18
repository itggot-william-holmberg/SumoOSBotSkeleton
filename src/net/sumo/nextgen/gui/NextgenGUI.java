package net.sumo.nextgen.gui;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JScrollPane;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import org.osbot.rs07.api.ui.Skill;

import net.sumo.nextgen.Nextgen;
import net.sumo.nextgen.data.Resources;
import net.sumo.nextgen.enums.skills.Assignment;
import net.sumo.nextgen.task.Task;
import net.sumo.nextgen.task.TaskType;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.JCheckBox;

public class NextgenGUI {

	private JFrame frame;

	JList<String> list;
	private JScrollPane sourceScroll;
	private JList<Assignment> sourceList;
	private JScrollPane destScroll;
	private JList<Task> destList;
	private JButton addButton;
	private JButton btnRemoveTask;
	DefaultListModel<Task> destmodel;
	DefaultListModel<Assignment> sourcemodel;
	JButton btnStart;

	private JCheckBox checkBoxProfileSleep;

	private JComboBox comboBoxAxe;

	/**
	 * Launch the application.
	 * 
	 * 
	 * 
	 * /** Create the application.
	 */
	public NextgenGUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		frame.setSize(600, 500);
		frame.setVisible(true);

		JComboBox comboBoxSkillGoal = new JComboBox();
		comboBoxSkillGoal.setBounds(150, 49, 52, 27);
		frame.getContentPane().add(comboBoxSkillGoal);
		for (int i = 1; i <= 99;) {
			comboBoxSkillGoal.addItem(i);
			i++;
		}

		sourcemodel = new DefaultListModel<Assignment>();
		sourceScroll = new JScrollPane();
		sourceScroll.setBounds(19, 51, 122, 242);
		frame.getContentPane().add(sourceScroll);

		sourceList = new JList<Assignment>(sourcemodel);
		sourceScroll.setViewportView(sourceList);
		destmodel = new DefaultListModel<Task>();
		destScroll = new JScrollPane();
		destScroll.setBounds(303, 51, 117, 247);
		frame.getContentPane().add(destScroll);

		destList = new JList<Task>(destmodel);
		destScroll.setViewportView(destList);

		Arrays.asList(Assignment.values()).forEach(stage -> {
			sourcemodel.addElement(stage);
		});
		btnStart = new JButton("START");
		btnStart.setBounds(227, 377, 153, 72);
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (checkBoxProfileSleep.isSelected()) {
					Resources.useProfileSleeping = true;
				}
				
				if(comboBoxAxe.getSelectedItem() != null){
					Resources.wcAxe = (String) comboBoxAxe.getSelectedItem();
				}

				for (int i = 0; i < Nextgen.taskHandler.size();) {
					Nextgen.taskHandler.remove(Nextgen.taskHandler.get(i));
					i++;
				}
				for (int i = 0; i < destList.getModel().getSize();) {

					Task grabben = destList.getModel().getElementAt(i);

					Nextgen.taskHandler.add(grabben);

					i++;
				}
				Resources.shouldRun = true;
			}

		});
		frame.getContentPane().add(btnStart);

		addButton = new JButton("ADD >>");
		addButton.setBounds(177, 194, 86, 16);
		frame.getContentPane().add(addButton);
		addButton.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				destmodel.addElement(new Task(sourceList.getSelectedValue(),
						Integer.parseInt(comboBoxSkillGoal.getSelectedItem().toString())));
			}

		});

		btnRemoveTask = new JButton("<< REMOVE");
		btnRemoveTask.setBounds(177, 236, 86, 16);
		btnRemoveTask.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (destList.getModel().getSize() > 0 && !destList.isSelectionEmpty()) {
					destmodel.remove(destList.getSelectedIndex());
				}
			}

		});
		frame.getContentPane().add(btnRemoveTask);

		checkBoxProfileSleep = new JCheckBox("Profile sleeping?");
		checkBoxProfileSleep.setBounds(456, 121, 122, 23);
		frame.getContentPane().add(checkBoxProfileSleep);
		
		comboBoxAxe = new JComboBox();
		comboBoxAxe.setBounds(467, 49, 52, 27);
		frame.getContentPane().add(comboBoxAxe);
		
		comboBoxAxe.addItem("Bronze axe");
		comboBoxAxe.addItem("Iron axe");
		comboBoxAxe.addItem("Mithril axe");
		comboBoxAxe.addItem("Adamant axe");
		comboBoxAxe.addItem("Rune axe");
		comboBoxAxe.addItem("Dragon axe");
		

	}

	public class IngredientListCellRenderer extends DefaultListCellRenderer {
		public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected,
				boolean cellHasFocus) {
			super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
			if (value instanceof Task) {
				Task ingredient = (Task) value;
				setText("Task: " + ingredient.getLevelGoal() + ingredient.getTask());
			}
			return this;
		}
	}
}
