/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ventanas.reportes;

import java.awt.Color;
import java.awt.GridLayout;
import java.util.ArrayList;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import modelo.Area;
import modelo.Empleado;
import modelo.Manager;
import modelo.Sistema;

/**
 *
 * @author esteb
 */
public class ReporteArea extends javax.swing.JDialog {
    private Sistema modelo;
    private ArrayList<Area> cacheAreas;

    /**
     * Creates new form ReporteArea
     */
    public ReporteArea(java.awt.Frame parent, boolean modal, Sistema modelo) {
        super(parent, modal);
        initComponents();

        this.modelo = modelo;
        this.cacheAreas = new ArrayList<Area>();

        this.lblDetalle.setText("Detalle: -");

        this.tblAreas.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        this.tblAreas.getSelectionModel().addListSelectionListener(
                new javax.swing.event.ListSelectionListener() {
                    @Override
                    public void valueChanged(javax.swing.event.ListSelectionEvent e) {
                        if (!e.getValueIsAdjusting()) {
                            int fila = tblAreas.getSelectedRow();
                            if (fila >= 0 && fila < cacheAreas.size()) {
                                Area a = cacheAreas.get(fila);
                                poblarGrillaEmpleados(a);
                            }
                        }
                    }
                }

        );
        this.cargarAreasOrdenadasPorPorcentajeDesc();
        this.setLocationRelativeTo(parent);

    }

    private void cargarAreasOrdenadasPorPorcentajeDesc() {
        DefaultTableModel modeloTabla = (DefaultTableModel) this.tblAreas.getModel();

        modeloTabla.setRowCount(0);
        cacheAreas.clear();

        ArrayList<Area> todas = this.modelo.listarAreasOrdenadasPorNombre();
        ArrayList<Area> copia = new ArrayList<Area>();
        int i = 0;
        while (i < todas.size()) {
            copia.add(todas.get(i));
            i++;
        }

        int j = 0;
        while (j < copia.size()) {
            int max = j;
            int k = j + 1;
            while (k < copia.size()) {
                double pctK = porcentaje(copia.get(k));
                double pctM = porcentaje(copia.get(max));
                if (pctK > pctM) {
                    max = k;
                }
                k++;
            }
            if (max != j) {
                Area tmp = copia.get(j);
                copia.set(j, copia.get(max));
                copia.set(max, tmp);
            }
            j++;
        }

        int t = 0;
        while (t < copia.size()) {
            Area a = copia.get(t);
            double pct = porcentaje(a);
            double presupuesto = a.getPresupuestoAnual();
            double asignado = a.salarioAnualAsignado();
            int cant = this.modelo.getEmpleadosPorArea(a).size();

            Object[] fila = {
                    a.getNombre(),
                    redondear2(pct),
                    redondear2(presupuesto),
                    redondear2(asignado),
                    cant
            };

            modeloTabla.addRow(fila);
            cacheAreas.add(a);
            t++;
        }

        if (!cacheAreas.isEmpty()) {
            this.tblAreas.setRowSelectionInterval(0, 0);
            poblarGrillaEmpleados(cacheAreas.get(0));
        } else {
            this.pnlEmpleados.removeAll();
            this.pnlEmpleados.revalidate();
            this.pnlEmpleados.repaint();
            this.lblDetalle.setText("Detalle: -");
        }
    }

    private double porcentaje(Area a) {
        if (a.getPresupuestoAnual() <= 0) {
            return 0;
        }
        double pct = (a.salarioAnualAsignado() * 100.0) / a.getPresupuestoAnual();
        return pct;
    }

    private double redondear2(double v) {
        return Math.round(v * 100.0) / 100.0;
    }

     // CHAT GPT : poblarGrillaEmpleados 

    private void poblarGrillaEmpleados(Area area) {
        // obtenemos empleados del área usando el método del sistema
        ArrayList<Empleado> emps = this.modelo.getEmpleadosPorArea(area);

        // ordenar empleados por nombre (selección)
        int j = 0;
        while (j < emps.size()) {
            int min = j;
            int k = j + 1;
            while (k < emps.size()) {
                String a = emps.get(k).getNombre();
                String b = emps.get(min).getNombre();
                if (a.compareToIgnoreCase(b) < 0) {
                    min = k;
                }
                k++;
            }
            if (min != j) {
                Empleado tmp = emps.get(j);
                emps.set(j, emps.get(min));
                emps.set(min, tmp);
            }
            j++;
        }

        // buscar salario máximo para escalar colores
        double maxSal = 0;
        int m = 0;
        while (m < emps.size()) {
            if (emps.get(m).getSalarioMensual() > maxSal) {
                maxSal = emps.get(m).getSalarioMensual();
            }
            m++;
        }
        if (maxSal <= 0) {
            maxSal = 1.0;
        }

        // definimos las columnas de la grilla
       
        int columnas = (emps.size() > 6) ? 3 : 2;

        this.pnlEmpleados.removeAll();
        this.pnlEmpleados.setLayout(new GridLayout(0, columnas, 6, 6));

        int e = 0;
        while (e < emps.size()) {
            Empleado emp = emps.get(e);

            double ratio = emp.getSalarioMensual() / maxSal; // 0..1
            int blue = (int) (ratio * 255);
            if (blue < 0) {
                blue = 0;
            }
            if (blue > 255) {
                blue = 255;
            }

            JButton btn = new JButton(emp.getNombre() + " - $" + redondear2(emp.getSalarioMensual()));
            btn.setBackground(new Color(0, 0, blue));
            btn.setForeground(blue > 128 ? Color.WHITE : Color.BLACK);
            btn.setFocusPainted(false);

            btn.addActionListener(ev -> {
                Manager mng = emp.getManager();
                String nomMng = (mng == null) ? "-" : (mng.getNombre() + " (" + mng.getCi() + ")");
                //String ruta = (emp.getRutaCV() == null || emp.getRutaCV().isEmpty()) ? "-" : emp.getRutaCV();

                String detalle = "Nombre: " + safe(emp.getNombre()) + "   |   CI: " + emp.getCi() + "\n"
                        + "Celular: " + emp.getCelular() + "   |   Salario: $" + redondear2(emp.getSalarioMensual())
                        + "\n"
                        + "Área: " + safe(area.getNombre()) + "   |   Manager: " + nomMng + "\n";
                       // + "CV: " + ruta;

                lblDetalle.setText("Detalle: " + emp.getNombre() + " (" + emp.getCi() + ")");
                JOptionPane.showMessageDialog(
                        ReporteArea.this,
                        detalle,
                        "Empleado",
                        JOptionPane.INFORMATION_MESSAGE);
            });

            this.pnlEmpleados.add(btn);
            e++;
        }

        this.pnlEmpleados.revalidate();
        this.pnlEmpleados.repaint();

        if (emps.isEmpty()) {
            this.lblDetalle.setText("Detalle: (sin empleados en el área)");
        } else {
            this.lblDetalle.setText("Detalle: seleccione un empleado");
        }
    }

