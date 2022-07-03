package GestorCorreo;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

public class GestorCorreo extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JTextArea textAreaRecibidos;
	private JPanel panelCarpetas;
	private JPanel panelMenu;
	private JPanel panelMostrar;
	private JPanel panelAñadir;
	private JPanel panelEliminar;
	private JPanel panelMostrarPrimero;
	private CarpetaCorreo recibidos = new CarpetaCorreo();
	private CarpetaCorreo eliminados = new CarpetaCorreo();
	private JPanel contentPane;
	private JTextField textFieldOrigen;
	private JTextField textFieldDestino;
	private JTextField textFieldAsunto;
	private JTextField textFieldAsuntoBorrar;
	private boolean carpetas;
	private int panel;
	private String origen;
	private String destino;
	private String asunto;
	private String mensaje;
	private Correo eliminar;
	private int numBorrar;
	private JTextField textFieldNumBorrar;
	private JTextField textFieldOrigenPrimero;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GestorCorreo frame = new GestorCorreo();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public GestorCorreo() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 600, 450);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		panelMenu = new JPanel();
		panelMenu.setBounds(0, 0, 586, 413);
		contentPane.add(panelMenu);
		panelMenu.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Gestor de Correo");
		lblNewLabel.setBounds(10, 10, 566, 25);
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelMenu.add(lblNewLabel);
		
		JButton btnMostrarCorreos = new JButton("Mostrar Correos");
		btnMostrarCorreos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = 1;
				preguntarCarpeta();
			}
		});
		btnMostrarCorreos.setBounds(160, 100, 250, 25);
		btnMostrarCorreos.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMenu.add(btnMostrarCorreos);
		
		JButton btnAñadirCorreo = new JButton("Añadir Correo");
		btnAñadirCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = 2;
				preguntarCarpeta();
			}
		});
		btnAñadirCorreo.setBounds(160, 50, 250, 25);
		btnAñadirCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMenu.add(btnAñadirCorreo);
		
		JButton btnBorrarCorreo = new JButton("Borrar Correo");
		btnBorrarCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = 3;
				preguntarCarpeta();
			}
		});
		btnBorrarCorreo.setBounds(160, 150, 250, 25);
		btnBorrarCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMenu.add(btnBorrarCorreo);
		
		JButton btnMostrar1rCorreo = new JButton("Mostrar 1r correo de un emisor");
		btnMostrar1rCorreo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				panel = 4;
				preguntarCarpeta();
			}
		});
		btnMostrar1rCorreo.setBounds(160, 200, 250, 25);
		btnMostrar1rCorreo.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMenu.add(btnMostrar1rCorreo);
		
		JButton btnSalir = new JButton("Salir");
		btnSalir.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		btnSalir.setBounds(160, 250, 250, 25);
		btnSalir.setFont(new Font("Tahoma", Font.BOLD, 13));
		panelMenu.add(btnSalir);
		
		panelCarpetas = new JPanel();
		panelCarpetas.setBounds(0, 0, 586, 413);
		contentPane.add(panelCarpetas);
		panelCarpetas.setLayout(null);
		
		JButton btnRecibidos = new JButton("Recibidos");
		btnRecibidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carpetas = true;
				mostrarPanel(panel, carpetas, recibidos);
			}
		});
		btnRecibidos.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnRecibidos.setBounds(160, 100, 250, 30);
		panelCarpetas.add(btnRecibidos);
		
		JButton btnEliminados = new JButton("Eliminados");
		btnEliminados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carpetas = false;
				mostrarPanel(panel, carpetas, eliminados);
			}
		});
		btnEliminados.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnEliminados.setBounds(160, 200, 250, 30);
		panelCarpetas.add(btnEliminados);
		
		JLabel lblTitulo = new JLabel("Elige la carpeta");
		lblTitulo.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblTitulo.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo.setBounds(10, 10, 566, 20);
		panelCarpetas.add(lblTitulo);
		
		panelMostrar = new JPanel();
		panelMostrar.setBounds(0, 0, 586, 413);
		contentPane.add(panelMostrar);
		panelMostrar.setLayout(null);
		
		JLabel lblTitulo_1 = new JLabel("Mensajes Recibidos");
		lblTitulo_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblTitulo_1.setBounds(10, 10, 566, 20);
		lblTitulo_1.setFont(new Font("Tahoma", Font.BOLD, 14));
		panelMostrar.add(lblTitulo_1);
		
		textAreaRecibidos = new JTextArea();
		textAreaRecibidos.setBounds(150, 50, 400, 350);
		panelMostrar.add(textAreaRecibidos);
		
		JButton btnMostrarRecibidos = new JButton("Recibidos");
		btnMostrarRecibidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaRecibidos.setText(leerFichero("recibidos.txt"));
			}
		});
		btnMostrarRecibidos.setBounds(10, 100, 120, 25);
		panelMostrar.add(btnMostrarRecibidos);
		
		JButton btnMostrarEliminados = new JButton("Eliminados");
		btnMostrarEliminados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				textAreaRecibidos.setText(leerFichero("eliminados.txt"));
			}
		});
		btnMostrarEliminados.setBounds(10, 135, 120, 25);
		panelMostrar.add(btnMostrarEliminados);
		
		JButton btnCancelar = new JButton("Cancelar");
		btnCancelar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMenuInicial();
			}
		});
		btnCancelar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelar.setBounds(10, 280, 120, 25);
		panelMostrar.add(btnCancelar);
		
		panelAñadir = new JPanel();
		panelAñadir.setBounds(0, 0, 586, 413);
		contentPane.add(panelAñadir);
		panelAñadir.setLayout(null);
		
		JLabel lblOrigen = new JLabel("Origen");
		lblOrigen.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrigen.setBounds(10, 20, 80, 20);
		panelAñadir.add(lblOrigen);
		
		JLabel lblDestino = new JLabel("Destino");
		lblDestino.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDestino.setBounds(10, 50, 80, 20);
		panelAñadir.add(lblDestino);
		
		JLabel lblAsunto = new JLabel("Asunto");
		lblAsunto.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAsunto.setBounds(10, 80, 80, 20);
		panelAñadir.add(lblAsunto);
		
		textFieldOrigen = new JTextField();
		textFieldOrigen.setBounds(120, 20, 450, 20);
		panelAñadir.add(textFieldOrigen);
		textFieldOrigen.setColumns(10);
		
		textFieldDestino = new JTextField();
		textFieldDestino.setBounds(120, 50, 450, 20);
		panelAñadir.add(textFieldDestino);
		textFieldDestino.setColumns(10);
		
		textFieldAsunto = new JTextField();
		textFieldAsunto.setBounds(120, 80, 450, 20);
		panelAñadir.add(textFieldAsunto);
		textFieldAsunto.setColumns(10);
		
		JTextArea textAreaMensaje = new JTextArea();
		textAreaMensaje.setBounds(20, 120, 550, 220);
		panelAñadir.add(textAreaMensaje);
		
		JButton btnCrear = new JButton("Crear");
		btnCrear.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				origen = textFieldOrigen.getText();
				destino = textFieldDestino.getText();
				asunto = textFieldAsunto.getText();
				mensaje = textAreaMensaje.getText();
				Correo nuevo = new Correo(origen, destino, asunto, mensaje);
				if (carpetas) {
					recibidos.añadirC(nuevo);
					String nombre = "recibidos.txt";
					crearFichero(recibidos, nombre);
				} else {
					eliminados.añadirC(nuevo);
					String nombre = "eliminados.txt";
					crearFichero(recibidos, nombre);
				}
				textFieldOrigen.setText("");
				textFieldDestino.setText("");
				textFieldAsunto.setText("");
				textAreaMensaje.setText("");
			}
		});
		btnCrear.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCrear.setBounds(300, 380, 120, 25);
		panelAñadir.add(btnCrear);
		
		JButton btnCancelarNueva = new JButton("Cancelar");
		btnCancelarNueva.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMenuInicial();
			}
		});
		btnCancelarNueva.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnCancelarNueva.setBounds(440, 380, 120, 25);
		panelAñadir.add(btnCancelarNueva);
		
		panelEliminar = new JPanel();
		panelEliminar.setBounds(0, 0, 586, 413);
		contentPane.add(panelEliminar);
		panelEliminar.setLayout(null);
		
		JLabel lbltitulo_2 = new JLabel("Borrar correo");
		lbltitulo_2.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltitulo_2.setBounds(10, 10, 566, 25);
		panelEliminar.add(lbltitulo_2);
		
		JLabel lblCarp = new JLabel("Carpeta");
		lblCarp.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblCarp.setBounds(10, 60, 100, 20);
		panelEliminar.add(lblCarp);
		
		JLabel lblOrig = new JLabel("Origen");
		lblOrig.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblOrig.setBounds(110, 60, 100, 20);
		panelEliminar.add(lblOrig);
		
		textFieldAsuntoBorrar = new JTextField();
		textFieldAsuntoBorrar.setBounds(210, 60, 365, 20);
		panelEliminar.add(textFieldAsuntoBorrar);
		textFieldAsuntoBorrar.setColumns(10);
		
		JTextArea textAreaMensajeBorrar = new JTextArea();
		textAreaMensajeBorrar.setBounds(160, 90, 415, 310);
		panelEliminar.add(textAreaMensajeBorrar);
		
		JButton btnRecibidosBorrar = new JButton("Recibidos");
		btnRecibidosBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carpetas = true;
			}
		});
		btnRecibidosBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnRecibidosBorrar.setBounds(20, 100, 100, 25);
		panelEliminar.add(btnRecibidosBorrar);
		
		JButton btnEliminadosBorrar = new JButton("Eliminados");
		btnEliminadosBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				carpetas = false;
			}
		});
		btnEliminadosBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnEliminadosBorrar.setBounds(20, 135, 100, 25);
		panelEliminar.add(btnEliminadosBorrar);
		
		JButton btnAceptarBorrar = new JButton("Aceptar");
		btnAceptarBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				eliminar = new Correo("", "", "", "");
				numBorrar = Integer.parseInt(textFieldNumBorrar.getText());
				try {
					if (carpetas) {
						eliminar = recibidos.borrarC(numBorrar);
						eliminados.añadirC(eliminar);
						crearFichero(eliminados, "eliminados.txt");
					} else {
						eliminados.borrarC(numBorrar);
					}
					
					if (eliminar != null) {
						textAreaMensajeBorrar.setText(leerFichero("eliminados.txt"));
					}
					
				} catch (Exception e3) {
					e3.printStackTrace();
				}
			}
		});
		btnAceptarBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnAceptarBorrar.setBounds(20, 250, 100, 25);
		panelEliminar.add(btnAceptarBorrar);
		
		JButton btnCancelarBorrar = new JButton("Cancelar");
		btnCancelarBorrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMenuInicial();
			}
		});
		btnCancelarBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		btnCancelarBorrar.setBounds(20, 285, 100, 25);
		panelEliminar.add(btnCancelarBorrar);
		
		JLabel lblNumBorrar = new JLabel("Nº Correo");
		lblNumBorrar.setHorizontalAlignment(SwingConstants.CENTER);
		lblNumBorrar.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblNumBorrar.setBounds(20, 180, 100, 20);
		panelEliminar.add(lblNumBorrar);
		
		textFieldNumBorrar = new JTextField();
		textFieldNumBorrar.setBounds(20, 210, 100, 20);
		panelEliminar.add(textFieldNumBorrar);
		textFieldNumBorrar.setColumns(10);
		
		panelMostrarPrimero = new JPanel();
		panelMostrarPrimero.setBounds(0, 0, 586, 413);
		contentPane.add(panelMostrarPrimero);
		panelMostrarPrimero.setLayout(null);
		
		JLabel lbltitulo_3 = new JLabel("Mostrar el primero correo encontrado del emisor");
		lbltitulo_3.setHorizontalAlignment(SwingConstants.CENTER);
		lbltitulo_3.setFont(new Font("Tahoma", Font.BOLD, 16));
		lbltitulo_3.setBounds(10, 10, 566, 25);
		panelMostrarPrimero.add(lbltitulo_3);
		
		JLabel lblOrigPrimero = new JLabel("Origen");
		lblOrigPrimero.setBounds(110, 60, 100, 20);
		panelMostrarPrimero.add(lblOrigPrimero);
		
		JButton btnAceptarPrimero = new JButton("Aceptar");
		btnAceptarPrimero.setBounds(20, 250, 100, 25);
		panelMostrarPrimero.add(btnAceptarPrimero);
		
		JButton btnCancelarPrimero = new JButton("Cancelar");
		btnCancelarPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mostrarMenuInicial();
			}
		});
		btnCancelarPrimero.setBounds(20, 285, 100, 25);
		panelMostrarPrimero.add(btnCancelarPrimero);
		
		textFieldOrigenPrimero = new JTextField();
		textFieldOrigenPrimero.setBounds(210, 60, 365, 20);
		panelMostrarPrimero.add(textFieldOrigenPrimero);
		textFieldOrigenPrimero.setColumns(10);
		
		JTextArea textAreaMensajePrimero = new JTextArea();
		textAreaMensajePrimero.setBounds(160, 90, 415, 310);
		panelMostrarPrimero.add(textAreaMensajePrimero);
		
		JRadioButton rdbtnRecibidos = new JRadioButton("Recibidos");
		rdbtnRecibidos.setSelected(true);
		rdbtnRecibidos.setBounds(20, 100, 100, 25);
		panelMostrarPrimero.add(rdbtnRecibidos);
		
		JRadioButton rdbtnEliminados = new JRadioButton("Eliminados");
		rdbtnEliminados.setBounds(20, 135, 100, 25);
		panelMostrarPrimero.add(rdbtnEliminados);
		
		JPanel panel_3 = new JPanel();
		panel_3.setBounds(0, 0, 586, 413);
		contentPane.add(panel_3);
		panel_3.setLayout(null);
		
		btnAceptarPrimero.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Correo encontrado = new Correo("", "", "", "");
				origen = textFieldOrigenPrimero.getText();
				if (rdbtnRecibidos.isSelected()) {
					try {
						encontrado = recibidos.buscarC(origen);
						if (encontrado != null) {
							textAreaMensajePrimero.setText(leerFichero("recibidos.txt"));
						} else {
							textAreaMensajePrimero.setText("No ha sido posible encontrar ese correo en recibidos.");
						}
					} catch (Exception e4) {
						e4.printStackTrace();
					}

				} else if (rdbtnEliminados.isSelected()) {
					try {
						encontrado = eliminados.buscarC(origen);
						if (encontrado != null) {
							textAreaMensajePrimero.setText(leerFichero("eliminados.txt"));
						} else {
							textAreaMensajePrimero.setText("No ha sido posible encontrar ese correo en eliminados.");
						}
					} catch (Exception e5) {
						e5.printStackTrace();
					}
				}
			}
		});
		
		rdbtnRecibidos.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnEliminados.setSelected(false);
			}
		});
		
		rdbtnEliminados.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				rdbtnRecibidos.setSelected(false);
			}
		});
		
		mostrarMenuInicial();
	}
	
	public void preguntarCarpeta() {
		panelMenu.setVisible(false);
		panelMostrar.setVisible(false);
		panelAñadir.setVisible(false);
		panelEliminar.setVisible(false);
		panelMostrarPrimero.setVisible(false);
		
		panelCarpetas.setVisible(true);
	}
	
	public void mostrarMenuInicial() {
		panelMostrar.setVisible(false);
		panelAñadir.setVisible(false);
		panelEliminar.setVisible(false);
		panelMostrarPrimero.setVisible(false);
		panelCarpetas.setVisible(false);
		
		panelMenu.setVisible(true);
	}
	
	public void mostrarMensajes(CarpetaCorreo carpeta) {
		panelMenu.setVisible(false);
		panelAñadir.setVisible(false);
		panelEliminar.setVisible(false);
		panelMostrarPrimero.setVisible(false);
		panelCarpetas.setVisible(false);
		
		panelMostrar.setVisible(true);
		
		textAreaRecibidos.setText(carpeta.toString());
	}
	
	public void mostrarAñadir() {
		panelMenu.setVisible(false);
		panelEliminar.setVisible(false);
		panelMostrarPrimero.setVisible(false);
		panelCarpetas.setVisible(false);
		panelMostrar.setVisible(false);
		
		panelAñadir.setVisible(true);
	}
	
	public void mostrarEliminar() {
		panelMenu.setVisible(false);
		panelMostrarPrimero.setVisible(false);
		panelCarpetas.setVisible(false);
		panelMostrar.setVisible(false);
		panelAñadir.setVisible(false);
		
		panelEliminar.setVisible(true);
	}
	
	public void mostrarPrimero() {
		panelMenu.setVisible(false);
		panelCarpetas.setVisible(false);
		panelMostrar.setVisible(false);
		panelAñadir.setVisible(false);
		panelEliminar.setVisible(false);
		
		panelMostrarPrimero.setVisible(true);
	}
	
	public void mostrarPanel(int panel, boolean carpeta, CarpetaCorreo carp) {
		if (panel == 1) {
			mostrarMensajes(carp);
		} else if (panel == 2) {
			mostrarAñadir();
		} else if (panel == 3) {
			mostrarEliminar();
		} else if (panel == 4) {
			mostrarPrimero();
		}
	}
	
	public void crearFichero(CarpetaCorreo correo, String nombre) {
		FileWriter escritor = null;
		PrintWriter printEscritor = null;
		try {
			escritor = new FileWriter(nombre);
			printEscritor = new PrintWriter(escritor);
			
			Correo [] list = correo.getListaCorreos();
			
			for (Correo c : list) {
				if (c != null) {
					printEscritor.println(c);
				} else {
					;
				}
			}

		}  catch (Exception e1) {
            e1.printStackTrace();
        } finally {
           try { if (null != escritor) { escritor.close(); }  
           } catch (Exception e2) { e2.printStackTrace(); }
        }
	}
	
	public String leerFichero(String nombre) {
		String text = "";
		FileReader fileReader = null;
		BufferedReader bufferedReader = null;
		try {
			File fichero = new File(nombre);
			fileReader = new FileReader(fichero);
			bufferedReader = new BufferedReader(fileReader);
			
			String linea = "";
			while ((linea = bufferedReader.readLine()) != null) {
				text += linea + "\n";
		}
		      
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
	         try{                    
	        	 if(fileReader != null){   
	            	fileReader.close();     
	            }                  
	         }catch (Exception e2){ 
	            e2.printStackTrace();
	         }
		}
		return text;
	}
}
