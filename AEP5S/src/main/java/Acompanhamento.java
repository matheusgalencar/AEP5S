import java.time.LocalDateTime;

public class Acompanhamento {

    private long id;
    private long solicitacaoId;
    private String status;
    private String comentario;
    private LocalDateTime dataRegistro;

    public Acompanhamento(long solicitacaoId, String status, String comentario) {
        this.solicitacaoId = solicitacaoId;
        this.status = status;
        this.comentario = comentario;
        this.dataRegistro = LocalDateTime.now();
    }

    public long getId() {
        return id;
    }

    public long getSolicitacaoId() {
        return solicitacaoId;
    }

    public String getStatus() {
        return status;
    }

    public String getComentario() {
        return comentario;
    }

    public LocalDateTime getDataRegistro() {
        return dataRegistro;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setSolicitacaoId(long solicitacaoId) {
        this.solicitacaoId = solicitacaoId;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public void setDataRegistro(LocalDateTime dataRegistro) {
        this.dataRegistro = dataRegistro;
    }
}
