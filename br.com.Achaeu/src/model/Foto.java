package model;

public class Foto extends IEntidade{
    private String diretorioAbsoluto;
    private Integer idLocal;
    private Local local;

    public Foto() {
        super();
        this.local = new Local();
    }    

    public Foto(String diretorioAbsoluto, Integer idLocal, Local local, Integer id) {
        super(id);
        this.diretorioAbsoluto = diretorioAbsoluto;
        this.idLocal = idLocal;
        this.local = local;
    }

    public String getDiretorioAbsoluto() {
        return diretorioAbsoluto;
    }

    public void setDiretorioAbsoluto(String diretorioAbsoluto) {
        this.diretorioAbsoluto = diretorioAbsoluto;
    }

    public Integer getIdLocal() {
        return idLocal;
    }

    public void setIdLocal(Integer idLocal) {
        this.idLocal = idLocal;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }

    @Override
    public String toString() {
        return "Foto{" + "diretorioAbsoluto=" + diretorioAbsoluto + ", idLocal=" + idLocal + ", local=" + local + '}';
    }    
}
