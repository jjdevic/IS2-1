package presentacion.Alumno;

import java.util.List;

import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import negocio.Alumno.TAlumno;
import presentacion.GUIMaker;


public class GUIMostrarTodos extends JFrame{
	private static final long serialVersionUID = 1L;
	private boolean isInit=false;
	private JTable tabla;
	private String[] colNames = {"id", "DNI","nombre","apellidos","telefono","email","amaxofobia","activo"};
	
	
	public String getValueAt(List<TAlumno> lista, int arg0, int arg1) {
		// TODO Auto-generated method stub
		String s = null;
		switch (arg1) {
		case 0:
			s = Integer.toString(lista.get(arg0).getId());
			break;
		case 1:
			s = lista.get(arg0).getDNI();
			break;
		case 2:
			s = lista.get(arg0).getNombre();
			break;
		case 3:
			s = lista.get(arg0).getApellidos();
			break;
		case 4:
			s = Integer.toString(lista.get(arg0).getTelefono());
			break;
		case 5:
			s = lista.get(arg0).getEmail();
			break;
		case 6:
			s = Boolean.toString(lista.get(arg0).getAmaxofobia());
			break;
		case 7:
			s = Boolean.toString(lista.get(arg0).getActivo());
		}
		return s;
	}
	
	public void mostrarAlumnos(List<TAlumno> lista) {
		if(isInit) {
			setVisible(true);
			actualizarTabla(lista);
			return;
		}
		isInit=true;
		GUIMaker.getInstance().configurateSubWindow(this, 1200, 800, "Mostrar todos los alumnos");
		tabla= new JTable();
		actualizarTabla(lista);
		tabla.getTableHeader().setReorderingAllowed(false);
		JScrollPane p= new JScrollPane(tabla);
		this.pack();
		this.add(p);
		this.setSize(720,450);
		this.setVisible(true);
	}
	
	private void actualizarTabla(List<TAlumno> lista) {
		String[][]datos= new String[lista.size()][colNames.length];
		for(int i=0;i<lista.size();++i) 
			for(int j=0;j<colNames.length;++j) 
				datos[i][j]= getValueAt(lista,i,j);
				
		DefaultTableModel tmodel = new DefaultTableModel(datos,colNames) {
			private static final long serialVersionUID = 1L;

			@Override
			public boolean isCellEditable(int row, int col) {
				return false;
			}
		};
		tabla.setModel(tmodel);
	}
}
