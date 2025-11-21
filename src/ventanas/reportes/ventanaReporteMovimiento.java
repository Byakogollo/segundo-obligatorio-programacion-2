/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ventanas.reportes;

import java.util.ArrayList;
import javax.swing.ComboBoxModel;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JComboBox;
import javax.swing.table.DefaultTableModel;

import modelo.Area;
import modelo.Empleado;
import modelo.Movimiento;
import modelo.Sistema;

/**
 *
 * @author esteb
 */
public class ventanaReporteMovimiento extends javax.swing.JDialog {
    private Sistema modelo;

    /**
     * Creates new form ventanaReporteArea
     */
    public ventanaReporteMovimiento(java.awt.Frame parent, boolean modal, Sistema modelo) {
        super(parent, modal);
        initComponents();
        this.modelo = modelo;
        this.cargarTabla();

        this.cargarCombos();

    }

    private void cargarCombos() {

        DefaultComboBoxModel<Object> modeloEmpleados = new DefaultComboBoxModel<>();

        modeloEmpleados.addElement("Todos");

        for (Empleado e : this.modelo.getEmpleados()) {
            modeloEmpleados.addElement(e);
        }
        this.cmbFiltrarEmpleados.setModel(modeloEmpleados);

        DefaultComboBoxModel<Object> modeloAreaOrigen = new DefaultComboBoxModel<>();
        modeloAreaOrigen.addElement("Todas");
        for (Area a : this.modelo.getAreas()) {
            modeloAreaOrigen.addElement(a);
        }
        this.cmbFiltrarAreaOrigen.setModel(modeloAreaOrigen);

        DefaultComboBoxModel<Object> modeloAreaDestino = new DefaultComboBoxModel<>();
        modeloAreaDestino.addElement("Todas");
        for (Area a : this.modelo.getAreas()) {
            modeloAreaDestino.addElement(a);
        }
        this.cmbFiltrarAreasDestino.setModel(modeloAreaDestino);

        DefaultComboBoxModel<String> modeloMeses = new DefaultComboBoxModel<>();
        modeloMeses.addElement("Todos");
        for (int i = 1; i <= 12; i++) {
            modeloMeses.addElement(String.valueOf(i));
        }
        this.cmbFiltrarMeses.setModel(modeloMeses);
    }

    private void cargarTabla() {

        DefaultTableModel tabla = (DefaultTableModel) this.tblMovimientos.getModel();

        tabla.setRowCount(0);

        ArrayList<Movimiento> lista = this.modelo.listarMovimientosPorMesAsc();

        int i = 0;
        while (i < lista.size()) {
            Movimiento mov = lista.get(i);

            int mes = mov.getMes();
            Area origen = mov.getOrigen();
            Area destino = mov.getDestino();
            Empleado emp = mov.getEmpleado();

            String nomOrigen = origen.getNombre();
            String nomDestino = destino.getNombre();
            String nomEmpleado = (emp.getNombre() + " (" + emp.getCi() + ")");

            tabla.addRow(new Object[] {
                    mes,
                    nomOrigen,
                    nomDestino,
                    nomEmpleado
            });

            i++;
        }
    }

