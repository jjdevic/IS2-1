package presentacion.Profesor;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import negocio.Profesor.TProfesor;
import presentacion.Controller;
import presentacion.GUIMaker;
import presentacion.eventos;

public class GUIAltaProfesor extends JFrame {
	private static final long serialVersionUID = 1L;
	private boolean isInit = false;
	private JButton btnAlta = new JButton("Dar de alta");
	private JButton btnCancelar = new JButton("Cancelar");
	private String[] labels = { "Insertar idSucursal", "Insertar DNI", "Insertar nombre", "Insertar apellidos",
			"Insertar telefono", "Insertar email", "Insertar sueldo" };
	//private JCheckBox actividad = new JCheckBox("�Est� activo?", true);
	private JLabel etiqGeneral;
	private JTextField[] inputs = new JTextField[labels.length];

	public void initGui() {
		if (isInit) {
			this.setVisible(true);
			return;
		}
		isInit = true;
		this.setLayout(new GridLayout(9, 2, 5, 10));
		for (int i = 0; i < labels.length; i++) {
			etiqGeneral = new JLabel(labels[i] + ": ", SwingConstants.CENTER);
			inputs[i] = new JTextField(20);
			add(etiqGeneral);
			etiqGeneral.setLabelFor(inputs[i]);
			add(inputs[i]);
		}

		//add(new JPanel());
		//add(actividad);
		add(btnCancelar);
		add(btnAlta);
		setVisible(true);

		GUIMaker.getInstance().configurateSubWindow(this, 410, 250, "Registrar un alta");

		// Botones
		btnAlta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				for (int i = 0; i < labels.length; i++) {
					if (inputs[i].getText().isEmpty()) {
						JOptionPane.showMessageDialog(null,
								"Los campos no pueden ser vac�os", "ERROR",
								JOptionPane.ERROR_MESSAGE);
						return;
					}
				}

				try {
					TProfesor a = new TProfesor(0, Integer.valueOf(inputs[0].getText()), inputs[1].getText(),
							inputs[2].getText(), inputs[3].getText(), Integer.valueOf(inputs[4].getText()),
							inputs[5].getText(), Integer.valueOf(inputs[6].getText()), true);
					Controller.getInstance().accion(eventos.ALTA_PROFESOR, a);
				} catch (Exception ex) {
					JOptionPane.showMessageDialog(null, "Compruebe formato de los datos. \n (" + ex.toString() + ")",
							"Informaci�n", JOptionPane.WARNING_MESSAGE);
				}
			}
		});
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
	}

}
