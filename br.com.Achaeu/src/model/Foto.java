package model;

public class Foto extends IEntidade{
    private String diretorioAbsoluto;
    private Integer idLocal;

    public Foto() {
        super();
    }    
    
    public Foto(Integer id, String diretorioAbsoluto, Integer idLocal) {
        super(id);
        this.diretorioAbsoluto = diretorioAbsoluto;
        this.idLocal = idLocal;
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

    @Override
    public String toString() {
        return "Foto{" + "id=" + super.getId() + ", diretorioAbsoluto=" + diretorioAbsoluto + ", idLocal=" + idLocal + '}';
    }    
}