    private void actualizarTablaConFiltros() {

        Object areaOrigenSeleccionada = this.cmbFiltrarAreaOrigen.getSelectedItem();
        Object areaDestinoSeleccionada = this.cmbFiltrarAreasDestino.getSelectedItem();
        Object empleadoSeleccionado = this.cmbFiltrarEmpleados.getSelectedItem();
        Object mesSeleccionado = this.cmbFiltrarMeses.getSelectedItem();

        javax.swing.table.DefaultTableModel tabla = (javax.swing.table.DefaultTableModel) tblMovimientos.getModel();

        tabla.setRowCount(0);



        System.out.println(empleadoSeleccionado.toString().split(" ")[0]);
        System.out.println(areaOrigenSeleccionada.toString());
        System.out.println(areaDestinoSeleccionada.toString());
        System.out.println(mesSeleccionado.toString());

        

        for (Movimiento mov : this.modelo.getMovimientos()) {
            if (empleadoSeleccionado != null &&
                    !"Todos".equals(empleadoSeleccionado.toString().split(" ")[0])) {
                if (!mov.getEmpleado().getNombre().equals(empleadoSeleccionado.toString().split(" ")[0])) {
                    continue;
                }
            }

            if (areaOrigenSeleccionada != null &&
                    !"Todas".equals(areaOrigenSeleccionada.toString())) {
                if (!mov.getOrigen().getNombre().equals(areaOrigenSeleccionada.toString())) {
                    continue;
                }
            }

            if (areaDestinoSeleccionada != null &&
                    !"Todas".equals(areaDestinoSeleccionada.toString())) {
                if (!mov.getDestino().getNombre().equals(areaDestinoSeleccionada.toString())) {
                    continue;
                }
            }

                if (mesSeleccionado != null &&
                        !"Todos".equals(mesSeleccionado.toString())) {
                    if (mov.getMes() != Integer.parseInt(mesSeleccionado.toString())) {
                        continue;
                    }
                }

                tabla.addRow(new Object[] {
                        mov.getMes(),
                        mov.getOrigen().getNombre(),
                        mov.getDestino().getNombre(),
                        mov.getEmpleado().getNombre() + " (" + mov.getEmpleado().getCi() + ")"
                });
            
        }
    }

    private void resetearFiltrosYTabla() {
   
    cmbFiltrarEmpleados.setSelectedIndex(0);
    cmbFiltrarAreaOrigen.setSelectedIndex(0);
    cmbFiltrarAreasDestino.setSelectedIndex(0);
    cmbFiltrarMeses.setSelectedIndex(0);

    
    actualizarTablaConFiltros();
}


    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        btnCerrar = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblMovimientos = new javax.swing.JTable();
        btnExportar = new javax.swing.JButton();
        lblFiltrarEmpleados = new javax.swing.JLabel();
        lblFiltrarMeses = new javax.swing.JLabel();
        lblFiltrarAreas2 = new javax.swing.JLabel();
        cmbFiltrarAreasDestino = new javax.swing.JComboBox<>();
        cmbFiltrarEmpleados = new javax.swing.JComboBox<>();
        cmbFiltrarMeses = new javax.swing.JComboBox<>();
        btnAplicarFiltros = new javax.swing.JButton();
        lblFiltrarAreas1 = new javax.swing.JLabel();
        cmbFiltrarAreaOrigen = new javax.swing.JComboBox<>();

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setFont(new java.awt.Font("Agency FB", 1, 36)); // NOI18N
        getContentPane().setLayout(null);

