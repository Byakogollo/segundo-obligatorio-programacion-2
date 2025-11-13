/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package ventanas.personal;

import java.util.Objects;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import modelo.Manager;
import modelo.Sistema;

/**
 *
 * @author Byakogollo
 */
public class VentanaModificarManager extends javax.swing.JDialog {
    private Sistema modelo;
    /**
     * Creates new form VentanaModificarManager
     */
    public VentanaModificarManager(java.awt.Frame parent, boolean modal, Sistema modelo) {
        super(parent, modal);
        this.modelo = modelo;
        initComponents();
        this.lstManagers.setListData( this.modelo.getManagers().toArray(new Manager[0]));
        
    }

     private void MostrarDatos(Manager seleccion){
                      
                
               
                DefaultTableModel table = (DefaultTableModel) tblEmpleados.getModel();
                table.setRowCount(0);
                
               
                
                for(int i = 0; i<seleccion.getEmpleadosACargo().size();i++){
                                    
                
                table.insertRow(0,new Object[] {
                   seleccion.getEmpleadosACargo().get(i).getNombre(),
                   seleccion.getEmpleadosACargo().get(i).getCi(),
                   seleccion.getEmpleadosACargo().get(i).getCelular(),
                   seleccion.getEmpleadosACargo().get(i).getSalarioMensual(),
                                                            
                });
                
               
    }
                 this.txtAntiguedad.setText(""+seleccion.getAntiguedad());
                this.txtCedula.setText(""+seleccion.getCi());
                this.txtNombre.setText(seleccion.getNombre());
                this.spnTelefono.setValue(seleccion.getCelular());
                 
    }
     
   
     
 
     
     
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        lstManagers = new javax.swing.JList<>();
        lblTitulo = new javax.swing.JLabel();
        lblAntiguedad = new javax.swing.JLabel();
        lblNombre = new javax.swing.JLabel();
        lblCedula = new javax.swing.JLabel();
        lblTelefono = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tblEmpleados = new javax.swing.JTable();
        btnCancelar = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        txtAntiguedad = new javax.swing.JTextField();
        txtNombre = new javax.swing.JTextField();
        txtCedula = new javax.swing.JTextField();
        spnTelefono = new javax.swing.JSpinner();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        getContentPane().setLayout(null);

        lstManagers.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lstManagersMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(lstManagers);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(70, 150, 140, 230);

        lblTitulo.setFont(new java.awt.Font("Segoe UI", 1, 36)); // NOI18N
        lblTitulo.setText("Modificar Manager");
        getContentPane().add(lblTitulo);
        lblTitulo.setBounds(230, 20, 330, 80);

        lblAntiguedad.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblAntiguedad.setText("Antiguedad");
        getContentPane().add(lblAntiguedad);
        lblAntiguedad.setBounds(330, 260, 71, 20);

        lblNombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblNombre.setText("Nombre");
        getContentPane().add(lblNombre);
        lblNombre.setBounds(330, 140, 50, 20);

        lblCedula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblCedula.setText("Cedula");
        getContentPane().add(lblCedula);
        lblCedula.setBounds(330, 180, 42, 20);

        lblTelefono.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblTelefono.setText("Telefono");
        getContentPane().add(lblTelefono);
        lblTelefono.setBounds(330, 220, 52, 20);

        tblEmpleados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Cedula", "Celular", "Salario"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Double.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(tblEmpleados);

        getContentPane().add(jScrollPane2);
        jScrollPane2.setBounds(70, 420, 660, 180);

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });
        getContentPane().add(btnCancelar);
        btnCancelar.setBounds(520, 310, 150, 60);

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });
        getContentPane().add(btnModificar);
        btnModificar.setBounds(280, 310, 150, 60);

        txtAntiguedad.setEditable(false);
        txtAntiguedad.setText("Seleccione un manager");
        getContentPane().add(txtAntiguedad);
        txtAntiguedad.setBounds(520, 260, 150, 22);

        txtNombre.setEditable(false);
        txtNombre.setText("Seleccione un manager");
        getContentPane().add(txtNombre);
        txtNombre.setBounds(520, 140, 150, 22);

        txtCedula.setEditable(false);
        txtCedula.setText("Seleccione un manager");
        getContentPane().add(txtCedula);
        txtCedula.setBounds(520, 180, 150, 22);
        getContentPane().add(spnTelefono);
        spnTelefono.setBounds(520, 220, 150, 22);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("Managers");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(70, 120, 80, 30);

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("Empleados");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 390, 80, 30);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
                                              
            JOptionPane alerta = new JOptionPane();
       int seleccion = alerta.showConfirmDialog(this,"Desea salir?", "Cancelar", JOptionPane.YES_NO_CANCEL_OPTION);
       
       if(seleccion == alerta.YES_OPTION){
           this.dispose();
       }
                                               

    }//GEN-LAST:event_btnCancelarActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        Manager m = (Manager) this.lstManagers.getSelectedValue();
        if(!Objects.equals((Integer) this.spnTelefono.getValue(), m.getCelular()) && (Integer) this.spnTelefono.getValue() > 0){
            
            
            if(JOptionPane.showConfirmDialog(null, "Desea modificar el contacto del manager?", "Advertencia", JOptionPane.OK_CANCEL_OPTION) == JOptionPane.YES_OPTION){
                this.modelo.modificarTelefonoManager(m.getCi(), (Integer)this.spnTelefono.getValue());
                JOptionPane.showMessageDialog(null,"El contacto ha sido modificado","Exito", JOptionPane.INFORMATION_MESSAGE);
                this.MostrarDatos((Manager)this.lstManagers.getSelectedValue());
            }
            
            
        }else{
            JOptionPane.showMessageDialog(null, "El numero nuevo no puede ser igual al anteriormente guardado", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnModificarActionPerformed

    private void lstManagersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lstManagersMouseClicked
       if(evt.getClickCount() >= 1){
           
        Manager m = (Manager) this.lstManagers.getSelectedValue();
        this.MostrarDatos(m);
        }
    }//GEN-LAST:event_lstManagersMouseClicked

    /**
     * @param args the command line arguments
     */
   

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel lblAntiguedad;
    private javax.swing.JLabel lblCedula;
    private javax.swing.JLabel lblNombre;
    private javax.swing.JLabel lblTelefono;
    private javax.swing.JLabel lblTitulo;
    private javax.swing.JList<Manager> lstManagers;
    private javax.swing.JSpinner spnTelefono;
    private javax.swing.JTable tblEmpleados;
    private javax.swing.JTextField txtAntiguedad;
    private javax.swing.JTextField txtCedula;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
