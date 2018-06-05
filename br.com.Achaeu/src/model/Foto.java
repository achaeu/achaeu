package model;

public class Foto {
    private Integer id;
    private String diretorioAbsoluto;
    private Integer idLocal;

    public Foto(Integer id, String diretorioAbsoluto, Integer idLocal) {
        this.id = id;
        this.diretorioAbsoluto = diretorioAbsoluto;
        this.idLocal = idLocal;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
        return "Foto{" + "id=" + id + ", diretorioAbsoluto=" + diretorioAbsoluto + ", idLocal=" + idLocal + '}';
    }    
}