        btnCerrar.setText("Cerrar");
        btnCerrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCerrarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCerrar);
        btnCerrar.setBounds(670, 390, 140, 40);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 3, 36)); // NOI18N
        jLabel1.setText("Reporte Movimientos de Areas");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(140, 20, 540, 38);

        tblMovimientos.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null },
                        { null, null, null, null }
                },
                new String[] {
                        "Mes", "Area Origen", "Area Destino", "Empleado"
                }));
        jScrollPane1.setViewportView(tblMovimientos);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(40, 90, 430, 340);

        btnExportar.setText("Exportar Reporte");
        btnExportar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnExportarActionPerformed(evt);
            }
        });
        getContentPane().add(btnExportar);
        btnExportar.setBounds(510, 390, 140, 40);

        lblFiltrarEmpleados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFiltrarEmpleados.setText("Filtrar Empleados");
        getContentPane().add(lblFiltrarEmpleados);
        lblFiltrarEmpleados.setBounds(500, 120, 120, 40);

        lblFiltrarMeses.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFiltrarMeses.setText("Filtrar Meses");
        getContentPane().add(lblFiltrarMeses);
        lblFiltrarMeses.setBounds(500, 170, 120, 40);

        lblFiltrarAreas2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFiltrarAreas2.setText("Area Destino");
        getContentPane().add(lblFiltrarAreas2);
        lblFiltrarAreas2.setBounds(500, 270, 120, 40);

        getContentPane().add(cmbFiltrarAreasDestino);
        cmbFiltrarAreasDestino.setBounds(630, 280, 180, 26);

        cmbFiltrarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltrarEmpleadosActionPerformed(evt);
            }
        });
        getContentPane().add(cmbFiltrarEmpleados);
        cmbFiltrarEmpleados.setBounds(630, 130, 180, 26);

        cmbFiltrarMeses.setModel(new javax.swing.DefaultComboBoxModel<>(
                new String[] { "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12" }));
        cmbFiltrarMeses.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltrarMesesActionPerformed(evt);
            }
        });
        getContentPane().add(cmbFiltrarMeses);
        cmbFiltrarMeses.setBounds(630, 180, 180, 26);

        btnAplicarFiltros.setText("Resetear Filtros");
        btnAplicarFiltros.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAplicarFiltrosActionPerformed(evt);
            }
        });

        cmbFiltrarAreaOrigen.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltrarAreaOrigenActionPerformed(evt);
            }
        });

        cmbFiltrarAreasDestino.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cmbFiltrarAreasDestinoActionPerformed(evt);
            }
        });

        getContentPane().add(btnAplicarFiltros);
        btnAplicarFiltros.setBounds(600, 330, 130, 30);

        lblFiltrarAreas1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblFiltrarAreas1.setText("Area Origen");
        getContentPane().add(lblFiltrarAreas1);
        lblFiltrarAreas1.setBounds(500, 220, 120, 40);

        getContentPane().add(cmbFiltrarAreaOrigen);
        cmbFiltrarAreaOrigen.setBounds(630, 230, 180, 26);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnExportarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnExportarActionPerformed
        // TODO add your handling code here:
    }// GEN-LAST:event_btnExportarActionPerformed

    private void btnCerrarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnCerrarActionPerformed
        this.dispose();
    }// GEN-LAST:event_btnCerrarActionPerformed

    private void cmbFiltrarMesesActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmbFiltrarMesesActionPerformed
        actualizarTablaConFiltros();
    }// GEN-LAST:event_cmbFiltrarMesesActionPerformed

    private void cmbFiltrarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmbFiltrarEmpleadosActionPerformed
        actualizarTablaConFiltros();
    }// GEN-LAST:event_cmbFiltrarEmpleadosActionPerformed

    private void cmbFiltrarAreaOrigenActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmbFiltrarAreaOrigenActionPerformed
        actualizarTablaConFiltros();
    }// GEN-LAST:event_cmbFiltrarAreaOrigenActionPerformed

    private void cmbFiltrarAreasDestinoActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_cmbFiltrarAreasDestinoActionPerformed
        actualizarTablaConFiltros();
    }// GEN-LAST:event_cmbFiltrarAreasDestinoActionPerformed

    private void btnAplicarFiltrosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_btnAplicarFiltrosActionPerformed
        resetearFiltrosYTabla();
    }// GEN-LAST:event_btnAplicarFiltrosActionPerformed

    /**
     * @param args the command line arguments
     */

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAplicarFiltros;
    private javax.swing.JButton btnCerrar;
    private javax.swing.JButton btnExportar;
    private javax.swing.JComboBox<Object> cmbFiltrarAreaOrigen;
    private javax.swing.JComboBox<Object> cmbFiltrarAreasDestino;
    private javax.swing.JComboBox<Object> cmbFiltrarEmpleados;
    private javax.swing.JComboBox<String> cmbFiltrarMeses;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblFiltrarAreas1;
    private javax.swing.JLabel lblFiltrarAreas2;
    private javax.swing.JLabel lblFiltrarEmpleados;
    private javax.swing.JLabel lblFiltrarMeses;
    private javax.swing.JTable tblMovimientos;
    // End of variables declaration//GEN-END:variables
}
