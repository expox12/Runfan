package mycompany.runfan;

/**
 * Created by macmini02 on 18.04.16.
 */
public class item_object {

    private String titulo;
    private int icono;

    public item_object(String titulo, int icono) {
        this.titulo = titulo;
        this.icono = icono;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public int getIcono() {
        return icono;
    }

    public void setIcono(int icono) {
        this.icono = icono;
    }
}