    private String safe(String s) {
        return (s == null) ? "-" : s;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated
    // Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lblTitulo = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tblAreas = new javax.swing.JTable();
        pnlInferior = new javax.swing.JPanel();
        scrollEmpleados = new javax.swing.JScrollPane();
        pnlEmpleados = new javax.swing.JPanel();
        lblDetalle = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);

        lblTitulo.setText("Estado de Areas");

        tblAreas.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][] {
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null },
                        { null, null, null, null, null }
                },
                new String[] {
                        "Area", "% Asignado", "Presupuesto", "Asignado", "Empleados"
                }) {
            boolean[] canEdit = new boolean[] {
                    false, false, false, true, true
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        jScrollPane1.setViewportView(tblAreas);

        javax.swing.GroupLayout pnlEmpleadosLayout = new javax.swing.GroupLayout(pnlEmpleados);
        pnlEmpleados.setLayout(pnlEmpleadosLayout);
        pnlEmpleadosLayout.setHorizontalGroup(
                pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 145, Short.MAX_VALUE));
        pnlEmpleadosLayout.setVerticalGroup(
                pnlEmpleadosLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGap(0, 212, Short.MAX_VALUE));

        scrollEmpleados.setViewportView(pnlEmpleados);

        lblDetalle.setText("Detalle");

        javax.swing.GroupLayout pnlInferiorLayout = new javax.swing.GroupLayout(pnlInferior);
        pnlInferior.setLayout(pnlInferiorLayout);
        pnlInferiorLayout.setHorizontalGroup(
                pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlInferiorLayout.createSequentialGroup()
                                .addGroup(pnlInferiorLayout
                                        .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(pnlInferiorLayout.createSequentialGroup()
                                                .addGap(16, 16, 16)
                                                .addComponent(scrollEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                        148, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(pnlInferiorLayout.createSequentialGroup()
                                                .addGap(38, 38, 38)
                                                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 106,
                                                        javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap(117, Short.MAX_VALUE)));
        pnlInferiorLayout.setVerticalGroup(
                pnlInferiorLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(pnlInferiorLayout.createSequentialGroup()
                                .addGap(19, 19, 19)
                                .addComponent(scrollEmpleados, javax.swing.GroupLayout.PREFERRED_SIZE, 215,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(lblDetalle, javax.swing.GroupLayout.PREFERRED_SIZE, 24,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(110, Short.MAX_VALUE)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 348,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(pnlInferior, javax.swing.GroupLayout.PREFERRED_SIZE,
                                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(29, Short.MAX_VALUE))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 188,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(232, 232, 232)));
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lblTitulo, javax.swing.GroupLayout.PREFERRED_SIZE, 27,
                                        javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 139,
                                                javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(pnlInferior, javax.swing.GroupLayout.PREFERRED_SIZE,
                                                javax.swing.GroupLayout.DEFAULT_SIZE,
                                                javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(53, Short.MAX_VALUE)));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        // <editor-fold defaultstate="collapsed" desc=" Look and feel setting code
        // (optional) ">
        /*
         * If Nimbus (introduced in Java SE 6) is not available, stay with the default
         * look and feel.
         * For details see
         * http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(ReporteArea.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ReporteArea.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ReporteArea.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ReporteArea.class.getName()).log(java.util.logging.Level.SEVERE, null,
                    ex);
        }
        // </editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ReporteArea dialog = new ReporteArea(new javax.swing.JFrame(), true, new Sistema());
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lblDetalle;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JPanel pnlEmpleados;
    private javax.swing.JPanel pnlInferior;
    private javax.swing.JScrollPane scrollEmpleados;
    private javax.swing.JTable tblAreas;
    // End of variables declaration//GEN-END:variables
}
